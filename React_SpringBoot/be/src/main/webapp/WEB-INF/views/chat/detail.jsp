<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Document</title>
  <%@ include file="../layout/prestyle.jsp" %>


</head>
<body>
<%@ include file="../layout/header.jsp" %>

<style>
    a {
        text-decoration: none;
    }

    .chat-avatar {
        width: 45px;
        height: 100%;
    }

    .chat-message {
        width: 80%;
    }

    #chatList {
        max-height: 1000px;
    }
</style>

<section>
  <div class="container py-5">
    <div class="row">
      <div class="col-md-12">
        <div class="card" id="chat3">
          <div class="card-body">
            <div class="row">
              <div class="col-md-6 col-lg-5 col-xl-4 mb-4 mb-md-0">
                <div class="p-3">
                  <div class="input-group rounded mb-3">
                    <input type="search" class="form-control rounded" placeholder="Search"
                           aria-label="Search"
                           aria-describedby="search-addon" />
                    <span class="input-group-text border-0" id="search-addon">
                                              <i class="fas fa-search"></i>
                                        </span>
                  </div>

                  <div>
                    <ul class="list-unstyled mb-0">
                      <li class="p-2 border-bottom bg-body-secondary">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-success badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Marie Horwitz</p>
                              <p class="small text-muted">Hello, Are you there?</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">Just now</p>
                            <span class="badge bg-danger rounded-pill float-end">3</span>
                          </div>
                        </a>
                      </li>
                      <li class="p-2 border-bottom">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava2-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-warning badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Alexa Chung</p>
                              <p class="small text-muted">Lorem ipsum dolor sit.</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">5 mins ago</p>
                            <span class="badge bg-danger rounded-pill float-end">2</span>
                          </div>
                        </a>
                      </li>
                      <li class="p-2 border-bottom">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-success badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Danny McChain</p>
                              <p class="small text-muted">Lorem ipsum dolor sit.</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">Yesterday</p>
                          </div>
                        </a>
                      </li>
                      <li class="p-2 border-bottom">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava4-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-danger badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Ashley Olsen</p>
                              <p class="small text-muted">Lorem ipsum dolor sit.</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">Yesterday</p>
                          </div>
                        </a>
                      </li>
                      <li class="p-2 border-bottom">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava5-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-warning badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Kate Moss</p>
                              <p class="small text-muted">Lorem ipsum dolor sit.</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">Yesterday</p>
                          </div>
                        </a>
                      </li>
                      <li class="p-2">
                        <a href="#!" class="d-flex justify-content-between">
                          <div class="d-flex flex-row">
                            <div>
                              <img
                                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                                alt="avatar" class="d-flex align-self-center me-3"
                                width="60">
                              <span class="badge bg-success badge-dot"></span>
                            </div>
                            <div class="pt-1">
                              <p class="fw-bold mb-0">Ben Smith</p>
                              <p class="small text-muted">Lorem ipsum dolor sit.</p>
                            </div>
                          </div>
                          <div class="pt-1">
                            <p class="small text-muted mb-1">Yesterday</p>
                          </div>
                        </a>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>

              <div class="col-md-6 col-lg-7 col-xl-8">
                <div id="chatList" class="pt-3 pe-3 overflow-scroll h-50">
                  <div id="observerBlock">ㄴㄴㄴ</div>


                  <div class="d-flex flex-row justify-content-start">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                    <div class="chat-message">
                      <p class="small p-2 ms-3 mb-1 rounded-3 bg-body-tertiary">Lorem ipsum
                        dolor
                        sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt
                        ut labore et
                        dolore
                        magna aliqua.</p>
                      <p class="small ms-3 mb-3 rounded-3 text-muted float-end">12:00 PM | Aug
                        13</p>
                    </div>
                  </div>

                  <div class="d-flex flex-row justify-content-end">
                    <div class="chat-message">
                      <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Ut enim ad
                        minim veniam,
                        quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat.</p>
                      <p class="small me-3 mb-3 rounded-3 text-muted">12:00 PM | Aug 13</p>
                    </div>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                  </div>

                  <div class="d-flex flex-row justify-content-start">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                    <div class="chat-message">
                      <p class="small p-2 ms-3 mb-1 rounded-3 bg-body-tertiary">Duis aute
                        irure
                        dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat
                        nulla pariatur.
                      </p>
                      <p class="small ms-3 mb-3 rounded-3 text-muted float-end">12:00 PM | Aug
                        13</p>
                    </div>
                  </div>

                  <div class="d-flex flex-row justify-content-end">
                    <div class="chat-message">
                      <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Excepteur
                        sint occaecat
                        cupidatat
                        non proident, sunt in culpa qui officia deserunt mollit anim id est
                        laborum.</p>
                      <p class="small me-3 mb-3 rounded-3 text-muted">12:00 PM | Aug 13</p>
                    </div>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                  </div>

                  <div class="d-flex flex-row justify-content-start">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                    <div class="chat-message">
                      <p class="small p-2 ms-3 mb-1 rounded-3 bg-body-tertiary">Sed ut
                        perspiciatis
                        unde omnis iste natus error sit voluptatem accusantium doloremque
                        laudantium, totam
                        rem
                        aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto
                        beatae vitae
                        dicta
                        sunt explicabo.</p>
                      <p class="small ms-3 mb-3 rounded-3 text-muted float-end">12:00 PM | Aug
                        13</p>
                    </div>
                  </div>

                  <div class="d-flex flex-row justify-content-end">
                    <div class="chat-message">
                      <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Nemo enim
                        ipsam
                        voluptatem quia
                        voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni
                        dolores eos
                        qui
                        ratione voluptatem sequi nesciunt.</p>
                      <p class="small me-3 mb-3 rounded-3 text-muted">12:00 PM | Aug 13</p>
                    </div>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                  </div>

                  <div class="d-flex flex-row justify-content-start">
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                    <div class="chat-message">
                      <p class="small p-2 ms-3 mb-1 rounded-3 bg-body-tertiary">Neque porro
                        quisquam
                        est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit,
                        sed quia non
                        numquam
                        eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat
                        voluptatem.</p>
                      <p class="small ms-3 mb-3 rounded-3 text-muted float-end">12:00 PM | Aug
                        13</p>
                    </div>
                  </div>

                  <div class="d-flex flex-row justify-content-end">
                    <div class="chat-message">
                      <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Ut enim ad
                        minima veniam,
                        quis
                        nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut
                        aliquid ex ea
                        commodi
                        consequatur?</p>
                      <p class="small me-3 mb-3 rounded-3 text-muted">12:00 PM | Aug 13</p>
                    </div>
                    <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                         alt="avatar 1" class="chat-avatar">
                  </div>

                </div>

                <div class="text-muted d-flex justify-content-start align-items-center pe-3 pt-3 mt-2">
                  <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                       alt="avatar 3" class="chat-avatar">
                  <input type="text" class="form-control form-control-lg"
                         id="exampleFormControlInput2"
                         placeholder="Type message">
                  <a class="ms-1 text-muted" href="#!"><i class="fas fa-paperclip"></i></a>
                  <a class="ms-3 text-muted" href="#!"><i class="fas fa-smile"></i></a>
                  <a class="ms-3" href="#!"><i class="fas fa-paper-plane"></i></a>
                </div>

              </div>
            </div>

          </div>
        </div>

      </div>
    </div>

  </div>
