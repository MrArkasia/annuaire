<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<label for="nomPersonne">Nom <span class="requis">*</span></label>
<input type="text" id="nomPersonne" name="nomPersonne" value="<c:out value="${personne.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomPersonne']}</span>
<br />

<label for="prenomPersonne">Prénom </label>
<input type="text" id="prenomPersonne" name="prenomPersonne" value="<c:out value="${personne.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomPersonne']}</span>
<br />

<label for="emailPersonne">Adresse email</label>
<input type="email" id="emailPersonne" name="emailPersonne" value="<c:out value="${personne.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['emailPersonne']}</span>
<br />

<label for="mpPersonne">Mot de passe <span class="requis">*</span></label>
<input type="text" id="mpPersonne" name="mpPersonne" value="<c:out value="${personne.mp}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['mpPersonne']}</span>
<br />

<label for="siteWebPersonne">Site WEB </label>
<input type="text" id="siteWebPersonne" name="siteWebPersonne" value="<c:out value="${personne.siteWeb}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['siteWebPersonne']}</span>
<br />

<label for="naissancePersonne">Date de naissance </label>
<input type="text" id="naissancePersonne" name="naissancePersonne" value="<c:out value="${personne.dateNaissance}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['naissancePersonne']}</span>
<br />