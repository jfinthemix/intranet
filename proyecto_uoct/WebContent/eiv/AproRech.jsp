<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="proyecto_uoct.documentacion.VO.DocumentodeListaVO, java.util.List, java.util.Iterator" %>
<%@ page import="proyecto_uoct.EIV.VO.EventoVO,java.text.SimpleDateFormat" %>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List eventos=null;
eventos=(List) request.getAttribute("eventos");
Integer id_eiv= (Integer) request.getAttribute("id_eiv");
String mensaje = (String) request.getAttribute("mensaje");
String nomEIV = (String) request.getAttribute("nomEIV");

%>

<html>
<head>
<title>Editar Bit&aacute;cora</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

    <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

  <script language="Javascript" type="text/javascript">
<!--   Para el traspaso de variables entre Ventanas -->
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }

  function pasaDoc(idDoc,codDoc,tipoDoc){
    aproRechForm.idDoc.value = idDoc;
    aproRechForm.codDoc.value = codDoc;
    aproRechForm.tipoDoc.value = tipoDoc;

    otra.window.close();
  }








  </script>
  <!-- calendario popup-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->

  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Aprobar/Rechazar EISTU</h3></td>
  </tr>
  <tr>
    <td><h4 align="center"><font color="#000000">EISTU-<%=id_eiv %>: <%=nomEIV %></font></h4></td>
  </tr>
  <tr>
    <form action="eivAction.do" name="aproRechForm" method="POST">
      <td height="307">
        <div align="center">
          <input type="hidden" name="hacia" value="aprobar_rechazar" />
          <input type="hidden" name="id_eiv" value="<%= id_eiv%>" />
          <input type="hidden" name="nomEIV" value="<%= nomEIV%>" />
          <table width="500" border="1" align="center">
            <tr>
              <td> <% if (mensaje != null){ %> <div align="center" ><%=mensaje %></div>
                <%}%> <table width="575" border="1" align="center">
                  <tr>
                    <td bgcolor="#ADD8E4"><div align="right"><strong>Acci&oacute;n:</strong></div></td>
                    <td><p>
                        <label> <strong>
                        <input name="accion" type="radio" value="1" checked>
                        Aprobar</strong></label>
                        <strong><br>
                        <label>
                        <input type="radio" name="accion" value="2">
                        Rechazar</label>
                        </strong> </p></td>
                  </tr>
                  <tr>
                    <td width="85" bgcolor="#ADD8E4"><div align="right"><strong>Fecha
                        : </strong></div></td>
                    <td width="395"> <input type="text" name="fecha" size=8 readonly="readonly">
                      &nbsp; <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar fecha"></a>
                    </td>
                  </tr>
                  <tr>
                    <td bgcolor="#ADD8E4"><div align="right"><strong>Descripci&oacute;n:
                        </strong></div></td>
                    <td><textarea name="desc_ev" cols="60"></textarea></td>
                  </tr>
                  <tr>
                    <td bgcolor="#ADD8E4"><div align="right"><strong>Documento
                        Relacionado: </strong></div></td>
                    <td><div align="left">
                        <input name="idDoc" type="hidden" value="" />
                        <input name="tipoDoc" type="text" size="5" readonly="readonly"/>
                        -
                        <input type="text" size="12" readonly="readonly" name="codDoc"/>
                        <a href="../documentacion/documentoAction.do?hacia=abuscarDocsSal_pop" target="_blank" onClick="popUp(this.href, this.target, 'width=700,height=600, scrollbars=1'); return false;">Buscar
                        Documento</a> </div></td>
                  </tr>
                </table>
                <p align="center">
                  <input type="submit" name="agregar" value="Registrar">
                </p></td>
            </tr>
          </table>
        </div></td>
    </form>
  </tr>
  <tr>
    <td> <h4 align="center">
        <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("aproRechForm");
    frmvalidator.addValidation("fecha","req","Debe ingresar la fecha de la bitácora");
    frmvalidator.addValidation("idDoc","req","Debe indicar el documento despachado");
    frmvalidator.addValidation("desc_ev","req","Debe ingresar la descripción del despacho");
    frmvalidator.addValidation("desc_ev","maxlen=500","La descripción del despacho no puede superar los 500 caracteres");
  </script>
        <script language="JavaScript" type="text/javascript">
<!-- // create calendar object(s) just after form tag closed
// specify form element as the only parameter (document.forms['formname'].elements['inputname']);
// note: you can have as many calendar objects as you need for your application
				var cal1 = new calendar1(document.forms['aproRechForm'].elements['fecha']);
				cal1.year_scroll = true;
				cal1.time_comp = false;


							//-->
</script>
        Bit&aacute;cora del EISTU</h4></td>
  </tr>
  <tr>
    <td> <div align="center">
        <table width="500" border="1" align="center">
          <tr bgcolor="#C0C0C0">
            <td width="100"> <div align="center"><strong>Fecha del Evento</strong></div></td>
            <td width="300"> <div align="center"><strong>T&iacute;tulo Bit&aacute;cora</strong></div></td>
            <td width="100"><strong>Documento Relacionado</strong></td>
          </tr>
          <%
 if (eventos!=null){
 Iterator ie=eventos.iterator();
 while (ie.hasNext()){
   EventoVO even= (EventoVO) ie.next();
   %>
          <tr>
            <td><%= sdf.format(even.getFechaEv())%></td>
            <td><%= even.getTitulo()%></td>
            <%if(even.getIdDocRel().intValue()!=0){ %>
            <td><%=even.getTipoDoc() %>- <%=even.getCodDoc() %></td>
            <%}else{out.print("<td>&nbsp;</td>");}
     %>
          </tr>
          <%
}
}
%>
        </table>
      </div></td>
  </tr>
</table>
</form>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/eiv.html" target="_blank">Ayuda</a>
  </div>
</body>
</html>

