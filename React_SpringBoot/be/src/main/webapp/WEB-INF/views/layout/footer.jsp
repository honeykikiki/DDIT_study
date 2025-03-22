<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<footer class="footer">
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-6 order-last order-md-first">
        <div class="copyright text-center text-md-start">
          <p class="text-sm">
            Designed and Developed by
            <a href="https://plainadmin.com" rel="nofollow" target="_blank">
              PlainAdmin
            </a>
          </p>
        </div>
      </div>
      <!-- end col-->
      <div class="col-md-6">
        <div class="terms d-flex justify-content-center justify-content-md-end">
          <a href="#0" class="text-sm">Term & Conditions</a>
          <a href="#0" class="text-sm ml-15">Privacy & Policy</a>
        </div>
      </div>
    </div>
    <!-- end row -->
  </div>
  <!-- end container -->
</footer>

<%-- INFO 알림 --%>
<div class="toast-container position-fixed end-0 p-3" style="top: 80px">
  <div class="toast fade hide p-2 bg-white" role="alert" aria-live="assertive" id="infoSuccess" aria-atomic="true">
    <div class="toast-header border-0">
      <i class="lni lni-checkmark text-success"></i>
      <span class="ms-2 me-auto text-bold">{{title}}</span>
      <small class="text-body">{{sender}}</small>
      <i class="fas fa-times text-md ms-3 cursor-pointer" data-bs-dismiss="toast" aria-label="Close"></i>
    </div>
    <hr class="horizontal dark m-0">
    <div class="toast-body">
      {{body}}
    </div>
  </div>
</div>
<%-- INFO 알림 --%>