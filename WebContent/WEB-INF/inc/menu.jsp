<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>

	<fieldset>
        <legend>Menu</legend>
    	<p><a href="<c:url value="/action/my/creationPersonne"/>">Ajouter une personne</a></p>
    	<p><a href="<c:url value="/action/my/listePersonnes"/>">Annuaire</a></p>
   	</fieldset>
   	
</div>