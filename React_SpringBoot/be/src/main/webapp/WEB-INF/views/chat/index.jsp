<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--해당 파일에 타이틀 정보를 넣어준다--%>
<c:set var="title" scope="application" value="채팅" />
<fmt:setLocale value="ko_KR" />
<fmt:setTimeZone value="Asia/Seoul" />
<c:set var="now" value="<%= new java.util.Date() %>" />
<c:set var="yesterday" value="<%= new java.util.Date(System.currentTimeMillis() - 86400000) %>" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta
			name="viewport"
			content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
	/>
	<meta http-equiv="X-UA-Compatible" content="ie=edge" />
	<title>${title}</title>
	<c:import url="../layout/prestyle.jsp" />
	
	<style>
      a {
          text-decoration: none;
      }

      .chat-avatar {
          width: 45px;
          height: 100%;
      }

      #chatList {
          max-height: 60vh;
          overflow-x: hidden; /* 가로 스크롤 활성화 */
          overflow-y: scroll; /* 세로 스크롤 숨김 */
      }

      /* ( 크롬, 사파리, 오페라, 엣지 ) 동작 */
      #chatList::-webkit-scrollbar-track-piece {
          display: none;
      }

      .text-truncate-2 {
          text-overflow: ellipsis;
          text-wrap: wrap;
          -webkit-line-clamp: 2;
          -webkit-box-orient: vertical;
          overflow: hidden;
          display: -webkit-box;
      }
	</style>
</head>
<body>
<c:import url="../layout/sidebar.jsp" />
<main class="main-wrapper">
	<c:import url="../layout/header.jsp" />
	
	<section class="section">
		<div class="container-fluid">
			<c:import url="../layout/title-wrap.jsp" />
			
			<div class="row">
				<div class="col-md-12">
					<div class="card" id="chat3">
						<div class="card-body">
							<div class="row">
								<div class="col-md-6 col-lg-5 mb-4 mb-md-0">
									<div class="p-3">
										<button class="btn btn-primary mb-2">
											채팅방 만들기
										</button>
										
										<div class="input-group rounded mb-3">
											<input type="search" class="form-control rounded" placeholder="이름 입력" aria-label="Search" aria-describedby="search-addon" />
											<span class="input-group-text border-0" id="search-addon">
												<i class="fas fa-search"></i>
											</span>
										</div>
										
										<div>
											<%--채팅방 목록--%>
											<ul class="list-unstyled mb-0">
												<c:forEach var="chatRoom" items="${chatRoomVOList}">
													<li class="p-2 rounded border-bottom chatRoom" data-chtt-room-no="${chatRoom.chttRoomNo}">
														<div class="d-flex justify-content-between text-truncate" style="cursor: pointer">
															<div class="d-flex flex-row">
																	<%-- 채팅방 상대 이미지 --%>
																<div>
																		<%--채팅방 이미지--%>
																	<img
																			src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
																			alt="avatar" class="d-flex align-self-center me-3"
																			width="60">
																		<span class="badge bg-success badge-dot"></span>
																</div>
																	
																	<%-- 채티방 이름 마지막 메세지 --%>
																<div class="pt-1">
																	<p class="fw-bold mb-0">${chatRoom.chttRoomNm}</p>
																	<p class="chat-last-msg small text-muted text-truncate-2">
																			${empty chatRoom.lastMsg ? "대화 내용 없음" : chatRoom.mssageTy == "1" ? "사진을 보냈습니다." : chatRoom.lastMsg}
																	</p>
																</div>
															</div>
															<div class="pt-1">
																	<%-- 마지막 보낸 메세지 시간 --%>
																<p class="chat-create-date small text-muted mb-1">
																	<c:choose>
																		<%-- 오늘이면 시간만 표시 --%>
																		<c:when test="${fn:substring(chatRoom.creatDe, 0, 10) == fn:substring(now, 0, 10)}">
																			<fmt:formatDate value="${chatRoom.creatDe}" pattern="HH:mm" />
																		</c:when>
																		
																		<%-- 전날이면 날짜만 표시 --%>
																		<c:when test="${fn:substring(chatRoom.creatDe, 0, 4) == fn:substring(yesterday, 0, 4)}">
																			<fmt:formatDate value="${chatRoom.creatDe}" pattern="MM.dd" />
																		</c:when>
																		
																		<%-- 전날이면 날짜만 표시 --%>
																		<c:when test="${fn:substring(chatRoom.creatDe, 0, 4) != fn:substring(yesterday, 0, 4)}">
																			<fmt:formatDate value="${chatRoom.creatDe}" pattern="yyyy.MM.dd" />
																		</c:when>
																		
																		<%-- 그 외 날짜+시간 표시 --%>
																		<c:otherwise>
																			<fmt:formatDate value="${chatRoom.creatDe}" pattern="yyyy.MM.dd HH:mm" />
																		</c:otherwise>
																	</c:choose>
																</p>
																	<%-- 채팅 안 읽은 카운트 --%>
																<span class="read-badge badge bg-danger rounded-pill float-end ${chatRoom.readCount != 0 ? '' :  'd-none'}">${chatRoom.readCount}</span>
															</div>
														</div>
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
								
								<div id="chat" class="col-md-6 col-lg-7 d-none position-relative">
									<div id="chatList" class="pt-3 pe-3">
										<div id="loader" class="d-none text-center">
											<div class="spinner-border" role="status">
												<span class="visually-hidden">Loading...</span>
											</div>
										</div>
										
										<%-- 옵저버 블록 --%>
										<div id="observerBlock"></div>
										<%-- 채팅 온경우 알림 --%>
										<div class="mx-2 mb-4 p-2 rounded-2 bg-warning-400 text-center">마지막 채팅입니다.</div>
										
											<%--진짜 채팅이 들어가느 구간--%>
										<div id="realChatList"></div>
										
										<div id="chatInnerAlert"
												 class="position-absolute left-0 m-2 px-3 py-2 rounded-2
												        bg-warning-400 text-truncate d-flex justify-content-between
												        align-items-center d-none"
												 style="width: calc(100% - 4rem);
															bottom: 60px;
															cursor: pointer;"
												 onclick="document.querySelector('#chatList').scrollTop = document.querySelector('#chatList').scrollHeight">
											<span class="content text-truncate me-2" style="flex-grow: 1;">
													채팅 내용이 여기 들어갈거임 (클릭하면 제일 밑으로 내려감) 채팅 내용이 여기 들어갈거임 (클릭하면 제일 밑으로 내려감)
											</span>
											<span>&darr;</span> <!-- 아래 화살표 추가 -->
										</div>
										
										<div id="alertObserverBlock"></div>
									</div>
									
									<%--채팅 발솔 부분 --%>
									<div class="text-muted d-flex justify-content-start align-items-center pe-3 pt-3 mt-2 gap-3">
										<img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp" alt="avatar 3" class="chat-avatar">
										<div class="position-relative">
