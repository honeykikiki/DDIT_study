package kr.or.ddit.be.controller;

import kr.or.ddit.be.vo.AlertVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class AlertController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

//    @MessageMapping("/alert/room")
//    public ResponseEntity<String> receiveAlert(@Payload ChatVO message) {
//
//
//    }

    // Post로 메세지 보내기 기능
    @PostMapping("/alert/message")
    @ResponseBody
    public String sendMessage(@RequestBody AlertVO message) {
        log.debug("message => {}", message);
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
        messagingTemplate.convertAndSend("/sub/alert/room/" + message.getEmpNo(), message);
        return "success";
    }
}
