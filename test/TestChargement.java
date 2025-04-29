import jeu.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestChargement {

    @Test // On va tester le cas où le chargement s'est produit
    public void testChargementOK() throws Exception {
        Jeu jeu = Chargement.chargerJeu("laby/laby_test.txt");
        assertNotNull(jeu.getPerso()); // On verifie si le personnage est present
        assertTrue(jeu.getCaisses().getTaille() == jeu.getDepots().getTaille()); // On verifie si il y a le meme nombre de depot que de caisses
    }

    @Test
    public void testCaractereInconnu() { // On test si un caractere inconnu est detecte
        assertThrows(FichierIncorrectException.class, () -> Chargement.chargerJeu("laby/laby_simple_caractereinconnue.txt")); // On test la leve d'exception
    }

    @Test
    public void testPersonnageInvalide() { // On test si le personnage n'apparait pas dans le labyrinthe
        assertThrows(FichierIncorrectException.class, () -> Chargement.chargerJeu("laby/laby_simple_personnageInvalide.txt"));
    }

    @Test
    public void testErreurCaissesDepot() { // On regarde si il y a le meme nombre de caisse que de depots
        assertThrows(FichierIncorrectException.class, () -> {
            Chargement.chargerJeu("laby/laby_simple_depotsInvalide.txt");
        });
    }

    @Test
    public void testGetDimensionLabyOK() { // On regarde si les dimensions mesuree sont valides
        int[] dimensions = Chargement.getDimensionLaby("laby/laby_simple.txt"); // le labyrinthe simple possede ces dimensions (x : 5, y : 7)
        assertNotNull(dimensions);
        assertEquals(2, dimensions.length);
        assertEquals(7, dimensions[0]); // largeur attendue (colonnes)
        assertEquals(5, dimensions[1]); // longueur attendue (lignes)
    }
}
