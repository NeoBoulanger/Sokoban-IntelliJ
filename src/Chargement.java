import java.io.*;

public class Chargement {
    private BufferedReader src;
    private String nomFichier;

    public Chargement(String nomF) {
        // Assigner un nom de fichier
        this.nomFichier=nomF;

        // Creer un bufferedReader
        this.src = new BufferedReader(new FileReader(this.nomFichier));
    }

    static Jeu chargerJeu(String nomFichier) {
        // On récupere dans un tableau de String chacune des lignes du fichiers texte

        // On parcours chacun des char et on determine le type de chacun des char : "MURS", "CAISSE", "PERSO", "DEPOT", "VIDE"
        // Puis on creer un objet correspondant a chaque fois que l'on tombe sur l'un des elements ci-dessus

        // A la fin de la boucle, on possède, un tableau de lignes avec le labyrinthe, un PERSO, des CAISSES, des DEPOTS, des MURS, des VIDES
   }
}
