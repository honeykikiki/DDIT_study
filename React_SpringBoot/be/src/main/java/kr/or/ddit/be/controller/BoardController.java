package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.BoardService;
import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.PaginationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public Map<String, Object> list(PaginationVO<BoardVO> paginationVO,
                                    BoardVO boardVO) {
        Map<String, Object> resultMap = new HashMap<>();
        paginationVO.setSearchVO(boardVO);

        List<BoardVO> list = boardService.list(paginationVO);
        resultMap.put("list", list);
        resultMap.put("pagination", paginationVO);
        return resultMap;
    }

    @PostMapping("/insert")
    public Map<String, Object> insert(BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<>();
        int result = boardService.insert(boardVO);

        boardVO.setFiles(null); // MultipartFile 내보내는 경우 JSON변경이 불가능해서 에러가 생김
        resultMap.put("item", boardVO);
        resultMap.put("code", result);
        log.debug("boardVO: {}", boardVO);
        return resultMap;
    }

    @PostMapping("/update")
    public Map<String, Object> update(BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<>();
        int result = boardService.update(boardVO);
        resultMap.put("item", boardVO);
        resultMap.put("code", result);
        log.debug("boardVO: {}", boardVO);
        return  resultMap;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<>();
        int delete = boardService.delete(boardVO.getBoardId());
        resultMap.put("code", delete);

        return  resultMap;
    }
}
