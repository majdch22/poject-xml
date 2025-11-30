package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Employes_ins;

@WebServlet("/employes")
public class ListEmployesServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            File file = new File(XML_PATH);

            JAXBContext context = JAXBContext.newInstance(Employes_ins.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Employes_ins data = (Employes_ins) unmarshaller.unmarshal(file);

            request.setAttribute("employes_ins", data.getEmploye());
            request.getRequestDispatcher("gererEmployes.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erreur lecture XML employés", e);
        }
    }
}