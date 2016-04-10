package proyecto_uoct.EIV.model;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.ejb.CreateException;
import java.util.List;

import proyecto_uoct.dao.EIVDAO;
import java.util.Date;
import proyecto_uoct.EIV.VO.EIVVO;
import proyecto_uoct.EIV.VO.FlujoVO;
import proyecto_uoct.EIV.VO.EventoVO;
import proyecto_uoct.EIV.VO.BuscadorEIVVO;
import proyecto_uoct.EIV.VO.BusFlujosVO;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import java.util.StringTokenizer;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.Message.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import com.sun.mail.smtp.SMTPTransport;


public class EIVBean implements SessionBean {
    public EIVBean() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    SessionContext sessionContext;
    public void ejbCreate() throws CreateException {
    }

    public void ejbRemove() {
    }

    public void ejbActivate() {
    }

    public void ejbPassivate() {
    }


    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    public List getEstadosEIV() {
        List estados = null;
        EIVDAO eivdao = EIVDAO.getInstance();
        estados = eivdao.getEstadosEIV();
        return estados;
    }







    public EIVVO getDetalleEIV(Integer id_eiv) {
        EIVVO eiv = null;
        EIVDAO eivdao = new EIVDAO();
        eiv = eivdao.getDetalleEIV(id_eiv);
        return eiv;
    }

    public List getEventosEIV(Integer id_eiv) {
        EIVDAO eivdao = EIVDAO.getInstance();
        List eventos = null;
        eventos = eivdao.getEventos(id_eiv);
        return eventos;

    }

    public List getFlujosxCalle(String calle) {
        EIVDAO eivdao = EIVDAO.getInstance();
        List flujos = null;
        flujos = eivdao.getFlujosxCalle(calle);
        return flujos;
    }

    public List getFlujosxEIV(Integer id_eiv) {
        EIVDAO eivdao = EIVDAO.getInstance();
        List flujos = null;
        flujos = eivdao.getFlujosEIV(id_eiv);
        return flujos;

    }

    public List getFlujosxFecha(Date fecha) {
        EIVDAO eivdao = EIVDAO.getInstance();
        List flujos = null;
        flujos = eivdao.getFlujosxFecha(fecha);
        return flujos;
    }

    public List getListaComunas() {

        EIVDAO eivdao = EIVDAO.getInstance();

        List comunas = eivdao.getListaComunas();
        return comunas;
    }

    public List getTodaslasRedes() {
        EIVDAO eivdao = EIVDAO.getInstance();

        List redes = eivdao.getTodaslasRedes();
        return redes;

    }

    public Integer insertEIV(EIVVO nuevoeiv) {

        EIVDAO eivdao = EIVDAO.getInstance();

        Integer id_nuevo = null;
        try {
            id_nuevo = eivdao.insertEIV(nuevoeiv);
            eivdao.insertEIV_red(id_nuevo, nuevoeiv.getRedes());
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return id_nuevo;
        }

        return id_nuevo;
    }

    public void insertFlujo(FlujoVO flujo) {
        EIVDAO eivdao = EIVDAO.getInstance();
        eivdao.insertFlujo(flujo);
    }

    /*
    *Se inserta el evento en la base de datos con los datos de 'evento' y correspondiente al EIV 'id_eiv'.
    Los tipos de eventos son:
(1) Primera Revisión: Implica el cambio de estado del EIV a 'En Observación{2}'
(2) Reingreso de documentación.
(3) Despacho de documentacion:
(4) Aprobacion por parte de UOCT:Implica el cambio de estado del EIV a 'Aprobado por UOCT{3}'
(5) Rechazo por parte de UOCT: Implica el cambio de estado del EIV a 'Rechazado por UOCT{4}'
(6) Aprobacion por parte de SEREMITT
(7) Rechazo por parte de SEREMITT
(8) Otro tipo de bitácora.
    */

