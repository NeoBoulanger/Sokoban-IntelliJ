package jeu;
import java.util.Scanner;
public class MainJeu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Jeu jeu=Chargement.chargerJeu(args[0]);
        boolean fini=false;
        while(!fini) {
            for (int i = 0; i<20 ; i++) {
                System.out.println();
            }
            System.out.println(jeu.jeuToString());
            System.out.println("\033[32m" + "Choisissez une action : \n*********************\n" +
                    "Haut : " + Jeu.HAUT + '\n' + "Bas : " + Jeu.BAS + '\n' +
                    "Gauche : " + Jeu.GAUCHE + '\n' + "Droite : " + Jeu.DROITE + "\033[0m");

            String nb = scanner.nextLine();

            try{
                jeu.deplacerPerso(nb);
            }catch (ActionInconnueException e){
                System.out.println(e.getMessage());
            }

            fini = jeu.etreFini();
        }
        for (int i = 0; i<20 ; i++) {
            System.out.println();
        }
        System.out.println(jeu.jeuToString());
        System.out.println("\n Félicitations !! Vous avez réussi le niveau ! \n \n \n \n");

    }
}