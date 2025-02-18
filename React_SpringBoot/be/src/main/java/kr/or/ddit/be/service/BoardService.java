package kr.or.ddit.be.service;

import kr.or.ddit.be.mapper.BoardMapper;
import kr.or.ddit.be.mapper.FilesMapper;
import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    @Autowired
    FilesMapper filesMapper;

    public List<BoardVO> list() {
        return boardMapper.list();
    }


    public BoardVO findById(int id) {
        return boardMapper.findById(id);
    }


    public int insert(BoardVO boardVO) {
        int result = boardMapper.insert(boardVO);

        if (result == 1) {
            // 파일 추가하기
            MultipartFile[] bdFiles = boardVO.getFiles();
            FileVO[] fileList = Arrays.stream(bdFiles).map(boardVO1 -> {
                String saveDir = "/Users/heoseongjin/Documents/GitHub/ddit/ys/board/";
                String saveName = UUID.randomUUID().toString().replace("-", "");
                File file = new File(saveDir + saveName);
                if (!new File(saveDir).isDirectory()) new File(saveDir).mkdir();

                try {
                    boardVO1.transferTo(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                FileVO fileVO = new FileVO();

                fileVO.setFileName("/board/" + saveName);
                fileVO.setContent(boardVO1.getOriginalFilename());
                fileVO.setBoardId(boardVO.getBoardId());
                filesMapper.insert(fileVO);
                return fileVO;
            }).toArray(FileVO[]::new);

            boardVO.setFileVOList(fileList);
        }

        return result;
    }


    public int update(BoardVO boardVO) {
        return boardMapper.update(boardVO);
    }


    public int delete(int boardId) {
        return boardMapper.delete(boardId);
    }
}
