package com.agunga.cis;

/**
 * Created by agunga on 1/18/17.
 */
public class Nurse extends Employee {



    @Override
    void work() {
        dispatchDrugs();
    }

    public void dispatchDrugs(){
        Patient patient = new Patient("4");
        String output = patient.getNationalId()+" "+patient.getDrugs();
        System.out.println(output);
    }
}
