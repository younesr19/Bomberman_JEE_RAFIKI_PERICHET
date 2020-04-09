<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  <h1 class="mt-4">Classement global</h1><br><br>

 <div class="list-group-item content-histo" style="margin-right:380px;margin-left:390px;">
 	<c:set var="count" value="1"></c:set> 
	<c:forEach items="${ classement }" var="classement" varStatus="status">
	<a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
	    <div class="d-flex w-100 justify-content-between">
	   	<h3>${count} - ${classement.pseudo}</h3> 
	     <img style="width:100px;height:auto;" src="${pageContext.request.contextPath}/avatar/${classement.cheminAvatar}"/>
	    </div>
	    <h4><strong>Score total  : </strong><c:out value="${classement.score_max}"></c:out> points</h4>
	</a>
	<c:if test="${classement.score_max != 0 }">
		<c:set var="count" value="${count+1 }"></c:set>
	</c:if>
	</c:forEach>
</div>

