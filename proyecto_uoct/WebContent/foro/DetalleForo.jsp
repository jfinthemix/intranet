<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,java.sql.Date,proyecto_uoct.foro.VO.DetForoVO,proyecto_uoct.foro.VO.DocForoVO,proyecto_uoct.foro.VO.PostUsuVO " errorPage="" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
String mensaje=(String)request.getAttribute("mensaje");

DetForoVO detforo= (DetForoVO) request.getAttribute("detForo");
   List listadocs= (List) request.getAttribute("docsForo");
   List listapost= (List) request.getAttribute("postForo");

   Integer ses_idusu= (Integer)request.getAttribute("ses_idusu");
   Integer id_admin_foro=(Integer) request.getAttribute("id_admin_foro");


   Integer id_foro= detforo.getIdForo();
   String temaforo= detforo.getTemaForo();
   String titforo=detforo.getTitForo();
   Date fechaini=detforo.getFechaIniForo();
   String Descforo= detforo.getDescForo();
   String adminforo= detforo.getAdminForo();
%>
<html>
<head>
<title>Detalle de Foro</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<%if(mensaje!=null){out.print("<div align= \"center\"><font color=\"red\">"+mensaje+"</font></div>");} %>
<table width="713" border="0">
  <tr>
    <td colspan="2"><h3>Tema:<%= temaforo%></h3></td>
  </tr>
  <tr>
    <td colspan="2"><h4>Foro :<%= titforo %></h4></td>
  </tr>
  <tr>
    <td height="21" colspan="2"><strong>Fecha de inicio</strong>:<%=sdf.format(fechaini)%></td>
  </tr>
  <tr>
    <td width="433"><table width="434" border="1" align="left">
        <tr>
          <td width="147" bgcolor="#A6F7BA">
<div align="center"><strong>Descripci&oacute;n
              del foro </strong></div></td>
          <td width="271" bgcolor="#A6F7BA">
<div align="center"><%=Descforo %></div></td>
        </tr>
        <tr bgcolor="#F7FBC4">
          <td>
            <div align="center"><strong>Administrador del foro</strong></div></td>
          <td>
            <div align="center"><%=adminforo%></div></td>
        </tr>
      </table></td>
    <td width="307">
      <%
  if(listadocs!=null){
   Iterator iflu=listadocs.iterator();
  request.setAttribute("iflu",iflu);
  %>
      <div align="left"> <display:table name="iflu" requestURI="foroAction.do" class="its" pagesize="5" id="flus" >
        <display:column property="tituloDoc" title="Documentos" href="foroAction.do?hacia=descargarDoc" paramId="idDoc" paramProperty="idDocForo" >
        </display:column>


		<display:setProperty name="basic.msg.empty_list" value="No hay documentos"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
		<display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No hay documentos.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/>
		<display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
		<display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
		<display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>


		</display:table> </div>
      <% }
else{out.print("&nbsp;");}
%>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <h4>Mensajes del Foro</h4>

      <display:table name="postForo" id="lp" requestURI="foroAction.do" pagesize="15" class="its">
        <display:column title="Usuario" property="usu">
        </display:column>

        <display:column title="Mensaje" property="post">
        </display:column>

        <display:column title="    Fecha    " sortProperty="fechaPost" class="texto">
          <%=sdf.format(((PostUsuVO)lp).getFechaPost()) %>
        </display:column>



        <display:setProperty name="basic.msg.empty_list" value="El foro no contiene mensajes"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>El foro no contiene mensajes.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/>
		<display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
		<display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
		<display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
		<display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
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
    <td colspan="2"><div align="right"><a href="foroAction.do?hacia=aPostear&id_foro=<%=id_foro %>"><font size="4">Postear
        en este foro!!</font></a></div></td>
  </tr>
  <tr>
    <td height="94" colspan="2">
      <% if (ses_idusu.intValue()== id_admin_foro.intValue()) { %> <p align="right">(Si eres el administrador de este foro puedes
        <a href="foroAction.do?hacia=aEditarForo&id_foro=<%=id_foro %>">Administrar
        el foro aqu&iacute;</a>)</p>
      <%  }%> </td>
  </tr>
</table>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
