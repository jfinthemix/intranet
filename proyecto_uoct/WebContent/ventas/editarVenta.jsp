<%@page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@ page import="proyecto_uoct.ventas.VO.*,java.text.SimpleDateFormat,java.util.List,java.util.Iterator,java.lang.Float" %>
<%
List tiposInf = (List) request.getAttribute("tiposInf");
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
VtaVO venta=(VtaVO)request.getAttribute("venta");


%>
<html>
<head>
<title>Nueva Venta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<!--   Para el traspaso de variables entre Ventanas -->
<script type="text/javascript">
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }


function pasaCli(nomCli, idCli){
    form1.nomCli.value = nomCli;
    form1.idCli.value = idCli;
    otra.window.close();
  }


<!-- para el traspaso de variables------>

function agregarItem(cant,tipo,descripcion,lista){
if(validaCant(cant) && validaLargoDesc(descripcion) ){
var v=cant.value +"--"+tipo.options[tipo.selectedIndex].value+"!!"+descripcion.value;
var t=cant.value +" "+tipo.options[tipo.selectedIndex].text+" - "+descripcion.value;

var no = new Option();
no.value =v;
no.text = t;
lista.options[lista.options.length] = no;
cant.value="";
descripcion.value="";
}
}

function validaCant(cant){
if(cant.value==""){
alert("Indique la cantidad de Items de la venta");
cant.focus();
return false;
}
if(isNaN(cant.value)){
alert("Cantidad solamente puede ser un número");
cant.focus();
return false;

}
return true;
}


function validaLargoDesc(des){
	if (des.value.length>150){
	alert("La cantidad de caracteres de la descripción supera la cantidad permitida. Máximo 150 caracteres.Cantidad actual:"+des.value.length+" caracteres");
	des.focus();
	return false;
	}
	return true;
}


function borrar(CONTROL){
for (var i =0;i<CONTROL.length;i++){
if (CONTROL.options[i].selected ==true){
CONTROL.options[i]=null;
}
}
}

  <!---Selecciona toda la lista del detalle de la venta-->
function SelectAllList(CONTROL){
for(var i = 0;i < CONTROL.length;i++){
CONTROL.options[i].selected = true;
}
}



</script>
<!--calendario-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>

<script type="text/javascript">

function confirmAnulacion(){
var resp=confirm("Al anular una venta, ésta NO SERÁ ELIMINADA,\n simplemente se indicará la anulación \ny no se podrán registrar nuevas fechas\n ni cambiar su estado.\n¿ESTÁ SEGURO QUE DESEA ANULAR LA VENTA? ");
return resp;
}

</script>




