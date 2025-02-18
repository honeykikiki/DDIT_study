package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private Long boardId;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
}
