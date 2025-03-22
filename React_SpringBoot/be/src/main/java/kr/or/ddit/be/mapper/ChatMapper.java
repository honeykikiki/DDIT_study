package kr.or.ddit.be.mapper;

import kr.or.ddit.be.vo.ChatRoomVO;
import kr.or.ddit.be.vo.ChatVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChatMapper {
    // 내 채팅방 목록 불러오기
    public List<ChatRoomVO> chatList(int emplNo);

    int insertMessage(ChatVO chatRoomVO);

    List<ChatVO> selectChatList(Map<String, Object> queryMap);

    int invalidChatRoom(int chttRoomNo);

    int readChatMsg(Map<String, Object> queryMap);

    void updateRecentMsg(ChatVO chatRoomVO);

    int[] chatEmpNoList(ChatVO chatRoomVO);

    // 메세지 저장하기

    // 채팅방 메세지 불러오기

    // 채팅방 만들기

    // 이미 있는 채팅방인지 확인하기
}
