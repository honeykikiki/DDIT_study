package kr.or.ddit.be.service;

import kr.or.ddit.be.mapper.BoardMapper;
import kr.or.ddit.be.mapper.FilesMapper;
import kr.or.ddit.be.util.UploadFile;
import kr.or.ddit.be.vo.AttachFileVO;
import kr.or.ddit.be.vo.BoardVO;
import kr.or.ddit.be.vo.PaginationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FilesMapper filesMapper;

    @Autowired
    private UploadFile uploadFile;

    public List<BoardVO> list(PaginationVO<BoardVO> searchBoardVO) {
        List<BoardVO> list = boardMapper.list(searchBoardVO);

        /*
         * 모든 게시물 카운트
         * 몇개씩 가져오는지
         * 현재 페이지
         * 전체 페이지 카운트
         * 검색 정보
         * */

        // 페이지 전체 정보 가져오기
        int totalCount = boardMapper.getTotalCount(searchBoardVO);
        searchBoardVO.setTotalCount(totalCount);

        // 파일 정보 가져오기
        for (BoardVO boardVO : list) {
            List<AttachFileVO> attachFileVOList = filesMapper.list(boardVO);
            boardVO.setAttachFileVOList(attachFileVOList);
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
            List<AttachFileVO> attachFileVOList = this.uploadFile.addFiles("board", bdFiles, boardVO.getBoardId());


//            List<FileVO> fileList = Arrays.stream(bdFiles).map(boardVO1 -> {
//                String saveDir = "/Users/heoseongjin/Documents/GitHub/ddit/ys/board/";
//                String saveName = UUID.randomUUID().toString().replace("-", "");
//                File file = new File(saveDir + saveName);
//                FileVO fileVO = new FileVO();
//                if (!new File(saveDir).isDirectory()) new File(saveDir).mkdir();
//
//                try {
//                    boardVO1.transferTo(file);
//
//                    fileVO.setFileName("/board/" + saveName);
//                    fileVO.setContent(boardVO1.getOriginalFilename());
//                    fileVO.setBoardId(boardVO.getBoardId());
//                    filesMapper.insert(fileVO);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//                return fileVO;
//            }).toList();

            boardVO.setAttachFileVOList(attachFileVOList);
        }

        return result;
    }

    public int update(BoardVO boardVO) {
        return boardMapper.update(boardVO);
    }

    @Transactional
    public int delete(int boardId) {
        int result = 0;
        BoardVO boardVO = new BoardVO();
        boardVO.setBoardId(boardId);
        List<AttachFileVO> attachFileVOList = filesMapper.list(boardVO);
        List<Integer> fileIdList = new ArrayList<>();

        attachFileVOList.stream().forEach((fileVO) -> {
            // 이미지 먼저 삭제하기
            Path filePath = Paths.get("/Users/heoseongjin/Documents/GitHub/ddit/ys" + fileVO.getFileStrePath());
            try {
                Files.deleteIfExists(filePath);
//                fileIdList.add(fileVO.getFileId());
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
