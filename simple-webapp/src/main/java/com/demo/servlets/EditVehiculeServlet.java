package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Vehicules;

import com.demo.Vehicule;
@WebServlet("/editVehicule")
public class EditVehiculeServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicule.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String matricule = req.getParameter("matricule");
            int capacite = Integer.parseInt(req.getParameter("capacite"));
            boolean disponible = req.getParameter("disponible") != null;

            JAXBContext context = JAXBContext.newInstance(Vehicules.class);
            Unmarshaller u = context.createUnmarshaller();
            Vehicules data = (Vehicules) u.unmarshal(new File(XML_PATH));

            for (Vehicule v : data.getVehicules()) {
                if (v.getId() == id) {
                    v.setMatricule(matricule);
                    v.setCapacite(capacite);
                    v.setDisponible(disponible);
                }
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, new File(XML_PATH));

            resp.sendRedirect("vehicules");

        } catch (Exception e) {
            throw new ServletException("Erreur modification", e);
        }
    }
}
