import jeu.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
public class TestJeu {
    private Jeu jeu;

    // On va creer un labyrinthe rudimentaire et le finir tout en testant toute les possibilités
    @BeforeEach
    public void setUp() {
        Labyrinthe laby = new Labyrinthe(3, 3); // On creer une grille de 3*3
        laby.placerMur(0, 0); // On place un mur pour tester la methode correspondante
        ListeElements caisses = new ListeElements();
        ListeElements depots = new ListeElements();
        caisses.ajouterElement(new Caisse(1, 1)); // On ajoute une caisse a (1,1)
        depots.ajouterElement(new Depot(2, 1)); // On ajoute un depot a (2,1)
        Perso perso = new Perso(0, 1); // On ajoute un personnage a (0,1)

        jeu = new Jeu(perso, caisses, depots, laby); // On creer le jeu correspondant
    }

    @Test
    public void testJeuToStringValide() {
        String res = jeu.jeuToString();
        assertNotNull(res); // On vérifie que la String res n'est pas nulle
        assertTrue(res.contains("Voici le descriptif")); // Il y a donc du contenu dedans
    }

    @Test
    public void testGetChar() {
        assertEquals(Labyrinthe.MUR, jeu.getChar(0, 0)); // On test chacun des elements pour être sur que les méthodes ajoute correctement les bons caractères
        assertEquals(Labyrinthe.PJ, jeu.getChar(0, 1));
        assertEquals(Labyrinthe.CAISSE, jeu.getChar(1, 1));
        assertEquals(Labyrinthe.DEPOT, jeu.getChar(2, 1));
        assertEquals(Labyrinthe.VIDE, jeu.getChar(1, 2));
    }

    @Test
    public void testGetSuivant() {
        int[] suivant = jeu.getSuivant(1, 1, Jeu.DROITE); // On test la méthode getSuivant
        assertArrayEquals(new int[]{2, 1}, suivant); // On vérifie qu'on a bien la position suivante selon la direction du déplacement
    }

    @Test
    public void testDeplacerPersoVersVide() throws ActionInconnueException {
        jeu.deplacerPerso(Jeu.BAS); // On test un déplacement du joueur
        assertEquals(0, jeu.getPerso().getX()); // On verifie les nouvelles coordonnees
        assertEquals(2, jeu.getPerso().getY());
    }

    @Test
    public void testDeplacerPersoPousseCaisse() throws ActionInconnueException {
        jeu.deplacerPerso(Jeu.DROITE); // On deplace le joueur vers la caisse en (1,1)
        // la caisse a été poussée
        assertEquals(2, jeu.getCaisses().getElement(0).getX()); // On verifie que la caisse s'est bien decalee
        assertEquals(1, jeu.getCaisses().getElement(0).getY());
        // le perso a pris l'ancienne place de la caisse
        assertEquals(1, jeu.getPerso().getX()); // On verifie que le personnage s'est bien decale
        assertEquals(1, jeu.getPerso().getY());
    }

    @Test
    public void testDeplacerPersoMur() throws ActionInconnueException {
        jeu.deplacerPerso(Jeu.HAUT); // On deplace le personnage vers un mur

        assertEquals(0, jeu.getPerso().getX()); // On verifie que le peronnage ne s'est pas deplace
        assertEquals(1, jeu.getPerso().getY());
    }

    @Test
    public void testDeplacerPersoActionInvalide() { // On verifie une action invalide
        assertThrows(ActionInconnueException.class, () -> jeu.deplacerPerso("x"));
    }

    @Test
    public void testEtreFiniFalse() { // On verifie si le jeu est fini
        assertFalse(jeu.etreFini()); // Nous n'avons pas pousse la caisse (1,1) sur le depot en (2,1)
    }

    @Test
    public void testEtreFiniTrue() throws ActionInconnueException {
        jeu.deplacerPerso(Jeu.DROITE); // pousse la caisse sur le dépôt
        assertTrue(jeu.etreFini()); // Le jeu est fini
    }
}