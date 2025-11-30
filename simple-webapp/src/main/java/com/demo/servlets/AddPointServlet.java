package com.demo.servlets;

import com.demo.Points;
import com.demo.Point;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*;

@WebServlet("/addPoint")
public class AddPointServlet extends HttpServlet {

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
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Points data = (Points) unmarshaller.unmarshal(new File(XML_PATH));

            data.addPoint(new Point(id, localisation, typeElectrique, etat, contexte));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(XML_PATH));

            resp.sendRedirect("points");

        } catch (Exception e) {
            throw new ServletException("Erreur ajout point", e);
        }
    }
}