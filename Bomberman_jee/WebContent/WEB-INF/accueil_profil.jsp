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
  
    <!-- /#sidebar-wrapper -->
    
    

    <!-- Page Content -->
    <div id="page-content-wrapper">

      <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
              <a class="nav-link" href="Accueil" onclick="changeContent('accueil')"><img src="${pageContext.request.contextPath}/icone/home.png"/>Accueil <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
               <img src="${pageContext.request.contextPath}/icone/settings.png"/> Paramètres
              </a>
              <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="Connexion">Déconnexion</a>
                <a class="dropdown-item" href="#">Modifier le profil</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>




 
      
       <div class="container-fluid content" id="profil" style="display:block;">
       		<c:set var="joueurn" value="${joueur}" scope="request" />
		    <%@include file="profil.jsp" %>
		<c:if test="${estAmi==false}">
			<center><a href="Profil?id=${joueur.id}&ajoutAmi=1"><h4>Ajouter en ami ?</h4></a></center>
		</c:if>
      </div>
      <div class="container-fluid content" id="profil" style="display:block;">
       		<c:set var="historique" value="${historique}" scope="request" />
		    <%@include file="historique_profil.jsp" %>

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