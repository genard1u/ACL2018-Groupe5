/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ul.acl.modele;

/**
 *
 * @author walidone
 */

public class Mure extends Statique{
public static final String MURETYPE="MUR";  
    public Mure(int posX, int posY) {
        super(posX, posY,MURETYPE);
    }

    @Override
    public String getAffchage() {
    return "[ ]";
    }
    
}
