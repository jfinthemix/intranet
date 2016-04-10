<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.fallas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%

List lista_sistema      = (List) request.getAttribute("lista_sistema");

Integer sistemaDeBuscar         = (Integer) request.getAttribute("sistema");
Integer subsistemaDeBuscar      = (Integer) request.getAttribute("subsistema");
Integer dispositivoDeBuscar     = (Integer) request.getAttribute("dispositivo");

Integer dispositivo     = (Integer) request.getAttribute("dispositivo");

String nomSistemaDeBuscar       = (String) request.getAttribute("nomSistema");
String nomSubsistemaDeBuscar    = (String) request.getAttribute("nomSubsistema");
String nomDispositivoDeBuscar   = (String) request.getAttribute("nomDispositivo");


//System.out.println("ingresar_fallas:" + sistemaDeBuscar + " - " + subsistemaDeBuscar + " - " + dispositivoDeBuscar + " -/- " + nomSistemaDeBuscar + " - " + nomSubsistemaDeBuscar + " - " + nomDispositivoDeBuscar);

/*LinkedList lista_sistema                = new LinkedList();
lista_sistema= (LinkedList) request.getAttribute("lista_sistema");

FallaVO listS = new FallaVO();
listS.set_id_sistema(new Integer(0)); listS.set_nombre_sistema(" - ");
lista_sistema.addFirst(listS);
*/

List lista_subsistema   = (List) request.getAttribute("lista_subsistema");
List lista_dispositivo  = (List) request.getAttribute("lista_dispositivo");

List lista_sistemaSubsistema            = (List) request.getAttribute("SistemaSubsistema");
List lista_sistemaSubsistemaDispositivo = (List) request.getAttribute("SistemaSubsistemaDispositivo");

Integer var_sistema     = (Integer) request.getAttribute("sistema");
Integer var_subsistema  = (Integer) request.getAttribute("subsistema");
Integer var_dispositivo = (Integer) request.getAttribute("dispositivo");
String mensaje          = (String) request.getAttribute("mensaje");
String mailsBd          = (String) request.getAttribute("mailsBd");
SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
Calendar hoyG           = Calendar.getInstance();
java.util.Date hoyJ     = new Date();
String fecha_actual     = sdf.format(hoyJ);
fecha_actual            = fecha_actual.substring(0,10);


int tamDispositivo                      = lista_sistemaSubsistemaDispositivo.size();
int tamSubsistema                       = lista_sistemaSubsistema.size();



//Cuando venga de leer XML
String dispositivoXml = "";
String problemaXml    = (String) request.getAttribute("problemaXml")!=null ?  (String) request.getAttribute("problemaXml") : "";

/*out.println("URTD:"+dispositivo);
out.print("PROBLEMA:"+problemaXml);
*/



%>










<html lang="es"><head>

	
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
		<link rel="icon" href="img/favicon.ico"><title>Unidad Operativa de Control de Tránsito</title>
		
		
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet"><!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		
<script language="JavaScript" type="text/javascript">

var supportsKeys = false
function favChange(rnum) {
	f = document.form_nueva_falla;
	if (f.favs.selectedIndex >=0) {
		f.msgTo.value = f.favs.options[f.favs.selectedIndex].value
	}
	if (f.favs.selectedIndex > 0 && f.favs.selectedIndex <= rnum + 1) {
		f.problema.focus();
	} else {
		f.msgTo.focus();
	}
}
function calcCharLeft(f) {
		lenUSig = f.lenSSig.value
		maxLength = 700 - f.lenSysSig.value - lenUSig
        if (f.problema.value.length > maxLength) {
	        f.problema.value = f.problema.value.substring(0,maxLength)
		    charleft = 0
        } else {
			charleft = maxLength - f.problema.value.length
		}
        f.msgCL.value = charleft
}
function textKey(f) {
	supportsKeys = true
	calcCharLeft(f)
}

function cambiarTitulo(){
  document.form_nueva_falla.titulo.value = "Falla " + document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
  document.form_nueva_falla.nomDispositivo.value = document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
}

function ocultarFila(num,ver) {
  dis= ver ? '' : 'none';
  tab=document.getElementById('tabla');
  tab.getElementsByTagName('tr')[num].style.display=dis;
}




