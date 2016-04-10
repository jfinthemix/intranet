<!DOCTYPE html>

<%@page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.util.*,proyecto_uoct.mantenedorDispositivos.VO.*,proyecto_uoct.mantenedorSubsistemas.VO.*,java.util.Iterator"
	errorPage=""%>
<%@ page
	import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
	String nombreSubsistema = (String) request.getAttribute("nombreSubsistema");
	if (nombreSubsistema == null) {
		nombreSubsistema = "CAMARAS";
	}

	//System.out.println("nombreSubsistema: "+nombreSubsistema);
	String mensaje = (String) request.getAttribute("mensaje");
	Integer idPerfil = (Integer) request.getAttribute("idPerfil");
	LinkedList listaSubsistema = new LinkedList();
	listaSubsistema = (LinkedList) request.getAttribute("listaSubsistema");
	//Integer idAdmin                               = new Integer(1);
	//Integer idGFallas                             = new Integer(43);

	// if(mensaje!="ok" && mensaje !=null){
%>

<META HTTP-EQUIV="Refresh"
	CONTENT="3; URL=../mantenedorDispositivos/dispositivoAction.do?hacia=Buscar&mensaje='ok'&subsistema=<%=nombreSubsistema%>">
<%
	// } 

	//Integer var_subsistema                       = (Integer) request.getAttribute("lista_subsistema");
	//Integer var_empresa                          = (Integer) request.getAttribute("lista_empresa");

	//LinkedList listaSubsistema                   = new LinkedList();
	//listaSubsistema                              = (LinkedList) request.getAttribute("listaSubsistema");
	//LinkedList listaEmpresa                      = new LinkedList();
	//listaEmpresa                                 = (LinkedList) request.getAttribute("listaEmpresa");
	LinkedList listaDispositivo = new LinkedList();
	listaDispositivo = (LinkedList) request.getAttribute("listaDispositivo");

	Integer cero = new Integer(0);
%>

<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Intranet de la UOCT">
<meta name="author" content="jfanasco">
<link rel="icon" href="img/favicon.ico">

<title>Unidad Operativa de Control de Tránsito</title>

<link href="css/grid.css" rel="stylesheet">
<link href="css/glyphs.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/datepicker.css" rel="stylesheet">

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->


<script language="JavaScript" src="../util/valid/gen_validatorv2.js"
	type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
	function valida_envia() {
		document.formMantenedorDispositivo.submit();
	}

	function confirmaEliminacion() {
		var resp = confirm("Al eliminar este dispositivo se eliminará todo lo asociado a él.\n ¿Desea continuar con la eliminación?");
		return resp;
	}
</script>


</head>

<body>


	<div class="main">
		<div class="container">
			<div class="row clearfix">



				<div class="col-sm-6 desarrollo">

					<h2>Mantenedor de Dispositivos</h2>

					<font color="red"> <%
 	if (mensaje != null)
 		out.print(mensaje);
 %></font>



					<div class="box boxpost boxpostdoble">
						<h4>
							Dispositivos <span class="pull-right"><small>Exportar:</small>
								<a href="javascript:void(0)" class="pull-right"><img
									src="img/ico_excel.png"></a> <a href="javascript:void(0)"
								class="pull-right"><img src="img/ico_pdf.png"></a></span>
						</h4>
						<form id="form1" class="form-horizontal" action="../mantenedorDispositivos/dispositivoAction.do">
						<div class="form-group">
							<div class="col-sm-12">
								<input name="accion" value="1" type="hidden">
								<input name="hacia" value="Buscar" type="hidden">
								 <label	for="selectTipo" class="col-sm-4 control-label">Tipo
									dispositivo</label> <select name="subsistema" size="1" id="subsistema">
									<%
										//out.println("<option value=\"" + nombreSubsistema + "\" selected>" + nombreSubsistema + "</option>");
										Iterator i = listaSubsistema.iterator();
										while (i.hasNext()) {
											SubsistemaVO subs = (SubsistemaVO) i.next();
											if (nombreSubsistema.equals(subs.getNombreSubsistema())) {
												out.println(	
														"<option value=\"" + nombreSubsistema + "\" selected>" + nombreSubsistema + "</option>");
											}
											out.println("<option value=\"" + subs.getNombreSubsistema() + "\" >" + subs.getNombreSubsistema()
													+ "</option>");
										}
									%>
								</select>

							
									
									<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> Buscar</a>



							</div>
						</div>
						
						
						
						<script  type="text/javascript" >
		function submitThisForm1() {

			var formulario = $('#form1');
			
			var action = 'mantenedorDispositivos/dispositivoAction.do'
			SubmitFormulario(action, formulario);
			

		}

		function Llamadalink(hacia, link) {
			link = link.replace('#', '');
			link = 'documentacion/clienteAction.do' + link + '&hacia=' + hacia;
			//alert(link+' ' +param);
			LlamadaPagina(link);

			//clienteAction.do?hacia=detalleEntExt
		}
	</script>
					
						
						
						
						</form>

