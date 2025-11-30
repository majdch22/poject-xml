package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*;  
import com.demo.Vehicules;

@WebServlet("/vehicules")
public class ListVehiculesServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicule.xml";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            File file = new File(XML_PATH);

            JAXBContext context = JAXBContext.newInstance(Vehicules.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Vehicules data = (Vehicules) unmarshaller.unmarshal(file);

            request.setAttribute("vehicules", data.getVehicules());
            request.getRequestDispatcher("gererVehicules.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erreur lecture XML", e);
        }
    }
}
