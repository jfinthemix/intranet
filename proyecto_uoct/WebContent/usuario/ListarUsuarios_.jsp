<%@ page contentType="text/html; charset=iso-8859-1" language="java"  errorPage="" %>
<%@ page import="java.sql.*,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO"%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List listausu= (List) request.getAttribute("listausu");
int col=0;
%>

<html>
<head>
<title>Usuarios</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<div class="box boxpost">
<h4>Usuarios de la Intranet</h4>

  <%if(listausu!=null){
Iterator i= listausu.iterator();
  request.setAttribute("usuarioss",i);

  %>
        <display:table name="usuarioss" class="table table-striped table-bordered table-hover tablesorter" requestURI="usuarioAction.do" id="usus" pagesize="50">
        <display:column sortable="true" title="Nombre" href="usuarioAction.do?hacia=verDatosUsuario" paramId="id_usu" paramProperty="idUsu" >
        <% if(((UsuarioVO)usus).getNombreUsu2()!=null){%>
        <%=((UsuarioVO)usus).getNombreUsu()+" "+ ((UsuarioVO)usus).getNombreUsu2()+" "+((UsuarioVO)usus).getApellUsu()%>
        <%}else{ %>
        <%=((UsuarioVO)usus).getNombreUsu()+" "+((UsuarioVO)usus).getApellUsu()%>
        <%} %>
        </display:column>
        <display:column title="UserName" sortable="true" > <%=((UsuarioVO)usus).getUsername() %> </display:column>
        <display:column title="Area" sortable="true"> <%=((UsuarioVO)usus).getArea() %> </display:column>
        <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagination\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagination\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
        <display:setProperty name="export.csv" value="false"/> <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.rtf" value="false"/> <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/> </display:table>
        <%} %>
      
  </div>
  
<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>

</body>
</html>
