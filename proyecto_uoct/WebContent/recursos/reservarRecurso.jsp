<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>



<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="Unidad Operativa de Control de Tránsito">
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>



  <body>
  
      <script type="text/javascript">

    var nav4 = window.Event ? true : false;
    function acceptNum(evt){
      var key = nav4 ? evt.which : evt.keyCode;
      return (key <= 13 || (key >= 48 && key <= 57));
    }

    function goReservar() {


      if(formulario.motivo.value==""){
        alert("Debe indicar el motivo/destino de la reserva");
        formulario.motivo.focus();
        formulario.motivo.select();
        return false;
      }

      if(formulario.horaInicio_tmp.value==""){
        alert("Debe indicar la hora de inicio");
        formulario.horaInicio_tmp.focus();
        formulario.horaInicio_tmp.select();
        return false;
      }


      if(formulario.horaFin_tmp.value==""){
        alert("Debe indicar la hora de fin");
        formulario.horaFin_tmp.focus();
        formulario.horaFin_tmp.select();
        return false;
      }

      if(formulario.horaInicio_tmp.value.length<5 || formulario.horaFin_tmp.value.length<5){
        alert("El formato de la hora es HH:MM");
        formulario.horaInicio_tmp.focus();
        formulario.horaInicio_tmp.select();
        return false;
      }


      if(formulario.fecha.value==""){
        alert("Debe indicar la fecha");
        formulario.fecha.focus();
        formulario.fecha.select();
        return false;
      }

      var horaIniT=formulario.horaInicio_tmp.value.substr(0,2);
      var minIniT=formulario.horaInicio_tmp.value.substr(3,5);
      var horaFinT=formulario.horaFin_tmp.value.substr(0,2);
      var minFinT=formulario.horaFin_tmp.value.substr(3,5);


     if(parseInt(horaIniT,10) > parseInt(horaFinT,10)) {
       alert("La hora de inicio no puede ser mayor que la hora de fin");
       formulario.horaInicio_tmp.focus();
       formulario.horaInicio_tmp.select();
       return false;
     }

     if(parseInt(horaIniT,10) <= parseInt(horaFinT,10)) {

        if(parseInt(horaIniT,10)== parseInt(horaFinT,10)) {
          if(parseInt(minIniT,10) >= parseInt(minFinT,10)) {
            alert("La hora de inicio no puede ser mayor que la hora de fin");
            formulario.horaInicio_tmp.focus();
            formulario.horaInicio_tmp.select();
            return false;
          }
        }
      }
      formulario.horaInicio.value=horaIniT;
      formulario.horaFin.value=horaFinT;
      formulario.minutosInicio.value=minIniT;
      formulario.minutosFin.value=minFinT;

      window.opener.goReservar();
    }

    onload = function() {
      formulario.idRecurso.value = window.opener.document.all["idRecurso"].value;
      formulario.nombreRecurso.value = window.opener.document.all["nombreRecurso"].value;
      formulario.fecha.value = window.opener.document.all["fecha"].value;
    }

    </script>
  
  
<h3 align="left">Reservar</h3>

 <form name="formulario" action="">
   <input type="hidden" name="idRecurso" />
   <input type="hidden" name="horaInicio"/>
   <input type="hidden" name="minutosInicio"/>
   <input type="hidden" name="horaFin"/>
   <input type="hidden" name="minutosFin"/>

<div class="box boxpost">
   <table width="590" border="1" cellpadding="0" cellspacing="0">
     <tr bgcolor="#E6E6E6">
       <td width="232" align="center"><label class="col-sm-4 control-label">Recurso</label></td>
       <td width="274" align="center"><label class="col-sm-4 control-label">Fecha:</label></td>
       <td width="151" align="center"><label class="col-sm-4 control-label">Hora inicio<br />(ej:07:30)</label></td>
       <td width="115" align="center"><label class="col-sm-4 control-label">Hora fin <br />(ej:10:30)</label></td>
     </tr>
     <tr>
       <td> <input name="nombreRecurso" type="text" readonly="readonly" > &nbsp;
       </td>
       <td valign="middle"> <input name="fecha" type="text" size="10" readonly="readonly" >
         &nbsp; <a href="javascript:cal1.popup();"><img src="../util/calendario/img/cal.gif" width="16" height="16" border="0" alt="Click Here to Pick up the date"></a>
         </td>
         <td> <input name="horaInicio_tmp" type="text" size="5" maxlength="5" onKeyUp="this.value=formateahora(this.value);" />
         </td>
         <td> <input name="horaFin_tmp" type="text" size="5" maxlength="5" onKeyUp="this.value=formateahora(this.value);" />
      </td>
    </tr>
    <tr>
      <td colspan="4" background="#ADD8E4"><div align="center"><strong>Motivo/
          Destino</strong></div>
        <div align="left"> </div></td>
    </tr>
    <tr>
      <td colspan="4"><div align="center">
          <input name="motivo" type="text"  size="60" >
         </div></td>
    </tr>
    <tr>
      <td colspan="4"><div align="center">
          <input type="button" name="enviar" value="Reservar" onClick="return goReservar()"/>
        </div></td>
    </tr>
  </table>
  </div>
 <br/>
    </form>


    <script language="JavaScript" type="text/javascript">
      var cal1 = new calendar1(document.forms['formulario'].elements['fecha']);
      cal1.year_scroll = true;
      cal1.time_comp = false;
    </script>


    <script language="JavaScript" type="text/javascript">
      var frmvalidator  = new Validator("formulario");
      frmvalidator.addValidation("horaInicio","req","Debe ingresar la hora de Inicio");
      frmvalidator.addValidation("horaFin","req","Debe ingresar la hora de Fin");
      frmvalidator.addValidation("horaInicio","maxlen=5","Ejemplo de hora: 19:55");
      frmvalidator.addValidation("horaFin","maxlen=5","Ejemplo de hora: 19:55");
      </script>





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
      var long1 = hora.length;
      var hh;
      var mm;
      if ((long1>=2) && (primerslap==false)) {
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
        if ((long1<=2) && (primerslap=true)) {
          hora=hora.substr(0,1);
          primerslap=false;
        }
      }
      if ((long1==5) && (segundoslap==false)){
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
        if ((long1<5) && (segundoslap=true)) {
          hora=hora.substr(0,4);
          segundoslap=false;
        }
      }
      if (long1>=5){
        hora=hora.substr(0,5);
        hh=hora.substr(0,2);
        mm=hora.substr(3,2);
      }
      return (hora);
    }
  </script>


<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/recursos.html" target="_blank">Ayuda</a>
  </div>

  </body>
</html>
