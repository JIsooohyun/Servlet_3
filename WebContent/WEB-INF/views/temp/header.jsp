<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="<%=application.getContextPath()%>/index.do">Home</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="<%=application.getContextPath() %>/notice/noticeList">Notice</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">Page 1-1</a></li>
          <li><a href="#">Page 1-2</a></li>
          <li><a href="#">Page 1-3</a></li>
        </ul>
      </li>
      <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <%-- <c:if test=""></c:if><!-- 꺼낸 애가 null이면 회원가입 보여주기 -->
    <c:choose><!-- switch case와 비슷한 걔념 -->
    	<c:when test=""><!-- if -->
    	</c:when>
    	<c:when test="">
    	</c:when>
    	<c:otherwise><!-- 나머지 전부 다 -->
    	</c:otherwise>
    </c:choose> --%>
    <c:if test="${sessionScope.memberDTO ne null}">
      <li><a href="<%=application.getContextPath()%>/member/memberCheck">My Page</a></li>
      <li><a href="<%=application.getContextPath()%>/member/memberLogin">Logout</a></li>
    </c:if>
    <c:if test="${sessionScope.memberDTO eq null}">
      <li><a href="<%=application.getContextPath()%>/member/memberCheck">Sign Up</a></li>
      <li><a href="<%=application.getContextPath()%>/member/memberLogin"> Login</a></li>
    </c:if>
    </ul>
  </div>
</nav>
<div class="container">
  <div class="jumbotron">
    <h1>Bootstrap Tutorial</h1>      
    <p>Bootstrap is the most popular HTML, CSS, and JS framework for developing responsive, mobile-first projects on the web.</p>
  </div>   
</div>