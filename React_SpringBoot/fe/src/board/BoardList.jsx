import { useCallback, useEffect, useState } from "react";
import Board from "./Board";
import Button from "../components/Button";
import { getBoardList } from "../remote/board";
import usePagination from "../hooks/usePagination";
import BoardInsertForm from "../components/BoardInsertForm";
import SearchForm from "../components/SearchForm";

/**
 *
 * @error
 *
 * 현재 검색 기능이 문제됨
 * 다시 조회 하는경우
 */
const BoardList = () => {
  const [bRefresh, setBRefresh] = useState(false);
  const [boardList, setBoardList] = useState([]);
  const { pagination, setPagination, nextPage, prevPage, currentPage } = usePagination(getBoardList);

  // 처음 한번만 불러와서 세팅
  useEffect(() => {
    getBoardList().then((data) => {
      setBoardList(data.list);
      setPagination(data.pagination);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [bRefresh]);

  // 페이지 정보가 변경될 경우에만 변경
  useEffect(() => {
    fetchBoardList();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pagination]);

  const fetchBoardList = useCallback(async () => {
    const param = new URLSearchParams();
    param.append("pageNo", pagination.pageNo ?? 1);
    param.append("title", pagination.searchVO.title ?? 1);
    param.append("content", pagination.searchVO.content ?? 1);
    param.append("writer", pagination.searchVO.writer ?? 1);

    try {
      const data = await getBoardList(param);
      console.log(2);
      setBoardList(data.list);
    } catch (error) {
      console.error("Failed to fetch board list:", error);
    }
  }, [pagination]);

  return (
    <>
      <div>Board</div>
      <BoardInsertForm setBoardList={setBoardList} />

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

      {pagination?.pageSize}
      <br />
      {pagination?.totalCount}

      <Button title="<" onClick={prevPage} />

      {Array(pagination?.pageSize ?? 0)
        .fill("")
        .map((_, idx) => {
          idx += 1;
          // let color = {};
          return <Button key={idx} title={idx} onClick={() => currentPage(idx)} />;
        })}
      <Button title=">" onClick={nextPage} />

      <SearchForm setPagination={setPagination} setBRefresh={setBRefresh} />
    </>
  );
};

export default BoardList;
