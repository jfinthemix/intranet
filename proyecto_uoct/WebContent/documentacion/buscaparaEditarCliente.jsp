<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, java.util.List, java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.CliEntVO" errorPage=""%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
  List clientes = (List) request.getAttribute("listaCliEnt");
  int col = 0;
%>
<html>
<head>
<title>Buscar cliente</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script type="text/javascript">

function popUp(href, target, features) {
  reservar = window.open(href, target, features);
  reservar.window.focus();
}
</script></head>
<body>

<h2>Editar cliente</h2>

<div class="box clearfix">
<h4>Buscar cliente</h4>
<div class="col-md-6 searchtab">

<form action="clienteAction.do" method="POST">
<div class="form-group">
        <input type="hidden" name="hacia" value="buscarparaEditarCli"/>
                    
  										<label for="inputBusca1">
  											<span class="glyphicons glyphicons-search"></span><br>
  											Por palabra clave*<br>
  											<small>*Nombre, apellido, dirección, entidad, etc.</small>
  										</label>
    									<input name="palClave" type="text" class="form-control" id="inputBusca1">
  									</div>
  									<a class="botoVerde busca" href="javascript:void(0)"><span class="glyphicons glyphicons-search"></span> Buscar</a>
</form>

</div>
<div class="col-md-6 searchtab">
				 				<form action="clienteAction.do">
  									<div class="form-group">
  										<label for="inputBusca2">
  											<span class="glyphicons glyphicons-search"></span><br>
  											Clientes particulares
  										</label>
  										<input type="hidden" name="hacia" value="buscarparaEditarCli">
                          				<input name="Particulares" type="text" class="form-control" id="inputBusca2">
  									</div>
  									<a class="botoVerde busca" href="javascript:void(0)"><span class="glyphicons glyphicons-search"></span> Buscar</a>
								</form>
				 			</div>          
   </div>           
           
          <%
  if (clientes != null) {
  Iterator i = clientes.iterator();
  request.setAttribute("clientes_l",i);
  %>
      	<div class="box boxpost encuentra">  
        
        <display:table class="table table-striped table-bordered table-hover tablesorter" pagesize="15" name="clientes_l" id="clis" requestURI="clienteAction.do">
          <display:caption>Clientes encontrados </display:caption>


		 <display:setProperty name="basic.empty.showtable" value="true"/> <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/> <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty> 
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty> 
        <display:setProperty name="export.csv" value="false"></display:setProperty>
        <display:setProperty name="export.xml" value="false"/> <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.xml.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.csv.filename" value="registroDocumentacion"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10'>"/>
        <display:setProperty name="export.amount" value="list"/>




		  <display:column title="Nombre del Cliente" sortProperty="nomCli" sortable="true" href="clienteAction.do?hacia=detalleCliente" paramId="id_cli" paramProperty="idCli">
          <%=((CliEntVO)clis).getNomCli()+" "+((CliEntVO)clis).getApeCli() %> </display:column> <display:column title="Entidad externa">
          <%if(((CliEntVO)clis).getNomEnt()!=null){ %>
          <%=((CliEntVO)clis).getNomEnt()%>
          <% }else{%>
          <%="&nbsp;" %>
          <% }%>
          </display:column> <display:column title="Estado">
          <% if(((CliEntVO)clis).getIsActivo().intValue()==1){%>
          <%="Activado"%>
          <%}
      if(((CliEntVO)clis).getIsActivo().intValue()==0){%>
          <%="Desactivado"%>
          <%}%>
          </display:column> <display:column title="Editar" href="clienteAction.do?hacia=aeditarDatosCli" paramId="idCli" paramProperty="idCli" >Editar
          </display:column> </display:table>
          <%}%>
 
</div>
</body>
</html>
