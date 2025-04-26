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
}
