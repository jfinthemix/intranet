<!DOCTYPE html>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="java.util.List,java.util.Iterator,java.util.LinkedList" %>
<%@ page import="proyecto_uoct.usuario.VO.UsuarioVO,proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.proyecto.VO.*,java.text.SimpleDateFormat" %>
<%
List listausu=(List) request.getAttribute("listausu");

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

DetalleProyectoVO detproy= (DetalleProyectoVO) request.getAttribute("detalleProy");
List enc= detproy.getEquipo();




//--------------- separa una lista de responsables(usus_resp) y una lista de usuarios(usus)
List usus_resp = new LinkedList();
List usus = new LinkedList();

if (enc!=null && listausu!=null){
  Iterator ilista = listausu.iterator();//un iterador para la lista completa de usuarios

  while(ilista.hasNext()){// para cada usuario
  boolean esR=false;
  UsuarioVO usu=(UsuarioVO)ilista.next();
  Iterator irespons= enc.iterator();//un iterador para la lista de responsables
  while (irespons.hasNext()){
    UsuarioVO r=(UsuarioVO)irespons.next();
    if (r.getIdUsu().intValue()==usu.getIdUsu().intValue()){
      usus_resp.add(usu);// si el usuario es responsable lo pone en una lista de responsables
      esR=true;
      break;
    }
  }
  if(!esR){
    usus.add(usu);// si no es responsable, lo pone en otra lista
  }
}
}



%>





<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
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
	</head>
	
	<body>
	
<script type="text/javascript">



function pasaCli(nomCli, idCli){
    form_proy.nomCli.value = nomCli;
    form_proy.idCli.value = idCli;
    otra.window.close();
  }

</script>	
	
	
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Editar Iniciativa de Inversión</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de Iniciativa</h4>
				 			<form class="form-horizontal" name="form_proy" id="form_proy" method="post" action="proyectoAction.do">
				 			<input type="hidden" name="hacia" id="hacia" value="actualizarIni">
				 			 <input type="hidden" name="idIni" value="<%=detproy.getIdProy() %>"/>
				 			 
				 				<div class="form-group">
    								<label for="inputTitulo" class="col-sm-4 control-label">Título*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputTitulo" name="nom_proy" maxlength="40" value="<%=detproy.getNomProy() %>">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputEjecutor" class="col-sm-4 control-label">Ejecutor*</label>
    								<div class="col-sm-5">
    								<input type="hidden" name="idCli" value="<%= detproy.getIdCliente()%>"/>
      								<input type="text" class="form-control" id="inputEjecutor" name="nomCli" readonly="readonly" value="<%=detproy.getNomCliente()%>" >
    								</div>
    								<div class="col-sm-3">
      								<a   href="documentacion/clienteAction.do?hacia=busCli_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=900,height=800, scrollbars=1'); return false;"  class="botoGris botoMini noMarg"><span class="glyphicons glyphicons-search"></span> Buscar</a>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="inputInicio" class="col-sm-4 control-label">Fecha de inicio*</label>
    								<div class="col-sm-8">
      								<input class="form-control inputFecha" id="fechaini" name="fechaini" size="15" placeholder="DD-MM-YYYY" required="required" maxlength="10" pattern="(0[1-9]|1[0-9]|2[0-9]|3[01])-(0[1-9]|1[012])-[0-9]{4}" type="date" value="<%=sdf.format(detproy.getFechaProy()) %>" >
    								
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtDescripcion" class="col-sm-4 control-label">Descripción*</label>
    								<div class="col-sm-8">
      								<textarea class="form-control" id="descrip" name="descrip" rows="4" ><% if(detproy.getDescripcion()!=null && !detproy.getDescripcion().equals("")){out.print(detproy.getDescripcion());}%></textarea>
      								
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEquipo" class="col-sm-4 control-label">Equipo de trabajo*</label>
    								<div class="col-sm-8">

