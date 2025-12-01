package com.demo.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/export")
public class ExportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String EMPLOYES_XML = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";
    private static final String VEHICULES_XML = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicule.xml";
    private static final String COLLECTE_XML  = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        String filePath;

        // Determine which file to export
        switch (type) {
            case "employes":
                filePath = EMPLOYES_XML;
                break;
            case "vehicules":
                filePath = VEHICULES_XML;
                break;
            case "collecte":
                filePath = COLLECTE_XML;
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Type d'export invalide.");
                return;
        }

        File xmlFile = new File(filePath);
        if (!xmlFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Le fichier XML n'existe pas.");
            return;
        }

        // Prepare file download
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + xmlFile.getName() + "\"");

        try (FileInputStream fis = new FileInputStream(xmlFile);
             OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
