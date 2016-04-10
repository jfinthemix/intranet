package proyecto_uoct.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.ejb.EJBHome;
import javax.ejb.EJBLocalHome;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;


/*
 ------------
               1
 ----------------


 importar los Local y LocalHome Interfaces de cada bean
 por ej:

 import com.duoc.cajacobranza.beneficios.convenios.logic.ConvenioLocal;
 import com.duoc.cajacobranza.beneficios.convenios.logic.ConvenioLocalHome;

 */
import proyecto_uoct.foro.model.TemaForoLocal;
import proyecto_uoct.foro.model.TemaForoLocalHome;
import proyecto_uoct.foro.model.ForosLocal;
import proyecto_uoct.foro.model.ForosLocalHome;
import proyecto_uoct.infoyrep.model.ReportajeLocal;
import proyecto_uoct.infoyrep.model.ReportajeLocalHome;
import proyecto_uoct.usuario.model.UsuarioLocal;
import proyecto_uoct.usuario.model.UsuarioLocalHome;
import proyecto_uoct.documentacion.model.ClienteLocal;
import proyecto_uoct.documentacion.model.ClienteLocalHome;
import proyecto_uoct.documentacion.model.DocumentoLocal;
import proyecto_uoct.documentacion.model.DocumentoLocalHome;
import proyecto_uoct.EIV.model.EIVLocal;
import proyecto_uoct.EIV.model.EIVLocalHome;
import proyecto_uoct.proyecto.model.ProyectoLocal;
import proyecto_uoct.proyecto.model.ProyectoLocalHome;
import proyecto_uoct.reservas.model.RecursosLocal;
import proyecto_uoct.reservas.model.RecursosLocalHome;
import proyecto_uoct.infoyrep.model.InfoInstitLocal;
import proyecto_uoct.infoyrep.model.InfoInstitLocalHome;
import proyecto_uoct.usuario.model.PerfilLocal;
import proyecto_uoct.usuario.model.PerfilLocalHome;
import proyecto_uoct.inventario.model.InventarioLocal;
import proyecto_uoct.inventario.model.InventarioLocalHome;
import proyecto_uoct.ventas.model.VentasLocal;
import proyecto_uoct.ventas.model.VentasLocalHome;
import proyecto_uoct.mensaje.model.MensajeLocal;
import proyecto_uoct.mensaje.model.MensajeLocalHome;
import proyecto_uoct.fallas.model.FallasLocal;
import proyecto_uoct.fallas.model.FallasLocalHome;
import proyecto_uoct.EmpMant.model.EmpMantLocal;
import proyecto_uoct.EmpMant.model.EmpMantLocalHome;
import proyecto_uoct.mantenedorSistemas.model.MantenedorSistemasLocal;
import proyecto_uoct.mantenedorSistemas.model.MantenedorSistemasLocalHome;
import proyecto_uoct.mantenedorSubsistemas.model.MantenedorSubsistemasLocal;
import proyecto_uoct.mantenedorSubsistemas.model.MantenedorSubsistemasLocalHome;
import proyecto_uoct.mantenedorDispositivos.model.MantenedorDispositivosLocal;
import proyecto_uoct.mantenedorDispositivos.model.MantenedorDispositivosLocalHome;

/**
 *  Esta clase implementa el patron Service Locator. Permite obtener referencias
 *  a interfaces Home (Local y Remota de EJBs y referencias a interfaces Local y
 *  Remota de un EJB. Además utiliza el patron Singleton para facilitar la
 *  creación de un objeto LocalizadorDeServicios. La forma de uso es la siguente:<br><br>
 *
 *  ReferenciaLocalEJB ejb = LocalizadorDeServicios.getInstance().getReferenciaLocalEJB();<br><br>
 *
 *  Este código ha sido tomado de una implementacion de Sun. <br>
 *  Ademas de tener métodos especificos que retornan la referencia local o remota
 *  de un EJB de la capa de infraestructura, permite obtener el home local y
 *  remoto de cualquier EJB recibiendo como parametro el nombre JNDI del home.
 *  Para obtener la referencia de un EJB especifico utiliza un archivo de
 *  propiedades : NombresJNDI.properties para obtener los nombres JNDI de cada EJB.
 * @author Ricardo Reyes
 * MODIFIED by Daniel Saez Srain
 * Last Modify 21/11/2005
 * @version 1.0
 */
