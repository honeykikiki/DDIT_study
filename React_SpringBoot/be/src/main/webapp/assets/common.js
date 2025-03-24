const RECONNECT_INTERVAL = 5000; // 5초 후 재연결 시도
const TALK = "TALK";
const JOIN = "JOIN";
const FILE = "FILE";
const CHAT = "CHAT";
let stompClientMap = {}; //
let isSubscribedMap = {}; // 구독 중인지 확인
let emp = null; // 이거 바꿔야 함 (진짜 empNo로)
// 임시로 사용
window.location.search.split("&").filter(item => {
  let [key, value] = item.split("=");
  if(key.includes("emplNo")) {
    emp = value;
  }
}); // 로그인 된 No 임시

// 알림 클래스
class Alert {
  dom = null;
  constructor(dom) {
      this.dom = dom;
  }

  setMessage(message) {
    this.dom.querySelector(".toast-header span").textContent = message.title;
    this.dom.querySelector(".toast-header small").textContent = message.empName;
    this.dom.querySelector(".toast-body").textContent = message.mssageCn;
  }

  show() {
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(this.dom);
    toastBootstrap.show();
  }
}

document.addEventListener("DOMContentLoaded", function() {
  let infoAlert = new Alert(document.getElementById('infoSuccess'));

  // 내 계정 알림 구독
  connectWebSocket({
    roomPath: "alert/room",
    chttRoomNo: emp, receiveMessage: (message) => {
      console.log(message, "alert" + message);

      if (message.type === TALK) {
        for (const dom of document.querySelectorAll(".chatRoom")) {
          // 내가 보고 있는 채팅방이 아닌 경우
          if (!dom.classList.contains("bg-body-secondary") && dom.dataset.chttRoomNo == message.chttRoomNo) {
            let badgeDom = dom.querySelector(".read-badge");
            let chatLastMsgDom = dom.querySelector(".chat-last-msg");
            let chatCreateDateDom = dom.querySelector(".chat-create-date");

            badgeDom.classList.remove("d-none");
            // 알림 카운트
            badgeDom.innerHTML = Number(badgeDom.innerHTML) + 1;
            // 채팅 내용
            chatLastMsgDom.innerHTML = message?.mssageCn ?? "내용 없음";
            // 채팅 받은 시간
            chatCreateDateDom.innerHTML = formatDate(message?.createDate ?? new Date(), "HH:mm");

            message.title = "메시지 알림";
            infoAlert.setMessage(message);
            // 내가 보낸 메세지는 안받기
            infoAlert.show();
            break; // 루프 중단
          }
        }
      }


    }
  });

  // 메시지 전송 이벤트 등록
  let submitMessageDom = document.querySelector("#submitMessage");
  if(submitMessageDom) {
    let messageInput = document.querySelector("#messageInput");
    messageInput.addEventListener("keyup", (e) => {
      let value = e.target.value;

      // 채팅을 입력하면 전송 버튼 활성화
      if(value.length > 0) {
        submitMessageDom.classList.add("text-primary");
      } else {
        submitMessageDom.classList.remove("text-primary");
      }

      if(e.keyCode === 13) { // 엔터 친 겨우
        let chttRoomNo = findOpenChatRoomNo();

        submitMessage({
          messageValue: messageInput.value,
          chttRoomNo,
          type: TALK,
          emplNo: emp
        });
        messageInput.value = "";
      }
    })

    submitMessageDom.addEventListener("click", () => {
      let chttRoomNo = findOpenChatRoomNo();

      submitMessage({
        messageValue: messageInput.value,
        chttRoomNo,
        type: TALK,
        emplNo: emp
      });
      messageInput.value = "";
    });

    let prevKey = null;
    messageInput.addEventListener("keydown", (e) => {
      // 15 => shift 51 => #
      console.log(prevKey,  e.keyCode)
      if (prevKey === 16 && e.keyCode === 51) {
        console.log("shift + #")
        // 여기서
      } else {

      }

      prevKey = e.keyCode;
    })
  }

  // 메세지 파일 업로드
  let uploadFiles = document.querySelector("#uploadFiles");
  if (uploadFiles) {
    uploadFiles.addEventListener("change", async (e) => {
      let files = e.target.files
      if(files.length > 0) {
        const formData = new FormData();
        for(const file of files) {
          formData.append("uploadFiles", file);
        }

        const response = await fetch("/message/file", {
          method: "POST",
          body: formData
        })

        const data = await response.json();

        let chttRoomNo = findOpenChatRoomNo();

        submitMessage({
          messageValue: data.fileVOList[0].fileName,
          chttRoomNo,
          type: FILE,
          emplNo: emp
        });

        e.target.value = null;
      }
    })
  }
});

