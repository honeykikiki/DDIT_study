package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.ChatService;
import kr.or.ddit.be.vo.ChatVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Slf4j
@Controller
public class WebSocketController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // 채팅 메시지 수신 및 저장
    @MessageMapping("/chat/message")
    public ResponseEntity<String> receiveMessage(@Payload ChatVO message) {
        log.debug("message => {}", message);
        if (ChatVO.MessageType.JOIN.equals(message.getType())) {
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        }

        if (ChatVO.MessageType.FILE.equals(message.getType())) {
            byte[] decodedFile = Base64.getDecoder().decode(message.getFileData().split(",")[1]);
            String saveDir = "/Users/heoseongjin/Documents/GitHub/ddit/ys/chat/";
            if (!new File(saveDir).isDirectory()) new File(saveDir).mkdir();
            String saveName = UUID.randomUUID().toString().replace("-", "");
            Path filePath = Paths.get(saveDir + saveName);

            try {
                log.debug("filePath => {}", filePath);
                Files.write(filePath, decodedFile);
                message.setFileName("/chat/" + saveName);
                log.info("파일 저장 완료: {}", filePath.toString());
            } catch (IOException e) {
                log.error("파일 저장 중 오류 발생", e);
            }
        }

        // chatService.saveChat(message);//메시지를 받을때마다 데이터베이스에 저장
        if (ChatVO.MessageType.IMG.equals(message.getType())) {
            //이미지인 경우 처리할 로직
        }

        // 메시지를 해당 채팅방 구독자들에게 전송
        // 채팅방 들어온 사람들 모음
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        log.debug("filePath => {}", message);
        return ResponseEntity.ok("메시지 전송 완료");
    }

    // 채팅방 목록


}
