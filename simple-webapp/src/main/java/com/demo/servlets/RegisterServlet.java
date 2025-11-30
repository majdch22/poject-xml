package com.demo.servlets;

import com.demo.Employe_ins;
import com.demo.Employes_ins;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.bind.*;

import java.io.File;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String nom = req.getParameter("nom");
            String prenom = req.getParameter("prenom");
            String cin = req.getParameter("cin");
            String tel = req.getParameter("tel");
            String competence = req.getParameter("competence");
            String password = req.getParameter("password");
            String confirmPassword = req.getParameter("confirm_password");

            // Vérifier que les mots de passe correspondent
            if (!password.equals(confirmPassword)) {
                req.setAttribute("error", "Les mots de passe ne correspondent pas !");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
                return;
            }

            JAXBContext ctx = JAXBContext.newInstance(Employes_ins.class);

            Employes_ins employes;
            File file = new File(XML_PATH);

            if (file.exists()) {
                Unmarshaller u = ctx.createUnmarshaller();
                employes = (Employes_ins) u.unmarshal(file);
            } else {
                employes = new Employes_ins();
            }

            // Vérifier si le CIN existe déjà (quel que soit le status)
            for (Employe_ins e : employes.getEmploye()) {
                if (e.getCin().equals(cin)) {
                    req.setAttribute("error", "Ce CIN est déjà inscrit !");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                    return;
                }
            }

            // Ajouter un employé avec status "en_attente"
            employes.addEmploye(new Employe_ins(nom,prenom,cin,tel,password,"en_attente",competence,true));

            // Sauvegarde dans XML
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(employes, file);

            req.setAttribute("success", "Demande d'inscription envoyée avec succès ! En attente de validation par l'admin.");
            req.getRequestDispatcher("register.jsp").forward(req, resp);

        } catch (Exception e) {
            throw new ServletException("Erreur inscription", e);
        }
    }
}