/**
 *
 * @param chttRoomNo - 채팅방 id
 * @param messageSend - 메세지 보내는 JSON
 * @param receiveMessage - 메세지 받고 실행
 */
function connectWebSocket({roomPath = "chat/room", chttRoomNo, receiveMessage}) {
  let stompClient = stompClientMap[chttRoomNo];
  let isSubscribed = isSubscribedMap[chttRoomNo];
  if(stompClient) {
    stompClient.disconnect();
    stompClient = null;
    isSubscribed = false;
  }

  const socket = new SockJS("http://localhost:8888/ws");
  const client = Stomp.over(socket);
  client.debug = null;

  client.connect(
    {},
    () => {
      console.log("웹소켓 연결 성공! => " + roomPath + chttRoomNo);

      stompClientMap[chttRoomNo] = client;
      if(!isSubscribed) {
        client.subscribe(`/sub/${roomPath}/${chttRoomNo}`, (message) => {
          const newMessage = JSON.parse(message.body);
          // console.log(newMessage, "받은 메시지");
          receiveMessage(newMessage);
        });

        isSubscribedMap[chttRoomNo] = true;
      }
    },
    (error) => {
      console.error("웹소켓 연결 실패:", error);
      setTimeout(() => connectWebSocket({roomPath, chttRoomNo, receiveMessage}), RECONNECT_INTERVAL);
    }
  );

  client.onclose = () => {
    console.warn("웹소켓 연결 종료됨. 재연결 시도...");
    setTimeout(() => connectWebSocket({roomPath, chttRoomNo, receiveMessage}), RECONNECT_INTERVAL);
  };
}

// 웹소켓 끊기
function disconnectWebSocket({chttRoomNo}) {
  console.log("disconnect web socket " + chttRoomNo);

  if (stompClientMap[chttRoomNo]) {
    stompClientMap[chttRoomNo].disconnect();
  }
}

// 채팅 웹 소켓 연결 함수
function chatWebSocketConnect({chttRoomNo}) {
  connectWebSocket({
    roomPath: "chat/room",
    chttRoomNo, receiveMessage: (message) => {
      // 여기는 알림
      console.log(message, "recevide" + chttRoomNo);

      // 내가 보낸 메세지는 안받기
      if(emp == message.emplNo) return;

      buildChatMessage(
        document.querySelector("#realChatList"),
        {message}
      );

      // 채팅 보고 있는 화면이 맨 밑이 아닌경우
      let chatList = document.querySelector("#chatList");
      if (chatList.scrollHeight - chatList.scrollTop > 1200) {
        // 보고 있는 채팅방인 경우 채팅방 하단에 알림 뜨게 만들기
        document.querySelector("#chatInnerAlert").classList.remove("d-none");
        document.querySelector("#chatInnerAlert .content").innerHTML = message.mssageCn;
      } else {
        chatList.scrollTop = chatList.scrollHeight;
      }
    }
  });
}

function submitMessage({messageValue, type, chttRoomNo, emplNo}) {
  // 채팅 입력한게 없으면 채팅 안보내지게
  if(messageValue.length <= 0) return;

  if(!stompClientMap[chttRoomNo]) {
    console.warn("웹소켓이 아직 연결되지 않았습니다.");
    return;
  }

  const message = {
    type,
    chttRoomNo,
    emplNo,
    emplNm: "허성진",
    emplProfile: "",
    mssageCn: messageValue,
    creatDe: new Date(),
    bPrevChat: false,
  };

  stompClientMap[chttRoomNo]?.send(`/pub/chat/message`, {}, JSON.stringify(message));

  buildChatMessage(
    document.querySelector("#realChatList"),
    {message}
  );

  setTimeout(() => {
    let chatList = document.querySelector("#chatList");
    chatList.scrollTop = chatList.scrollHeight; // 채팅 밑으로 내리기
  }, 10)
}

