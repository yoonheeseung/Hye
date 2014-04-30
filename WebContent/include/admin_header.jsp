<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--JSTL 태그라이브러리 추가 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
<script src="./js/jquery.js"></script>
<script src="./js/admin_gongji.js"></script>
</head>
<body>
 <div id="aIndex_wrap">
 
  <!-- 상단부분 -->
  <div id="aIndex_header">
   <!-- 관리자 로고 -->
   <div id="aIndex_logo">
    <a href="admin_index.html">
    <img src="./images/admin/admin_logo.jpg" />
    </a>
   </div>
   <!-- 상단메뉴 -->
   <div id="aIndex_menu">
    <ul>
     <li><a href="admin_gongji_list.html">공지사항</a></li>
     <li><a href="#">게시판</a></li>
     <li><a href="#">자료실</a></li>
     <li><a href="#">회원관리</a></li>
    </ul>
   </div>
   <!-- 오른쪽 부분 -->
   <div id="aIndex_right">
   <form method="post" action="admin_logout.html">
    <span id="aIndex_login">
    ${admin_name}님 로그인을 환영합니다!
    <input type="submit" value="로그아웃" />
    </span>    
   </form>
   </div>
  </div>
  
  <div class="clear"></div>