/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 * @author Juan Pablo Daza Pereira
 * @author Nicolas Bernal Fuquene
 */
public class CountThreadsMain {

    public static void main(String a[]){
        CountThread intervalo1 = new CountThread(0,99);
        CountThread intevalo2 = new CountThread(99,199);
        CountThread intervalo3 = new CountThread(200,299);

        intervalo1.run();
        intevalo2.run();
        intervalo3.run();
    }
    
}
