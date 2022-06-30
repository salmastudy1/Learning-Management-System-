package sample;

public abstract class Student extends User {
    private String gender;
    private String paymentAmount;
    private String major;
    private String level;


    public Student(String id, String name, String address, String phoneNo, String gender, String paymentAmount, String major, String level) {
        super(id, name, address, phoneNo);
        this.gender = gender;
        this.paymentAmount =paymentAmount;
        this.major = major;
        this.level = level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }



}