package com.demo.servlets;

import com.demo.Employes_ins;
import com.demo.Employe_ins;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*;

@WebServlet("/addEmploye")
public class AddEmployeServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

        @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Read form fields
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String cin = req.getParameter("cin");
            String tel = req.getParameter("tel");
            String password = req.getParameter("password");
            String competence = req.getParameter("competence");
            boolean disponibilite = req.getParameter("disponibilite") != null;

            // Load XML
            JAXBContext context = JAXBContext.newInstance(Employes_ins.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            File xmlFile = new File(XML_PATH);
            Employes_ins employes;

            if (xmlFile.exists()) {
                employes = (Employes_ins) unmarshaller.unmarshal(xmlFile);
            } else {
                employes = new Employes_ins(); // empty list
            }

            // Create new employee
            Employe_ins emp = new Employe_ins(nom,prenom,cin,tel,password,"accepted",competence,disponibilite);

            // Add employee
            employes.getEmploye().add(emp);

            // Save XML
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(employes, xmlFile);

            // Redirect
            resp.sendRedirect("employes");

        } catch (Exception e) {
            throw new ServletException("Erreur ajout employé", e);
        }
    }
}
