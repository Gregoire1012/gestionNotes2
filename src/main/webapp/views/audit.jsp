<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionnotes.model.Audit" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Audit des opérations</title>

    <!-- Bootstrap 3 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <style>
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
<jsp:include page="/includes/sidebar.jsp" />

<div class="content">

    <h2 class="page-title">
        <i class="fa fa-search"></i> Audit des opérations
    </h2>

    <!-- ================= FILTRE ================= -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong><i class="fa fa-filter"></i> Filtrer</strong>
        </div>

        <div class="panel-body">
            <form method="get" class="form-inline">

                <div class="form-group">
                    <label>Utilisateur :</label>
                    <input type="text" name="utilisateur" class="form-control"
                           value="<%= request.getParameter("utilisateur") != null ? request.getParameter("utilisateur") : "" %>">
                </div>

                <div class="form-group">
                    <label>Type :</label>
                    <input type="text" name="typeOp" class="form-control"
                           value="<%= request.getParameter("typeOp") != null ? request.getParameter("typeOp") : "" %>">
                </div>

                <div class="form-group">
                    <label>Date :</label>
                    <input type="date" name="date" class="form-control"
                           value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>">
                </div>

                <button type="submit" class="btn btn-primary">
                    <i class="fa fa-filter"></i> Filtrer
                </button>
            </form>
        </div>
    </div>

    <!-- ================= TABLE ================= -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <strong><i class="fa fa-list"></i> Liste des opérations</strong>
        </div>

        <div class="panel-body">
            <table class="table table-bordered table-striped table-hover">

                <thead>
                    <tr>                        
                        <th>Type d'opération</th>
                        <th>Utilisateur</th>
                        <th>Ancien état</th>
                        <th>Nouvel état</th>
                        <th>Date</th>
                    </tr>
                </thead>

                <tbody>
                <%
                    List<Audit> liste = (List<Audit>) request.getAttribute("listeAudit");

                    if (liste != null && !liste.isEmpty()) {
                        for (Audit a : liste) {
                %>
					<tr>
					    <td><%= a.getOperation() %></td>
					    <td><%= a.getTableName() %></td>
					    <td><%= a.getAncienValeur() %></td>
					    <td><%= a.getNouvelleValeur() %></td>
					    <td><%= a.getDateOperation() %></td>
					</tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6" class="text-center">
                        Aucune opération trouvée
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>

            </table>
        </div>
    </div>

</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"></script>

</body>
</html>