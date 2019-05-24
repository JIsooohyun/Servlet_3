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
      <li class="dropdown"><a href="${pageContext.request.contextPath}/qna/qnaList">QnA</a>
      </li>
      <li><a href="#">Page 2</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">

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