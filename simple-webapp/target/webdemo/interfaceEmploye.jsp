<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String username = (String) session.getAttribute("username");
    String userType = (String) session.getAttribute("userType");
    
    // Rediriger vers login si pas connecté
    if (username == null || !"employe".equals(userType)) {
        response.sendRedirect("index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord Employé</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D') 
                        no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 20px 0;
        }

        .header {
            background: #2e86de;
            color: white;
            padding: 20px;
            text-align: center;
            border-radius: 8px;
            margin-bottom: 20px;
        }

        .welcome {
            background: rgba(255,255,255,0.9);
            padding: 20px;
            text-align: center;
            border-radius: 8px;
            margin-bottom: 20px;
            box-shadow: 0 6px 15px rgba(0,0,0,0.1);
        }

        .user-info {
            background: rgba(232,244,255,0.9);
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 25px;
            text-align: center;
            border-left: 4px solid #2e86de;
        }

        .menu {
            max-width: 500px;
            margin: 0 auto;
        }

        .menu a {
            display: flex;
            align-items: center;
            padding: 15px;
            margin: 12px 0;
            background: #2e86de;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        .menu a:hover {
            background: #1b4f72;
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0,0,0,0.2);
        }

        .menu-icon {
            font-size: 20px;
            margin-right: 12px;
            width: 25px;
            text-align: center;
        }

        .logout {
            text-align: center;
            margin-top: 25px;
        }

        .logout a {
            color: #2e86de;
            text-decoration: none;
            font-weight: bold;
            padding: 10px 25px;
            border: 2px solid #2e86de;
            border-radius: 5px;
            transition: all 0.3s ease;
        }

        .logout a:hover {
            background: #2e86de;
            color: white;
        }
    </style>
</head>

<body>
    <div class="header">
        <h1>🚛 Tableau de Bord Employé</h1>
    </div>
    
    <div class="welcome">
        <h2>👋 Bienvenue, <%= username %> !</h2>
        <p>Vous êtes connecté en tant qu'employé de collecte</p>
    </div>

    <div class="user-info">
        <strong>📅 Date du jour :</strong> <span id="currentDate"></span> | 
        <strong>⏰ Heure :</strong> <span id="currentTime"></span>
    </div>

    <div class="menu">
        <a href="gererRapports.jsp">
            <span class="menu-icon">📋</span>
            <span>Gérer les rapports des tournées</span>
        </a>
        
        <a href="niveauRemplissage.jsp">
            <span class="menu-icon">📊</span>
            <span>Mettre à jour le niveau de remplissage</span>
        </a>
        
        <a href="gererCarte.jsp">
            <span class="menu-icon">🗺️</span>
            <span>Gérer la carte des points de collecte</span>
        </a>
        
        <a href="profilEmploye.jsp">
            <span class="menu-icon">👤</span>
            <span>Mon profil employé</span>
        </a>
    </div>

    <div class="logout">
        <a href="logout">🚪 Se déconnecter</a>
    </div>

    <!-- Script pour date et heure -->
    <script>
        function updateDateTime() {
            const now = new Date();
            const dateOptions = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
            const timeOptions = { hour: '2-digit', minute: '2-digit', second: '2-digit' };
            
            document.getElementById('currentDate').textContent = now.toLocaleDateString('fr-FR', dateOptions);
            document.getElementById('currentTime').textContent = now.toLocaleTimeString('fr-FR', timeOptions);
        }
        
        updateDateTime();
        setInterval(updateDateTime, 1000);
    </script>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
