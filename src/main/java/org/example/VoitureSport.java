package org.example;

public class VoitureSport extends Voiture {

    public VoitureSport(){};
    public VoitureSport(int prix_origine,int anneeFabrication, int kilometrage) {
        super(prix_origine, anneeFabrication, kilometrage);
        this.marque = marque;
    }

    public VoitureSport(String marque,int prix_origine,int anneeFabrication, int kilometrage) {
        super(marque,prix_origine, anneeFabrication, kilometrage);
    }
    @Override
    public double getValeurCatalogue() {
        ValeurCatalogue valeurCatalogue = (prix_origine,annee, km) -> {
            int age = 2024 - annee;
            double valeur = prix_origine - (age * 10000) - (km / 16000) * 5000 + (age * 2500);
            return Math.max(valeur, 0); // Évite une valeur négative
        };
        return valeurCatalogue.calculerValeur(prix_origine,anneeFabrication, kilometrage);
    }
}
