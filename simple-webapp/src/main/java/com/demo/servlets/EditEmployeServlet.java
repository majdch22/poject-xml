package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Employes_ins;
import com.demo.Employe_ins;

@WebServlet("/editEmploye")
public class EditEmployeServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
        String cin = req.getParameter("cin");
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String tel = req.getParameter("tel");
            String competence = req.getParameter("competence");
            boolean disponibilite = req.getParameter("disponibilite") != null;

            JAXBContext context = JAXBContext.newInstance(Employes_ins.class);
            Unmarshaller u = context.createUnmarshaller();
            Employes_ins data = (Employes_ins) u.unmarshal(new File(XML_PATH));

            for (Employe_ins e : data.getEmploye()) {
                if (e.getCin().equals(cin)) {
                    e.setNom(nom);
                    e.setPrenom(prenom);
                    e.setTel(tel);
                    e.setCompetence(competence);
                    e.setDisponibilite(disponibilite);
                }
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, new File(XML_PATH));

            resp.sendRedirect("employes");

        } catch (Exception e) {
            throw new ServletException("Erreur modification employé", e);
        }
    }
}