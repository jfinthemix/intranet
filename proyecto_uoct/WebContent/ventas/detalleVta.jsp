<%@ page import="proyecto_uoct.common.util.UtilVenta,proyecto_uoct.ventas.VO.*,java.text.SimpleDateFormat,java.util.List,java.util.Iterator,java.lang.Float,java.lang.Math" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
VtaVO venta=(VtaVO)request.getAttribute("venta");
Float uf=(Float)request.getAttribute("uf");
Integer total=new Integer(0);
String mensaje=(String)request.getAttribute("mensaje");
Integer editable= (Integer)request.getAttribute("editable");
UtilVenta util=new UtilVenta();
%>
<html>
<head>
<title>Detalle de Venta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Venta : <font color="#FF0022"><%=venta.getCodVenta() %>
        <%if (mensaje!=null){out.print("<font color=\"red\"><div align=\"center\"><strong>"+mensaje+"</strong></div></font>");} %>
        </font></h3></td>
  </tr>
  <tr>
    <td><div align="left">
        <table width="600" border="1" align="left" cellpadding="1">
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Estado
                de la venta:</strong></div></td>
            <td width="357"><%=venta.getEstado() %></td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Cliente:</strong></div></td>
            <td><%=venta.getCliente().getNomCli() %></td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                de Inicio:</strong></div></td>
            <td><%=sdf.format(venta.getFechaRecepcion()) %></td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                de entrega de la cotización:</strong></div></td>
            <td> <% if(venta.getFechaCotizacion()!=null){out.print(sdf.format(venta.getFechaCotizacion()));}else{out.print("Fecha no registrada");} %> </td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                de Pago:</strong></div></td>
            <td> <% if(venta.getFechaPago()!=null){ out.print(sdf.format(venta.getFechaPago()));}else{out.print("Fecha no registrada");} %> </td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                de Entrega de la Información:</strong></div></td>
            <td> <% if(venta.getFechaEntrega()!=null){ out.print(sdf.format(venta.getFechaEntrega()));}else{out.print("Fecha no registrada");} %> </td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                Finalización del proceso:</strong></div></td>
            <td> <% if(venta.getFechaFin()!=null){ out.print(sdf.format(venta.getFechaFin()));}else{out.print("Fecha no registrada");} %> </td>
          </tr>
          <tr>
            <td width="227" bgcolor="#ADD8E4"> <div align="right"><strong>Código de la Factura:</strong></div></td>
            <td> <% if(venta.getCodFact()!=null){ out.print(venta.getCodFact());}else{out.print("Código aun no registrado");} %> </td>
          </tr>

        </table>
      </div></td>
  </tr>
  <tr>
    <td><h4 align="left"><strong>Informaci&oacute;n Vendida:</strong></h4></td>
  </tr>
  <tr>
    <td><div align="left">
        <table width="600" border="1" bordercolor="#FF0000">
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
              precioItem=new Float(inf.getPrecio().floatValue()*uf.floatValue());
              int precioItemRedon=Math.round(precioItem.floatValue());
              precioItemI= precioItemRedon* inf.getCantidad().intValue();
              total=new Integer(total.intValue() + precioItemI);
              %>
          <tr>

            <td><div align="center"><%=inf.getCantidad()%></div></td>
            <td><%=inf.getTipoInfo() %></td>
            <td width="150"><%if(inf.getDescripcion()!=null){
            out.print(inf.getDescripcion());}
            else{out.print("&nbsp;");}
            %></td>
            <td><div align="right">$<%=util.formateaPrecioPesos((new Integer(precioItemRedon)).toString()) %></div></td>
            <td><div align="right">$<%=util.formateaPrecioPesos((new Integer(precioItemI)).toString()) %></div></td>
          </tr>
          <%}
        }
        %>
          <tr>
            <td> <strong>UF=$<%=uf.toString().replace('.',',') %></strong></td>
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
             %></div></td>
          </tr>
          <tr>
            <td height="24" colspan="4" align="right"><strong>Total ($ con IVA):</strong></td>
            <td><div align="right">$
              <%
            Integer tot= new Integer((int)Math.round(total.floatValue()*1.19));
            out.print(util.formateaPrecioPesos(tot.toString())); %>
             </div></td>
          </tr>
        </table>
      </div></td>

  </tr>
  <tr>
    <td>
      <br />
      <table width="600" border="1" bordercolor="#FF0000">
      <tr>
        <td width="180">Comentario</td>
        <td width="400"><%if(venta.getComentario()!=null){
          out.print(venta.getComentario());
        }else{
        out.print("&nbsp;");
        }
          %>
      </tr>
      </table>
    </td></tr>
</table>

<div align="center">
  <tr>
      <td><div align="center"></div></td>

    <td>&nbsp;</td>
    </tr>

  <tr>
      <td><div align="center"></div></td>

    <td>

      <%


      if(editable.intValue()==1 && venta.getIdEstado().intValue()!=5 && venta.getIdEstado().intValue()!=6){%>
          <a href="ventasAction.do?accion=editarVenta&idVenta=<%=venta.getIdVenta()%>">Editar Venta</a>
          <% }%>
    </td>


  </tr>


  </table>
</div>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>


</body>
</html>
