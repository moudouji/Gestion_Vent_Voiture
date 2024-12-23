package org.example;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        VoitureDAO voitureDAO = new VoitureDAO();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n---- Menu Principal ----");
            System.out.println("1. Ajouter une voiture");
            System.out.println("2. Afficher les voitures");
            System.out.println("3. Modifier une voiture");
            System.out.println("4. Supprimer une voiture");
            System.out.println("5. Calculer la valeur catalogue d'une voiture");
            System.out.println("0. Quitter");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1: // Ajouter une voiture
                    System.out.print("Entrez la marque : ");
                    String marque = scanner.nextLine();

                    System.out.print(" Entrez prix d’origine : ");
                    int prix_origine = scanner.nextInt();

                    System.out.print("Entrez l'année de fabrication: ");
                    int anneeFabrication = scanner.nextInt();

                    System.out.print("Entrez le kilométrage : ");
                    int kilometrage = scanner.nextInt();

                    scanner.nextLine();

                    System.out.print("Entrez le type (typique/sport) : ");
                    String type = scanner.nextLine();

                    voitureDAO.ajouterVoiture(marque,prix_origine, anneeFabrication, kilometrage, type);
                    break;

                case 2: // Afficher les voitures
                    voitureDAO.afficherVoitures();
                    break;

                case 3: // Modifier une voiture
                    System.out.print("Entrez l'ID de la voiture à modifier : ");
                    int id = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Entrez la nouvelle marque : ");
                    String nouvelleMarque = scanner.nextLine();

                    System.out.print("Entrez la nouvelle prix d'origine : ");
                    int nouvelle_prix_origine = scanner.nextInt();

                    System.out.print("Entrez la nouvelle année de fabrication : ");
                    int nouvelleAnnee = scanner.nextInt();

                    System.out.print("Entrez le nouveau kilométrage : ");
                    int nouveauKilometrage = scanner.nextInt();
                    scanner.nextLine(); // Consommer la nouvelle ligne

                    // Appeler une méthode pour modifier la voiture (à implémenter dans VoitureDAO)
                    voitureDAO.modifierVoiture(id, nouvelleMarque,nouvelle_prix_origine, nouvelleAnnee, nouveauKilometrage);
                    break;
                case 4: // Supprimer un voitures

                    System.out.print("Entrez l'ID de la voiture à supprimer  : ");
                    int idsup = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("tu veux  supprimer la voiture de l id  : "+idsup+"(yes/no)");
                    String ver = scanner.nextLine();
                    if(ver.equals("yes")) voitureDAO.supprimerVoiture(idsup);
                    break;
                case 5: // Calculer la valeur catalogue

                    System.out.print(" Entrez prix d’origine : ");
                    int prix_origineCatalogue = scanner.nextInt();

                    System.out.print("Entrez l'année de fabrication : ");
                    int anneeCatalogue = scanner.nextInt();

                    System.out.print("Entrez le kilométrage : ");
                    int kilometrageCatalogue = scanner.nextInt();

                    scanner.nextLine();
                    System.out.print("Entrez le type (typique/sport) : ");
                    String typeCatalogue = scanner.nextLine();

                    Voiture voiture;
                    if (typeCatalogue.equalsIgnoreCase("typique")) {
                        voiture = new VoitureTypique( prix_origineCatalogue, anneeCatalogue, kilometrageCatalogue);
                    } else {
                        voiture = new VoitureSport( prix_origineCatalogue, anneeCatalogue, kilometrageCatalogue);
                    }
                    System.out.println("Valeur catalogue : " + voiture.getValeurCatalogue() + " DH");
                    break;

                case 0: // Quitter
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide, veuillez réessayer.");
                    break;
            }
        } while (choix != 0);

        scanner.close();
    }
}
