<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@page import="java.util.List,java.util.LinkedList,java.util.Iterator"  %>
<%@page import="proyecto_uoct.common.VO.ModuloVO,proyecto_uoct.common.VO.FuncionalidadVO"  %>
<%
List funs=(List)request.getAttribute("funs");
List mods=(List)request.getAttribute("mods");
String sitemap="";
Iterator im=mods.iterator();
int cont_smenu=0;
while (im.hasNext()){                  // PARA CADA MODULO
ModuloVO m= (ModuloVO)im.next();

Iterator ifun=funs.iterator();

List temp=new LinkedList();              //CREA UNA LISTA TEMPORAL
int cont=0;

// PARA CADA FUNCIÓN DEL PERFIL DEL USUARIO, SI PERTENECE AL MODULO, LA GUARDA EN LA LISTA TEMPORAL CONTANDO CUANTAS VAN

while (ifun.hasNext()){
  FuncionalidadVO f=(FuncionalidadVO)ifun.next();
  if(f.getIdModulo().equals(m.getIdModulo())){
    temp.add(f);
    cont++;
  }
}

// SI EN LA LISTA TEMPORAL HAY FUNCIONALIDADES IMPRIME EL MODULO Y LAS FUNCIONALIDADES

if(cont!=0){
  cont_smenu++;
   sitemap=sitemap+
   "<dt onclick=\"javascript:montre2('s_menu"+cont_smenu+"');\">"+m.getNombreModulo()+
   "</dt> <dd id=\"s_menu"+cont_smenu+"\"><ul>";
  Iterator itemp=temp.iterator();
  while(itemp.hasNext()){
    FuncionalidadVO ftemp=(FuncionalidadVO)itemp.next();

    if(ftemp.getPopup().intValue()==0){
      sitemap=sitemap+ "<li><a href=\""+ftemp.getURLFun()+"\" Target=\"mainFrame\">-"+ftemp.getNombreFun()+"</a></li>";
    }else{
    sitemap=sitemap+ "<li><a href=\"#\" onClick=popUp('"+ftemp.getURLFun()+"',this.target,'"+ftemp.getParams_popup()+"')>-"+ftemp.getNombreFun()+"</a></li>";
    }

    }
  }
  sitemap = sitemap+"</ul> </dd>";

}

//sitemap=sitemap+ " <dt><a href=\"indexAction.do?hacia=signout\" target=\"_parent\"><font color=\"#ffffff\">-Cerrar Sesión</font></a></dt> "+
 //  " </ul> ";

 sitemap=sitemap+ "</ul>";
%>
<head>
<title>menu vertical</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script type="text/javascript">
<!--
window.onload=montre;

function montre(id) {
var d = document.getElementById(id);
	for (var i = 1; i<=<%=cont_smenu%>; i++) {
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
top: 0;
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
background: #3C7A93; /*fondo del menu principal*/
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
background: #eee;
}

#mentions {
font-family: verdana, arial, sans-serif;
position: absolute;
bottom : 200px;
left : 10px;
color: #000;
background-color: #ddd;
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

<dt onclick="javascript:montre();"><a href="homeAction.do" target="mainFrame"><font color="#ffffff">PORTADA</font></a></dt>

<%=sitemap %>

</dl>
</body>
</html>
