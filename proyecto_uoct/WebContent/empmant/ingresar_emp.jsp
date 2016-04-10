<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String) request.getAttribute("mensaje");
%>


<html lang="es">
<head>

	
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
		<link rel="icon" href="img/favicon.ico"><title>Unidad Operativa de Control de Tránsito</title>
		
		
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet"><!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.form_nueva_empmant.submit();
}
</script>		
		
		
		
		</head><body><br>
<div class="main">
			<div class="container">
				<div class="row clearfix">
				

				
					<div class="col-sm-6 desarrollo">
					
						<h2>Ingresar Empresa Mantenedora</h2>
						
 <%
        if (mensaje != null)
        out.print(mensaje);
        %>						
						
						<div class="box boxpost">
				 			<h4>Datos de la Empresa</h4>
				 			<form id="form1" class="form-horizontal"name="form_nueva_empmant" method="post" action="../empmant/empMantAction.do" >
				 						<input type="hidden" name="hacia" value="Grabar">
				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre</label>
    								<div class="col-sm-8">
      								<input class="form-control" id="inputNombre" name="nombre" type="text" value="" maxlength="50">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputDire" class="col-sm-4 control-label">Dirección</label>
    								<div class="col-sm-8">
      								<input class="form-control" id="inputDire" type="text" name="direccion" maxlength="50">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFono" class="col-sm-4 control-label">Teléfono</label>
    								<div class="col-sm-8">
      								<input class="form-control" id="inputFono" type="text" name="telefono" maxlength="20" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="selectVigencia" class="col-sm-4 control-label">Vigencia</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectVigencia" name="vigente">
      									<option selected="selected" disabled="disabled">Seleccionar</option>
      									<option selected value="1">Vigente</option>
                  <option value="0">No vigente</option>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtTerreno" class="col-sm-4 control-label">Mail terreno</label>
    								<div class="col-sm-8">
      								<textarea class="form-control" id="txtTerreno" rows="4" name="mail_terreno" maxlength="50"></textarea>
      								<p class="help-block">Separar por "," si es más de un mail.</p>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtSala" class="col-sm-4 control-label">Mail sala</label>
    								<div class="col-sm-8">
      								<textarea class="form-control" id="txtTerreno" rows="4" name="mail_sala" maxlength="50"></textarea>
      								<p class="help-block">Separar por "," si es más de un mail.</p>
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    											<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> 
											Guardar
										</a>
										
										<script  type="text/javascript" >
		function submitThisForm1() {

			var formulario = $('#form1');
			
			var action = 'empmant/empMantAction.do'
			SubmitFormulario(action, formulario);
			

		}

		function Llamadalink(hacia, link) {
			link = link.replace('#', '');
			link = 'documentacion/clienteAction.do' + link + '&hacia=' + hacia;
			//alert(link+' ' +param);
			LlamadaPagina(link);

			//clienteAction.do?hacia=detalleEntExt
		}
	</script>
      								</div>
  									</div>
    							</div>
    							
    							<script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("form_nueva_empmant");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre");
            frmvalidator.addValidation("telefono","req","Debe ingresar el telefono");
            frmvalidator.addValidation("direccion","req","Debe ingresar la direccion");
            frmvalidator.addValidation("mail_terreno","req","Debe ingresar el o los mails que correspondan a mantenciones en terreno.");
            frmvalidator.addValidation("mail_sala","req","Debe ingresar el o los mails que correspondan a mantenciones en terreno.");
          </script>
  							</form>
						</div>
						
						
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div><br>
</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				</footer><div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito <span id="pie"></span></p>
					</div>
				</div>
        	
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body></html>