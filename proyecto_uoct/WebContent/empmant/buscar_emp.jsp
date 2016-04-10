<%@page contentType="text/html; charset=iso-8859-1" language="java"
	import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"
	errorPage=""%>
<%@ page
	import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
	List listaempresas = (List) request.getAttribute("listaempresas");
	Integer var_vigente = (Integer) request.getAttribute("vigente");

	Integer cero = new Integer(0);
	Integer uno = new Integer(1);
	Integer dos = new Integer(2);
%>

<!DOCTYPE html>
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


<script language="JavaScript" type="text/javascript">
	function valida_envia() {
		document.form_buscar.submit();
	}
	function popUp(URL) {
		day = new Date();
		id = day.getTime();
		eval("page"
				+ id
				+ " = window.open(URL, '"
				+ id
				+ "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left = 440,top = 412');");
	}
</script>


</head>

<body>
	<div class="preheader headerlog">
		<div class="container">
			<div class="row">
				<div class="col-sm-3 col-xs-3">
					<a href="index_logeado.html">
						<h1>
							UOCT | <span>Intranet</span>
						</h1>
					</a>
				</div>

			</div>
		</div>
	</div>

	<div class="main">
		<div class="container">
			<div class="row clearfix">


				<div class="col-sm-6 desarrollo">

					<h2>Empresa Mantenedora</h2>

					<div class="box boxpost">
						<h4>Buscar Empresas</h4>
						<form class="form-horizontal" id="form1" class="form-horizontal"
							action="../empmant/empMantAction.do">
							<div class="form-group">
								<div class="col-sm-12">
									<select class="form-control" name="vigente" size="1"
										id="vigente">
										<%
											if (dos.equals(var_vigente)) {
												out.print("<option value='2' selected>Todas</option>");
												out.print("<option value='1'>Vigente</option>");
												out.print("<option value='0'>No vigente</option>");
											} else {
												if (uno.equals(var_vigente)) {
													out.print("<option value='1' selected>Vigente</option>");
													out.print("<option value='2'>Todas</option>");
													out.print("<option value='0'>No vigente</option>");
												} else {
													if (cero.equals(var_vigente)) {
														out.print("<option value='0' selected>No vigente</option>");
														out.print("<option value='2'>Todas</option>");
														out.print("<option value='1'>Vigente</option>");
													} else {
														if (var_vigente == null) {
										%>

										<%
											}
													}
												}
											}
										%>
									</select>
								</div>
							</div>
							<div class="boxOpciones">
								<div class="form-group">
									<div class="col-sm-12">
										<input name="hacia" type="hidden" value="Buscar empresa">
										<a OnClick="submitThisForm1();" href="javascript:void(0)"
											class="botoVerde"> Buscar Empresa </a>

										<script type="text/javascript">
											function submitThisForm1() {

												var formulario = $('#form1');

												var action = 'empmant/empMantAction.do'
												SubmitFormulario(action,
														formulario);

											}

											function Llamadalink(hacia, link) {
												link = link.replace('#', '');
												link = 'documentacion/clienteAction.do'
														+ link
														+ '&hacia='
														+ hacia;
												//alert(link+' ' +param);
												LlamadaPagina(link);

												//clienteAction.do?hacia=detalleEntExt
											}
										</script>
									</div>
								</div>
							</div>
						</form>
					</div>

					<div class="box encuentra">
						<h4>Listado de Empresas</h4>
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>N</th>
									<th>Empresa</th>
									<!-- <th>Vig.</th>
          							<th>Tipo dispos.</th>
          							<th>Mail según tipo de dispositivo en mantención</th>
          							<th class="col-sm-3">Opciones</th> -->
								</tr>
							</thead>
							<tbody>
								<!-- empresa-->
								<%
									if (listaempresas != null) {
										Iterator ile = listaempresas.iterator();
										int indice = 0;
										String nomAnterior = "";
										String mail_2;
										while (ile.hasNext()) {
											EmpMantVO empmant = (EmpMantVO) ile.next();
								%>


								<tr>
									<td rowspan="2" bgcolor="#ADD8E4" align="left"><div
											align="right">
											<font size="1"><strong> <%
 	if (empmant.get_nombre().equals(nomAnterior)) {
 				out.print("&nbsp;");
 				out.print("</strong></font></td><td rowspan='2' align='center' >");
 				out.print("&nbsp;");
 			} else {
 				indice++;
 				out.print(indice);
 				out.print("</strong></font></td><td rowspan='2' align='center' >");
 %>
													<a
													href="empMantAction.do?hacia=ver_emp&&id_emp=<%out.print(empmant.get_id_empresa());%>"
													title="Editar empresa">Editar</a> <%
 	}
 %></td>
									<td rowspan="2" align="left"><font size="1"> <%
 	if (empmant.get_nombre().equals(nomAnterior)) {
 				out.print("&nbsp;");
 			} else {
 %> <a
											href="javascript:popUp('http://localhost:8090/WM_1/empmant/empMantAction.do?hacia=popUp&&nombre=<%out.print(empmant.get_nombre());%>&&direccion=<%out.print(empmant.get_direccion());%>&&telefono=<%out.print(empmant.get_telefono());%>&&vigencia=<%out.print(empmant.get_vigente());%>&&tipo_dispositivo=<%out.print(empmant.get_tipo_dispositivo());%>&&mail_1=<%out.print(empmant.get_mail_1());%>&&tipo_dispositivo2=<%out.print(empmant.get_tipo_dispositivo2());%>&&mail_2=<%out.print(empmant.get_mail_2());%>')">
												<%
													out.print(empmant.get_nombre());
												%>
										</a> <%
 	}
 %>

									</font></td>

									<!-- <td rowspan="2" align="left" ><font size="1">
                    <%if (empmant.get_nombre().equals(nomAnterior)) {
						out.print("&nbsp;");
					} else {
						if (empmant.get_vigente().intValue() == 1) {
							out.println("SI");
						}
						if (empmant.get_vigente().intValue() == 0) {
							out.println("NO");
						}
					}%></font>
                  </td>
                  <td align="left" ><font size="1">
                    <%out.println(empmant.get_tipo_dispositivo());%></font>
                  </td>
                  <td align="left" ><font size="1">
                    <%out.println(empmant.get_mail_1());%></font>
                  </td>
                </tr>
                <tr>
                  <td align="left" ><font size="1">
                    <%out.println(empmant.get_tipo_dispositivo2());%></font>
                  </td>
                  <td align="left" ><font size="1">
                    <%out.println(empmant.get_mail_2());%></font>
                  </td>
                </tr>
                <%nomAnterior = empmant.get_nombre();
				}
			}%> -->
</tr>

          						
          						
        						</tbody>
    						</table>
    					</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
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
				<script src="js/moment.js"></script>
				<script src="js/truncate.js"></script>
				<script src="js/fullcalendar.min.js"></script>
				<script src="js/uoct.js"></script>
</body>
</html>
