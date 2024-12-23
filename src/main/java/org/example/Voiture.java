package org.example;

public abstract class Voiture {
    protected String marque;
    protected int prix_origine;
    protected int anneeFabrication;
    protected int kilometrage;

    public Voiture(){};
    // Constructeur avec trois paramètres
    public Voiture(int prix_origine, int anneeFabrication, int kilometrage) {
        this.prix_origine = prix_origine;
        this.anneeFabrication = anneeFabrication;
        this.kilometrage = kilometrage;
    }

    // Constructeur avec quatre paramètres, incluant la marque
    public Voiture(String marque, int prix_origine, int anneeFabrication, int kilometrage) {

        this(prix_origine, anneeFabrication, kilometrage);
        this.marque = marque;


    }


    public void affichier_informations(){
        System.out.println("Marque: " + marque);
        System.out.println("Année: " + anneeFabrication);
        System.out.println("Kilométrage: " + kilometrage + " Km");
        System.out.println("Valeur d'origine: " + prix_origine + " DH");
        System.out.println("---------------------------------------------------");
    }
    public abstract double getValeurCatalogue();

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getPrix_origine() {
        return prix_origine;
    }

    public void setPrix_origine(int prix_origine) {
        this.prix_origine = prix_origine;
    }

    public int getAnneeFabrication() {
        return anneeFabrication;
    }

    public void setAnneeFabrication(int anneeFabrication) {
        this.anneeFabrication = anneeFabrication;
    }

    public int getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }
}
