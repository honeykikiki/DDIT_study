import { useState, useEffect, useRef } from "react";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

const RECONNECT_INTERVAL = 5000; // 5초 후 재연결 시도

const ChatPage = () => {
  const [messages, setMessages] = useState([]);
  const [messageInput, setMessageInput] = useState("");
  const [username, setUsername] = useState("");
  const [isNameSet, setIsNameSet] = useState(false);

  const stompClient = useRef(null); // STOMP 클라이언트를 useRef로 관리
  const isSubscribed = useRef(false); // 중복 구독 방지
  const sender = username;

  // 웹소켓 접속
  const connectWebSocket = () => {
    // 이미 연결이 되어있는 경우 중복 연결을 방지하기 위해
    if (stompClient.current) {
      stompClient.current.disconnect(); // 기존 연결 해제
      stompClient.current = null; // 초기화
      isSubscribed.current = false; // 구독 상태 초기화
    }

    console.log("웹소켓 연결 시도...");
    const socket = new SockJS("http://localhost:8080/ws");
    const client = Stomp.over(socket);

    client.connect(
      {},
      () => {
        console.log("웹소켓 연결 성공!");
        stompClient.current = client; // 새로운 STOMP 클라이언트 설정

        const roomId = "test";
        if (!isSubscribed.current) {
          client.subscribe(`/sub/chat/room/${roomId}`, (message) => {
            const newMessage = JSON.parse(message.body);
            setMessages((prevMessages) => [...prevMessages, newMessage]);
            console.log(newMessage, "받은 메시지");
          });
          isSubscribed.current = true; // 중복 구독 방지
        }
      },
      (error) => {
        console.error("웹소켓 연결 실패:", error);
        setTimeout(connectWebSocket, RECONNECT_INTERVAL); // 재연결 시도
      }
    );

    client.onclose = () => {
      console.warn("웹소켓 연결 종료됨. 재연결 시도...");
      setTimeout(connectWebSocket, RECONNECT_INTERVAL);
    };
  };

  useEffect(() => {
    connectWebSocket(); // 웹소켓 연결 시도

    return () => {
      if (stompClient.current) {
        stompClient.current.disconnect();
        console.log("웹소켓 연결 해제");
      }
    };
  }, []);

  useEffect(() => {
    const messageElements = document.querySelectorAll(".message");
    messageElements.forEach((element) => {
      setTimeout(() => {
        element.classList.add("show");
      }, 10);
    });
  }, [messages]);

  const sendMessage = () => {
    if (!stompClient.current || !stompClient.current.connected) {
      console.error("STOMP 클라이언트가 아직 연결되지 않았습니다.");
      return;
    }

    const roomId = "test";
    const message = {
      type: "TALK",
      roomId,
      sender: sender,
      message: messageInput,
      createDate: new Date(),
    };
    stompClient.current.send(`/pub/chat/message`, {}, JSON.stringify(message));
    setMessageInput("");
  };

  const handleNameSubmit = () => {
    localStorage.setItem("username", username);
    setIsNameSet(true);

    if (stompClient.current) {
      const roomId = "test";
      const joinMessage = {
        type: "JOIN",
        roomId: roomId,
        sender: username,
        createDate: new Date(),
      };
      stompClient.current.send(`/pub/chat/message`, {}, JSON.stringify(joinMessage));
    }
  };

  const [selectedFile, setSelectedFile] = useState(null);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    // setSelectedFile(event.target.files);
    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        setSelectedFile(reader.result); // Base64로 변환된 파일 저장
      };
    }
  };

  const sendFileMessage = () => {
    if (stompClient.current && selectedFile) {
      const message = {
        type: "FILE",
        roomId: "test",
        sender: username,
        fileData: selectedFile, // Base64 파일 데이터
        // fileName: "uploaded_file.jpg",
        time: new Date(),
      };
      stompClient.current.send(`/pub/chat/message`, {}, JSON.stringify(message));
      // setSelectedFile(null);
    }
  };

  return (
    <div>
      {!isNameSet && (
        <div className="modal">
          <div className="modal-content">
            <h2>Enter your name</h2>
            <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            <button onClick={handleNameSubmit}>Submit</button>
          </div>
        </div>
      )}

      <div>
        {messages.map((msg, index) => (
          <div key={index} className="message">
            {msg.sender || "알 수 없음"}: {msg.message} ({msg.createDate ?? "시간 없음"})
            {msg.type === "FILE" ? (
              <img src={"http://localhost:8080/ys" + msg.fileName} alt="uploaded" width="200px" />
            ) : (
              msg.message
            )}
          </div>
        ))}
      </div>

      <div className="input-container">
        <input type="text" value={messageInput} onChange={(e) => setMessageInput(e.target.value)} />
        <button onClick={sendMessage}>전송</button>
      </div>

      <input type="file" onChange={handleFileChange} />
      <button onClick={sendFileMessage}>파일 전송</button>
    </div>
  );
};

export default ChatPage;
