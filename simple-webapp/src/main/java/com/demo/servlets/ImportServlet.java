package com.demo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import jakarta.xml.bind.*;
import javax.xml.validation.*;
import javax.xml.XMLConstants;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

import com.demo.Employes_ins;
import com.demo.Vehicules;
import com.demo.Points;
import com.demo.Employe_ins;
import com.demo.Vehicule;
import com.demo.Point;

@WebServlet("/import")
@MultipartConfig
public class ImportServlet extends HttpServlet {

    private static final String EMPLOYES_XML = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";
    private static final String VEHICULES_XML = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicules.xml";
    private static final String COLLECTE_XML  = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";

    private static final String EMPLOYES_XSD = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xsd";
    private static final String VEHICULES_XSD = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicules.xsd";
    private static final String COLLECTE_XSD  = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xsd";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        Part filePart = request.getPart("xmlFile");

        if (type == null || filePart == null) {
            throw new ServletException("Type d'import ou fichier manquant.");
        }

        try (InputStream xmlInput = filePart.getInputStream()) {

            String result;

            switch (type) {
                case "employes":
                    result = importAndMergeXML(xmlInput, Employes_ins.class, EMPLOYES_XSD, EMPLOYES_XML);
                    break;
                case "vehicules":
                    result = importAndMergeXML(xmlInput, Vehicules.class, VEHICULES_XSD, VEHICULES_XML);
                    break;
                case "collecte":
                    result = importAndMergeXML(xmlInput, Points.class, COLLECTE_XSD, COLLECTE_XML);
                    break;
                default:
                    throw new ServletException("Type d'import inconnu: " + type);
            }

            // Handle result
            if (result.startsWith("ERROR")) {
                request.setAttribute("errorMessage", result);
            } else {
                request.setAttribute("successMessage", "Importation effectuée avec succès !");
            }

            request.getRequestDispatcher("ImportAndExport.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("errorMessage", "Erreur lors de l'import: " + e.getMessage());
            request.getRequestDispatcher("ImportAndExport.jsp").forward(request, response);
        }
    }

    /** MAIN IMPORT FUNCTION WITH DUPLICATE CHECKING */
    private <T> String importAndMergeXML(InputStream xmlFile, Class<T> clazz, String xsdPath, String savePath)
            throws Exception {

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));

        JAXBContext ctx = JAXBContext.newInstance(clazz);
        Unmarshaller u = ctx.createUnmarshaller();
        u.setSchema(schema);

        // New uploaded data
        T newData = (T) u.unmarshal(xmlFile);

        // Existing file data
        T existingData = null;
        File existingFile = new File(savePath);

        if (existingFile.exists() && existingFile.length() > 0) {
            try (FileInputStream fis = new FileInputStream(existingFile)) {
                existingData = (T) u.unmarshal(fis);
            }
        }

        // Duplicate detection
        String duplicateMsg = checkDuplicates(existingData, newData, clazz);
        if (duplicateMsg != null) {
            return "ERROR - Doublons détectés : " + duplicateMsg;
        }

        // Merge
        T merged = mergeData(existingData, newData, clazz);

        // Save merged XML
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(merged, new File(savePath));

        return "OK";
    }

    /** DETECT DUPLICATES BASED ON ID/CIN/MATRICULE */
    private <T> String checkDuplicates(T existing, T newData, Class<T> clazz) {
        if (existing == null) return null;

        List<String> duplicates = new ArrayList<>();

        if (clazz == Employes_ins.class) {
            List<Employe_ins> oldList = ((Employes_ins) existing).getEmploye();
            List<Employe_ins> newList = ((Employes_ins) newData).getEmploye();

            for (Employe_ins n : newList) {
                for (Employe_ins e : oldList) {
                    if (n.getCin().equals(e.getCin())) {
                        duplicates.add("Employé CIN=" + n.getCin());
                    }
                }
            }
        }

        if (clazz == Vehicules.class) {
            List<Vehicule> oldList = ((Vehicules) existing).getVehicules();
            List<Vehicule> newList = ((Vehicules) newData).getVehicules();

            for (Vehicule n : newList) {
                for (Vehicule e : oldList) {
                    if (n.getMatricule().equals(e.getMatricule())) {
                        duplicates.add("Véhicule Matricule=" + n.getMatricule());
                    }
                }
            }
        }

        if (clazz == Points.class) {
            List<Point> oldList = ((Points) existing).getPoints();
            List<Point> newList = ((Points) newData).getPoints();

            for (Point n : newList) {
                for (Point e : oldList) {
                    if (n.getId()==e.getId()) {
                        duplicates.add("Point id=" + n.getId());
                    }
                }
            }
        }

        return duplicates.isEmpty() ? null : String.join(", ", duplicates);
    }

    /** MERGE LOGIC */
    private <T> T mergeData(T existing, T newData, Class<T> clazz) {
        if (existing == null) return newData;

        if (clazz == Employes_ins.class) {
            List<Employe_ins> merged = new ArrayList<>();
            merged.addAll(((Employes_ins) existing).getEmploye());
            merged.addAll(((Employes_ins) newData).getEmploye());
            ((Employes_ins) existing).setEmploye(merged);
            return existing;
        }

        if (clazz == Vehicules.class) {
            List<Vehicule> merged = new ArrayList<>();
            merged.addAll(((Vehicules) existing).getVehicules());
            merged.addAll(((Vehicules) newData).getVehicules());
            ((Vehicules) existing).setVehicules(merged);
            return existing;
        }

        if (clazz == Points.class) {
            List<Point> merged = new ArrayList<>();
            merged.addAll(((Points) existing).getPoints());
            merged.addAll(((Points) newData).getPoints());
            ((Points) existing).setPoints(merged);
            return existing;
        }

        return newData;
    }
}
