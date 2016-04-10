<%@page contentType="text/html; charset=iso-8859-1" language="java" errorPage=""%>
<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.ventas.VO.InfoVtaVO"%>
<%List tiposInf = (List) request.getAttribute("tiposInfoVta");%>
<html>
<head>
<title>Nueva Venta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<link href="../util/styla.css" rel="stylesheet" type="text/css">
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



</script><!--calendario-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script></head>
<body>
<table width="750" border="0">
  <tr>
    <td><h3>Iniciar Proceso de Venta</h3></td>
  </tr>
  <form name="form1" method="post" action="ventasAction.do">
  <tr>
    <td>  <input type="hidden" name="accion" value="regProcesoVta" />
      <table width="700" border="1" align="left">
          <tr>
            <td width="133" bgcolor="#ADD8E4"> <div align="right"><strong>C&oacute;digo
                de la Venta*:</strong></div></td>
            <td width="595"><input name="codVta" type="text" id="codVta" maxlength="20"> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Fecha de Inicio*:</strong>
              </div></td>
            <td><input type="text" name="fechaPresentacion" size=8 readonly="readonly">
              &nbsp; <a href="javascript:cal1.popup();"> <img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
              </a></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>Cliente*:</strong>
              </div></td>
            <td> <input type="hidden" name="idCli" value=""/> <input type="text" name="nomCli" readonly="readonly"/>
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
                    </table></td>
                </tr>
                <tr>
                  <td colspan="3"><div align="center">
                      <select name="detVenta" size="5" multiple>
                      </select>
                    </div></td>
                </tr>
              </table></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"> <strong>Comentario:</strong>
              </div></td>
            <td> <textarea name="comentario" cols="50" rows="6"></textarea>
            </td>
          </tr>
        </table></td>
  </tr>
  <tr>
    <td align="center"><input type="submit" name="Submit" value="Ingresar" onClick="javascript:SelectAllList(this.form.detVenta);"></td>
  </tr></form>
</table>
<table width="750" border="0" align="center">
  <tr>

  </tr>
</table>

<script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("form1");

frmvalidator.addValidation("accion","req","falta el parametro de acción");

frmvalidator.addValidation("codVta","req","Falta el codigo de la venta");
frmvalidator.addValidation("codVta","maxlen=20","Código no puede superar los 20 caracteres");

frmvalidator.addValidation("idCli","req","falta el cliente");
frmvalidator.addValidation("detVenta","req","falta el detalle de la venta");

frmvalidator.addValidation("fechaPresentacion","req","falta la fecha");
frmvalidator.addValidation("fechaPresentacion","maxlen=10"," La Fecha no puede superar los 10 caracteres");
frmvalidator.addValidation("comentario","maxlen=500","Comentario no puede superar los 500 caracteres");
</script>


</form>
<script language="JavaScript" type="text/javascript">
				var cal1 = new calendar1(document.forms['form1'].elements['fechaPresentacion']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
//-->
</script>
<hr>
  <div align="right"><a href="../ayuda/ventas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
