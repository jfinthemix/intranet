<!DOCTYPE html>

<%@ page contentType="text/html; charset=iso-8859-1" language="java"%>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%@ page import="proyecto_uoct.reservas.VO.*,proyecto_uoct.common.util.Fecha"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ taglib uri="/tagOutlook" prefix="o" %>


<%
List recursos = (List) request.getAttribute("recursos");
List reservas = (List) request.getAttribute("RESERVAS");
Long idusuario = (Long) request.getAttribute("Id_Usu");
String fecha_s=(String) request.getAttribute("fechaBusqueda");

Date hoy=new Date();
Date fechaReserva;



SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
Fecha procesFecha= Fecha.getInstance();

if(fecha_s==null){
fecha_s= fecha.format(new Date());
}

%>


<html lang="es">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Intranet de la UOCT">
		<meta name="author" content="jfanasco" >
		<link rel="icon" href="img/favicon.ico">
		
		<title>Unidad Operativa de Control de Tránsito</title>
		
		<link href="css/grid.css" rel="stylesheet">
		<link href="css/glyphs.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<link href="css/datepicker.css" rel="stylesheet">
		<link href="css/timepicker.css" rel="stylesheet">
		<link href="css/fullcalendar.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
			<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	
	<body>
		
		 <script type="text/javascript">

  function popUp(href, target, features) {
    reservar = window.open(href, target, features);
    reservar.window.focus();
  }


  function goBuscar() {
    formulario.accion.value = "VER_SALA";
    formulario.submit();
  }



    function goEliminar(id,idrecurso) {
    formulario.idReserva.value = id;
    formulario.idRecurso.value= idrecurso;
    formulario.accion.value = "ELIMINARSALA";
     formulario.fecha.value="<%=fecha_s%>";
    elimin=window.confirm('¿Está seguro(a) de eliminar esta reserva?');
    (elimin)?formulario.submit():'return false';
  }




  function f_reservar(idrecurso,nombrerecurso,fecha_r){
  formulario.idRecurso.value= idrecurso;
  formulario.nombreRecurso.value= nombrerecurso;
  formulario.fecha.value=fecha_r;
  reservar = window.open('recursos/reservarRecurso.jsp', '_blank', 'width=550,height=400');
  reservar.window.focus();
  return false;
  }


  function goReservar() {
    formulario.idRecurso.value=reservar.document.all["idRecurso"].value;
    formulario.fecha.value = reservar.document.all["fecha"].value;
    formulario.fechaBusqueda.value = reservar.document.all["fecha"].value;
    formulario.horaInicio.value = reservar.document.all["horaInicio"].value;
    formulario.minutosInicio.value = reservar.document.all["minutosInicio"].value;
    formulario.horaFin.value = reservar.document.all["horaFin"].value;
    formulario.minutosFin.value = reservar.document.all["minutosFin"].value;
    formulario.motivo.value = reservar.document.all["motivo"].value;
    reservar.window.close();
    formulario.accion.value = "RESERVARSALA";
    var action = 'recursos/RecursosAction.do';
    SubmitFormulario(action,formu);

  }

  function submitThisReservar(formu) {
		var action = 'recursos/RecursosAction.do';
		
		SubmitFormulario(action,formu);

	}
  

  </script>
		
		
		
		<div class="main">
			<div class="container">
				<div class="row clearfix">
				
					
				
					<div class="col-sm-6 desarrollo">
					
						<h4>Reservas de Sala de Reuniones 1 (Proyector)</h4>
						
						<div class="box boxpost">
				 			<h4>Buscar fecha</h4>
				 			
				 			
<form name="formulario" id="formulario" action="recursos/recursosAction.do" method="POST" class="form-horizontal" >
  <input type="hidden" name="accion" />
  <input type="hidden" name="idRecurso" />
  <input type="hidden" name="idReserva"/>
  <input type="hidden" name="horaInicio"/>
  <input type="hidden" name="minutosInicio"/>
  <input type="hidden" name="horaFin"/>
  <input type="hidden" name="minutosFin"/>
  <input type="hidden" name="nombreRecurso"/>
  <input type="hidden" name="motivo"/>
   <input type="hidden" name="fecha"/>
				 			
			 			
				 				<div class="form-group">
    								<label for="inputFecha" class="col-sm-4 control-label">Fecha</label>
    								<div class="col-sm-8">
      								<input type="text" name="fechaBusqueda" class="form-control inputFecha" id="inputFecha" value="<%=fecha_s%>">
    								</div>
    							</div>
  								<div class="boxOpciones">
    								<div class="form-group">
    									<div class="col-sm-12">
    										<a href="javascript:void(0)" onclick="javascript:submitThisForm1();" class="botoVerde"><span class="glyphicons glyphicons-search"></span> Buscar</a>
      								</div>
  									</div>
    							</div>
  							</form>
						</div>
						
				 		<div class="box">
				 		
				 		 <script language="JavaScript">
function setEvent(h1,m1,h2,m2)
{
  alert("Reserva desde "+h1+":"+m1+" hasta "+h2+":"+m2);
}
</script>

