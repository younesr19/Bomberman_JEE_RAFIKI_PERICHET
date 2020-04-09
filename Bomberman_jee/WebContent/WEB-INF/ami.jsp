<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<div class="list-group" style="margin-right:100px;">

		
		<div id="liste_ami" class="list-group" style="display:none;margin-right:50px;">
		<table style="cellspacing:10px;">
			<c:forEach items="${liste_ami}" var="ami">
			<tr>
			 <div class="d-flex w-100 justify-content-between">
			 	<c:if test="${ami.value == true}">
			 	 <td> <img src="${pageContext.request.contextPath}/icone/circle_green.png"></td>
			 	</c:if>
			 	<c:if test="${ami.value == false}">
			 	 <td> <img src="${pageContext.request.contextPath}/icone/circle_red.png"></td>
			 	</c:if>
			 	<td><a href="Profil?id=${ami.key.id}">${ami.key.pseudo}</a></td><td><em>&nbsp;&nbsp;Niveau ${ami.key.niveau}</em></td>
			</div>
			</tr>
			</c:forEach>
	  </table>
		</div>
</div>