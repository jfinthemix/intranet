<%@page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.DocumentodeListaVO"
	errorPage=""%>
<%@ page
	import="java.text.SimpleDateFormat,proyecto_uoct.documentacion.VO.TipoDocVO"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

			List listausu = (List) request.getAttribute("listausu");
			List tiposentrantes = (List) request.getAttribute("tiposentrantes");
			List tipossalientes = (List) request.getAttribute("tipossalientes");
			List listadocs = (List) request.getAttribute("listadocs");
			Iterator iv = tiposentrantes.iterator();
			Iterator iiv = tipossalientes.iterator();

			String mensaje = (String) request.getAttribute("mensaje");

			if (listadocs != null && listadocs.size() == 0) {
				mensaje = "No hay documentos coincidentes con los parámetros ingresados";
			}
%>
<html lang="es">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Intranet de la UOCT">
<meta name="author" content="Unidad Operativa de Control de Tránsito">
<link rel="icon" href="img/favicon.ico">

<title>Unidad Operativa de Control de Tránsito</title>

<link href="recursos/css/grid.css" rel="stylesheet">
<link href="recursos/css/glyphs.css" rel="stylesheet">
<link href="recursos/css/style.css" rel="stylesheet">


<link
	href="<%=request.getContextPath()%>${helpPath}/recursos/css/grid.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>${helpPath}/recursos/css/glyphs.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>${helpPath}/recursos/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>${helpPath}/recursos/css/fullcalendar.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>${helpPath}/recursos/css/timepicker.css"
	rel="stylesheet">

<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/jquery-1.12.0.min.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/bootstrap.min.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/moment.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/truncate.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/bootstrap-datepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/jquery.tablesorter.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/jquery.timepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/CalendarPopup.js"></script>
<script
	src="<%=request.getContextPath()%>${helpPath}/recursos/js/uoct.js"></script>




<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>


