<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.gestionnotes.model.Note" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des notes</title>

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
        <i class="fa fa-pencil-square-o"></i> Gestion des notes
    </h2>

    <!-- ================= FORM ================= -->
    <div class="panel panel-default">
        <div class="panel-heading">
            <strong><i class="fa fa-edit"></i> Ajouter / Modifier une note</strong>
        </div>

        <div class="panel-body">
            <form action="<%= request.getContextPath() %>/note" method="post" class="form-inline">

                <input type="hidden" name="id"
                       value="<%= request.getAttribute("id") != null ? request.getAttribute("id") : "" %>">

                <!-- ETUDIANT -->
                <div class="form-group">
                    <label>Étudiant :</label>
                    <select name="etudiant" class="form-control" required>
                        <option value="">-- Choisir matricule--</option>

                        <%
                            List<String> etudiants = (List<String>) request.getAttribute("etudiants");
                            String selectedEtu = (String) request.getAttribute("etudiant");

                            if (etudiants != null) {
                                for (String e : etudiants) {
                        %>
                        <option value="<%= e %>"
                            <%= (selectedEtu != null && selectedEtu.equals(e)) ? "selected" : "" %>>
                            <%= e %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- MATIERE -->
                <div class="form-group">
                    <label>Matière :</label>
                    <select name="matiere" class="form-control" required>
                        <option value="">-- Choisir matière--</option>

                        <%
                            List<String> matieres = (List<String>) request.getAttribute("matieres");
                            String selectedMat = (String) request.getAttribute("matiere");

                            if (matieres != null) {
                                for (String m : matieres) {
                        %>
                        <option value="<%= m %>"
                            <%= (selectedMat != null && selectedMat.equals(m)) ? "selected" : "" %>>
                            <%= m %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>

                <!-- NOTE -->
                <div class="form-group">
                    <label>Note :</label>
                    <input type="number" step="0.01" min="0" max="20"
                           name="note" class="form-control" required
                           value="<%= request.getAttribute("note") != null ? request.getAttribute("note") : "" %>">
                </div>

                <button type="submit" class="btn btn-success">
                    <i class="fa fa-check"></i> Enregistrer
                </button>

            </form>
        </div>
    </div>

    <!-- ================= TABLE ================= -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <strong><i class="fa fa-list"></i> Liste des notes</strong>
        </div>

        <div class="panel-body">
            <table class="table table-bordered table-striped table-hover">

                <thead>
                    <tr>                        
                        <th>Étudiant</th>
                        <th>Matière</th>
                        <th>Note</th>
                        <th>Actions</th>
                    </tr>
                </thead>

                <tbody>
                <%
                    List<Note> listeNotes = (List<Note>) request.getAttribute("listeNotes");

                    if (listeNotes != null) {
                        for (Note n : listeNotes) {
                %>
                <tr>                    
                    <td><%= n.getEtudiant() %></td>
                    <td><%= n.getMatiere() %></td>
                    <td><%= n.getNote() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/note?action=edit&id=<%= n.getId() %>"
                           class="btn btn-warning btn-xs">
                            <i class="fa fa-pencil"></i> Modifier
                        </a>

<a href="#" 
   class="btn btn-danger btn-xs btn-delete-note" 
   data-href="<%= request.getContextPath() %>/note?action=delete&id=<%= n.getId() %>">
    <i class="fa fa-trash"></i> Supprimer
</a>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">Aucune note trouvée</td>
                </tr>
                <%
                    }
                %>
                </tbody>

            </table>
        </div>
    </div>

</div>


<!-- Modal suppression -->
<div id="confirmDeleteModal" class="modal fade" tabindex="-1" role="dialog">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title"><i class="fa fa-exclamation-triangle"></i> Confirmer la suppression</h4>
      </div>
      <div class="modal-body">
        <p>Voulez-vous vraiment supprimer cette note ?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">Annuler</button>
        <a href="#" class="btn btn-danger btn-sm" id="btnConfirmDelete">Supprimer</a>
      </div>
    </div>
  </div>
</div>


<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js"></script>

<script>
$(document).ready(function() {
    var deleteUrl;

    // Clic sur bouton supprimer
    $('.btn-delete-note').click(function(e) {
        e.preventDefault();
        deleteUrl = $(this).data('href'); // récupère l'URL de suppression
        $('#confirmDeleteModal').modal('show'); // affiche le modal
    });

    // Confirmer la suppression
    $('#btnConfirmDelete').click(function() {
        window.location.href = deleteUrl;
    });
});
</script>

</body>
</html>