<%--											<ul class="position-absolute bottom-100 mb-2 bg-white border rounded mt-1 w-100">--%>
<%--												--%>
<%--												<c:forEach var="idx" begin="1" end="5">--%>
<%--													${idx}--%>
<%--												</c:forEach>--%>
<%--												--%>
<%--												<li class="p-2">--%>
<%--													<img--%>
<%--															src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"--%>
<%--															alt="avatar" class="d-flex align-self-center me-3"--%>
<%--															width="60">--%>
<%--													허성진--%>
<%--												</li>--%>
<%--												--%>
<%--											</ul>--%>
											
											<input type="text" class="form-control form-control-lg" id="messageInput" placeholder="메세지를 입력해주세요." />
										</div>
										
										<%--파일--%>
										<label for="uploadFiles" style="cursor: pointer">
											<i class="fas fa-paperclip"></i>
										</label>

										<%-- 숨겨진 파일 인풋 --%>
										<input type="file" name="uploadFiles" id="uploadFiles" class="d-none">
										
										<%--이모티콘--%>
<%--										<a class="text-muted" href="#!">--%>
<%--											<i class="fas fa-smile"></i>--%>
<%--										</a>--%>
										<%--submitMessage--%>
										<a id="submitMessage" class="text-muted" href="#!">
											<i class="fas fa-paper-plane"></i>
										</a>
									</div>
								</div>
							</div>
						
						</div>
					</div>
				
				</div>
			</div>
		
		</div>
	</section>
	<c:import url="../layout/footer.jsp" />
</main>

<c:import url="../layout/prescript.jsp" />

<%--<script type="importmap">--%>
<%--	{--%>
<%--		"imports": {--%>
<%--			"sockjs-client": "https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js",--%>
<%--			"stompjs": "https://cdn.jsdelivr.net/npm/stompjs@2/lib/stomp.min.js"--%>
<%--		}--%>
<%--	}--%>
<%--</script>--%>
<%--<script src="https://ga.jspm.io/npm:es-module-shims@1.5.1/dist/es-module-shims.js" crossorigin="anonymous"></script>--%>
<%--<script type="module">--%>
<%--  import Stomp from 'stompjs';--%>

