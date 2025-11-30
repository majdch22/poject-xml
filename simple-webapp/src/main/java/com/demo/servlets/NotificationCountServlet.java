package com.demo.servlets;

import com.demo.Employe_ins;
import com.demo.Employes_ins;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.bind.*;
import java.io.IOException;
import java.io.File; 

@WebServlet("/notificationCount")
public class NotificationCountServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            File file = new File(XML_PATH);
            int count = 0;

            if (file.exists()) {
                JAXBContext ctx = JAXBContext.newInstance(Employes_ins.class);
                Unmarshaller u = ctx.createUnmarshaller();
                Employes_ins employes = (Employes_ins) u.unmarshal(file);
                
                // Compter seulement les employés avec status "en_attente"
                for (Employe_ins e : employes.getEmploye()) {
                    if ("en_attente".equals(e.getStatus())) {
                        count++;
                    }
                }
            }

            resp.setContentType("text/plain");
            resp.getWriter().write(String.valueOf(count));

        } catch (Exception e) {
            resp.getWriter().write("0");
        }
    }
}