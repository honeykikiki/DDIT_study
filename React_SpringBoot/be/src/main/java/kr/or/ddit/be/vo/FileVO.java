package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.Date;

@Data
public class FileVO {
    private int fileId;
    private String fileName;
    private String content;
    private Date createDate;
    private int boardId;
}
