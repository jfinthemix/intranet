package proyecto_uoct.ventas.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;
import proyecto_uoct.dao.VentasDAO;
import proyecto_uoct.ventas.VO.InfoVtaVO;
import proyecto_uoct.ventas.VO.CliVtaVO;
import proyecto_uoct.ventas.VO.VtaVO;
import proyecto_uoct.ventas.VO.BusVtaVO;
import java.util.Iterator;
import java.util.Date;
import javax.mail.internet.InternetAddress;
import javax.mail.Session;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import com.sun.mail.smtp.SMTPTransport;
import javax.mail.MessagingException;
import java.util.StringTokenizer;

public class VentasBean implements SessionBean {
    SessionContext sessionContext;
    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }


    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public void ejbCreate() throws CreateException, CreateException {
    }

    public List getTiposInfo(boolean activo) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            return d.getTiposInfo(activo);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean regInfoVta(InfoVtaVO info) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.regTipoInfo(info);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public InfoVtaVO getDetalleInfoVta(Integer idInfo) {
        VentasDAO d = VentasDAO.getInstance();
        return d.detalleTipoInfo(idInfo);
    }

    public boolean actualizarInfoVta(InfoVtaVO info) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.actuTipoInfo(info);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public boolean regClienteVta(CliVtaVO cli) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.regCliVta(cli);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public List buscarClienteVta(String palClave, boolean activo) {
        VentasDAO d = VentasDAO.getInstance();
        return d.busCliVta(palClave, activo);
    }

    public CliVtaVO getDetalleCliente(Integer idCli) {
        VentasDAO d = VentasDAO.getInstance();
        return d.detalleCliVta(idCli);
    }

    public List exportarAgenda() {
        VentasDAO d = VentasDAO.getInstance();
        return d.agendaCliVta();
    }

    public boolean actualizarCliente(CliVtaVO cli) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.actuCliVta(cli);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public boolean regProcesoVenta(VtaVO vta) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            Integer nv = d.regVenta(vta);
            d.regDetalleVta(vta.getDetVenta(), nv);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public List getEstadosVta() {
        VentasDAO d = VentasDAO.getInstance();
        return d.getEstadosVtas();
    }

    public List buscarVentas(BusVtaVO bus) {
        VentasDAO d = VentasDAO.getInstance();
        List v = d.busVentas(bus);
        if (v != null) {
            Iterator iv = v.iterator();
            while (iv.hasNext()) {
                VtaVO venta = (VtaVO) iv.next();
                venta.setCliente(d.detalleCliVta(venta.getCliente().
                                                 getIdCliente()));
            }
        }
        return v;
    }

    public VtaVO detalleVta(Integer idVenta) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            VtaVO v = d.getVenta(idVenta);
            v.setDetVenta(d.getDetalleVta(idVenta));
            v.setCliente(d.detalleCliVta(v.getCliente().getIdCliente()));
            return v;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public float getUF() {
        VentasDAO d = VentasDAO.getInstance();
        return d.getUF();
    }

    public boolean regFechaCotizacion(String fecha, Integer idVenta) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.regFechaCot(idVenta, fecha);
            d.cambiaEstado(idVenta, new Integer(2));
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public boolean regFechaPago(String fecha, Integer idVenta, Float uf) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.regFechaPago_UF(idVenta, fecha, uf);
            d.cambiaEstado(idVenta, new Integer(3)); // EL 3 INDICA QUE EL NUEVO ESTADO ES "Pago Recibido"
            List det = d.getDetalleVta(idVenta);
            Iterator i = det.iterator();
            while (i.hasNext()) {
                InfoVtaVO info = (InfoVtaVO) i.next();
                d.setPrecioPago(info.getIdDetalle(), info.getPrecio());
            }
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }

    }


    public boolean regFechaEntrega(String fecha, Integer idVenta) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            d.regFechaEntrega(idVenta, fecha);
            d.cambiaEstado(idVenta, new Integer(4)); // El estado nº 4 es "información entregada"
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;

        }

    }

    public boolean anularVenta(Integer idVenta) {

        VentasDAO d = VentasDAO.getInstance();
        try {
            d.cambiaEstado(idVenta, new Integer(6));
            return true;
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }


    public boolean actualizaUF(String uf) {
        VentasDAO d = VentasDAO.getInstance();
        try {
            Float ufF = Float.valueOf(uf);
            d.actualizaUF(ufF);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public void actualizarVenta(VtaVO venta) throws Exception {
        try {
            VentasDAO d = VentasDAO.getInstance();
            d.actualizarVenta(venta);
            d.eliminarDetalleVenta(venta.getIdVenta());
            d.regDetalleVta(venta.getDetVenta(), venta.getIdVenta());
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            throw e;
        }

    }

    public boolean regFechaFin(String fecha, String codFact, Integer idVta) {
        VentasDAO d = VentasDAO.getInstance();
        try {

            d.regFechaFin(fecha, codFact, idVta);
            d.cambiaEstado(idVta, new Integer(5));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }

    }

    public Boolean correoCotizacion(Integer id_venta) {
        VentasDAO d = VentasDAO.getInstance();

        VtaVO venta = d.getVenta(id_venta);

        String contenido = "Unidad Operativa de control de Tránsito\n\r" +
                           "\n\r" +
                           "A través de correo electrónico de Ant., se ha solicitado a la Unidad Operativa de Control de Tránsito" +
                           " (UOCT) un presupuesto por información de tránsito generada por esta Unidad. " +
                           "Al respecto, quisiera informar a usted que el costo de los antecedentes requeridos se detalla a continuación:\n\r";

        if (venta.getDetVenta() != null) {
            Iterator idet = venta.getDetVenta().iterator();
            Float precioItem = new Float("0");
            int precioItemI = 0;
           /* while (idet.hasNext()) {
                InfoVtaVO inf = (InfoVtaVO) idet.next();
                precioItem = new Float(inf.getPrecio().floatValue() *
                                       uf.floatValue());
                int precioItemRedon = Math.round(precioItem.floatValue());
                precioItemI = precioItemRedon * inf.getCantidad().intValue();
                total = new Integer(total.intValue() + precioItemI);
    */
                String tabla =
                        "<table width=\"600\" border=\"1\" bordercolor=\"#FF0000\">" +
                        "<tr bgcolor=\"#ADD8E4\">" +
                        "<td width=\"90\"><strong>Cantidad</strong></td>" +
                        "<td width=\"150\"><strong>Tipo Informaci&oacute;n</strong></td>" +
                        "<td width=\"169\"><strong>Descripci&oacute;n</strong></td>" +
                        "<td width=\"74\"><strong>Precio unitario($)</strong></td>" +
                        "<td width=\"80\"><strong>Precio total($)</strong></td>" +
                        "</tr> </table>";

                /*                            "<tr>" +
                                            "<td><div align=\"center\">" +
                                            inf.getCantidad() "</div></td>" +
                                            "<td>" + inf.getTipoInfo() + "</td>" +
                                            "<td width=\"150\">" +
                                            if (inf.getDescripcion() != null) {
                                        out.print(inf.getDescripcion());
                                    } else {
                                        out.print("&nbsp;");
                                    }
                                    "</td>" +
                                            "<td><div align=\"right\">$" +
                                            util.
                 formateaPrecioPesos((new Integer(precioItemRedon)).
                 toString()) + "</div></td>" +
                                            "<td><div align=\"right\">$" +
                                            util.
                 formateaPrecioPesos((new Integer(precioItemI)).toString()) +
                                            "</div></td>" +
                                            "</tr>" +

                                            "<tr>"
                                            "<td> <strong>UF=$" +
                 uf.toString().replace('.', ',') + "</strong></td>" +
                                            "<td height=\"24\" colspan=\"3\" align=\"right\"><strong>SubTotal (sin IVA):</strong></td>" +
                                            "<td><div align=\"right\">$" +
                 util.formateaPrecioPesos(total.toString()) +
                                            "</div></td>" +
                                            "</tr>"


                                                       "<tr>"+
                 <td height="24" colspan="4" align="right"><strong>IVA</strong></td>
                                                         <td><div align="right">$
                                             <%
                 Float ivaF=new Float(total.floatValue() * 0.19);
                 int iva=Math.round(ivaF.floatValue());
                 String ivaS=new Integer(iva).toString().replace('.',',');
                 out.print(util.formateaPrecioPesos(ivaS));
                                                          %></div></td>
                                                       </tr>
                                                       <tr>
                                                         <td height="24" colspan="4" align="right"><strong>Total ($ con IVA):</strong></td>
                                                         <td><div align="right">$
                                                           <%
                 Integer tot= new Integer((int)Math.round(total.floatValue()*1.19));
                 out.print(util.formateaPrecioPesos(tot.toString())); %>
                                                          </div></td>
                                                       </tr>
                                                 </table>




                 */

                try {

               // java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

                Properties props = new Properties();
                props.put("mail.transport.protocol","smtp");
                props.put("mail.smtp.host", "smtp.uoct.cl");
                props.put("mail.smtp.port", "25");
                //props.put("mail.smtps.auth", "true");
                //props.put("mail.smtp.starttls.enable", "true");

                String from="gf@uoct.cl";
                String rcpt="jf@uoct.cl";
                String subject="prueba correo";
                String body=contenido;

                props.put("mail.user", from);
                //props.put("mail.password", passw);

                //this.setSMTP_AUTH_USER(from);
                //this.setSMTP_AUTH_PWD(passw);
                //Authenticator auth = new SMTPAuthenticator();
                //            auth.setDatos(from,passw);


                Session mailSession = Session.getDefaultInstance(props,null);

                MimeMessage message = new MimeMessage(mailSession);

                if (from.equals("")) {
                    message.setFrom(new InternetAddress(props.getProperty(
                            "mail.from")));
                } else {
                    message.setFrom(new InternetAddress(from));
                }



                StringTokenizer rcpts = new StringTokenizer(rcpt, ",");

                while (rcpts.hasMoreElements()) {
                    String auxRcpt = (String) rcpts.nextElement();
                    message.addRecipient(MimeMessage.RecipientType.TO,
                                         new InternetAddress(auxRcpt));
                }

                message.setSubject(subject);
                message.setText(body);
                message.setContent(body, "text/plain");

               /*
                message.saveChanges();
                Transport transp= mailSession.getTransport("smtp");
                transp.connect();
                transp.sendMessage(message,message.getAllRecipients());
                */

               /*Transport transport = mailSession.getTransport();
               transport.connect();
               transport.sendMessage(message, message.getAllRecipients());
               transport.close();
    */
               SMTPTransport tr =(SMTPTransport)mailSession.getTransport();
               tr.connect();
               tr.send(message,message.getAllRecipients());
               tr.close();

               System.out.print("Listo el correo!!!");
           } catch (MessagingException me) {
                me.printStackTrace();

            }



            }
        return null;
    }
    }

