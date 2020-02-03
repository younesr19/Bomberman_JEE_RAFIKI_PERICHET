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
		       <div class="sidebar-heading"><aa style="color:blue;">${joueur.pseudo}</aa>    #${joueur.niveau} 
		       <img style="width:150px;height:auto;border-radius:50%; border:solid blue 3px" src="${pageContext.request.contextPath}/avatar/${joueur.cheminAvatar}"/>
		       
		       </div>
		    </c:when>
		    <c:when test="${ joueur.sexe == 2 }">
		       <div class="sidebar-heading"><aa style="color:red;">${joueur.pseudo}</aa>    #${joueur.niveau}
		       <img style="width:150px;height:auto;border-radius:50%; border:solid red 3px;" src="${pageContext.request.contextPath}/avatar/${joueur.cheminAvatar}"/>
		       </div> 
		    </c:when>
		    <c:otherwise></c:otherwise>
	 </c:choose>
      <div class="list-group list-group-flush">
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('ami')">Ami</a>
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('historique')">Historique</a>
        <a href="#" class="list-group-item list-group-item-action bg-light" onclick="changeContent('tata')">Téléchargez le jeu !</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Classement</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Profil</a>
        <a href="#" class="list-group-item list-group-item-action bg-light">Status</a>
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
                <a class="dropdown-item" href="#">Modifier le profil</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>




      <div class="container-fluid content" id="accueil" style="display:none;">
        <h1 class="mt-4">Bienvenue ${joueur.pseudo} !</h1>
        <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>
        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p>
      </div>
      
      <div class="container-fluid content" id="ami" style="background-color: blue;display:none;">
        <h1 class="mt-4">Bienvenue ${joueur.pseudo} !</h1>
        <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>
        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p>
      </div>
      
      <div class="container-fluid content" id="historique" style=display:block;">
        <%@include file="historique.jsp" %>
      </div>
      
      <div class="container-fluid content" id="tata" style="background-color: purple;display:none;">
        <h1 class="mt-4">Bienvenue ${joueur.pseudo} !</h1>
        <p>The starting state of the menu will appear collapsed on smaller screens, and will appear non-collapsed on larger screens. When toggled using the button below, the menu will change.</p>
        <p>Make sure to keep all page content within the <code>#page-content-wrapper</code>. The top navbar is optional, and just for demonstration. Just create an element with the <code>#menu-toggle</code> ID which will toggle the menu when clicked.</p>
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
  </script>

</body>

</html>