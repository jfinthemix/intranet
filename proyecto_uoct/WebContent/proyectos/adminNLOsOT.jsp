<%@ page import="proyecto_uoct.proyecto.VO.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleOTVO ot=(DetalleOTVO) request.getAttribute("detOT");
%>
<html>
<head>
<title>
Admin. NLOs
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script type="text/javascript">

function confirmaEliminacion(){
  alert("Advertencia:La operación de eliminar una NLO no se puede deshacer");
var resp=confirm("Al eliminar la NLO se conserva el registro del documento con que fue registrada .\n¿Está seguro de eliminar esta NLO? ");
return resp;
}

</script>


</head>
<body bgcolor="#ffffff">
<div align="left">
  <h3>Lista de NLO de la OT:<%=ot.getNomOT() %> </h3>
    <%
if(ot.getNLOs().size()!=0){
request.setAttribute("nsot",ot.getNLOs());
%>
  <p align="right"><a href="proyectoAction.do?hacia=aRegNLOOT&idOT=<%=ot.getIdOT()%>">
          Registrar NLO </a></p>
  <display:table class="its" name="nsot" pagesize="15" id="ns" requestURI="proyectoAction.do">
  <display:column title="ID NLO" property="id"> </display:column>
   <display:column title="Fecha de la NLO"><%=sdf.format(((NLOVO)ns).getFechaNLO())%> </display:column>
    <display:column title="Descripción NLO" property="str" maxWords="10">
  </display:column>
   <display:column title="Editar"> <a href="proyectoAction.do?hacia=editarNLO&idOT=<%=ot.getIdOT() %>&idNLO=<%=((NLOVO)ns).getId()%>">Editar
  NLO</a> </display:column>
  <display:column title="Eliminar"> <a href="proyectoAction.do?hacia=eliminarNLO&idNLO=<%=((NLOVO)ns).getId()%>" onclick="return confirmaEliminacion();" >Eliminar
  NLO</a> </display:column>

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
</div>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
