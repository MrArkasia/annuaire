<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<%-- Vérification de la présence d'un objet utilisateur en session --%>
	<c:if test="${!empty sessionScope.sessionPersonne}">
		<fieldset>
	        <legend>Etat</legend>
	    	<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
	   	</fieldset>
   	</c:if>
</div>