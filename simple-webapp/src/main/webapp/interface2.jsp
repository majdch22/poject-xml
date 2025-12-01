<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String username = (String) session.getAttribute("username");
    String userType = (String) session.getAttribute("userType");

    if (username == null || !"admin".equals(userType)) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord Admin</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
                        no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .dashboard-card {
            background: rgba(255, 255, 255, 0.88);
            border-radius: 12px;
            backdrop-filter: blur(6px);
            padding: 30px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        }

        .menu-btn {
            padding: 14px;
            font-size: 17px;
            border-radius: 10px;
        }

        .menu-btn:hover {
            opacity: 0.85;
        }

        .logout-link {
            color: white;
            font-weight: 500;
        }

        .logout-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="container py-5">

    <!-- HEADER -->
    <div class="dashboard-card mb-4 text-center">
        <h1 class="text-success fw-bold">Gestion du Système - Admin</h1>
        <p class="text-secondary">Connecté en tant que : <strong><%= username %></strong></p>
    </div>

    <!-- MENU -->
    <div class="dashboard-card mx-auto" style="max-width: 480px;">

        <div class="d-grid gap-3">

            <a href="vehicules" class="btn btn-primary menu-btn">🚗 Gérer les véhicules</a>
            <a href="employes" class="btn btn-primary menu-btn">👥 Gérer les employés</a>
            <a href="points" class="btn btn-primary menu-btn">🗺️ Gérer les points de collecte</a>
            <a href="planifierTournees.jsp" class="btn btn-primary menu-btn">📅 Planifier les tournées</a>
            <a href="consulterCarte.jsp" class="btn btn-primary menu-btn">🌍 Consulter la carte</a>

            <a href="notifications" class="btn btn-warning menu-btn d-flex justify-content-between align-items-center">
                <span>🔔 Notifications</span>
                <span class="badge bg-danger" id="notificationCount">0</span>
            </a>

            <a href="ImportAndExport.jsp" class="btn btn-primary menu-btn">📊 Import / Export des données</a>
        </div>
    </div>

    <!-- LOGOUT -->
    <div class="text-center mt-4">
        <a href="logout" class="logout-link">🚪 Se déconnecter</a>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script>
    fetch('notificationCount')
        .then(response => response.text())
        .then(count => {
            document.getElementById('notificationCount').textContent = count;
        });
</script>

</body>
</html>
