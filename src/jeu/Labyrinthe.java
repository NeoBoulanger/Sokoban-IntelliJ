package jeu;

public class Labyrinthe {
    private boolean[][] murs;

    public static final char MUR = '#' ;
    public static final char DEPOT = '.' ;
    public static final char CAISSE = '$' ;
    public static final char PJ = '@' ;
    public static final char VIDE = ' ' ;

    public Labyrinthe(int x, int y) {
        this.murs = new boolean[x][y];
    }

    public void placerMur(int x, int y) {
        this.murs[x][y] = true;
    }

    public boolean etreMur(int x, int y) {
        return murs[x][y];
    }

    public int getLargeur() {
        return murs.length;
    }

    public int getHauteur() {
        return murs[0].length;
    }

    public boolean[][] getMurs() {
        return murs;
    }
}
