package kr.or.ddit.be.service;

import kr.or.ddit.be.mapper.BoardMapper;
import kr.or.ddit.be.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<BoardVO> list() {
        return boardMapper.list();
    }


    public BoardVO findById(Long id) {
        return boardMapper.findById(id);
    }


    public int insert(BoardVO boardVO) {
        return boardMapper.insert(boardVO);
    }


    public int update(BoardVO boardVO) {
        return boardMapper.update(boardVO);
    }


    public int delete(Long boardId) {
        return boardMapper.delete(boardId);
    }
}
