<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%--해당 파일에 타이틀 정보를 넣어준다--%>
<c:set var="title" scope="application" value="메인" />

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
	<c:import url="../layout/prestyle.jsp"/>
<%--	<%@ include file="../layout/prestyle.jsp" %>--%>
</head>
<body>
<%--<%@ include file="../layout/sidebar.jsp" %>--%>
<main class="main-wrapper">
<%--	<%@ include file="../layout/header.jsp" %>--%>
	<section class="section">
		<div class="container-fluid">
<%--			<%@ include file="../layout/title-wrap.jsp" %>--%>
			<%-- row 안에 작업 하기 한줄 --%>
			<%-- col 세로 몇칸을 가져갈지 --%>
			<div class="row">
				<div class="col-12">
					div
				
				</div>
			</div>
		</div>

	</section>
<%--	<%@ include file="../layout/footer.jsp" %>--%>
</main>

<%--<%@ include file="../layout/prescript.jsp" %>--%>
</body>
</html>
