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

    public Element getElement(int pos) {
        return this.liste.get(pos);
    }

    public int getTaille() {
        return this.liste.size();
    }
}