<body>

	<script type="text/javascript">
		function pasaDoc(idDoc, codDoc, tipoDoc) {
			window.opener.pasaDoc(idDoc, codDoc, tipoDoc);
		}
		
		function submitThisForm1() {
			var formulario = $('#buscarDocs');
			var action = 'eiv/eivAction.do';
			document. buscarDocs.submit();

		}
		
	</script>

	<div class="main">
		<div class="container">
			<div class="row clearfix">



				<div class="col-sm-8 desarrollo box boxpost">
					<h3>Buscar Documento</h3>
					<form action="eivAction.do" class="form-horizontal">
						<input type="hidden" name="hacia" value="buscarDocOficio" />
						 <input	name="tipoBus" type="hidden" value="1">
						<div class="form-group">
							<label for="inputCodigo" class="col-sm-2 control-label">Código</label>
							<div class="col-sm-3 guionpost">
								<select class="form-control" id="inputCodigo1" name="idTipoDoc">

									<%
									    while (iv.hasNext()) {// lista los tipos de documentos entrantes
											TipoDocVO tipo = (TipoDocVO) iv.next();

											if (tipo.getTipo().equalsIgnoreCase("oficio")) {
											    out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
											}

											if (tipo.getTipo().equalsIgnoreCase("ordinario")) {
											    out.println("<option value=" + tipo.getIdTipo() + ">" + tipo.getTipo() + "</option>");
											}
									    }
									%>
								</select>
							</div>
							<div class="col-sm-4">
								<input class="form-control" id="codigoDoc" type="text"	maxlength="50" name="codigoDoc">
							</div>
						</div>
						<div class="form-group">
							<label for="inputMateria" class="col-sm-2 control-label">Materia</label>
							<div class="col-sm-8">
								<input class="form-control" id="inputMateria" name="materia"
									type="text">
							</div>
						</div>
						<div class="form-group input-daterange">
							<label for="inputFecha" class="col-sm-2 control-label">Fecha</label>
							<div class="col-sm-1" name="fecha_ini">
								<label class="control-label">Desde:</label>
							</div>
							<div class="col-sm-3">
								<input class="form-control inputFecha" id="inputDesde" name="fecha_ini"
									type="text">
							</div>
							<div class="col-sm-1">
								<label class="control-label">Hasta:</label>
							</div>
							<div class="col-sm-3">
								<input class="form-control inputFecha" name="fecha_fin"
									id="fecha_fin" type="text">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPalabra" class="col-sm-2 control-label">Palabra
								clave</label>
							<div class="col-sm-8">
								<input class="form-control" name="palClave" id="palClave"
									type="text"> <span id="helpBlock" class="help-block">Palabra
									que se buscará en el nombre y apellido del cliente, mat. y
									resumen del doc.</span>
							</div>
						</div>
						<div class="form-group">
							<label for="selectEncargado" class="col-sm-2 control-label">Encargado</label>
							<div class="col-sm-6">
								<select class="form-control" name="id_usuario" id="id_usuario">

									<option selected value="0">nombre del usuario</option>
									<%
									    Iterator ius = listausu.iterator();
									    while (ius.hasNext()) {
											UsuarioVO usu = (UsuarioVO) ius.next();
											if (usu.getIsActivo()) {
											    if (usu.getNombreUsu2() != null) {
												out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " "
													+ usu.getNombreUsu2() + " " + usu.getApellUsu() + "</option>");
											    } else {
												out.println("<option value=" + usu.getIdUsu() + ">" + usu.getNombreUsu() + " "
													+ usu.getApellUsu() + "</option>");
											    }
											}
									    }
									%>
								</select>
							</div>
						</div>
						<div class="boxOpciones">
						
								<div class="col-sm-4">
								
											<input type="submit" class="botoVerde busca" value="Buscar">
								
								</div>
						
						</div>
					</form>



				

						<%
						    if (listadocs != null && listadocs.size() > 0) {
						%>
							<div class="box boxpost">
						<h3>Seleccione un documento de la siguiente lista</h3>
						<display:table name="listadocs"
							class="table table-striped table-bordered table-hover" id="ld"
							pagesize="15" requestURI="eiv/eivAction.do">
							<%
							    if (((DocumentodeListaVO) ld).getTipoDoc().toUpperCase().equals("OFICIO")
										    || ((DocumentodeListaVO) ld).getTipoDoc().toUpperCase().equals("ORDINARIO")) {
							%>

							<display:column title="Seleccionar" maxLength="50"
								sortable="true" sortProperty="codDoc">
								<a href="#"
									onClick="return pasaDoc('<%=((DocumentodeListaVO) ld).getIdDoc()%>','<%=((DocumentodeListaVO) ld).getCodDoc()%>','<%=((DocumentodeListaVO) ld).getTipoDoc()%>')"><%=((DocumentodeListaVO) ld).getTipoDoc()%>-<%=((DocumentodeListaVO) ld).getCodDoc()%></a>
							</display:column>

							<display:column title="Materia" sortable="true"
								property="materiaDoc" maxWords="5">
							</display:column>

							<display:column title="Fecha ingreso/egreso" sortable="true"
								sortProperty="fechaio">
								<%=sdf.format(((DocumentodeListaVO) ld).getFechaio())%>
							</display:column>

							<display:column title="Estado" sortable="true"
								sortProperty="idEstado">
								<%
								    if (((DocumentodeListaVO) ld).getIdEstado()) {
												out.println("Respondido");
											    } else {
												out.println("Pendiente");
											    }
								%>
							</display:column>
							<%
							    }
							%>


							<display:setProperty name="basic.msg.empty_list"
								value="No se encontraron elementos para mostrar" />
							<display:setProperty name="basic.empty.showtable" value="true" />
							<display:setProperty name="basic.msg.empty_list_row"
								value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>" />
							<display:setProperty name="export.banner"
								value="<div class='exportlinks'>Exportar: {0}</div>" />
							<display:setProperty name="paging.banner.placement"
								value="bottom" />
							<display:setProperty name="paging.banner.no_items_found"
								value="<span class='pagebanner'>&nbsp;</span>" />
							<display:setProperty name="paging.banner.one_item_found"
								value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>" />
							<display:setProperty name="paging.banner.all_items_found"
								value="<span class='pagebanner'>{0} {1} encontrados.</span>" />
							<display:setProperty name="paging.banner.some_items_found"
								value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>" />
							<display:setProperty name="paging.banner.full"
								value="<span class=\"pagelinks\">[<a
									href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a
									href=\"{4}\">Ultimo</a>]"></display:setProperty>
							<display:setProperty name="paging.banner.first"
								value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a
									href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
							<display:setProperty name="paging.banner.last"
								value="	<span class=\"pagelinks\">[<a
									href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
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



						</display:table>

					</div>
					</form>
					</div>
					<%
					    }
					%>
				
</body>
</html>

