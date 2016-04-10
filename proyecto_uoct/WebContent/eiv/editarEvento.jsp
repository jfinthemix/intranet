<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="proyecto_uoct.documentacion.VO.DocumentodeListaVO, java.util.List, java.util.Iterator,java.text.SimpleDateFormat" %>
<%@ page import="proyecto_uoct.EIV.VO.EventoVO" %>
<%

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List eventos=null;

Integer id_eiv= (Integer) request.getAttribute("id_eiv");


String nomEIV = (String) request.getAttribute("nomEIV");
String mensaje = (String) request.getAttribute("mensaje");

EventoVO evento= (EventoVO) request.getAttribute("detEvento");

%>

<html>
<head>
<title>Editar Bit&aacute;cora</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="Javascript" type="text/javascript">
<!--
<!--   Para el traspaso de variables entre Ventanas -->
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }

  function pasaDoc(idDoc,codDoc,tipoDoc){
    actualizarEvento.idDoc.value = idDoc;
    actualizarEvento.codDoc.value = codDoc;
    actualizarEvento.tipoDoc.value = tipoDoc;

    otra.window.close();
  }

//-->
</script>
  <!-- calendario popup-->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script><!-- Date only with year scrolling -->
  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<META http-equiv="pragma" content="no-cache">

</head>

<body onLoad="verTipoBitacora()">
<table width="750" border="0">
  <tr>
    <td height="21"> <h3 align="center">Edici&oacute;n de Bitácora:</h3></td>
  </tr>
  <tr>
    <td><h4 align="center">EISTU - <%=id_eiv %>: <%=nomEIV %>
        <% if (mensaje != null){ %>
      </h4>
      <div align="center"><font color="red"><%=mensaje %></font>
        <%} %>
      </div></td>
  </tr>
  <form action="eivAction.do" name="actualizarEvento">
    <tr>
      <td> <div align="center">
          <input type="hidden" name="hacia" value="actualizarEvento" />
          <input type="hidden" name="id_eiv" value="<%= id_eiv%>" />
          <input type="hidden" name="nomEIV" value="<%=nomEIV%>" />
            <input type="hidden" name="idEvento" value="<%=evento.getIdEvento()%>" />
          <input type="hidden" name="busdoc" value="../documentacion/documentoAction.do?hacia=abuscarDocs_pop"/>
          <table width="556" border="1" align="center">
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>T&iacute;tulo de
                  la Bit&aacute;cora:</strong></div></td>
              <td><%= evento.getTitulo() %>&nbsp;</td>
            </tr>
            <tr>
              <td width="145" bgcolor="#ADD8E4"><div align="right"><strong>Fecha
                  de la Bit&aacute;cora: </strong></div></td>
              <td width="395"> <input type="text" name="fechaEvento" size=8 readonly="readonly" value="<%=sdf.format(evento.getFechaEv())%>">
                &nbsp;<a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar fecha"></a>
              </td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Descripci&oacute;n:
                  </strong></div></td>
              <td><textarea name="desc_ev" cols="60"  rows="6" ><%= evento.getDescEv() %></textarea></td>
            </tr>
            <tr>
              <td bgcolor="#ADD8E4"><div align="right"><strong>Documento Relacionado:
                  </strong></div></td>
              <td><div align="left">

                  <input name="idDoc" type="hidden" value="<%if(evento.getIdDocRel()!=null){ out.print(evento.getIdDocRel());}%>" />
                  <input name="tipoDoc" type="text" size="5" readonly="readonly" value="<%if(evento.getTipoDoc()!=null){ out.print(evento.getTipoDoc());}
                   %>"/>
                  -
                  <input type="text" size="12" readonly="readonly" name="codDoc" value="<%if(evento.getCodDoc()!=null){ out.print(evento.getCodDoc());} %>"/>
                  <a href="" target="_blank" onClick="popUp(actualizarEvento.busdoc.value, this.target, 'width=1000,height=400, scrollbars=1'); return false;">Buscar
                  Documento</a> </div></td>
            </tr>
          </table>
        </div></td>
    </tr>
    <tr>
      <td> <div align="center">
          <input type="submit" name="agregar" value="Actualizar Bitácora">
          &nbsp;
          <input name="reset"  type="reset" value="Borrar formulario"/>
        </div></td>
    </tr>
  </form>
  <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("actualizarEvento");

    frmvalidator.addValidation("fechaEvento","req","Debe ingresar la fecha de la bitácora");

    frmvalidator.addValidation("desc_ev","req","Debe ingresar la descripción de la bitácora");
    frmvalidator.addValidation("desc_ev","maxlen=500","La descripción de la bitácora no puede superar los 500 caracteres");


  </script>
  <script language="JavaScript" type="text/javascript">

				var cal1 = new calendar1(document.forms['actualizarEvento'].elements['fechaEvento']);
				cal1.year_scroll = true;
				cal1.time_comp = false;
</script>
</table>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/eiv.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
