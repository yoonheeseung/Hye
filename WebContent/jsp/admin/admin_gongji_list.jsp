<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../include/admin_header.jsp"%>

<!-- 관리자 메인 본문 -->
<div id="aIndex_cont">
	<div id="aGongji_list">
		<h2 class="aGongji_title">관리자 공지목록</h2>
		<div class="aGongji_count">게시물수: ${listcount}개</div>
		<%--복사 시작 --%>
		<table id="aGongji_t">
			<tr>
				<th width="6%">번호</th>
				<th width="40%">제목</th>
				<th width="14%">작성자</th>
				<th width="17%">작성일</th>
				<th width="10%">조회수</th>
				<th width="14%">수정/삭제</th>
			</tr>

			<c:if test="${!empty glist}">
				<c:forEach var="list" items="${glist}">
				<tr>
				<td align="center">
				${list.gongji_no}</td>
				<td align="left">
				<a href="admin_gongji_cont.html?gongji_no=${list.gongji_no}&page=${page}&state=cont">${list.gongji_title}</a>
				<%-- 복수개 파라미터 값을 get방식으로 넘기는 법
				     *.html?파라미터이름=값&파라미터이름=값&...&기호로 구분해서
				      값을 넘김 --%>
					<tr>
						<td align="center">
						${list.gongji_no}</td>

						<td align="left">
<a	href=
"admin_gongji_cont.html?gongji_no=${list.gongji_no}&page=${page}&state=cont">${list.gongji_title}</a>
							<%-- 복수개 피마미터 값을 GET방식으로 넘기는 법.
              *.html?피라미터이름=값&피라미터이름=값&... &기호로 구분해서
              값을 넘김. --%></td>

						<td align="center">${list.gongji_name}</td>
						<td align="center">${list.gongji_date}</td>
						<td align="center">${list.gongji_hit}</td>
						<td align="center">
<input type="button" value="수정" class="input_b"
onclick=
"location=
'admin_gongji_cont.html?gongji_no=${list.gongji_no}&page=${page}&state=edit'" />

<input type="button" value="삭제" class="input_b"
onclick="if(confirm('정말로 삭제할까요?')==true){
location=
'admin_gongji_cont.html?gongji_no=${list.gongji_no}&page=${page}&state=del'; 
}else{return;}" />
<%-- 자바스크립트에서 window객체 하위의 confirm()메서드는 확인/
취소 버튼을 가진 경고창을 만들어 준다.확인을 클릭하면 true,
취소를 클릭하면 false를 반환.삭제유무를 물어본다. --%>						
						</td>
					</tr>
				</c:forEach>
			</c:if>

			<c:if test="${empty glist}">
				<tr>
					<th colspan="5">공지목록이 없습니다!</th>
				</tr>
			</c:if>
		</table>

		<!-- 자료실 페이징 추가 -->
		<div id="bList_paging">

			<!-- 일반 페이징 -->
			<c:if test="${(empty find_field) && (empty find_name)}">
				<c:if test="${page <= 1 }">
			[이전]&nbsp;
			</c:if>
				<c:if test="${page > 1 }">
					<a href="admin_gongji_list.html?page=${page-1}">[이전]</a>&nbsp;
			</c:if>


				<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
					<c:if test="${a == page}">
					<${a}>
					<%--페이지 번호 클릭 선택시 실행되는 부분 --%>
					</c:if>

					<c:if test="${a != page}">
						<%-- 페이지번호가 선택되지 않았을때 실행되는 부분 --%>
						<a href="admin_gongji_list.html?page=${a}">[${a}]</a>&nbsp;
				</c:if>
					<!-- 쪽번호 출력되는 부분 -->
				</c:forEach>


				<c:if test="${page >= maxpage}">
			 [다음]
			</c:if>
				<c:if test="${page < maxpage }">
					<a href="admin_gongji_list.html?page=${page+1}">[다음]</a>
				</c:if>
			</c:if>
		</div>

		<div id="bListW_menu">
			<input type="button" value="공지작성" class="input_b"
				onclick="location='admin_gongji_write.html?page=${page}'" />
		</div>
		<%--복사끝 --%>
	</div>
</div>

<jsp:include page="../../include/admin_footer.jsp" />