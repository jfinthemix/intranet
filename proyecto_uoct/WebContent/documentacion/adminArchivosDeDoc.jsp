<%@page import="java.util.List,java.util.Iterator,proyecto_uoct.documentacion.VO.ArchivoDocVO"%>
<%@taglib uri="/WEB-INF/lib/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/lib/struts-template.tld" prefix="template"%>
<%@taglib uri="/WEB-INF/lib/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/lib/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/lib/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/lib/struts-nested.tld" prefix="nested"%>
<%
List archivos=(List) request.getAttribute("archivos");
String tipoDoc=(String) request.getAttribute("tipoDoc");
String codDoc=(String)request.getAttribute("codDoc");
Integer idDoc=(Integer)request.getAttribute("idDoc");
String mensaje=(String) request.getAttribute("mensaje");
%>

<html>
<head>
<title>
Administrar archivos de Documento
</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../util/valid/gen_validatorv2.js" type="text/javascript"></script>

<script type="text/javascript">


 function valLargoFile()
{
  var frm = document.forms['inforActividades'];// reemplazar nombre del form
  archivo=frm.informe.value; //reemplazar nombre del file
  largo=20; //reemplazar la longitud del campo
  while(archivo.indexOf('\\') !=-1){
  archivo=archivo.slice(archivo.indexOf('\\')+1);
  }
  if(archivo.length>largo){
  alert("nombre del archivo demasiado largo");
  return false;
  }else{
  return true;
  }

}
</script>

</head>
<body bgcolor="#ffffff">
<div align="center">
  <table width="259" border="0">
    <tr>
      <td><div align="center">
          <h3>Administrar archivos de :<%=tipoDoc%>-<%=codDoc%></h3>
        </div></td>
    </tr>
    <tr>
      <td><div align="center">
          <html:form action="/documentacion/documentoAction.do" name="form_regDoc"  method="post" type="proyecto_uoct.documentacion.controller.RegDocFormBean" enctype="multipart/form-data">
          <table width="413" border="1">
            <input type="hidden" name="idDoc" value="<%=idDoc%>"/>
            <input type="hidden" name="tipoDoc" value="<%=tipoDoc%>"/>
            <input type="hidden" name="codDoc" value="<%=codDoc%>"/>
            <input type="hidden" name="hacia" value="agregarArchivoDoc"/>
            <tr>
              <td width="107" bgcolor="#C0C0C0">Agregar archivo</td>
              <td width="296"> <html:file property="eldoc"/> </td>
            </tr>
            <tr>
              <td colspan="2"> <div align="center">
                  <input type="submit" name="Submit" value="Agregar">
                </div></td>
            </tr>
          </table>
		</html:form>
     <script language="JavaScript" type="text/javascript">
	var frmvalidator  = new Validator("form_regDoc");
	frmvalidator.setAddnlValidationFunction("valLargoFile");
	frmvalidator.addValidation("eldoc","req","Debe indicar el archivo");
	</script>
	    </div></td>
    </tr>
    <tr>
      <td> <div align="center">
          <table width="417" border="0">
            <tr>
              <%if(archivos!=null){ %>
              <td width="233" bgcolor="#F7FBC4"><div align="center"><strong>Archivos
                  asociados</strong></div></td>
              <td width="83" bgcolor="#FF0000"><div align="center"><strong>Eliminar</strong></div></td>
            </tr>
            <%
	    Iterator i=archivos.iterator();
	    while(i.hasNext()){
	      ArchivoDocVO a=(ArchivoDocVO) i.next();
    	  %>
            <tr>
              <td><%=a.getNomArchivo()%></td>
              <td><a href="documentoAction.do?hacia=borrarArchivoDoc&idArchivo=<%=a.getIdArchivo()%>&idDoc=<%=idDoc%>&codDoc=<%=codDoc%>&tipoDoc=<%=tipoDoc%>">Eliminar</a>
            </tr>
            <%
  }
}
  %>
          </table>
        </div>
	</td>
    </tr>
  </table>
  <hr>
    <div align="center"><img src="../util/img/volver.jpg" alt="Volver" onclick="history.back()"></div><div align="right"><a href="../ayuda/documentacion.html" target="_blank">Ayuda</a>
  </div>
</div>
</body>
</html>