public class LocalizadorServicios {
    /**
     * contexto inicial del arbol JNDI.
     */
    private InitialContext ic;

    /**
     * cache donde se guardan la referencias a las interfaces Home usando el
     * nombre JNDI como llave.
     */
    private Map cache;

    /**
     * referencia estatica para implementar el Singleton.
     */
    private static LocalizadorServicios me;

    /**
     * cache de la referencia local del EJB Ejecutor.
     */
    /*  crear una interfaz local de cada bean
     ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
     por ej:

         private ConvenioLocal convenioLocal;
     */
    /**
     * objeto propiedades donde se guardan los nombres JNDI de los EJBs
     * de infraestructura. Se carga desde el archivo de propiedades.
     +++++++++++++++++++++++++++++++++++++++++++++++

     --------------------
                   /
                  |       2
                   \
     ----------------------------------
     */

    private TemaForoLocal temaForoLocal;

    private ForosLocal forosLocal;

    private ReportajeLocal reportajeLocal;

    private UsuarioLocal usuarioLocal;

    private ClienteLocal clienteLocal;

    private DocumentoLocal documentoLocal;

    private EIVLocal eivLocal;

    private ProyectoLocal proyectoLocal;

    private RecursosLocal recursosLocal;

    private InfoInstitLocal infoInstitLocal;

    private PerfilLocal perfilLocal;

    private InventarioLocal inventarioLocal;

    private VentasLocal ventasLocal;

    private MensajeLocal mensajeLocal;

    private FallasLocal fallasLocal;

    private EmpMantLocal empmantLocal;

    private MantenedorSistemasLocal sistemaLocal;

    private MantenedorSubsistemasLocal subsistemaLocal;

    private MantenedorDispositivosLocal dispositivoLocal;

    private ResourceBundle props;




    /**
     * nombre del archivo de propiedades....cambiar el paquete donde esta el archivo NombresJNDI
     */

    private final String nombresJNDI =
            "proyecto_uoct.common.NombresJNDI";
    /**
     * bloque estatico donde se crea la unica referencia del LocalizadorDeServicios
     */
    static {
        try {
            me = new LocalizadorServicios();
        } catch (LocalizadorServiciosException se) {
            System.err.println(se);
            se.printStackTrace(System.err);
        }
    }

    /**
     * Constructor privado para que solo sea llamado desde esta misma clase.
     *
     * @throws LocalizadorServiciosException si ocurre alguna excepcion al obtener
     *         el contexto inicial o no se puede encontrar el archivo de propiedades
     * el contexto inicial o al cargar el archivo de propiedades.
     */
    private LocalizadorServicios() throws LocalizadorServiciosException {
        try {
            ic = new InitialContext();
            cache = Collections.synchronizedMap(new HashMap());
            props = ResourceBundle.getBundle(nombresJNDI);
        } catch (NamingException ne) {
            ne.printStackTrace();
            throw new LocalizadorServiciosException(ne);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new LocalizadorServiciosException(ex);
        }
    }

    /**
     * Permite obtener una referencia al unico objeto LocalizadorDeServicios.
     *
     * @return la unica instancia de LocalizadorDeServicios (por JVM)
     */
    static public LocalizadorServicios getInstance() {
        return me;
    }

