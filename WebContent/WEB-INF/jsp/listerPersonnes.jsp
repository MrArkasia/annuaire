<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des personnes existants</title>
        <link type="text/css" rel="stylesheet" href="../../inc/style.css" />
    </head>
    <body>
        <c:import url="/WEB-INF/inc/menu.jsp" />
        <div id="corps">
        <c:choose>
            <%-- Si aucun personne n'existe en session, affichage d'un message par défaut. --%>
            <c:when test="${ empty sessionScope.personnes }">
                <p class="erreur">Aucun personne enregistré.</p>
            </c:when>
            <%-- Sinon, affichage du tableau. --%>
            <c:otherwise>
            <table>
                <tr>
                    <th>Nom</th>
                    <th>Prénom</th>
                    <th>Email</th>
                    <th>Site WEB</th>
                    <th>Date de naissance</th>
                    <th class="action">Modifier</th>
                    <th class="action">Supprimer</th>                    
                </tr>
                <%-- Parcours de la Map des personnes en session, et utilisation de l'objet varStatus. --%>
                <c:forEach items="${ sessionScope.personnes }" var="mapPersonnes" varStatus="boucle">
                <%-- Simple test de parité sur l'index de parcours, pour alterner la couleur de fond de chaque ligne du tableau. --%>
                <tr class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
                    <%-- Affichage des propriétés du bean Personne, qui est stocké en tant que valeur de l'entrée courante de la map --%>
                    <td><c:out value="${ mapPersonnes.value.nom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.prenom }"/></td>
                    <td><c:out value="${ mapPersonnes.value.email }"/></td>
                    <td><c:out value="${ mapPersonnes.value.siteWeb }"/></td>
                    <td><c:out value="${ mapPersonnes.value.dateNaissance }"/></td>
                    <%-- Lien vers la servlet de modification, avec passage du nom du personne - c'est-à-dire la clé de la Map - en paramètre grâce à la balise <c:param/>. --%>
                    <td class="action">
                        <a href="<c:url value="/modifierPersonne"><c:param name="idPersonne" value="${ mapPersonnes.key }" /></c:url>">
                            <img src="<c:url value="/WEB-INF/inc/modifier.png"/>" alt="Modifier" />
                        </a>
                    </td>
                    <td class="action">
                        <a href="<c:url value="/suppressionPersonne"><c:param name="idPersonne" value="${ mapPersonnes.key }" /></c:url>">
                            <img src="<c:url value="/WEB-INF/inc/supprimer.png"/>" alt="Supprimer" />
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </c:otherwise>
        </c:choose>
        </div>
    </body>
</html>