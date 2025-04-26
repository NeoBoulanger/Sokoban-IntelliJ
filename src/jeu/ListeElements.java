package jeu;

import java.util.*;

public class ListeElements {
    private ArrayList<Element> liste;

    public ListeElements() {
        this.liste = new ArrayList<Element>();
    }

    public void ajouterElement(Element e) {
        this.liste.add(e);
    }
}
