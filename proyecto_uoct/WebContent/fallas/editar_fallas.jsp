<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.fallas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String)  request.getAttribute("mensaje");
SimpleDateFormat sdf    = new SimpleDateFormat("dd-MM-yyyy");
Calendar hoyG           = Calendar.getInstance();
java.util.Date hoyJ     = new Date();
String fecha_actual     = sdf.format(new java.util.Date());
FallaVO falla           = (FallaVO) request.getAttribute("falla");
List listadetallefallas = (List) request.getAttribute("listadetallefallas");
Integer idPerfil        = (Integer) request.getAttribute("idPerfil");
Integer cero            = new Integer(0);
String evento           = (String) request.getAttribute("evento");

String dia              = "";
String mes              = "";
String anio             = "";
dia                     = fecha_actual.substring(0,2);
mes                     = fecha_actual.substring(3,5);
anio                    = fecha_actual.substring(6);
String fecha_cierre     = dia+"-"+mes+"-"+anio;

//String fecha_cierre     = (String) request.getAttribute("fecha");

Integer id_dispositivo  = new Integer(0);
Integer id_falla        = (Integer)  new Integer(0);
Integer id_comentario   = (Integer)  new Integer(0);
String sistema          = "";
String subsistema       = "";
String dispositivo      = "";
String titulo           = "";
Integer estado          = new Integer(0);
String fecha_ingreso2   = "";
String usu_inicia       = "";
String aux              = "largo";
String fecha_cierre2    = "";
String usu_cierra       = "";
String fecha_plazo2     = "";

String desabilitar      = "";
String inicioColor      = "";
String finColor         = "";


if(evento.equals("falla")){
  if(listadetallefallas!=null){
    Iterator i=listadetallefallas.iterator();
    while(i.hasNext()){
      FallaVO encabezado = (FallaVO) i.next();
      //en caso de ser falla o comentario
      id_dispositivo     = encabezado.get_id_dispositivo();
      id_falla           = encabezado.get_id_falla();
      sistema            = encabezado.get_nombre_sistema();
      subsistema         = encabezado.get_nombre_subsistema();
      dispositivo        = encabezado.get_nombre_dispositivo();
      titulo             = encabezado.get_titulo();
      estado             = encabezado.get_estado();
      fecha_ingreso2     = encabezado.get_fecha_ingreso2();
      aux                = aux + "" + fecha_cierre2;
      usu_inicia         = encabezado.get_usu_inicia();
      fecha_cierre2      = encabezado.get_fecha_cierre2();
      usu_cierra         = encabezado.get_usu_cierra();
      fecha_plazo2       = encabezado.get_fecha_plazo2();
    }
  }
}
if(evento.equals("comentario")){
  if(listadetallefallas!=null){
    Iterator i=listadetallefallas.iterator();
    while(i.hasNext()){
      FallaVO encabezado = (FallaVO) i.next();
      //en caso de ser falla o comentario
      id_dispositivo     = encabezado.get_id_dispositivo();
      id_comentario      = encabezado.get_id_falla();
      sistema            = encabezado.get_nombre_sistema();
      subsistema         = encabezado.get_nombre_subsistema();
      dispositivo        = encabezado.get_nombre_dispositivo();
      fecha_ingreso2     = encabezado.get_fecha_ingreso2();
      aux                = aux + "" + fecha_cierre2;
      usu_inicia         = encabezado.get_usu_inicia();
    }
  }
}
%>
<html>
<head>
<title>Bit&aacute;cora de dispositivos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script language="JavaScript" src="calendar1.js" type="text/javascript">
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
</script>
<link href="../util/styla.css" rel="stylesheet" type="text/css">

