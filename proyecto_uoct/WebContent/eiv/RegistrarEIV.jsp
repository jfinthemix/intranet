<!DOCTYPE html>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="proyecto_uoct.EIV.VO.EIVVO,java.util.List,java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO" %>
<%@ page import="proyecto_uoct.common.VO.IdStrVO,proyecto_uoct.documentacion.VO.CliEntVO" %>
<%@ page import="java.text.SimpleDateFormat,java.util.Date" %>
<%
List listaIngenieros=(List) request.getAttribute("listaIngenieros");
List comunas= (List) request.getAttribute("comunas");
List redes= (List) request.getAttribute("redes");
List tiposeiv=(List) request.getAttribute("tiposeiv");
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");

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
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
	
	
	<script type="text/javascript">
								// Script para el traspaso de variables
				
								
								
	var sortitems = 1;
				

							
	function mover(){
		var fbox=form1.red;
		var tbox=form1.listaRel;
			var red= fbox.value;
			if(validaRed(red) && validaRepeticion(red,tbox)) {
				var no = new Option();
				no.value = fbox.value;
				no.text = fbox.value;
				tbox.options[tbox.options.length] = no;
				fbox.value="";
				if (sortitems) SortD(tbox);
			}

	}
	
	function validaRed(red){
		var malo=red.search("[^0-9]");
		if(malo!=-1){
			alert("La red solamente se puede indicar con un número");
			return false;

		}
		if(red==""){
		alert("la red no puede ser nula");
		return false;
		}
		var noRed=parseInt(red);
		if(noRed>9999 || noRed<=0){
		alert("La red debe estar entre 1 y 9999");
		return false;
		}
		else return true;
	}

	function validaRepeticion(red,tbox){
	for (var i=0;i<tbox.options.length;i++){
		if(red==tbox.options[i].value){
		alert("la red " +red+" ya está en la lista");
		return false;
		}
	}
	return true;
	}
								
			

								//--------------------------

								function move2() {
									var form=document.getElementById("form1");
									
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

function BumpUp(box)  {
for(var i=0; i<box.options.length; i++) {
if(box.options[i].value == "")  {
for(var j=i; j<box.options.length-1; j++)  {
box.options[j].value = box.options[j+1].value;
box.options[j].text = box.options[j+1].text;
}
var ln = i;
break;
   }
}
if(ln < box.options.length)  {
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
								
								
function borrar(){
	var CONTROL=form1.listaRel;
					for (var i =0;i<CONTROL.length;i++){
						if (CONTROL.options[i].selected ==true){
							CONTROL.options[i]=null;
						}
					}
				}
				
				
				
function submitThisForm1() {
	var formulario = $('#busOTform');
	var action = 'proyectos/proyectoAction.do'
	SubmitFormulario(action,	formulario);

}

//Para registrar sin correo
function submitThisForm2() {
	var formulario = $('#form1');
	//SelectAllList(formulario.listaRel);
	var action = 'eiv/eivAction.do';
	SubmitFormulario(action,formulario);

}

//Para registrar enviando correo

function submitThisForm3() {
	var formulario = $('#busOTform');
	var action = 'proyectos/proyectoAction.do'
	SubmitFormulario(action,	formulario);

}

function SelectAllList(CONTROL){
	for(var i = 0;i < CONTROL.length;i++){
	CONTROL.options[i].selected = true;
	}
	}
				
				
				
function pasaDoc(idDoc,codDoc,tipoDoc){
	  form1.idDocOficio.value = idDoc;
	  form1.codOficio.value = codDoc;

	  otra.window.close();
	  }

				
				
								
								
	</script>
	
	
		
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h2>Registrar EIV</h2>
						
				 		<div class="box boxpost">
				 			<h4>Datos de EISTU</h4>
				 			<form class="form-horizontal" action="eiv/eivAction.do" name="form1" id="form1" method="post">
				 			<input type="hidden" name="hacia" value="registrarEIV" />
				 			<input type="hidden" name="submit" value="Submit" />
				 			<input type="hidden" name="noEmail" value="Registrar SIN enviar email" />
				 				<div class="form-group">
    								<label for="inputTitulo" class="col-sm-4 control-label">Título*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputTitulo" name="titulo_eiv" size="40" maxlength="90">
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputDoc" class="col-sm-4 control-label">Documento original*</label>
    								<div class="col-sm-2 guionpost">
      								<p class="form-control-static">OFICIO</p>
    								</div>
    								<div class="col-sm-3">
      								<input type="text" class="form-control" id="inputDoc" name="codOficio" size="20" readonly="readonly">
    								</div>
    								<div class="col-sm-3">
      								<a href="eiv/eivAction.do?hacia=cargarBusDoc" onclick="javascript:popUp(this.href, this.target, 'width=1000,height=800, scrollbars=1'); return false;" data-toggle="modal" data-target="#modalBuscaOficio" class="botoGris botoMini noMarg"><span class="glyphicons glyphicons-search"></span> Buscar</a>
    								</div>
  								</div>
  								<div class="form-group">
  								<input type="hidden" name="idDocOficio"/>
    								<label for="selectTipoEstudio" class="col-sm-4 control-label">Tipo de estudio*</label>
    								<div class="col-sm-8">
      								<select name="id_tipoestudio" id="id_tipoestudio">
                  <%
        Iterator ite=tiposeiv.iterator();
        while(ite.hasNext()){
          IdStrVO t=(IdStrVO) ite.next();

        %>
                  <option value="<%=t.getId() %>" ><%=t.getStr() %></option>
                  <%} %>
                </select>
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="inputPres" class="col-sm-4 control-label">Fecha de presentación*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputPres" name="fechaPresentacion" size=8>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEnv" class="col-sm-4 control-label">Envío SEREMITT*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputEnv" name="fechaEnvioSeremitt" size=8>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputIngreso" class="col-sm-4 control-label">Fecha de ingreso UOCT*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputIngreso" name="fechaIngreso" size=8 >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputVto" class="col-sm-4 control-label">Fecha de vencimiento*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control inputFecha" id="inputVto" name="fechaVencimiento" size=8 >
    								</div>
    							</div>
  								<div class="form-group">
    								<label for="selectComuna" class="col-sm-4 control-label">Comuna*</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="id_comuna" name="id_comuna">
      									  <option value="0">----------</option>
                  <%
     if (comunas!= null){

       Iterator ic= comunas.iterator();
       while(ic.hasNext()){
         IdStrVO com= (IdStrVO) ic.next();
         String s_com= "<option value=\""+ com.getId()+"\"";
         s_com += ">"+com.getStr()+"</option>";
         out.println(s_com);
       }

     }

      %>
      								</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputDire" class="col-sm-4 control-label">Dirección*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputDire" name="direccion" size="40" maxlength="200">
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputRedes" class="col-sm-4 control-label">Redes involucradas*</label>
    								<div class="col-sm-8">
    									<input type="text" id="red" class="form-control" name="red">
    								</div>
    								<div class="col-sm-8 col-sm-offset-4 mTop10 text-right">
      								<a id="botoAgregar" href="javascript:void(0);" onclick="javascript:mover();" class="botoGris botoMini"><span class="glyphicons glyphicons-chevron-down"></span> Agregar</a>
      								<a id="botoBorrar" onClick="javascript:borrar()" href="javascript:void(0);" class="botoRojo botoMini noMarg"><span class="glyphicons glyphicons-delete"></span> Borrar</a>
    								</div>
    								<div class="col-sm-8 col-sm-offset-4 mTop10">
    									<select multiple id="listaRel" class="form-control" size="4" name="listaRel">
    									</select>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputParking" class="col-sm-4 control-label">N° de estacionamientos*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputParking" name="estacionamientos" maxlength="5" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputConsultor" class="col-sm-4 control-label">Consultor*</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputConsultor" name="consultor" maxlength="100" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEmp" class="col-sm-4 control-label">Empresa del consultor</label>
    								<div class="col-sm-8">
      								<input type="text" class="form-control" id="inputEmp" name="empconsultora" maxlength="100" >
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputEncargado" class="col-sm-4 control-label">Encargado del EISTU*</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="inputEncargado" name="id_ingeniero" >
      									<option value="0">---------</option>
                  <%
      if (listaIngenieros!=null){
        Iterator a= listaIngenieros.iterator();
        while (a.hasNext()){
          UsuarioVO inge= (UsuarioVO) a.next();
          String str=null;
          str= "<option value=\""+inge.getIdUsu()+"\"";
          if(inge.getNombreUsu2()!=null){
            str +=" >"+inge.getNombreUsu()+" "+inge.getNombreUsu2()+" "+inge.getApellUsu()+"</option>";
          }else{
            str +=" >"+inge.getNombreUsu()+" "+inge.getApellUsu()+"</option>";
          }
          out.println(str);
        }
      }
      %>
										</select>
    								</div>
    							</div>
  								
  							</form>
						</div>
				 			
				 		
				 		<div class="col-sm-12">
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm2();" class="botoGris">Registrar sin enviar correo</a>
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm3();" class="botoVerde"><span class="glyphicons glyphicons-envelope"></span> Registrar y enviar correo</a>
      								</div>
				 		
				 		
					
					</div> <!--  /desarrollo  -->
			
					
			
				</div><!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

<script language="JavaScript" type="text/javascript">
function SelectAllList(CONTROL){
for(var i = 0;i < CONTROL.length;i++){
CONTROL.options[i].selected = true;
}
}
</script>


		
		

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/uoct.js"></script>
  </body>
</html>
