<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="java.util.List,java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.ventas.VO.VtaVO,java.text.SimpleDateFormat,proyecto_uoct.ventas.VO.CliVtaVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List estados=(List)request.getAttribute("estados");
List ventas=(List)request.getAttribute("ventas");%>
<html>
<head>
<title>Busqueda de Ventas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<!--calendario-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>

<!--   Para el traspaso de variables entre Ventanas -->
<script type="text/javascript">
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }


function pasaCli(nomCli, idCli){
    form1.nomCli.value = nomCli;
    form1.idCli.value = idCli;
    otra.window.close();
  }
</script>
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Buscar Ventas</h3></td>
  </tr>
  <form method="post" action="ventasAction.do" name="form1">
    <tr>
      <td> <div align="left">
          <input type="hidden" name="accion" value="buscarVta" />
          <table width="455" border="1" align="left">
            <tr>
              <td width="146" bgcolor="#ADD8E4">
                <div align="right"><strong>Fecha
                  Inicio:</strong></div></td>
              <td width="278"> <table width="246" border="0">
                  <tr>
                    <td width="77" bgcolor="#F7FBC4">
                      <div align="right">Entre
                        el:</div></td>
                    <td width="159"><input type="text" name="fechaPresentacion" size=8 readonly="readonly">
                      &nbsp;<a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
                      </a></td>
                  </tr>
                  <tr>
                    <td bgcolor="#F7FBC4">
                      <div align="right">y el:</div></td>
                    <td><input type="text" name="fechaPresentacion2" size=8 readonly="readonly">
                      &nbsp;<a href="javascript:cal2.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
                      </a></td>
                  </tr>
                </table></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"> <div align="right"><strong>Cliente:</strong></div></td>
              <td> <input type="hidden" name="idCli" value="0"/> <input type="text" name="nomCli" readonly="readonly"/>
                &nbsp; <a href="ventasAction.do?accion=busCliVta_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=350,height=500, scrollbars=1'); return false;">Buscar
                Cliente</a> </td>
            </tr>
            <tr>
              <td height="26" bgcolor="#ADD8E4">
                <div align="right"><strong>Estado
                  de la Venta :</strong></div></td>
              <td><select name="idEstado" size="1">
                  <option value="0" selected></option>
                  <% if(estados!=null){
  Iterator ie=estados.iterator();
  while(ie.hasNext()){
  IdStrVO e=(IdStrVO)ie.next();
  %>
                  <option value="<%=e.getId()%>"><%=e.getStr() %></option>
                  <%
}
}
%>
                </select></td>
            </tr>

             <tr>
              <td height="26" bgcolor="#ADD8E4">
                <div align="right"><strong>Palabra Clave (detalle de venta):</strong></div></td>
              <td> <input type="text" name="palClave" maxlength="30"/>  </td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td> <div align="left">
          <input type="submit" name="Submit" value="Buscar">
        </div></td>
    </tr>
  </form>
  <tr>
    <td><div align="left">
        <script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['form1'].elements['fechaPresentacion']);
				cal1.year_scroll = true;
				cal1.time_comp = false;

				var cal2 = new calendar1(document.forms['form1'].elements['fechaPresentacion2']);
				cal2.year_scroll = true;
				cal2.time_comp = false;


//-->
</script>
        <%if(ventas!=null){%>
        <display:table class="its" name="ventas" id="vs" requestURI="ventasAction.do">
        <display:caption>Ventas Encontradas</display:caption>


        <display:column title="Cod. de Venta" property="codVenta" sortable="true" sortProperty="codVenta" href="ventasAction.do?accion=detalleVenta" paramId="idVenta" paramProperty="idVenta" maxLength="40">
        </display:column>

        <display:column title="Fecha de Inicio"><%=sdf.format(((VtaVO)vs).getFechaRecepcion()) %> </display:column>

        <display:column title="Cliente"><%=(((VtaVO)vs).getCliente()).getNomCli() %> </display:column>

        <display:column title="Estado" property="estado" sortable="true" sortProperty="estado">
        </display:column>

        <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
        <display:setProperty name="export.csv" value="false"/> <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.rtf" value="false"/> <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/> </display:table>
        <%} %>
      </div></td>
  </tr>
</table>

<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
