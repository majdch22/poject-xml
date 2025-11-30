<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription Employé</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: url('https://images.unsplash.com/photo-1583364486567-ce2e045676e9?q=80&w=1334&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D')
                        no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', sans-serif;
        }

        .form-container {
            margin-top: 60px;
            max-width: 420px;
            background: rgba(255, 255, 255, 0.90);
            border-radius: 12px;
            padding: 35px;
            backdrop-filter: blur(6px);
            box-shadow: 0 8px 18px rgba(0,0,0,0.25);
        }

        .form-title {
            color: #0d6efd;
            font-weight: bold;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 12px;
            text-decoration: none;
        }

        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>

<div class="container d-flex justify-content-center">
    <div class="form-container">

        <h2 class="text-center form-title mb-4">Créer un compte Employé</h2>

        <form action="register" method="post">

            <div class="mb-3">
                <label class="form-label">Nom</label>
                <input type="text" name="nom" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Prénom</label>
                <input type="text" name="prenom" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">CIN</label>
                <input type="text" name="cin" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Code Travail</label>
                <input type="text" name="code_travail" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Téléphone</label>
                <input type="text" name="tel" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Mot de passe</label>
                <input type="password" name="password" class="form-control" required>
            </div>

            <div class="mb-3">
                <label class="form-label">Confirmer le mot de passe</label>
                <input type="password" name="confirm_password" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary w-100 mt-2">S'inscrire</button>

            <a href="index.jsp" class="back-link text-dark">← Retour</a>

            <p class="text-danger text-center mt-3">${error}</p>
            <p class="text-success text-center mt-3">${success}</p>

        </form>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
