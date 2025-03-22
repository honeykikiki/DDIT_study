package kr.or.ddit.be.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ChatRoomVO {
    private int chttRoomNo;
    private String chttRoomNm;
    private Date creatDe;
    private String chttRoomTy;
    private int lastMssageNo;
    private int readCount; // 안읽은 채팅 카운트
    private String lastMsg; // 마지막 메세지
    private String mssageTy;

    // 채팅방에 들어가 있는 사람들
    private int[] emplNo;
    private int chatLastRead;
}
