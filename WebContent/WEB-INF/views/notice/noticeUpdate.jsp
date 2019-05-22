<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp" />
</head>
<body>
	<c:import url="../temp/header.jsp" />
	<div class="container">
		<h1>Notice Update</h1>
		<form action="./noticeUpdate" method="post">
			<div class="form-group">
				<label for="title">Title:</label> 
				<input type="text" class="form-control" id="title" name="title" value=${noticeDTO.title} >
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label> 
				<input type="text" class="form-control" id="writer" name="writer" value=${noticeDTO.writer}>
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea class="form-control" rows="20" id="contents"name="contents">${noticeDTO.contents}</textarea>
			</div>
			<button class="btn btn-primary">Write</button>
		</form>
	</div>
</body>
</html>