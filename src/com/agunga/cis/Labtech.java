package com.agunga.cis;

/**
 * Created by agunga on 1/18/17.
 */
public class Labtech extends Employee {

    public void recordTestResults(){
        Patient patient = new Patient();
//        patient.setNationalId();
        String output = ""+patient.getNationalId()+" "+patient.getBlood_type()+" "+patient.getWeight()+" ";
        System.out.println(output);
    }

    @Override
    void work() {

    }

}