    public boolean insertEventoEIV(EventoVO evento, Integer id_eiv, Integer tipoEvento) {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            eivdao.insertEventoEIV(evento, id_eiv, tipoEvento);
            if(tipoEvento.intValue()==1){
                eivdao.actuEstadoEIV(new Integer(2),id_eiv);// Estado 2 = 'En observacion'
            }
            if(tipoEvento.intValue()==4){
                eivdao.actuEstadoEIV(new Integer(3),id_eiv); //Estado 3= 'Aprobado por UOCT'
            }

            if(tipoEvento.intValue()==5){
                eivdao.actuEstadoEIV(new Integer(4),id_eiv); // Estado 4 = 'Rechazado por UOCT'
            }

            if(tipoEvento.intValue()==6){
                eivdao.cambiaEstadoSeremitt(id_eiv, 1);// IMPLICA QUE EL VALOR 1 ES 'APROBADO'
            }

            if(tipoEvento.intValue()==7){
                eivdao.cambiaEstadoSeremitt(id_eiv, 2); // IMPLICA QUE EL VALOR 2 ES 'RECHAZADO'
            }

            return true;
        } catch (Exception e) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public boolean actuaEstadoEIV(Integer id_estado, Integer id_eiv) {

        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            eivdao.actuEstadoEIV(id_estado, id_eiv);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public List getTiposEIV() {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            return eivdao.getTiposEIV();
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean insertTipoEIV(String tipo) {
        EIVDAO eiv = EIVDAO.getInstance();
        try {
            return eiv.insertTipoEIV(tipo);
        } catch (Exception ex) {
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public boolean eliminarTipoEIV(Integer tipo) {
        EIVDAO eiv = EIVDAO.getInstance();
        try {
            return eiv.eliminarTipoEIV(tipo);
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public boolean agregarComuna(String nomComuna) {
        EIVDAO eiv = EIVDAO.getInstance();
        try {
            return eiv.insertComuna(nomComuna);
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean eliminarComuna(Integer idComuna) {
        EIVDAO eiv = EIVDAO.getInstance();
        try {
            return eiv.eliminarComuna(idComuna);
        } catch (Exception ex) {
            return false;
        }
    }

    public List buscarEIV(BuscadorEIVVO bus) {
        EIVDAO eiv = EIVDAO.getInstance();
        return eiv.buscarEIV(bus);
    }

    public boolean actualizarEIV(EIVVO eiv) {
        EIVDAO eivdao = EIVDAO.getInstance();

        try {
            eivdao.actualizarEIV(eiv);
            eivdao.eliminarRedesEIV(eiv.getIdEIV());
            if (eiv.getRedes() != null) {
                eivdao.insertEIV_red(eiv.getIdEIV(), eiv.getRedes());
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public boolean eliminarFlujo(Integer idFlujo) {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            if (eivdao.eliminarFlujo(idFlujo)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;

        }
    }

    public EventoVO getDetalleEvento(Integer idEvento) {
        EIVDAO eivdao = EIVDAO.getInstance();
        return eivdao.getDetalleEvento(idEvento);
    }

  /*  public boolean despacharEIV(EventoVO despacho, Integer id_eiv) {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            eivdao.insertEventoEIV(despacho, id_eiv, new Integer(2));
            eivdao.actuEstadoEIV(new Integer(2), id_eiv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }*/

   /* public boolean aprobar_rechazar(EventoVO evento, Integer idEIV,
                                    Integer accion) {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {

            if (accion.intValue() == 1) {
                eivdao.insertEventoEIV(evento, idEIV, new Integer(3));
                eivdao.actuEstadoEIV(new Integer(3), idEIV);
            } else {
                eivdao.actuEstadoEIV(new Integer(4), idEIV);
                eivdao.insertEventoEIV(evento, idEIV, new Integer(4));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }
    */
    public boolean eliminarEvento(Integer idEvento) {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            eivdao.eliminarEvento(idEvento);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            sessionContext.setRollbackOnly();
            return false;
        }
    }

    public List buscarFlujos(BusFlujosVO busflujos) {
        EIVDAO eivdao = EIVDAO.getInstance();
        return eivdao.buscarFlujos(busflujos);
    }

    public void alertaEmail(String from,String passw, String rcpt, String subject,
                            String body) throws Exception {

        try {

            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

            Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            props.put("mail.smtp.host", "smtp.uoct.cl");
            props.put("mail.smtp.port", "25");
            //props.put("mail.smtps.auth", "true");
            //props.put("mail.smtp.starttls.enable", "true");
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
       } catch (MessagingException me) {
            me.printStackTrace();
            throw me;
        }


    }



    public List buscaEIVxFechaVenc(Date fechaVenc)throws Exception {
        EIVDAO eivdao = EIVDAO.getInstance();
        try {
            return eivdao.buscarEIVxFechaVenc(fechaVenc);
        } catch (Exception ex) {
            throw ex;
        }

    }

    public void actualizarEvento( EventoVO evento) throws Exception {
        EIVDAO eivdao = EIVDAO.getInstance();
            try {
                eivdao.actualizarEvento(evento);
            } catch (Exception ex) {
                throw ex;
            }


    }

    private void jbInit() throws Exception {
    }

    /* private void setSMTP_AUTH_USER(String username) {
    }

    private void setSMTP_AUTH_PWD(String psw) {
    }

   String SMTP_AUTH_USER=null;
    String sMTP_AUTH_PWD;
    private class SMTPAuthenticator extends javax.mail.Authenticator {

                public PasswordAuthentication getPasswordAuthentication() {
                        String username = SMTP_AUTH_USER;
                        String password = SMTP_AUTH_PWD;
                        return new PasswordAuthentication(username, password);
                }
        }*/

}
