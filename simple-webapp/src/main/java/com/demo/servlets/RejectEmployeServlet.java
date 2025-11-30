package com.demo.servlets;

import com.demo.Employes_ins;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.bind.*;
import java.io.IOException;
import java.io.File;

@WebServlet("/rejectEmploye")
public class RejectEmployeServlet extends HttpServlet {

    private static final String XML_PATH =
            "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            String cin = req.getParameter("cin");

            JAXBContext ctx = JAXBContext.newInstance(Employes_ins.class);
            File file = new File(XML_PATH);

            if (file.exists()) {
                Unmarshaller u = ctx.createUnmarshaller();
                Employes_ins employes = (Employes_ins) u.unmarshal(file);

                employes.getEmploye().removeIf(e -> e.getCin().equals(cin));

                Marshaller m = ctx.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(employes, file);
            }

            // 👉 Important : redirection (GET)
            resp.sendRedirect("notifications");

        } catch (Exception e) {
            throw new ServletException("Erreur rejet employé", e);
        }
    }
}
