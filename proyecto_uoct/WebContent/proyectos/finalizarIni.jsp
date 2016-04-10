<%

Integer idIni=(Integer) request.getAttribute("idProy");
String nom=(String) request.getAttribute("nomProy");

%>
<html>
<head>
<title>
finalizarIni
</title>

<link href="../util/styla.css" rel="stylesheet" type="text/css">

  <!-- European format dd-mm-yyyy -->
<script language="JavaScript" src="calendar1.js" type="text/javascript"></script>

<script type="text/javascript">
function confirmaciones(f){
	if(f.fechaFin.value==''){
	alert("Debe ingresar la fecha de finalización de la iniciativa");
	return true;
	}
	alert("Para finalizar la iniciativa deberá confirmar su desición tres veces");
	primera=window.confirm(" Al finalizar la iniciativa no se podrá editar ningun otro dato de ella.\r\n¿Está seguro de continuar?");
	(primera)? segundaConfirmacion(f):'return  false';

}

function segundaConfirmacion(f){
	segunda=window.confirm("Esta es la segunda confirmación.\r\n¿esta seguro de continuar?");
	(segunda)?terceraConfirmacion(f):'return false';
}

function terceraConfirmacion(f){
	tercera=window.confirm("Esta es la tercera confirmación.\r\nSi responde sí, la iniciativa será cerrada.\r\n¿Está seguro de continuar?");
	(tercera)?f.submit():'return false';

}

</script>


</head>
<body>
<form action="proyectoAction.do" name="form1" method="POST">
  <table width="750" border="0">
    <tr>
      <td><h3>
          <input type="hidden" name="hacia" value="finalizarIni" />
          <input type="hidden" name="idProy" value="<%=idIni%>" />
          Finalización de la Iniciativa:<%=nom %> </h3></td>
    </tr>
    <tr>
      <td><font color="black"><strong>Al ingresar la fecha de finalización de
        la Iniciativa no se<br />
        podrá editar,posteriormente ningun dato asociado a ésta, a sus OT o NLO
        </strong></font></td>
    </tr>
    <tr>
      <td><table width="380" border="1">
          <tr>
            <td width="154" bgcolor="#ADD8E4"><strong>Fecha de Finalización de
              la Iniciativa</strong></td>
            <td width="210"> <div align="left">
                <input name="fechaFin" type="text" id="fechaFin2" size=12 readonly="readonly">
                <a href="javascript:cal1.popup();"><img src="img/cal.gif" width="16" height="16" border="0" alt="Seleccionar Fecha">
                </a> </div></td>
          </tr>
        </table></td>
    </tr>
    <tr>
      <td><input type="button" name="finalizar" value="Registrar la Fecha" onClick="confirmaciones(this.form)" /></td>
    </tr>
  </table>
</form>

  <script language="JavaScript" type="text/javascript">
var cal1 = new calendar1(document.forms['form1'].elements['fechaFin']);
cal1.year_scroll = true;
cal1.time_comp = false;
</script>
<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/iniciativas.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
