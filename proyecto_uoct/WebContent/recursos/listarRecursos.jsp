<!DOCTYPE html>

<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%@ page import="proyecto_uoct.reservas.VO.*"%>
<%@ page import="java.util.*"%>

<%
List recursos = (List) request.getAttribute("RECURSOS");

%>

<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		
		 <script type="text/javascript">

    function goVerAgenda(id) {
      formulario.idRecurso.value = id;
      formulario.accion.value = "VER_AGENDA";
      formulario.submit();
    }
    
    
	function Llamadalink(hacia, link) {
		
		link = link.replace('#', '');

		link = 'recursos/recursosAction.do'+ '?accion=' + hacia + link ;
		
		LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
	}

    </script>
		
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Recursos compartidos</h2>
						
						
						
				 		<div class="box">



  <display:table id="recurs" name="RECURSOS" class="table table-striped table-bordered table-hover tablesorter" requestURI="recursosAction.do">
    <display:column title="Recurso" property="nombre" sortable="true" sortProperty="nombre">
    </display:column>

    <display:column title="Descripción" property="descripcion">
    </display:column>

    <display:column title="Agenda">
    <a href="#" class="botoGris botoMini" onclick="javascript:Llamadalink('VER_AGENDA','&idRecurso=<%= ((RecursoVO)recurs).getIdRecurso()%>')" ><span class="glyphicons glyphicons-calendar"></span> Ver agenda </a>
    </display:column>

  </display:table>



        				</div>
				 		
				 		
					
					</div>
			
					
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/jquery.tablesorter.js"></script> 
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
