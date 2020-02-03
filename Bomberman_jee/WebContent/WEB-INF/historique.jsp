<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  <h1 class="mt-4">Historique des parties</h1><br><br>

 <div class="list-group" style="margin-right:100px;">
	<c:forEach items="${ historique }" var="partie" varStatus="status">
	<c:if test="${partie.id_joueur == joueur.id}">
	<a href="#" class="list-group-item list-group-item-action flex-column align-items-start" onclick="changeContentHistorique(${partie.id_partie})">
	    <div class="d-flex w-100 justify-content-between">
	      <c:if test="${partie.agagne == true}" >
	      	<h3 ><vierge class=" mb-1 text-success">Victoire </vierge>#${partie.id_partie}</h3>
	      </c:if>
	      <c:if test="${partie.agagne == false}" >
	      	<h3 ><vierge class="mb-1 text-danger">Défaite </vierge>#${partie.id_partie}</h3>
	      </c:if>
	      
	      <strong><fmt:formatDate type = "date" dateStyle = "medium" value = "${partie.date_partie}" /></strong>
	    </div>
	    <p class="mb-1"><strong>Score : </strong><c:out value="${partie.score}"></c:out> points</p>
	    <p><strong>Type de partie : </strong>${partie.type_partie}</p>
	</a>
	<div id="${partie.id_partie}" class="content-histo list-group-item" style="display:none;margin-right:50px;">
		<c:set var="count" value="1"></c:set>
		<c:forEach items="${ historique }" var="partie_detail" varStatus="status_detail">
			<c:if test="${partie.id_partie == partie_detail.id_partie}">
				
				 <c:choose>
		   			 <c:when test="${count == 1 }">
						   <img src="${pageContext.request.contextPath}/icone/gold_medal.png">
						       
		   			 </c:when>
		  			  <c:when test="${count == 2 }">
			    		   <img src="${pageContext.request.contextPath}/icone/silver_medal.png">
		   			 </c:when>
		   			 <c:when test="${count == 3 }">
			    		   <img src="${pageContext.request.contextPath}/icone/bronze_medal.png">
			    	
		   			 </c:when>
				    <c:otherwise>${count}</c:otherwise>
	 			</c:choose>
				  <strong>${partie_detail.pseudo}</strong>  ${partie_detail.score}<br>
				<c:set var="count" value="${count+1 }"></c:set>
			</c:if>
		</c:forEach>
		<c:set var="count" value="1"></c:set>
		
	</div>
	</c:if>
	</c:forEach>
</div>

  <script>
    function changeContentHistorique(id) {
    	console.log(id);
    	//$(".content-histo").css("display", "none");

    	let test = $("#"+id).css("display");
		if(test=="block"){
	    	$("#"+id).css("display", "none");

		}
		else{
	    	$("#"+id).css("display", "block");

		}
    }
  </script>