<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
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
  
  <!-- 관리자 메인 본문 -->
  <div id="aIndex_cont">
   <h2 class="aCont_title">관리자 메인 화면입니다.</h2>
  </div>
  
   <div class="clear"></div>
   
  <!-- 관리자 하단부분 -->
  <div id="aIndex_footer">
   <h2 class="aFooter_title">
   서울시 마포구 대흥동 00빌딩...
   </h2>
  </div>
 </div>
</body>
</html>