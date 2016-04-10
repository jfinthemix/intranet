<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.proyecto.VO.DetalleProyectoVO" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.proyecto.VO.ProyectodeListaVO " %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List proyectos=(List) request.getAttribute("proyectos");
Integer idSes=(Integer)request.getAttribute("idSes");
Integer regnlo=(Integer)request.getAttribute("regnlo");
Integer regot=(Integer)request.getAttribute("regot");

Date hoy = new Date();
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
Integer ano = Integer.valueOf(sdf2.format(hoy));


%>
<html>
<head>
<title>Búsqueda de Iniciativas</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3>Editar Iniciativas de Inversión</h3></td>
  </tr>
  <tr>
    <td>Seleccione el a&ntilde;o de inicio del Proyecto</td>
  </tr>
  <tr>
    <td><form action="proyectoAction.do" method="POST">
        <input type="hidden" name="hacia" value="buscarParaEditarIni" />
        <select name="ano" >
          <option value="<%=ano.intValue()+1%>"><%=ano.intValue()+1%></option>
          <%   for (int ia=0;ia<=5;ia++){%>
          <option value="<%=ano.intValue()-ia%>"><%=ano.intValue()-ia%></option>
          <%}%>
        </select>
        <input type="submit" name="buscarProyecto" value="Buscar" />
      </form></td>
  </tr>
  <tr>
    <td><div align="left">
        <%if (proyectos!=null){
%>
        <display:table name="proyectos" id="inis" pagesize="15" class="its" requestURI="proyectoAction.do">
        <display:caption>Editar Iniciativas de Inversión </display:caption> <display:column title="Nombre de la Iniciativa" property="nomProy" href="proyectoAction.do?hacia=detalleIni" paramProperty="idProy" paramId="idProy">
        </display:column> <display:column title="Encargado" property="encargado">
        </display:column> <display:column title="Estado">
        <%if(((ProyectodeListaVO)inis).getIsActivo()){out.print("Activo");}else{out.print("Inactivo");} %>
        </display:column> <display:column title="Editar Datos de la Iniciativa" href="proyectoAction.do?hacia=editarIni" paramProperty="idProy" paramId="idProy" >
        <%if(((ProyectodeListaVO)inis).getIsActivo()&& ((ProyectodeListaVO)inis).getEsDelEquipo() && (((ProyectodeListaVO)inis).getIdEncargado().equals(idSes))){out.print("Editar Datos");} %>
        </display:column> <display:column title="Admininstrar Documentos">
        <%if(((ProyectodeListaVO)inis).getIsActivo() && ((ProyectodeListaVO)inis).getEsDelEquipo() ){%>
        <a href="proyectoAction.do?hacia=adminDocsIni&nomProy=<%=(((ProyectodeListaVO)inis).getNomProy())%>&idProy=<%=(((ProyectodeListaVO)inis).getIdProy())%>">
        Admin.Documentos</a>
        <%}else{out.print("&nbsp");} %>
        </display:column> <display:column title="Admin. OT" href="proyectoAction.do?hacia=adminOTs" paramProperty="idProy" paramId="idProy" >
        <%if(((ProyectodeListaVO)inis).getIsActivo()&& regot.intValue()==1 && ((ProyectodeListaVO)inis).getEsDelEquipo()){out.print("Administrar OTs");}%>
        </display:column> <display:column title="Admin. NLO de la Iniciativa" href="proyectoAction.do?hacia=adminNLOdelaIni" paramProperty="idProy" paramId="idProy" >
        <%if(((ProyectodeListaVO)inis).getIsActivo()&& regnlo.intValue()==1 && ((ProyectodeListaVO)inis).getEsDelEquipo()){out.print("Administrar NLO de la Iniciativa");}%>
        </display:column> <display:column title="Finalizar la Iniciativa" href="proyectoAction.do?hacia=aFinalizarIni" paramProperty="idProy" paramId="idProy" >
        <%if((((ProyectodeListaVO)inis).getIdEncargado().equals(idSes)) && (((ProyectodeListaVO)inis).getIsActivo()) && ((ProyectodeListaVO)inis).getEsDelEquipo()){out.print("Finalizar la Iniciativa");}%>
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
        <display:setProperty name="export.amount" value="list"/>

		 </display:table>
        <%} %>
      </div></td>
  </tr>
</table>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
