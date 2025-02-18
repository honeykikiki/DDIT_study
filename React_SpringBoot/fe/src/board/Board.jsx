import { useState } from "react";

// eslint-disable-next-line react/prop-types
const Board = ({ board, refresh }) => {
  const [curBoard, setCurBoard] = useState(board);
  const [isUpdate, setIsUpdate] = useState(false);
  const date = new Date(curBoard.createDate);

  const handleChange = (e) => {
    const { name, value } = e.target;
    console.log(name, value);
    setCurBoard({ ...curBoard, [name]: value });
  };

  const handleIsUpdate = () => {
    setIsUpdate(!isUpdate);
    // 초기 값으로 변경
    setCurBoard(board);
  };

  const handleUpdate = () => {
    const formData = new FormData();
    formData.append("boardId", curBoard.boardId);
    formData.append("title", curBoard.title);
    formData.append("content", curBoard.content);
    formData.append("writer", curBoard.writer);

    fetch("http://localhost:8080/board/update", {
      method: "POST",
      body: formData,
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        if (data.code === 1) {
          setIsUpdate(!isUpdate);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleDelete = () => {
    fetch("http://localhost:8080/board/delete", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ boardId: curBoard.boardId }),
    })
      .then((res) => res.json())
      .then((data) => {
        console.log(data);
        if (data.code === 1) {
          console.log(1);

          refresh((prevData) => prevData.filter(({ boardId }) => boardId !== curBoard.boardId));
        }
      });
  };

  return (
    <tr key={curBoard.boardId}>
      <td>
        {isUpdate ? (
          <input id="title" name="title" type="text" onChange={handleChange} value={curBoard.title} />
        ) : (
          curBoard.title
        )}
      </td>
      <td>
        {isUpdate ? (
          <input id="content" name="content" type="text" onChange={handleChange} value={curBoard.content} />
        ) : (
          curBoard.content
        )}
      </td>
      <td>
        {isUpdate ? (
          <input id="writer" name="writer" type="text" onChange={handleChange} value={curBoard.writer} />
        ) : (
          curBoard.writer
        )}
      </td>
      <td>{date.toLocaleDateString()}</td>
      <td>
        <button onClick={handleIsUpdate}>{isUpdate ? "수정취소" : "수정"}</button>
        {/* api 통한 변경 */}
        {isUpdate ? <button onClick={handleUpdate}>변경</button> : null}
      </td>

      <td>
        <button onClick={handleDelete}>삭제</button>
      </td>
    </tr>
  );
};

export default Board;
