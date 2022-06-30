package sample;

public class Mainstream extends Student {
    //static variable for generating id's automatically
    static int noOfMainstream = 200;

    Mainstream( String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level){

        super(String.valueOf(noOfMainstream ++), name , address, phoneNo, gender, paymentAmount,major,level);

    }
}