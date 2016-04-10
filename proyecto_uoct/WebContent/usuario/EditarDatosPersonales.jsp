<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.usuario.VO.UsuarioVO" errorPage="" %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<%
UsuarioVO usuario= (UsuarioVO) request.getAttribute("usuario");
String mensaje=(String) request.getAttribute("mensaje");
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
%>
<html>
<head>
<title>Editar datos de <%= usuario.getNombreUsu() %> <%= usuario.getApellUsu() %></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">



<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script language="JavaScript" type="text/javascript">

   function esDigito(sChr){
    var sCod = sChr.charCodeAt(0);
    return ((sCod > 47) && (sCod < 58));
   }

   function valSep(oTxt){
    var bOk = false;
    var sep1 = oTxt.value.charAt(2);
    bOk = (sep1 == "/")|| (sep1 == "-");
    return bOk;
   }

   function finMes(oTxt){
    var nMes = parseInt(oTxt.value.substr(3, 2), 10);
    var nRes = 0;
    switch (nMes){
     case 1: nRes = 31; break;
     case 2: nRes = 28; break;
     case 3: nRes = 31; break;
     case 4: nRes = 30; break;
     case 5: nRes = 31; break;
     case 6: nRes = 30; break;
     case 7: nRes = 31; break;
     case 8: nRes = 31; break;
     case 9: nRes = 30; break;
     case 10: nRes = 31; break;
     case 11: nRes = 30; break;
     case 12: nRes = 31; break;
    }
    //return nRes + (((nMes == 2) && (nAno % 4) == 0)? 1: 0);
	return nRes;
   }

   function valDia(oTxt){
    var bOk = false;
    var nDia = parseInt(oTxt.value.substr(0, 2), 10);
    bOk = ((nDia >= 1) && (nDia <= finMes(oTxt)));
    return bOk;
   }

   function valMes(oTxt){
    var bOk = false;
    var nMes = parseInt(oTxt.value.substr(3, 2), 10);
    bOk = ((nMes >= 1) && (nMes <= 12));
    return bOk;
   }



function valFecha(){
//	var f=window.document.forms.frm;
	var oTxt=form1.cumpleanos;
    var bOk = true;
	if(oTxt.value == ""){
	return true;
	}
	if ((oTxt.value.length ==5)){
   		bOk = bOk && (valMes(oTxt));
	    bOk = bOk && (valDia(oTxt));
   		bOk = bOk && (valSep(oTxt));
		if (!bOk){
   	 		alert ("Fecha inválida(Formato permitido:dd-mm)");
		  oTxt.value = "";
	      oTxt.focus();
		  return false;
	 	} else return true;
	}
	else{
	alert ("Fecha inválida(Formato permitido:dd-mm)");
	oTxt.value = "";
    oTxt.focus();
	return false;
	}
}
</script>

</head>

<body>
	<h2>Editar datos personales</h2>
    
  <div class="box boxpost">
  <h4>Mis datos</h4>
    
    <form action="usuarioAction.do" method="POST" name="form1" class="form-horizontal">
        <input type="hidden" name="hacia" value="actualizarDatosPersonales">
        
        <div class="form-group">
    								<label for="inputNombre" class="col-sm-4 control-label">Nombre</label>
    								<div class="col-sm-8">
      								<input type="text" name="nom_usu" class="form-control" id="inputNombre" value="<%=usuario.getNombreUsu() %>">
    								</div>
		</div>
        <div class="form-group">
    								<label for="inputNombreDos" class="col-sm-4 control-label">Segundo nombre</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputNombreDos" name="apell_usu" value="<%=usuario.getApellUsu() %>">
    								</div>
  								</div>
  								
  		<div class="form-group">
    								<label for="inputApellido" class="col-sm-4 control-label">Apellido paterno</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputApellido" name="apell_usu" value="<%=usuario.getApellUsu() %>">
    								</div>
		</div>
  		<div class="form-group">
    								<label for="inputApellidoDos" class="col-sm-4 control-label">Apellido materno</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputApellidoDos" name="apell2_usu" value="<%
      if(usuario.getApellUsu2()!=null){
      out.print(usuario.getApellUsu2());}
     %>">
    								</div>
  								</div>	
  	<div class="form-group">
    								<label for="inputAnexo" class="col-sm-4 control-label">Anexo telefónico</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputAnexo" name="anx" value="<%
     if( usuario.getAnexo()==null){
     out.print("");
     }else{
     out.print(usuario.getAnexo());
     }
     %>">
    								</div>
  								</div>
  	<div class="form-group">
    								<label for="inputTelefono" class="col-sm-4 control-label">Teléfono particular</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputTelefono" name="telefono" value="<%
      if(usuario.getTelefono().intValue()== 0){
      out.print("");
      }else{
      out.print(usuario.getTelefono());}
      %>">
    								</div>
  								</div>
  		<div class="form-group">
    								<label for="inputCelular" class="col-sm-4 control-label">Celular</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputCelular" name="celular" value="<%

      if(usuario.getCelular().intValue()==0){
      out.print("");
      }else{
        out.print(usuario.getCelular());
      }
      %>">
    								</div>
  								</div>

