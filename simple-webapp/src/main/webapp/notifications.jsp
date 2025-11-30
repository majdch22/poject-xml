<%@ page import="com.demo.Employe_ins" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Notifications - Demandes d'Inscription</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D') 
                        no-repeat center center fixed;
            background-size: cover;
            padding: 30px 0;
        }

        .container-custom {
            max-width: 900px;
            margin: 0 auto;
            background: rgba(255,255,255,0.95);
            padding: 25px;
            border-radius: 12px;
            box-shadow: 0 8px 18px rgba(0,0,0,0.25);
        }

        .page-title {
            color: #0d6efd;
            font-weight: bold;
            margin-bottom: 25px;
            text-align: center;
        }

        .card {
            margin-bottom: 20px;
        }

        .password-field {
            font-style: italic;
            color: #666;
        }

        .btn-space {
            margin-right: 10px;
        }

        .empty-message {
            text-align: center;
            color: #666;
            padding: 40px 0;
            font-size: 18px;
        }
    </style>

    <script>
        function submitForm(formId) {
            document.getElementById(formId).submit();
        }
    </script>
</head>

<body>

<div class="container-custom">

    <h2 class="page-title">📋 Demandes d'Inscription en Attente</h2>

    <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("success") %></div>
    <% } %>

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("error") %></div>
    <% } %>

    <%
        List<Employe_ins> demandes = (List<Employe_ins>) request.getAttribute("demandesInscription");
        if (demandes != null && !demandes.isEmpty()) {
            for (Employe_ins demande : demandes) {
    %>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">👤 <%= demande.getPrenom() %> <%= demande.getNom() %></h5>
            <p class="card-text">
                <strong>🆔 CIN:</strong> <%= demande.getCin() %><br>
                <strong>💼 Code Travail:</strong> <%= demande.getCode_travail() %><br>
                <strong>📞 Téléphone:</strong> <%= demande.getTel() %><br>
                <strong>🔐 Mot de passe:</strong> <span class="password-field"><%= demande.getPassword() %></span>
            </p>

            <!-- FORMULAIRES HIDDEN -->
            <form id="acceptForm_<%= demande.getCin() %>" action="${pageContext.request.contextPath}/acceptEmploye" method="post" class="d-none">
                <input type="hidden" name="cin" value="<%= demande.getCin() %>">
            </form>

            <form id="rejectForm_<%= demande.getCin() %>" action="${pageContext.request.contextPath}/rejectEmploye" method="post" class="d-none">
                <input type="hidden" name="cin" value="<%= demande.getCin() %>">
            </form>

            <!-- BOUTONS -->
            <button class="btn btn-success btn-space" onclick="submitForm('acceptForm_<%= demande.getCin() %>')">✅ Accepter</button>
            <button class="btn btn-danger" onclick="submitForm('rejectForm_<%= demande.getCin() %>')">❌ Refuser</button>
        </div>
    </div>

    <% 
            }
        } else { 
    %>

    <div class="empty-message">📭 Aucune demande d'inscription en attente</div>

    <% } %>

    <div class="text-center mt-3">
        <a href="interface2.jsp" class="btn btn-secondary">← Retour au tableau de bord</a>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