/*
function ocultarFila(num,ver) {
  dis= ver ? '' : 'none';
  tab=document.getElementById('tabla');                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    a');
  tab.getElementsByTagName('tr')[num].style.display=dis;
  if(dis=='none'){
    document.form_nueva_falla.titulo.value = "no vacio";
    document.form_nueva_falla.envia_a.value = "no vacio";
  }
}
*/


var tamD = <%out.print(tamDispositivo);%>;
var tamS = <%out.print(tamSubsistema);%>;

var M_Subsistema  = new Array(tamS);//5 al 9 de febrero
var M_Dispositivo = new Array(tamD);//105 //<%//=lista_sistemaSubsistemaDispositivo.size()%>
<%
int y=0;
int z=0;

Iterator x  = lista_sistemaSubsistema.iterator();
while (x.hasNext()) {
  FallaVO auxSubsistema = (FallaVO) x.next();
  out.print("M_Subsistema["+y+"] = new Array(\""+ auxSubsistema.get_id_sistema() + "\" , \"" + auxSubsistema.get_id_subsistema() + "\" , \""  + auxSubsistema.get_nombre_subsistema() + "\");\n");
  y++;
}
Iterator xx = lista_sistemaSubsistemaDispositivo.iterator();
while (xx.hasNext()) {
  FallaVO auxDispositivo = (FallaVO) xx.next();
  out.print("M_Dispositivo["+z+"] = new Array(\""+ auxDispositivo.get_id_subsistema() + "\" , \""  + auxDispositivo.get_id_dispositivo() + "\" , \""  + auxDispositivo.get_nombre_dispositivo() + "\");\n");
  z++;
}
%>
function valida_cambioInicial(){
  with (document.form_nueva_falla){
    <%
    if (var_sistema == null && var_subsistema == null && var_dispositivo == null){
      %>
      sistema.selectedIndex     = 0;
      subsistema.selectedIndex  = 0;
      dispositivo.selectedIndex = 0;
      <%
    }%>
  }
}
function valida_cambio(){
  with (document.form_nueva_falla){
    carga_lista(sistema, subsistema, M_Subsistema);
  }
}

function valida_cambio2(){
  with (document.form_nueva_falla){
    carga_lista2(subsistema, dispositivo, M_Dispositivo);
  }
}

function del_lista(Lista) {
  while (Lista.length>0) Lista.remove(0);
}

function carga_lista(Origen, Destino, Matriz) {
  del_lista(Destino);
  var ii = Origen.selectedIndex;
  var paso = true;
  for (j = 0;j<Matriz.length;j++ && paso) {
    if (Origen.options[ii].value == Matriz[j][0]) {
      var oOption = new Option (Matriz[j][2], Matriz[j][1], "", "");
      Destino.options[Destino.length]= oOption;
    }
    else
    if (Origen.options[ii].value < Matriz[j][0]) paso = false;
  }
  Destino.selectedIndex=0;
}

function carga_lista2(Origen, Destino, Matriz) {
  del_lista(Destino);
  var ii = Origen.selectedIndex;
  var paso = true;
  for (j = 0;j<Matriz.length;j++ && paso) {
    if (Origen.options[ii].value == Matriz[j][0]) {
      var oOption = new Option (Matriz[j][2], Matriz[j][1], "", "");
      Destino.options[Destino.length]= oOption;
    }
    else
    if (Origen.options[ii].value < Matriz[j][0]) paso = false;
  }
  Destino.selectedIndex=0;
  cambiarTitulo();
}

function confirmaEliminacion(){
  var resp=confirm("Al eliminar esta falla se eliminará todo lo asociado a ella.\n ¿Desea continuar con la eliminación?");
  return resp;
}

function confirmaEliminacion(){
  var resp=confirm("Al eliminar esta falla se eliminará todo lo asociado a ella.\n ¿Desea continuar con la eliminación?");
  return resp;
}

function valida_envia(){
  document.form_nueva_falla.submit();
}

</script>
		




</head>

