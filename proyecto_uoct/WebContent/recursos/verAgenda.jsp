<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@ page import="proyecto_uoct.reservas.VO.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>


<%
RecursoVO recurso = (RecursoVO) request.getAttribute("RECURSO");
List reservas = (List) request.getAttribute("RESERVAS");
Long idusuario = (Long) request.getAttribute("Id_Usu");

if(recurso.getReservas() != null && recurso.getReservas().size() > 0) {
  reservas = recurso.getReservas();
}


SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
%>

<html>
<head>
  <title>Agenda de Recursos</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <script type="text/javascript" src="../util/calendario/calendar1.js"></script>
  <script type="text/javascript">

  function goBuscar() {
    formulario.accion.value = "BUSCAR_RESERVAS";
    formulario.submit();
  }

  function goEliminar(id,form) {
    formulario.idReserva.value = id;
    formulario.accion.value = "ELIMINAR";
    elimin=window.confirm('¿Está seguro(a) de eliminar esta reserva?');
    (elimin)?formulario.submit():'return false';
  }

  var reservar = null;
  function popUp(href, target, features) {
    reservar = window.open(href, target, features);
    reservar.window.focus();
  }

  function goReservar() {
    formulario.fecha.value = reservar.document.all["fecha"].value;
    formulario.horaInicio.value = reservar.document.all["horaInicio"].value;
    formulario.minutosInicio.value = reservar.document.all["minutosInicio"].value;
    formulario.horaFin.value = reservar.document.all["horaFin"].value;
    formulario.minutosFin.value = reservar.document.all["minutosFin"].value;
    formulario.motivo.value = reservar.document.all["motivo"].value;
    reservar.window.close();
    formulario.accion.value = "RESERVAR";
    formulario.submit();
  }

  </script>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>

<table width="750" border="0">
  <tr>
    <td valign="top"> <form name="formulario" action="recursosAction.do" method="POST">
        <h3>Recurso: <%= recurso.getNombre()%></h3>
        <input type="hidden" name="idRecurso" value="<%= recurso.getIdRecurso()%>" />
        <input type="hidden" name="nombreRecurso" value="<%= recurso.getNombre()%>" />
        <input type="hidden" name="idReserva"/>
        <input type="hidden" name="accion"/>
        <!--Campos usados para reservar-->
        <input type="hidden" name="fecha"/>
        <input type="hidden" name="horaInicio"/>
        <input type="hidden" name="minutosInicio"/>
        <input type="hidden" name="horaFin"/>
        <input type="hidden" name="minutosFin"/>
        <input type="hidden" name="motivo"/>
        <table width="469" border="0" align="left">
          <tr bgcolor="#ADD8E4">
            <td colspan="3" valign="top"><strong>Buscar reservas hechas entre</strong></td>
          </tr>
          <tr>
            <td width="182"> <input name="fechaDesde" type="text" size="10" readonly="readonly" >
              &nbsp; <a href="javascript:cal1.popup();"><img src="../util/calendario/img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
            </td>
            <td width="183"> <input name="fechaHasta" type="text" size="10" readonly="readonly" >
              &nbsp; <a href="javascript:cal2.popup();"><img src="../util/calendario/img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
            </td>
            <td width="90"> <input type="button" name="buscar" value="Buscar" onclick="javascript:goBuscar()" />
            </td>
          </tr>
        </table>
		      </form>
</td>
  </tr>
  <tr>
    <td valign="top" align="left">
	        <h4 align="left">Reservas</h4>

          <display:table name="RESERVAS" id="res" requestURI="recursosAction.do" pagesize="15" class="its">
          <display:column title="Fecha"> <%= fecha.format(((ReservaVO)res).getFechaReserva())%> </display:column>

          <display:column title="Hora"> <%= hora.format(((ReservaVO)res).getHoraDeInicio()) + " - " + hora.format(((ReservaVO)res).getHoraDeFin())%> </display:column>

          <display:column title="Solicitante">
          <%= ((ReservaVO)res).getNombreUsuario()%> </display:column>

          <display:column title="Motivo/Destino">
            <%= ((ReservaVO)res).getMotivo()%>
          </display:column>

          <display:column title="Eliminar Reserva">
          <% if (idusuario.equals(((ReservaVO)res).getIdUsuario())){%>
          <a href="#" onclick="goEliminar(<%= ((ReservaVO)res).getIdReserva()%>)">Eliminar</a>
          <%}
              else { out.println("&nbsp;");}
              %>
          </display:column> <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
          <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
          <display:setProperty name="paging.banner.placement" value="bottom"/>
          <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
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
          <display:setProperty name="export.amount" value="list"/>

		  </display:table>


</td>
  </tr>
  <tr>
    <td valign="top" align="left"><a href="reservarRecurso.jsp" target="_blank" onClick="popUp(this.href, this.target, 'width=600,height=250'); return false;">
          Reservar </a> </td>
  </tr>
</table>

  <script language="JavaScript" type="text/javascript">
    var cal1 = new calendar1(document.forms['formulario'].elements['fechaDesde']);
    cal1.year_scroll = true;
    cal1.time_comp = false;
    var cal2 = new calendar1(document.forms['formulario'].elements['fechaHasta']);
    cal1.year_scroll = true;
    cal1.time_comp = false;
  </script>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/recursos.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
