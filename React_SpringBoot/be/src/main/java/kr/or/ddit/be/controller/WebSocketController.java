package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.ChatService;
import kr.or.ddit.be.util.UploadFile;
import kr.or.ddit.be.vo.ChatVO;
import kr.or.ddit.be.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Controller
public class WebSocketController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UploadFile UploadFile;

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
            String saveName = UploadFile.uploadFileToBinary("chat", message.getFileData());
            message.setFileName(saveName);
        }

        // chatService.saveChat(message);//메시지를 받을때마다 데이터베이스에 저장
        // 메시지를 해당 채팅방 구독자들에게 전송
        // 채팅방 들어온 사람들 모음
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        log.debug("filePath => {}", message);
        return ResponseEntity.ok("메시지 전송 완료");
    }

    // 채팅방 목록
    @PostMapping("/message/file")
    @ResponseBody
    public Map<String, Object> sendFile(MultipartFile[] uploadFiles) {
        log.debug("uploadFiles => {}", Arrays.toString(uploadFiles));
        Map<String, Object> resultMap = new HashMap<>();
        List<FileVO> fileVOList = null;
        // 파일 업로드
        if (uploadFiles != null && uploadFiles.length > 0) {
            fileVOList = this.UploadFile.addFile("chat", uploadFiles, 0);
        }

        resultMap.put("fileVOList", fileVOList);

        return resultMap;
    }

}
