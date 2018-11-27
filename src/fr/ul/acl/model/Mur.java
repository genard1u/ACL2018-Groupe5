/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ul.acl.model;

/**
 *
 * @author walidone
 */

public class Mur extends Statique{
public static final String MUR="MUR";
    public Mur(int posX, int posY) {
        super(posX, posY,MUR);
    }

    @Override
    public boolean isMagic() {
        return false;
    }

    @Override
    public String getAffichage() {
       return "[ ]";
    }
}
