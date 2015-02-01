<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8" />
        <title>Liste des personnes existants</title>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
    </head>

<body>
<div class="navbar navbar-fixed-top">
	<div class = "navbar-inner">
		<div class = "container">
			<a class = "brand" href = "accueil.jsp"> Home </a>		
			<ul class="nav">
			<li class="divider-vertical"></li>
			<li><a href = "Site?page=connexion"> Admin </a></li>	
			<li class="divider-vertical"></li>
			</ul>
			<ul class="nav pull-right">
			<li class="divider-vertical"></li>
			<li><a href = "Site?page=connexion"> Se connecter </a></li>
			</ul>
		</div>
	</div>
</div>

<div class="container">

	<div class="hero-unit">
		<h1> Gestion d'un annuaire </h1>
		
		<p> Bienvenue sur notre Annuaire </p>
	</div>
</div>	

<div class="container">
	<hr>
	<footer>
        <p>&copy; Master Professionnel ISL</p>
    </footer>
</div>

<form>
<input type="hidden" name="page" value="accueil"/>
</form>
</body>
</html>