<%@ page import="proyecto_uoct.ventas.VO.*,java.text.SimpleDateFormat,
java.util.List,java.util.Iterator,java.lang.Float,
proyecto_uoct.common.util.UtilVenta" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
VtaVO venta=(VtaVO)request.getAttribute("venta");
float uf=((Float)request.getAttribute("uf")).floatValue();
UtilVenta util=new UtilVenta();
Integer total=new Integer(0);
%>
<html>
<head>
<title>Registrar Fecha de Entrega de la Cotización</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
  <!--calendario-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


<script type="text/javascript">

function confirmaEnvioFecha(formu){

if(formu.fecha.value==''){
alert('Debe ingresar la fecha');
return false;
}


 resp=window.confirm('Al registrar la fecha de pago la venta pasará al estado\n PAGADA.\n¿Desea continuar?');
(resp)?formu.submit():'return false';
}
  </script>


</head>

<body>
<table width="598" border="0">
  <tr>
    <td colspan="2"><h3>Registrar Fecha de Pago de Venta: <font color="#FF0000"><%= venta.getCodVenta()%></font></h3></td>
  </tr>

    <form name="recalcForm" method="post" action="ventasAction.do">
<input type="hidden" name="accion" value="refrescaRegPago">
<input type="hidden" name="idVenta" value="<%=venta.getIdVenta()%>" />
<table width="396" border="0">
          <tr>
            <td width="390" height="29" bgcolor="#A6F7BA"><strong>Recalcular usando
              UF igual a:</strong>
              <input type="text" name="nuevaUF" value="<%=(new Float(uf)).toString().replace('.',',') %>" >
              <strong>pesos</strong></td>
          </tr>
          <tr>
            <td><div align="center">
                <input type="submit" name="recalc" value="Recalcular">
              </div></td>
          </tr>

          <tr>
            <td height="10"></td>
          </tr>

        </table>
</form>



  <form action="ventasAction.do" method="POST" name="form1">
    <tr>
      <td height="97" colspan="2"> <input type="hidden" name="accion" value="regFechaPago" />
        <input type="hidden" name="idVenta" value="<%=venta.getIdVenta()%>" />
		<input type="hidden" name="valorUF" value="<%=uf%>" />

        <input type="hidden" name="Submit" value="submit" /> <table width="440" border="1" cellpadding="1">
          <tr>
            <td width="226" bgcolor="#F7FBC4"> <div align="right"><strong>Fecha
                de Recepci&oacute;n:</strong></div></td>
            <td width="126"><%=sdf.format(venta.getFechaRecepcion()) %></td>
          </tr>
          <tr>
            <td width="226" bgcolor="#F7FBC4"> <div align="right"><strong>Fecha
                de Entrega de la Cotización:</strong></div></td>
            <td width="126"><%=sdf.format(venta.getFechaCotizacion()) %></td>
          </tr>
          <tr>
            <td bgcolor="#F7FBC4"> <div align="right"><strong>Cliente:</strong></div></td>
            <td><%= venta.getCliente().getNomCli() %></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td colspan="2"> <table width="600" border="1" bordercolor="#FF0000">
          <tr bgcolor="#ADD8E4">
            <td width="90"><strong>Cantidad</strong></td>
            <td width="150"><strong>Tipo Informaci&oacute;n</strong></td>
            <td width="169"><strong>Descripci&oacute;n</strong></td>
            <td width="74"><strong>Precio unitario($)</strong></td>
            <td width="80"><strong>Precio total($)</strong></td>
          </tr>
          <% if(venta.getDetVenta()!=null){
            Iterator idet=venta.getDetVenta().iterator();
            Float precioItem=new Float("0");
            int precioItemI=0;
            while(idet.hasNext()){
              InfoVtaVO inf=(InfoVtaVO) idet.next();
              precioItem=new Float(inf.getPrecio().floatValue()*uf);
              int precioItemRedon=Math.round(precioItem.floatValue());
              precioItemI= precioItemRedon* inf.getCantidad().intValue();
              total=new Integer(total.intValue() + precioItemI);
              %>
          <tr>
            <td><div align="center"><%=inf.getCantidad()%></div></td>
            <td><div align="center"><%=inf.getTipoInfo() %></div></td>
            <td width="169"> <div align="center">
                <%if(inf.getDescripcion()!=null){
            out.print(inf.getDescripcion());}
            else{out.print("&nbsp;");}
            %>
              </div></td>
            <td><div align="right">$<%=util.formateaPrecioPesos((new Integer(precioItemRedon)).toString()) %></div></td>
            <td><div align="right">$<%=util.formateaPrecioPesos((new Integer(precioItemI)).toString()) %></div></td>
          </tr>
          <%}
        }
        %>
          <tr>
            <td> <strong>UF=$<%=(new Float(uf)).toString().replace('.',',') %></strong></td>
            <td height="24" colspan="3" align="right"><strong>SubTotal (sin IVA):</strong></td>
            <td><div align="right">$<%=util.formateaPrecioPesos(total.toString()) %></div></td>
          </tr>
          <tr>
            <td height="24" colspan="4" align="right"><strong>IVA</strong></td>
            <td><div align="right">$
                <%
            Float ivaF=new Float(total.floatValue() * 0.19);
            int iva=Math.round(ivaF.floatValue());
            String ivaS=new Integer(iva).toString().replace('.',',');
            out.print(util.formateaPrecioPesos(ivaS));
             %>
              </div></td>
          </tr>
          <tr>
            <td height="24" colspan="4" align="right"><strong>Total (con IVA):</strong></td>
            <td><div align="right">$<%
            Integer tot= new Integer((int)Math.round(total.floatValue()*1.19));
            out.print(util.formateaPrecioPesos(tot.toString())); %></div></td>
          </tr>
          <tr>
            <td height="24" colspan="5" align="right"><table width="600" border="1" bordercolor="#FF0000">
                <tr>
                  <td width="180">Comentario</td>
                  <td width="400">
                    <%if(venta.getComentario()!=null){
          out.print(venta.getComentario());
        }else{
        out.print("&nbsp;");
        }
          %>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>

    <tr>
      <td height="26" align="center" colspan="2">
</td>
    </tr>

	    <tr>
      <td width="134" bgcolor="#ADD8E4"><font color="#FF0000"><strong>Fecha de
        Pago: </strong></font> </td>
      <td width="606"><input type="text" name="fecha" size=10 readonly="readonly">
        &nbsp;<a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
        </a></td>
    </tr>
    <tr>
      <td colspan="2"> <input type="button" name="sub" value="Registrar!" onClick="confirmaEnvioFecha(this.form);">
      </td>
    </tr>
  </form>
</table>

    <script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['form1'].elements['fecha']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
//-->
</script>



<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
