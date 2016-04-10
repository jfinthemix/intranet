package proyecto_uoct.EIV.controller;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SendEmailServlet extends HttpServlet  {
    public SendEmailServlet(){
    }
    public void init(ServletConfig config) throws ServletException {
        EmailTrigger et=new EmailTrigger();
        try {
            et.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
