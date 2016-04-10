<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.foro.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  List temas = (List) request.getAttribute("temas");
  List listaForos = (List) request.getAttribute("listaForos");
  String stemas="";
  if(temas!=null){
    Iterator i = temas.iterator();
    while (i.hasNext()) {
      TemasVO datos_tema = (TemasVO) i.next();
      stemas = stemas + "<option value=\"" + datos_tema.getIdTema() +
      "\">" +
      datos_tema.getTema() + "</option>";
    }
  }



%>
<html>
<head>
<title>Buscar Foro de Discusi&oacuten</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<!-- script del calendario-->
<!-- American format mm/dd/yyyy -->
<script language="JavaScript" src="calendar2.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<!-- American format mm/dd/yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="left">Buscar foros de Discusi&oacute;n:</h3></td>
  </tr>
  <tr>
    <td height="131"> <form action="foroAction.do" method="POST" name="busForoForm">
        <input type="hidden" name="hacia" value="buscarForo" />
        <table width="431" border="0" align="left">
          <tr>
            <td width="132" bgcolor="#ADD8E4"> <div align="right"><strong>Por
                Palabra Clave</strong></div></td>
            <td width="289"><strong>
              <input type="text" name="palabra">
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Por fecha de inicio</strong></div></td>
            <td><strong>
              <input type="Text" name="fecha" value="" readonly="readonly">
              <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Por Tema</strong></div></td>
            <td><strong>
              <select name="id_tema">
                <option value=""></option>
                <%  if(temas!=null){
                  out.print(stemas);}%>
              </select>
              </strong></td>
          </tr>
          <tr>
            <td colspan="2"><div align="center"><strong>
                <input type="submit" name="Submit2" value="Buscar">
                </strong></div></td>
          </tr>
        </table>
      </form></td>
  </tr>
  <tr>
    <td height="131"> <div align="left">
        <script language="JavaScript" type="text/javascript">
      var cal1 = new calendar1(document.forms['busForoForm'].elements['fecha']);
      cal1.year_scroll = true;
      cal1.time_comp = false;
    </script>
        <%if(listaForos!=null){
          Iterator ilf=listaForos.iterator();
          request.setAttribute("listaForos",ilf);
          %>
        <display:table class="its" name="listaForos" requestURI="foroAction.do" id="tabla" pagesize="15">
        <display:caption>Foros</display:caption>
		<display:column sortable="true" property="tituloForo" title="Foro" href="foroAction.do?hacia=detalleForo" paramId="id_foro" paramProperty="idForo" >
        </display:column>
		<display:column sortable="true"  title="Tema del Foro"><%=(((ForodeListaVO)tabla).getTemaForo())%> </display:column>
		<display:column sortable="true"  title="Fecha de Inicio"><%=sdf.format(((ForodeListaVO)tabla).getFechaForo())%> </display:column>

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
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>

