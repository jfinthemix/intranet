<%@page contentType="text/html; charset=iso-8859-1" language="java" import="java.util.List,proyecto_uoct.EmpMant.VO.*,java.util.Iterator"  errorPage=""%>
<%@ page import="java.text.SimpleDateFormat, java.util.Date, java.util.Calendar" %>
<%@taglib prefix="display" uri="/displaytag_12"%>
<%
List listaempresas                      = (List) request.getAttribute("listaempresas");
Integer var_vigente                     = (Integer) request.getAttribute("vigente");

Integer cero                            = new Integer(0);
Integer uno                             = new Integer(1);
Integer dos                             = new Integer(2);

%>
<html>
<head>
<title>Empresa mantenedora</title>
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="../util/styla.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/javascript">
function valida_envia(){
  document.form_buscar.submit();
}
function popUp(URL){
  day = new Date();
  id = day.getTime();
  eval("page" + id + " = window.open(URL, '" + id + "', 'toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left = 440,top = 412');");
}
</script>
</head>

<body bgcolor="#FFFFFF" text="#000000">
  <form name="form_buscar" method="post" action="../empmant/empMantAction.do">
    <table width="85%" border="0" align="center">
      <tr>
        <td colspan="2">Empresa mantenedora</td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">
          <table width="100%" border="1" align="center">
            <tr>
              <td width="15%" bgcolor="#ADD8E4"> <div align="right"><strong>empresas</strong></div></td>
              <td width="85%">
                <select name="vigente" size="1" id="vigente">
                  <%
                  if(dos.equals(var_vigente)){
                    out.print("<option value='2' selected>Todas</option>");
                    out.print("<option value='1'>Vigente</option>");
                    out.print("<option value='0'>No vigente</option>");
                  }
                  else{
                    if(uno.equals(var_vigente)){
                      out.print("<option value='1' selected>Vigente</option>");
                      out.print("<option value='2'>Todas</option>");
                      out.print("<option value='0'>No vigente</option>");
                    }
                    else{
                      if(cero.equals(var_vigente)){
                        out.print("<option value='0' selected>No vigente</option>");
                        out.print("<option value='2'>Todas</option>");
                        out.print("<option value='1'>Vigente</option>");
                      }
                      else{
                        if(var_vigente==null){%>
                        <option value="2" selected>Todas</option>
                        <option value="1">Vigente</option>
                        <option value="0">No vigente</option>
                        <%
                        }
                      }
                    }
                  }
                  %>
                </select>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <tr>
        <td colspan="2"><div align="center">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td> <font size="2">&nbsp; </font> </td>
              <td><div align="right"><font size="2">
                <input name="hacia" type="hidden" value="Buscar empresa">
                <input name="boton" type="button" value="Buscar empresa" onclick="valida_envia()">
                </font></div>
              </td>
            </tr>
          </table>
        </div></td>
      </tr>
      <tr>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td colspan="2">
          <table ="100%" border="1" align="center" cellpadding="0" cellspacing="0">
            <tr bgcolor="#ADD8E4">
              <td width="5%" colspan="2" height="2"><div align="center"><b>n</b></div></td>
              <td width="15%" bgcolor="#ADD8E4" height="2"><div align="center"><b>empresa</b></div></td>
              <td width="10%" bgcolor="#ADD8E4" height="2"><div align="center"><b>vigencia</b></div></td>
              <td width="15%" bgcolor="#ADD8E4" height="2"><div align="center"><b>tipo dispositivo</b></div></td>
              <td width="55%" bgcolor="#ADD8E4" height="2"><div align="center"><b>mail según tipo de dispositivo en mantención</b></div></td>
            </tr>

            <%
            if(listaempresas!=null){
              Iterator ile=listaempresas.iterator();
              int indice = 0;
              String nomAnterior = "";
              String mail_2;
              while(ile.hasNext()){
                EmpMantVO empmant = (EmpMantVO) ile.next();%>


                <tr>
                  <td rowspan="2" bgcolor="#ADD8E4" align="left" ><div align="right"><font size="1"><strong>
                  <%
                  if(empmant.get_nombre().equals(nomAnterior)){
                    out.print("&nbsp;");
                    out.print("</strong></font></td><td rowspan='2' align='center' >");
                    out.print("&nbsp;");
                  }
                  else {
                    indice++;
                    out.print(indice);
                    out.print("</strong></font></td><td rowspan='2' align='center' >"); %>
                    <a href="empMantAction.do?hacia=ver_emp&&id_emp=<%out.print(empmant.get_id_empresa());%>" title="Editar empresa"><img src="imagenes/icono_update.png" width="17" height="17" alt="" border="0"></a>
                  <%
                  }
                  %>
                  </td>
                  <td rowspan="2" align="left" ><font size="1">
                    <%
                    if(empmant.get_nombre().equals(nomAnterior)){
                      out.print("&nbsp;");
                    }
                    else{
                      %>
                      <a href="javascript:popUp('http://localhost:8090/WM_1/empmant/empMantAction.do?hacia=popUp&&nombre=<% out.print(empmant.get_nombre());%>&&direccion=<% out.print(empmant.get_direccion());%>&&telefono=<% out.print(empmant.get_telefono());%>&&vigencia=<% out.print(empmant.get_vigente());%>&&tipo_dispositivo=<% out.print(empmant.get_tipo_dispositivo());%>&&mail_1=<% out.print(empmant.get_mail_1());%>&&tipo_dispositivo2=<% out.print(empmant.get_tipo_dispositivo2());%>&&mail_2=<% out.print(empmant.get_mail_2());%>')"><% out.print(empmant.get_nombre());%></a>
                    <%
                    }%>

                    </font>
                  </td>

                 <td rowspan="2" align="left" ><font size="1">
                    <%
                    if(empmant.get_nombre().equals(nomAnterior)){
                      out.print("&nbsp;");
                    }
                    else{
                      if(empmant.get_vigente().intValue()==1){
                        out.println("SI");
                      }
                      if(empmant.get_vigente().intValue()==0){
                        out.println("NO");
                      }
                    }%></font>
                  </td>
                  <td align="left" ><font size="1">
                    <% out.println(empmant.get_tipo_dispositivo()); %></font>
                  </td>
                  <td align="left" ><font size="1">
                    <% out.println(empmant.get_mail_1());   %></font>
                  </td>
                </tr>
                <tr>
                  <td align="left" ><font size="1">
                    <% out.println(empmant.get_tipo_dispositivo2()); %></font>
                  </td>
                  <td align="left" ><font size="1">
                    <% out.println(empmant.get_mail_2());   %></font>
                  </td>
                </tr>
                <%
                nomAnterior = empmant.get_nombre();
               }
            }
            %>
          </table>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>
