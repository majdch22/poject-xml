package com.demo.servlets;

import com.demo.Admin;
import com.demo.Employe_ins;
import com.demo.Employes_ins;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String ADMIN_XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\admin.xml";
    private static final String EMPLOYE_INS_XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // 1. Vérifier si c'est l'admin
            if (checkAdmin(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("userType", "admin");
                session.setAttribute("username", username);
                request.getRequestDispatcher("interface2.jsp").forward(request, response);
                return;
            }

            // 2. Vérifier si c'est un employé accepté
            Employe_ins employe = checkEmploye(username, password);
            if (employe != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userType", "employe");
                session.setAttribute("username", employe.getPrenom() + " " + employe.getNom());
                session.setAttribute("employeCin", employe.getCin());
                request.getRequestDispatcher("interfaceEmploye.jsp").forward(request, response);
                return;
            }

            // 3. Si aucune correspondance
            request.setAttribute("error", "Nom d'utilisateur ou mot de passe incorrect.");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur système : " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private boolean checkAdmin(String username, String password) {
        try {
            File file = new File(ADMIN_XML_PATH);
            if (!file.exists()) {
                return false;
            }

            JAXBContext context = JAXBContext.newInstance(Admin.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Admin admin = (Admin) unmarshaller.unmarshal(file);

            return admin.getUsername().equals(username) && admin.getPassword().equals(password);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Employe_ins checkEmploye(String username, String password) {
        try {
            File file = new File(EMPLOYE_INS_XML_PATH);
            if (!file.exists()) {
                return null;
            }

            JAXBContext context = JAXBContext.newInstance(Employes_ins.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employes_ins employes = (Employes_ins) unmarshaller.unmarshal(file);

            // Chercher un employé avec le CIN comme username, le bon password et status
            // "accepte"
            for (Employe_ins employe : employes.getEmploye()) {
                if (employe.getCin().equals(username) &&
                        employe.getPassword().equals(password) &&
                        "accepte".equals(employe.getStatus())) {
                    return employe;
                }
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}