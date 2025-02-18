package kr.or.ddit.be.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class BoardVO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
    private FileVO[] fileVOList;

    private MultipartFile[] files;
}
