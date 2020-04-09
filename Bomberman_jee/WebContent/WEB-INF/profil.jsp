<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<head>




</head>
<center><h1 class="mt-4 ">Profil de ${joueurn.pseudo}</h1></center><br><br>

<div class="container">
	<div id="user-profile-2" class="user-profile">
	  <div class="tabbable">
	    <div class="tab-content no-border padding-24">
	      <div id="home" class="tab-pane in active">
	        <div class="row">
	          <div class="col-xs-12 col-sm-3 center">
	            <span class="profile-picture">
	              <img class="editable img-responsive" style="width:150px;" alt=" Avatar" id="avatar2"
	                src="${pageContext.request.contextPath}/avatar/${joueurn.cheminAvatar}">
	            </span>
	
	
		            <div class="space space-4"></div>
					<c:if test="${joueur.pseudo != joueurn.pseudo}">
				
			            <a href="#" class="btn btn-sm btn-block btn-success">
			              <i class="ace-icon fa fa-plus-circle bigger-120"></i>
			              <span class="bigger-110">Ajouter dans la liste d'ami</span>
			            </a>
					</c:if>
		
		          </div><!-- /.col -->
	          <div class="col-xs-12 col-sm-9">
	            <h4 class="blue">
	              <span class="middle">${joueurn.pseudo}</span>
	
	            </h4>
	
	            <div class="profile-user-info">
	
	              <div class="profile-info-row">
	                <div class="profile-info-name"> Adresse mail </div>
	
	                <div class="profile-info-value">
	                  <i class="fa fa-map-marker light-orange bigger-110"></i>
	                  <span>${joueurn.email}</span>
	                </div>
	                
	                 <div class="profile-info-name"> Niveau </div>
	
	                <div class="profile-info-value">
	                  <i class="fa fa-map-marker light-orange bigger-110"></i>
	                  <span>${joueurn.niveau}</span>
	                </div>
	              </div>
	
	              <div class="profile-info-row">
	                <div class="profile-info-name"> Sexe </div>
	
	                <div class="profile-info-value">
	                
	                	<c:if test="${joueurn.sexe == 1}">
	                		  <span>Masculin</span>
	                	</c:if>
	                	
	                	<c:if test="${joueurn.sexe == 2}">
	                		  <span>Masculin</span>
	                	</c:if>
	                </div>
	                
	                <div class="profile-info-name"> Victoire/Défaite </div>
	
	                <div class="profile-info-value">
	                
	                		<span>${joueurn.nbr_victoire}/${joueurn.nbr_defaite} <fmt:formatNumber type="percent"  maxIntegerDigits="10" value="${joueurn.nbr_victoire/(joueurn.nbr_victoire+joueurn.nbr_defaite)}"/> </span>
	                </div>
	                
	              </div>
	
	              <div class="profile-info-row">
	                <div class="profile-info-name"> Création de compte </div>
	
	                <div class="profile-info-value">
	             	     <span>	     
	                	   <fmt:formatDate type = "date" dateStyle = "medium" value = "${joueurn.date_create}" />
					 	</span>
	                </div>
	                
	                <div class="profile-info-name"> Score max </div>
	
	                <div class="profile-info-value">
	             	     <span>	${joueurn.score_max}
					 	</span>
	                </div>
	              </div>
	
	              <div class="profile-info-row">
	                <div class="profile-info-name"> Dernière partie </div>
	
	                <div class="profile-info-value">
	                  <span>30/12/1998</span>
	                </div>
	                
	               	<div class="profile-info-name"> Classement général</div>
	
	                <div class="profile-info-value">
	                  <span>98984</span>
	                </div>
	              </div>
	            </div>
	
	            <div class="hr hr-8 dotted"></div>
	
	        
	          </div><!-- /.col -->
	          
	        </div><!-- /.row -->
	
	        <div class="space-20"></div>
	
	      </div><!-- /#home -->
	    </div>
	  </div>
	</div>

</div>