<body onload="valida_cambioInicial();"><br>
<div class="main">
			<div class="container">
				<div class="row clearfix">
				

				
					<div class="col-sm-6 desarrollo">
					
						<h2>Ingresar evento de dispositivo</h2>
						
						<%
      if (mensaje != null)
        out.print(mensaje);
        %>
						
						<div class="box boxpost">
				 			<h4>Datos del evento</h4>
				 			<form id="form1" name="form_nueva_falla" method="post" class="form-horizontal" method="post" action="fallasAction.do">
				 				<div class="form-group">
    								<label for="sistema" class="col-sm-4 control-label">Sistema</label>
    								<div class="col-sm-8">
      								<select name="sistema" id="sistema" onchange="javascript:valida_cambio();carga_lista2(subsistema, dispositivo, M_Dispositivo);" class="form-control">
      									
      									<option value="0" selected="selected" disabled="disabled"> Seleccionar</option>
    										 <%
                  Iterator i = lista_sistema.iterator();
                  String nombre_sistema = "";
                  while (i.hasNext()) {
                    FallaVO sistema = (FallaVO) i.next();
                    if(sistema.get_id_sistema().equals(var_sistema)){
                      out.println("<option value=\"" + sistema.get_id_sistema()+ "\" selected>" + sistema.get_nombre_sistema() + "</option>");
                      nombre_sistema= sistema.get_nombre_sistema();
                    }
                    else{
                      out.print("<option value=\"" + sistema.get_id_sistema()+ "\">" + sistema.get_nombre_sistema() + "</option>");
                    }
                  }
              %>
    									</select>  
    									    								</div>
    							</div>
    							<div class="form-group">
    								<label for="subsistema" class="col-sm-4 control-label">Subsistema</label>
    								
 <%
                if(subsistemaDeBuscar!=null){
                  out.println("<option value=\"" + subsistemaDeBuscar + "\" selected>" + nomSubsistemaDeBuscar + "</option>");
                }
                else{
              %>    								
    								
    								<div class="col-sm-8">
      								<select name="subsistema" id="subsistema" onchange="javascript:valida_cambio2();" class="form-control">
      								
      								
   <%
                  Iterator ii = lista_sistemaSubsistema.iterator();
                  String nombre_subsistema = "";
                  while (ii.hasNext()) {
                    FallaVO subsistema = (FallaVO) ii.next();
                    if(subsistema.get_id_sistema().equals(var_sistema)){
                      if(subsistema.get_id_subsistema().equals(var_subsistema)){
                        out.println("<option value=\"" + subsistema.get_id_subsistema()+ "\" selected>" + subsistema.get_nombre_subsistema() + "</option>");
                        nombre_subsistema=subsistema.get_nombre_subsistema();
                      }
                      else{
                        if(var_subsistema==null){
                          out.println("<option value=\"" + "0" + "\" selected>" + " - " + "</option>");
                        }
                      }
                      out.print("<option value=\"" + subsistema.get_id_subsistema() + "\">" + subsistema.get_nombre_subsistema() + "</option>");
                    }
                  }
                %>
              </select>

           <%  }%>
      								


    								</div>
    							</div>
    							<div class="form-group">
    								<label for="dispositivo" class="col-sm-4 control-label">Dispositivo</label>
    								<div class="col-sm-8">
   <%
              if(dispositivoDeBuscar!=null){
                  out.println("<option value=\"" + dispositivoDeBuscar + "\" selected>" + nomDispositivoDeBuscar + "</option>");
                  //System.out.println(dispositivoDeBuscar+ " y " + nomDispositivoDeBuscar);
                  %>
                  <input type="hidden" name="dispositivo" value="<%=dispositivoDeBuscar%>" />
                  <input type="hidden" name="nomDispositivo" value="<%=nomDispositivoDeBuscar%>" />
                  <%
                }
                else{
                %>

                <select name="dispositivo" size="1" id="dispositivo" onChange="cambiarTitulo()" class="form-control">
                  <%

                  Iterator iii = lista_sistemaSubsistemaDispositivo.iterator();
                  String nombre_dispositivo="";
                  while (iii.hasNext()) {
                    FallaVO dispoSitivo = (FallaVO) iii.next();
                    if(dispoSitivo.get_id_subsistema().equals(var_subsistema)){

                      if(dispoSitivo.get_id_dispositivo().equals(var_dispositivo)){
                        out.println("<option value=\"" + dispoSitivo.get_id_dispositivo()+ "\" selected>" + dispoSitivo.get_nombre_dispositivo() + "</option>");
                        nombre_dispositivo=dispoSitivo.get_nombre_dispositivo();
                      }
                      else{
                        if(var_dispositivo==null){
                          out.println("<option value=\"" + "0" + "\" selected>" + " - " + "</option>");
                        }
                      }
                      out.print("<option value=\"" + dispoSitivo.get_id_dispositivo() + "\">" + dispoSitivo.get_nombre_dispositivo() + "</option>");
                    }
                  }%>
                </select>

                <%  }%>    								
    								
      								
    								</div>
    							</div>
				 				<div class="form-group">
    								<label for="inputHora" class="col-sm-4 control-label">Hora</label>
    								<div class="col-sm-2">
    									<input class="form-control" id="inputHora" name="hora" type="text" maxlength="5" onKeyUp = "this.value=formateahora(this.value);"> <input name="fecha_actual" type="hidden" value="<% out.print(fecha_actual);%>">
    								</div>
    								<div class="col-sm-6">
    									<p class="form-control-static">horas (HH:MM)</p>
    								</div>
  								</div>
  								<div class="form-group">
    								<label for="selectEvento" class="col-sm-4 control-label">Evento</label>
    								<div class="col-sm-8">
      								<select class="form-control" id="selectEvento" name="evento">
      							<option selected onclick="ocultarFila(5,true)" value="falla">Falla</option>
                <option onClick="ocultarFila(5,false)" value="comentario">Comentario</option>
    									</select>
    									
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="titulo" class="col-sm-4 control-label">Título del asunto</label> 
    								
    								<div class="col-sm-8">
    									<input name="titulo" id="titulo" class="form-control" type="text" value="<% if(dispositivoDeBuscar!=null) {out.print("Falla "+nomDispositivoDeBuscar);}%>">
    									<input name="nomDispositivo" type="hidden">
    			
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="inputMail" class="col-sm-4 control-label">Enviar mail c/cc a</label>
    								<div class="col-sm-8">
      								<input class="form-control" id="inputMail" type="text" name="enviar_a" size="50" value="<%//out.print("manter@auter.cl, faraya@auter.cl, gf@uoct.cl");%>">
    									<label class="checkbox-inline">
  											<input id="checkMail" name="enviaMail" value="si" checked="checked" type="checkbox"> Enviar mail a empresa mantenedora y GF
  											
										</label>
    								</div>
    							</div>
    							<div class="form-group">
    								<label for="problema" class="col-sm-4 control-label">Mensaje (Problema)</label>
    								<div class="col-sm-8">
      								<textarea name="problema" rows="4" onkeyup="textKey(this.form)" class="form-control" onKeyUp="textKey(this.form)"><!--   <% out.print(problemaXml); %> --> </textarea>
      								<input name="lenSSig" value="0" type="hidden">
              						<input name="lenLSig" value="0" type="hidden">
              						<input name="lenSysSig" value="0" type="hidden">
    									<p class="form-control-static">Máx. 700 caracteres. Disponibles: <input value="700" name="msgCL" disabled="disabled" size="4"></p>
    								</div>
    							</div>
    							<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" class="botoVerde"><span class="glyphicons glyphicons-search"></span> Guardar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
						<!-- <div class="box boxpost encuentra">
				 			<h4>Fallas <span class="pull-right"><small>Exportar:</small> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_excel.png"></a> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_pdf.png"></a></span></h4>
				 			<table class="table table-striped table-bordered table-hover">
      						<thead>
        							<tr>
          							<th>Ingreso<br>
