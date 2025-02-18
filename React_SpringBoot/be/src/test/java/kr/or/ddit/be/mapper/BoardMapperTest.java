package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.BoardVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardMapperTest {

    @Autowired
    private BoardMapper boardMapper;

    private Long testBoardId; // 테스트용 게시물 ID

    @BeforeEach
    void setUp() {
        // 게시물 추가
        BoardVO board = new BoardVO();
        board.setTitle("테스트 제목1");
        board.setContent("테스트 내용1");
        board.setWriter("작성자1");
        boardMapper.insert(board);
        System.out.println("board.getBoardId() => " + board);
        testBoardId = board.getBoardId(); // 삽입 후 생성된 ID 저장
    }

    @AfterEach
    void tearDown() {
        // 테스트 후 남은 게시물 정리
        if (testBoardId != null) {
            boardMapper.delete(testBoardId);
        }
    }

    @Test
    @DisplayName("게시물 추가 후 조회 테스트")
    void findById() {
        BoardVO board = boardMapper.findById(testBoardId);
        assertNotNull(board);
        assertEquals("테스트 제목1", board.getTitle());
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    void update() {
        BoardVO board = new BoardVO();
        board.setBoardId(testBoardId);
        board.setTitle("수정된 제목");
        board.setContent("수정된 내용");

        boardMapper.update(board);

        BoardVO updatedBoard = boardMapper.findById(testBoardId);
        System.out.println(updatedBoard + " updatedBoard");
        assertEquals("수정된 제목", updatedBoard.getTitle());
        assertEquals("수정된 내용", updatedBoard.getContent());
    }

    @Test
    @DisplayName("게시물 삭제 테스트")
    void delete() {
        boardMapper.delete(testBoardId);
        BoardVO board = boardMapper.findById(testBoardId);
        assertNull(board);
    }
}
