package com.demo.servlets;
import com.demo.Vehicules;
import com.demo.Vehicule;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*;

@WebServlet("/addVehicule")
public class AddVehiculeServlet extends HttpServlet {

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
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Vehicules data = (Vehicules) unmarshaller.unmarshal(new File(XML_PATH));

            data.addVehicule(new Vehicule(id, matricule, capacite, disponible));

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(data, new File(XML_PATH));

            resp.sendRedirect("vehicules");

        } catch (Exception e) {
            throw new ServletException("Erreur ajout", e);
        }
    }
}
