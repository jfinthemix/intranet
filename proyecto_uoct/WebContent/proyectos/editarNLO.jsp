<!DOCTYPE html>
<%@page import="proyecto_uoct.proyecto.VO.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
DetalleProyectoVO proy=(DetalleProyectoVO)request.getAttribute("detalleProy");
NLOdeListaVO nlo= (NLOdeListaVO)request.getAttribute("detnlo");
DetalleOTVO ot=(DetalleOTVO)request.getAttribute("ot");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
%>
<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
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
	
	<script language="JavaScript" type="text/javascript">

function confirmarEliminacion(f){
  borrar = window.confirm('Al eliminar esta NLO no quedará ningún registro de ella, ni en el modulo de Iniciativas de Inversión, ni en el de Documentación: ¿Está seguro que desea continuar?');
  (borrar)?f.submit():'return false';
}

<script type="text/javascript">
function submitThisForm1() {
	
	var formulario = $('#form1');

	var action = 'proyectos/proyectoAction.do'
	SubmitFormulario(action, formulario);

}
</script>


</script>
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Editar NLO</h2>
						
						<div class="box boxpost">
				 			<h4>Datos de la NLO</h4>
				 			<form class="form-horizontal" action="proyectoAction.do" name="form1" id="form1" method="POST" >
				 			 <input type="hidden"  name="hacia" value="actualizarNLO"/>
        <input type="hidden" name="idNLO" value="<%=nlo.getId()%>" />
				 				<div class="form-group">
    								<label class="col-sm-4 control-label">Iniciativa asociada</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%=proy.getNomProy() %></p>
    								</div>
    							</div>
    							 <%if(ot!=null){ %>
    							<div class="form-group">
    								<label class="col-sm-4 control-label">OT asociada </label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><%=ot.getNomOT() %></p>
      							</div>
    							</div>
    							         <%} %>
    							<div class="form-group">
    								<label for="inputFechaNLO" class="col-sm-4 control-label">Fecha de la NLO*</label>
    								<div class="col-sm-8">
      								<input class="form-control inputFecha" id="inputFechaNLO" type="text" value="<%=sdf.format(nlo.getFechaNLO()) %>" >
      								<span id="helpBlock" class="help-block">Se registra como fecha de la NLO y del Documento.</span>
      							</div>
    							</div>
    							<div class="form-group">
    								<label for="txtDesc" class="col-sm-4 control-label">Descripción de la NLO*</label>
    								<div class="col-sm-8">
    									<textarea class="form-control" id="txtDesc" rows="4" name="id"><%=nlo.getStr() %></textarea>
    								</div>
    							</div>
    							
    							
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" class="botoVerde" onclick="javascript:submitThisForm1()">Guardar los cambios</a>
      								
    										<a href="javascript:void(0)" class="botoVerde">Reestablecer</a>
    										
      								<form id="borrarNLO" action="proyectoAction.do" name="borrarNLO" method="POST">

    <input type="hidden" name="hacia" value="eliminarNLO">
    <input type="hidden" name="eliminar" value="eliminarNLO">
  <input type="hidden" name="idNLO" value="<%=nlo.getId()%>">
   <a href="javascript:void(0)" class="botoVerde" onclick="javascript:confirmarEliminacion(this.form);">Eliminar NLO </a>
</form>
      								</div>
  									</div>
    							</div>
  							</form>
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
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
