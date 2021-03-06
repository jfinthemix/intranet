<!DOCTYPE html>

<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.fallas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@page buffer="50kb"%>

<%

SimpleDateFormat sdf                          = new SimpleDateFormat("dd-MM-yyyy HH:mm");

List lista_subsistema                         = (List) request.getAttribute("lista_subsistema");
List lista_dispositivo                        = (List) request.getAttribute("lista_dispositivo");
//inicializo lista_sistema
LinkedList lista_sistema                      = new LinkedList();
lista_sistema                                 = (LinkedList) request.getAttribute("lista_sistema");
//inicializo lista_sistemaSubsistema
LinkedList lista_sistemaSubsistema            = new LinkedList();
lista_sistemaSubsistema                       = (LinkedList) request.getAttribute("SistemaSubsistema");

Integer sistemaParaIngresar     = new Integer(0);
Integer subsistemaParaIngresar  = new Integer(0);
Integer dispositivoParaIngresar = new Integer(0);

//Add a lista_sistemaSubsistema un "-" por cada sistema
Iterator mu = lista_sistema.iterator();
while (mu.hasNext()) {
  FallaVO sist = (FallaVO) mu.next();
  FallaVO listSs = new FallaVO();
  listSs.set_id_subsistema(new Integer(0)); listSs.set_id_sistema(sist.get_id_sistema()); listSs.set_nombre_subsistema(" - ");
  lista_sistemaSubsistema.addFirst(listSs);
  }

//Add a lista_sistema
FallaVO listS = new FallaVO();
listS.set_id_sistema(new Integer(0)); listS.set_nombre_sistema(" - ");
lista_sistema.addFirst(listS);

//inicializo lista_sistemaSubsistemaDispositivo
LinkedList lista_sistemaSubsistemaDispositivo = new LinkedList();
lista_sistemaSubsistemaDispositivo            = (LinkedList) request.getAttribute("SistemaSubsistemaDispositivo");

//add a lista_sistemaSubsistemaDispositivo un "-" por cada subsistema
Iterator mua = lista_sistemaSubsistema.iterator();
while (mua.hasNext()) {
  FallaVO sub = (FallaVO) mua.next();
  FallaVO listD = new FallaVO();
  listD.set_id_dispositivo(new Integer(0)); listD.set_id_subsistema(sub.get_id_subsistema()); listD.set_nombre_dispositivo(" - ");
  lista_sistemaSubsistemaDispositivo.addFirst(listD);
}

//add a lista_sistemaSubsistema
FallaVO listSs = new FallaVO();
listSs.set_id_subsistema(new Integer(0)); listSs.set_id_sistema(new Integer(0)); listSs.set_nombre_subsistema(" - ");
lista_sistemaSubsistema.addFirst(listSs);

//add a lista_sistemaSubsistemaDispositivo
FallaVO listD = new FallaVO();
listD.set_id_dispositivo(new Integer(0)); listD.set_id_subsistema(new Integer(0)); listD.set_nombre_dispositivo(" - ");
lista_sistemaSubsistemaDispositivo.addFirst(listD);


List listafallas          = (List) request.getAttribute("listafallas");
Integer var_sistema       = (Integer) request.getAttribute("sistema");
Integer var_subsistema    = (Integer) request.getAttribute("subsistema");
Integer var_dispositivo   = (Integer) request.getAttribute("dispositivo");

Integer var_estado        = (Integer) request.getAttribute("estado");
String  estadoString      = (String)  request.getAttribute("estadoString");
String  aux               = "1";
String  fechaInicial      = ""  + (String)  request.getAttribute("fechaInicial");
aux                       = aux + "" + fechaInicial;
String  fechaActual       = ""  + (String)  request.getAttribute("fechaActual");
String mes                = "";
String anio               = "";
String  fecha_actual      = (String) request.getAttribute("fecha");
mes                       = fecha_actual.substring(3,5);
anio                      = fecha_actual.substring(6);
String fecha_inicial      = "01-"+mes+"-"+anio;
Integer idPerfil          = (Integer) request.getAttribute("idPerfil");
/*Integer idAdmin         = new Integer(1);
Integer idGFallas         = new Integer(43);*/
Integer cero              = new Integer(0);
int oo                    = 0;
boolean ban               = false;
int tamDispositivo        = lista_sistemaSubsistemaDispositivo.size();
int tamSubsistema         = lista_sistemaSubsistema.size();
int tamSistema            = lista_sistema.size();

