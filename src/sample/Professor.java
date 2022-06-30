package sample;

import java.util.ArrayList;

public class Professor extends User {
    //static variable for generating id's automatically

    static int noOfProfessors = 1000;
    private Speciality speciality;
    ArrayList<String> appointment ;

    Professor( String name,String phoneNo, String address,Speciality speciality, ArrayList<String> appointment ){
        super(String.valueOf(noOfProfessors ++),name, address, phoneNo );

        this.speciality = speciality;
        this.appointment = appointment;

    }
    //Recently Added
    Professor( String name,String phoneNo, String address,Speciality speciality){
        super(String.valueOf(noOfProfessors ++),name, address, phoneNo );

        this.speciality = speciality;
        this.appointment = new ArrayList<>();

    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }
}