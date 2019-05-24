<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp"></c:import>
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	<div class="container">
		<h1>QnA List</h1>
		<table class="table table-hover;">
			<tr>
				<td>NUM</td>
				<td>TITLE</td>
				<td>WRITER</td>
				<td>DATE</td>
				<td>HIT</td>
			</tr>
			<c:forEach items="${list}" var="DTO">
				<tr>
					<td>${DTO.num}</td>
					<td>
					<c:forEach begin="1" end="${DTO.depth}" varStatus="i">&nbsp; &nbsp;</c:forEach>
					<%-- <c:forEach begin="1" end="${DTO.depth}" varStatus="i">
					--
					<c:if test="${i.last}">L</c:if>
					</c:forEach> --%>
					<a href="./qnaSelect?num=${DTO.num}">${DTO.title}</a></td>
					<td>${DTO.writer}</td>
					<td>${DTO.reg_date}</td>
					<td>${DTO.hit}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="container">
		<ul class="pager">
			<c:if test="${pager.curBlock gt 1}">
				<!--test안에 조건식 넣기-->
				<li class="previous"><a href="./qnaList?curPage=${pager.startNum-1}&kind=${pager.search.kind}&search=${pager.search.search}">Previous</a></li>
			</c:if>
			<li>
				<ul class="pagination">
				<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" step="1" var="i">
					<li><a href="./qnaList?curPage=${i}&kind=${pager.search.kind}&search=${pager.search.search}">${i}</a></li>
				</c:forEach>
				</ul>
			</li>
			<c:if test="${pager.curBlock lt pager.totalBlock}">
				<li class="next"><a href="./qnaList?curPage=${pager.lastNum+1}&kind=${pager.search.kind}&search=${pager.search.search}">Next</a></li>
			</c:if>
		</ul>
	</div>
	<div class="container">
	<a href="./qnaWrite">Go Write</a>
	</div>
</body>
</html>