package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.LinkedList;

public interface Algorithme {
    // Touver le plus court chemin de point (x,y) vers (Hx,Hy) , type : peut traverser les mur ou non
    LinkedList<Cmd> getChemin(Plateau plateau, int Hx, int Hy, int x, int y, String type);
}
