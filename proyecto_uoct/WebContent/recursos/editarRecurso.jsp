<!DOCTYPE html>
<%@page import="proyecto_uoct.reservas.VO.RecursoVO" %>
<%
RecursoVO rec=(RecursoVO) request.getAttribute("RECURSO");
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
		
		
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Editar Recurso:<br />
						<h2><small><%=rec.getNombre() %></small></h2>
						
				 		<div class="box boxpost">
				 			
				 			<form class="form-horizontal" action="recursosAction.do" name="form1" id="form1" method="POST" >
				 			 <input type="hidden" name="accion" value="ACTUALIZAR_RECURSO" />
       					 <input type="hidden" name="idRec" value="<%=rec.getIdRecurso() %>" />
				 				<div class="form-group">
    								<div class="col-sm-4">
    									<label class="control-label">Nombre del recurso</label>
    								</div>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="nombre" name="nombre" value="<%=rec.getNombre() %>">
    								</div>
    								</div>
    								<div class="form-group">
    					
    								<div class="col-sm-4">
      								<label class="control-label">Descripción:</label>
    								</div>
    								<div class="col-sm-8">
    									<textarea type="text" class="form-control inputFecha" id="desc" name="desc"><%= rec.getDescripcion()%></textarea>
    								</div>
    								</div>
    								
<div class="form-group">
    								<div class="col-sm-4">
      								<label class="control-label">Estado:</label>
    								</div>
    								<div class="col-sm-8">
    									<select name="idEstado" size="1">
                <%%>
                <option value="1" <%if (rec.getIsActivo()){out.print(" selected=\"selected\"");}%>>Activo</option>
                <option value="0" <%if (!rec.getIsActivo()){out.print(" selected=\"selected\"");}%>>Inactivo</option>
              </select>
    								</div>


    								
  								</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm1()" class="botoVerde busca">Guardar Cambios</a>
    										
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
		
		
		<script type="text/javascript">
		
function submitThisForm1() {
												
												var formulario = $('#form1');

												var action = 'recursos/recursosAction.do'
												SubmitFormulario(action,
														formulario);

											}		
		
		</script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
