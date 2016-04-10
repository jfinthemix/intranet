<%@ page language="java" import="java.io.*, java.util.*" %>
<%
String filepath = request.getParameter("p");
String filename = null;;//request.getParameter("filename");
filename = filepath.substring(filepath.lastIndexOf("//")+2);

response.setContentType("application/octate");// Multipart/Related--application/x-msdownload--application/octate*
response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(filepath)));// + "\\" + filename);
int i;
while ((i=in.read()) != -1) {
out.write(i);
}
in.close();
out.close();
%>
