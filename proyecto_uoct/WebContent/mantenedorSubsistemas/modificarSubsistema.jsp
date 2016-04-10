<!DOCTYPE html>

<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.mantenedorSubsistemas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje           = (String) request.getAttribute("mensaje");
LinkedList listaSistema  = new LinkedList();
listaSistema             = (LinkedList) request.getAttribute("listaSistema");

Integer id_subsistema    = (Integer) request.getAttribute("id_subsistema");
String nombre            = (String) request.getAttribute("nombre");
Integer id_sistema       = (Integer) request.getAttribute("id_sistema");
String nombre_sistema    = (String) request.getAttribute("nombre_sistema");
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
		
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.formMantenedorSubsistema.submit();
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

					</div>
				
					<div class="col-sm-6 desarrollo">
					   <%
        if (mensaje != null){
          out.print("<br>"+mensaje+"<br>");
        }
        %>
						<h2>Editar Subsistema</h2>
						
						<div class="box boxpost">
				 			<h4>Datos del Subsistema</h4>
				 			<form class="form-horizontal" name="formMantenedorSubsistema" method="post" action="../mantenedorSubsistemas/subsistemaAction.do">
				 				<div class="form-group">
    								<label for="selectSistema" class="col-sm-4 control-label">Sistema asociado</label>
    								<div class="col-sm-8">
      								    <select name="id_sistema">
               <%
                Iterator i = listaSistema.iterator();
                while (i.hasNext()) {
                  SubsistemaVO sistema = (SubsistemaVO) i.next();
                  if(sistema.getIdSistema().equals(id_sistema)){
                    out.print("<option value=\"" + sistema.getIdSistema()+ "\" selected>" + sistema.getNombreSistema() + "</option>");
                   }
                  else{
                    out.print("<option value=\"" + sistema.getIdSistema()+ "\">" + sistema.getNombreSistema() + "</option>");
                  }
                }
                %>
              </select>
    								</div>
    							</div>
				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre del Subsistema</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputNombre" value="Amplificadores">
    								</div>
    								
    								
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<button type="reset" class="botoGris"><span class="glyphicons glyphicons-undo"></span> Reestablecer valores</button>
    										<button type="submit" class="botoVerde" value="Grabar"><span class="glyphicons glyphicons-disk-save"></span> Guardar</button>
      								</div>
  									</div>
    							</div>

 <input type="hidden" name="id_subsistema" value="<% out.print(id_subsistema);%>">
                <input type="hidden" name="hacer" value="-1">    							
    							
    							
 <script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("formMantenedorSubsistema");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre del Subsistema");
          </script>    							
    							
  							</form>
						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div>
			
					<div class="col-sm-3 sidebar">
					
	
			
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