<%
if(recursos!=null && !recursos.isEmpty()){



      Iterator irecursos= recursos.iterator();
      while (irecursos.hasNext()){
        RecursoVO rec=(RecursoVO) irecursos.next();


        // Obtenemos la lista de reservas asociada al recurso

        List reservasDelRecurso = new LinkedList();

        Iterator ireservas = reservas.iterator();
        while(ireservas.hasNext()){
        List reservasXrecurso= (List) ireservas.next();
        Iterator iresXrec= reservasXrecurso.iterator();
        if(iresXrec.hasNext()){
          ReservaVO res_tmp=(ReservaVO) iresXrec.next();
          if(res_tmp.getIdRecurso().intValue()== rec.getIdRecurso().intValue()){
          reservasDelRecurso=reservasXrecurso;
          break;
          }
        }
        }

      %>

    <table>
      <tr>

        <%for (int i=2;i<7;i++){
          Date diahoy=fecha.parse(fecha_s);
          Date diacorrelativo=procesFecha.getDiadeSemana(diahoy,i);
          String diaS=null;
          switch (i){
            case 1:{
              diaS="Domingo";
              break;
            }
            case 2:{
              diaS="Lunes";
              break;
            }
            case 3:{
              diaS="Martes";
              break;
            }
            case 4:{
              diaS="Miércoles";
              break;
            }
            case 5:{
              diaS="Jueves";
              break;
            }
            case 6:{
              diaS="Viernes";
              break;
            }
            case 7:{
              diaS="Sábado";
              break;
            }

          } // fin switch
  %>

  <td>
    <table>
      <tr><th>Reservas del <%=diaS %> <%=fecha.format(diacorrelativo) %></th></tr>
      <tr><td><a href="#" onclick="f_reservar('61','Sala 1(Proyector)','<%=fecha.format(diacorrelativo) %>');">Reservar</a></td></tr>

      <tr>
        <td>

          <o:outlook width="100" start="5" end="23">

            <%
            Iterator ires = reservasDelRecurso.iterator();
            while (ires.hasNext()){

              ReservaVO reserva= (ReservaVO) ires.next();
              Calendar dia_c=Calendar.getInstance();
              dia_c.setTime(reserva.getFechaReserva());

              if(dia_c.get(Calendar.DAY_OF_WEEK)==i){


                String horaI = hora.format(reserva.getHoraDeInicio());
                String horaF = hora.format(reserva.getHoraDeFin());
                String motivo= reserva.getMotivo();
                String usuario = reserva.getNombreUsuario();

                %>
                <o:event start="<%=horaI%>" end="<%=horaF%>">
                  <% // Solamente se puede eliminar una reserva posterior al momento actual, o hasta un día antes.


                  Calendar hoy_cal= Calendar.getInstance();
                  hoy_cal.setTime(new Date());
                  hoy_cal.add(Calendar.DATE,-1);
                  hoy= hoy_cal.getTime();
                  boolean es_despues=false;

                  if(reserva.getFechaReserva().after(hoy)){es_despues=true;}


                  Integer horaActual=new  Integer(hora.format(hoy).substring(0,2));
                  Integer minutoActual=new  Integer(hora.format(hoy).substring(3,5));

                  Integer horaIniRes=new  Integer(horaI.substring(0,2));
                  Integer minutoIniRes=new  Integer(horaI.substring(3,5));

                  if(fecha.format(hoy).equals(fecha.format(reserva.getFechaReserva()))){
                    if(horaActual.intValue()<horaIniRes.intValue()){
                      es_despues=true;
                    }
                    if(horaActual.intValue()==horaIniRes.intValue() && minutoActual.intValue()<=minutoIniRes.intValue()){
                      es_despues=true;
                    }
                  }

                  if(reserva.getIdUsuario().intValue()== idusuario.intValue() && es_despues){ %>
                  <a href="#" onclick="goEliminar(<%=reserva.getIdReserva() %>, '61')" title="<%=usuario %>---<%=motivo %>">Eliminar</a> <br /><%} %>

                  <a href="#" title="<%=horaI %> - <%=horaF %> - <%=usuario %> - <%=motivo %>">
                    <%=horaI %> - <%=horaF %> <br /> <%=usuario %> - <%=motivo %></a> </o:event>

                    <%}
                    }%>
                    </o:outlook>


                  </td>
                </tr>

              </table>

            </td>
        <%} %>

<%}
}
else{ out.println("No hay recursos registrados en la Intranet.Comunicarse con el Administrador");}%>



  </tr>
</table>

					
					</div>
			
					
			
      		
      	</div><!-- /container -->
		
		</div><!-- /main -->
	


<script type="text/javascript">
function submitThisForm1() {
												
												var formulario = $('#formulario');

												var action = 'recursos/recursosAction.do'
												SubmitFormulario(action,
														formulario);

											}
</script>
		
		
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-datepicker.min.js"></script>
    <script src="js/bootstrap-datepicker.es.min.js"></script>
    <script src="js/jquery.timepicker.min.js"></script>
    <script src="js/moment.js"></script>
    <script src="js/truncate.js"></script>
    <script src="js/fullcalendar.min.js"></script>
	 <script src="js/uoct.js"></script>
  </body>
</html>
