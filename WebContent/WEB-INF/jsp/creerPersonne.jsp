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
        <c:import url="/WEB-INF/inc/menu.jsp" />
        <div>
            <form method="post" action="<c:url value="/creationPersonne"/>" enctype="multipart/form-data">
                <fieldset>
                    <legend>Informations personne</legend>
                    <c:import url="/WEB-INF/inc/inc_personne_form.jsp" />
                </fieldset>  
                <p class="info">${ form.resultat }</p>
                <input type="submit" value="Valider"  />
                <input type="reset" value="Remettre à zéro" /> <br />
            </form>
        </div>
    </body>
</html>