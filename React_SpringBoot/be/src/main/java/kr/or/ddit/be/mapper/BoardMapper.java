package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.PaginationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    public int getTotalCount(PaginationVO<BoardVO> paginationVO);
    public List<BoardVO> list(PaginationVO<BoardVO> paginationVO);
    public BoardVO findById(int id);

    public int insert(BoardVO boardVO);
    public int update(BoardVO boardVO);
    public int delete(int boardId);

}
