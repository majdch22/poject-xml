package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Points; 

@WebServlet("/points")
public class ListPointsServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            File file = new File(XML_PATH);

            JAXBContext context = JAXBContext.newInstance(Points.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Points data = (Points) unmarshaller.unmarshal(file);

            request.setAttribute("points", data.getPoints());
            request.getRequestDispatcher("gererPoints.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erreur lecture XML points", e);
        }
    }
}