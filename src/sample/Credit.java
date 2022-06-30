package sample;

public class Credit extends Student {
    private String GPA;

    //static variable for generating id's automatically
    static int noOfCredit = 550;

    Credit( String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level, String GPA ){

        super(String.valueOf(noOfCredit ++), name , address, phoneNo, gender, paymentAmount,major,level);

        this.GPA= GPA;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }
}