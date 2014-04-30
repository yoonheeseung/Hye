<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>

<!-- 관리자 메인 본문 -->
<div id="aIndex_cont">
  <div id="aGongji_cont">
   <h2 class="aGongji_title">관리자 공지내용</h2>
   <table id="aGongji_t">
    <tr>
     <th>제목</th>
     <td>${gcont.gongji_title}</td>
    </tr>
    <tr>
     <th>내용</th>
     <td>${gongji_cont}</td>
    </tr>
    <tr>
     <th>조회수</th>
     <td>${gcont.gongji_hit}</td>
    </tr>
   </table>
   <div id="aGongji_menu">
   <input type="button" value="목록" class="input_b"
   onclick="location='admin_gongji_list.html?page=${page}'" />
   </div>
  </div>
</div>

<%@ include file="../../include/admin_footer.jsp" %>