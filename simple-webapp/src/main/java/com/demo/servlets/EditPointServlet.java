package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Points;
import com.demo.Point;

@WebServlet("/editPoint")
public class EditPointServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String localisation = req.getParameter("localisation");
            String typeElectrique = req.getParameter("typeElectrique");
            String etat = req.getParameter("etat");
            String contexte = req.getParameter("contexte");

            JAXBContext context = JAXBContext.newInstance(Points.class);
            Unmarshaller u = context.createUnmarshaller();
            Points data = (Points) u.unmarshal(new File(XML_PATH));

            for (Point p : data.getPoints()) {
                if (p.getId() == id) {
                    p.setLocalisation(localisation);
                    p.setTypeElectrique(typeElectrique);
                    p.setEtat(etat);
                    p.setContexte(contexte);
                }
            }

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, new File(XML_PATH));

            resp.sendRedirect("points");

        } catch (Exception e) {
            throw new ServletException("Erreur modification point", e);
        }
    }
}
