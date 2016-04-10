package proyecto_uoct.foro.VO;

import java.util.Date;

public class PostUsuVO {
    public PostUsuVO() {
    }

    private Integer idPost = null;
    private String post = null;
    private String usu = null;
    private Date fechaPost = null;


    public void setIdPost(Integer idP) {
        this.idPost = idP;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setFechaPost(Date fecha) {
        this.fechaPost = fecha;
    }


    public void setUsu(String us) {
        this.usu = us;
    }


    public Integer getIdPost() {
        return this.idPost;
    }


    public String getUsu() {
        return this.usu;
    }

    public String getPost() {
        return this.post;
    }

    public Date getFechaPost() {
        return this.fechaPost;
    }


}
