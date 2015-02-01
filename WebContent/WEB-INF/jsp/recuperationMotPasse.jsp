<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Fiche personne</title>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" />
    </head>
    <body>
		<div>
			<form method="post" action="<c:url value="/action/my/connexion"/>">
				<fieldset>
		        	<legend>Connexion</legend>
		        	
					<c:if test="${empty sessionScope.sessionPersonne}">
					
						<p>Saisissez l'adresse électronique associée à votre 
						compte, puis cliquez sur Continuer. Nous vous
						enverrons par email un nouveau mot de passe.</p>
					
						<!-- EMAIL -->
						<label for="emailPersonne">Adresse email </label>
						<input type="email" id="email" name="email" placeholder="Ex : name@mail.com" size="30" maxlength="60" />
						<span class="erreur">${form.erreurs['email']}</span>
						<br /><br />
						
						<!-- VALIDATION -->
						
						<input type="submit" value="Envoyer" />
						
						<p><br /><a href="<c:url value="/action/my/listePersonnes"/>">Retour</a></p>
		
		            </c:if>
				</fieldset>
			</form>
		</div>
	</body>
</html>