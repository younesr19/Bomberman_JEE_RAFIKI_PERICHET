<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="stylesheet.css" />
    </head>
    <body>
    	<div name="formulaire" id="outer">
    		<div name="formulaire" id="inner">
		        <form method="post" action="Formulaire">
		            <fieldset>
		                <legend>Connexion au jeu du Bomberman</legend>
		                <p>Vous pouvez vous connecter via ce formulaire.</p>
		
		                <label for="email">Adresse email <span class="requis">*</span></label>
		                <input type="email" id="email" name="email" value="" size="20" maxlength="60" required />
		                <br />
		
		                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
		                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" required/>
		                <span class="erreur">${forms.erreurs['motdepasse']}</span>
		                <br />
		                
		                <input type="submit" value="Connexion" class="sansLabel" />
		                <br />
		                
		                <p class="${empty forms.erreurs ? 'succes' : 'erreur'}">${forms.resultat}</p>
		            </fieldset>
		        </form>
	        <div name="formulaire" id="inner">
        </div>
    </body>
    
    
</html>