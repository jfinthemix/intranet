<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, proyecto_uoct.usuario.VO.UsuarioVO,java.util.List,java.lang.Integer, java.util.Iterator,proyecto_uoct.usuario.VO.AreaVO" errorPage=""%>
<%@page import="proyecto_uoct.common.VO.PerfilVO" %>
<%
  UsuarioVO usuario = (UsuarioVO) request.getAttribute("usuario");
  List areas = (List) request.getAttribute("areas");
  List perfiles=(List)request.getAttribute("perfiles");

%>
<html>
<head>
<title>Editar Datos de Usuario</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
</head>
<body>
<table width="748" border="0">
  <tr>
    <td width="742"><h3>Editar Datos de Usuario de Intranet</h3></td>
  </tr>
  <form name="form1" method="post" action="usuarioAction.do">
  <tr>
    <td>
        <input type="hidden" name="hacia" value="actualizarUsuario">
        <input type="hidden" name="idUsu" value="<%= usuario.getIdUsu()%>">
        <table width="53%" border="1" align="left">
          <tr>
            <td width="45%" bgcolor="#ADD8E4"> <div align="right"> <strong>nombre</strong>
              </div></td>
            <td width="55%"> <input type="text" name="nombre_usu" value="<%= usuario.getNombreUsu()%>">
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>segundo nombre</strong>
              </div></td>
            <td> <input type="text" name="nombre2_usu" value="<%
     if (usuario.getNombreUsu2()!=null){
     out.print(usuario.getNombreUsu2());
     }%>"> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>apellido</strong>
              </div></td>
            <td> <input type="text" name="apellido_usu" value="<%= usuario.getApellUsu()%>">
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>segundo apellido</strong>
              </div></td>
            <td> <input type="text" name="apellido2_usu" value="<%if (usuario.getApellUsu2()!=null){
       out.print(usuario.getApellUsu2());
     }%>"> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>username de Intranet</strong>
              </div></td>
            <td> <input type="text" name="username" value="<%= usuario.getUsername()%>">
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong> &aacute;rea de
                trabajo </strong> </div></td>
            <td> <select name="id_area">
                <%
        Iterator i = areas.iterator();
        String sel = null;
        int a_usu = usuario.getIdArea().intValue();
        while (i.hasNext()) {
          AreaVO area = (AreaVO) i.next();
          int a_area = area.getIdArea().intValue();
          if (a_usu == a_area) {
            sel = "selected";
          }
          else {
            sel = " ";
          }
          out.print("<option " + sel + "  value=\"" + area.getIdArea() + "\">" + area.getArea() + "</option>");
        }
      %>
              </select> </td>
          </tr>
          <tr>
            <td> <strong>Estado</strong> </td>
            <td> <%
      if (usuario.getIsActivo()) {
        out.print("Activo");
      }
      else {
        out.print("Inactivo");
      }
    %> </td>
          </tr>
        </table>
     </td>
  </tr>
  <tr>
    <td><table width="395" border="1" align="left">
        <tr>
          <td bgcolor="#ADD8E4"><strong>Perfil del usuario</strong></td>
          <td><select name="idPerfil">
              <%Iterator iperfil=perfiles.iterator();
		while(iperfil.hasNext()){
		PerfilVO per=(PerfilVO) iperfil.next();

                %>
              <option value="<%=per.getId()%>" <%
                if(per.getId().equals(usuario.getIdPerfil())){
                  out.print(" selected");

                }%> > <%= per.getStr()%></option>
              <%
		}
		%>
            </select> </td>
        </tr>
        <tr>
          <td bgcolor="#ADD8E4"><strong>Cambiar la contraseña</strong></td>
          <td><a href="usuarioAction.do?hacia=aReasignarContrasena&id_usu=<%= usuario.getIdUsu()%>&nomUsu=<%= usuario.getNombreUsu()+" "+usuario.getApellUsu() %>">Cambiar contraseña</a></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td><input type="submit" name="Submit" value="Guardar Los Cambios"> <input name="reset" type="reset" value="Restaurar a los Datos Originales"/></td>
  </tr> </form>
  <script language="JavaScript" type="text/javascript">
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

            frmvalidator.addValidation("id_area","req","Debe indicar el área");
            frmvalidator.addValidation("id_area","dontselect","Debe indicar el área");

            frmvalidator.addValidation("idPerfil","req","Debe indicar el perfil del nuevo usuario");

          </script>
  <tr>
    <td><form action="usuarioAction.do" method="post">
        <div align="right">
          <input type="hidden" name="hacia" value="cambiarEstadoUsu"/>
          <input type="hidden" name="idUsu" value="<%= usuario.getIdUsu()%>">
          <input type="submit" name="Submit2" value="<%
if (usuario.getIsActivo()){
out.print("Desactivar a Este Usuario ");
}
else{
out.print("Activar a Este Usuario");
}
%> "/>
        </div>
      </form></td>
  </tr>
</table>



<br>
<br>
<hr>
  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