    /**
     * Obtiene una referencia al home local. Los clientes deben usar un cast para
     * obtener la referencia al Home que requieren.
     *
     * @param  jndiHomeName el nombre JNDI con el que esta registrado el Home Local
     *         del EJB.
     * @throws LocalizadorServiciosException si ocurre alguna excepcion al hacer
     *         el lookup del Home Local.
     * @return el Home local de EJB que corresponde a jndiHomeName
     */
    public EJBLocalHome getLocalHome(String jndiHomeName) throws
            LocalizadorServiciosException {
        EJBLocalHome home = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                home = (EJBLocalHome) cache.get(jndiHomeName);
            } else {
                home = (EJBLocalHome) ic.lookup(jndiHomeName);
                cache.put(jndiHomeName, home);
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
        return home;
    }

    /**
     * Obtiene una referencia al home remoto. Los clientes deben usar un cast para
     * obtener la referencia al Home que requieren.
     *
     * @param  jndiHomeName el nombre JNDI con el que esta registrado el Home Remoto
     *         del EJB.
     * @param  className el objeto class del Home Remoto, necesario para aplicar
     *         el metodo narrow de javax.rmi.PortableRemoteObject.
     * @throws LocalizadorServiciosException si ocurre alguna excepcion al hacer
     *         el lookup del Home Remoto.
     * @return el Home Remoto de EJB que corresponde a jndiHomeName
     */
    public EJBHome getRemoteHome(String jndiHomeName, Class className) throws
            LocalizadorServiciosException {
        EJBHome home = null;
        try {
            if (cache.containsKey(jndiHomeName)) {
                home = (EJBHome) cache.get(jndiHomeName);
            } else {
                Object objref = ic.lookup(jndiHomeName);
                Object obj = PortableRemoteObject.narrow(objref, className);
                home = (EJBHome) obj;
                cache.put(jndiHomeName, home);
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
        return home;
    }

    /*
     ----------------

               /
              |     3
              |
               \

     ---------------------



       declarar las funciones para encontrar la interfaz local correspondiente a cada EJB
     por ej:


     public ConvenioLocal getConvenioLocal() throws
             LocalizadorServiciosException {
         try {
             if (convenioLocal != null) {
                 return convenioLocal;
             } else {
                 Object objref = ic.lookup(props.getString("CONVENIO"));
                 ConvenioLocalHome home = (ConvenioLocalHome) objref;
                 convenioLocal = home.create();
                 return convenioLocal;
             }
         } catch (NamingException ne) {
             throw new LocalizadorServiciosException(ne);
         } catch (Exception e) {
             throw new LocalizadorServiciosException(e);
         }
     }


     */

    public TemaForoLocal getTemaForoLocal() throws
            LocalizadorServiciosException {
        try {
            if (temaForoLocal != null) {
                return temaForoLocal;
            } else {
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/foro!proyecto_uoct.foro.model.TemaForoLocalHome");
                //Object objref = ic.lookup(props.getString("TEMAFORO"));
                TemaForoLocalHome home = (TemaForoLocalHome) objref;
                temaForoLocal = home.create();
                return temaForoLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }

    public ForosLocal getForosLocal() throws
            LocalizadorServiciosException {
        try {
            if (forosLocal != null) {
                return forosLocal;
            } else {
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Foro!proyecto_uoct.foro.model.ForosLocalHome");
                //Object objref = ic.lookup(props.getString("FOROS"));
                ForosLocalHome home = (ForosLocalHome) objref;
                forosLocal = home.create();
                return forosLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    
    public ReportajeLocal getReportajeLocal() throws
            LocalizadorServiciosException {
        try {
            if (reportajeLocal != null) {
                return reportajeLocal;
            } else {
                //Object objref = ic.lookup(props.getString("REPORTAJE"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Reportaje!proyecto_uoct.infoyrep.model.ReportajeLocalHome");
                ReportajeLocalHome home = (ReportajeLocalHome) objref;
                reportajeLocal = home.create();
                return reportajeLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }
//@EJB(beanInterface=proyecto_uoct.usuario.model.UsuarioLocal.class, beanName="UsuarioBean",name="Usuario" )
    public UsuarioLocal getUsuarioLocal() throws LocalizadorServiciosException {
    
        try {
            if (usuarioLocal != null) {
                return usuarioLocal;
            } else {
                //Object objref = ic.lookup(props.getString("USUARIO"));
                Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Usuario!proyecto_uoct.usuario.model.UsuarioLocalHome");
            	UsuarioLocalHome home = (UsuarioLocalHome) objref;
                usuarioLocal = home.create();
                return usuarioLocal;
            	
            	
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public ClienteLocal getClienteLocal() throws
            LocalizadorServiciosException {
        try {
            if (clienteLocal != null) {
                return clienteLocal;
            } else {
                //Object objref = ic.lookup(props.getString("CLIENTE"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Cliente!proyecto_uoct.documentacion.model.ClienteLocalHome");
                ClienteLocalHome home = (ClienteLocalHome) objref;
                clienteLocal = home.create();
                return clienteLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public DocumentoLocal getDocumentoLocal() throws
            LocalizadorServiciosException {
        try {
            if (documentoLocal != null) {
                return documentoLocal;
            } else {
               // Object objref = ic.lookup(props.getString("DOCUMENTO"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Documento!proyecto_uoct.documentacion.model.DocumentoLocalHome");
                DocumentoLocalHome home = (DocumentoLocalHome) objref;
                documentoLocal = home.create();
                return documentoLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }

    public EIVLocal getEIVLocal() throws
            LocalizadorServiciosException {
        try {
            if (eivLocal != null) {
                return eivLocal;
            } else {
                //Object objref = ic.lookup(props.getString("EIV"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/EIV!proyecto_uoct.EIV.model.EIVLocalHome");
                EIVLocalHome home = (EIVLocalHome) objref;
                eivLocal = home.create();
                return eivLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public ProyectoLocal getProyectoLocal() throws
            LocalizadorServiciosException {
        try {
            if (proyectoLocal != null) {
                return proyectoLocal;
            } else {
                //Object objref = ic.lookup(props.getString("PROYECTO"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Proyecto!proyecto_uoct.proyecto.model.ProyectoLocalHome");
                ProyectoLocalHome home = (ProyectoLocalHome) objref;
                proyectoLocal = home.create();
                return proyectoLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public RecursosLocal getRecursosLocal() throws
            LocalizadorServiciosException {
        try {
            if (recursosLocal != null) {
                return recursosLocal;
            } else {
                //Object objref = ic.lookup(props.getString("RECURSOS"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Recursos!proyecto_uoct.reservas.model.RecursosLocalHome");
                RecursosLocalHome home = (RecursosLocalHome) objref;
                recursosLocal = home.create();
                return recursosLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public InfoInstitLocal getInfoInstitLocal() throws
            LocalizadorServiciosException {
        try {
            if (infoInstitLocal != null) {
                return infoInstitLocal;
            } else {
            	//Object objref = ic.lookup(props.getString("INFOINSTIT"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/InfoInstit!proyecto_uoct.infoyrep.model.InfoInstitLocalHome");
                InfoInstitLocalHome home = (InfoInstitLocalHome) objref;
                infoInstitLocal = home.create();
                return infoInstitLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public PerfilLocal getPerfilLocal() throws
            LocalizadorServiciosException {
        try {
            if (perfilLocal != null) {
                return perfilLocal;
            } else {
                //Object objref = ic.lookup(props.getString("PERFIL"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Perfil!proyecto_uoct.usuario.model.PerfilLocalHome");
                PerfilLocalHome home = (PerfilLocalHome) objref;
                perfilLocal = home.create();
                return perfilLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }



    public InventarioLocal getInventarioLocal() throws
            LocalizadorServiciosException {
        try {
            if (inventarioLocal != null) {
                return inventarioLocal;
            } else {
                //Object objref = ic.lookup(props.getString("INVENTARIO"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Inventario!proyecto_uoct.inventario.model.InventarioLocalHome");
                InventarioLocalHome home = (InventarioLocalHome) objref;
                inventarioLocal = home.create();
                return inventarioLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }




    public VentasLocal getVentasLocal() throws
            LocalizadorServiciosException {
        try {
            if (ventasLocal != null) {
                return ventasLocal;
            } else {
                //Object objref = ic.lookup(props.getString("VENTAS"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Ventas!proyecto_uoct.ventas.model.VentasLocalHome");
                VentasLocalHome home = (VentasLocalHome) objref;
                ventasLocal = home.create();
                return ventasLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }



    public MensajeLocal getMensajeLocal() throws
            LocalizadorServiciosException {
        try {
            if (mensajeLocal != null) {
                return mensajeLocal;
            } else {
                //Object objref = ic.lookup(props.getString("MENSAJE"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Mensaje!proyecto_uoct.mensaje.model.MensajeLocalHome");
                MensajeLocalHome home = (MensajeLocalHome) objref;
                mensajeLocal = home.create();
                return mensajeLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }



    public FallasLocal getFallasLocal() throws
            LocalizadorServiciosException {
        try {
            if (fallasLocal != null) {
                return fallasLocal;
            } else {
                //Object objref = ic.lookup(props.getString("FALLAS"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/Fallas!proyecto_uoct.fallas.model.FallasLocalHome");
                FallasLocalHome home = (FallasLocalHome) objref;
                fallasLocal = home.create();
                return fallasLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }

    public EmpMantLocal getEmpMantLocal() throws
            LocalizadorServiciosException {
        try {
            if (empmantLocal  != null) {
                return empmantLocal;
            } else {
                //Object objref = ic.lookup(props.getString("EMPMANT"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/EmpMant!proyecto_uoct.EmpMant.model.EmpMantLocalHome");
                EmpMantLocalHome home = (EmpMantLocalHome) objref;
                empmantLocal = home.create();
                return empmantLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }

    public MantenedorSistemasLocal getMantenedorSistemasLocal() throws
            LocalizadorServiciosException {
        try {
            if (sistemaLocal  != null) {
                return sistemaLocal;
            } else {
                //Object objref = ic.lookup(props.getString("MANTENEDORSISTEMAS"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/MantenedorSistemas!proyecto_uoct.mantenedorSistemas.model.MantenedorSistemasLocalHome");
                MantenedorSistemasLocalHome home = (MantenedorSistemasLocalHome) objref;
                sistemaLocal = home.create();
                return sistemaLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }


    public MantenedorSubsistemasLocal getMantenedorSubsistemasLocal() throws
            LocalizadorServiciosException {
        try {
            if (subsistemaLocal  != null) {
                return subsistemaLocal;
            } else {
                //Object objref = ic.lookup(props.getString("MANTENEDORSUBSISTEMA"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/MantenedorSubsistemas!proyecto_uoct.mantenedorSubsistemas.model.MantenedorSubsistemasLocalHome");
                MantenedorSubsistemasLocalHome home = (MantenedorSubsistemasLocalHome) objref;
                subsistemaLocal = home.create();
                return subsistemaLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }




    public MantenedorDispositivosLocal getMantenedorDispositivosLocal() throws
            LocalizadorServiciosException {
        try {
            if (dispositivoLocal  != null) {
                return dispositivoLocal;
            } else {
                //Object objref = ic.lookup(props.getString("MANTENEDORDISPOSITIVO"));
            	Object objref = ic.lookup("java:global/EARproyecto_uoct/EJBproyecto_uoct/MantenedorDispositivos!proyecto_uoct.mantenedorDispositivos.model.MantenedorDispositivosLocalHome");
                MantenedorDispositivosLocalHome home = (MantenedorDispositivosLocalHome) objref;
                dispositivoLocal = home.create();
                return dispositivoLocal;
            }
        } catch (NamingException ne) {
            throw new LocalizadorServiciosException(ne);
        } catch (Exception e) {
            throw new LocalizadorServiciosException(e);
        }
    }



/*
    *
    *  /
    * |   4         ----- MODIFICAR EL ARCHIVO "proyecto_uoct.common.NombresJNDI"
    *  \
    */

}
