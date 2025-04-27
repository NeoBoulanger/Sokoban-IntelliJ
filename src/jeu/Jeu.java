package jeu;

public class Jeu {
    private Perso perso;
    private ListeElements caisses;
    private ListeElements depots;
    private Labyrinthe laby;

    protected static final String HAUT = "Haut";
    protected static final String BAS = "Bas" ;
    protected static final String GAUCHE = "Gauche" ;
    protected static final String DROITE = "Droite" ;

    public Jeu(Perso perso, ListeElements caisses, ListeElements depots, Labyrinthe laby) {
        this.perso = perso;
        this.caisses = caisses;
        this.depots = depots;
        this.laby = laby;
    }

    public String jeuToString(){
        String res = "Voici le descriptif de l'état du Jeu : \n \n" ;
        for (int y = 0; y < laby.getHauteur(); y++) {
            for (int x = 0; x < laby.getLargeur(); x++){
                res+=this.getChar(x,y);
            }
            res+="\n" ;
        }
        return res;
    }

    public char getChar(int x, int y) {
        if (perso.getX() == x && perso.getY() == y) {
            return Labyrinthe.PJ;
        }
        for (int i = 0; i < caisses.getTaille(); i++){
            if (caisses.getElement(i).getX() == x && caisses.getElement(i).getY() == y) {
                return Labyrinthe.CAISSE;
            }
        }
        for (int j = 0; j <depots.getTaille(); j++) {
            if (depots.getElement(j).getX() == x && depots.getElement(j).getY() == y) {
                return Labyrinthe.DEPOT;
            }
        }
        if(laby.etreMur(x, y)) {
            return Labyrinthe.MUR;
        }
        return Labyrinthe.VIDE;
    }
}
