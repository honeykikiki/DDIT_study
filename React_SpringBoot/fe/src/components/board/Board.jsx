import { useState } from "react";
import { boardDelete, boardUpdate as boardUpdate } from "../../remote/board";
import Input from "../Input";
import Button from "../Button";

// eslint-disable-next-line react/prop-types
const Board = ({ board, refresh }) => {
  const [curBoard, setCurBoard] = useState(board);
  const [isUpdate, setIsUpdate] = useState(false);
  const date = new Date(curBoard.createDate);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCurBoard({ ...curBoard, [name]: value });
  };

  const handleIsUpdate = () => {
    setIsUpdate(!isUpdate);
    // 초기 값으로 변경
    setCurBoard(board);
  };

  const handleUpdate = async () => {
    const data = await boardUpdate(curBoard);
    // 데이터 처리 에러가 혹은 수정 안된 경우
    if (data != null && data.code == 1) {
      setIsUpdate(!isUpdate);
    }
  };

  const handleDelete = async () => {
    const data = await boardDelete(curBoard.boardId);
    // 데이터 처리 에러가 혹은 삭제 안된 경우
    if (data != null && data.code === 1) {
      refresh((prevData) => prevData.filter(({ boardId }) => boardId !== curBoard.boardId));
    }
  };

  return (
    <tr key={curBoard.boardId}>
      <td>
        {isUpdate ? (
          // <input id="title" name="title" type="text" onChange={handleChange} value={curBoard.title} />
          <Input dataName="title" onChange={handleChange} value={curBoard.title} />
        ) : (
          curBoard.title
        )}
      </td>
      <td>
        {isUpdate ? (
          // <input id="content" name="content" type="text" onChange={handleChange} value={curBoard.content} />
          <Input dataName="content" onChange={handleChange} value={curBoard.content} />
        ) : (
          curBoard.content
        )}
      </td>
      <td>
        {isUpdate ? (
          // <input dataName="writer" type="text" onChange={handleChange} value={curBoard.writer} />
          <Input dataName="writer" onChange={handleChange} value={curBoard.writer} />
        ) : (
          curBoard.writer
        )}
      </td>
      <td>{date.toLocaleDateString()}</td>
      <td>
        <Button title={isUpdate ? "수정취소" : "수정"} onClick={handleIsUpdate} />
        {/* api 통한 변경 */}
        {isUpdate ? <Button title="변경" onClick={handleUpdate} /> : null}
      </td>

      <td>
        {curBoard.fileVOList?.length > 0
          ? curBoard.fileVOList.map((file) => (
              <div key={file.fileId}>
                <img src={`http://localhost:8080/ys${file.fileName}`} width={50} height={50} alt="" />
              </div>
            ))
          : "이미지 없음"}
      </td>
      <td>
        <Button title="삭제" onClick={handleDelete} />
      </td>
    </tr>
  );
};

export default Board;
