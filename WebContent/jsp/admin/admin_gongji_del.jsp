<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../../include/admin_header.jsp"%>

<!-- 관리자 메인 본문 -->
<div id="aIndex_cont">
	<div id="aGDel_wrap">
		<h2 class="aGDel_title">관리자 공지삭제</h2>
		<form method="post" action="admin_gongji_delok.html"
			onsubmit="return del_check();">
			<input type="hidden" name="gongji_no" value="${gcont.gongji_no}" />
			<input type="hidden" name="page" value="${page}" />
			<table id="aGDel_t">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="del_pwd" id="del_pwd" size="14" /></td>
				</tr>
			</table>
			<div id="aGDel_menu">
				<input type="submit" value="공지삭제" class="input_b" />
				<input type="reset" value="삭제취소" class="input_b"
				onclick="$('#del_pwd').focus();" />
			</div>
		</form>
	</div>

</div>
<jsp:include page="../../include/admin_footer.jsp" />
