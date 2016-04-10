<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>

<html>
<head>
<title>Publicaci&oacute;n de reportajes</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
 <META HTTP-EQUIV="Cache-Control" CONTENT ="no-cache">.
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>

<body>

<h2>Actualizar reportaje</h2>
<div class="box boxpost">
<h4>Datos del reportaje</h4>

<form class="form-horizontal" action="/info_instit_y_report/reportAction.do">
<div class="form-group">
    								<label for="inputTitulo" class="col-sm-4 control-label">Título</label>
    								<div class="col-sm-8">
      								<input name="tit_rep" size="40" type="text" class="form-control" id="inputTitulo">
    								</div>
	</div>
	<div class="form-group">
    								<label for="inputPosicion" class="col-sm-4 control-label">Posición en portada</label>
    								<div class="col-sm-8">
      								<select name="tipo_rep" class="form-control" id="inputPosicion">
    										<option value="0" disabled="" selected="">Seleccionar</option>
    										<option value="1">Arriba</option>
    										<option value="2">Abajo</option>
    									</select>
    								</div>
  								</div>
  		<div class="form-group">
    								<label for="inputImagen" class="col-sm-4 control-label">Imagen</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><input name="foto" type="file" id="inputImagen"></p>
    								</div>
    							</div>	
    <div class="form-group">
    								<label for="inputContenido" class="col-sm-4 control-label">Contenido del reportaje</label>
    								<div class="col-sm-8">
      								<textarea name="desc_rep" class="form-control" rows="20" id="inputContenido"></textarea>
    								</div>
  								</div>
  									<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
      									<button type="submit" class="botoVerde">Publicar</button>
      								</div>
  									</div>
    							</div>					
</form>
</div>
 <div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
</div>
 

 <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("IngReportajeFBean");
frmvalidator.addValidation("tipo_rep","req","Debe ingresar la posición del reportaje");
frmvalidator.addValidation("tipo_rep","dontselect=0","Debe ingresar la posición del reportaje" );

frmvalidator.addValidation("tit_rep","req","Debe Ingresar titulo del reportaje");
frmvalidator.addValidation("tit_rep","maxlen=120","Titulo no puede superar los 120 caracteres");

frmvalidator.addValidation("foto","req","Debe Ingresar fotografía");

frmvalidator.addValidation("desc_rep","req","Debe Ingresar descripción del reportaje");
frmvalidator.addValidation("desc_rep","maxlen=4000","Descripción no puede superar los 4000 caracteres");

</script>


  
</body>
</html>
