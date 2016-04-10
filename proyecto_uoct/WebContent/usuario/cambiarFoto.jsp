<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>

<html>
  <head>
    <title>
      Actualizar Fotogra&iacute;a
    </title>
    <link href="../util/styla.css" rel="stylesheet" type="text/css">
      <script language="JavaScript" type="text/javascript">
<!--      function confirmarEnvio(f){--->
<!--     borrar = window.confirm('Guardar una nueva Fotografía implica eliminar la antigua. ¿Está seguro de la operación?');-->
<!--        (borrar)?f.submit():'return false';-->

    <!--  }-->

extArray = new Array(".gif", ".jpg");
function LimitAttach(form, file) {
allowSubmit = false;
if (!file) return;
while (file.indexOf("\\") != -1)
file = file.slice(file.indexOf("\\") + 1);
ext = file.slice(file.indexOf(".")).toLowerCase();
for (var i = 0; i < extArray.length; i++) {
if (extArray[i] == ext) { allowSubmit = true; break; }
}
if (allowSubmit) form.submit();
else
alert("Se permiten únicamente archivos con la extención: "
+ (extArray.join("  ")) + "\nPor favor, seleccione otro archivo "
+ "e intente de nuevo.");
}

</script>


  </head>
  <body>
<html:form action="/usuario/usuarioAction.do" name="formFoto"  method="post" type="proyecto_uoct.usuario.controller.UsuarioFormBean" enctype="multipart/form-data">
<table width="514" border="0">
  <tr>
    <td height="33"> <div align="left">
        <h3>Actualizar Fotograf&iacute;a
          <input type="hidden" name="hacia" value="actualizarFoto"/>
        </h3>
      </div></td>
  </tr>
  <tr>
    <td> <div align="left"></div></td>
  </tr>
  <tr>
    <td> <div align="left"><html:file property="foto"/> </div></td>
  </tr>
  <tr>
    <td> <div align="left">
        <input type="button" name="Submit" value="Actualizar"  onclick="LimitAttach(this.form, this.form.foto.value)" >
      </div></td>
  </tr>
</table>


</html:form>
<hr>

  <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div>
<div align="right"><a href="../ayuda/usuario.html" target="_blank">Ayuda</a> </div>

      </body>
    </html>


