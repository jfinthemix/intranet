package proyecto_uoct.foro.model;

import javax.ejb.EJBLocalObject;
import java.util.List;

public interface TemaForoLocal extends EJBLocalObject {


    public List getTemasForo();

    public void agregarTema(String nuevoTema);

    public boolean borrarTema(int id_tema);
}
