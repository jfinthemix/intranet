<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, java.util.List, java.util.Iterator,proyecto_uoct.EIV.VO.EIVVO,proyecto_uoct.EIV.VO.FlujoVO,proyecto_uoct.EIV.VO.EventoVO,java.text.SimpleDateFormat" errorPage=""%>
<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
EIVVO eiv = (EIVVO) request.getAttribute("detalleeiv");
List eventos= (List) request.getAttribute("eventos");
%>
<html>
<head>
<title><%= eiv.getNomEiv()%></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>
<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Detalle del EISTU : <%= eiv.getNomEiv()%></h3></td>
  </tr>
  <tr>
    <td><div align="center">
        <table width="431" border="1" align="center">
          <tr>
            <td width="203" bgcolor="#ADD8E4"><div align="right"><strong>EISTU:</strong></div></td>
            <td colspan="2"> <h5>EISTU - <%=eiv.getIdEIV() %></h5></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>T&iacute;tulo:</strong></div></td>
            <td colspan="2"><%= eiv.getNomEiv() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Tipo de Estudio:</strong></div></td>
            <td colspan="2"><%=eiv.getTipoEstudio() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha Presentación:</strong></div></td>
            <td colspan="2"><%= sdf.format(eiv.getFechaPresent()) %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Envío desde SEREMITT:</strong></div></td>
            <td colspan="2"><%= sdf.format(eiv.getFechaEnvioSeremitt()) %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha Ingreso:</strong></div></td>
            <td colspan="2"><%= sdf.format(eiv.getFechaIng()) %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de Vencimiento:</strong></div></td>
            <td colspan="2"><%= sdf.format(eiv.getFechaVenc()) %> </td>
          </tr>

          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Estado del EIV en
                UOCT:</strong></div></td>
            <td colspan="2"><%= eiv.getEstado()%></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Estado del EIV en
                SEREMITT:</strong></div></td>
            <td colspan="2"><%if (eiv.getEstadoSeremitt().intValue()==1){
             out.print("Aprobado por SEREMITT");}
            if (eiv.getEstadoSeremitt().intValue()==2){
             out.print("Rechazado por SEREMITT");}
             else{
             out.print("--");
           }
             %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Comuna:</strong></div></td>
            <td colspan="2"><%= eiv.getComuna()%></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Direcci&oacute;n:</strong></div></td>
            <td colspan="2"><%=eiv.getDir()%></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Redes Involucradas:</strong></div></td>
            <td width="212"> <%
      List redes = eiv.getRedes();
      if (redes != null) {
        Iterator ir = redes.iterator();
        while (ir.hasNext()) {
          Integer red = (Integer) ir.next();
          out.println(red + " - ");
      }
      }
    %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Nº de estacionamientos</strong></div></td>
            <td colspan="2"><%= eiv.getEstacionamientos() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Consultor:</strong></div></td>
            <td colspan="2"><%= eiv.getNomCons() %></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Empresa Consultora</strong></div></td>
            <td colspan="2"> <%if(eiv.getEmpCons()!=null){
      out.println(eiv.getEmpCons());}
      else{
      out.print("&nbsp;");
      }
      %> </td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Encargado del EIV:</strong></div></td>
            <td colspan="2"> <%
   if(eiv.getNom2Encarg()!=null){
     out.print(eiv.getNomEncarg()+ " "+ eiv.getNom2Encarg()+" "+ eiv.getApeEncarg());
   }else{
       out.print(eiv.getNomEncarg()+ " "+eiv.getApeEncarg());
   }

%> </td>
          </tr>
          <tr>
            <td  bgcolor="#ADD8E4"><div align="right">Oficio del EIV</div></td>
            <td><%=eiv.getNomDocumento() %></td>
          </tr>
        </table>
      </div></td>
  </tr>
  <tr>
    <td><h4 align="center">Flujos Vehiculares registrados para este EISTU</h4></td>
  </tr>
  <tr>
    <td><div align="center">
        <table width="656" border="1" align="center">
          <tr bgcolor="#F7FBC4">
            <td width="77"> <div align="center"> <strong>Fecha</strong></div></td>
            <td width="76"> <div align="center"> <strong>Tipo de d&iacute;a</strong>
              </div></td>
            <td width="228"> <div align="center"> <strong>intersecci&oacute;n</strong>
              </div></td>
            <td width="247"> <div align="center"> <strong>Intervalo Horario</strong>
              </div></td>
          </tr>
          <%
  List flujos = eiv.getFlujos();
  if (flujos != null) {
    Iterator iflu = flujos.iterator();
    while (iflu.hasNext()) {
      FlujoVO flu = (FlujoVO) iflu.next();

    out.println("<tr>");
    out.println("<td>"+sdf.format(flu.getFecha())+"</td>");
    out.println("<td>"+flu.getTipoDia()+"</td>");
    out.println("<td>"+flu.getInterseccion()+"</td>");
    out.println("<td>"+flu.getHorasMed()+"</td>");
    out.println("</tr>");
    }
  }
%>
        </table>
      </div></td>
  </tr>
  <tr>
    <td><h4 align="center">Bit&aacute;cora del EISTU</h4></td>
  </tr>
  <tr>
    <td> <div align="center">
        <%
if (eventos!=null){
  Iterator iev= eventos.iterator();
while (iev.hasNext()){
  EventoVO even= (EventoVO) iev.next();
%>
        <table width="636" border="1" align="center">
          <tr>
            <td width="153" height="26"><%= sdf.format(even.getFechaEv())%></td>
            <td width="277"><%=even.getTitulo()%></td>
            <td width="184">
              <%if(even.getTipoDoc()!=null && even.getCodDoc()!=null){
                out.print(even.getTipoDoc()+"-"+even.getCodDoc());
              }else{
              out.print("&nbsp;");
              }
              %></td>
          </tr>
          <tr>
            <td  bgcolor="#A6F7BA"> <div align="center"><strong>Descripci&oacute;n
                de Bit&aacute;cora</strong></div></td>
            <td bgcolor="#F7FBC4" colspan="2"><%=even.getDescEv() %></td>
          </tr>

        </table>
        <br />
        <%
}
}
%>
      </div></td>
  </tr>
</table>
<h2 align="center">&nbsp;</h2>
<div align="right"></div>
<div align="center">
  <h3>&nbsp;</h3>
</div>
<h3 align="center">&nbsp;</h3>
</body>
</html>
