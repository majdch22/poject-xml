

package com.demo.servlets;
import java.io.IOException;
import com.demo.Employe_ins;
import com.demo.Employes_ins;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.xml.bind.*;

import java.io.File;
@WebServlet("/acceptEmploye")
public class AcceptEmployeServlet extends HttpServlet {

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

                for (Employe_ins e : employes.getEmploye()) {
                    if (e.getCin().equals(cin)) {
                        e.setstatus("accepte");
                        break;
                    }
                }

                Marshaller m = ctx.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.marshal(employes, file);
            }

            // 👉 Important : redirection (GET)
            resp.sendRedirect("notifications");

        } catch (Exception e) {
            throw new ServletException("Erreur acceptation employé", e);
        }
    }
}