if(aux.length()>5){
  fecha_inicial = fechaInicial;
  fecha_actual  = fechaActual;
}
Calendar hoyG=Calendar.getInstance();
java.util.Date hoyJ=new Date();

%>


<html lang="es"><head>
  
  <meta charset="utf-8">
 
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  
  <meta name="viewport" content="width=device-width, initial-scale=1">

  
  <meta name="description" content="Intranet de la UOCT">

  
  <meta name="author" content="jfanasco" >

  
  <link rel="icon" href="img/favicon.ico">
  <title>Unidad Operativa de Control de Tr�nsito</title>

  
  
  <link href="css/grid.css" rel="stylesheet">

  
  <link href="css/glyphs.css" rel="stylesheet">

  
  <link href="css/style.css" rel="stylesheet">

  
  <link href="css/datepicker.css" rel="stylesheet">

<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
		
<script language="JavaScript" type="text/javascript">
var tamD      = <%out.print(tamDispositivo);%>;
var tamS      = <%out.print(tamSubsistema);%>;
var tamSist   = <%out.print(tamSistema);%>;

var tamD      = tamD + 1;
var tamS      = tamS + 1;
var M_Sistema     = new Array(tamSist+1);
<%
Integer[] arreglo = new Integer[tamSistema+1];
Integer[] arreglo2= new Integer[tamSubsistema+1];

int w     =0;
int wFin  =0;
int v     =0;
int vFin  =0;

int y=0;
int z=0;

int ww=0;
int vv=0;
Integer integerrrr= new Integer(0);
Iterator xxx  = lista_sistema.iterator();
while (xxx.hasNext()) {
  FallaVO auxSistema = (FallaVO) xxx.next();
  out.print("M_Sistema["+w+"] = new Array(\""+ auxSistema.get_id_sistema() + "\" , \""  + auxSistema.get_nombre_sistema() + "\");\n");
  arreglo[w] = auxSistema.get_id_sistema();
  w++;
}
wFin   = w;
w      = 0;
%>
tamS   = tamS+<%out.print(wFin);%>;
var M_Subsistema  = new Array(tamS);
<%
/*
while(w<wFin){
  out.print("M_Subsistema["+y+"] = new Array(\""+ arreglo[w] + "\" , \"" + "0" + "\" , \""  + " - " + "\");\n");
  y++;
  w++;
}*/
Iterator x  = lista_sistemaSubsistema.iterator();
while (x.hasNext()) {
  FallaVO auxSubsistema = (FallaVO) x.next();
   if(v==0){
    out.print("M_Subsistema["+y+"] = new Array(\""+ "0" + "\" , \"" + "0" + "\" , \""  + " - " + "\");\n");
    arreglo2[v] = cero;
    y++;
    v++;
  }
  out.print("M_Subsistema["+y+"] = new Array(\""+ auxSubsistema.get_id_sistema() + "\" , \"" + auxSubsistema.get_id_subsistema() + "\" , \""  + auxSubsistema.get_nombre_subsistema() + "\");\n");
  arreglo2[v] = auxSubsistema.get_id_subsistema();
  v++;
  y++;
}
vFin   = v;
v      = 0;
%>
tamD   = tamD+<%out.print(vFin);%>;
var M_Dispositivo = new Array(tamD);
<%
/*
while(v<vFin){
  out.print("M_Dispositivo["+z+"] = new Array(\""+ arreglo2[v] + "\" , \"" + "0" + "\" , \""  + " - " + "\");\n");
  v++;
  z++;
}*/
Iterator xx = lista_sistemaSubsistemaDispositivo.iterator();
while (xx.hasNext()) {
  FallaVO auxDispositivo = (FallaVO) xx.next();
  out.print("M_Dispositivo["+z+"] = new Array(\""+ auxDispositivo.get_id_subsistema() + "\" , \""  + auxDispositivo.get_id_dispositivo() + "\" , \""  + auxDispositivo.get_nombre_dispositivo() + "\");\n");
  z++;
}

