<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionnotes.model.Matiere" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des matières</title>

    <!-- Bootstrap 3 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css">
    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        /* Body & Font */
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background: #f1f5f9;
        }

        /* Sidebar */
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
		.menu li a:hover {
		    background: rgba(56, 189, 248, 0.2);
		    color: #38bdf8;
		    padding-left: 25px;
		}
		.menu li a.active {
		    background: #38bdf8;
		    color: #0f172a;
		    font-weight: bold;
		}
		.menu .logout {
		    position: absolute;
		    bottom: 20px;
		    width: 100%;
		}

        /* Content */
        .content {
            margin-left: 240px;
            padding: 30px;
        }
        h2.page-title {
            margin-bottom: 20px;
            color: #1e293b;
        }

        .panel {
            background: #fff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
        }

        .form-label {
            font-weight: bold;
        }

        .table th {
            background: #38bdf8;
            color: #fff;
        }
        .table {
            border-radius: 8px;
            overflow: hidden;
        }

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

<div class="content container-fluid">
    <h2 class="page-title"><i class="fa fa-book"></i> Gestion des matières</h2>

    <!-- Formulaire -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong><i class="fa fa-edit"></i> Ajouter / Modifier une matière</strong>
        </div>
        <div class="panel-body">
            <form action="<%= request.getContextPath() %>/matiere" method="post">
                <input type="hidden" name="id" value="<%= request.getAttribute("id") != null ? request.getAttribute("id") : "" %>">

                <div class="row">
                    <div class="col-md-3">
                        <label class="form-label">Numéro matière :</label>
                        <input type="text" name="num_matiere" class="form-control" required
                               value="<%= request.getAttribute("num_matiere") != null ? request.getAttribute("num_matiere") : "" %>">
                    </div>
                    <div class="col-md-4">
                        <label class="form-label">Nom :</label>
                        <input type="text" name="nom" class="form-control" required
                               value="<%= request.getAttribute("nom") != null ? request.getAttribute("nom") : "" %>">
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Coefficient :</label>
                        <input type="number" name="coefficient" class="form-control" required
                               value="<%= request.getAttribute("coefficient") != null ? request.getAttribute("coefficient") : "" %>">
                    </div>
                </div>

                <div class="row" style="margin-top:15px;">
                    <div class="col-md-12">
                        <button type="submit" class="btn btn-success"><i class="fa fa-check"></i> Enregistrer</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!-- Tableau des matières -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <strong><i class="fa fa-list"></i> Liste des matières</strong>
        </div>
        <div class="panel-body">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th>Numéro matière</th>
                        <th>Nom</th>
                        <th>Coefficient</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Matiere> liste = (List<Matiere>) request.getAttribute("listeMatieres");
                        if (liste != null && !liste.isEmpty()) {
                            for (Matiere m : liste) {
                    %>
                    <tr>
                        <td><%= m.getNumMatiere() %></td>
                        <td><%= m.getNom() %></td>
                        <td><%= m.getCoefficient() %></td>
                        <td>
                            <a href="<%= request.getContextPath() %>/matiere?action=edit&id=<%= m.getId() %>" class="btn btn-warning btn-xs">
                                <i class="fa fa-pencil"></i> Modifier
                            </a>
                            <a href="#" class="btn btn-danger btn-xs btn-delete" data-href="<%= request.getContextPath() %>/matiere?action=delete&id=<%= m.getId() %>">
                                <i class="fa fa-trash"></i> Supprimer
                            </a>
                        </td>
                    </tr>
                    <%
                            }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">Aucune matière trouvée</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</div>


<!-- Modal Suppression -->
<div id="deleteModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><i class="fa fa-exclamation-triangle"></i> Confirmer la suppression</h4>
      </div>
      <div class="modal-body">
        <p>Voulez-vous vraiment supprimer cette matière ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Annuler</button>
        <a href="#" class="btn btn-danger btn-sm" id="confirmDelete">Supprimer</a>
      </div>
    </div>
  </div>
</div>



<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"></script>

<script>
$(document).ready(function(){
    var deleteUrl;

    // Clic sur Supprimer
    $('.btn-delete').click(function(e){
        e.preventDefault();
        deleteUrl = $(this).data('href');
        $('#deleteModal').modal('show');
    });

    // Confirmer la suppression
    $('#confirmDelete').click(function(){
        window.location.href = deleteUrl;
    });
});
</script>

</body>
</html>