<%--  console.log(Stomp)--%>
<%--&lt;%&ndash;  import SockJS from "sockjs-client";&ndash;%&gt;--%>

<%--&lt;%&ndash;  console.log(Stomp, SockJS)&ndash;%&gt;--%>

<%--</script>--%>

<script>
  window.onload = function(target) {
    // 채팅 무한 스크롤
    let chatList = document.querySelector("#chatList");
    let observerBlock = document.querySelector("#observerBlock");
    let scrollHeight = chatList.scrollHeight;

    let intersectionObserver = new IntersectionObserver(async (entries) => {
      if(entries[0].intersectionRatio > 0) {
        // 마지막 채팅인 경우 넘기기
        // 채팅 30개씩 가져오기
        console.log("여기가 옵저버 만나면 발동!~");

        // 여기서 채팅 목록 받아오기
        // buildChatMessage(observerBlock, {});

        // 메시지가 추가된 후 스크롤을 맨 아래로 이동
        // 현재 스크롤 위치와 전체 높이를 비교하여, 사용자가 스크롤을 맨 아래로 안 내린 경우에는 위치를 그대로 유지


        // 채팅 불러온 경우 지금 보던 스크롤 위치 고정
        // if(!chatList) return;
        // const currentScrollHeight = chatList.scrollHeight;
        // chatList.scrollTop = currentScrollHeight - scrollHeight;
				//
        // scrollHeight = currentScrollHeight;
      }
    });
    intersectionObserver.observe(observerBlock);
    
    let intersectionObserver2 = new IntersectionObserver(async (entries) => {
      if(entries[0].intersectionRatio > 0) {
        document.querySelector("#chatInnerAlert").classList.add("d-none");
      }
    });

    // 옵저버 설정
    intersectionObserver2.observe(document.querySelector("#alertObserverBlock"));

    // 채팅방 클릭
    let prevChatRoomNo = null;
    document.querySelectorAll(".chatRoom").forEach(dom => {
      dom.addEventListener("click", (e) => {
        e.stopPropagation();
        const chttRoomNo = dom.dataset.chttRoomNo; // this는 li 요소를 가리킴
        document.querySelector("#realChatList").innerHTML = ""; // 전에 보던 메세지 삭제
        // 채팅방 커넥션 끊고
        disconnectWebSocket({chttRoomNo: prevChatRoomNo}); // 나가는 채팅방 연결 끊기

        // 채팅창 비 활성화
        document.querySelectorAll(".chatRoom").forEach((dom) => {
          dom.classList.remove("bg-body-secondary");
        })
				
        if(chttRoomNo === prevChatRoomNo)  {
					// 채팅창 비활성 화
          document.querySelector("#chat").classList.add("d-none");
          
          prevChatRoomNo = null;
          return;
				}

				// 뱃지 데이터 초기화
				dom.querySelector(".read-badge").innerHTML = "0";
        dom.querySelector(".read-badge").classList.add("d-none");
				
        getChatMessage({chttRoomNo}); // 이전 메세지 가져오기
        chatWebSocketConnect({chttRoomNo}); // 들어가는 채팅방 연결
				
        document.querySelector("#chat").classList.remove("d-none");
        prevChatRoomNo = chttRoomNo; // 현재 채팅방 번호
  
        dom.classList.add("bg-body-secondary");
      })
    })

    document.querySelectorAll(".chatRoom")[0].click();
  }

  function getChatMessage({chttRoomNo}) {
     // emp 이거는 로그인 정보로 바꿔야함
    fetch(`/chat/messageList?chttRoomNo=\${chttRoomNo}&emplNo=\${emp}`)
      .then((res) => res.json())
      .then((result) => {
        // console.log(result);
        if(result != null) {
          result.forEach((message) => {
            buildChatMessage(document.querySelector("#realChatList"), {message});
          })
        }
      })
      .catch((error) => {
        console.error(error);
      })
      .finally(() => {
        setTimeout(() => {
          let chatList = document.querySelector("#chatList");
          chatList.scrollTop = chatList.scrollHeight; // 채팅 밑으로 내리기
				}, 10)
      })
  }
</script>
</body>
</html>
