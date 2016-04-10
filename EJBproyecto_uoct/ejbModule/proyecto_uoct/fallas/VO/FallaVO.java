package proyecto_uoct.fallas.VO;
import java.sql.*;
public class FallaVO {
    private Integer id_falla           = null;
    private Integer id_usu_inicia      = null;
    private String  usu_inicia         = null;
    private Integer id_usu_cierra      = null;
    private String  usu_cierra         = null;
    private String  titulo             = null;

    private Date    fecha_ingreso      = null;
    private String  fecha_ingreso2     = null;

    private Date    fecha_cierre       = null;
    private String  fecha_cierre2      = null;

    private Date    fecha_plazo        = null;
    private String  fecha_plazo2       = null;
    private Integer estado             = null;

    private String  cerrar             = null;

    private Integer id_detalle_falla   = null;
    private Integer id_usu_actualiza   = null;
    private String  username           = null;
    private String  comentario         = null;
    private Date    fecha_actualiza    = null;
    private String  fecha_actualiza2   = null;
    private String  problema           = null;

    private Integer id_sistema         = null;
    private String  nombre_sistema     = null;

    private Integer id_subsistema      = null;
    private String  nombre_subsistema  = null;

    private Integer id_dispositivo     = null;
    private String  nombre_dispositivo = null;
    private String  nom_dispositivo    = null;
    private String mailDispositivo     = null;

    public FallaVO() {
    }
    /**********************************/
    /*             Falla              */
    /**********************************/
    public void set_id_falla(Integer id_falla) {
        this.id_falla = id_falla;
    }
    public Integer get_id_falla() {
        return this.id_falla;
    }
    public void set_id_usu_inicia(Integer id_usu_inicia) {
        this.id_usu_inicia = id_usu_inicia;
        }
    public Integer get_id_usu_inicia() {
        return this.id_usu_inicia;
    }
    public void set_usu_inicia(String usu_inicia) {
           this.usu_inicia = usu_inicia;
           }
    public String get_usu_inicia() {
        return this.usu_inicia;
    }
    public void set_id_usu_cierra(Integer id_usu_cierra) {
        this.id_usu_cierra = id_usu_cierra;
        }
    public Integer get_id_usu_cierra() {
        return this.id_usu_cierra;
    }
    public void set_usu_cierra(String usu_cierra) {
           this.usu_cierra = usu_cierra;
           }
    public String get_usu_cierra() {
           return this.usu_cierra;
    }
    public void set_titulo(String titulo) {
           this.titulo = titulo;
       }
    public String get_titulo() {
           return this.titulo;
    }
    public void set_cerrar(String cerrar) {
        this.cerrar = cerrar;
    }
    public String get_cerrar() {
        return this.cerrar;
    }
    public void set_fecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public Date get_fecha_ingreso() {
        return this.fecha_ingreso;
    }
    public void set_fecha_ingreso2(String fecha_ingreso2) {
        this.fecha_ingreso2 = fecha_ingreso2;
    }
    public String get_fecha_ingreso2() {
        return this.fecha_ingreso2;
    }
    public void set_fecha_cierre(Date fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }
    public Date get_fecha_cierre() {
        return this.fecha_cierre;
    }
    public void set_fecha_cierre2(String fecha_cierre2) {
        this.fecha_cierre2 = fecha_cierre2;
    }
    public String get_fecha_cierre2() {
        return this.fecha_cierre2;
    }
    public void set_fecha_plazo(Date fecha_plazo) {
        this.fecha_plazo = fecha_plazo;
    }
    public Date get_fecha_plazo() {
        return this.fecha_plazo;
    }
    public void set_fecha_plazo2(String fecha_plazo2) {
        this.fecha_plazo2 = fecha_plazo2;
    }
    public String get_fecha_plazo2() {
        return this.fecha_plazo2;
    }
    public void set_estado(Integer estado) {
           this.estado = estado;
       }
    public Integer get_estado() {
           return this.estado;
    }
    /**********************************/
    /*        Detalle Falla           */
    /**********************************/
    public void set_id_detalle_falla(Integer id_detalle_falla) {
            this.id_detalle_falla = id_detalle_falla;
        }
    public Integer get_id_detalle_falla() {
            return this.id_detalle_falla;
    }
    public void set_username(String username) {
        this.username = username;
    }
    public String get_username() {
         return this.username;
    }
    public void set_id_usu_actualiza(Integer id_usu_actualiza) {
        this.id_usu_actualiza = id_usu_actualiza;
    }
    public Integer get_id_usu_actualiza() {
        return this.id_usu_actualiza;
    }
    public void set_comentario(String comentario) {
        this.comentario = comentario;
    }
    public String get_comentario() {
         return this.comentario;
    }
    public void set_fecha_actualiza(Date fecha_actualiza) {
        this.fecha_actualiza = fecha_actualiza;
    }
    public Date get_fecha_actualiza() {
        return this.fecha_actualiza;
    }
    public void set_fecha_actualiza2(String fecha_actualiza2) {
        this.fecha_actualiza2 = fecha_actualiza2;
    }
    public String get_fecha_actualiza2() {
        return this.fecha_actualiza2;
    }

