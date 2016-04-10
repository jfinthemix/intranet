<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, java.util.List, java.util.Iterator,proyecto_uoct.common.VO.IdStrVO" errorPage="" %>

<html>
<head>
<title>Registrar Entidades Externas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>

<body>

<h2>Registrar Entidad Externa</h2>
<div class="box boxpost">
<h4>Datos de la entidad</h4>

  <form name="form1" method="post" class="form-horizontal" action="clienteAction.do">
    <input type="hidden" name="hacia" value="registrarEntExt" />
    <div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre*</label>
    								<div class="col-sm-8">
      								<input name="nom_instit" type="text" class="form-control" id="inputNombre">
    								</div>
    							</div>
    
    <div class="form-group">
    								<label for="inputTelefono" class="col-sm-4 control-label">Teléfono</label>
    								<div class="col-sm-8">
      								<input type="text" name="fono_instit"  class="form-control" id="inputTelefono">
    								</div>
    							</div>
    
    <div class="form-group">
    								<label for="inputSitio" class="col-sm-4 control-label">Sitio web</label>
    								<div class="col-sm-8">
      								<input name="web" type="text" class="form-control" id="inputSitio">
    								</div>
    							</div>
    <div class="form-group">
    								<label for="inputDireccion" class="col-sm-4 control-label">Dirección</label>
    								<div class="col-sm-8">
      								<input name="dir_instit" type="text" class="form-control" id="inputDireccion">
    								</div>
    							</div>
            
            
    <div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
      									<button name="Submit"  type="submit" class="botoVerde"><span class="glyphicons glyphicons-disk-save"></span> Guardar</button>
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
  var frmvalidator  = new Validator("form1");
  frmvalidator.addValidation("nom_instit","req","Debe ingresar el nombre");
  frmvalidator.addValidation("nom_instit","maxlen=80","Nombre no puede superar los 80 caracteres");
  frmvalidator.addValidation("nom_instit","alnumspace");

  frmvalidator.addValidation("fono_instit","alnumspace");
  frmvalidator.addValidation("fono_instit","maxlen=25","Teléfono no puede superar los 25 caracteres");


  frmvalidator.addValidation("web","maxlen=150","Sitio Web no puede superar los 150 caracteres");

  frmvalidator.addValidation("dir_instit","maxlen=50","Dirección no puede superar los 50 caracteres");
  frmvalidator.addValidation("dir_instit","alnumspace");

</script>

</body>
</html>
