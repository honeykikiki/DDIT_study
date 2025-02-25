import { useCallback, useEffect, useRef, useState } from "react";
import Board from "./Board";
import Input from "../components/Input";
import Button from "../components/Button";
import { boardInsert, getBoardList } from "../remote/board";
import usePagination from "../hooks/usePagination";

const BoardList = () => {
  const formRef = useRef(null);
  const [boardList, setBoardList] = useState([]);
  const { pagination, setPagination, nextPage, prevPage, currentpage } = usePagination(getBoardList);

  // 처음 한번만 불러와서 세팅
  useEffect(() => {
    // fetchBoardList();
    getBoardList().then((data) => {
      setBoardList(data.list);
      setPagination(data.pagination);
    });
  }, []);

  // 페이지 정보가 변경될 경우에만 변경
  useEffect(() => {
    fetchBoardList();
  }, [pagination]);

  const fetchBoardList = useCallback(async () => {
    // 마지막 또는 처음보다 작은곳 가는 경우 다시 안불러 오게 수정

    const param = new URLSearchParams();
    param.append("pageNo", pagination.pageNo ?? 1);

    try {
      const data = await getBoardList(param);
      setBoardList(data.list);
      // setPagination(data.pagination);
    } catch (error) {
      console.error("Failed to fetch board list:", error);
    }
  }, [pagination.pageNo]);

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
          {boardList?.length > 0 ? (
            boardList.map((board) => <Board key={board.boardId} board={board} refresh={setBoardList} />)
          ) : (
            <tr>
              <td colSpan={4}>정보가 없습니다</td>
            </tr>
          )}
        </tbody>
        <tfoot></tfoot>
      </table>

      <Button title="<" onClick={prevPage} />
      {Array(pagination?.pageSize ?? 0)
        .fill("")
        .map((_, idx) => {
          idx += 1;
          // let color = {};
          return <Button key={idx} title={idx} onClick={() => currentpage(idx)} />;
        })}
      <Button title=">" onClick={nextPage} />
    </>
  );
};

export default BoardList;
