<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../temp/bootstrap.jsp" />
<style type="text/css">
	.del{
		color: red;
		cursor: pointer;
	}
</style>
</head>
<body>
	<c:import url="../temp/header.jsp" />
	<div class="container">
		<h1>QnA Write</h1>
		<form action="./qnaWrite" method="post" enctype="multipart/form-data"><!-- enctype이 multipart로 들어가면 requset가 여러개로 조각나서 들어간다.  -->
		<!-- 파일 업로드하려면 메서드 방식은 post이다 무조건!! -->
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
			<div class="form-group" id="addfile" >
				<label for="file">File:</label> <!-- 파일은 이진데이터이다. -->
				
			</div>
			<div class="form-group">
				<input type="button" class="btn btn-danger" id="btn" value="Add" >
			</div>
			<button class="btn btn-primary">Write</button>
		</form>
	</div>
<script type="text/javascript">
	var c = 0;
	var d1 = 0;
	$("#btn").click(function() {
		if(c<5){
		$('#addfile').append('<div id="'+d1+'"><input type="file" class="form-control" id="" name="f'+d1+'"><span title="'+d1+'" class="del">X</span></div>');
			c=c+1;
			d1++;
		}else{
			alert("5개 이하만 가능");
		}
	});
	
	$("#addfile").on("click", ".del", function() {
		/* $(this).prev().remove();
		$(this).remove();  */
		var v = $(this).attr('title');
		$('#'+v).remove();
		c--;
	});
	
	
	
</script>

</body>
</html>