package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.xml.bind.*; 
import com.demo.Employes_ins;

@WebServlet("/deleteEmploye")
public class DeleteEmployeServlet extends HttpServlet {

    private static final String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String cin = req.getParameter("cin");

            JAXBContext context = JAXBContext.newInstance(Employes_ins.class);
            Unmarshaller u = context.createUnmarshaller();
            Employes_ins data = (Employes_ins) u.unmarshal(new File(XML_PATH));

            data.getEmploye().removeIf(e -> e.getCin().equals(cin));

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(data, new File(XML_PATH));

            resp.sendRedirect("employes");

        } catch (Exception e) {
            throw new ServletException("Erreur suppression employé", e);
        }
    }
}