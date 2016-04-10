package proyecto_uoct.mensaje.model;

import javax.ejb.EJBLocalObject;

public interface MensajeLocal extends EJBLocalObject {

    public String getMensaje(int id);

    public boolean setMensaje(String mensaje, int id);

}
