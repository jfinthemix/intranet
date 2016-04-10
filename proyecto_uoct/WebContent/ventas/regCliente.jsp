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
	</head>
	
	<body>

		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Registrar Cliente de Ventas</h2>
						
						<div class="box boxpost">

				 			<form class="form-horizontal" action="ventasAction.do" method="POST" name="cliForm" id="cliForm">
 <input type="hidden" name="accion" value="RegClienteVta" />
				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" name="nom_cli" id="nom_cli" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputRut" class="col-sm-4 control-label">Rut</label>
    								<div class="col-sm-6 guionpost">
      								<input class="form-control" name="rut" type="text" size="8" maxlength="8" id="rut2" >
    								</div>
    								<div class="col-sm-2">
      								<input class="form-control" type="text" size="1" name="codRut" id="codRut" maxlength="1" >
    								</div>
    							</div>
				 				<div class="form-group">
    								<label for="inputTelefono" class="col-sm-4 control-label">Teléfono</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control"   name="fono_cli"  id="fono_cli" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEmail" class="col-sm-4 control-label">Email</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="email_cli" name="email_cli">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputGiro" class="col-sm-4 control-label">Giro</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="giro" name="giro" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputDireccion" class="col-sm-4 control-label">Dirección</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="dir_cli"  name="dir_cli">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputContacto" class="col-sm-4 control-label">Persona de contacto</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="contactos" name="contactos" cols="40" id="contactos" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtComentario" class="col-sm-4 control-label">Comentario</label>
    								<div class="col-sm-8">
      								<textarea class="form-control" id="comentario" name="comentario" cols="40" rows="4"></textarea>
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
											<input type="hidden" name="Submit" value="Guardar">
    									<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">

    										
      										<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> 
											Guardar
										</a>
										
										<script  type="text/javascript" >
		function submitThisForm1() {

			var formulario = $('#cliForm');
			
			var action = 'ventas/ventasAction.do'
			SubmitFormulario(action, formulario);
			

		}


	</script>
										
										
      								</div>
  									</div>
    							</div>
      								</div>
  									</div>
    							</div>
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