<div class="form-group">
    								<label for="inputDireccion" class="col-sm-4 control-label">Dirección</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputDireccion" name="dir_usu" value="<%
     if(usuario.getDir()==null){
       out.print("");
     }else{
     out.print(usuario.getDir());
     }
      %>">
    								</div>
  								</div>
  	<div class="form-group">
    								<label for="inputEmail" class="col-sm-4 control-label">Email</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputEmail" name="email" value="<%
      if(usuario.getEmail()==null){
        out.print("");
      }else{
        out.print(usuario.getEmail());
      }
        %>">
    								</div>
  								</div>
  						
  	<div class="form-group">
    								<label class="col-sm-4 control-label">Curriculum</label>
    								<div class="col-sm-8">
      								<p class="form-control-static"><a href="usuarioAction.do?hacia=descargarCurriculo">Agregar curriculum</a></p>
    								</div>
  								</div>
  	<div class="form-group">
  	  <%
  String cumple="";
  String dia="";
  String mes="";
  if (usuario.getCumpleanos()!=null){
    cumple=sdf.format(usuario.getCumpleanos());
    dia=cumple.substring(0,2);
    mes=cumple.substring(3,5);
    cumple=dia+"-"+mes;
  }%>
    								<label for="inputCumple" class="col-sm-4 control-label">Fecha de nacimiento</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputCumple" name="cumpleanos" value="<%=cumple%>" >
    								</div>
  								</div>
  	<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
      									<button type="submit" class="botoVerde"><span class="glyphicons glyphicons-disk-save"></span> Guardar</button>
      								</div>
  									</div>
    							</div>
  	</form>					
</div>

	<div class="box clearfix">
				 			<h4>Otras opciones</h4>
				 			<div class="col-md-4 linktab">
				 				<a  href="usuarioAction.do?hacia=informesActPers">
				 					<span class="glyphicons glyphicons-file"></span><br>
				 					Informe de actividades
				 				</a>
				 			</div>
				 			<div class="col-md-4 linktab">
				 				<a href="usuarioAction.do?hacia=cambiarContrasena&id_usu=<%= usuario.getIdUsu()%>">
				 					<span class="glyphicons glyphicons-lock"></span><br>
				 					Cambiar password
				 				</a>
				 			</div>
				 			<div class="col-md-4 linktab">
				 				<a  href="usuarioAction.do?hacia=acambiarFotografia">
				 					<span class="glyphicons glyphicons-picture"></span><br>
				 					Cambiar fotografía
				 				</a>
				 			</div>
  						</div>


<div class="verMas">
		<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
		<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
</div>

        

<script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("form1");

  frmvalidator.addValidation("nom_usu","req","Debe ingresar el nombre");
  frmvalidator.addValidation("nom_usu","alnumspace");
  frmvalidator.addValidation("nom_usu","maxlen=20","Nombre no puede superar los 20 caracteres");
  frmvalidator.addValidation("nom2_usu","maxlen=20","2º Nombre no puede superar los 20 caracteres");
  frmvalidator.addValidation("nom2_usu","alnumspace");
  frmvalidator.addValidation("apell_usu","req","Debe ingresar el Apellido");
  frmvalidator.addValidation("apell_usu","alnumspace");
  frmvalidator.addValidation("apell_usu","maxlen=20","Apellido no puede superar los 20 caracteres");
  frmvalidator.addValidation("apell2_usu","maxlen=20","2º Apellido no puede superar los 20 caracteres");
  frmvalidator.addValidation("apell2_usu","alnumspace");
  frmvalidator.addValidation("email","email");
  frmvalidator.addValidation("celular","numeric","Se aceptan solo números en el celular");
  frmvalidator.addValidation("telefono","numeric","Se aceptan solo números en el télefono");

  frmvalidator.setAddnlValidationFunction("valFecha");

</script>


</body>
</html>
