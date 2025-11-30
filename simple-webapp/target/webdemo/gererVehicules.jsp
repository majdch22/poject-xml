<%@ page import="com.demo.Vehicule" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion Véhicules</title>

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
            max-width: 900px;
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

    <h2 class="table-title">Liste des Véhicules</h2>

    <div class="mb-3 text-end">
        <a href="ajouterVehicule.jsp" class="btn btn-success">➕ Ajouter un véhicule</a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered align-middle">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Matricule</th>
                    <th>Capacité</th>
                    <th>Disponible</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <% 
                List<Vehicule> list = (List<Vehicule>) request.getAttribute("vehicules");
                for (Vehicule v : list) {
            %>
                <tr>
                    <td><%= v.getId() %></td>
                    <td><%= v.getMatricule() %></td>
                    <td><%= v.getCapacite() %></td>
                    <td><%= v.isDisponible() ? "Oui" : "Non" %></td>
                    <td>
                        <a href="modifierVehicule.jsp?id=<%= v.getId() %>" class="btn btn-sm btn-primary">Modifier</a>
                        <a href="deleteVehicule?id=<%= v.getId() %>" class="btn btn-sm btn-danger">Supprimer</a>
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
