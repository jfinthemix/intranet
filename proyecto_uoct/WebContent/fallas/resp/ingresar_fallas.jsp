<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.*,proyecto_uoct.fallas.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
List lista_sistema      = (List) request.getAttribute("lista_sistema");

Integer sistemaDeBuscar         = (Integer) request.getAttribute("sistema");
Integer subsistemaDeBuscar      = (Integer) request.getAttribute("subsistema");
Integer dispositivoDeBuscar     = (Integer) request.getAttribute("dispositivo");

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


%>
<html>
<head>
<title>Bit&aacute;cora de dispositivos</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script language="JavaScript" type="text/javascript">
function cambiarTitulo(){
  document.form_nueva_falla.titulo.value = "Falla " + document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
  document.form_nueva_falla.nomDispositivo.value = document.form_nueva_falla.dispositivo.options[document.form_nueva_falla.dispositivo.selectedIndex].text;
}
function ocultarFila(num,ver) {
  dis= ver ? '' : 'none';
  tab=document.getElementById('tabla');
  tab.getElementsByTagName('tr')[num].style.display=dis;
}



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


</script>
</head>
<body bgcolor="#FFFFFF" text="#000000" onload="valida_cambioInicial();">
<form name="form_nueva_falla" method="post" action="fallasAction.do">
  <table width="85%" border="0" align="center">
    <tr>
      <td colspan="2">Bitácora, eventos de dispositivos</td>
    </tr>
    <tr>
      <td colspan="2"><font color="red">
        <%
      if (mensaje != null)
        out.print(mensaje);
        %>
        </font>&nbsp;</td>
    </tr>
    <tr>
      <td colspan="2"> <table width="100%" border="1" align="center" id="tabla">
          <tr>
            <td width="35%" bgcolor="#ADD8E4"> <div align="right"><strong>sistema</strong></div></td>
            <td width="65%">
              <%
                Integer valorCero=new Integer(0);
                boolean bandera = true;
                %>

                <%
                if(sistemaDeBuscar!=null){
                  out.println("<option value=\"" + sistemaDeBuscar + "\" selected>" + nomSistemaDeBuscar + "</option>");
                }
                else{
                  %>


                <select name="sistema" size="1" id="sistema" onChange="javascript:valida_cambio();carga_lista2(subsistema, dispositivo, M_Dispositivo);">
                  <option value=0> - </option>
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

             <%  }%>

            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>subsistema</strong></div></td>
            <td>
              <%
                if(subsistemaDeBuscar!=null){
                  out.println("<option value=\"" + subsistemaDeBuscar + "\" selected>" + nomSubsistemaDeBuscar + "</option>");
                }
                else{
              %>
              <select name="subsistema" size="1" id="subsistema" onChange="javascript:valida_cambio2();">
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

            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>dispositivo</strong></div></td>
            <td>

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

                <select name="dispositivo" size="1" id="dispositivo" onChange="cambiarTitulo()">
                  <%

                  Iterator iii = lista_sistemaSubsistemaDispositivo.iterator();
                  String nombre_dispositivo="";
                  while (iii.hasNext()) {
                    FallaVO dispositivo = (FallaVO) iii.next();
                    if(dispositivo.get_id_subsistema().equals(var_subsistema)){

                      if(dispositivo.get_id_dispositivo().equals(var_dispositivo)){
                        out.println("<option value=\"" + dispositivo.get_id_dispositivo()+ "\" selected>" + dispositivo.get_nombre_dispositivo() + "</option>");
                        nombre_dispositivo=dispositivo.get_nombre_dispositivo();
                      }
                      else{
                        if(var_dispositivo==null){
                          out.println("<option value=\"" + "0" + "\" selected>" + " - " + "</option>");
                        }
                      }
                      out.print("<option value=\"" + dispositivo.get_id_dispositivo() + "\">" + dispositivo.get_nombre_dispositivo() + "</option>");
                    }
                  }%>
                </select>

                <%  }%>

              </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>hora ingreso</strong></div></td>
            <td> <input name="hora" type="text" size="6" maxlength="5" onKeyUp = "this.value=formateahora(this.value);">
              <font size="3">&nbsp;horas.(HH:MM)</div></font> <input name="fecha_actual" type="hidden" value="<% out.print(fecha_actual);%>">
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>evento</strong></div></td>
            <td> <select name="evento">
                <option selected onclick="ocultarFila(5,true)" value="falla">Falla</option>
                <option onClick="ocultarFila(5,false)" value="comentario">Comentario</option>
              </select> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>t&iacute;tulo del asunto</strong></div>
              <br/> <div align="right"><strong>enviar mail c/cc a</strong></div></td>
            <td> <font size="1">
              <input type="text" name="titulo" size="50" value="<% if(dispositivoDeBuscar!=null) out.print("Falla "+nomDispositivoDeBuscar);%>">
              <input type="hidden" name="nomDispositivo" size="50" value="">
              <br>
              <br>
              <input type="text" name="enviar_a" size="50" value="<%//out.print("manter@auter.cl, faraya@auter.cl, gf@uoct.cl");%>">
              </font></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>mensaje (problema)</strong></div></td>
            <td> <textarea name="problema" cols="35" rows="2"></textarea>
                 <label> <br>enviar mail a empresa mantenedora y GF
                   <input type="checkbox" name="enviaMail" value="si" checked>
                 </label>
            </td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td colspan="2"> <table width="60%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td><font size="2">
              <input type="submit" name="hacia" value="Grabar">
              </font></td>
            <td><div align="right"><font size="2">
                <input type="reset" name="BotonCancelar" value="Cancelar">
                </font></div></td>
          </tr>
        </table> <script language="JavaScript" type="text/javascript">
      var frmvalidator  = new Validator("form_nueva_falla");
      frmvalidator.addValidation("problema","req","Debe ingresar un problema");
      frmvalidator.addValidation("hora","req","Debe ingresar la hora");
      frmvalidator.addValidation("hora","maxlen=5","Ejemplo de hora: 19:55");
      </script> </td>
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
