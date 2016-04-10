<%@ page import="proyecto_uoct.usuario.VO.AreaVO,java.util.List,java.util.LinkedList"%>
<%@page import="java.util.Iterator" %>
<%
List areas=(List)request.getAttribute("areas");
String mensaje=(String)request.getAttribute("mensaje");
%>
<html>
<head>
<title>
regItem
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>



</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3>Ingresar &Iacute;tem</h3>
        <%if(mensaje!=null){
 out.print("<strong><font color=\"red\">"+mensaje+"</font></strong>");}
  %>
      </td>
  </tr>
  <tr>
    <td><form action="inventarioAction.do" name="regItemForm" method="POST">
        <input type="hidden" name="accion" value="registrarItem" />
        <table width="536" border="1" align="left">
          <tr>
            <td width="100" bgcolor="#ADD8E4">
              <div align="right"><strong>Nombre</strong></div></td>
            <td width="420">
<input type="text"  maxlength="100" name="nomItem"></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4">
              <div align="right"><strong>Tipo de Unidad*</strong></div></td>
            <td>
              <select name="tUnidad" size="1" id="tUnidad">
                <option value="1" selected="selected">unidad</option>
                <option value="2">paquete</option>
                <option value="3">rollo</option>
              </select></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Categor&iacute;a*</strong></div></td>
            <td><select name="idCategoria" size="1" id="idCategoria">
                <option value="0" selected="selected">------</option>
                <option value="1">Aseo</option>
                <option value="2">Escritorio</option>
                <option value="3">Computaci&oacute;n</option>
              </select></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4">
              <div align="right"><strong>Cantidad(n&ordm;)*</strong></div></td>
            <td><input type="text" name="cantidad" maxlength="6"></td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
                <input type="submit" name="Submit" value="Registrar">
                &nbsp;&nbsp;
                <input type="reset" name="Submit2" value="Anular">
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
  <tr>
    <td><script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("regItemForm");
frmvalidator.addValidation("nomItem","req","Debe indicar el  Nombre del Item");
frmvalidator.addValidation("nomItem","maxlen=100","Nombre no puede superar los 100 caracteres");

frmvalidator.addValidation("cantidad","req","Debe la cantidad de unidades del item");
frmvalidator.addValidation("cantidad","num");
frmvalidator.addValidation("cantidad","maxlen=6","Cantidad no puede superar los 6 dígitos");

frmvalidator.addValidation("idCategoria","dontselect=0","Debe indicar la categoría del item que se está registrando");

</script></td>
  </tr>
</table>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/inventario.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
