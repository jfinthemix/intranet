<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">




<head>
<title>menu vertical</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript">
<!--
window.onload=montre;

function montre(id) {
var d = document.getElementById(id);
	for (var i = 1; i<=11; i++) {
	if (document.getElementById('s_menu'+i)) {document.getElementById('s_menu'+i).style.display='none';}
	}
if (d) {d.style.display='block';}
}


function montre2(id){
var d =document.getElementById(id);

	if(d.style.display=="none")
	{ d.style.display="";
	}
	else
		{d.style.display="none";
	}
}



function popUp(href, target, features) {
  res = window.open(href, target, features);
  res.window.focus();
}


//-->
</script>
<style type="text/css">
<!--

/* CSS issu des tutoriels http://css.alsacreations.com */
body {
margin: 0;
padding: 0;
background:#ffffff;
/*background-image: url(util/img/abajo.jpg);*/
font: 80% verdana, arial, sans-serif;
}
dl, dt, dd, ul, li {
margin: 0;
padding: 0;
list-style-type: none;
}
#menu {
position: absolute;
top: 50;
left: 0;
}
dl#menu {
width: 15em;
}
dl#menu dt {
cursor: pointer;
margin: 2px 0;;
height: 20px;
line-height: 20px;
text-align: center;
font-weight: bold;
border: 1px solid gray;
background: #3C7A93;
	color: #ffffff;
}
dl#menu dd {
border: 1px solid gray;
}
dl#menu li {
text-align: left;
background: #99BEDD; /* fondo del submenu*/
}
dl#menu li a, dl#menu dt a {
color: #000;
text-decoration: none;
display: block;
border: 0 none;
height: 100%;
}

dl#menu li a:hover, dl#menu dt a:hover {
background: #99B0BE; /* submenu mouseover */
}

#mentions {
font-family: verdana, arial, sans-serif;
position: absolute;
bottom : 200px;
left : 10px;
color: #000;
background-color: #99B0BE;
}
#mentions a {text-decoration: none;
color: #222;
}
#mentions a:hover{text-decoration: underline;
}

-->
</style>
</head>

<body>




<dl id="menu">

<div align="center"><font style="verdana" size="3" color="#42939F"><strong> MENU</strong> </font></div>
<dt onclick="javascript:montre();"><a href="index2Action.do" target="_top"><font color="#ffffff">PORTADA</font></a></dt>
<dt onclick="javascript:montre2('s_menu1');">UOCTINOS</dt>
<dd id="s_menu1"><ul><li><a href="index2Action.do?accion=cumpleanos" Target="mainFrame">-Cumpleaños del personal</a></li>
<li><a href="index2Action.do?accion=cuadroanexos" Target="mainFrame">-Anexos telefónicos</a></li></ul></dd>

<dt onclick="javascript:montre2('s_menu7');">CONTENIDO</dt>
<dd id="s_menu7"><ul><li><a href="index2Action.do?accion=infoinstit" Target="mainFrame">-Documentos UOCT</a></li></ul> </dd>

<dt onclick="javascript:montre2('s_menu11');">UTILITARIOS</dt>
<dd id="s_menu11"><ul><li><a href="#" onClick=popUp('http://inti.uoct.cl:8080/intranet/pages/calculadora/calculo.html',this.target,'width=430,height=300')>-Calculadora de verdes</a></li>
<li><a href="#" onClick=popUp('http://inti.uoct.cl:8080/intranet/pages/noticias.jsp',this.target,'width=1015,height=355')>-Noticias CNT</a></li></ul> </dd>
</dl>
</body>
</html>
