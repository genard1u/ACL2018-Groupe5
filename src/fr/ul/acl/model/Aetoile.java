package fr.ul.acl.model;

import fr.ul.acl.engine.Cmd;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Aetoile implements Algorithme {
    private static Algorithme aetoile;
    /*recuperation d'une instence de Aetoile */
    public static Algorithme getInstence(){
        if (aetoile==null)aetoile=new Aetoile();
        return aetoile;
    }
    private Aetoile(){

    };
    private static Map<String,Integer> open;
    private static Map<String,Integer> close;
    private static Map<String,String>pred;

    /*calculer le plus court chemin */
    @Override
    public  LinkedList<Cmd>getChemin(Plateau plateau ,int Hx,int Hy,int x,int y,String type){
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
    /*calculer heuristique distance manhattan */
    private static int geth(int Hx,int Hy,String cle ){
        String[] pos = cle.split(",");
        int x=Integer.valueOf(pos[0]);
        int y=Integer.valueOf(pos[1]);
        return Math.abs(Hx-x)+Math.abs(Hy-y);
    }
    /*selectionner le prochain sommet a visiter*/
    private static String getTop(Map<String,Integer> open){
        String top="";
        int min=1000000000;
        for(Map.Entry<String, Integer> elm :open.entrySet()) {
           if(elm.getValue()<min){
               min=elm.getValue();
               top=elm.getKey();
           }
        }
        return top;
    }
    /*selectionner les successeur d'un sommet*/
    private static LinkedList<String>getSecc(String key,Plateau plateau,String type){
        LinkedList<String> secc=new LinkedList<>();
        String[] pos = key.split(",");
        int x=Integer.valueOf(pos[0]);
        int y=Integer.valueOf(pos[1]);
        if(plateau.isAccessible(x+1,y)||type==Fantome.FANTOME)secc.add(String.valueOf(x+1)+','+String.valueOf(y));
        if(plateau.isAccessible(x-1,y)||type==Fantome.FANTOME)secc.add(String.valueOf(x-1)+','+String.valueOf(y));
        if(plateau.isAccessible(x,y+1)||type==Fantome.FANTOME)secc.add(String.valueOf(x)+','+String.valueOf(y+1));
        if(plateau.isAccessible(x,y-1)||type==Fantome.FANTOME)secc.add(String.valueOf(x)+','+String.valueOf(y-1));
        return secc;
    }
    /*selectionner les successeur qui peut amelioré le chemin */
    private static LinkedList<String>filtreSecc(int cout,LinkedList<String> secc,int Hx,int Hy){
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
    /*convertire un arc une Commande*/
    private static Cmd move(String p1,String p2){
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
    /*convertire un chemin a une suit de commande */
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
