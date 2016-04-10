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

<html>
<head>
  <title>Agenda de Sala de reuniones 2 (TV)</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
       <script type="text/javascript" src="../calendario/CalendarPopup.js"></script>
  <script type="text/javascript">

  function popUp(href, target, features) {
    reservar = window.open(href, target, features);
    reservar.window.focus();
  }


  function goBuscar() {
    formulario.accion.value = "VER_SALAB";
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
  reservar = window.open('reservarRecurso.jsp', '_blank', 'width=620,height=250');
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
    formulario.submit();
  }


  </script>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>

<form name="formulario" action="recursosAction.do" method="POST">
  <input name="accion" type="hidden"/>
  <input type="hidden" name="idRecurso" />
  <input type="hidden" name="idReserva"/>
  <input type="hidden" name="horaInicio"/>
  <input type="hidden" name="minutosInicio"/>
  <input type="hidden" name="horaFin"/>
  <input type="hidden" name="minutosFin"/>
  <input type="hidden" name="nombreRecurso"/>
  <input type="hidden" name="motivo"/>
   <input type="hidden" name="fecha"/>


<table>
  <tr>
    <td><h2><strong> Reservas de Sala de Reuniones 2 (TV)</strong></h2></td>
  </tr>
  <tr>
    <td>&nbsp; </td>
  </tr>
<tr>
    <td><strong> Buscar en : <input name="fechaBusqueda" type="text" size="10" readonly="readonly" value="<%=fecha_s%>" >&nbsp; <a href="#"><img src="../calendario/images/calendar.gif" width="16" height="16" border="0" alt="Pinche aquí para escoger la fecha" id="cal1" onClick="calCenso(document.forms[0].fechaBusqueda, 'cal1')"></a></strong> &nbsp; <input type="button" name="buscar" value="Buscar" onclick="javascript:goBuscar()" /></td>
  </tr>
  <tr>
    <td><strong> </strong></td>
  </tr>

</table>

<br />




</form>

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
      <tr><td><a href="#" onclick="f_reservar('101','Sala 2 (TV)','<%=fecha.format(diacorrelativo) %>');">Reservar</a></td></tr>

      <tr>
        <td>

          <o:outlook width="130" start="5" end="23">

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
                  <a href="#" onclick="goEliminar(<%=reserva.getIdReserva() %>,'101' )" title="<%=usuario %>---<%=motivo %>">Eliminar  </a> <br /><%} %>

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


<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/recursos.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
