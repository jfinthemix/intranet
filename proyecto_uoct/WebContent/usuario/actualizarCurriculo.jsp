<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>

<html>
  <head>
    <title>
      actualizarCurriculo
    </title>
    <link href="../util/styla.css" rel="stylesheet" type="text/css">

<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>


      <script language="JavaScript" type="text/javascript">
      function confirmarEnvio(f){
        borrar = window.confirm('Guardar un nuevo curriculo implica eliminar el antiguo. ¿Está seguro de la operación?');
        (borrar)?f.submit():'return false';
      }
    </script>


  </head>
  <body>

<html:form action="/usuario/usuarioAction.do" name="formCurr"  method="post" type="proyecto_uoct.usuario.controller.UsuarioFormBean" enctype="multipart/form-data">
<table width="514" border="0">
  <tr>
    <td height="33"> <h3 align="left">Actualizar el Curr&iacute;culo</h3>
      <div align="left">
        <input type="hidden" name="hacia" value="actualizarCurriculo"/>
      </div></td>
  </tr>
  <tr>
    <td> <div align="left"><html:file property="curric"/> </div></td>
  </tr>
  <tr>
    <td> <div align="left">
        <input type="button" name="Submit" value="Guardar" onclick="confirmarEnvio(this.form);" >
      </div></td>
  </tr>

</table>
  </html:form>
<p>
  <script language="JavaScript" type="text/javascript">
var frmvalidator  = new Validator("formCurr");
frmvalidator.addValidation("curric","req","Debe indicar el curriculum");
</script>
</p>

<hr>

<hr>
<div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>
</body>
    </html>

