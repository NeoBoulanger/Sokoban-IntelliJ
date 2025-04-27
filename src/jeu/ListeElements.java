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

    public Element getElement(int x, int y) {
        for (int i = 0; i < this.liste.size(); i++) {
            if (this.liste.get(i).getX()==x && this.liste.get(i).getY()==y) {
                return this.liste.get(i);
            }
        }
        return null;
    }

    public Element getElement(int indice) {
        return this.liste.get(indice);
    }

    public int getTaille() {
        return this.liste.size();
    }
}
