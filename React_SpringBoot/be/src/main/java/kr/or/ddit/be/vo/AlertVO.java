package kr.or.ddit.be.vo;

import lombok.Data;

@Data
public class AlertVO {
    public enum MessageType {
        CHAT, PAYMENT
    }

    private int chatRoomNo;
    private int empNo; // 알림 받을 사람
    private String title;
    private String body;
    private String sender;
    private AlertVO.MessageType type; // 메시지 타입

    private String msgType = "0"; // 0: 채팅 | 1: 파일
    private int id; // 타입에 맞는 아이디

    public void setType(String type) {
        switch (type) {
            case "CHAT" -> this.type = AlertVO.MessageType.CHAT;
            case "PAYMENT" -> this.type = AlertVO.MessageType.PAYMENT;
        }

        if (this.type == AlertVO.MessageType.CHAT) this.msgType = "0";
        if (this.type == AlertVO.MessageType.PAYMENT) this.msgType = "1";
    }


}
