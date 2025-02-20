package kr.or.ddit.be.service;

import kr.or.ddit.be.mapper.BoardMapper;
import kr.or.ddit.be.mapper.FilesMapper;
import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
        List<BoardVO> list = boardMapper.list();
        for (BoardVO boardVO : list) {
            System.out.println(boardVO.getBoardId());
            List<FileVO> fileVOList = filesMapper.list(boardVO);
            boardVO.setFileVOList(fileVOList);
        }

        return list;
    }

    public BoardVO findById(int id) {
        return boardMapper.findById(id);
    }

    public int insert(BoardVO boardVO) {
        int result = boardMapper.insert(boardVO);
        MultipartFile[] bdFiles = boardVO.getFiles();

        if (result == 1 && bdFiles != null && bdFiles.length > 0) {
            // 파일 추가하기
            List<FileVO> fileList = Arrays.stream(bdFiles).map(boardVO1 -> {
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
            }).toList();

            boardVO.setFileVOList(fileList);
        }

        return result;
    }

    public int update(BoardVO boardVO) {
        return boardMapper.update(boardVO);
    }

    @Transactional
    public int delete(int boardId) {
        int result = 0;
        BoardVO  boardVO = new BoardVO();
        boardVO.setBoardId(boardId);
        List<FileVO> fileVOList = filesMapper.list(boardVO);
        List<Integer> fileIdList = new ArrayList<>();

        fileVOList.stream().forEach((fileVO) -> {
            // 이미지 먼저 삭제하기
            Path filePath = Paths.get("/Users/heoseongjin/Documents/GitHub/ddit/ys" + fileVO.getFileName());
            try {
                Files.deleteIfExists(filePath);
                fileIdList.add(fileVO.getFileId());
            } catch (IOException e) {
                System.out.println("파일 삭제 실패: " + e.getMessage());
            }
        });

        if (!fileIdList.isEmpty()) {
            filesMapper.delete(fileIdList);
        }

        return boardMapper.delete(boardId);
    }
}
