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

    	<c:import url="/WEB-INF/inc/connexion.jsp" />
        <c:import url="/WEB-INF/inc/menu.jsp" />
        
        <fieldset>
        <legend>Fiche personne</legend>
        
        
        
	        <div id="corps">
	            <!--<p class="info">${ form.resultat }</p>-->
	            <p>Nom : <c:out value="${ personne.nom }"/></p>
	            <p>Prénom : <c:out value="${ personne.prenom }"/></p>
	            <p>Email : <c:out value="${ personne.email }"/></p>
	            <p>Site WEB : <c:out value="${ personne.siteWeb }"/></p>
	            <p>Date de naissance : <c:out value="${ personne.dateNaissance }"/></p>
	        </div>
        
        
        
        </fieldset>
        
    </body>
</html>