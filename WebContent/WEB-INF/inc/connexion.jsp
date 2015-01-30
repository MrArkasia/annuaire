<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<form method="post" action="<c:url value="/action/my/connexion"/>" enctype="multipart/form-data">
		<fieldset>
        	<legend>Connexion</legend>

			<!-- EMAIL -->
			<label for="emailPersonne">Adresse email </label>
			<input type="email" id="emailPersonne" name="emailPersonne" placeholder="Ex : name@mail.com" size="30" maxlength="60" />
			<br />
			
			<!-- MOT DE PASSE -->
			<label for="mpPersonne">Mot de passe </label>
			<input type="password" id="mpPersonne" name="mpPersonne"  size="30" maxlength="60" />
			
			<!-- VALIDATION -->
			<input type="submit" value="Valider"  />
			
			<p><a href="<c:url value="/action/mycreationPersonne"/>">J'ai oublié mon mot de passe</a></p>

		</fieldset>
	</form>
</div>