<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*,proyecto_uoct.documentacion.VO.DocumentoVO,java.util.List, java.util.Iterator,proyecto_uoct.usuario.VO.UsuarioVO, proyecto_uoct.documentacion.VO.DocumentodeListaVO" errorPage="" %>
<%@page import="java.text.SimpleDateFormat,proyecto_uoct.documentacion.VO.ArchivoDocVO" %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
DocumentoVO detDoc= (DocumentoVO) request.getAttribute("detDoc");
Integer ses_idtipousu= (Integer) request.getAttribute("ses_idtipousu");
%>
<html>
<head>
<title>Detalle Documento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="750" border="0">
  <tr>
    <td><h3 align="center">Detalle de Documento:<%=detDoc.getTipoDoc() %>-<%=detDoc.getCodDoc() %></h3></td>
  </tr>
  <tr>
    <td><div align="center">
        <table width="69%" border="1" align="center">
          <tr>
            <td width="38%" bgcolor="#ADD8E4"> <div align="right"><strong>C&oacute;digo</strong></div></td>
            <td width="62%"> <div align="left"><%=detDoc.getTipoDoc() %>-<%=detDoc.getCodDoc() %></div></td>
          </tr>
          <tr>
            <td width="38%" bgcolor="#ADD8E4"> <div align="right"><strong>Descargar</strong></div></td>
            <td width="62%"> <div align="left">
                <%
    if (detDoc.getArchivos().size()!=0){
      Iterator iarch=detDoc.getArchivos().iterator();
      while (iarch.hasNext()){
        ArchivoDocVO a=(ArchivoDocVO) iarch.next();%>
                <div align="center"><a href="documentoAction.do?hacia=descargarArchivo&idArchivo=<%=a.getIdArchivo()%>"><%=a.getNomArchivo() %></a></div>
                <%  }
      }else{
        out.print("&nbsp;");}
        %>
              </div></td>
          </tr>
          <tr>
            <td width="38%" bgcolor="#ADD8E4"> <div align="right"><strong>Sentido</strong></div></td>
            <td width="62%"> <div align="left"><%=detDoc.getSentidoDoc() %> </div></td>
          </tr>
          <tr>
            <td width="38%" bgcolor="#ADD8E4"> <div align="right"><strong>Estado</strong></div></td>
            <td width="62%"> <div align="left">
                <%
    if (detDoc.getIdEstado()){out.println("Respondido");}
    else{out.println("Sin Respuesta");}

    %>
              </div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"> <div align="right"><strong>Materia</strong></div></td>
            <td> <div align="left"><%=detDoc.getMateriaDoc() %></div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>
                <% if(detDoc.getSentidoDoc().toUpperCase().equals("ENTRANTE")){out.print("Fecha de Ingreso");}else{out.print("Fecha de Envío"); }%>
                </strong></div></td>
            <td> <div align="left"><%= sdf.format(detDoc.getFechaio()) %> </div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Fecha de Documento</strong></div></td>
            <td> <div align="left"><%=sdf.format(detDoc.getFechaDoc()) %></div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Responsable</strong></div></td>
            <td> <div align="left">
                <%
       List resps=detDoc.getResponsableDoc();
       if(resps!=null){
         Iterator i=resps.iterator();
         while (i.hasNext()){
           UsuarioVO unresp= (UsuarioVO) i.next();
           if(unresp.getNombreUsu2()!=null){
             out.println(unresp.getNombreUsu()+" "+ unresp.getNombreUsu2()+" "+unresp.getApellUsu()+"<br>");
           }else{
             out.println(unresp.getNombreUsu()+" "+unresp.getApellUsu()+"<br>");
           }
         }
       }
        %>
              </div></td>
          </tr>
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Ant.</strong></div></td>
            <td><div align="left">
                <%
       List docs= detDoc.getDocsRelacionados();
       if(docs.size()!=0){
       Iterator ii=docs.iterator();
       while (ii.hasNext()){
         DocumentodeListaVO doc= (DocumentodeListaVO) ii.next();
         out.println ("<a href=\"documentoAction.do?hacia=detalleDoc&id_doc="+doc.getIdDoc()+"\">"+doc.getTipoDoc()+"-"+doc.getCodDoc()+"</a><br>");
       }
      }else{out.print("&nbsp;");}
        %>
              </div></td>
          </tr>


          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Respuesta</strong></div></td>
            <td><div align="left">
              <%
              List docsPos= detDoc.getDocsPosteriores();
              if(docsPos.size()!=0){
                Iterator iii=docsPos.iterator();
                while (iii.hasNext()){
                  DocumentodeListaVO doc= (DocumentodeListaVO) iii.next();
                  out.println ("<a href=\"documentoAction.do?hacia=detalleDoc&id_doc="+doc.getIdDoc()+"\">"+doc.getTipoDoc()+"-"+doc.getCodDoc()+"</a><br>");
                }
              }else{out.print("&nbsp;");}
              %>
            </div></td>
          </tr>



          <!--<tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Resumen del Documento</strong></div></td>
            <td><div align="left"><%=detDoc.getResumen() %></div></td>
          </tr>
          -->
          <tr>
            <td bgcolor="#ADD8E4"><div align="right"><strong>Datos del Cliente
                Relacionado</strong></div></td>
            <td> <table border="0">
                <tr>
                  <td width="15%"><strong>Nombre</strong></td>
                  <td width="85%"><a href="clienteAction.do?hacia=detalleCliente&id_cli=<%=detDoc.getIdCli()%>"><%= detDoc.getNomCli()%> <%= detDoc.getApeCli()%></a></td>
                </tr>
                <tr>
                  <td><strong>Instituci&oacute;n</strong></td>
                  <td><%if(detDoc.getEntidad()!=null){ %>
                    <a href="clienteAction.do?hacia=detalleEntExt&id_ent=<%=detDoc.getIdEntidad() %>"><%= detDoc.getEntidad()%></a>
                  <%}else{ %>
                  &nbsp;
                 <%} %>
                  </td>
                </tr>
              </table></td>
          </tr>
        </table>
      </div></td>
  </tr>
</table>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a></div>
</body>
</html>
