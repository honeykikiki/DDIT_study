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
	<c:import url="../layout/prestyle.jsp" />
	
</head>
<body>
<c:import url="../layout/sidebar.jsp" />
<main class="main-wrapper">
	<c:import url="../layout/header.jsp" />
	
	<section class="section">
		<div class="container-fluid">
			<c:import url="../layout/title-wrap.jsp" />
			
			<%-- row 안에 작업 하기 한줄 --%>
			<%-- col 세로 몇칸을 가져갈지 --%>
			
			<div class="row">
				<div class="col-12">
					<div class="card-style">
						<h1>공부 하면 좋을 내용</h1>
						<ul>
							<li>row, col</li>
							<li>
								m-1 ~ 5, mt, ml, mr, mb, 마진
								<a href="https://getbootstrap.com/docs/5.3/utilities/spacing/">링크</a>
							</li>
							<li>
								p-1 ~ 5, pt, pl, pr, pb, px, py, 패딩
								<a href="https://getbootstrap.com/docs/5.3/utilities/spacing/">링크</a>
							</li>
							<li>
								card
								<a href="https://demo.plainadmin.com/cards">링크</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="row mt-5">
				<div class="col-12">
					<div class="card-style">
						<%-- 카드 스타일로 넣어야 뒷 배경이 생긴다--%>
						여기 안에서 작업을 진행하면 됩니다~~~~
						<div>
							<a href="https://demo.plainadmin.com/" target="_blank">템플릿 사이트</a>
							<p>여기 들어가면 우리가 사용한 템플릿 데모 보는게 가능</p>
						</div>
						<div>
							<a href="https://getbootstrap.com/docs/5.3/getting-started/introduction/" target="_blank">
								부트스트랩5 사이트
							</a>
							<p>여기 들어가면 클래스가 어떤 css인지, component 정보도 확인 가능</p>
						</div>
					</div>
				</div>
			</div>
			
			<%--아이콘 사용--%>
			<div class="row mt-5">
				<div class="col-3">
					<div class="icon-card mb-30">
						<div class="icon purple">
							<i class="lni lni-cart-full"></i>
						</div>
						<div class="content">
							<h6 class="mb-10">New Orders</h6>
							<h3 class="text-bold mb-10">34567</h3>
							<p class="text-sm text-success">
								<i class="lni lni-arrow-up"></i> +2.00%
								<span class="text-gray">(30 days)</span>
							</p>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="card-style">
						여기 안에서 작업을 진행하면 됩니다~~~~
					</div>
				</div>
			</div>
			
			
			<div class="card mt-4">
				<div class="card-header p-3">
					<h5 class="mb-0">Notifications</h5>
					<p class="text-sm mb-0">
						Notifications on this page use Toasts from Bootstrap. Read more details <a href="https://getbootstrap.com/docs/5.0/components/toasts/" target="
          ">here</a>.
					</p>
				</div>
				<div class="card-body p-3">
					<div class="row">
						<div class="col-lg-3 col-sm-6 col-12">
							<button class="btn bg-gradient-success w-100 mb-0 toast-btn" type="button" data-target="successToast">Success</button>
						</div>
						<div class="col-lg-3 col-sm-6 col-12 mt-sm-0 mt-2">
							<button class="btn bg-gradient-info w-100 mb-0 toast-btn" type="button" data-target="infoToast">Info</button>
						</div>
						<div class="col-lg-3 col-sm-6 col-12 mt-lg-0 mt-2">
							<button class="btn bg-gradient-warning w-100 mb-0 toast-btn" type="button" data-target="warningToast">Warning</button>
						</div>
						<div class="col-lg-3 col-sm-6 col-12 mt-lg-0 mt-2">
							<button class="btn bg-gradient-danger w-100 mb-0 toast-btn" type="button" data-target="dangerToast">Danger</button>
						</div>
					</div>
				</div>
			</div>
			
			
			<div class="toast-container position-fixed end-0 p-3" style="top: 80px">
			
				
				<div class="toast fade hide p-2 mt-2 bg-gradient-info" role="alert" aria-live="assertive" id="infoToast" aria-atomic="true">
					<div class="toast-header bg-transparent border-0">
						<i class="material-symbols-rounded text-white me-2">
							notifications
						</i>
						<span class="me-auto text-white font-weight-bold">Material Dashboard </span>
						<small class="text-white">11 mins ago</small>
						<i class="fas fa-times text-md text-white ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
					</div>
					<hr class="horizontal light m-0">
					<div class="toast-body text-white">
						Hello, world! This is a notification message.
					</div>
				</div>
				
				<div class="toast fade hide p-2 mt-2 bg-white" role="alert" aria-live="assertive" id="warningToast" aria-atomic="true">
					<div class="toast-header border-0">
						<i class="material-symbols-rounded text-warning me-2">
							travel_explore
						</i>
						<span class="me-auto font-weight-bold">Material Dashboard </span>
						<small class="text-body">11 mins ago</small>
						<i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
					</div>
					<hr class="horizontal dark m-0">
					<div class="toast-body">
						Hello, world! This is a notification message.
					</div>
				</div>
				
				<div class="toast fade hide p-2 mt-2 bg-white" role="alert" aria-live="assertive" id="dangerToast" aria-atomic="true">
					<div class="toast-header border-0">
						<i class="material-symbols-rounded text-danger me-2">
							campaign
						</i>
						<span class="me-auto text-gradient text-danger font-weight-bold">Material Dashboard </span>
						<small class="text-body">11 mins ago</small>
						<i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
					</div>
					<hr class="horizontal dark m-0">
					<div class="toast-body">
						Hello, world! This is a notification message.
					</div>
				</div>
			</div>
			
		</div>
	
		
	</section>
	<c:import url="../layout/footer.jsp" />
</main>

<c:import url="../layout/prescript.jsp" />

</body>
</html>