    public void set_problema(String problema) {
        this.problema = problema;
    }
    public String get_problema() {
        return this.problema;
    }


    /**********************************/
    /*            Sistema             */
    /**********************************/
    public void set_id_sistema(Integer id_sistema) {
        this.id_sistema = id_sistema;
    }
    public Integer get_id_sistema() {
        return this.id_sistema;
    }
    public void set_nombre_sistema(String nombre_sistema) {
        this.nombre_sistema = nombre_sistema;
    }
    public String get_nombre_sistema() {
        return this.nombre_sistema;
    }
    /**********************************/
    /*         Subsistema             */
    /**********************************/
    public void set_id_subsistema(Integer id_subsistema) {
        this.id_subsistema = id_subsistema;
    }
    public Integer get_id_subsistema() {
        return this.id_subsistema;
    }
    public void set_nombre_subsistema(String nombre_subsistema) {
        this.nombre_subsistema = nombre_subsistema;
    }
    public String get_nombre_subsistema() {
        return this.nombre_subsistema;
    }
    /**********************************/
    /*         Dispositivo            */
    /**********************************/
    public void set_id_dispositivo(Integer id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
    public Integer get_id_dispositivo() {
        return this.id_dispositivo;
    }
    public void set_nom_dispositivo(String nom_dispositivo) {
        this.nom_dispositivo = nom_dispositivo;
    }
    public String get_nom_dispositivo() {
        return this.nom_dispositivo;
    }

    public void set_nombre_dispositivo(String nombre_dispositivo) {
        this.nombre_dispositivo = nombre_dispositivo;
    }
    public String get_nombre_dispositivo() {
        return this.nombre_dispositivo;
    }
    public void setMailDispositivo(String mailDispositivo) {
        this.mailDispositivo = mailDispositivo;
    }
    public String getMailDispositivo() {
        return this.mailDispositivo;
    }

    /*xml*/
    private String dispositivoXml;
    private String coord_xXml, coord_yXml, localXml, codigoXml, descripcionXml, horasXml;

    public void setDispositivoXml(String dispositivoXml) {
        this.dispositivoXml = dispositivoXml;
    }
    public String getDispositivoXml() {
        return dispositivoXml;
    }

    public void setCoord_xXml(String coord_xXml) {
        this.coord_xXml = coord_xXml;
    }
    public String getCoord_xXml() {
        return coord_xXml;
    }

    public void setCoord_yXml(String coord_yXml) {
        this.coord_yXml = coord_yXml;
    }
    public String getCoord_yXml() {
        return coord_yXml;
    }

    public void setLocalXml(String localXml) {
        this.localXml = localXml;
    }
    public String getLocalXml() {
        return localXml;
    }

    public void setCodigoXml(String codigoXml) {
        this.codigoXml = codigoXml;
    }
    public String getCodigoXml() {
        return codigoXml;
    }

    public void setDescripcionXml(String descripcionXml) {
        this.descripcionXml = descripcionXml;
    }
    public String getDescripcionXml() {
        return descripcionXml;
    }

    public void setHorasXml(String horasXml) {
        this.horasXml = horasXml;
    }
    public String getHorasXml() {
        return horasXml;
    }

/********************/

    /*  prueba  */
    /*
    private String titulo2, descripcion, enlace, fecha;
        public void setTitulo(String titulo2) {
                this.titulo2 = titulo2;
        }
        public String getTitulo() {
                return titulo2;
        }

        public void setFecha(String titulo) {
                this.fecha = titulo;
        }
        public String getFecha() {
                return fecha;
        }

        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }
        public String getDescripcion() {
                return descripcion;
        }

        public void setEnlace(String enlace) {
                this.enlace = enlace;
        }
        public String getEnlace() {
                return enlace;
        }
*/












}
