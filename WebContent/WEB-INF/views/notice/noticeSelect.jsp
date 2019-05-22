<%@page import="com.sh.notice.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="../temp/bootstrap.jsp" />
</head>
<body>
	<jsp:include page="../temp/header.jsp" />
	<div class="container">
		<h1>Notice Select</h1>
		<table class="table table-hover">
				<tr>
					<td>NUM</td>
					<td>TITLE</td>
					<td>CONTENTS</td>
					<td>WRITER</td>
					<td>DATE</td>
					<td>HIT</td>
				</tr>
				<tr>
					<td>${noticeDTO.num}</td>
					<td>${requestScope.noticeDTO.title}</td>
					<td>${noticeDTO.contents}</td>
					<td>${requestScope.noticeDTO.writer}</td>
					<td>${requestScope.noticeDTO.reg_date}</td>
					<td>${requestScope.noticeDTO.hit}</td>
				</tr>
			</table>
		<div class="container">
		<a href="./noticeDelete?num=${noticeDTO.num}">DELETE</a>
		<a href="./noticeUpdate?num=${noticeDTO.num}">UPDATE</a>
		</div>
	</div>
</body>
</html>