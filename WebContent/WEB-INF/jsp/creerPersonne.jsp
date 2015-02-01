<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Création d'un personne</title>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css" />
    </head>
    <body>
    	<c:import url="/WEB-INF/inc/connexion.jsp" />
    	<%-- Vérification de la présence d'un objet utilisateur en session --%>
    	<c:if test="${!empty sessionScope.sessionPersonne}">
	        <c:import url="/WEB-INF/inc/menu.jsp" />
	        <div>
	        	<fieldset>
	        	<legend>Informations personne</legend>
		            <form method="post" action="<c:url value="/action/my/creationPersonne"/>">
	                    <c:import url="/WEB-INF/inc/inc_personne_form.jsp" />
	                	<input type="submit" value="Valider"  />
	                	<input type="reset" value="Remettre à zéro" />  
		            </form>
		       		<p><br /><a href="<c:url value="/action/my/listePersonnes"/>">Retour</a></p>   
	            </fieldset>
	        </div>
        </c:if>
    </body>
</html>