package kr.or.ddit.be.vo;

import lombok.Data;
import java.util.Date;

@Data
public class ChatVO {
    public enum MessageType {
        TALK, JOIN, IMG, FILE
    }

    private MessageType type; // 메시지 타입
    private String roomId; // 방 번호
    private String sender; // 채팅을 보낸 사람
    private String message; // 메시지
    private String fileName; // 파일 이름
    private String fileData; // Base64 인코딩된 파일 데이터
    private Date createDate; // 채팅 발송 시간
}
