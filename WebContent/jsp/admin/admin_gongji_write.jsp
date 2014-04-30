<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp" %>
<%-- ../../ 두단계 상위 폴더로 이동 --%>

 <!-- 관리자 메인 본문 -->
 <div id="aIndex_cont">
    <div id="aGongji_wrap">
     <h2 class="aGongji_title">관리자 공지작성</h2>
     <form method="post" action="admin_gongji_ok.html" onsubmit="return gongji_check();">
      <table id="aGongji_t">
       <tr>
        <th>공지이름</th>
        <td>
        <input name="gongji_name" id="gongji_name"
        size="14" />
        </td>
       </tr>
       <tr>
        <th>공지제목</th>
        <td>
        <input name="gongji_title" id="gongji_title"
        size="36" />
        </td>
       </tr>
       <tr>
        <th>비밀번호</th>
        <td>
        <input type="password" name="gongji_pwd"
        id="gongji_pwd" size="14" />
        </td>
       </tr>
       <tr>
        <th>공지내용</th>
        <td>
        <textarea name="gongji_cont" id="gongji_cont"
        rows="8" cols="35"></textarea>
        </td>
       </tr>
      </table>
      <div id="aGongji_menu">
      <input type="submit" value="공지작성" class="input_b"/>
      <input type="reset" value="작성취소" class="input_b"
      onclick="$('#gongji_name').focus();" />
      <input type="button" value="공지목록" class="input_b" 
         onclick="location='admin_gongji_list.html?page=${page}'" />      
      </div>
     </form>
    </div>
  </div>