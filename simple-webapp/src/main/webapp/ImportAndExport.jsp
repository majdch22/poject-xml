<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Import / Export</title>
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
<body class="bg-light">

<div class="container table-container">

    <h2 class="table-title">Import / Export des Données</h2>
    <% if (request.getAttribute("successMessage") != null) { %>
    <div class="alert alert-success">
        <%= request.getAttribute("successMessage") %>
    </div>
<% } %>

<% if (request.getAttribute("errorMessage") != null) { %>
    <div class="alert alert-danger">
        <%= request.getAttribute("errorMessage") %>
    </div>
<% } %>


    <div class="row">

        <!-- IMPORT SECTION -->
        <div class="col-md-6">
            <div class="card shadow-sm p-3">
                <h4>Importer un fichier XML</h4>

                <form action="import" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label class="form-label">Type de données :</label>
                        <select name="type" class="form-select" required>
                            <option value="">Choisir un type</option>
                            <option value="employes">Employés</option>
                            <option value="vehicules">Véhicules</option>
                            <option value="collecte">Points de Collecte</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Choisir un fichier XML :</label>
                        <input type="file" name="xmlFile" class="form-control" accept=".xml" required>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Importer</button>
                </form>
            </div>
        </div>

        <!-- EXPORT SECTION -->
        <div class="col-md-6">
            <div class="card shadow-sm p-3">
                <h4>Exporter les données</h4>
                <p>Choisir le type de données à exporter :</p>
                
                <div class="d-grid gap-2">
                    <a href="export?type=employes" class="btn btn-success">Exporter Employés</a>
                    <a href="export?type=vehicules" class="btn btn-success">Exporter Véhicules</a>
                    <a href="export?type=collecte" class="btn btn-success">Exporter Points de Collecte</a>
                </div>
            </div>
        </div>

    </div>

    <div class="text-center mt-4">
        <a href="interface2.jsp" class="btn btn-secondary">Retour</a>
    </div>

</div>

</body>
</html>