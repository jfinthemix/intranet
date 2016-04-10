<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="proyecto_uoct.proyecto.VO.DetalleProyectoVO,java.util.List,java.util.Iterator" errorPage="" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import="proyecto_uoct.proyecto.VO.DocumentodeListaProyVO,proyecto_uoct.proyecto.VO.OTdeListaVO"%>
<%@ page import="proyecto_uoct.proyecto.VO.NLOdeListaVO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");
List enc= detproy.getEquipo();

%>

<html>
<head>
<title>Detalle de Iniciativa</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<div align="center">
  <table width="750" border="0">
    <tr>
      <td colspan="2"><h3>Iniciativa : <%=detproy.getNomProy() %></h3></td>
    </tr>
    <tr>
      <td width="539"><table width="476" border="1" align="left">
          <tr>
            <td width="95" bgcolor="#ADD8E4"><div align="right"><strong>Fecha
                de Inicio: </strong></div></td>
            <td width="254"><%= sdf.format(detproy.getFechaProy()) %></td>
          </tr>
          <tr>
            <td width="95" bgcolor="#ADD8E4"><div align="right"><strong>Encargado:
                </strong></div></td>
            <td width="254"><%= detproy.getEncargado() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Equipo de Trabajo:
                </strong></div></td>
            <td> <%
  if (enc !=null){
  Iterator ienc = enc.iterator();

  while (ienc.hasNext()){
    UsuarioVO u = (UsuarioVO) ienc.next();
   out.print(u.getNombreUsu()+" "+u.getNombreUsu2()+" "+u.getApellUsu()+"</br>");
    }
  }

 %> </td>
          </tr>
          <tr>
            <td width="95" bgcolor="#ADD8E4"><div align="right"><strong>Ejecutor : </strong></div></td>
            <td width="254"><%= detproy.getNomCliente() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Descripci&oacute;n
                de la Iniciativa:</strong></div></td>
            <td> <%
   if (detproy.getDescripcion()!=null && !detproy.getDescripcion().equals("")){
   out.println(detproy.getDescripcion());
   }
   else{out.println("&nbsp;");}
    %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fondos totales:</strong></div></td>
            <td> <%
   if (detproy.getFondosTotales().intValue()!=0){
   out.println(detproy.getFondosTotales());
   }
   else{out.println("&nbsp;");}
    %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fondos Anuales:</strong></div></td>
            <td> <%
   if (detproy.getFondosAnuales()!=null){
   out.println(detproy.getFondosAnuales());
   }
   else{out.println("&nbsp;");}
    %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Estado de la Iniciativa:</strong></div></td>
            <td> <%
   if (detproy.getFondosAnuales()!=null){
   out.println(detproy.getFondosAnuales());
   }
   else{out.println("&nbsp;");}
    %> </td>
          </tr>
          <!--
  <tr>
    <td bgcolor="#ADD8E4"><strong>Fecha de Finalización:</strong></td>
    <td><%
   if (detproy.getFondosAnuales()!=null){
   out.println(detproy.getFondosAnuales());
   }
   else{out.println("&nbsp;");}
    %></td>
  </tr>

-->
          <tr>
            <td colspan="2">&nbsp; </td>
          </tr>
        </table></td>
      <td width="201" valign="top"> <div align="left">
          <%if(detproy.getDocumentos().size()!=0){
      request.setAttribute("docs",detproy.getDocumentos());
      %>
          <display:table name="docs" class="its" pagesize="10" id="dt">
            <display:column title="Documentos de la Iniciativa" property="str" href="proyectoAction.do?hacia=descargarDocIni" paramId="idDoc" paramProperty="id">
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
          <display:setProperty name="export.amount" value="list"/> </display:table>
          <% }else{
       out.print("&nbsp;");
     }
     %>
        </div></td>
    </tr>
    <tr>
      <td colspan="2"> <div align="left">
          <%
 List ots=(List) detproy.getOTs();
 if(ots.size()!=0){
 request.setAttribute("otss",ots);
 %>
          <display:table id="o" name="otss" pagesize="15" requestURI="proyectoAction.do" class="its">
          <display:caption>Ordenes de Trabajo </display:caption>
          <display:column title="ID OT" property="id" sortable="true">
          </display:column>
          <display:column title="Nombre de la OT" property="str" href="proyectoAction.do?hacia=detalleOT" paramId="idOT" paramProperty="id">
          </display:column>
          <display:column title="Fecha Vencimiento" sortable="true"><%= sdf.format(((OTdeListaVO)o).getFechaInicio()) %> </display:column>

          <display:column title="Estado" property="estadoOT">
          </display:column>

          <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/>
          <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
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
          <%}%>
        </div></td>
    </tr>
    <tr>
      <td colspan="2"><div align="right"><a href="proyectoAction.do?hacia=aRegOT&idProy=<%=detproy.getIdProy()%>">Registrar
          nueva OT para la Iniciativa</a></div></td>
    </tr>
    <tr>
      <td colspan="2"><div align="left">
          <%
if(detproy.getNLOs().size()!=0){
request.setAttribute("nsot",detproy.getNLOs());
%>
          <display:table class="its" name="nsot" id="ns" pagesize="15" requestURI="proyectoAction.do">
          <display:caption>NLO de la Iniciativa </display:caption>

          <display:column title="ID NLO" property="id">
          </display:column>

          <display:column title="Descripción NLO" property="str">
          </display:column>

          <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
          <display:setProperty name="basic.empty.showtable" value="true"/>
          <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
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
          <%} %>
        </div></td>
    </tr>
    <tr>
      <td colspan="2"><div align="right"><a href="proyectoAction.do?hacia=aRegNLOdeIni&idProy=<%=detproy.getIdProy()%>">Registrar
          nueva NLO para la Iniciativa</a></div></td>
    </tr>
  </table>

</div>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
