<%@ page import="proyecto_uoct.ventas.VO.*,java.text.SimpleDateFormat,java.util.List,java.util.Iterator,java.lang.Float" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
VtaVO venta=(VtaVO)request.getAttribute("venta");%>
<html>
<head>
<title>Registrar Fecha de Finalización de la venta</title>
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


 resp=window.confirm('Al registrar la FECHA DE FIN la venta pasará al estado\n VENTA FINALIZADA.\n¿Desea continuar?');
(resp)?formu.submit():'return false';
}

  </script>


<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>



</head>

<body>
<table width="750" border="0">
  <tr>
    <td colspan="2"><h3>Registrar Fecha de Fin de la Venta:</h3></td>
  </tr>
  <form action="ventasAction.do" method="POST" name="form1">
    <tr>
      <td colspan="2"><font color="#FF0000"><%= venta.getCodVenta()%></font></td>
    </tr>
    <tr>
      <td colspan="2"> <input type="hidden" name="accion" value="regFechaFinVenta" />
        <input type="hidden" name="idVenta" value="<%=venta.getIdVenta()%>" />
        <input type="hidden" name="Submit" value="eeee" /> <table width="345" border="1" cellpadding="1">
          <tr>
            <td width="224" bgcolor="#F7FBC4"> <div align="right"><strong>Fecha
                de Recepci&oacute;n:</strong></div></td>
            <td width="105"><%=sdf.format(venta.getFechaRecepcion()) %></td>
          </tr>
          <tr>
            <td width="224" bgcolor="#F7FBC4"> <div align="right"><strong>Fecha
                de Entrega de la Cotización:</strong></div></td>
            <td width="105"><%=sdf.format(venta.getFechaCotizacion()) %></td>
          </tr>
          <tr>
            <td width="224" bgcolor="#F7FBC4"> <div align="right"><strong>Fecha
                de Pago:</strong></div></td>
            <td width="105"><%=sdf.format(venta.getFechaPago()) %></td>
          </tr>
          <tr>
            <td bgcolor="#F7FBC4"><strong>Fecha de Entrega de Informaci&oacute;n:</strong></td>
            <td><%=sdf.format(venta.getFechaEntrega()) %></td>
          </tr>
          <tr>
            <td bgcolor="#F7FBC4"> <div align="right"><strong>Cliente:</strong></div></td>
            <td><%= venta.getCliente().getNomCli() %></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td width="239" bgcolor="#ADD8E4"><div align="right"><font color="#FF0000"><strong>Fecha
          de Finalizaci&oacute;n de la Venta</strong>: </font> </div></td>
      <td width="501"><input type="text" name="fecha" size=10 readonly="readonly">
        &nbsp;<a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
        </a></td>
    </tr>
    <tr>
      <td bgcolor="#ADD8E4"><div align="right"><font color="#FF0000"><strong>C&oacute;digo
          de la Factura</strong>: </font> </div></td>
      <td><input type="text" name="codFactura" size=30> </td>
    </tr>
    <tr>
      <td colspan="2"> <input type="button" name="sub" value="Registrar!" onClick="confirmaEliminacion(this.form);">
      </td>
    </tr>
  </form>
</table>

    <script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['form1'].elements['fecha']);
				cal1.year_scroll = true;
				cal1.time_comp = false;

</script>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
