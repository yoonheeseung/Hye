<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- html5 문서정의 --%>
<html>
<head>
<meta charset="UTF-8">
<%-- html5 언어코딩 타입 설정 --%>
<title>관리자 로그인 화면</title>
<link rel="stylesheet" type="text/css" href="./css/admin.css" />
<script src="./js/jquery.js"></script>
<script>
  function admin_check(){
	  if($.trim($("#admin_id").val())==""){
		  alert("관리자 아이디를 입력하세요!");
		  $("#admin_id").val("").focus();
		  return false;
	  }
	  if($.trim($("#admin_pwd").val())==""){
		  alert("관리자 비번을 입력하세요!");
		  $("#admin_pwd").val("").focus();
		  return false;
	  }
  }
</script>
</head>
<body>
<div id="aLogin_wrap">
 <h2 class="aLogin_title">관리자 로그인</h2>
 <form method="post" action="admin_login_ok.html"
 onsubmit="return admin_check();">
  <table id="aLogin_t">
   <tr>
    <th>관리자 아이디</th>
    <td>
    <input name="admin_id" id="admin_id" size="14" 
    tabindex="1" />
    <%-- tabindex속성을 1,2로 지정하면 관리자 아이디를 입력하고
    탭키를 누르면 다음 커서가 로그인 서브밋으로 이동하지 않고,
    관리자 비번 입력창으로 이동한다. --%>
    </td>
    <th rowspan="2">
    <input type="submit" value="로그인" class="input_s" />
    </th>
   </tr>
   <tr>
    <th>관리자 비밀번호</th>
    <td>
    <input type="password" name="admin_pwd" id="admin_pwd"
    size="14" tabindex="2" />
    </td>
   </tr>
  </table>
 </form>
</div>
</body>
</html>