<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="proyecto_uoct.documentacion.VO.DocumentodeListaVO, java.util.List, java.util.Iterator,java.text.SimpleDateFormat" %>
<%@ page import="proyecto_uoct.EIV.VO.EventoVO" %>
<%

SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

List eventos=null;

eventos=(List) request.getAttribute("eventos");
Integer id_eiv= (Integer) request.getAttribute("id_eiv");

String nomEIV = (String) request.getAttribute("nomEIV");
String mensaje = (String) request.getAttribute("mensaje");
%>

<html>
<head>
<title>Editar Bit&aacute;cora</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

  <!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<link href="../util/styla.css" rel="stylesheet" type="text/css">
  <script language="Javascript" type="text/javascript">

<!--   Para el traspaso de variables entre Ventanas -->
var otra=null;
function popUp(href, target, features) {
    otra = window.open(href, target, features);
    otra.window.focus();
  }

  function pasaDoc(idDoc,codDoc,tipoDoc){
    agregarEvento.idDoc.value = idDoc;
    agregarEvento.codDoc.value = codDoc;
    agregarEvento.tipoDoc.value = tipoDoc;

    otra.window.close();
  }

  function eliminar(idEvento,idEIV,nomEIV,hacia){
  elimEIV.hacia.value=hacia;
  elimEIV.idEvento.value=idEvento;
  elimEIV.id_eiv.value=idEIV;
  elimEIV.nomEIV.value=nomEIV;
  	env=window.confirm("Al eliminar la bitácora no se podrá recuperar.\r\n¿Está seguro de continuar?");
	(env)?elimEIV.submit():'return false';

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
    <td><h4 align="center">EISTU - <%=id_eiv %>: <%=nomEIV %>
        <% if (mensaje != null){ %>
      </h4>
      <div align="center"><font color="red"><%=mensaje %></font>
        <%} %>
      </div></td>
  </tr>




    <tr>
      <td><h4 align="center">Bit&aacute;cora del EISTU</h4></td>
    </tr>
    <tr>
      <td><div align="center">
          <table width="585" border="1" align="center">
            <tr bgcolor="#C0C0C0">
              <td width="98"> <div align="center"><strong>Fecha</strong></div></td>
              <td width="220"> <div align="center"><strong>T&iacute;tulo de bit&aacute;cora
                  </strong></div></td>
              <td width="93"><strong>Documento </strong></td>
	          <td width="70"><strong>Editar </strong></td>
              <td width="70"><strong>Eliminar </strong></td>

            </tr>
            <%
 if (eventos!=null){
 Iterator ie=eventos.iterator();
 while (ie.hasNext()){
   EventoVO even= (EventoVO) ie.next();

   %>
            <tr>
              <td><%= sdf.format(even.getFechaEv()) %></td>
              <td><%= even.getTitulo() %></td>
              <% if (even.getIdDocRel().intValue()!=0){ %>
              <td> <%=even.getTipoDoc() %>-<%= even.getCodDoc()%></td>
              <%} else {
     %>
              <td>&nbsp;</td>
              <%}%>
			  <td width="70"><a href="eivAction.do?hacia=editarEvento&idEvento=<%=even.getIdEvento()%>&id_eiv=<%=id_eiv%>&nomEIV=<%=nomEIV%>">
                Editar </a></td>
              <td width="70">
			  <a href="#" onClick="eliminar(<%=even.getIdEvento()%>,<%=id_eiv%>,'<%=nomEIV%>','eliminarEvento')">Eliminar</a>
			  <!---<a href="eivAction.do?hacia=eliminarEvento&idEvento=<%=even.getIdEvento()%>&id_eiv=<%=id_eiv%>&nomEIV=<%=nomEIV%>">
                Eliminar </a>
				 -->
				</td>

            </tr>
            <%
}
}
 %>
          </table>
        </div></td>
    </tr>
<form action="eivAction.do" method="post" name="elimEIV">
<input name="hacia" type="hidden" value="">
<input name="idEvento" type="hidden" value="">
<input name="id_eiv" type="hidden" value="">
<input name="nomEIV" type="hidden" value="">
</form>
</table>

<hr>
 <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div> <div align="right"><a href="../ayuda/eiv.html" target="_blank">Ayuda</a>
  </div>

</body>
</html>
