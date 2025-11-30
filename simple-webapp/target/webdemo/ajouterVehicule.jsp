<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter Véhicule</title>

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
            max-width: 450px;
            background: rgba(255,255,255,0.9);
            padding: 35px;
            border-radius: 12px;
            backdrop-filter: blur(6px);
            box-shadow: 0 8px 18px rgba(0,0,0,0.25);
        }

        .form-title {
            color: #0d6efd;
            font-weight: bold;
        }

        .back-link {
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

        <h2 class="text-center form-title mb-4">Ajouter un véhicule</h2>

        <form action="addVehicule" method="post">

            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="number" name="id" class="form-control">
            </div>

            <div class="mb-3">
                <label class="form-label">Matricule</label>
                <input type="text" name="matricule" class="form-control">
            </div>

            <div class="mb-3">
                <label class="form-label">Capacité</label>
                <input type="number" name="capacite" class="form-control">
            </div>

            <div class="form-check mb-3">
                <input class="form-check-input" type="checkbox" name="disponible" value="true" id="chkDisponible">
                <label class="form-check-label" for="chkDisponible">
                    Disponible
                </label>
            </div>

            <button type="submit" class="btn btn-primary w-100">Ajouter</button>
        </form>

        <div class="text-center mt-3">
            <a href="vehicules" class="back-link text-dark">← Retour</a>
        </div>

    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
