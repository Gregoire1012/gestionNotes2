<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- FontAwesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <style>
        body {
            background: #f4f6f9;
            font-family: 'Segoe UI', sans-serif;
        }

        .content {
            margin-left: 220px;
            padding: 25px;
        }

        .page-title {
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        /* Welcome Banner */
        .welcome-box {
            background: linear-gradient(135deg, #3498db, #2c3e50);
            color: white;
            padding: 25px;
            border-radius: 10px;
            margin-bottom: 25px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .welcome-box h3 {
            margin: 0;
            font-weight: 600;
        }

        .welcome-box p {
            margin-top: 10px;
            opacity: 0.9;
        }

        /* Quick Actions */
        .quick-actions .btn {
            margin: 5px;
            border-radius: 25px;
            padding: 10px 20px;
        }

        /* Panels */
        .panel {
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
        }

        .panel-heading {
            background: #2c3e50 !important;
            color: white !important;
            border-radius: 10px 10px 0 0;
            font-weight: bold;
        }

        /* Table */
        .table th {
            background: #ecf0f1;
        }
        
        
        
 
	    body {
	    margin: 0;
	    font-family: 'Segoe UI', sans-serif;
	    background: #f1f5f9;
		}

		/* SIDEBAR */
		.sidebar {
		    position: fixed;
		    top: 0;
		    left: 0;
		    width: 240px;
		    height: 100%;
		    background: linear-gradient(180deg, #1e293b, #0f172a);
		    color: #fff;
		    transition: 0.3s;
		    box-shadow: 2px 0 10px rgba(0,0,0,0.2);
		}
		
		/* LOGO */
		.sidebar .logo {
		    display: flex;
		    align-items: center;
		    justify-content: center;
		    padding: 20px;
		    font-size: 18px;
		    font-weight: bold;
		    color: #38bdf8;
		    border-bottom: 1px solid rgba(255,255,255,0.1);
		}
		
		.sidebar .logo i {
		    margin-right: 10px;
		    font-size: 20px;
		}
		
		/* MENU */
		.menu {
		    list-style: none;
		    padding: 0;
		    margin: 0;
		}
		
		.menu li {
		    margin: 5px 0;
		}
		
		.menu li a {
		    display: flex;
		    align-items: center;
		    padding: 12px 20px;
		    color: #cbd5e1;
		    text-decoration: none;
		    font-size: 14px;
		    transition: all 0.3s ease;
		}
		
		.menu li a i {
		    margin-right: 12px;
		    width: 20px;
		    text-align: center;
		}
		
		/* HOVER */
		.menu li a:hover {
		    background: rgba(56, 189, 248, 0.2);
		    color: #38bdf8;
		    padding-left: 25px;
		}
		
		/* ACTIVE */
		.menu li a.active {
		    background: #38bdf8;
		    color: #0f172a;
		    font-weight: bold;
		}
		
		/* LOGOUT */
		.menu .logout {
		    position: absolute;
		    bottom: 20px;
		    width: 100%;
		}
		
		/* CONTENT */
		.content {
		    margin-left: 240px;
		    padding: 30px;
		}
		
		/* TITRE */
		h2.page-title {
		    margin-bottom: 20px;
		    color: #1e293b;
		}
		
		/* CARD / PANEL */
		.panel {
		    background: #fff;
		    border-radius: 10px;
		    padding: 20px;
		    box-shadow: 0 4px 12px rgba(0,0,0,0.08);
		}
		
		/* TABLE */
		.table {
		    border-radius: 8px;
		    overflow: hidden;
		}
		
		.table th {
		    background: #38bdf8;
		    color: white;
		}
		
		/* BUTTON */
		.btn {
		    border-radius: 6px;
		    transition: 0.3s;
		}
		
		.btn:hover {
		    transform: scale(1.05);
		}


        

    </style>
</head>

<body>

<!-- Sidebar -->
<jsp:include page="includes/sidebar.jsp" />

<div class="content">

    <!-- Header -->
    <jsp:include page="includes/header.jsp" />

    <div class="container-fluid">

        <!-- Title -->
        <h2 class="page-title">
            <i class="fa fa-dashboard"></i> Tableau de bord
        </h2>

        <!-- Welcome -->
        <div class="welcome-box">
            <h3>Bienvenue 👋</h3>
            <p>
                Vous êtes connecté au système de gestion des notes.<br>
                Gérez facilement les étudiants, examens et résultats.
            </p>
        </div>

        <!-- Actions rapides -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-bolt"></i> Actions rapides
            </div>
            <div class="panel-body quick-actions">
                <a href="etudiant" class="btn btn-primary">
                    <i class="fa fa-user"></i> Gérer les étudiants
                </a>

                <a href="matiere" class="btn btn-success">
                    <i class="fa fa-file-text"></i> Gérer les matières
                </a>

                <a href="note" class="btn btn-warning">
                    <i class="fa fa-pencil"></i> Saisir les notes
                </a>

                <a href="audit" class="btn btn-danger">
                    <i class="fa fa-history"></i> Voir audit
                </a>
            </div>
        </div>

        <!-- Informations -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <i class="fa fa-info-circle"></i> Informations système
            </div>
            <div class="panel-body">
                <ul>
                    <li>✔ Gestion des étudiants</li>
                    <li>✔ Gestion des matières</li>
                    <li>✔ Suivi des notes</li>
                    <li>✔ Historique des opérations (Audit)</li>
                </ul>
            </div>
        </div>

    </div>

</div>

</body>
</html>