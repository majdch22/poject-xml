<%@ page import="jakarta.xml.bind.*" %>
<%@ page import="com.demo.Vehicules" %>
<%@ page import="com.demo.Vehicule" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\vehicule.xml";
    int id = Integer.parseInt(request.getParameter("id"));

    JAXBContext ctx = JAXBContext.newInstance(Vehicules.class);
    Unmarshaller u = ctx.createUnmarshaller();
    Vehicules data = (Vehicules) u.unmarshal(new java.io.File(XML_PATH));

    Vehicule v = null;
    for (Vehicule x : data.getVehicules())
        if (x.getId() == id) v = x;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Véhicule</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D') no-repeat center center fixed;
            background-size: cover;
            min-height: 100vh;
        }
        .form-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 12px;
            max-width: 500px;
            margin: 50px auto;
            box-shadow: 0 0 20px rgba(0,0,0,0.3);
        }
        .btn-custom {
            width: 100%;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2 class="text-center mb-4">Modifier Véhicule</h2>

    <form action="editVehicule" method="post">
        <input type="hidden" name="id" value="<%= v.getId() %>">

        <div class="mb-3">
            <label for="matricule" class="form-label">Matricule</label>
            <input type="text" class="form-control" id="matricule" name="matricule" value="<%= v.getMatricule() %>" required>
        </div>

        <div class="mb-3">
            <label for="capacite" class="form-label">Capacité</label>
            <input type="number" class="form-control" id="capacite" name="capacite" value="<%= v.getCapacite() %>" required>
        </div>

        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="disponible" name="disponible" <%= v.isDisponible() ? "checked" : "" %>>
            <label class="form-check-label" for="disponible">Disponible</label>
        </div>

        <button type="submit" class="btn btn-primary btn-custom">Modifier</button>
    </form>

    <div class="mt-3 text-center">
        <a href="vehicules" class="btn btn-secondary">Retour</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
