<%@page import="java.util.List,proyecto_uoct.reservas.VO.RecursoVO,proyecto_uoct.reservas.VO.ReservaVO,java.util.Iterator"%>
<%@page import="java.text.SimpleDateFormat"%>

<%

SimpleDateFormat hora = new SimpleDateFormat("HH:mm");


List recursos= (List)request.getAttribute("RECURSOS");
Iterator rec1=recursos.iterator();

%>
<html>
<head>
<title>UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<meta http-equiv="refresh" content="60">
<style type="text/css">
#posi{
	position:absolute;
	top:0px;
	left:0px;

}
</style>

</head>

<body>
<div style="posi">

<table  border="0" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="18" height="20" background="util/img/esquinasuperiorder.jpg"></td>
    <td width="150" height="20" background="util/img/horizontalSup.jpg">&nbsp;</td>
    <td width="20" height="20" background="util/img/esquinasuperiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="122"  background="util/img/verticalIzq.jpg"> </td>
    <td ><form name="form1" method="post" action="indexAction.do" target="_parent">
          <div align="center"> </div>
        <table width="150" border="0" align="center">
                  <input type="hidden" name="hacia" value="inises" />
            <tr>
              <td ><div align="center"><strong><font color="47A5AE" size="2" face="Verdana, Arial, Helvetica, sans-serif">Nombre
                  de usuario:</font></strong></div></td>
            </tr>
            <tr>
              <td><div align="center">
                  <input name="nom" type="text" size="15">
                </div></td>
            </tr>
            <tr>
              <td><div align="center"><strong><font color="47A5AE" size="2" face="Verdana, Arial, Helvetica, sans-serif">Password:</font></strong></div></td>
            </tr>
            <tr>
              <td><div align="center">
                  <input name="psw" type="password" size="15">
                </div></td>
            </tr>
            <tr>
              <td><div align="center">
                  <input type="submit" name="Submit" value="Entrar">
                </div></td>
            </tr>
          </table>
      </form></td>
    <td  background="util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="19" background="util/img/esquinainferiorDer.jpg"></td>
    <td valign="top" background="util/img/horizontalInf.jpg">&nbsp;</td>
    <td height="19" background="util/img/esquinainferiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" background="util/img/esquinasuperiorder.jpg"></td>
    <td valign="top" background="util/img/horizontalSup.jpg">&nbsp;</td>
    <td height="20" background="util/img/esquinasuperiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" background="util/img/verticalIzq.jpg"></td>
    <td valign="top"><small><font color="476EAE" size="1" face="Verdana, Arial, Helvetica, sans-serif"><strong>RESERVAS
      PARA HOY</strong></font></small></td>
    <td height="20" background="util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td  background="util/img/verticalIzq.jpg"></td>
    <td > <marquee direction="up" scrollAmount='2' width='130' height='120'>
      <small><font color="476EAE" size="1" face="Verdana, Arial, Helvetica, sans-serif">
      <%
while (rec1.hasNext()){
  RecursoVO rec=(RecursoVO) rec1.next();

  out.print("<p>"+rec.getNombre()+"<br/>");
  List reservas=rec.getReservas();
  if(reservas!=null && reservas.size()!=0){
    Iterator ires=reservas.iterator();
    while(ires.hasNext()){
      ReservaVO reser=(ReservaVO)ires.next();
      out.println("&nbsp;Reservado HOY Desde:"+hora.format(reser.getHoraDeInicio())+"-Hasta:"+hora.format(reser.getHoraDeFin())+"</p>");
    }
  }else{
  out.print("     &nbsp; Sin reservas para hoy</p>");}
}
%>
      </FONT> </small></marquee></td>
    <td height="19" background="util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" valign="top" background="util/img/esquinainferiorDer.jpg">&nbsp;</td>
    <td valign="top" background="util/img/horizontalInf.jpg">&nbsp;</td>
    <td valign="top" background="util/img/esquinainferiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" valign="top" background="util/img/esquinasuperiorder.jpg">&nbsp;</td>
    <td valign="top" background="util/img/horizontalSup.jpg">&nbsp;</td>
    <td valign="top" background="util/img/esquinasuperiorIzq.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="19"  valign="top" background="util/img/verticalIzq.jpg">&nbsp;</td>
    <td valign="top"><small><font color="476EAE" size="1" face="Verdana, Arial, Helvetica, sans-serif"><strong>&Iacute;NDICES
      ECON&Oacute;MICOS </strong></font></small></td>
    <td valign="top" background="util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="57" valign="top" background="util/img/verticalIzq.jpg"><small><font color="476EAE" size="2" face="Verdana, Arial, Helvetica, sans-serif">
      </font></small> </td>
    <td valign="top"><small><font color="476EAE" size="1" face="Verdana, Arial, Helvetica, sans-serif"><strong>
      UF:$
      <script src="http://www.bci.cl/common/include/valores.js"></script>
      <script>
if(typeof(arrValores) != "undefined")
if(typeof(arrValores[4])=="object")
document.write(formatear_numero(arrValores[4].valor2));
                  </script>
      <br>
      US obs.:$
      <script>
if(typeof(arrValores) != "undefined")
if(typeof(arrValores[6])=="object")
document.write(formatear_numero(arrValores[55].valor2));
                  </script>
      <br>
      UTM:$
      <script>
if(typeof(arrValores) != "undefined")
if(typeof(arrValores[5])=="object")
document.write(formatear_numero(arrValores[5].valor2));
            </script>
      </strong></font></small></td>
    <td valign="top" background="util/img/verticalDer.jpg">&nbsp;</td>
  </tr>
  <tr>
    <td height="20" valign="top" background="util/img/esquinainferiorDer.jpg">&nbsp;</td>
    <td valign="top" background="util/img/horizontalInf.jpg">&nbsp;</td>
    <td valign="top" background="util/img/esquinainferiorIzq.jpg">&nbsp;</td>
  </tr>
</table>
</div>

</body>
</html>
