<%@page import="java.util.List, java.util.Iterator, proyecto_uoct.documentacion.VO.EntExtVO" %>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
List ents=(List) request.getAttribute("entidades");

%>
<html>
<head>
<title>
buscarEntExt
</title>


<script type="text/javascript">
function pasaNom_Id(nom,id){
	window.opener.pasaEntExt(nom,id);
}
</script>

</head>
<body >

<h2>Buscar Entidad Externa</h2>

<div  class="box boxpost">

<h4>Ingresa una palabra que esté en el nombre</h4>
    
          <form id="formBuscarEntidadExterna" class="form-horizontal" action="clienteAction.do" method="POST">
          <input type="hidden" name="hacia" value="buscarEntExt" />
          <div class="form-group">
    								<div class="col-sm-12">
      								<input name="nomEntExt" type="text" class="form-control" id="inputBusca">
    								</div>
    							</div>
    							
    							  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
      									<a id="abtnBuscarEntidadExterna" OnClick="submitThisForm();" href="javascript:void(0)" class="busca botoVerde"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
                      
          </form>
        
  <div class="box boxpost">
        <%
if(ents!=null){
Iterator i= ents.iterator();
request.setAttribute("entses",i);
%>
        <display:table name="entses" pagesize="15" requestURI="clienteAction.do" class="table table-striped table-bordered table-hover" id="entss">
        <display:caption>Entidades Encontradas </display:caption> <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]
        {0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
         <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}
        [Sgte/Ultimo]"></display:setProperty>
         <display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/> <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/> <display:column title="Nombre de la Entidad(Seleccionar)" sortable="true">
        <a href="clienteAction.do?hacia=detalleEntExt&id_ent=<%=((EntExtVO)entss).getIdEnt()%>" onClick="return pasaNom_Id('<%=((EntExtVO)entss).getNomEnt()%>','<%=((EntExtVO)entss).getIdEnt()%>')"><%=((EntExtVO)entss).getNomEnt()%></a> </display:column> </display:table>
        <%} %>


</div>

<script>
function submitThisForm()
{
	
	var formulario= $('#formBuscarEntidadExterna');
	var action='documentacion/clienteAction.do';
	SubmitFormulario(action, formulario);
}

</script>


</body>
</html>
