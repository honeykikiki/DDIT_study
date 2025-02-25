import { useState } from "react";

function usePagination(fetch) {
  const [pagination, setPagination] = useState({
    pageNo: 1,
    lastPageNo: 1,
    pageSize: 1,
    recordCountPerPage: 10,
    totalCount: 0,
    totalRecordCount: 0
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

  const currentpage = (page) => {
    setPagination((prevParam) => ({
      ...prevParam,
      pageNo: page,
    }))
  }

  return {
    pagination,
    setPagination,
    prevPage,
    nextPage,
    currentpage
  }
}

export default usePagination;