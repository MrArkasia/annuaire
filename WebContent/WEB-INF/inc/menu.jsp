<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
	<%-- Vérification de la présence d'un objet utilisateur en session --%>
	<c:if test="${!empty sessionScope.sessionPersonne}">
		<fieldset>
	        <legend>Menu</legend>	
	    	<p><a href="<c:url value="/action/my/listePersonnes"/>">Annuaire</a></p>
	    	<p><a href="<c:url value="/action/my/modifierPersonne"><c:param name="idPersonne" value="${sessionScope.sessionId}" /></c:url>">Modifier ma fiche</a></p>
	    	<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionAdmin}">
	    		<p><a href="<c:url value="/action/my/creationPersonne"/>">Ajouter une personne</a></p>
	    	</c:if>
	   	</fieldset>
   	</c:if>
</div>