<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<jsp:include page="/comunes/head.jsp" />

</head>
<body>
<jsp:include page="/comunes/cabecera.jsp" />
<div class="main">
			<div class="container">
				<div class="row clearfix">
				<jsp:include page="/comunes/menu.jsp" />
					<div id="divContenido" class="col-sm-6 desarrollo">
						
					</div>
				<jsp:include page="/comunes/seccionDerecha.jsp" />
		</div>
	</div>
</div>


<jsp:include page="/comunes/piePagina.jsp" />
</body>
</html>