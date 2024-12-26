package org.example;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAO {
    public void ajouterVoiture(String marque,int prix_origine, int anneeFabrication, int kilometrage, String type) {
        String sql = "INSERT INTO voitures (marque,annee_fabrication, kilometrage, type,prix_origine) VALUES (?, ?, ?, ?,?)";

        try (Connection connection = Dbconnecteur.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, marque);
            statement.setInt(2, anneeFabrication);
            statement.setInt(3, kilometrage);
            statement.setString(4, type);
            statement.setInt(5,prix_origine );
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Voiture ajoutée avec succès !");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la voiture : " + e.getMessage());
        }
    }

    public void ajouterVoiture(Voiture v,String type) {
        ajouterVoiture(v.marque,v.prix_origine,v.anneeFabrication, v.kilometrage, type);
    }


    // Méthode pour récupérer toutes les voitures
    public void afficherVoitures() {
        String sql = "SELECT * FROM voitures";

        try (Connection connection = Dbconnecteur.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("+-----+-----------+----------------+--------+-------------+-----------+------------------+");
            System.out.println("|  ID |   Marque  | Prix d'origine | Année  | Kilométrage | Type      | Valeur Catalogue |");
            System.out.println("+-----+-----------+----------------+--------+-------------+-----------+------------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String marque = resultSet.getString("marque");
                int prix_origine = resultSet.getInt("prix_origine");
                int anneeFabrication = resultSet.getInt("annee_fabrication");
                int kilometrage = resultSet.getInt("kilometrage");
                String type = resultSet.getString("type");

                Voiture voiture;
                if ("Typique".equals(type)) {
                    voiture = new VoitureTypique(marque, prix_origine, anneeFabrication, kilometrage);
                } else {
                    voiture = new VoitureSport(marque, prix_origine, anneeFabrication, kilometrage);
                }

                double valeurCatalogue = voiture.getValeurCatalogue();

                // Affichage des données formatées
                System.out.printf("| %-3d | %-9s | %-14d | %-6d | %-11d | %-8s  | %-17.2f|\n",
                        id, marque, prix_origine, anneeFabrication, kilometrage, type,valeurCatalogue);
            }

            System.out.println("+-----+-----------+----------------+--------+-------------+-----------+------------------+");

        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des voitures : " + e.getMessage());
        }
    }

    public void rechercherEtAfficher(String marque, Integer prixOrigine, Integer kilometrage, Integer anneeFabrication) {
        StringBuilder sql = new StringBuilder("SELECT * FROM voitures WHERE 1=1"); // Base de la requête

        // Ajouter dynamiquement les conditions en fonction des paramètres non nuls
        if (marque != null && !marque.isEmpty()) {
            sql.append(" AND marque = ?");
        }
        if (prixOrigine != null) {
            sql.append(" AND prix_origine = ?");
        }
        if (kilometrage != null) {
            sql.append(" AND kilometrage = ?");
        }
        if (anneeFabrication != null) {
            sql.append(" AND annee_fabrication = ?");
        }

        try (Connection connection = Dbconnecteur.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {

            // Remplir les paramètres dynamiquement
            int paramIndex = 1;
            if (marque != null && !marque.isEmpty()) {
                statement.setString(paramIndex++, marque);
            }
            if (prixOrigine != null) {
                statement.setInt(paramIndex++, prixOrigine);
            }
            if (kilometrage != null) {
                statement.setInt(paramIndex++, kilometrage);
            }
            if (anneeFabrication != null) {
                statement.setInt(paramIndex++, anneeFabrication);
            }

            // Exécuter la requête
            ResultSet resultSet = statement.executeQuery();

            // Afficher les résultats
            boolean found = false; // Pour vérifier s'il y a des résultats
            while (resultSet.next()) {
                found = true; // Au moins un résultat trouvé
                String type = resultSet.getString("type");
                String marqueResult = resultSet.getString("marque");
                int annee = resultSet.getInt("annee_fabrication");
                int km = resultSet.getInt("kilometrage");
                int prix = resultSet.getInt("prix_origine");

                System.out.println("Type: " + type);
                System.out.println("Marque: " + marqueResult);
                System.out.println("Année de Fabrication: " + annee);
                System.out.println("Kilométrage: " + km + " km");
                System.out.println("Prix d'origine: " + prix + " DH");
                System.out.println("----------------------------");
            }

            if (!found) {
                System.out.println("Aucune voiture ne correspond aux critères donnés.");
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des voitures : " + e.getMessage());
        }
    }



    public void modifierVoiture(int id, String marque,int prix_origine, int anneeFabrication, int kilometrage) {
        String sql = "UPDATE voitures SET marque = ?, annee_fabrication = ?, kilometrage = ?,prix_origine=? WHERE id = ?";

        try (Connection connection = Dbconnecteur.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, marque);
            statement.setInt(2, anneeFabrication);
            statement.setInt(3, kilometrage);
            statement.setInt(4,prix_origine );
            statement.setInt(5, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Voiture modifiée avec succès !");
            } else {
                System.out.println("Aucune voiture trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la voiture : " + e.getMessage());
        }

    }

    public void supprimerVoiture(int id) {
        String sql = "DELETE FROM voitures WHERE id = ?";

        try (Connection connection = Dbconnecteur.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Voiture supprimée avec succès !");
            } else {
                System.out.println("Aucune voiture trouvée avec cet ID.");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la voiture : " + e.getMessage());
        }
    }



}
