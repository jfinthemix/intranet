<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.mantenedorSistemas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje      = (String) request.getAttribute("mensaje");
Integer id_sistema  = (Integer) request.getAttribute("id_sistema");
String nombre       = (String) request.getAttribute("nombre");
%>


<!DOCTYPE html>
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
		
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
		
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.formMantenedorSistema.submit();
}
</script>		
		
	</head>
	
	<body onload="valida_cambioInicial();">
		<div class="preheader headerlog">
			<div class="container">
				<div class="row">
					<div class="col-sm-3 col-xs-3">
						<a href="index_logeado.html">
							<h1>UOCT | <span>Intranet</span></h1>
						</a>
					</div>

				</div>	
			</div>
		</div>
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Editar Sistema</h2>
						
						<div class="box boxpost">
				 			<h4>Datos del Sistema</h4>
				 			<form class="form-horizontal" name="formMantenedorSistema" method="post" action="../mantenedorSistemas/sistemaAction.do" >
				 			<%
        if (mensaje != null)
        out.print(mensaje);
        %>
				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre</label>
    								<div class="col-sm-8">
      								<input class="form-control" id="inputNombre"  name="nombre" type="text" value="<% out.print(nombre); %>" size="60" maxlength="20">
    								</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<button type="reset" class="botoGris"><span class="glyphicons glyphicons-undo"></span> Reestablecer valores</button>
    										<button type="submit" class="botoVerde" name="hacia" value="Grabar" ><span class="glyphicons glyphicons-disk-save"></span> Guardar</button>
<input type="hidden" name="id_sistema" value="<% out.print(id_sistema);%>">      							
      								</div>
  									</div>
    							</div>
    							
  <script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("formMantenedorSistema");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre del Sistema");
          </script>    							
    							
  							</form>
						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito, <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
    <script src="js/uoct_falla1.js"></script>
  </body>
</html>
