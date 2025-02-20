import { useEffect, useRef, useState } from "react";
import Board from "./Board";
import Input from "../components/Input";
import Button from "../components/Button";
import { boardInsert } from "../remote/board";

const BoardList = () => {
  const formRef = useRef(null);
  const [boardList, setBoardList] = useState([]);

  useEffect(() => {
    fetchBoardList();
  }, []);

  const fetchBoardList = async () => {
    try {
      const response = await fetch("http://localhost:8080/board/list");
      const data = await response.json();
      console.log(data.list);

      setBoardList(data.list);
    } catch (error) {
      console.error("Failed to fetch board list:", error);
    }
  };

  // 게시물 추가
  const handleInsert = async (e) => {
    e.preventDefault();

    // 폼이 없는경우 에러처리
    if (formRef === null) return;

    const data = await boardInsert(formRef);
    if (data !== null && data.code === 1) {
      setBoardList((prevBoard) => [data.item, ...prevBoard]);
      formRef.current.title.value = "";
      formRef.current.content.value = "";
      formRef.current.writer.value = "";
    }
  };

  return (
    <>
      <div>Board</div>
      <form ref={formRef}>
        title <Input dataName="title" /> <br />
        content <Input dataName="content" /> <br />
        writer <Input dataName="writer" /> <br />
        file <input id="files" name="files" type="file" multiple /> <br />
        <Button title={"추가"} onClick={handleInsert} />
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
