<%@ page language="java" import="java.io.*, java.util.*,java.io.File" %>
<%
//File b = (File)request.getAttribute("archivo");
byte[] b = (byte[])request.getAttribute("archivo");
ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(b,0,b.length);

response.setContentType("application/download");// Multipart/Related--application/x-msdownload--application/octate*
response.setHeader("content-disposition", "attachment; fileName=\"curriculo.doc\"");
response.setContentLength(output.size());
//DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(b)));
OutputStream outt = response.getOutputStream();
             output.writeTo(outt);
             outt.flush();
             outt.close();


%>
