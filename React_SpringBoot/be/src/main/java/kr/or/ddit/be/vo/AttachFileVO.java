package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AttachFileVO {
    private long atchFileNo;
    private int fileSn;
    private String fileStrePath; // 실제 파일 저장 위치
    private String fileNm; // 실제 파일 이름
    private String fileStreNm; // 저장 명
    private String fileExtsn; // 파일 확장자
    private long fileSize; // 파일 크기
    private int dwldCo; // 다운로드 횟수
    private String emplNo;
    private String fileDeleteAt;
    private Date fileCreatDt;
    private String fileViewSize;
    private String fileMime;
}
