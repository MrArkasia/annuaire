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
			<fieldset>
			<legend>Recuperation de mot de passe</legend>
				<form method="post" action="<c:url value="/action/my/recuperationMotPasse"/>">

					<c:if test="${empty sessionScope.sessionPersonne}">
					
						<p>Saisissez l'adresse électronique associée à votre 
						compte, puis cliquez sur Continuer. Nous vous
						enverrons par email un nouveau mot de passe.</p>
					
						<!-- EMAIL -->
						<label for="email">Adresse email </label>
						<input type="email" id="email" name="email" placeholder="Ex : name@mail.com" size="30" maxlength="60" />
						<span class="erreur">${form.erreurs['email']}</span>
						<br /><br />
						
						<!-- VALIDATION -->
						
						<input type="submit" value="Continuer" />
						
						
						
						<c:if test="${!empty sessionScope.notFindMail}">
							<div style="color:#FF0000">Nous sommes désolés. Nous n'avons pas pu vous identifier avec les informations fournies.</div>
						</c:if>
						
						<c:if test="${!empty sessionScope.recoveryId}">
							<div style="color:#33CC00">un email vient de vous être envoyé avec votre mot de passe.</div>
						</c:if>
						
						<p><br /><a href="<c:url value="/action/my/listePersonnes"/>">Retour</a></p>
		            </c:if>
				</form>
			</fieldset>
		</div>
	</body>
</html>