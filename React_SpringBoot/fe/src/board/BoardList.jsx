/* eslint-disable react-hooks/exhaustive-deps */
import { useCallback, useEffect, useState } from "react";
import Board from "./Board";
import { getBoardList } from "../remote/board";
import usePagination from "../hooks/usePagination";
import BoardInsertForm from "../components/BoardInsertForm";
import SearchForm from "../components/SearchForm";
import Pagination from "../components/Pagination";

/**
 *
 * @error
 * 현재 검색 기능이 문제됨
 * 다시 조회 하는경우 문제가 있음
 *
 * 해결 방법
 * 1. 검색 기능 조회 시 리랜더링 되어야함 근데 문제가 되는게 페이지 정보를 가져오지 못하고 있음
 */
const BoardList = () => {
  const [bRefresh, setBRefresh] = useState(false);
  const [boardList, setBoardList] = useState([]);
  const { pagination, setPagination, nextPage, prevPage, currentPage, firstPage, lastPage } =
    usePagination(getBoardList);

  // 처음 한번만 불러와서 세팅
  useEffect(() => {
    const param = new URLSearchParams();
    param.append("pageNo", 1);
    param.append("title", pagination.searchVO.title);
    param.append("content", pagination.searchVO.content);
    param.append("writer", pagination.searchVO.writer);

    getBoardList(param).then((data) => {
      setBoardList(data.list);
      setPagination(data.pagination);
    });
  }, [bRefresh]);

  // 페이지 정보가 변경될 경우에만 변경
  useEffect(() => {
    fetchBoardList();
  }, [pagination.pageNo]);

  const fetchBoardList = useCallback(async () => {
    const param = new URLSearchParams();

    param.append("pageNo", pagination.pageNo ?? 1);
    param.append("title", pagination.searchVO.title);
    param.append("content", pagination.searchVO.content);
    param.append("writer", pagination.searchVO.writer);

    try {
      const data = await getBoardList(param);
      setBoardList(data.list);
    } catch (error) {
      console.error("Failed to fetch board list:", error);
    }
  }, [pagination]);

  return (
    <>
      <div>Board</div>
      {/* <BoardInsertForm setBoardList={setBoardList} /> */}

      <SearchForm setPagination={setPagination} setBRefresh={setBRefresh} />
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

      <Pagination
        pagination={pagination}
        nextPage={nextPage}
        prevPage={prevPage}
        currentPage={currentPage}
        firstPage={firstPage}
        lastPage={lastPage}
      />
    </>
  );
};

export default BoardList;
