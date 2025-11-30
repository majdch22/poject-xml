<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            height: 100vh;
            margin: 0;
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D') no-repeat center center fixed;
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
        }

        .glass-card {
            width: 400px;
            background: rgba(255, 255, 255, 0.25);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.3);
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.4);
        }

        h2{
            color: white;
        }

        label {
            color: #fff;
            font-weight: bold;
        }

        .info p, .error, a {
            color: white;
        }
        a:hover {
            color: #dcdcdc;
        }
    </style>
</head>

<body>

<div class="glass-card">
    <h2 class="text-center mb-4">Connexion</h2>

    <form action="login" method="post">

        <div class="mb-3">
            <label>Nom d'utilisateur (CIN pour employé)</label>
            <input type="text" class="form-control" name="username" required>
        </div>

        <div class="mb-3">
            <label>Mot de passe</label>
            <input type="password" class="form-control" name="password" required>
        </div>

        <button type="submit" class="btn btn-light w-100 fw-bold">
            Se connecter
        </button>

        <p class="error text-center mt-3">${error}</p>

        <div class="info text-center mt-3">
            <p><strong>Admin:</strong> Identifiants admin</p>
            <p><strong>Employé:</strong> CIN + mot de passe</p>
            <p><a href="register.jsp">📝 S'inscrire comme employé</a></p>
        </div>

    </form>
</div>

</body>
</html>