<select multiple size="4" name="list2" id="grupoB">
<%
                  Iterator liu= usus.iterator();
                  while (liu.hasNext()){
                    UsuarioVO usu=(UsuarioVO) liu.next();
                    if(usu.getNombreUsu2()!=null){
                      out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+"</option>");
                    }else{
                      out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getApellUsu()+"</option>");
                    }
                  }
                  %>
                  
                   </select>    									
    									    									
    								</div>
    								<div class="col-sm-8 col-sm-offset-4 mTop10 text-right">
      								<a id="botoAdd" onclick="javascript:move();" class="botoGris botoMini addDoble"><span class="glyphicons glyphicons-chevron-down"></span> Agregar</a>
      								<a id="botoRemove" href="javascript:move2();" class="botoGris botoMini noMarg quitaDoble"><span class="glyphicons glyphicons-chevron-up"></span> Quitar</a>
    								</div>
    								<div class="col-sm-8 col-sm-offset-4 mTop10">
    									<select multiple size="4" id="grupoA" name="list1">
                        <%
                  Iterator liu2= usus_resp.iterator();
                  while (liu2.hasNext()){
                    UsuarioVO usu=(UsuarioVO) liu2.next();
                    if(usu.getNombreUsu2()!=null){
                      out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+"</option>");
                    }else{
                      out.println("<option value="+usu.getIdUsu()+">"+usu.getNombreUsu()+" "+usu.getApellUsu()+"</option>");
                    }
                  }

                  %>
                      </select>
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="selectEncargado" class="col-sm-4 control-label">Encargado*</label>
    								<div class="col-sm-8">

      								<select class="form-control" id="id_encargado" name="id_encargado" >
                <%
                  Iterator liu3= usus_resp.iterator();
                  while (liu3.hasNext()){
                    UsuarioVO usu=(UsuarioVO) liu3.next();
                    if(usu.getNombreUsu2()!=null){
                      %>
                <option value="<%=usu.getIdUsu()%>" <%if(usu.getIdUsu().equals(detproy.getIdEncargado())){out.print(" selected");}%>><%=usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+ usu.getApellUsu()%></option>;

                <%}else{%>
                <option value="<%=usu.getIdUsu()%>" <%if(usu.getIdUsu().equals(detproy.getIdEncargado())){out.print(" selected");}%>><%=usu.getNombreUsu()+" "+usu.getApellUsu()%></option>;

                <%}
                      }
                  %>
              </select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputFondos" class="col-sm-4 control-label">Fondos totales</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="ftotales" name="ftotales" value="<%if (detproy.getFondosTotales().intValue()!=0){out.print(detproy.getFondosTotales());} %>">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="txtAnuales" class="col-sm-4 control-label">Fondos anuales</label>
    								<div class="col-sm-8">
    									<textarea class="form-control" name="fanual" id="fanual" rows="4" ><%if(detproy.getFondosAnuales()!=null){out.print(detproy.getFondosAnuales());}%></textarea>
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    									<input type="hidden" id="Submit" name="Submit" value="Submit">
    										<button type="button" OnClick="submitThisForm1();" class="botoVerde"><span class="glyphicons glyphicons-disk-save" onClick="javascript:SelectAllList(document.form_proy.list1);"></span> Guardar</button>
    										
    										
      								</div>
  									</div>
    							</div>
    							
    							
    							
    							<script type="text/javascript">
								// Script para el traspaso de variables
				
								
								
								var sortitems = 1;

							
								function move() {
									var form=document.getElementById("form_proy");
									
									var fbox=form.list2;
									var tbox=form.list1;
									for (var i = 0; i < fbox.options.length; i++) {
										if (fbox.options[i].selected && fbox.options[i].value != "") {
											var no = new Option();
											no.value = fbox.options[i].value;
											no.text = fbox.options[i].text;
											tbox.options[tbox.options.length] = no;
											agregaEncargado(fbox);
											fbox.options[i].value = "";
											fbox.options[i].text = "";
										}
									}
									BumpUp(fbox);
									if (sortitems)
										SortD(tbox);

								}

								//--------------------------

								function move2() {
									var form=document.getElementById("form_proy");
									
									var fbox=form.list1;
									var tbox=form.list2;
									
									for (var i = 0; i < fbox.options.length; i++) {
										if (fbox.options[i].selected && fbox.options[i].value != "") {
											var no = new Option();
											no.value = fbox.options[i].value;
											no.text = fbox.options[i].text;
											tbox.options[tbox.options.length] = no;
											quitarEncargado(fbox);
											fbox.options[i].value = "";
											fbox.options[i].text = "";
										}
									}
									BumpUp(fbox);
									BumpUp(this.form_proy.id_encargado);
									if (sortitems)
										SortD(tbox);

								}

								//-------------------------------------------------

								function BumpUp(box) {
									for (var i = 0; i < box.options.length; i++) {
										if (box.options[i].value == "") {
											for (var j = i; j < box.options.length - 1; j++) {
												box.options[j].value = box.options[j + 1].value;
												box.options[j].text = box.options[j + 1].text;
											}
											var ln = i;
											break;
										}
									}
									if (ln < box.options.length) {
										box.options.length -= 1;
										BumpUp(box);
									}
								}

								//-------------------------------------------

								function SortD(box) {
									var temp_opts = new Array();
									var temp = new Object();
									for (var i = 0; i < box.options.length; i++) {
										temp_opts[i] = box.options[i];
									}
									for (var x = 0; x < temp_opts.length - 1; x++) {
										for (var y = (x + 1); y < temp_opts.length; y++) {
											if (temp_opts[x].text > temp_opts[y].text) {
												temp = temp_opts[x].text;
												temp_opts[x].text = temp_opts[y].text;
												temp_opts[y].text = temp;
												temp = temp_opts[x].value;
												temp_opts[x].value = temp_opts[y].value;
												temp_opts[y].value = temp;
											}
										}
									}
									for (var i = 0; i < box.options.length; i++) {
										box.options[i].value = temp_opts[i].value;
										box.options[i].text = temp_opts[i].text;
									}
								}

								//-------------------------------------------

								function agregaEncargado(nuevo) {
									for (var i = 0; i < nuevo.options.length; i++) {
										if (nuevo.options[i].selected && nuevo.options[i].value != "") {
											var no = new Option();
											no.value = nuevo.options[i].value;
											no.text = nuevo.options[i].text;
											this.form_proy.id_encargado.options[this.form_proy.id_encargado.options.length] = no;
										}
									}
								}

								//----------------------------------------

								function quitarEncargado(box) {
									for (var i = 0; i < box.options.length; i++) {
										if (box.options[i].selected && box.options[i].value != "") {
											for (var ii = 0; ii < this.form_proy.id_encargado.options.length; ii++) {
												if (box.options[i].value == this.form_proy.id_encargado.options[ii].value) {
													this.form_proy.id_encargado.options[ii].value = "";
													this.form_proy.id_encargado.options[ii].text = "";
												}
											}
										}
									}
								}

								// Funcion para seleccionar  todos los encargados de la Lista

								function SelectAllList(CONTROL) {
									for (var i = 0; i < CONTROL.length; i++) {
										CONTROL.options[i].selected = true;

									}
								}
								
								
								
								</script>
    							
    							<script type="text/javascript">
											function submitThisForm1() {

												SelectAllList(document.form_proy.list1);

												var formulario = $('#form_proy');

												var action = 'proyectos/proyectoAction.do'
												SubmitFormulario(action,
														formulario);

											}
										</script>
    							
    							
    							
  							</form>
						</div>
				 			
				 	
				 		
					
					</div>
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				<div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	</footer>
		</div> <!-- /container -->


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