%>
function valida_cambioInicial(){
  with (document.form_buscar){
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
  with (document.form_buscar){
    carga_lista(sistema, subsistema, M_Subsistema);
  }
}

function valida_cambio2(){
  with (document.form_buscar){
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
  if(Origen.selectedIndex==0){
    document.form_buscar.subsistema=null;
    document.form_buscar.dispositivo=0;
  }
}
var ayuda=true;

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
}

function confirmaEliminacion(){
  var resp=confirm("Al eliminar esta falla se eliminar� todo lo asociado a ella.\n �Desea continuar con la eliminaci�n?");
  return resp;
}

function confirmaEliminacion(){
  var resp=confirm("Al eliminar esta falla se eliminar� todo lo asociado a ella.\n �Desea continuar con la eliminaci�n?");
  return resp;
}

function valida_envia(){
  document.form_buscar.submit();
}

</script>
		
		
		
</head><body onload="valida_cambioInicial();">
<br>

<div class="main">
<div class="container">
<div class="row clearfix"><br>
<div class="col-sm-6 desarrollo">
<h2>Consultar Bit�cora</h2>
<div class="box boxpost">
<h4>Datos de b�squeda</h4>
<form name="form_buscar" method="post" class="form-horizontal" action="fallasAction.do">
  <div class="form-group"> <label for="sistema" class="col-sm-4 control-label">Sistema</label>
  <div class="col-sm-8">


 <%
              Integer valorCero=new Integer(0);
              %>
              <select name="sistema" class="form-control" size="1" id="sistema" onChange="javascript:valida_cambio();carga_lista2(subsistema, dispositivo, M_Dispositivo);">
               <%
                Iterator i = lista_sistema.iterator();
                String nombre_sistema = "";
                while (i.hasNext()) {
                  FallaVO sistema = (FallaVO) i.next();
                  if(sistema.get_id_sistema().equals(var_sistema)){
                    out.println("<option value=\"" + sistema.get_id_sistema()+ "\" selected>" + sistema.get_nombre_sistema() + "</option>");
                    sistemaParaIngresar = sistema.get_id_sistema();
                    nombre_sistema= sistema.get_nombre_sistema();
                  }
                  else{
                    out.print("<option value=\"" + sistema.get_id_sistema()+ "\">" + sistema.get_nombre_sistema() + "</option>");
                  }
                }
                %>
              </select>

 <input type="hidden" name="nombre_sistema" value="<%=nombre_sistema%>"/>

 </div>
  </div>
  <div class="form-group"> <label for="subsistema" class="col-sm-4 control-label">Subsistema</label>
  <div class="col-sm-8">
  
   <select name="subsistema" class="form-control" size="1" id="subsistema" onChange="javascript:valida_cambio2();">
                <%
                Iterator ii = lista_sistemaSubsistema.iterator();
                String nombre_subsistema = "";
                while (ii.hasNext()) {
                  FallaVO subsistema = (FallaVO) ii.next();
                  if(subsistema.get_id_sistema().equals(var_sistema)){
                    if(subsistema.get_id_subsistema().equals(var_subsistema)){
                      out.println("<option value=\"" + subsistema.get_id_subsistema()+ "\" selected>" + subsistema.get_nombre_subsistema() + "</option>");
                      subsistemaParaIngresar = subsistema.get_id_subsistema();
                      nombre_subsistema=subsistema.get_nombre_subsistema();
                    }
                    else{
                      out.print("<option value=\"" + subsistema.get_id_subsistema() + "\">" + subsistema.get_nombre_subsistema() + "</option>");
                    }
                  }
                }
                %>
           
  </select>
   <input type="hidden" name="nombre_subsistema" value="<%=nombre_subsistema%>"/> </div>
  </div>
  <div class="form-group"> <label for="dispositivo" class="col-sm-4 control-label">Dispositivo</label>
  <div class="col-sm-8">
  
  <select name="dispositivo" size="1" id="dispositivo" class="form-control">
                <%
                Iterator iii = lista_sistemaSubsistemaDispositivo.iterator();
                String nombre_dispositivo="";
                while (iii.hasNext()) {
                  FallaVO dispositivo = (FallaVO) iii.next();
                  if(dispositivo.get_id_subsistema().equals(var_subsistema)){

                    if(dispositivo.get_id_dispositivo().equals(var_dispositivo)){
                      out.println("<option value=\"" + dispositivo.get_id_dispositivo()+ "\" selected>" + dispositivo.get_nombre_dispositivo() + "</option>");
                      dispositivoParaIngresar = dispositivo.get_id_dispositivo();
                      nombre_dispositivo=dispositivo.get_nombre_dispositivo();
                    }
                    else{
                      out.print("<option value=\"" + dispositivo.get_id_dispositivo() + "\">" + dispositivo.get_nombre_dispositivo() + "</option>");
                    }
                  }
                }
                %>
              </select> <input type="hidden" name="nombre_dispositivo" value="<%=nombre_dispositivo%>"/>  </div>
  </div>
  <div class="form-group input-daterange"> <label for="inputFecha" class="col-sm-4 control-label">Fecha de ingreso</label>
  <div class="col-sm-2"> <label class="control-label">Entre el:</label>
  </div>
  <div class="col-sm-2 noPadL"> <input class="form-control inputFecha pad2" id="inputDesde" name="fecha_ini" type="Text" value="<% out.println(fecha_inicial);%>"> </div>
  <div class="col-sm-2"> <label class="control-label">Hasta el:</label>
  </div>
  <div class="col-sm-2 noPadL"> <input class="form-control inputFecha pad2" id="inputHasta" type="text value="<% out.println(fecha_actual);%>"> </div>
  </div>
  <div class="form-group"> <label for="selectEstado" class="col-sm-4 control-label">Estado de la falla</label>
  <div class="col-sm-8">
  <select class="form-control" id="selectEstado" name="estado" size="1" id="estado">
 <%
              if("Todas".equals(estadoString)){
                out.println("<option selected value=\"0\">Todas</option>");
                out.println("<option value=\"1\">Iniciada</option>");
                out.println("<option value=\"2\">Cerrada</option>");
                out.println("<option value=\"3\">Con solicitud de cierre</option>");
              }
              else {
                if("Iniciada".equals(estadoString)){
                  out.println("<option selected value=\"1\">Iniciada</option>");
                  out.println("<option value=\"0\">Todas</option>");
                  out.println("<option value=\"2\">Cerrada</option>");
                  out.println("<option value=\"3\">Con solicitud de cierre</option>");
                }
                else{
                  if("Cerrada".equals(estadoString)){
                    out.println("<option selected value=\"2\">Cerrada</option>");
                    out.println("<option value=\"0\">Todas</option>");
                    out.println("<option value=\"1\">Iniciada</option>");
                    out.println("<option value=\"3\">Con solicitud de cierre</option>");
                  }
                  else{
                    if("Con solicitud de cierre".equals(estadoString)){
                      out.println("<option selected value=\"3\">Con solicitud de cierre</option>");
                      out.println("<option  value=\"0\">Todas</option>");
                      out.println("<option value=\"1\">Iniciada</option>");
                      out.println("<option value=\"2\">Cerrada</option>");
                    }
                    else{
                      out.println("<option value=\"0\" selected>Todas</option>");
                      out.println("<option value=\"1\">Iniciada</option>");
                      out.println("<option value=\"2\">Cerrada</option>");
                      out.println("<option value=\"3\">Con solicitud de cierre</option>");
                    }
                  }
                }
              }
              %> </select>
  <span id="helpBlock" class="help-block">Nota: Incluye todo tipo de evento.</span> </div>
  </div>
  <div class="boxOpciones">
  <div class="form-group">
  <div class="col-sm-12"> <a href="javascript:void(0)" class="botoVerde busca" value="Buscar fallas" onclick="valida_envia()" ><span class="glyphicons glyphicons-search"></span>Buscar</a>
  </div>
  </div>
  </div>
  
  
  <input name="var_sistema"     type="hidden" value="">
                  <input name="var_subsistema"  type="hidden" value="">
                  <input name="var_dispositivo" type="hidden" value="">
                  <input name="hacia" type="hidden" value="Buscar">
                  
</form>
</div>

 <%
                //System.out.println("buscar_fallas:" + sistemaParaIngresar + " - " + subsistemaParaIngresar + " - " + dispositivoParaIngresar + " -/- " + nombre_sistema + " - " + nombre_subsistema + " - " + nombre_dispositivo);
                Integer ceroInteger = new Integer(0);

                if (ceroInteger.equals(sistemaParaIngresar) || ceroInteger.equals(subsistemaParaIngresar) || ceroInteger.equals(dispositivoParaIngresar)){%>
                &nbsp;
                <%
                }
                else{%>
                  <a href="fallasAction.do?hacia=ingresar_falla&sistema=<%=sistemaParaIngresar%>&nomSistema=<%=nombre_sistema%>&subsistema=<%=subsistemaParaIngresar%>&nomSubsistema=<%=nombre_subsistema%>&dispositivo=<%=dispositivoParaIngresar%>&nomDispositivo=<%=nombre_dispositivo%>&vieneDe=ingresar_fallas">Ingresar evento a este dispositivo</a></font>
                <%
                }%>



<div class="box boxpost encuentra">
<h4>Fallas <span class="pull-right"><small>Exportar:</small> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_excel.png"></a> <a href="javascript:void(0)" class="pull-right"><img src="img/ico_pdf.png"></a></span></h4>

 <display:table  name="listafallas" export="true" class="table table-striped table-bordered table-hover" id="lf" pagesize="20" requestURI="fallasAction.do">
          <display:column media="html excel pdf" title="fecha ingreso" sortable="false" sortProperty="fecha_ingreso2"><%=((FallaVO)lf).get_fecha_ingreso2() %></display:column>
          <display:column  media="html excel pdf" title="evento" sortable="false" sortProperty="titulo"><%=((FallaVO)lf).get_titulo() %></display:column>

          <display:column  media="html" title="plazo"  sortable="false" sortProperty="fecha_plazo2">
             <%
             if(((FallaVO)lf).get_estado().intValue()==0){
               out.print(" &nbsp; ");
             }
             else{
               if(((FallaVO)lf).get_fecha_cierre2()==null){//INICIADA Y NO CERRADA
               out.print(" - ");  }
             else{
               Date d1 = sdf.parse(((FallaVO)lf).get_fecha_cierre2());
               Date d2 = sdf.parse(((FallaVO)lf).get_fecha_plazo2());
               String relation;
               if (d1.equals(d2)){//relation = "=";
                 relation = "OK";}
               else{
                 if (d1.before(d2)){//relation = "<";
                 relation = "<font size='1' color='red'><img src='imagenes/clock.png' title='Dentro del plazo' width='17' height='17' alt='' border='0'></font>";}
                 else{//relation = ">";
                 relation = "<font size='1' color='red'><img src='imagenes/clock_red.png' title='Fuera del plazo' width='17' height='17' alt='' border='0'></font>";}
               out.print("<div align='center'>" + relation+ "</div>");
               }
             }
           }
           %>
          </display:column>
          <display:column media="excel pdf" title="plazo"  sortable="false" sortProperty="fecha_plazo2">
             <%
             if(((FallaVO)lf).get_estado().intValue()==0){
               out.print(" &nbsp; ");
             }
             else{
               if(((FallaVO)lf).get_fecha_cierre2()==null){//INICIADA Y NO CERRADA
               out.print(" - ");  }
             else{
               Date d1 = sdf.parse(((FallaVO)lf).get_fecha_cierre2());
               Date d2 = sdf.parse(((FallaVO)lf).get_fecha_plazo2());
               String relation;
               if (d1.equals(d2)){//relation = "=";
                 relation = "OK";}
               else{
                 if (d1.before(d2)){//relation = "<";
                 relation = "Dentro del plazo";}
                 else{//relation = ">";
                 relation = "Fuera del plazo";}
               out.print(relation);
               }
             }
           }
           %>
          </display:column>
          <display:column media="html excel pdf" title="estado" sortable="false" sortProperty="estado" >
           <%
           if(((FallaVO)lf).get_estado().intValue()==1){
             out.print("Iniciada");
           }
           if(((FallaVO)lf).get_estado().intValue()==2){
             out.print("Cerrada");
           }
           if(((FallaVO)lf).get_estado().intValue()==3){
             out.print("<font size='1' color='red'>Con solicitud de cierre</font>");
           }
           if(((FallaVO)lf).get_estado().intValue()==0){
             out.print(" &nbsp; ");
           }%>
         </display:column>

          <display:column  media="html" title=" - " sortable="false" sortProperty="link" >
          <%
          if(((FallaVO)lf).get_estado().intValue()==0){
            out.print(" &nbsp; ");
          }
          else{ %>
          <a href="fallasAction.do?hacia=ver_detalle_falla&&id_falla=<%out.print(((FallaVO)lf).get_id_falla());%>" title="Ver detalle de la falla"><img src="imagenes/icono_detalle.png" width="17" height="17" alt="" border="0"></a>
            <%
            //if(idAdmin.equals(idPerfil) || idGFallas.equals(idPerfil)){
              if(idPerfil.equals(cero)){
              }
              else{%>
            <a href="fallasAction.do?hacia=eliminar_falla&&id_falla=<%out.println(((FallaVO)lf).get_id_falla());%>" title="Eliminar falla" onClick='return confirmaEliminacion()'><img src="imagenes/icono_delete.png" alt="" width="17" height="17" border="0"></a>
              <%
            }
          }%>
        </display:column>


        <display:setProperty name="basic.msg.empty_list" value="No se encontraron elementos para mostrar"/>
        <display:setProperty name="basic.empty.showtable" value="true"/>
        <display:setProperty name="basic.msg.empty_list_row" value="<tr class='empty'><td colspan='0'>No se encontraron elementos para mostrar.</td></tr></tr>"/>
        <display:setProperty name="export.banner" value="<div class='exportlinks'>Exportar: {0}</div>"/>
        <display:setProperty name="paging.banner.placement" value="bottom"/>
        <display:setProperty name="paging.banner.no_items_found" value="<span class='pagebanner'>&nbsp;</span>"/>
        <display:setProperty name="paging.banner.one_item_found" value="<span class='pagebanner'>Se encontr&oacute; un {0}.</span>"/>
        <display:setProperty name="paging.banner.all_items_found" value="<span class='pagebanner'>{0} {1} encontrados.</span>"/>
        <display:setProperty name="paging.banner.some_items_found" value="<span class='pagebanner'>{0} {1} encontrados.Mostrando {2} a {3}.</span>"/>
        <display:setProperty name="paging.banner.full" value="<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Ant</a>]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.first" value="	<span class=\"pagelinks\">[Primero/Prev]{0}[<a href=\"{3}\">Sgte</a>/<a href=\"{4}\">Ultimo</a>]"></display:setProperty>
        <display:setProperty name="paging.banner.last" value="	<span class=\"pagelinks\">[<a href=\"{1}\">Primero</a>/<a href=\"{2}\">Prev</a>]{0}[Sgte/Ultimo]"></display:setProperty>
        <display:setProperty name="export.csv" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.rtf" value="false"/>
        <display:setProperty name="export.excel.filename" value="Bitacora.xls"/>
        <display:setProperty name="export.pdf.filename" value="Bitacora.pdf"/>
        <display:setProperty name="export.xml.filename" value="Bitacora.xml"/>
        <display:setProperty name="export.csv.filename" value="Bitacora.csv"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.amount" value="list"/>
        </display:table>
        
        

<div class="col-sm-5">
<ul class="pagination">
  <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">�</span></a><br>
    <br>
  <br>
</li>
  <li class="active"><a href="#">1</a><br>
    <br>
  <br>
</li>
  <li><a href="#">2</a><br>
    <br>
  <br>
</li>
  <li><a href="#">3</a><br>
    <br>
  <br>
</li>
  <li><a href="#" aria-label="Next"><span aria-hidden="true">�</span></a><br>
    <br>
  <br>
</li>
</ul>
</div>
</div>
</div>
</div>
<div class="verMas"> <a href="javascript:history.back()"><span class="glyphicons glyphicons-undo"></span> Volver</a> <a href="javascript:void(0)" class="pull-right"><span class="glyphicons glyphicons-circle-exclamation-mark"></span> Ayuda</a>
</div>
</div>
<br>
</div>
<!-- /row --> </div>
<!-- /container --> </div>

<!-- /main -->
<div class="container"> <footer> </footer>
<div class="row">
<div class="col-sm-12">
<p>Unidad Operativa de Control de Tr�nsito, <span id="pie"></span></p>
</div>
</div>
</div>

<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap-datepicker.min.js"></script>
<script src="js/bootstrap-datepicker.es.min.js"></script>
<script src="js/moment.js"></script>
<script src="js/truncate.js"></script>
<script src="js/fullcalendar.min.js"></script>
<script src="js/uoct.js"></script>
<script src="js/uoct_falla1.js"></script>
</body></html>