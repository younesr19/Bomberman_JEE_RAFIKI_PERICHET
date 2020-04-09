<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
  <center><h1 class="mt-4">Historique</h1></center><br><br>

 <div class="list-group" style="margin-right:380px;margin-left:390px;">
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