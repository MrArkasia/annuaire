<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- EMAIL -->
<label for="emailPersonne">Adresse email <span class="requis">*</span></label>
<input type="email" id="emailPersonne" name="emailPersonne" value="<c:out value="${personne.email}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['emailPersonne']}</span>
<br />

<!-- MOT DE PASSE -->
<label for="mpPersonne">Mot de passe <span class="requis">*</span></label>
<input type="password" id="mpPersonne" name="mpPersonne" value="<c:out value="${personne.mp}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['mpPersonne']}</span>
<br />

<!-- NOM -->
<label for="nomPersonne">Nom </label>
<input type="text" id="nomPersonne" name="nomPersonne" value="<c:out value="${personne.nom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['nomPersonne']}</span>
<br />

<!-- PRENOM -->
<label for="prenomPersonne">Prénom </label>
<input type="text" id="prenomPersonne" name="prenomPersonne" value="<c:out value="${personne.prenom}"/>" size="30" maxlength="30" />
<span class="erreur">${form.erreurs['prenomPersonne']}</span>
<br />

<!-- SITE WEB -->
<label for="siteWebPersonne">Site WEB </label>
<input type="text" id="siteWebPersonne" name="siteWebPersonne" value="<c:out value="${personne.siteWeb}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['siteWebPersonne']}</span>
<br />

<!-- DATE DE NAISSANCE -->
<label for="naissancePersonne">Date de naissance </label>
<input type="text" id="naissancePersonne" name="naissancePersonne" value="<c:out value="${personne.dateNaissance}"/>" size="30" maxlength="60" />
<span class="erreur">${form.erreurs['naissancePersonne']}</span>
<br />

<!-- GROUPE -->
<label for="groupe">Groupe </label>
<select name="groupe" id="groupe">
	<option value="null">Choisir un groupe</option>
	<option value="Groupe 1">Groupe 1</option>
	<option value="Groupe 2">Groupe 2</option>
	<option value="Groupe 3">Groupe 3</option>
	<option value="Groupe 4">Groupe 4</option>
</select>
<span class="erreur">${form.erreurs['groupe']}</span>
<br />

<br />