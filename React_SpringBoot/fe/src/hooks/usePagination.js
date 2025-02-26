import { useState } from "react";

function usePagination() {
  //값 넣어두면 자동완성 되서 넣은 거임
  const [pagination, setPagination] = useState({
    pageNo: 1,
    lastPageNo: 1,
    pageSize: 1,
    recordCountPerPage: 10,
    totalCount: 0,
    totalRecordCount: 0,
    searchVO: {
      boardId: "",
      content: "",
      createDate: "",
      fileVOList: "",
      files: "",
      title: "",
      writer: "",
    }
  });

  // 이전 페이지
  const prevPage = () => {
    if (pagination.pageNo <= 1) return;
    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: prevParam.pageNo - 1,
    }))
  }

  // 다음페이지
  const nextPage = () => {
    if (pagination?.pageSize <= pagination.pageNo) return;

    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: prevParam.pageNo + 1,
    }))
  }

  const currentPage = (page) => {
    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: page,
    }))
  }

  const firstPage = () => {
    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: 1,
    }))
  }
  
  const lastPage = () => {
    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: prevParam.pageSize,
    }))
  }

  return {
    pagination,
    setPagination,
    prevPage,
    nextPage,
    currentPage,
    firstPage,
    lastPage,
  }
}

export default usePagination;