</head>
<body>
<table width="750" border="0">
  <tr>
    <td colspan="2"><h3>Editar Proceso de Venta</h3></td>
  </tr>
  <form name="form1" method="post" action="ventasAction.do">
    <tr><td colspan="2"><strong> <font color="red"> Venta : <%=venta.getCodVenta() %></font></strong></td></tr>

  <!-- -->

  <%
  if(venta.getIdEstado().intValue()==1 || venta.getIdEstado().intValue()==2){
  %>

  <tr>


    <td colspan="2"> <input type="hidden" name="accion" value="actualizarVta" />
      <input type="hidden" name="idVenta" value="<%=venta.getIdVenta()%>" />



        <table width="700" border="0" align="left">
          <tr>
            <td> <table width="650" border="1" align="left">
              <tr>
                <td width="141" bgcolor="#ADD8E4"> <div align="right"><strong>Fecha
                  de Inicio:</strong></div></td>
                  <td width="512"><input type="text" name="fecha" size=10 readonly="readonly" value="<%=sdf.format(venta.getFechaRecepcion())%>">
                    &nbsp;<a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
                    </a> </td>
                  </tr>
                  <tr>
                    <td bgcolor="#ADD8E4"> <div align="right"> <strong>Cliente*:</strong>
                    </div></td>
                    <td> <input type="hidden" name="idCli" value="<%=venta.getCliente().getIdCliente() %>"/>
                      <input type="text" name="nomCli" readonly="readonly" value="<%=venta.getCliente().getNomCli() %>"/>
                        &nbsp; <a href="ventasAction.do?accion=busCliVta_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=350,height=500, scrollbars=1'); return false;">Buscar
                          Cliente</a> </td>
                        </tr>
                        <tr>
                          <td height="124" bgcolor="#ADD8E4"> <div align="right"> <strong>Detalle
                            de la Venta*:</strong> </div></td>
                            <td> <table width="101%" border="1">
                              <tr bgcolor="#A6F7BA">
                                <td width="12%"> <div align="center"> <strong>cantidad</strong>
                                </div></td>
                                <td width="48%"> <div align="center"> <strong>tipo Info</strong>
                                </div></td>
                                <td width="40%"> <div align="center">&nbsp;<strong>Detalle</strong>
                                </div></td>
                              </tr>
                              <tr>
                                <td> <div align="right">
                                  <input name="cantidad" type="text" maxlength="4" size="4">
                                  </div></td>
                                  <td> <div align="center">
                                    <select name="tipoInfo">
                                      <%
                                      if (tiposInf != null) {
                                        Iterator itip = tiposInf.iterator();
                                        while (itip.hasNext()) {
                                          InfoVtaVO info = (InfoVtaVO) itip.next();
                                          %>
                                          <option value="<%=info.getIdInfo()%>"><%=info.getTipoInfo() %> </option>
                                          <%
                                          }
                                        }
                                        %>
                                        </select>
                                      </div></td>
                                      <td><textarea name="descVta" id="descVta" cols="35" rows="3"></textarea></td>
                                    </tr>
                                    <tr>
                                      <td colspan="3"><table width="132" border="1" align="center">
                                        <tr>
                                          <td width="74"> <div align="center">
                                            <input type="button" name="Submit2" value="Agregar a la lista" onClick="agregarItem(this.form.cantidad,this.form.tipoInfo,this.form.descVta,this.form.detVenta);">
                                            </div></td>
                                          </tr>
                                          <tr>
                                            <td> <div align="center">
                                              <input type="button" name="Submit3" value="Eliminar de la lista" onclick="borrar(this.form.detVenta);">
                                              </div></td>
                                            </tr>
                                          </table>


                                        </td>
                                      </tr>
                                      <tr>
                                        <td colspan="3"><div align="center">
                                          <select name="detVenta" size="5" multiple>
                                            <%if(venta.getDetVenta()!=null){
                                            Iterator iv=venta.getDetVenta().iterator();
                                            while(iv.hasNext()){
                                              InfoVtaVO inf=(InfoVtaVO)iv.next();%>
                                              <option value="<%=inf.getCantidad() %>--<%=inf.getIdInfo()%>!!<%
                                                if (inf.getDescripcion()!=null){
                                                  out.print(inf.getDescripcion());
                                                }
                                                %>"><%=inf.getCantidad()%>
                                                <%=inf.getTipoInfo() %>-<%
                                                if (inf.getDescripcion()!=null){
                                                  out.print(inf.getDescripcion());
                                                }%></option>
                                                <% }
                                                }
                                                %>
                                                </select>
                                              </div></td>
                                            </tr>
                                          </table>



                                        </td>
                                      </tr>

                                      <tr>
                                        <td bgcolor="#ADD8E4"> <div align="right"> <strong>Comentario:</strong>
                                        </div></td>
                                        <td> <textarea name="comentario" cols="50" rows="6"><% if(venta.getComentario()!=null && !("").equals(venta.getComentario())){out.print(venta.getComentario());} %></textarea>
                                        </td>
                                      </tr>
                                      <tr>
                                        <td bgcolor="#ADD8E4"> <div align="right"> <strong>Estado:</strong>
                                        </div></td>
                                        <td> <%=venta.getEstado() %> </td>
                                      </tr>
                                    </table></td>
                                  </tr>
                                </table></td>
                              </tr>
                              <tr>
                                <td colspan="2"><input type="submit" name="Submit1" value="Guardar los cambios" onClick="javascript:SelectAllList(this.form.detVenta);">
                                  <input name="reset" type="reset" value="Restablecer valores" /> </td>
                                </tr>

                                <!-- -->

                               <% }
                              else{
                              %>

                              <tr>
                                <td align="center" colspan="2" >
                                  <font color="red">
                                    Los datos descriptivos de la venta no pueden ser editados.
                                  </font>
                              </td></tr>

                             <%} %>
    <tr>
      <td width="112" bgcolor="#F7FBC4"><strong>Operaciones disponibles:</strong> </td>
      <td width="628">
        <%if(venta.getIdEstado().intValue()==1){%>
        <div align="left"><a href="ventasAction.do?accion=regFechaCotizacion&idVenta=<%=venta.getIdVenta()%>">Registrar
          Fecha de entrega de Cotización</a></div>
        <%}%>
        <%if(venta.getIdEstado().intValue()==2){%>
        <div align="left"><a href="ventasAction.do?accion=regFechaPago&idVenta=<%=venta.getIdVenta()%>">Registrar
          Fecha de pago</a></div>
        <%}%>
        <%if(venta.getIdEstado().intValue()==3){%>
        <div align="left"><a href="ventasAction.do?accion=regFechaEntregaInfo&idVenta=<%=venta.getIdVenta()%>">Registrar
          Fecha de entrega de información</a></div>
        <%}%>
	   <%if(venta.getIdEstado().intValue()==4){%>
        <div align="left"><a href="ventasAction.do?accion=regFechaFinVenta&idVenta=<%=venta.getIdVenta()%>">Finalizar proceso de venta</a></div>
        <%}%>
        <div align="left"><a href="ventasAction.do?accion=anularVenta&idVenta=<%=venta.getIdVenta()%>" onClick='return confirmAnulacion()'>Venta
          no efectuada</a></div></td>
    </tr>
  </form>
</table>

  <%
  if(venta.getIdEstado().intValue()==1 || venta.getIdEstado().intValue()==2){
  %>

<script language="JavaScript" type="text/javascript">

var frmvalidator  = new Validator("form1");
frmvalidator.addValidation("comentario","maxlen=500","hasta 500 caracteres en el comentario ");
frmvalidator.addValidation("detVenta","req");
</script>



<script language="JavaScript" type="text/javascript">
var cal1 = new calendar1(document.forms['form1'].elements['fecha']);
cal1.year_scroll = true;
cal1.time_comp = false;
//-->
</script>
<%} %>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>
