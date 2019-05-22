<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setAttribute("s", 5);
	request.setAttribute("e", 10);
	String [] ar = {"a", "b", "c"};
	request.setAttribute("ar", ar);
	ArrayList<Integer> list = new ArrayList<Integer>();
	list.add(100);
	list.add(200);
	list.add(300);
	request.setAttribute("list", list);
%>
<c:forEach begin="${s}" end="${e}" step="1" var="i"><!-- 1부터 5까지 돌린다. step은 1씩 증가 var는 변수--><!-- for(int i=1; i<=5; i++) -->
	${i}
</c:forEach>

<c:forEach items="${ar}" var="a"><!-- 향상된 for문 --> <!-- ar에 들어있는 것 중 하나를 꺼내서 a에 담고 다시 반복 -->
	${a}
</c:forEach>

<c:forEach items="${list}" var="li" varStatus="t"><!-- varStatus는 변수의 상태 인덱스를 사용하고 싶을때 사용한다.  -->
	<p>${li}</p>
	<h3>count : ${t.count}</h3>
	<h3>Index : ${t.index }</h3>
	<h3>First : ${t.first }</h3>
	<h3>Last  : ${t.last}</h3>
</c:forEach>
</body>
</html>