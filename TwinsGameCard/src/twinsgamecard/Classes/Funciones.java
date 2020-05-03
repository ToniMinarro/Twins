package twinsgamecard.Classes;

import java.util.ArrayList;

/**
 * @author ToniMinarro (antonio_jose91@hotmail.es)
 */

public class Funciones {
    
    public static ArrayList<Integer> dameDivisoresIntermedios(int n) {
        ArrayList<Integer> d = new ArrayList<>();
        for(int i=1; i<=n ; i++) {
            if((n%i==0) &&(i>4&&i<10)) { d.add(i);}
        }
        return d;
    }  
}