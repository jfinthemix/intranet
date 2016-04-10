<%@page contentType="text/html; charset=iso-8859-1" language="java"
import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
String mensaje          = (String)  request.getAttribute("mensaje");
EmpMantVO empmant       = (EmpMantVO) request.getAttribute("empmant");
List listaempresa       = (List) request.getAttribute("listaempresa");
List listaempresa2      = (List) request.getAttribute("listaempresa");
List listadispositivosD = (List) request.getAttribute("listadispositivosD"); //D: disponible
List listadispositivosM = (List) request.getAttribute("listadispositivosM"); //M: en mantención

Integer id_empresa      = (Integer)  new Integer(0);
String nombre           = "";
String direccion        = "";
String telefono         = "";
Integer vigente         = new Integer(0);
String tipo_dispositivo = "";
String mail_1           = "";
String mail_2           = "";
Integer cero            = new Integer(0);
Integer uno             = new Integer(1);


if(listaempresa!=null){
  Iterator i=listaempresa.iterator();
  while(i.hasNext()){
    EmpMantVO encabezado = (EmpMantVO) i.next();
    id_empresa           = encabezado.get_id_empresa();
    nombre               = encabezado.get_nombre();
    direccion            = encabezado.get_direccion();
    telefono             = encabezado.get_telefono();
    vigente              = encabezado.get_vigente();
    mail_1               = encabezado.get_mail_1();
    mail_2               = encabezado.get_mail_2();
  }
}
%>
<html>
<head>
<title>Empresa mantenedora</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
.selectmultiple {
  font-size: 9px;
}
</style>

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript">
function SelectAllList(CONTROL, CONTROL2){
  for(var i = 0;i < CONTROL.length;i++){
    CONTROL.options[i].selected = true;
  }
  for(var j = 0;j < CONTROL2.length;j++){
    CONTROL2.options[j].selected = true;
  }
}
var delimiter = ":";
function MoveOption (MoveFrom, MoveTo, ToDo) {
  var SelectFrom = eval('document.form_editar_empmant.'+MoveFrom);
  var SelectTo = eval('document.form_editar_empmant.'+MoveTo);
  var SelectedIndex = SelectFrom.options.selectedIndex;
  var container;
  if (ToDo=='Add') {
    container="document.form_editar_empmant."+ToDo+MoveTo;
  }
  if (ToDo=='Remove') {
    container="document.form_editar_empmant."+ToDo+MoveFrom;
  }

  if (SelectedIndex == -1) {
    alert("Debe seleccionar un dispositivo.");
  }
  else {
    for (i=0; i<SelectFrom.options.length; i++) {
      if(SelectFrom.options[i].selected) {
        var name = SelectFrom.options[i].text;
        var ID = SelectFrom.options[i].value;
        SelectFrom.options[i] = null;
        SelectTo.options[SelectTo.options.length]=new Option (name,ID);
        i=i-1;
        if(ToDo=='Add'||ToDo=='Remove') {
          container.value=container.value+name+delimiter;
        }
      }
    }
  }
}
function CheckDuplicates (AddListContainer, RemoveListContainer) {
  var AddList = eval('document.form_editar_empmant.'+AddListContainer);
  var RemoveList = eval('document.form_editar_empmant.'+RemoveListContainer);
  var TempAddList = AddList.value;
  var TempRemoveList = RemoveList.value;
  if (TempAddList>''&&TempRemoveList>'') {
    TempAddList = TempAddList.substring(0,TempAddList.length-1);
    TempRemoveList = TempRemoveList.substring(0,TempRemoveList.length-1);
    var AddArray = TempAddList.split(delimiter);
    var RemoveArray = TempRemoveList.split(delimiter);
    for (i=0; i<AddArray.length; i++) {
      for (j=0; j<RemoveArray.length; j++) {
        if (AddArray[i]==RemoveArray[j]) {
          AddArray[i]='';
          RemoveArray[j]='';
          break;
        }
      }
    }
    AddList.value='';
    for (i=0; i<AddArray.length; i++) {
      if (AddArray[i]>'') {
        AddList.value = AddList.value + AddArray[i] + delimiter;
      }
    }
    RemoveList.value='';
    for (i=0; i<RemoveArray.length; i++) {
      if (RemoveArray[i]>'') {
        RemoveList.value = RemoveList.value + RemoveArray[i] + delimiter;
      }
    }
  }
}
</SCRIPT>
</head>
<body bgcolor="#FFFFFF" text="#000000">
  <form name="form_editar_empmant" method="post" action="../empmant/empMantAction.do">

  <table width="85%" border="0" align="center">
    <tr>
      <td colspan="3">Empresa mantenedora</td>
    </tr>
    <tr>
      <td colspan="2">&nbsp;</td>
    </tr>
    <%
      if (mensaje != null) out.print("<tr><td><font color='red'>" + mensaje + "</font></td></tr>");
      %>
    <tr>
      <td colspan="2"> <table width="100%" border="1" align="center" cellpadding="1" cellspacing="1">
          <tr>
            <td width="15%" bgcolor="#ADD8E4"> <div align="right"><strong>nombre</strong></div></td>
            <td width="85%" ><strong>
              <input name="nombre" type="text" value="<%out.println(nombre); %>" size="60" maxlength="50">
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>direcci&oacute;n</strong></div></td>
            <td><strong>
              <input name="direccion" type="text" id="direccion" value="<%out.println(direccion); %>" size="60" maxlength="50">
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>tel&eacute;fono</strong></div></td>
            <td><strong>
              <input name="telefono" type="text" id="telefono" value="<%out.println(telefono); %>" size="20" maxlength="20">
              </strong></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>vigencia</strong></div></td>
            <td>
              <%
                  out.print("<select name='vigente'>");
                  if(uno.equals(vigente)){
                    out.print("<option selected value='1'>Vigente</option>");
                    out.print("<option value='0'>No vigente</option>");
                  }
                  if(cero.equals(vigente)){
                    out.print("<option selected value='0'>No vigente</option>");
                    out.print("<option value='1'>Vigente</option>");
                  }
                  out.print("</select>");
                  %>
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>mail terreno</strong></div></td>
            <td> <input name="mail_TERRENO" type="text" id="mail_TERRENO" value="<% out.println(mail_1); %>" size="60" maxlength="200">
              <font size="1">[Separar por "," si es m&aacute;s de un mail]</font>
            </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>mail sala </strong></div></td>
            <td> <input name="mail_SALA" type="text" id="mail_SALA" value="<% out.println(mail_2); %>" size="60" maxlength="200">
              <font size="1">[Separar por "," si es m&aacute;s de un mail]</font>
            </td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="45%" align="center" bgcolor="#ADD8E4"><strong>dispositivos disponibles</strong></td>
            <td width="10%">&nbsp;</td>
            <td width="45%" align="center" bgcolor="#ADD8E4"><strong>dispositivos en mantenci&oacute;n</strong></td>
          </tr>
          <tr>
            <td align="left">
              <select id="listaA" name="listaA" size="10" class="selectmultiple" multiple>
                <%
                if(listadispositivosD!=null){
                  Integer id_dispositivo    = new Integer(0);
                  String nombre_dispositivo = "";
                  Iterator iii=listadispositivosD.iterator();
                  while(iii.hasNext()){
                    EmpMantVO dispositivos = (EmpMantVO) iii.next();
                    id_dispositivo         = dispositivos.get_id_dispositivo();
                    nombre_dispositivo     = dispositivos.get_nombre_dispositivo()+ " " + dispositivos.get_descripcion();
                    %>
                    <option value="<% out.print(id_dispositivo);%>">
                      <%out.print(nombre_dispositivo);%>
                    </option>
                    <%
                  }
                }%>
              </select>
            </td>
            <td align="center">
              <input onclick="MoveOption('listaA','listaB','Add');" type="button" title="Agregar" name="add_admin" value=">>">
              <input onclick="MoveOption('listaB','listaA','Remove');" type="button" title="Quitar" name="remove_admin" value="<<">
              </td>
            <td align="left">
              <select id="listaB" name="listaB" size="10" class="selectmultiple" multiple>
              <%
                if(listadispositivosM!=null){
                  Integer id_dispositivo    = new Integer(0);
                  String nombre_dispositivo = "";
                  Iterator iii=listadispositivosM.iterator();
                  while(iii.hasNext()){
                    EmpMantVO dispositivos = (EmpMantVO) iii.next();
                    id_dispositivo         = dispositivos.get_id_dispositivo();
                    nombre_dispositivo     = dispositivos.get_nombre_dispositivo()+ " " + dispositivos.get_descripcion();
                    %>
                    <option value="<% out.print(id_dispositivo);%>">
                      <%out.print(nombre_dispositivo);%>
                    </option>
                    <%
                  }
                }%>
              </select>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr>
      <td colspan="2">&nbsp; </td>
    </tr>
    <tr>
      <td colspan="2">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="85">&nbsp;</td>
            <td width="15%" align="right"> <input name="id_emp" type="hidden" value="<% out.print(id_empresa);%>" />
              <input name="hacia" type="hidden" value="Grabar cambios">
              <input name="boton" type="submit" value="Grabar cambios" onClick="javascript:SelectAllList(document.form_editar_empmant.listaA,document.form_editar_empmant.listaB);"></td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;
          <script language="JavaScript" type="text/javascript">
            var frmvalidator  = new Validator("form_editar_empmant");
            frmvalidator.addValidation("nombre","req","Debe ingresar el nombre");
            frmvalidator.addValidation("telefono","req","Debe ingresar el telefono");
            frmvalidator.addValidation("direccion","req","Debe ingresar la direccion");
            frmvalidator.addValidation("mail_SALA","req","Debe ingresar el o los mails que correspondan a mantenciones en sala.");
            frmvalidator.addValidation("mail_TERRENO","req","Debe ingresar el o los mails que correspondan a mantenciones en terreno.");
          </script>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>
