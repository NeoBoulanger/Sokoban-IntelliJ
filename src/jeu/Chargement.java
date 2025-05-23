package jeu;

import java.io.*;
import java.lang.reflect.Array;

public class Chargement {
    private BufferedReader src;
    private String nomFichier;

    public Chargement(String nomF) {
        // Assigner un nom de fichier
        this.nomFichier=nomF;

        // Creer un bufferedReader
        try{ // On gère l'exception FileNotFoundException dans ce bloc try catch
            this.src = new BufferedReader(new FileReader(this.nomFichier));
        }catch(FileNotFoundException e){
            System.out.println("Fichier non trouvé" + e.getMessage()); // On informe que le fichier n'est pas trouvé et on print le message de l'exception
        }
    }

    public static Jeu chargerJeu(String nomFichier) throws FichierIncorrectException{
        // On creer un objet Chargement pour s'adapter à la méthode static et un objet Labyrinthe
        Chargement chargement = new Chargement(nomFichier);
        // On récupere dans une String une ligne a traiter du fichiers texte et un tableau correspondant aux dimensions
        String ligne;
        int[] dimensions = Chargement.getDimensionLaby(nomFichier);
        // grace aux dimensions, on peut creer un objet labyrinthe
        Labyrinthe labyrinthe = new Labyrinthe(dimensions[0], dimensions[1]);
        // On creer une liste d'element de Caisses et Depots et un personnage afin de construire notre jeu
        ListeElements listeDepots = new ListeElements();
        ListeElements listeCaisses = new ListeElements();
        Perso personnage = null;
        // On parcours chacun des char et on determine le type de chacun des char : "MURS", "CAISSE", "PERSO", "DEPOT", "VIDE"
        try{
            for (int i=0; i < dimensions[1];i++) {
                ligne=chargement.src.readLine();
                for (int j = 0; j < dimensions[0]; j++) {
                    char c = (j < ligne.length()) ? ligne.charAt(j) : Labyrinthe.VIDE;
                    switch (c) {
                        case Labyrinthe.MUR:
                            labyrinthe.placerMur(j, i);
                            break;
                        case Labyrinthe.PJ:
                            personnage = new Perso(j, i);
                            break;
                        case Labyrinthe.CAISSE:
                            listeCaisses.ajouterElement(new Caisse(j, i));
                            break;
                        case Labyrinthe.DEPOT:
                            listeDepots.ajouterElement(new Depot(j, i));
                            break;
                        case Labyrinthe.VIDE:
                            break;
                        default:
                            throw new FichierIncorrectException("caractère inconnu <" + j + ">");
                    }
                }
            }
            if(listeCaisses.getTaille() != listeDepots.getTaille()){
                throw new FichierIncorrectException("Nombre de depots incorrect");
            }
            if(personnage!=null) {
                Jeu jeu=new Jeu(personnage, listeCaisses, listeDepots, labyrinthe);
                return jeu;
            }else {
                throw new FichierIncorrectException("personnage incorrect");
            }
        }
        catch(FichierIncorrectException e) {
            System.out.println("Fichier incorrect : " + e.getMessage());
            throw e;
        }
        catch (IOException e) {
            System.out.println("Erreur dans la lecture du fichier : " + e.getMessage());
        }
        throw new FichierIncorrectException("Fichier Incorrect");
    }


    public static int[] getDimensionLaby(String nomFichier) {
        // initialisation d'un tableau de int correspondant à dimension[0] : x et dimension [1] : y
        int[] dimensions = new int[2];
        // Creation d'un objet c pour s'adapter a la methode static
        Chargement c=new Chargement(nomFichier);
        // On va stocker chacune des lignes
        String ligne;
        // On parcours chacune des lignes du fichier text et on va determiner le nombre de ligne
        try{
            while((ligne=c.src.readLine())!= null) {
                dimensions[1]++;
                dimensions[0] = Math.max(dimensions[0], ligne.length());
            }
        }catch(IOException e){
            System.out.println("Erreur lors de la lecture d'une ligne" + e.getMessage());
        }
        return dimensions;
    }

    public BufferedReader getSrc() {
        return src;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setSrc(BufferedReader src) {
        this.src = src;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
    }
}
