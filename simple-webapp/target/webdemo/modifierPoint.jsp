<%@ page import="jakarta.xml.bind.*" %>
<%@ page import="com.demo.Points" %>
<%@ page import="com.demo.Point" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\point.xml";
    int id = Integer.parseInt(request.getParameter("id"));

    JAXBContext ctx = JAXBContext.newInstance(Points.class);
    Unmarshaller u = ctx.createUnmarshaller();
    Points data = (Points) u.unmarshal(new java.io.File(XML_PATH));

    Point p = null;
    for (Point x : data.getPoints())
        if (x.getId() == id) p = x;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Point de Collecte</title>
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
            max-width: 550px;
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
    <h2 class="text-center mb-4">Modifier Point de Collecte</h2>

    <form action="editPoint" method="post">
        <input type="hidden" name="id" value="<%= p.getId() %>">

        <div class="mb-3">
            <label for="localisation" class="form-label">Localisation</label>
            <input type="text" class="form-control" id="localisation" name="localisation" value="<%= p.getLocalisation() %>" required>
        </div>

        <div class="mb-3">
            <label for="typeElectrique" class="form-label">Type Électrique</label>
            <input type="text" class="form-control" id="typeElectrique" name="typeElectrique" value="<%= p.getTypeElectrique() %>" required>
        </div>

        <div class="mb-3">
            <label for="etat" class="form-label">État</label>
            <input type="text" class="form-control" id="etat" name="etat" value="<%= p.getEtat() %>" required>
        </div>

        <div class="mb-3">
            <label for="contexte" class="form-label">Contexte</label>
            <input type="text" class="form-control" id="contexte" name="contexte" value="<%= p.getContexte() %>" required>
        </div>

        <button type="submit" class="btn btn-primary btn-custom">Modifier</button>
    </form>

    <div class="mt-3 text-center">
        <a href="points" class="btn btn-secondary">Retour</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
