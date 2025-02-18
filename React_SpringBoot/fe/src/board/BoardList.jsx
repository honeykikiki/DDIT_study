import { useEffect, useReducer, useRef, useState } from "react";
import Board from "./Board";

const BoardList = () => {
  const formRef = useRef(null);
  const [boardList, setBoardList] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/board/list")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        // console.log(data);
        setBoardList(data.list);
      });
  }, []);

  // 게시물 추가
  const handleInsert = (e) => {
    e.preventDefault();

    // 폼이 없는경우 에러처리
    if (formRef === null) return;

    const formData = new FormData();
    formData.append("title", formRef.current.title.value);
    formData.append("content", formRef.current.content.value);
    formData.append("writer", formRef.current.writer.value);

    fetch("http://localhost:8080/board/insert", {
      method: "POST",
      body: formData,
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        if (data.code === 1) {
          setBoardList((prevBoard) => [data.item, ...prevBoard]);
          formRef.current.title.value = "";
          formRef.current.content.value = "";
          formRef.current.writer.value = "";
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return (
    <>
      <div>Board</div>
      <form ref={formRef}>
        title <input id="title" name="title" type="text" /> <br />
        content <input id="content" name="content" type="text" /> <br />
        writer <input id="writer" name="writer" type="text" /> <br />
        <button onClick={handleInsert}>추가</button>
      </form>

      <table border={1}>
        <thead>
          <tr>
            <td>제목</td>
            <td>내용</td>
            <td>작성자</td>
            <td>작성일</td>
          </tr>
        </thead>
        <tbody>
          {boardList.length > 0 ? (
            boardList.map((board) => <Board key={board.boardId} board={board} refresh={setBoardList} />)
          ) : (
            <tr>
              <td colSpan={4}>정보가 없습니다</td>
            </tr>
          )}
        </tbody>
        <tfoot></tfoot>
      </table>
    </>
  );
};

export default BoardList;
