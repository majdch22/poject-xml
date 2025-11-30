package com.demo.servlets;

import com.demo.Employe_ins;
import com.demo.Employes_ins;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.bind.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            File file = new File(XML_PATH);
            List<Employe_ins> demandesEnAttente = new ArrayList<>();

            if (file.exists()) {
                JAXBContext ctx = JAXBContext.newInstance(Employes_ins.class);
                Unmarshaller u = ctx.createUnmarshaller();
                Employes_ins employes = (Employes_ins) u.unmarshal(file);
                
                // Filtrer seulement les employés avec status "en_attente"
                for (Employe_ins e : employes.getEmploye()) {
                    if ("en_attente".equals(e.getStatus())) {
                        demandesEnAttente.add(e);
                    }
                }
            }

            req.setAttribute("demandesInscription", demandesEnAttente);
            req.getRequestDispatcher("notifications.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Erreur chargement notifications", e);
        }
    }
}
