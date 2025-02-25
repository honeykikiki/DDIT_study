package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PaginationVO<T> {
    /**
     * Required Fields
     * - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.
     * <p>
     * <p>
     * pageNo => 현재 페이지 번호
     * recordCountPerPage => 한 페이지당 게시되는 게시물 건수
     * pageSize => 페이지 사이즈 ex) 1 ~ 10 페이지면 10
     * totalPageCount => 전체 페이지 건 수.
     * lastPageNo => 페이지 마지막 번호
     * searchVo => 제네릭으로 받은 검색 정보
     * totalCount => 전체 게시물 건수
     */

    private int pageNo;
    private int recordCountPerPage = 10;
    private int pageSize = 10;
    private int totalRecordCount;
    private int lastPageNo;
    private T searchVO;
    private int totalCount;

    Map<String, String> pageMap = new HashMap<String, String>(); // VO에 있는 정보를 제외한 다른 정보를 넣어야 하는경우

    public PaginationVO() {
        this.pageNo = 1;
    }

    public PaginationVO(int currentPage) {
        if (currentPage == 0) {
            currentPage = 1;
        }

        this.pageNo = currentPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.pageSize = (totalCount / recordCountPerPage) + 1;
    }

//    public int getTotalCount() {
//        totalCount = ((getTotalRecordCount() - 1) / getRecordCountPerPage()) + 1;
//        return totalCount;
//    }
//
    public int getLastPageNo() {
        lastPageNo = (totalRecordCount / recordCountPerPage) + 1;
        return lastPageNo;
    }

}