<br>

						<display:table name="listaDispositivo" export="true"
							class="table table-striped table-bordered table-hover"
							id="ld" pagesize="30" requestURI="dispositivoAction.do">

							<display:column media="html excel pdf" class="centrado"
								title="ID " sortable="true" sortProperty="nombre"><%=((DispositivoVO) ld).getNombre()%></display:column>
							<display:column media="html excel pdf" class="centrado"
								title="descripcion" sortable="true" sortProperty="descripcion"><%=((DispositivoVO) ld).getDescripcion()%></display:column>
							<display:column media="html excel pdf" class="centrado"
								title="subsistema asociado" sortable="true"
								sortProperty="nombreSubsistema"><font size="2"><%=((DispositivoVO) ld).getNombreSubsistema()%></font></display:column>
							<display:column media="html excel pdf" class="centrado"	title="empresa mantenedora" sortable="true"	sortProperty="nombreEmpresa"><%=((DispositivoVO) ld).getNombreEmpresa()%></display:column>
							<!-- <display:column media="html excel pdf" class="centrado"	title="tipo de dispositivo" sortable="true"	sortProperty="tipoDispositivo"><%=((DispositivoVO) ld).getTipoDispositivo()%></display:column> -->
							<display:column media="html" class="centrado" title="modificar" sortable="false" sortProperty="modificar"> <a href="dispositivoAction.do?hacia=modificar&&id_dispositivo=<%out.print(((DispositivoVO) ld).getIdDispositivo());%>&&nombre=<%out.print(((DispositivoVO) ld).getNombre());%>&&descripcion=<%out.print(((DispositivoVO) ld).getDescripcion());%>&&id_subsistema=<%out.print(((DispositivoVO) ld).getIdSubsistema());%>&&nombre_subsistema=<%out.print(((DispositivoVO) ld).getNombreSubsistema());%>&&id_empresa=<%out.print(((DispositivoVO) ld).getIdEmpresa());%>&&nombre_empresa=<%out.print(((DispositivoVO) ld).getNombreEmpresa());%>&&id_tipo_dispositivo=<%out.print(((DispositivoVO) ld).getIdTipoDispositivo());%>&&tipo_dispositivo=<%out.print(((DispositivoVO) ld).getTipoDispositivo());%>" title="Modificar dispositivo"><img
									src="/imagenes/editar.jpg" width="17" height="17" alt=""	border="0"></a>	</display:column>

							<%
								//if(idAdmin.equals(idPerfil) || idGFallas.equals(idPerfil)){
									if (idPerfil.equals(cero)) {
										idPerfil = idPerfil;
									} else {
							%>
							<display:column media="html" class="centrado" title="eliminar"
								sortable="false" sortProperty="eliminar">
								<a
									href="dispositivoAction.do?hacia=eliminar&&id_dispositivo=<%out.print(((DispositivoVO) ld).getIdDispositivo());%>"
									title="Eliminar dispositivo"
									onClick='return confirmaEliminacion()'><img
									src="imagenes/icono_delete.png" alt="" width="17" height="17"
									border="0"></a>
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
								value="ListaDispositivo.xls" />
							<display:setProperty name="export.pdf.filename"
								value="ListaDispositivo.pdf" />
							<display:setProperty name="export.xml.filename"
								value="ListaDispositivo.xml" />
							<display:setProperty name="export.csv.filename"
								value="ListaDispositivo.csv" />
							<display:setProperty name="export.pdf.label"
								value="<img src='../util/img/pdf.gif' width='10' height='10' border='0'>" />
							<display:setProperty name="export.excel.label"
								value="<img src='../util/img/excel.gif' width='10' height='10' border='0'>" />
							<display:setProperty name="export.amount" value="list" />
						</display:table>

					
						
					</div>
					<script language="JavaScript" type="text/javascript">
						var frmvalidator = new Validator(
								"formMantenedorDispositivo");
					</script>
					<div class="verMas">
						<a href="javascript:history.back()"><span
							class="glyphicons glyphicons-undo"></span> Volver</a> <a
							href="javascript:void(0)" class="pull-right"><span
							class="glyphicons glyphicons-circle-exclamation-mark"></span>
							Ayuda</a>
					</div>


				</div>


			</div>
			<!-- /row -->


		</div>
		<!-- /container -->

	</div>
	<!-- /main -->


	<div class="container">
		<footer>
			<div class="row">
				<div class="col-sm-12">
					<p>
						Unidad Operativa de Control de Tránsito, <span id="pie"></span>
					</p>
				</div>
			</div>
		</footer>
	</div>
	<!-- /container -->


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/bootstrap-datepicker.es.min.js"></script>
	<script src="js/jquery.tablesorter.js"></script>
	<script src="js/moment.js"></script>
	<script src="js/truncate.js"></script>
	<script src="js/fullcalendar.min.js"></script>
	<script src="js/uoct.js"></script>
	<!-- <script src="js/uoct_falla1.js"></script> -->



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
