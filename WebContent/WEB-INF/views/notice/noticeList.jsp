<%@page import="com.sh.notice.NoticeDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<NoticeDTO> ar = (ArrayList<NoticeDTO>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<jsp:include page="../temp/bootstrap.jsp"/>
</head>
<body>
<jsp:include page="../temp/header.jsp"/>
	<div class="container">
		<h1>Notice List</h1>
		<table class="table table-hover;">
			<tr>
				<td> NUM </td> <td> TITLE </td> <td> WRITER </td> <td>DATE</td> <td>HIT</td>
			</tr>
			<%for(NoticeDTO noticeDTO : ar){ %>
			<tr>
				<td><%=noticeDTO.getNum() %></td><td><%=noticeDTO.getTitle() %></td><td><%=noticeDTO.getWriter() %></td><td><%=noticeDTO.getReg_date() %></td><td><%=noticeDTO.getHit() %></td>
			</tr>
			<%} %>
		</table>
	</div>
	<a href="./noticeWrite">Go Write</a>
</body>
</html>