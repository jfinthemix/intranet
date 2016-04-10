<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*, java.util.List,java.util.Iterator,proyecto_uoct.foro.VO.TemasVO" errorPage=""%>
<%List listatemas = (List) request.getAttribute("listadetemas");%>
<html>
<head>
<title>Registrar Foro
</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">

<!-- validador -->
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


</head>
<body>
<div align="center">
  <table width="684" border="0" >
    <tr>
      <td width="678"><h3>Ingresar Foro</h3></td>
    </tr>
	<form action="foroAction.do" name="regForoForm" method="POST">
    <tr>
      <td>
          <input type="hidden" name="hacia" value="agregarForo" />
          <table width="524" border="1" align="center">
            <tr>
              <td width="173" valign="top" bgcolor="#ADD8E4">
                <div align="right"><strong>Tema
                  al cual pertenece :</strong></div></td>
              <td width="335"> <select name="id_tema">
                  <option>-------</option>
                  <%
          Iterator i = listatemas.iterator();
          while (i.hasNext()) {
            TemasVO tema = (TemasVO) i.next();
            out.print("<option value=" + tema.getIdTema() + ">" + tema.getTema() + "</option>");
          }
        %>
                </select> </td>
            </tr>
            <tr>
              <td valign="top" bgcolor="#ADD8E4">
                <div align="right"><strong>T&iacute;tulo:</strong></div></td>
              <td> <input type="text" name="titForo" maxlength="200" > </td>
            </tr>
            <tr>
              <td valign="top" bgcolor="#ADD8E4">
                <div align="right"><strong>Descripci&oacute;n
                  del Foro:</strong></div></td>
              <td> <textarea name="descForo" cols="50" rows="6" ></textarea>
              </td>
            </tr>
            <tr>
              <td valign="top" bgcolor="#ADD8E4">
                <div align="right"><strong>Primer Post:</strong></div></td>
              <td> <textarea name="elPost" cols="50" rows="6"></textarea>
              </td>
            </tr>
          </table>
          <div align="center"> </div>
          <script language="JavaScript" type="text/javascript">
    var frmvalidator  = new Validator("regForoForm");

    frmvalidator.addValidation("id_tema","dontselect=0","Debe seleccionar el tema al cual pertenece el foro");


    frmvalidator.addValidation("titForo","req","Debe ingresar Título");
    frmvalidator.addValidation("titForo","maxlen=200","Título no puede superar los 200 caracteres");

    frmvalidator.addValidation("descForo","req","Debe ingresar una descripción");
    frmvalidator.addValidation("descForo","maxlen=200","Descripción no puede superar los 200 caracteres");


    frmvalidator.addValidation("elPost","req","Debe Ingresar el primer post del foro");
    frmvalidator.addValidation("elPost","maxlen=1000","Post no puede superar los 1000 caracteres");

  </script>
        </td>
    </tr>

    <tr>
      <td><div align="center">
            <input type="submit" name="Submit" value="Ingresar">
          </div></td>

    </tr>
	</form>
  </table>
</div>
<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>  <div align="right"><a href="../ayuda/foro.html" target="_blank">Ayuda</a>
  </div>

  </body>
</html>
