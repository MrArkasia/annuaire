<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<form method="post" action="<c:url value="/action/my/connexion"/>">
		<fieldset>
        	<legend>Connexion</legend>
        	
			<c:if test="${empty sessionScope.sessionPersonne}">
			<!-- EMAIL -->
			<label for="emailPersonne">Adresse email </label>
			<input type="email" id="email" name="email" placeholder="Ex : name@mail.com" size="30" maxlength="60" />
			<span class="erreur">${form.erreurs['email']}</span>
			<br />
			
			<!-- MOT DE PASSE -->
			<label for="mpPersonne">Mot de passe </label>
			<input type="password" id="motdepasse" name="motdepasse"  size="30" maxlength="60" />
			<span class="erreur">${form.erreurs['motdepasse']}</span>
			
			<!-- VALIDATION -->
			<input type="submit" value="Connexion" />
			
			<p><a href="<c:url value="/action/my/recuperationMotPasse"/>">Mot de passe oublié ?</a></p>
			</c:if>
			
			<%-- Vérification de la présence d'un objet utilisateur en session --%>
            <c:if test="${!empty sessionScope.sessionPersonne}">
                <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionPersonne.email}</p>
                <p><a href="<c:url value="/action/my/deconnexion"/>">Deconnexion</a></p>
            </c:if>

		</fieldset>
	</form>
</div>