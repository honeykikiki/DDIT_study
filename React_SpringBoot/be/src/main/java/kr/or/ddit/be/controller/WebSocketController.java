package kr.or.ddit.be.controller;

import kr.or.ddit.be.service.ChatService;
import kr.or.ddit.be.util.UploadFile;
import kr.or.ddit.be.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class WebSocketController {
    @Autowired
    private ChatService chatService;

    @Autowired
    private UploadFile UploadFile;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/chat/list")
    public String chatList(Model model,
                           int emplNo) {
        // 채팅방 목록 불러오기
        List<ChatRoomVO> chatRoomVOList = chatService.chatList(emplNo);
        log.debug("chatRoomVOList: {}", chatRoomVOList);

        model.addAttribute("chatRoomVOList", chatRoomVOList);

        return "chat/index";
    }

    // 채팅 메시지 수신 및 저장
    @MessageMapping("/chat/message")
    public ResponseEntity<String> receiveMessage(@Payload ChatVO message) {
        EmplVO emplVO = new EmplVO();

        List<AuthorVO.AuthType> authList = emplVO.getAuthList();

//        if (!emplVO.validWrite()) {
//            return ResponseEntity.ok("메시지 쓰기 권한이 없습니다.");
//        }

        if (ChatVO.MessageType.JOIN.equals(message.getType())) {
            message.setMssageCn(message.getEmplNm() + "님이 입장하셨습니다.");
        }

//        if (ChatVO.MessageType.FILE.equals(message.getType())) {
//            String saveName = UploadFile.uploadFileToBinary("chat", message.getFileData());
//            message.setFileName(saveName);
//        }

        log.debug("message => {}", message);
        int[] empNoList = chatService.insertMessage(message);//메시지를 받을때마다 데이터베이스에 저장

        // 채팅 알림 보내기
        for (int emplNo : empNoList) {
            messagingTemplate.convertAndSend("/sub/alert/room/" + emplNo, message);
        }

        // 메시지를 해당 채팅방 구독자들에게 전송
        // 채팅방 들어온 사람들 모음
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getChttRoomNo(), message);


        return ResponseEntity.ok("메시지 전송 완료");
    }

    // 채팅방 목록
    @PostMapping("/message/file")
    @ResponseBody
    public Map<String, Object> sendFile(MultipartFile[] uploadFiles) {
        log.debug("uploadFiles => {}", Arrays.toString(uploadFiles));
        Map<String, Object> resultMap = new HashMap<>();
        List<AttachFileVO> attachFileVOList = null;

        // 파일 업로드
        if (uploadFiles != null && uploadFiles.length > 0) {
            attachFileVOList = this.UploadFile.addFiles("chat", uploadFiles, 0);
        }

        resultMap.put("fileVOList", attachFileVOList);

        return resultMap;
    }

    @GetMapping("/chat/messageList")
    @ResponseBody
    public List<ChatVO> chat(
            @RequestParam(required = true) int chttRoomNo,
            @RequestParam(required = true) int emplNo) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("chttRoomNo", chttRoomNo);
        queryMap.put("emplNo", emplNo);

        List<ChatVO> chatVOList = this.chatService.selectChatList(queryMap);
        log.debug("chatVOList = {}", chatVOList);

        return chatVOList;
    }

    // Post로 메세지 보내기 기능
    @PostMapping("/message")
    @ResponseBody
    public String sendMessage(@RequestBody ChatVO message) {
        log.debug("message => {}", message);
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getChttRoomNo(), message);
        return "success";
    }
}
