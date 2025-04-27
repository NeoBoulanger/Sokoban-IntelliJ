package jeu;

public class Main {
    public static void main(String[] args) {
        Jeu jeu=Chargement.chargerJeu(args[0]);
        System.out.println(jeu.jeuToString());
    }
}