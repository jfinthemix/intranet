
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>




<html>
<head>
<jsp:include page="/comunes/head.jsp" />

</head>
<body>
<jsp:include page="/comunes/cabeceraSesion.jsp" />
<div class="main">
			<div class="container">
				<div class="row clearfix">
				<jsp:include page="/comunes/menuSesion.jsp" />
					<div id="divContenido" class="col-sm-6 desarrollo">
						
					</div>
				<jsp:include page="/comunes/seccionDerechaSesion.jsp" />
		</div>
	</div>
</div>


<jsp:include page="/comunes/piePagina.jsp" />
</body>
</html>






<!-- 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<title>Unidad Operativa de Control de Tránsito</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-cache">
  <META HTTP-EQUIV="EXPIRES"
CONTENT="0">
  <link href="util/styla.css" rel="stylesheet" type="text/css">
</head>
<frameset rows="*" cols="1024,*" framespacing="0" frameborder="NO" border="0">
  	<frameset rows="105,*" frameborder="NO" border="0" framespacing="0">
    	<frame src="indexAction.do?hacia=arriba" name="topFrame" scrolling="NO" noresize>
		 <frameset rows="*" cols="195,*" framespacing="0" frameborder="NO" border="0">
	      	<frame src="indexAction.do?hacia=cargarMenu" name="leftFrame" scrolling="NO" noresize>
    		<frame src="homeAction.do" name="mainFrame">
		</frameset>
</frameset>
  <frame src="fondoDerecha.jsp" name="rightFrame" scrolling="NO" noresize >
</frameset>
<noframes><body>

</body></noframes>
</html>

 -->