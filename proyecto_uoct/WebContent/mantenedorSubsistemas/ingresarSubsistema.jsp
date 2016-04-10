<!DOCTYPE html>
<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.mantenedorSubsistemas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje               = (String) request.getAttribute("mensaje");
List lista_sistema           = (List) request.getAttribute("lista_sistema");

Integer var_sistema          = (Integer) request.getAttribute("sistema");
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
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Ingresar Subsistema</h2>
						
<%
        if (mensaje != null){
          out.print("<br>"+mensaje+"<br>");
        }						
%>						
						<div class="box boxpost">
				 			<h4>Datos del Subsistema</h4>
				 			<form id="form1" name="formMantenedorSubsistema" method="post" action="../mantenedorSubsistemas/subsistemaAction.do">
								<input type="hidden" name="id_subsistema" value="0">
                				<input type="hidden" name="hacer" value="0">
                					<input type="hidden" name="hacia" value="Grabar">
				 				<div class="form-group">
    								<label for="selectSistema" class="col-sm-4 control-label">Sistema asociado</label>
    								<div class="col-sm-8">
      <%
              Integer valorCero=new Integer(0);
              boolean bandera = true;
              Integer valorUno=new Integer(1);;//el id del primer sistema es igual a 1
              %>
                  <select name="id_sistema" size="1" id="id_sistema" class="form-control" >
                  <%
                  Iterator i = lista_sistema.iterator();
                  String nombre_sistema = "";
                  while (i.hasNext()) {
                    SubsistemaVO subsistema = (SubsistemaVO) i.next();
                    if(subsistema.getIdSistema().equals(valorUno)){
                      out.print("<option value=\"" + subsistema.getIdSistema()+ "\" selected>" + subsistema.getNombreSistema() + "</option>");
                    }
                    else{
                      out.print("<option value=\"" + subsistema.getIdSistema()+ "\">" + subsistema.getNombreSistema() + "</option>");
                    }

                    nombre_sistema= subsistema.getNombreSistema();
                  }
                  %>
              </select>

      								
    								</div>
    							</div>
				 				<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre del Subsist.</label>

    								<input name="nombre" type="text" value="" size="20" maxlength="20" class="form-control" id="inputNombre">
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
			
			var action = 'mantenedorSubsistemas/subsistemaAction.do'
			SubmitFormulario(action, formulario);
			

		}


	</script>
										
										
      								</div>
  									</div>
    							</div>
    							<script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("formMantenedorSubsistema");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre del nuevo Subsistema");
          </script>
  							</form>
						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
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
