<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
        <link type="text/css" rel="stylesheet" href="css/stylesheet.css" />
    </head>
    <body>
    	<div name="formulaire" id="outer">
    		<div name="formulaire" id="inner">
		        <form method="post" action="Inscription" enctype="multipart/form-data">
		            <fieldset>
		                <legend>Inscription au jeu du Bomberman</legend>
		                <p>Vous pouvez vous inscrire via ce formulaire.</p>
		
		                <label for="email">Adresse email <span class="requis">*</span></label>
		                <input type="email" id="email" name="email" value="" size="20" maxlength="60" required />
		               	<span class="erreur">${forms.erreurs['email']}</span>
		                
		                <br />
		
				        <label for="pseudo">Pseudo <span class="requis">*</span></label>
		                <input type="text" id="pseudo" name="pseudo" value="" size="20" maxlength="60" required />
		                <br />
		
		
		                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
		                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required/>
		                <span class="erreur">${forms.erreurs['motdepasse']}</span>
		                <br />
		                
		                <label for="confmotdepasse">Confirmer le mot de passe <span class="requis">*</span></label>
		                <input type="password" id="confmotdepasse" name="confmotdepasse" value="" size="20" maxlength="20" required/>
		                <span class="erreur">${forms.erreurs['motdepasse']}</span>
		                <br />
		                
		               	<label for="sexe">Sexe <span class="requis">*</span></label>
		                Homme<input type="radio" id="sexe" name="sexe" value="1" required />
		              	Femme<input type="radio" id="sexe" name="sexe" value="2" required />
		                
		                <br />
		                		               
		               	<label for="avatar">Avatar <span class="requis">*</span></label>
		                <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg">
		                <br />
		              
		                <input type="submit" value="Inscription" class="sansLabel" />
		                <br />
		                
		                <p class="${empty forms.erreurs ? 'succes' : 'erreur'}">${forms.resultat}</p>
		            </fieldset>
		        </form>
	        <div name="formulaire" id="inner">
        </div>
    </body>
    
    
</html>