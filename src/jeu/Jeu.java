package jeu;

import javax.swing.*;

public class Jeu {
    private Perso perso;
    private ListeElements caisses;
    private ListeElements depots;
    private Labyrinthe laby;

    public static final String HAUT = "z";
    public static final String BAS = "s" ;
    public static final String GAUCHE = "q" ;
    public static final String DROITE = "d" ;

    public Jeu(Perso perso, ListeElements caisses, ListeElements depots, Labyrinthe laby) {
        this.perso = perso;
        this.caisses = caisses;
        this.depots = depots;
        this.laby = laby;
    }

    public String jeuToString(){
        // On initialise la String comportant le prochain labyrinthe a afficher
        // On rajoute un petit message avant tout
        String res = "\033[34m" + "Voici le descriptif de l'état du Jeu : \n \n" + "\033[0m";
        // On parcours chacune des lignes
        for (int y = 0; y < laby.getHauteur(); y++) {
            // On parcours chacun des chars d'une ligne et on determine le char qu'on va concat grace a getChar
            for (int x = 0; x < laby.getLargeur(); x++){
                //On concat le char correspondant
                res+=this.getChar(x,y);
            }
            // on saute une ligne pour passer a la ligne suivante
            res+="\n" ;
        }
        return res;
    }

    public char getChar(int x, int y) {
        // Vérification en premier lieu de si le char est le personnage
        if (perso.getX() == x && perso.getY() == y) {
            return Labyrinthe.PJ;
        }
        // On vérifie si c'est une caisse
        // on parcours la liste des caisses pour verifier chacunes des coordonnees de chacune des caisses
            if (caisses.getElement(x, y)!=null) {
                return Labyrinthe.CAISSE;
            }
        // On vérifie si c'est un depot
        // On fait pareil que précédemment
            if (depots.getElement(x,y) !=null) {
                return Labyrinthe.DEPOT;
            }
        // Enfin, on vérifie si c'est un mur
        if(laby.etreMur(x, y)) {
            return Labyrinthe.MUR;
        }
        // Si aucun des cas supérieurs est bon, c'est bel et bien un vide
        return Labyrinthe.VIDE;
    }

    public int[] getSuivant(int x, int y, String action) {
        int[] suivant = new int[2];
        switch (action) {
            case BAS:
                suivant[0]= x;
                suivant[1]= y+1;
                break;
            case GAUCHE:
                suivant[0]= x-1;
                suivant[1]= y;
                break;
            case HAUT:
                suivant[0]= x;
                suivant[1]= y-1;
                break;
            case DROITE:
                suivant[0]= x+1;
                suivant[1]= y;
                break;
            default:
                System.out.println("Erreur : action invalide");
                return null;
        }
        return suivant;
    }

    public void deplacerPerso(String action)throws ActionInconnueException {
        // On verifie si l'action est bonne, sinon on lance l'exception ActionInconnueException
            switch (action) {
                case Jeu.HAUT:
                case Jeu.BAS:
                case Jeu.GAUCHE:
                case Jeu.DROITE:
                    // On ne fait rien pour ces cas précis
                    break;

                default:
                    throw new ActionInconnueException("Action inattendue : " + action);
            }
            // On récupere les coordonnees suivante du joueur a deplacer
            int[] caseSuivante = getSuivant(this.perso.getX(), this.perso.getY(), action);
            int nextX = caseSuivante[0];
            int nextY = caseSuivante[1];

            // On vérifie si un mur est derriere
            if(laby.etreMur(nextX, nextY)) {
                return;
            }

            // On verifie si une caisse est derriere
            Element element = caisses.getElement(nextX, nextY);
            if(element != null) {
                Caisse caisse = (Caisse) element;
                int [] derriereCaisse = getSuivant(nextX, nextY, action);
                if(!laby.etreMur(derriereCaisse[0], derriereCaisse[1]) && caisses.getElement(derriereCaisse[0], derriereCaisse[1]) == null) {
                    caisse.setX(derriereCaisse[0]);
                    caisse.setY(derriereCaisse[1]);

                    this.perso.setX(nextX);
                    this.perso.setY(nextY);
                }
            } else {
                this.perso.setX(nextX);
                this.perso.setY(nextY);
            }
        }

    public boolean etreFini() {
        // On parcours la liste de toute les caisses
        for (int i = 0; i<=this.caisses.getTaille()-1 ; i++) {
            boolean fini =false;
            // Pour chaque caisses, on parcours la liste de tout les depots
            for(int j = 0; j<=this.depots.getTaille()-1; j++) {
                if(this.caisses.getElement(i).getX() == this.depots.getElement(j).getX()
                && this.caisses.getElement(i).getY() == this.depots.getElement(j).getY()) {
                    fini = true;
                    break;
                }
            }
            if(!fini) {
                return false;
            }
        }
        return true;
    }
}
