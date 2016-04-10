<%@page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.sql.*, java.util.List, java.util.Iterator,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.CliEntVO"
	errorPage=""%>
<%@taglib prefix="display" uri="/displaytag_12"%>

<%
	List clientes = (List) request.getAttribute("listaCliEnt");
%>
<html>
<head>
<title>Buscar cliente</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" type="text/javascript">
	function popUp(href, target, features) {
		reservar = window.open(href, target, features);
		reservar.window.focus();
	}
</script>
</head>
<body>

	<h2>Buscar clientes</h2>
	<div class="box clearfix">
		<h4>Selecciona una de las tres formas:</h4>

		<div class="col-md-6 searchtab tabMini">
			<form id="form1" action="clienteAction.do">
				<input type="hidden" name="hacia" value="buscarCli" /> <input
					type="hidden" name="PorPalabra" value="PorPalabra" />
				<div class="form-group">
					<label for="inputBusca1"> <span
						class="glyphicons glyphicons-search"></span><br> Por palabra
						clave<br>
					</label> <input type="text" name="palClave" class="form-control"
						id="inputBusca1">
				</div>
				<a OnClick="submitThisForm1();" class="botoVerde busca"
					name="PorPalabra" href="javascript:void(0)">Buscar</a>
			</form>
		</div>

		<div class="col-md-6 searchtab tabMini">
			<form id="form2" action="clienteAction.do">
				<input type="hidden" name="hacia" value="buscarCli"> <input
					type="hidden" name="Particulares" value="Particulares" />
				<div class="form-group">
					<label> <span class="glyphicons glyphicons-eye-open"></span><br>
						Clientes particulares
					</label>
				</div>
				<a OnClick="submitThisForm2();" class="botoVerde busca"
					name="Particulares" href="javascript:void(0)">Ver</a>
			</form>
		</div>
	</div>


	<%
		if (clientes != null) {
			Iterator icl = clientes.iterator();
			request.setAttribute("icl", icl);
	%>
	<div id="divResultado" class="box boxpost">

		<h4>
			Clientes encontrados <span class="pull-right"><small>Exportar:</small>
				<a href="javascript:void(0)" class="pull-right"><img
					src="recursos/img/ico_excel.png"></a> <a
				href="javascript:void(0)" class="pull-right"><img
					src="recursos/img/ico_pdf.png"></a></span>
		</h4>
		<display:table name="icl" export="true" pagesize="15"
			requestURI="clienteAction.do" id="icls"
			class="table table-striped table-bordered table-hover tablesorter">
			<display:caption>Lista de Clientes </display:caption>

			<display:column sortable="true" media="html" sortProperty="nomCli"
				title="Cliente" href="javascript:Llamadalink('detalleCliente','#')"
				paramId="id_cli" paramProperty="idCli">
				<%=((CliEntVO) icls).getNomCli() + " " + ((CliEntVO) icls).getApeCli()%>
			</display:column>

			<display:column media="excel pdf" title="Cliente">
				<%=((CliEntVO) icls).getNomCli() + " " + ((CliEntVO) icls).getApeCli()%>
			</display:column>

			<display:column media="html" sortable="true" title="Entidad externa"
				href="javascript:Llamadalink('detalleEntExt','#')" paramId="id_ent"
				paramProperty="idEnt">
				<%
					if (((CliEntVO) icls).getNomEnt() != null) {
				%>
				<%=((CliEntVO) icls).getNomEnt()%>
				<%
					}
				%>
			</display:column>


			<display:column media="excel pdf" title="Entidad externa">
				<%
					if (((CliEntVO) icls).getNomEnt() != null) {
				%>
				<%=((CliEntVO) icls).getNomEnt()%>
				<%
					}
				%>
			</display:column>

			<display:setProperty name="basic.msg.empty_list"
				value="No se encontraron elementos para mostrar" />
			<display:setProperty name="basic.empty.showtable" value="true" />
			<display:setProperty name="basic.msg.empty_list_row"
				value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>" />
			<display:setProperty name="export.banner"
				value="<div class='exportlinks'>Exportar: {0}</div>" />
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:setProperty name="paging.banner.no_items_found"
				value="<span class='pagebanner'>&nbsp;</span>" />
			<display:setProperty name="paging.banner.one_item_found"
				value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>" />
			<display:setProperty name="paging.banner.all_items_found"
				value="<span class='pagebanner'>{0} {1} encontrados.</span>" />
			<display:setProperty name="paging.banner.some_items_found"
				value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>" />
			<display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a
					href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a
					href=\"{4}\">Ultimo</a>]"></display:setProperty>
			<display:setProperty name="paging.banner.first"
				value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a
					href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
			<display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a
					href=\"{1}\">Primero</a>/<a href=\"{2}\">Pr</a>
				<a href=\"{2}\">ev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
			<display:setProperty name="export.csv" value="false" />
			<display:setProperty name="export.xml" value="false" />
			<display:setProperty name="export.rtf" value="false" />
			<display:setProperty name="export.excel.filename"
				value="registroDocumentacion" />
			<display:setProperty name="export.pdf.filename"
				value="registroDocumentacion" />
			<display:setProperty name="export.xml.filename"
				value="registroDocumentacion" />
			<display:setProperty name="export.csv.filename"
				value="registroDocumentacion" />
			<display:setProperty name="export.pdf.label"
				value="<img src='../util/img/pdf.gif' width='10' height='10'>" />
			<display:setProperty name="export.excel.label"
				value="<img src='../util/img/excel.gif' width='10' height='10'>" />
			<display:setProperty name="export.amount" value="list" />
			<display:setProperty name="sort.amount" value="list" />
		</display:table>
		<%
			}
		%>

	</div>


	<div class="verMas">
		<a href="javascript:history.back()"><span
			class="glyphicons glyphicons-undo"></span> Volver</a> <a
			href="javascript:void(0)" class="pull-right"><span
			class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
	</div>


	<script>
		function submitThisForm1() {

			var formulario = $('#form1');
			var action = 'documentacion/clienteAction.do';
			SubmitFormulario(action, formulario);
			$('#divResultado').removeClass("encuentra");

		}

		function submitThisForm2() {

			var formulario = $('#form2');
			var action = 'documentacion/clienteAction.do';
			SubmitFormulario(action, formulario);
			$('#divResultado').removeClass("encuentra");
		}

		function Llamadalink(hacia, link) {
			link = link.replace('#', '');
			link = 'documentacion/clienteAction.do' + link + '&hacia=' + hacia;
			//alert(link+' ' +param);
			LlamadaPagina(link);

			//clienteAction.do?hacia=detalleEntExt
		}
	</script>

</body>
</html>
