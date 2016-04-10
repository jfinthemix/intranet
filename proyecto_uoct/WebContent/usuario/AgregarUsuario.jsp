<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.usuario.VO.AreaVO,java.util.List,java.util.Iterator" errorPage="" %>
<%@page import="proyecto_uoct.common.VO.PerfilVO"%>
<%
List areas= (List) request.getAttribute("areas");
List perfiles= (List) request.getAttribute("perfiles");
%>

<html>
<head>
<title>Registrar nuevo usuario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</head>

<body>


<script language="JavaScript" type="text/javascript">
/*
var frmvalidator  = new Validator("form1");
frmvalidator.addValidation("nombre_usu","req","Debe indicar el  Nombre del usuario");
frmvalidator.addValidation("nombre_usu","maxlen=20","Nombre no puede superar los 20 caracteres");
frmvalidator.addValidation("nombre_usu","alnumspace");

frmvalidator.addValidation("nombre2_usu","maxlen=20","2º Nombre no puede superar los 20 caracteres");
frmvalidator.addValidation("nombre2_usu","alnumspace");

frmvalidator.addValidation("apellido_usu","req","Debe indicar el apellido");
frmvalidator.addValidation("apellido_usu","maxlen=20","Apellido no puede superar los 20 caracteres");
frmvalidator.addValidation("apellido_usu","alnumspace");

frmvalidator.addValidation("apellido2_usu","maxlen=20","2º Apellido no puede superar los 20 caracteres");
frmvalidator.addValidation("apellido2_usu","alnumspace");

frmvalidator.addValidation("username","req","Debe indicar el nombre de usuario de Intranet");
frmvalidator.addValidation("username","alnumhyphen");
frmvalidator.addValidation("username","maxlen=15","Nombre de usuario no puede superar los 15 caracteres");

frmvalidator.addValidation("password","req","Debe indicar Contraseña");
frmvalidator.addValidation("password","maxlen=15","Contraseña no puede superar los 15 dígitos");
frmvalidator.addValidation("password","alnumhyphen");

frmvalidator.addValidation("id_area","req","Debe indicar el área");
frmvalidator.addValidation("id_area","dontselect","Debe indicar el área");
frmvalidator.addValidation("id_perfil","req","Debe indicar el perfil del nuevo usuario");
frmvalidator.addValidation("id_perfil","dontselect=0","Debe indicar el perfil del nuevo usuario");*/
</script>



<h2>Agregar usuario</h2>
						
				 		<div class="box boxpost">
				 			<h4>Información del nuevo usuario</h4>
				 			<form name="form1" method="post" action="usuarioAction.do" class="form-horizontal">
				 			<input type="hidden" name="hacia" value="nuevoUsuario" />
  								<div class="form-group">
    								<label for="inputNombre" name="nombre_usu" class="col-sm-4 control-label">Nombre*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputNombre">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputNombreDos" class="col-sm-4 control-label">Segundo nombre</label>
    								<div class="col-sm-8">
      								<input name="nombre2_usu" type="text" class="form-control" id="inputNombreDos">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputApellido"  class="col-sm-4 control-label">Apellido paterno*</label>
    								<div class="col-sm-8">
      								<input type="text" name="apellido_usu" class="form-control" id="inputApellido">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputApellidoDos" class="col-sm-4 control-label">Apellido materno</label>
    								<div class="col-sm-8">
      								<input type="text" name="apellido2_usu" class="form-control" id="inputApellidoDos">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputUsername" class="col-sm-4 control-label">Username Intranet*</label>
    								<div class="col-sm-8">
      								<input type="text" name="username" class="form-control" id="inputUsername">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputPass" class="col-sm-4 control-label">Password*</label>
    								<div class="col-sm-8">
      								<input type="password" name="password" class="form-control" id="inputPass">
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputArea" class="col-sm-4 control-label">Área de trabajo*</label>
    								<div class="col-sm-8">
      								<select name="id_area" class="form-control" id="inputArea">
    										<option value="0" selected disabled>Seleccionar</option>
    										    <%
   Iterator i= areas.iterator();
   while (i.hasNext()){
     AreaVO area= (AreaVO) i.next();
     out.print("<option value=\""+area.getIdArea()+"\">"+ area.getArea()+"</option>");

   }

    %>
    										
    									</select>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputPerfil" class="col-sm-4 control-label">Perfil del usuario*</label>
    								<div class="col-sm-8">
      								<select name="id_perfil" class="form-control" id="inputPerfil">
    										<option value="0" selected disabled>Seleccionar</option>
    										<%
   Iterator ip= perfiles.iterator();
   while (ip.hasNext()){
     PerfilVO perf= (PerfilVO) ip.next();
     out.print("<option value=\""+perf.getId()+"\">"+ perf.getStr()+"</option>");

   }

    %>
    										
    										
    									</select>
    								</div>
  								</div>
  								
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-offset-2 col-sm-10">
      									<button type="submit" class="botoVerde">Registrar usuario</button>
    									</div>
  									</div>
    							</div>
  							</form>
						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
</body>
</html>