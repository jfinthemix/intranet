<%@ page import="proyecto_uoct.ventas.VO.*,java.text.SimpleDateFormat,java.util.List,java.util.Iterator,java.lang.Float" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
VtaVO venta=(VtaVO)request.getAttribute("venta");%>
<html>
<head>
<title>Registrar Fecha de Entrega de la Cotización</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
  <!--calendario-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>


<script type="text/javascript">

function confirmaEliminacion(formu){
if(formu.fecha.value==''){
alert('Debe ingresar la fecha');
return false;
}
 resp=window.confirm('Al registrar la fecha de la cotización la venta pasará al estado\n COTIZACIÓN ENTREGADA.\n¿Desea continuar?');
(resp)?formu.submit():'return false';
}
  </script>


<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>



</head>

<body>
<table width="608" border="0">
  <tr>
    <td colspan="2"><h3 align="left">Registrar Fecha de Entrega de Cotizaci&oacute;n
        a Venta : <font color="#FF0000"><%= venta.getCodVenta()%></font></h3></td>
  </tr>
  <form action="ventasAction.do" method="POST" name="form1">
    <tr>
      <td height="73" colspan="2"> <div align="left">
          <input type="hidden" name="accion" value="regFechaCotizacion" />
          <input type="hidden" name="idVenta" value="<%=venta.getIdVenta()%>" />
          <input type="hidden" name="Submit" value="eeee" />
          <table width="316" border="1" cellpadding="1">
            <tr>
              <td width="153" bgcolor="#F7FBC4">
<div align="right"><strong>Fecha de Recepci&oacute;n:</strong></div></td>
              <td width="147"><%=sdf.format(venta.getFechaRecepcion()) %></td>
            </tr>
            <tr>
              <td bgcolor="#F7FBC4">
<div align="right"><strong>Cliente:</strong></div></td>
              <td><%= venta.getCliente().getNomCli() %></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td width="217" bgcolor="#ADD8E4"><div align="left"><font color="#FF0000"><strong>Fecha
          de Entrega de Cotizaci&oacute;n:</strong></font></div></td>
      <td width="381"><div align="left"><font color="#FF0000"> </font>
          <input type="text" name="fecha" size=10 readonly="readonly">
          &nbsp;<a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
          </a></div></td>
    </tr>
    <tr>
      <td colspan="2"><div align="left">
          <input type="button" name="sub" value="Registrar!" onClick="confirmaEliminacion(this.form);">
        </div></td>
    </tr>
  </form>
</table>
<script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("form1");
frmvalidator.addValidation("fecha","req","Debe indicar la fecha");
frmvalidator.addValidation("fecha","maxlen=10","fecha no puede superar los 10 caracteres");
</script>
  <script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['form1'].elements['fecha']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
//-->
</script>

</div>

<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