</section>

<%@ include file="../layout/footer.jsp" %>
<%@ include file="../layout/prescript.jsp" %>

<script>
  window.onload = function() {
    // 채팅 무한 스크롤
    let chatList = document.querySelector("#chatList");
    let observerBlock = document.querySelector("#observerBlock");
    let scrollHeight = chatList.scrollHeight;
    chatList.scrollTop = scrollHeight; // 채팅 밑으로 내리기

    let intersectionObserver = new IntersectionObserver((entries) => {
      if(entries[0].intersectionRatio > 0) {
        console.log("여기가 옵저버 자리임");
        for(let i = 0; i < 10; i++) {
          // 처음 2번째에 추가
          const messageHTML = i % 2 === 0 ? `
                        <div class="d-flex flex-row justify-content-start">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava6-bg.webp"
                                 alt="avatar 1" class="chat-avatar">
                            <div class="chat-message">

                                <p class="small p-2 ms-3 mb-1 rounded-3 bg-body-tertiary">Lorem ipsum
                                    dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et
                                    dolore magna aliqua.🫨</p>
                                <p class="small ms-3 mb-3 rounded-3 text-muted float-end">12:00 PM | Aug 13 \${i}</p>
                            </div>
                        </div>
                    ` : `
                        <div class="d-flex flex-row justify-content-end">
                            <div class="chat-message">
                                <p class="small p-2 me-3 mb-1 text-white rounded-3 bg-primary">Ut enim ad
                                    minim veniam,
                                    quis
                                    nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                    consequat.</p>
                                <p class="small me-3 mb-3 rounded-3 text-muted">12:00 PM | Aug 13</p>
                            </div>
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="avatar 1" class="chat-avatar">
                        </div>
                    `;

          // observerBlock 뒤에 메시지 추가
          observerBlock.insertAdjacentHTML("afterend", messageHTML);
          // 메시지가 추가된 후 스크롤을 맨 아래로 이동
          // 현재 스크롤 위치와 전체 높이를 비교하여, 사용자가 스크롤을 맨 아래로 안 내린 경우에는 위치를 그대로 유지
        }

        // 채팅 불러온 경우 지금 보던 스크롤 위치 고정
        if(!chatList) return;
        const currentScrollHeight = chatList.scrollHeight;
        chatList.scrollTop = currentScrollHeight - scrollHeight;

        scrollHeight = currentScrollHeight;
      }
    });

    intersectionObserver.observe(observerBlock);
  }

</script>
</body>
</html>