</th>
          							<th>Evento<br>
</th>
          							<th>Plazo<br>
</th>
          							<th>Estado<br>
</th>
          							<th class="col-sm-3">Opciones<br>
</th>
          						</tr>
      						</thead>
      						<tbody>
        							<tr>
          							<td>04/11/2015 14:36<br>
</td>
          							<td>Falla 141 APOQUINDO - LAS CONDES<br>
</td>
          							<td>-<br>
</td>
          							<td>Iniciada<br>
</td>
          							<td>
          								<a href="javascript:void(0)" class="botoGris botoMini margR0" title="Ver detalles" alt="Ver detalles"><span class="glyphicons glyphicons-eye-open margR0"></span></a>
          								<a href="javascript:void(0)" class="botoRojo botoMini margR0" title="Eliminar" alt="Eliminar"><span class="glyphicons glyphicons-delete margR0"></span></a>	
          							<br>
</td>
          						</tr>
          						<tr>
          							<td>06/11/2015 20:00<br>
</td>
          							<td>Falla 5 ALAMEDA - SANTA ROSA<br>
</td>
          							<td>-<br>
</td>
          							<td>Iniciada<br>
</td>
          							<td>
          								<a href="javascript:void(0)" class="botoGris botoMini margR0" title="Ver detalles" alt="Ver detalles"><span class="glyphicons glyphicons-eye-open margR0"></span></a>
          								<a href="javascript:void(0)" class="botoRojo botoMini margR0" title="Eliminar" alt="Eliminar"><span class="glyphicons glyphicons-delete margR0"></span></a>	
          							<br>
