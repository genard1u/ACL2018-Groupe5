package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.LinkedList;

public interface Algorithme {
    LinkedList<Cmd> getChemin(Plateau plateau, int Hx, int Hy, int x, int y, String type);
}
