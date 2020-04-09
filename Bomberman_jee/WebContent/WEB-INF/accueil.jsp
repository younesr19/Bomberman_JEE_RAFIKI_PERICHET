<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Accueil</title>

  <link href="${pageContext.request.contextPath}/css/stylesheet-profil.css" rel="stylesheet" type="text/css">

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/css/simple-sidebar.css" rel="stylesheet">
  
  
  

</head>

<body>

  <div class="d-flex" id="wrapper">

    <!-- Sidebar -->
    <div class="bg-light border-right" id="sidebar-wrapper">
      <c:choose>
		    <c:when test="${ joueur.sexe == 1 }">
		       <div class="sidebar-heading"><h4 class="blue">${joueur.pseudo}</h4>
		       <img style="width:150px;height:auto;border-radius:50%; border:solid blue 3px" src="${pageContext.request.contextPath}/avatar/${joueur.cheminAvatar}"/>
		       
		       </div>
		    </c:when>
		    <c:when test="${ joueur.sexe == 2 }">
		       <div class="sidebar-heading pink"><h4 class="pink">${joueur.pseudo}</h4> 
		       <img style="width:150px;height:auto;border-radius:50%; border:solid red 3px;" src="${pageContext.request.contextPath}/avatar/${joueur.cheminAvatar}"/>
		       </div> 
		    </c:when>
		    <c:otherwise></c:otherwise>
	 </c:choose>
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContentAmi('liste_ami')">Ami</a>
        <%@include file="ami.jsp" %>

        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('historique')">Historique</a>
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('tata')">Téléchargez le jeu !</a>
        <a href="#" class="list-group-item list-group-item-action bg-light"onclick="changeContent('classement')">Classement</a>
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('profil')">Profil</a>
      </div>
    </div>
    <!-- /#sidebar-wrapper -->
    
    

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
        <button class="btn btn-primary" id="menu-toggle">Menu</button>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="#" onclick="changeContent('accueil')"><img src="${pageContext.request.contextPath}/icone/home.png"/>Accueil <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               <img src="${pageContext.request.contextPath}/icone/settings.png"/> Paramètres
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="Connexion">Déconnexion</a>
                <a class="dropdown-item" href="Modifier">Modifier le profil</a>
                <div class="dropdown-divider"></div>
              </div>
            </li>
          </ul>
        </div>
      </nav>




      <div class="container-fluid content" id="accueil" style="display:block;">
        <h1 class="mt-4">Bienvenue ${joueur.pseudo} !</h1>
        <p>Bomberman (ボンバーマン, Bonbāman?) est une série de jeux vidéo de Hudson Soft où le joueur incarne un poseur de bombes, le but étant de faire exploser les adversaires/ennemis pour gagner. </p>
       <p> Le jeu a connu un grand succès, surtout grâce à son mode multijoueur qui, suivant les machines, permet de jouer jusqu'à une dizaine de personnes en même temps. </p>        
      </div>
      
      <div class="container-fluid content" id="ami" style="display:none;">
        <h1 class="mt-4">Bienvenue ${joueur.pseudo} !</h1>
        <c:forEach items="${liste_joueurco}" var="joueur_co">
        	
        	${joueur_co.key}  :  ${joueur_co.value} <br>
        
        </c:forEach>
      </div>
      
      <div class="container-fluid content" id="historique" style="display:none;">
        <%@include file="historique.jsp" %>
      </div>
      
      <div class="container-fluid content" id="tata" style="display:none;">
        <h1 class="mt-4">Là où le jeu peut être téléchargé</h1>
      </div>
      
      
       <div class="container-fluid content" id="profil" style="display:none;">
       		<c:set var="joueurn" value="${joueur}" scope="request" />
		    <%@include file="profil.jsp" %>

      </div>
      
        <div class="container-fluid content" id="classement" style="display:none;">
		    <%@include file="classement.jsp" %>

     </div>
      
      
      
    </div>
    <!-- /#page-content-wrapper -->

  </div>
  <!-- /#wrapper -->

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Menu Toggle Script -->
  <script>
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
    
    function changeContent(id) {
    	console.log(id);
    	$(".content").css("display", "none");

    	$("#"+id).css("display", "block");
    	
    }
    function changeContentAmi(id) {
    	let test = $("#"+id).css("display");
		if(test=="block"){
	    	$("#"+id).css("display", "none");

		}
		else{
	    	$("#"+id).css("display", "block");

		}
    	
    }
  </script>

</body>

</html>