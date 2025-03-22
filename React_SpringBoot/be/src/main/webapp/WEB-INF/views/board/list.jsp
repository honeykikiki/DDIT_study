<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--해당 파일에 타이틀 정보를 넣어준다--%>
<c:set var="title" scope="application" value="게시판" />

<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>게시판 목록</title>
  <%@ include file="../layout/prestyle.jsp" %>
</head>
<body>
<%--<%@ include file="../layout/sidebar.jsp" %>--%>
<main class="main-wrapper">
<%--  <%@ include file="../layout/header.jsp" %>--%>
  <section class="section">
    <div class="container-fluid">
<%--      <%@ include file="../layout/title-wrap.jsp" %>--%>

      <div class="row">
        <div class="col-12">
          <div class="card-style">
            <table class="table table-hover">
              <thead>
              <tr>
                <th scope="col">넘버</th>
                <th scope="col">제목</th>
                <th scope="col">내용</th>
                <th scope="col">작성자</th>
                <th scope="col">생성일</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="board" items="${list}">
                <tr>
                  <td>
                    A${board.rnum}
                  </td>
                  <td>${board.title}</td>
                  <td>${board.content}</td>
                  <td>${board.writer}</td>
                  <td>${board.createDate}</td>
                </tr>
              </c:forEach>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </section>
  <%@ include file="../layout/footer.jsp" %>
</main>
<%@ include file="../layout/prescript.jsp" %>

</body>
</html>

