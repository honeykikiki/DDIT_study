package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.BoardService;
import kr.or.ddit.be.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public Map<String, Object> list() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<BoardVO> list = boardService.list();
        resultMap.put("list", list);
        return resultMap;
    }

    @PostMapping("/insert")
    public Map<String, Object> insert(BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = boardService.insert(boardVO);
        resultMap.put("item", boardVO);
        resultMap.put("code", result);
        log.debug("boardVO: {}", boardVO);
        return resultMap;
    }

    @PostMapping("/update")
    public Map<String, Object> update(BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int result = boardService.update(boardVO);
        resultMap.put("item", boardVO);
        resultMap.put("code", result);
        log.debug("boardVO: {}", boardVO);
        return  resultMap;
    }

    @PostMapping("/delete")
    public Map<String, Object> delete(@RequestBody BoardVO boardVO) {
        log.debug("boardVO: {}", boardVO);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int delete = boardService.delete(boardVO.getBoardId());
        resultMap.put("code", delete);

        return  resultMap;
    }
}