</td>
          						</tr>
        						</tbody>
    						</table>
    						<div class="boxOpciones">
    							<div class="paginas clearfix">
    								<div class="col-sm-7 text-left">
    									<strong>2 items encontrados.</strong> Mostrando 1 a 15.
    								</div>
    								<div class="col-sm-5">
    									<ul class="pagination">
        									<li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a><br>
</li>
        							
</li>
        									<li><a href="#">2</a><br>
</li>
        									<li><a href="#">3</a><br>
</li>
        									<li><a href="#" aria-label="Next"><span aria-hidden="true">»</span></a><br>
</li>
      								</ul>
    								</div>
    							</div>
    						</div>
						</div>
				 			
				 		<div class="verMas">
							<a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a>
							<a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
						</div>
				 		
					
					</div><br>
</div> --> <!-- /row -->
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	

      <div class="container">
			<footer>
				</footer><div class="row">
					<div class="col-sm-12">
						<p>Unidad Operativa de Control de Tránsito. <span id="pie"></span></p>
					</div>
				</div>
        	
		</div> 
		
		
		<!-- /container -->

<script>
function IsNumeric(valor){
    var log=valor.length; var sw="S";
    for (x=0; x<log; x++){
        v1=valor.substr(x,1);
        v2 = parseInt(v1);
        //Compruebo si es un valor numérico
        if (isNaN(v2)) {
            sw= "N";
        }
    }
    if (sw=="S") {
        return true;
    }
    else {
        return false;
    }
}
var primerslap=false;
var segundoslap=false;
function formateahora(hora){
    var long = hora.length;
    var hh;
    var mm;
    if ((long>=2) && (primerslap==false)) {
        hh=hora.substr(0,2);
        if ((IsNumeric(hh)==true) && (hh<=23)) {
            hora=hora.substr(0,2)+":"+hora.substr(3,2);
            primerslap=true;
        }
        else {
            hora="";
	    primerslap=false;
        }
    }
    else{
        hh=hora.substr(0,1);
        if (IsNumeric(hh)==false){
            hora="";
        }
        if ((long<=2) && (primerslap=true)) {
            hora=hora.substr(0,1);
            primerslap=false;
        }
    }
    if ((long==5) && (segundoslap==false)){
        mm=hora.substr(3,2);
        if ((IsNumeric(mm)==true) &&(mm<=59) ) {
            hora=hora.substr(0,5);
            segundoslap=true;
        }
        else {
            hora=hora.substr(0,3);
            segundoslap=false;
        }
    }
    else {
        if ((long<5) && (segundoslap=true)) {
            hora=hora.substr(0,4);
	    segundoslap=false;
        }
    }
    if (long>=5){
        hora=hora.substr(0,5);
        hh=hora.substr(0,2);
        mm=hora.substr(3,2);
    }
return (hora);
}
</script>



    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
    <script src="js/uoct.js"></script>
    <script src="js/uoct_falla2.js"></script>
  </body></html> 