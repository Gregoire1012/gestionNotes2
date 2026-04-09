<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Connexion - Gestion des Notes</title>

    <!-- Bootstrap 3 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        /* Background */
        body {
            height: 100vh;
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            background: rgb(98, 160, 234);
        }

        /* Card */
        .login-card {
            background: #f4f6f9;
            border-radius: 12px;
            padding: 40px 30px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }
        .login-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 30px rgba(0,0,0,0.15);
        }

        /* Title */
        .login-card h2 {
            text-align: center;
            margin-bottom: 30px;
            font-weight: bold;
            color: #333;
        }

        /* Input group with icon */
        .input-icon {
            position: relative;
            margin-bottom: 20px;
        }
        .input-icon i {
            position: absolute;
            top: 12px;
            left: 12px;
            color: #888;
        }
        .input-icon input {
            padding-left: 40px;
            border-radius: 8px;
            border: 1px solid #ccc;
            height: 42px;
            width: 100%;
            transition: all 0.3s;
        }
        .input-icon input:focus {
            border-color: #667eea;
            box-shadow: 0 0 8px rgba(102,126,234,0.3);
            outline: none;
        }

        /* Button */
        .btn-login {
            width: 100%;
            background-color: rgb(28, 113, 216);
            border: none;
            border-radius: 8px;
            color: #fff;
            font-weight: bold;
            font-size: 16px;
            padding: 12px;
            transition: all 0.3s;
        }
        .btn-login:hover {
            background-color: #5a67d8;
            transform: translateY(-2px);
        }

        /* Footer */
        .login-footer {
            text-align: center;
            margin-top: 20px;
            font-size: 13px;
            color: #999;
        }

        /* Error message */
        .alert {
            border-radius: 8px;
            margin-bottom: 15px;
        }

        @media(max-width: 480px){
            .login-card {
                padding: 30px 20px;
            }
        }

    </style>
</head>

<body>

<div class="login-card">

    <h2><i class="fa fa-user-circle"></i> Connexion</h2>

    <!-- Message d'erreur -->
    <%
        String erreur = (String) request.getAttribute("erreur");
        if(erreur != null){
    %>
        <div class="alert alert-danger text-center">
            <%= erreur %>
        </div>
    <%
        }
    %>

    <!-- Formulaire -->
    <form action="<%= request.getContextPath() %>/login" method="post">

        <div class="input-icon">
            <i class="fa fa-user"></i>
            <input type="text" name="username" placeholder="Nom d'utilisateur" class="form-control" required>
        </div>

        <div class="input-icon">
            <i class="fa fa-lock"></i>
            <input type="password" name="password" placeholder="Mot de passe" class="form-control" required>
        </div>

        <button type="submit" class="btn btn-login">Se connecter</button>
    </form>

    <div class="login-footer">
        © 2026 - Gestion des Notes
    </div>

</div>

</body>
</html>