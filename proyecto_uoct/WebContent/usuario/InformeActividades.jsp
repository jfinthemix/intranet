<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.InformeActividadesVO,java.text.SimpleDateFormat"%>
<%
String mensaje= (String) request.getAttribute("mensaje");
List informes=(List) request.getAttribute("informes");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
int cont=0;

%>
<html>
<head>
<title>Informes de Actividades Personales</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script language="JavaScript" type="text/javascript">
function confirmarEliminacion(f){
borrar = window.confirm('Seguro que desea eliminar el Informe');
(borrar)?f.submit():'return false';
}
</script>


<script type="text/javascript">


 function valLargoFile()
{
  var frm = document.forms['inforActividades'];// reemplazar nombre del form
  archivo=frm.informe.value; //reemplazar nombre del file
  largo=20; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo");
  return false;
  }else{
  return true;
  }

}
</script>


</head>

<body>
<div align="center">
  <table width="750" border="0">
    <tr>
      <td><h3>Informe de Actividades </h3>
        <% if (mensaje!=null){
out.println(mensaje);
}

%> </td>
    </tr>
    <html:form action="/usuario/usuarioAction.do" name="inforActividades"  method="post" type="proyecto_uoct.usuario.controller.UsuarioFormBean" enctype="multipart/form-data">
    <tr>
      <td><table width="377" border="1">
          <tr>
            <td width="129" bgcolor="#ADD8E4"><strong>Ingreso de Informe</strong></td>
            <td width="232">
              <input type="hidden" name="hacia" value="registrarInforme" />
              <html:file property="informe"/></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td><input type="submit" name="Submit7" value="Guardar"></td>
    </tr>
	</html:form>
    <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("inforActividades");
 frmvalidator.setAddnlValidationFunction("valLargoFile");
frmvalidator.addValidation("informe","req","Debe indicar el archivo");

</script>
    <tr>
      <td><table width="558" border="1">
          <tr>
            <td width="198" bgcolor="#C0C0C0">
              <div align="center"><strong>Fecha
                del Informe</strong></div></td>
            <td width="206" bgcolor="#C0C0C0">
              <div align="center"><strong>Nombre
                del archivo</strong></div></td>
            <td width="75" bgcolor="#C0C0C0">
              <div align="center"><strong>Descargar</strong></div></td>
            <td width="51" bgcolor="#C0C0C0">
              <div align="center"><strong>Borrar</strong></div></td>
          </tr>
          <%
   Iterator i = informes.iterator();
   while(i.hasNext()){
   InformeActividadesVO inf= (InformeActividadesVO) i.next();
   %>
          <tr bgcolor="<% if (cont==0) {out.print("#A6F7BA");cont=1;} else{out.print("#F7FBC4");cont=0;} %>" >
            <td>
              <% out.print(sdf.format(inf.getFechaInfor())); %>
            </td>
            <td><%= inf.getNomInfor() %></td>
            <td><form action="usuarioAction.do" method="POST">
                <input type="hidden" name="hacia" value="descargarInforme" />
                <input type="hidden" name="idInf" value="<%= inf.getIdInfor()%>" />
                <div align="center">
                  <input name="submit" type="submit" value="Descargar" />
                </div>
              </form></td>
            <td><form action="usuarioAction.do" method="POST">
                <input type="hidden" name="hacia" value="borrarInforme" />
                <input type="hidden" name="idInf" value="<%= inf.getIdInfor()%>" />
                <div align="center">
                  <input name="button" type="button" onClick="confirmarEliminacion(this.form);" value="Borrar"  />
                </div>
              </form></td>
          </tr>
          <% }%>
        </table></td>
    </tr>

  </table>




</div>
<hr>
  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

</body>
</html>
