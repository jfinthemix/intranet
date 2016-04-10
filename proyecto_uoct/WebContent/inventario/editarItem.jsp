<%@ page import="proyecto_uoct.inventario.VO.ItemVO" %>
<%@ page import="proyecto_uoct.usuario.VO.AreaVO,java.util.List,java.util.LinkedList"%>
<%@page import="java.util.Iterator" %>


<%
ItemVO item=(ItemVO)request.getAttribute("item");
%>
<html>
<head>
<title>
Editar Item
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function confirmarEnvio(f){
borrar = window.confirm('¿Seguro que desea eliminar el Item del inventario?');
(borrar)?f.submit():'return false';
}

function confirmarCambios(f){
borrar = window.confirm('¿Está seguro de los cambios efectuados?');
(borrar)?f.submit():'return false';
}
</script>

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>
<body bgcolor="#ffffff">
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Editar Item</h3>
</td>
  </tr>
  <tr>
    <td><form name="form1" method="post" action="inventarioAction.do">
        <input name="accion" type="hidden" value="actualizarItem">
        <input name="idItem" type="hidden" value="<%=item.getIdItem()%>">
        <table width="436" border="1" align="left">
          <tr>
            <td width="106" bgcolor="#ADD8E4"> <div align="right"><strong>Item</strong></div></td>
            <td width="314"> <div align="left">
                <input name="nomItem"  maxlength="100" type="hidden" id="nomItem" readonly="readonly" value="<%=item.getNomItem()%>">
                <%=item.getNomItem()%> </div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Categor&iacute;a</strong></div></td>
            <td> <div align="left">
                  <select name="idCategoria" size="1">
                    <option value="1" <% if (item.getIdCategoria().intValue()==1){out.print(" selected='selected' ");}%>>Aseo</option>
                    <option value="2" <% if (item.getIdCategoria().intValue()==2){out.print(" selected='selected' ");}%> >Escritorio</option>
                    <option value="3" <% if (item.getIdCategoria().intValue()==3){out.print(" selected='selected' ");}%>>Computaci&oacute;n</option>
                </select>
              </div></td>
          </tr>

          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Tipo de Unidad</strong></div></td>
            <td> <div align="left">
                  <select name="tUnidad" size="1">
                    <option value="1" <% if (item.getIdUnidad().intValue()==1){out.print(" selected='selected' ");}%>>Unidad</option>
                    <option value="2" <% if (item.getIdUnidad().intValue()==2){out.print(" selected='selected' ");}%> >Paquete</option>
                    <option value="3" <% if (item.getIdUnidad().intValue()==3){out.print(" selected='selected' ");}%>>Rollo</option>
                </select>
              </div></td>
          </tr>


          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Cantidad</strong></div></td>
            <td> <div align="left">
                <input type="text" name="cantidad" maxlength="6" value="<%=item.getCantidadItem()%>">
              </div></td>
          </tr>
          <tr>
            <td colspan="2"><div align="center">
                <input type="submit" name="Submit1" value="Guardar">
                <input type="reset" name="Submit2" value="Restablecer">
              </div></td>
          </tr>
        </table>
      </form></td>
  </tr>
  <tr>
    <td>
        <script language="JavaScript" type="text/javascript">
  var frmvalidator  = new Validator("form1");
  frmvalidator.addValidation("nomItem","req","Debe Nombre del Item");
  frmvalidator.addValidation("nomItem","maxlen=100","Nombre no puede superar los 100 caracteres");

  frmvalidator.addValidation("cantidad","req","Debe indicar la cantidad de unidades del ítem");
  frmvalidator.addValidation("cantidad","num");
  frmvalidator.addValidation("cantidad","maxlen=6","Cantidad no puede superar los 6 dígitos");
</script>

      <form action="inventarioAction.do?accion=eliminarItem" method="post" name="form2">
        <div align="left">
          <input type="hidden" name="accion" value="eliminarItem"/>
          <input type="hidden" name="idItem" value="<%=item.getIdItem()%>"/>
          <input type="button" name="Submit3" value="Eliminar este Item" onClick="confirmarEnvio(this.form);">
        </div>
      </form></td>
  </tr>
</table>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/inventario.html" target="_blank">Ayuda</a>
  </div></body>
</html>
