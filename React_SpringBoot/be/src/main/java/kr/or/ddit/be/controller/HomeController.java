package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.BoardService;
import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.PaginationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String list(Model model,
                       PaginationVO<BoardVO> paginationVO) {
//        List<BoardVO> list = boardService.list(paginationVO);
//        log.info("list = > " + list);
//        model.addAttribute("list", list);
//        model.addAttribute("pagenation", paginationVO);

        return "1demo/demo";
    }
}
