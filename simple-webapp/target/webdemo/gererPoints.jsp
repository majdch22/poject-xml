<%@ page import="com.demo.Point" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion Points de Collecte</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
                        no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
            padding: 30px 0;
        }

        .table-container {
            max-width: 1000px;
            margin: 0 auto;
            background: rgba(255,255,255,0.9);
            padding: 25px;
            border-radius: 12px;
            backdrop-filter: blur(6px);
            box-shadow: 0 8px 18px rgba(0,0,0,0.25);
        }

        .table-title {
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 20px;
            text-align: center;
        }

        .btn-link {
            text-decoration: none;
        }

        .btn-link:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="container table-container">

    <h2 class="table-title">Liste des Points de Collecte</h2>

    <div class="mb-3 text-end">
        <a href="ajouterPoint.jsp" class="btn btn-success">➕ Ajouter un point</a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered align-middle">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Localisation</th>
                    <th>Type Électrique</th>
                    <th>État</th>
                    <th>Contexte</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <% 
                List<Point> list = (List<Point>) request.getAttribute("points");
                for (Point p : list) {
            %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getLocalisation() %></td>
                    <td><%= p.getTypeElectrique() %></td>
                    <td><%= p.getEtat() %></td>
                    <td><%= p.getContexte() %></td>
                    <td>
                        <a href="modifierPoint.jsp?id=<%= p.getId() %>" class="btn btn-sm btn-primary">Modifier</a>
                        <a href="deletePoint?id=<%= p.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <div class="text-center mt-3">
        <a href="interface2.jsp" class="btn btn-secondary">← Retour</a>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