/**
 * @param dom - 채팅이 들어갈 dom
 * @param message - 메세지 넘버
 * --- 하단 내용 message에 들어간 내용
 *             mssageCn - 메세지
 *             emplNo - 나의 no
 *             empName - 사용자 이름
 *             created - 메세지 보낸 시간
 *             bPrevChat - 이전 채팅 내역
 */
function buildChatMessage(dom, {message}) {
  // 보내는 사람이랑 받는 사람이 같은 경우는 내가보낸 것
  const messageHTML = message.emplNo == emp ? `
      <div class="d-flex flex-row justify-content-end">
          <div class="chat-message text-end d-flex flex-column align-items-end" style="width: 80%">
              <p class="small me-4" style="font-size: 0.5rem">${message.emplNm}</p>
              ${ message.type === TALK ? 
                `<p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary" style="width: fit-content">${message.mssageCn}</p>` 
                : `<img src="/ys/${message.mssageCn}" class="me-3 mb-1 rounded float-start w-50" alt="...">`}
              <p class="small me-3 mb-3 rounded-3 text-muted" style="font-size: 0.5rem">${formatDate(new Date(message.creatDe))}</p>
          </div>
          <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
               alt="avatar 1" class="chat-avatar">
      </div>
  ` // 보낸 메세지
    : // 받은 메세지
    `
      <div class="d-flex flex-row justify-content-start">
          <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
               alt="avatar 1" class="chat-avatar">
          <div class="chat-message d-flex flex-column align-items-start" style="width: 80%">
              <p class="ms-4" style="font-size: 0.5rem">${message.emplNm}</p>
              ${ message.type === TALK ?
              `<p class="small p-2 ms-3 mb-1 rounded-3 bg-body-secondary">${message.mssageCn}</p>`
              : `<img src="/ys/${message.mssageCn}" class="ms-3 mb-1 rounded float-start w-50" alt="...">`}
              <p class="small ms-3 mb-3 rounded-3 text-muted float-end" style="font-size: 0.5rem">${formatDate(new Date(message.creatDe))}</p>
          </div>
      </div>
  `;

  // observerBlock 뒤에 메시지 추가
  if(!message.bPrevChat) {
    // 새로 추가된 채팅
    dom.insertAdjacentHTML("beforeend", messageHTML);
  } else {
    // 이전 채팅
    dom.insertAdjacentHTML("afterend", messageHTML);
  }
}

function findOpenChatRoomNo() {
  let chttRoomNo = 0;
  document.querySelectorAll(".chatRoom").forEach((dom) => {
    if (dom.classList.contains("bg-body-secondary")) {
      chttRoomNo = dom.dataset.chttRoomNo;
    }
  })

  return chttRoomNo;
}

function formatDate(date, format = "yyyy-MM-dd HH:mm") {
  if (!(date instanceof Date)) date = new Date(date); // 문자열 또는 숫자일 경우 Date 객체로 변환

  const pad = (num) => String(num).padStart(2, "0");

  const year = date.getFullYear();
  const month = pad(date.getMonth() + 1);
  const day = pad(date.getDate());
  const hour = pad(date.getHours());
  const minute = pad(date.getMinutes());

  return format
    .replace("yyyy", year)
    .replace("MM", month)
    .replace("dd", day)
    .replace("HH", hour)
    .replace("mm", minute);
}






{
  /* 알림창 */
  const toastTrigger = document.getElementById('liveToastBtn')
  const toastLiveExample = document.getElementById('liveToast')

  if(toastTrigger) {
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
    toastTrigger.addEventListener('click', () => {
      toastBootstrap.show()
    })
  }
}

/* 알림창 */
var toastButtonList = [].slice.call(document.querySelectorAll(".toast-btn"));

toastButtonList.map(function(toastButtonEl) {
  toastButtonEl.addEventListener("click", function() {
    var toastToTrigger = document.getElementById(toastButtonEl.dataset.target);
    console.log(toastToTrigger)
    if(toastToTrigger) {
      var toast = bootstrap.Toast.getOrCreateInstance(toastToTrigger);
      toast.show();
    }
  });
});
