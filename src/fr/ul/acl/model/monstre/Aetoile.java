package fr.ul.acl.model.monstre;

import fr.ul.acl.engine.Cmd;
import fr.ul.acl.model.Plateau;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Aetoile implements Algorithme {
	
    private static Algorithme aetoile;
    
    /**
     * recuperation d'une instence de Aetoile
     * @return instance de Aetoile
     */
    public static Algorithme getInstance() {
        if (aetoile==null)aetoile=new Aetoile();
        return aetoile;
    }
    
    private Aetoile() {}
    
    private static Map<String,Integer> open;
    private static Map<String,Integer> close;
    private static Map<String,String> pred;

    /**
     * calculer le plus court chemin
     * @param plateau le plateu correspandent au graphe
     * @param Hx position x de destination
     * @param Hy position y de destination
     * @param x position x de source
     * @param y position y de source
     * @param type type de monstre
     * @return liste commande
     */
    @Override
    public  LinkedList<Cmd>getChemin(Plateau plateau , int Hx, int Hy, int x, int y, String type){

        if(plateau==null||Hx<0||Hy<0||x<0||y<0||
                Hx>plateau.getLargeur()||x>plateau.getLargeur()
                ||Hy>plateau.getHauteur()||y>plateau.getHauteur())
            throw new IllegalArgumentException();

        open = new HashMap<>();
        close=new HashMap<>();
        pred=new HashMap<>();

        String fin=String.valueOf(Hx)+','+String.valueOf(Hy);
        String deb=String.valueOf(x)+','+String.valueOf(y);
        open.put(deb,0);
        while (!open.isEmpty()&&(!close.containsKey(fin))){
            String top=getTop(open);
            LinkedList<String> secc=getSecc(top,plateau,type);
            secc=filtreSecc(open.get(top),secc,Hx,Hy);
            for(String s :secc){
                open.put(s,open.get(top)+1+geth(Hx,Hy,s));
                pred.put(s,top);
            }
            close.put(top,open.get(top));
            open.remove(top);
        }
        return getChemin(deb,fin);
    }
    /**
     * calculer heuristique distance manhattan
     * @param Hx position x de destination
     * @param Hy position y de destination
     * @param cle le cle de sommet source
     * @return la valeur de L'heuristique
     */
    private static int geth(int Hx,int Hy,String cle ){
        if(Hx<0||Hy<0||cle==null) throw new IllegalArgumentException();
        String[] pos = cle.split(",");
        int x=Integer.valueOf(pos[0]);
        int y=Integer.valueOf(pos[1]);
        return Math.abs(Hx-x)+Math.abs(Hy-y);
    }
    /**
     * selectionner le prochain sommet a visiter
     * @param open la liste des sommet
     * @return cle de prochain sommet a visiter
     */
    private static String getTop(Map<String,Integer> open){
        if(open==null)throw new IllegalArgumentException();
        String top="";
        int min=999999999;
        for(Map.Entry<String, Integer> elm :open.entrySet()) {
            if(elm.getValue()<min){
                min=elm.getValue();
                top=elm.getKey();
            }
        }
        return top;
    }
    /**
     * selectionner les successeur d'un sommet
     * @param key cle de sommet
     * @param plateau le plateu correspandent au graphe
     * @param type le type de monstre
     * @return liste des cle
     */
    private static LinkedList<String>getSecc(String key,Plateau plateau,String type){
        if(key==null || plateau ==null || type==null)throw new IllegalArgumentException();
        LinkedList<String> secc=new LinkedList<>();
        String[] pos = key.split(",");
        int x=Integer.valueOf(pos[0]);
        int y=Integer.valueOf(pos[1]);

        try {

            if (plateau.isAccessible(x + 1, y) || type == Fantome.FANTOME)
                secc.add(String.valueOf(x + 1) + ',' + String.valueOf(y));
            if (plateau.isAccessible(x - 1, y) || type == Fantome.FANTOME)
                secc.add(String.valueOf(x - 1) + ',' + String.valueOf(y));
            if (plateau.isAccessible(x, y + 1) || type == Fantome.FANTOME)
                secc.add(String.valueOf(x) + ',' + String.valueOf(y + 1));
            if (plateau.isAccessible(x, y - 1) || type == Fantome.FANTOME)
                secc.add(String.valueOf(x) + ',' + String.valueOf(y - 1));

        } catch (IllegalArgumentException e) {}

        return secc;
    }
    /**
     selectionner les successeur qui peut amelioré le chemin
     * @param cout le cout de chemin entre la source et le sommet actuel
     * @param secc liste des successeurs de sommet
     * @param Hx posision x de la destination
     * @param Hy posision y de la destination
     * @return liste des cle
     */
    private static LinkedList<String>filtreSecc(int cout,LinkedList<String> secc,int Hx,int Hy){
        if(cout<0||secc==null||Hx<0||Hy<0)throw new IllegalArgumentException();
        LinkedList<String>seccf=new LinkedList<>();
        for (String cle :secc){
            String[] pos = cle.split(",");

            if(close.containsKey(cle)){
                if(close.get(cle)>cout+1+geth(Hx,Hy,cle)){
                    close.remove(cle);
                    pred.remove(cle);
                    seccf.add(cle);
                }
            }else {
                seccf.add(cle);
            }
        }
        return seccf;
    }
    /**
     * convertire un arc une Commande
     * @param p1 premier sommet d'arc
     * @param p2 deuxieme sommet d'arc
     * @return commande correspondante a l'arc
     */
    private static Cmd move(String p1,String p2){
        if(p1==null||p2==null)throw new IllegalArgumentException();
        String[] pos = p1.split(",");
        int x1=Integer.valueOf(pos[0]);
        int y1=Integer.valueOf(pos[1]);
        pos = p2.split(",");
        int x2=Integer.valueOf(pos[0]);
        int y2=Integer.valueOf(pos[1]);
        if(x1+1==x2)return Cmd.RIGHT;
        if(x1-1==x2)return Cmd.LEFT;
        if(y1+1==y2)return Cmd.DOWN;
        if(y1-1==y2)return Cmd.UP;
        return Cmd.IDLE;
    }
    /**
     * convertire un chemin a une suit de commande
     * @param cledeb le cle de sommet source
     * @param clefin le cle de sommet destination
     * @return liste des commandes
     */
    private static LinkedList<Cmd>getChemin(String cledeb,String clefin){
        LinkedList<Cmd>chemin=new LinkedList<>();
        if(pred.containsKey(clefin)) {
            String pr = clefin;
            while (!pr.equals(cledeb)){
                chemin.add(0,move(pred.get(pr),pr));
                pr=pred.get(pr);
            }
        }
        return chemin;
    }
}