
<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ page import="java.util.List,java.util.Iterator, proyecto_uoct.usuario.VO.UsuarioVO,java.text.SimpleDateFormat,java.util.Calendar,java.util.Date" %>

<%
SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
SimpleDateFormat sdf2= new SimpleDateFormat("dd");
SimpleDateFormat sdf3= new SimpleDateFormat("MM");
String[] montharray={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre","Enero"};
String[] diaarray={"","Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sábado"};


Calendar hoyG=Calendar.getInstance();
java.util.Date hoyJ=new Date();
String estemes= montharray[hoyG.get(Calendar.MONTH)];

List cumpleaneros= (List) request.getAttribute("cumpleaneros");

Iterator i= cumpleaneros.iterator();

String cumpleanos=null;


int c=0;

while( i.hasNext()){
  UsuarioVO usu=(UsuarioVO) i.next();

  String cumpEsteAnoS=sdf.format(usu.getCumpleanos());
  String esteAnoS=sdf.format(new java.util.Date());
  esteAnoS=esteAnoS.substring(6);


  cumpEsteAnoS=cumpEsteAnoS.replaceAll("2000",esteAnoS);

  java.util.Date cumpEsteAnoD= sdf.parse(cumpEsteAnoS);

  Calendar cumpEsteAnoC= Calendar.getInstance();
  cumpEsteAnoC.setTime(cumpEsteAnoD);

  int mes=Integer.parseInt(sdf3.format(new java.util.Date()));
  Calendar fechaG=Calendar.getInstance();

  if(cumpleanos!=null){

    if(usu.getNombreUsu2()!=null){
      cumpleanos+=diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+", "+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+" &nbsp;&nbsp;";

    }else{
      cumpleanos+=diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+", "+usu.getNombreUsu()+" "+usu.getApellUsu()+" &nbsp;&nbsp;";
    }
  }else{

    if(usu.getNombreUsu2()!=null){
      cumpleanos=diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+", "+usu.getNombreUsu()+" "+usu.getNombreUsu2()+" "+usu.getApellUsu()+" &nbsp;&nbsp;";
    }else{
      cumpleanos=diaarray[cumpEsteAnoC.get(Calendar.DAY_OF_WEEK)] +" "+cumpEsteAnoC.get(Calendar.DAY_OF_MONTH)+", "+usu.getNombreUsu()+" "+usu.getApellUsu()+" &nbsp;&nbsp;";
    }



  }
c++;
}

if(c==0){
   cumpleanos= "No hay cumpleaños este mes";
}


%>

<html>
<head>
<title>UOCT</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<style type="text/css">
#posi{
	position:absolute;
	top:5px;
	left:0px;

}



</style>
<script language="JavaScript" type="text/javascript">
function MM_openBrWindow(theURL,winName,features) { //v2.0
  window.open(theURL,winName,features);
}
//-->
</script>
 </head>

<body><div id="posi">
<table width="1024" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td height="60" colspan="6"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="1024" height="60">
        <param name="movie" value="util/flash/uoct6.swf">
        <param name="quality" value="high">
        <embed src="util/flash/uoct6.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="1024" height="60"></embed></object></td>
  </tr>
  <tr >
    <td colspan="2" height="7" ></td>
    <td width="203" height="7"></td>
    <td width="17" rowspan="2" background="util/img/slash3gif.gif"></td>
    <td width="85" rowspan="2" bgcolor="#3C7A93"></td>
    <td width="108" rowspan="3" valign="bottom" bgcolor="#3C7A93"><div align="center"><a href="index2Action.do" target="_top"><img src="util/img/home.gif" alt="Portada intranet" width="30" height="30" border="0"></a><a href="#"><img src="util/img/camara.gif" width="30" height="30" border="0" alt="C&aacute;maras en Vivo" onClick="MM_openBrWindow('http://www.uoct.cl/uoct/camaras/index.jsp?camara=1&amp;formato=1','Camaras','width=775,height=350')"></a></div></td>
  </tr>
  <tr>
    <td height="8" colspan="2" bgcolor="#99BEDD" ></td>
    <td width="203" bgcolor="#99BEDD"></td>
  </tr>
  <tr>
    <td width="610" height="20" bgcolor="#3C7A93"> <div align="right">
        <marquee border="0" align="middle" scrollamount="2"  scrolldelay="50" behavior="scroll"  width="520" height="18">
        <font color="ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Cumplea&ntilde;os
        del mes de <%=estemes %>:</font><font color="#01349D" size="2" face="Verdana, Arial, Helvetica, sans-serif">
        <font color="#F5F988"><%= cumpleanos %>
        </font></font>
        </marquee>
      </div></td>
    <td colspan="4" bgcolor="#3C7A93">
      <!-- Copiar dentro del tag BODY -->
      <div align="right">
        <script languaje="JavaScript">
	var mydate=new Date()
	var year=mydate.getYear()
	if (year < 1000)
	year+=1900
	var day=mydate.getDay()
	var month=mydate.getMonth()
	var daym=mydate.getDate()
	if (daym<10)
	daym="0"+daym
	var dayarray=new Array("Domingo","Lunes","Martes","Miércoles","Jueves","Viernes","Sabado")
	var montharray=new Array("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")
	document.write("<small><font color='ffffff' face='Verdana' size='1'>"+dayarray[day]+" "+daym+" de "+montharray[month]+" de "+year+"</font></small>")
	</script>
      </div>
      <div align="left"> </div></td>
  </tr>
</table>
</div>
</body>
</html>

