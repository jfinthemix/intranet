<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List, java.util.Iterator,proyecto_uoct.documentacion.VO.EntExtVO" errorPage=""%>

<%
response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>


<%
  List listainstit = (List) request.getAttribute("listainstit");
  Iterator ii = listainstit.iterator();
%>
<html>
<head>
<title>Registrar Cliente</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="Cache-Control" content="no-cache">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script type="text/JavaScript">
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }

  function pasaEntExt(nomEntExt, idEntExt){
    formulario.nomEntExt.value = nomEntExt;
    formulario.idEntExt.value = idEntExt;
    otra.window.close();
  }

    function ningunaEntidad(){
  formulario.nomEntExt.value="ninguna (cliente independiente)";
  formulario.idEntExt.value="";
  }
</script>
</head>
<body>

<h2>Registrar cliente</h2>

<div class="box boxpost">
<h4>Datos del nuevo cliente</h4>


  <form name="formulario" method="post" action="clienteAction.do" class="form-horizontal">
    <input type="hidden" name="hacia" value="ingresarCli"/>
	<div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre*</label>
    								<div class="col-sm-8">
      								<input name="nom_cli" type="text" class="form-control" id="inputNombre">
    								</div>
  								</div>
	 <div class="form-group">
    								<label for="inputApellido" class="col-sm-4 control-label">Apellido*</label>
    								<div class="col-sm-8">
      								<input name="ape_cli" type="text" class="form-control" id="inputApellido">
    								</div>
  								</div>
         
     <div class="form-group">
    								<label for="inputTelefono" class="col-sm-4 control-label">Teléfono</label>
    								<div class="col-sm-8">
      								<input name="fono_cli" type="text" class="form-control" id="inputTelefono">
    								</div>
  								</div>
     <div class="form-group">
    								<label for="inputEmail" class="col-sm-4 control-label">Email</label>
    								<div class="col-sm-8">
      								<input  name="email_cli" type="email" class="form-control" id="inputEmail">
    								</div>
  								</div>          
   	<div class="form-group">
    								<label for="inputCelular" class="col-sm-4 control-label">Celular</label>
    								<div class="col-sm-8">
      								<input  name="cel_cli" type="text" class="form-control" id="inputCelular">
    								</div>
  								</div>           
    	<div class="form-group">
    								<label for="inputCargo" class="col-sm-4 control-label">Cargo</label>
    								<div class="col-sm-8">
      								<input name="cargo" type="text" class="form-control" id="inputCargo">
    								</div>
  								</div>          
          <div class="form-group">
    								<label for="inputComentario" class="col-sm-4 control-label">Comentario</label>
    								<div class="col-sm-8">
      								<textarea name="comentario" id="inputComentario" class="form-control" rows="4"></textarea>
    								</div>
  								</div>    
                <div class="form-group">
                 <input type="hidden" name="idEntExt">
    			<label for="inputEntidad" class="col-sm-4 control-label">Entidad a la que pertenece</label>
    			<div class="col-sm-8">
											<input name="nomEntExt" type="text" class="form-control" placeholder="Nombre de la entidad">
    								</div>
  								</div>
             <div class="form-group">
    								<label class="col-sm-4 control-label"></label>
    								<div class="col-sm-8">
    									<p class="form-control-static">
    										<a href="clienteAction.do?hacia=abuscarEntidadExt" onClick="popUp(this.href, this.target, 'width=600,height=300,scrollbars=yes'); return false;" class="botoGris botoMini"><span class="glyphicons glyphicons-search"></span> Buscar entidad</a>
    										<a href="clienteAction.do?hacia=aregEntExt" class="botoGris botoMini"><span class="glyphicons glyphicons-circle-plus"></span> Crear entidad</a>
    									</p>
    									<p><a  class="botoGris botoMini"><span class="glyphicons glyphicons-circle"></span> Ninguna Entidad</a></p>
      								
    			</div>
  			</div>
  			
  			
             <div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<button  name="Submit" type="submit" class="botoVerde"><span class="glyphicons glyphicons-disk-save"></span> Guardar</button>
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
var frmvalidator  = new Validator("formulario");
frmvalidator.addValidation("nom_cli","req","Debe ingresar el nombre");
frmvalidator.addValidation("nom_cli","maxlen=50","Nombre no puede superar los 50 caracteres");
frmvalidator.addValidation("nom_cli","alnumspace");

frmvalidator.addValidation("ape_cli","req","Debe ingresar el apellido");
frmvalidator.addValidation("ape_cli","maxlen=50","Apellido: no puede superar los 50 caracteres");
frmvalidator.addValidation("ape_cli","alnumspace");

frmvalidator.addValidation("fono_cli","maxlen=25","No puede superar los 25 dígitos");

frmvalidator.addValidation("email_cli","maxlen=50","Email no puede superar los 50 caracteres");
frmvalidator.addValidation("email_cli","email");


frmvalidator.addValidation("cel_cli","maxlen=25","Celular: no puede superar los 25 dígitos");

frmvalidator.addValidation("cargo","maxlen=100","Cargo: no puede superar los 100 caracteres");
frmvalidator.addValidation("cargo","alnumspace");

frmvalidator.addValidation("comentario","maxlen=200","Comentario: no puede superar los 200 caractéres");

</script>



</body>
</html>