</head>
<body bgcolor="#FFFFFF" text="#000000">
<form name="form_editar_falla" method="post" action="fallasAction.do">
  <table width="100%" border="0" align="left">
    <tr>
      <td colspan="2"><h3 align="left">Bitácora, eventos de dispositivos</h3></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <%
    if (mensaje != null) out.print("<tr><td><font color='red'>" + mensaje + "</font></td></tr>");
    %>
    <tr>
      <td> <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1">
          <tr>
            <td width="35%" bgcolor="#ADD8E4"><div align="right"><strong>sistema</strong></div></td>
            <td width="65%" ><strong>
              <% out.println(sistema); %>
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>subsistema</strong></div></td>
            <td><strong>
              <% out.println(subsistema); %>
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>dispositivo</strong></div></td>
            <td><strong>
              <% out.println(dispositivo);%>
              </strong></td>
          </tr>

          <%
          if(evento.equals("falla")){ %>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>falla</strong></div></td>
            <td> <% out.println(titulo); %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>estado</strong></div></td>
            <td> <%
            if(estado.intValue()==1){
              out.println("Iniciada");
            }
            if(estado.intValue()==2){
              out.println("Cerrada");
              desabilitar= "disabled='disabled'";
              inicioColor="<font color='#9f9f9f'";
              finColor=">";
            }
            if(estado.intValue()==3){
              out.println("Con solicitud de cierre");
            }
           %> </td>
          </tr>
          <%
          } %>

          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>ingreso</strong></div></td>
            <td> <% out.print(fecha_ingreso2); %>
              -
              <% out.print(usu_inicia); %> </td>
          </tr>

          <%
          if(evento.equals("falla")){ %>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>cierre</strong></div></td>
            <td> <%
            if(aux.length()>10){
              out.print(fecha_cierre2 + " - " + usu_cierra);
            }
            else{
              out.print(" - ");
            }
            %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>plazo cierre</strong></div></td>
            <td> <% out.print(fecha_plazo2); %> </td>
          </tr>
          <%
          } %>

        </table></td>
    </tr>
    <tr>
      <td>&nbsp; </td>
    </tr>
    <tr>
      <td>
        <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0">
        <%
        if(evento.equals("falla")){ %>
        <tr>
          <td colspan="3" width="85%">

            <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
              <tr>
                <td width="30%">
                  <font size="2">Mensaje:
                    <input name="dispositivo" type="hidden" value="<%out.print(id_dispositivo);%>" >
                      <input name="id_falla" type="hidden" value="<%out.print(id_falla);%>">
                        <input name="fecha_actual" type="hidden" value="<%out.print(fecha_actual);%>">
                          <input name="titulo" type="hidden" value="<% out.print(titulo); %>">
                            <%
                            if(idPerfil.equals(cero)){ //auter o Ing. SC%>

                               <select name="solicitarcerrar" size="1" id="solicitarcerrar" <% out.print(desabilitar);%>>
                                  <option selected value="si">Solicitar cierre</option>
                                  <option value="no">Comentario</option>
                                </select>
                              <%
                              }
                              else{ //Admin o GF que tienen la funcionalidad%>
                              <select name="cerrar" size="1" id="cerrar" <% out.print(desabilitar);%>>
                                <option selected value="si">Cerrar reclamo</option>
                                <option value="no">Comentario</option>
                              </select>
                                <%
                                }
                                %>
                                </font>
                              </td>
                              <td width="30%">
                                <% //SI EL USUARIO ES SSS U OPERADOR DE SALADA DE CONTROL
                                if(idPerfil.equals(cero)){
                                  //SE OCULTAN LA HORA Y EL CALENDARIO PARA Q NO APARESCA EL BUG

                                  %>
                                 <input name="fecha_cierre" type="hidden" value="<% out.println(fecha_cierre);%>" size="10" maxlength="20" readonly>
                                 <input name="hora" type="hidden" size="6" maxlength="5" value="00:00">

                                 <textarea cols="45" rows="1" name="comentario"
                                 <% out.print(desabilitar);%>>A las ______ hrs. se pre-chequea el dispositivo, encontrándose en correcto funcionamiento.</textarea>
                            <%  }
                                else{ //SI EL USUARIO ES ADMIN O GESTION DE FALLAS %>
                                   <input name="fecha_cierre" type="Text" value="<% out.println(fecha_cierre);%>" size="10" maxlength="20" readonly>
                                   <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha"></a>

                                   <input name="hora" type="text" size="6" maxlength="5" onKeyUp = "this.value=formateahora(this.value);">
                                     <font size="3">&nbsp;horas.(HH:MM)</div></font>
                                   <textarea cols="45" rows="1" name="comentario"
                                     <% out.print(desabilitar);%>>Se recepciona el dispositivo, encontrándose en correcto funcionamiento.</textarea>
                               <% }%>
                    <input name="comentario" type="hidden" value="0" >
                  </td>
                </tr>
              </table>
               <script language="JavaScript" type="text/javascript">
                 var frmvalidator  = new Validator("form_editar_falla");
                 frmvalidator.addValidation("hora","req","Debe ingresar la hora");
                 frmvalidator.addValidation("hora","maxlen=5","Ejemplo de hora: 09:55");
               </script>
               <script language="JavaScript" type="text/javascript">
                 var cal1 = new calendar1(document.forms['form_editar_falla'].elements['fecha_cierre']);
                 cal1.year_scroll = true;
                 cal1.time_comp = false;
               </script>
            </td>

            <td width="15%">
              <div align="center">
                <font size="2">
                  <input name="hacia" type="submit" value="Grabar detalle" <% out.print(desabilitar);%>>
                </font>
              </div>
            </td>
          </tr>
          <%
          }%>
        <display:table name="listadetallefallas" export="true" class="its" id="ldf" pagesize="10" requestURI="fallasAction.do">

        <display:column media="html excel pdf" title="comentario" sortable="false" sortProperty="titulo"><%=((FallaVO)ldf).get_comentario() %></display:column>
        <display:column media="html excel pdf" title="actualización" sortable="false" sortProperty="estado"><% out.print(((FallaVO)ldf).get_fecha_actualiza2()+" hrs.");%></display:column>
        <display:column media="html excel pdf" title="usuario" sortable="false" sortProperty="estado"><%=((FallaVO)ldf).get_username() %></display:column>

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
        <display:setProperty name="export.excel.filename" value="registroBitacora.xls"/>
        <display:setProperty name="export.pdf.filename" value="registroBitacora.pdf"/>
        <display:setProperty name="export.xml.filename" value="registroBitacora.xml"/>
        <display:setProperty name="export.csv.filename" value="registroBitacora.csv"/>
        <display:setProperty name="export.pdf.label" value="<img src='../util/img/pdf.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.excel.label" value="<img src='../util/img/excel.gif' width='10' height='10' border='0'>"/>
        <display:setProperty name="export.amount" value="list"/>
        </display:table>


        </table></td>
    </tr>
  </table>
  <br>
  <hr><div align="right"><a href="../ayuda/bitacora.html" target="_blank">Ayuda</a></div>
</form>


<script type="text/javascript" language="JavaScript">
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

</body>
</html>
