<%
String idTipoDoc=(String)request.getAttribute("idTipoDoc");
String nomTipoDoc=(String)request.getAttribute("nomTipoDoc");
%>
<html>
<head>
<title>
Reservar código de Documento
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
<div align="left"> 
  <table width="321" border="0">
    <tr> 
      <td><h3><font color="#00FF00">Ud. se dispone a reservar un codigo de documento 
          del tipo:</font></h3></td>
    </tr>
    <tr> 
      <td><h3><font color="red"><%=nomTipoDoc%></font></h3></td>
    </tr>
    <tr> 
      <td><h3><font color="#FF0000">¿Seguro que desea realizar esta reserva?</font></h3></td>
    </tr>
    <tr>
      <td><form action="documentoAction.do" method="POST" name="form1">
          <input type="hidden" name="hacia" value="reservarDoc" />
          <input type="hidden" name="idTipoDoc" value="<%=idTipoDoc%>" />
          <input type="submit" value="SI" name="si"/>
          &nbsp;&nbsp; 
          <input type="button" name="no" value="NO" onClick="window.close();" />
        </form></td>
    </tr>
  </table>
  
</div>
</body>
</html>
