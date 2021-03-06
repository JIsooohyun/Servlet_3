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
		<h1>Notice Write</h1>
		<form action="./noticeWrite" method="post" enctype="multipart/form-data"><!-- enctype이 multipart로 들어가면 requset가 여러개로 조각나서 들어간다.  -->
			<div class="form-group">
				<label for="title">Title:</label> 
				<input type="text" class="form-control" id="title" name="title">
			</div>
			<div class="form-group">
				<label for="writer">Writer:</label> 
				<input type="text" class="form-control" id="writer" name="writer">
			</div>
			<div class="form-group">
				<label for="contents">Contents:</label>
				<textarea class="form-control" rows="20" id="contents" name="contents"></textarea>
			</div>
			<div class="form-group">
				<label for="file">File:</label> <!-- 파일은 이진데이터이다. -->
				<input type="file" class="form-control" id="f1" name="f1">
			</div>
			<button class="btn btn-primary">Write</button>
		</form>
	</div>

</body>
</html>