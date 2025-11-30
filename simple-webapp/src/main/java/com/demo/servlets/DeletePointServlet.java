package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Points;

@WebServlet("/deletePoint")
public class DeletePointServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(req.getParameter("id"));

            JAXBContext context = JAXBContext.newInstance(Points.class);
            Unmarshaller u = context.createUnmarshaller();
            Points data = (Points) u.unmarshal(new File(XML_PATH));

            data.getPoints().removeIf(p -> p.getId() == id);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, new File(XML_PATH));

            resp.sendRedirect("points");

        } catch (Exception e) {
            throw new ServletException("Erreur suppression point", e);
        }
    }
}