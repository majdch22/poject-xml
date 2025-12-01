<%@ page import="jakarta.xml.bind.*" %>
<%@ page import="com.demo.Employes_ins" %>
<%@ page import="com.demo.Employe_ins" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String XML_PATH = "C:\\Users\\chorf\\OneDrive\\Bureau\\simple-webapp\\simple-webapp\\src\\main\\resources\\employe_ins.xml";
    String cin= request.getParameter("cin");

    JAXBContext ctx = JAXBContext.newInstance(Employes_ins.class);
    Unmarshaller u = ctx.createUnmarshaller();
    Employes_ins data = (Employes_ins) u.unmarshal(new java.io.File(XML_PATH));

    Employe_ins e = null;
    for (Employe_ins x : data.getEmploye())
        if (x.getCin().equals(cin)) e = x;
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Employé</title>
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
    <h2 class="text-center mb-4">Modifier Employé</h2>

    <form action="editEmploye" method="post">
        <input type="hidden" name="cin" value="<%= e.getCin() %>">

        <div class="mb-3">
            <label for="nom" class="form-label">Nom</label>
            <input type="text" class="form-control" id="nom" name="nom" value="<%= e.getNom() %>" required>
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">Prenom</label>
            <input type="text" class="form-control" id="prenom" name="prenom" value="<%= e.getPrenom() %>" required>
        </div>        
        <div class="mb-3">
            <label for="nom" class="form-label">tel</label>
            <input type="text" class="form-control" id="tel" name="tel" value="<%= e.getTel() %>" required>
        </div>
        <div class="mb-3">
            <label for="competence" class="form-label">Compétence</label>
            <input type="text" class="form-control" id="competence" name="competence" value="<%= e.getCompetence() %>" required>
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">password</label>
            <input type="password" class="form-control" id="password" name="password" value="<%= e.getPassword() %>" required>
        </div> 
        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="disponibilite" name="disponibilite" <%= e.isDisponibilite() ? "checked" : "" %>>
            <label class="form-check-label" for="disponibilite">Disponible</label>
        </div>

        <button type="submit" class="btn btn-primary btn-custom">Modifier</button>
    </form>

    <div class="mt-3 text-center">
        <a href="employes" class="btn btn-secondary">Retour</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
