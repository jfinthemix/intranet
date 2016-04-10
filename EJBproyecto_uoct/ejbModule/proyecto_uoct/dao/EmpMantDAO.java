package proyecto_uoct.dao;

import java.sql.*;
import java.text.*;
import java.util.*;
import proyecto_uoct.common.*;
import proyecto_uoct.EmpMant.VO.*;

public class EmpMantDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    public EmpMantDAO() {
    }
    private static EmpMantDAO dao = null;
    public static EmpMantDAO getInstance() {
        if (dao == null) {
            dao = new EmpMantDAO();
        }
        return dao;
    }
    public int ingresarEmpMant(
            String  n_nombre,
            String n_telefono,
            String n_direccion,
            Integer n_vigente,
            Integer n_id_tipo_dispositivo,
            String n_mail_terreno,
            Integer n_id_tipo_dispositivo2,
            String n_mail_sala) throws Exception{
        int valor_id_empresa_mantenedora = 0;
        Integer uno                      = 1;
        Integer dos                      = 2;
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps   = null;
            PreparedStatement ps22 = null;
            PreparedStatement ps3  = null;
            PreparedStatement ps4  = null;
            ps = conn.prepareCall(
                    " insert into empresa_mantenedora (id_empresa_mantenedora, nombre, telefono, direccion, vigente) " +
                    " values (s_empresa_mantenedora.nextval, ?, ?, ?, ?)"); //id_falla.nextval
            ps.setString(1, n_nombre);
            ps.setString(2, n_telefono);
            ps.setString(3, n_direccion);
            ps.setInt(4, n_vigente);
            ps.executeUpdate();
            ps22 = conn.prepareCall("select s_empresa_mantenedora.currval from dual");
            ResultSet rs22 = ps22.executeQuery();
            if (rs22.next()) {
                valor_id_empresa_mantenedora = rs22.getInt(1);
            }
            ps3 = conn.prepareCall(
                    " insert into emp_mant_tipo_dispo (id_empresa_mantenedora, id_tipo_dispositivo, mail) " +
                    " values (?, ?, ?)");
            ps3.setInt(1, valor_id_empresa_mantenedora);
            ps3.setInt(2, n_id_tipo_dispositivo);
            ps3.setString(3, n_mail_terreno);
            ps3.executeUpdate();
            ps4 = conn.prepareCall(
                    " insert into emp_mant_tipo_dispo (id_empresa_mantenedora, id_tipo_dispositivo, mail) " +
                    " values (?, ?, ?)");
            ps4.setInt(1, valor_id_empresa_mantenedora);
            ps4.setInt(2, n_id_tipo_dispositivo2);
            ps4.setString(3, n_mail_sala);
            ps4.executeUpdate();
            conn.close();
        } catch (Exception ex3) {
            ex3.printStackTrace();
            throw ex3;
        }
        return valor_id_empresa_mantenedora;
    }
    public int modificarEmpMant(Integer id_empresa,
                                 String nombre,
                                 String direccion,
                                 String telefono,
                                 Integer vigente,
                                 String mail_1,
                                 String mail_2) throws Exception{
        try {
            Connection conn           = ConnectionPool.getInstance().getConnection();
            PreparedStatement update1 = null;
            PreparedStatement update2 = null;
            PreparedStatement update3 = null;
            PreparedStatement update4 = null;
            int id   = id_empresa;
            int vi   = vigente;
            //en empresa_mantenedora
            update1 = conn.prepareCall(
                    " update empresa_mantenedora " +
                    " set nombre=?, direccion=?, telefono=?, vigente=? " +
                    " where id_empresa_mantenedora=? ");
            update1.setString(1, nombre);
            update1.setString(2, direccion);
            update1.setString(3, telefono);
            update1.setInt(4, vi);
            update1.setInt(5, id);
            update1.executeUpdate();
            //emp_mant_tipo_dispo
            update2 = conn.prepareCall(
                    " update emp_mant_tipo_dispo " +
                    " set mail=? " +
                    " where id_empresa_mantenedora=? and id_tipo_dispositivo=1");
            update2.setString(1, mail_1);
            update2.setInt(2, id);
            update2.executeUpdate();

            update3 = conn.prepareCall(
                    " update emp_mant_tipo_dispo " +
                    " set mail=? " +
                    " where id_empresa_mantenedora=? and id_tipo_dispositivo=2");
            update3.setString(1, mail_2);
            update3.setInt(2, id);
            update3.executeUpdate();

            conn.close();
        }  catch(Exception e){
            e.printStackTrace();
        }
        return id_empresa;
    }
    public List buscarEmpMant(Integer vigente) throws Exception{
        List listaEmpresa = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
                    "select em. id_empresa_mantenedora, em.nombre, em.telefono, em.direccion, em.vigente, " +
                    " td.descripcion as des_1, emtd.mail as mail_1, " +
                    " td2.descripcion as des_2, emtd2.mail as mail_2 " +
                    " from empresa_mantenedora em, emp_mant_tipo_dispo emtd,  emp_mant_tipo_dispo emtd2, " +
                    " tipo_dispositivo td, tipo_dispositivo td2 " +
                    " where em.id_empresa_mantenedora  = emtd.id_empresa_mantenedora " +
                    " and emtd.id_tipo_dispositivo     = td.id_tipo_dispositivo " +
                    " and emtd.id_tipo_dispositivo     = 1 " +
                    " and td.id_tipo_dispositivo       = 1 " +
                    " and emtd2.id_tipo_dispositivo    = 2 " +
                    " and td2.id_tipo_dispositivo      = 2 " +
                    " and emtd.id_empresa_mantenedora  = emtd2.id_empresa_mantenedora ";
            if (vigente != 2) {
                select_1 += " and vigente         =" + vigente;
            }
            select_1 += " order by 6,1";
            //System.out.print(select_1);
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs         = ps.executeQuery();
            EmpMantVO empmant    = null;
            int ban=1;
            while (rs.next()) {
                empmant = new EmpMantVO();
                empmant.set_id_empresa(new Integer(rs.getInt("id_empresa_mantenedora")));
                empmant.set_nombre(rs.getString("nombre"));
                empmant.set_telefono(rs.getString("telefono"));
                empmant.set_direccion(rs.getString("direccion"));
                empmant.set_vigente(new Integer(rs.getInt("vigente")));
                empmant.set_tipo_dispositivo(rs.getString("des_1"));
                empmant.set_mail_1(rs.getString("mail_1"));
                empmant.set_tipo_dispositivo2(rs.getString("des_2"));
                empmant.set_mail_2(rs.getString("mail_2"));
                listaEmpresa.add(empmant);
                ban=ban+1;
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpresa;
    }
    public List verEmpMant(Integer id_emp) throws Exception{
        List listaEmpresa = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
                    "select em. id_empresa_mantenedora, em.nombre, em.telefono, em.direccion, em.vigente, " +
                    " td.descripcion as des_1, emtd.mail as mail_1, " +
                    " td2.descripcion as des_2, emtd2.mail as mail_2 " +
                    " from empresa_mantenedora em, emp_mant_tipo_dispo emtd,  emp_mant_tipo_dispo emtd2, " +
                    " tipo_dispositivo td, tipo_dispositivo td2 " +
                    " where em.id_empresa_mantenedora  = emtd.id_empresa_mantenedora " +
                    " and emtd.id_tipo_dispositivo     = td.id_tipo_dispositivo " +
                    " and emtd.id_tipo_dispositivo     = 1 " +
                    " and td.id_tipo_dispositivo       = 1 " +
                    " and emtd2.id_tipo_dispositivo    = 2 " +
                    " and td2.id_tipo_dispositivo      = 2 " +
                    " and emtd.id_empresa_mantenedora  = emtd2.id_empresa_mantenedora " +
                    " and emtd.id_empresa_mantenedora  =" + id_emp +
                    " order by 6";
            //System.out.print(select_1);
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs         = ps.executeQuery();
            EmpMantVO empmant    = null;
            while (rs.next()) {
                empmant = new EmpMantVO();
                empmant.set_id_empresa(new Integer(rs.getInt("id_empresa_mantenedora")));
                empmant.set_nombre(rs.getString("nombre"));
                empmant.set_telefono(rs.getString("telefono"));
                empmant.set_direccion(rs.getString("direccion"));
                empmant.set_vigente(new Integer(rs.getInt("vigente")));
                empmant.set_tipo_dispositivo(rs.getString("des_1"));
                empmant.set_mail_1(rs.getString("mail_1"));
                empmant.set_tipo_dispositivo(rs.getString("des_2"));
                empmant.set_mail_2(rs.getString("mail_2"));
                listaEmpresa.add(empmant);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpresa;
    }

    public List listaDispositivosD(Integer id_empresa) throws Exception{
        List listaEmpresa = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
                    " select id_dispositivo, nombre, descripcion, id_tipo_dispositivo " +
                    " from dispositivo " +
                    " where id_empresa_mantenedora=0" +
                    " order by id_dispositivo";
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs         = ps.executeQuery();
            EmpMantVO empmant    = null;
            while (rs.next()) {
                empmant = new EmpMantVO();
                empmant.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                empmant.set_nombre_dispositivo(rs.getString("nombre"));
                empmant.set_descripcion(rs.getString("descripcion"));
                empmant.set_id_tipo_dispositivo(new Integer(rs.getInt("id_tipo_dispositivo")));
                listaEmpresa.add(empmant);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpresa;
    }
    public List listaDispositivosM(Integer id_empresa) throws Exception{
        List listaEmpresa = new LinkedList();
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            String select_1 =
                    " select id_dispositivo, nombre, descripcion, id_tipo_dispositivo " +
                    " from dispositivo " +
                    " where id_empresa_mantenedora=" + id_empresa +
                    " order by id_dispositivo";
            PreparedStatement ps = conn.prepareCall(select_1);
            ResultSet rs         = ps.executeQuery();
            EmpMantVO empmant    = null;
            while (rs.next()) {
                empmant = new EmpMantVO();
                empmant.set_id_dispositivo(new Integer(rs.getInt("id_dispositivo")));
                empmant.set_nombre_dispositivo(rs.getString("nombre"));
                empmant.set_descripcion(rs.getString("descripcion"));
                empmant.set_id_tipo_dispositivo(new Integer(rs.getInt("id_tipo_dispositivo")));
                listaEmpresa.add(empmant);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEmpresa;
    }
    public boolean modificarDispositivo(String[] listaA, String[] listaB, Integer id_emp) throws Exception{
        try {
            Connection conn = ConnectionPool.getInstance().getConnection();
            EmpMantVO empmant    = null;
            if(listaA!= null){
                int tamListaA = listaA.length;
                int inicioA = 0;
                if (tamListaA > 0) {
                    while (inicioA < tamListaA) {
                        String update =
                                " update dispositivo " +
                                " set id_empresa_mantenedora = 0" +
                                " where id_dispositivo =" + listaA[inicioA];
                        PreparedStatement ps = conn.prepareCall(update);
                        ps.executeUpdate();
                        inicioA++;
                    }
                }
            }
            if(listaB!= null){
                int tamListaB = listaB.length;
                int inicioB = 0;
                if (tamListaB > 0) {
                    while (inicioB < tamListaB) {
                        String update =
                                " update dispositivo " +
                                " set id_empresa_mantenedora =" + id_emp +
                                " where id_dispositivo =" + listaB[inicioB];
                        PreparedStatement ps = conn.prepareCall(update);
                        ps.executeUpdate();
                        inicioB++;
                    }
                }
            }


            conn.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
