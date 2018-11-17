package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.LinkedList;

public interface Algorithme {
    /**
     *  Touver le plus court chemin de point (x,y) vers (Hx,Hy) , type : peut traverser les mur ou non
     * calculer le plus court chemin
     * @param plateau le plateu correspandent au graphe
     * @param Hx position x de destination
     * @param Hy position y de destination
     * @param x position x de source
     * @param y position y de source
     * @param type type de monstre
     * @return liste commande
     */
    LinkedList<Cmd> getChemin(Plateau plateau, int Hx, int Hy, int x, int y, String type);
}