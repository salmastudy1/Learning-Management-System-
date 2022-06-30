package sample;

// This class performs most of the logic behind the program
import java.util.ArrayList;
import java.util.Iterator;
public class AdminAccountController {
    static ArrayList<Professor> arrSProfessor = new ArrayList<>();
    static ArrayList<Credit> arrCredit = new ArrayList<>();
    static ArrayList<Mainstream> arrMainstream = new ArrayList<>();


    static void addProfessor( String name,String phoneNo, String address,Speciality speciality, ArrayList<String> appointment){
        arrSProfessor.add(new Professor( name, phoneNo, address, speciality,appointment));
    }

    static void addProfessor( String name,String phoneNo, String address,Speciality speciality){
        arrSProfessor.add(new Professor( name, phoneNo, address, speciality));
    }
    static void addMainstream( String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level ){
        arrMainstream.add(new Mainstream( name, phoneNo, address, gender, paymentAmount, major, level));
    }
    static void addCredit( String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level, String GPA ){
        arrCredit.add(new Credit( name, phoneNo, address, gender, paymentAmount, major, level, GPA));
    }
    static void editProfessor(String id, String name,String phoneNo, String address,Speciality speciality){
        //Note that ID can never change it's the means to recognize the object
        for (Professor p : arrSProfessor) {
            if( id.compareTo(p.getId()) == 0){
                p.setName(name);
                p.setPhoneNo(phoneNo);
                p.setAddress( address);
                p.setSpeciality(speciality);
                break;
            }
        }

    }
    static void addAppointment(String id , String appointment){
        for (Professor p : arrSProfessor) {
            if( id.compareTo(p.getId()) == 0){
                p.appointment.add(appointment);
                break;
            }
        }
    }
    static void editMainstream(String id, String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level ){
        //Note that ID can never change it's the means to recognize the object
        for (Mainstream m : arrMainstream) {
            if( id.compareTo(m.getId()) == 0){
                m.setName(name);
                m.setPhoneNo(phoneNo);
                m.setAddress(address);
                m.setGender(gender);
                m.setPaymentAmount(paymentAmount);
                m.setMajor(major);
                m.setLevel(level);
                break;
            }
        }

    }
    static void editCredit(String id, String name,String phoneNo, String address,String gender, String paymentAmount, String major,String level,String GPA ){
        //Note that ID can never change it's the means to recognize the object
        for (Credit c : arrCredit) {
            if( id.compareTo(c.getId()) == 0){
                c.setName(name);
                c.setPhoneNo(phoneNo);
                c.setAddress(address);
                c.setGender(gender);
                c.setPaymentAmount(paymentAmount);
                c.setMajor(major);
                c.setLevel(level);
                c.setGPA(GPA);
                break;
            }
        }

    }
    static void displayProfessor(){
        if(arrSProfessor.size() != 0){
            for (Professor p : arrSProfessor) {
                System.out.println("------------------------------Professor ----------------------------");
                System.out.println("My id: " + p.getId());
                System.out.println("My name: " + p.getName());
                System.out.println("My phoneNo: " + p.getPhoneNo());
                System.out.println("My address: " + p.getAddress());
                System.out.println("My speciality: " + p.getSpeciality());
                int sz = p.appointment.size();
                for (int i = 0; i < sz ; i++) {
                    System.out.println("Appointment " + (i + 1) + " : " + p.appointment.get(i));
                }
            }
        }
        else{
            System.out.println("Professors' Array is Empty!");
        }
    }
    static void displayMainstream(){
        if(arrMainstream.size() != 0){
            for (Mainstream m : arrMainstream) {
                System.out.println("--------------------------Mainstream student ----------------------------");
                System.out.println("My id: " + m.getId());
                System.out.println("My name: " + m.getName());
                System.out.println("My phoneNo: " + m.getPhoneNo());
                System.out.println("My address: " + m.getAddress());
                System.out.println("My gender: " + m.getGender());
                System.out.println("My payment Amount: " + m.getPaymentAmount());
                System.out.println("My major: "+ m.getMajor());
                System.out.println("My level: "+ m.getLevel());
            }
        }
        else{
            System.out.println("Mainstreams' Array is Empty!");
        }

    }
    static void displayCredit(){
        if(arrCredit.size() != 0){
            for (Credit c : arrCredit) {
                System.out.println("--------------------------Credit student ----------------------------");
                System.out.println("My id: " + c.getId());
                System.out.println("My name: " + c.getName());
                System.out.println("My phoneNo: " + c.getPhoneNo());
                System.out.println("My address: " + c.getAddress());
                System.out.println("My gender: " + c.getGender());
                System.out.println("My payment Amount: " + c.getPaymentAmount());
                System.out.println("My major: "+ c.getMajor());
                System.out.println("My level: "+ c.getLevel());
            }
        }
        else{
            System.out.println("Credit students' Array is Empty!");
        }

    }

    static ArrayList<Professor> getSArrProfessor(Speciality speciality){
        ArrayList<Professor> arrProfessor= new ArrayList<>();
        for (Professor p : arrSProfessor) {
         //   System.out.println(p.getSpeciality()+" ");
           // System.out.println(speciality+"input");
            if( p.getSpeciality() == speciality){
                arrProfessor.add(p);
            }
        }
        return arrProfessor;
    }
    //test this method
    static Professor getProfessor(String id){
        Professor Prof = null;
        for (Professor p : arrSProfessor) {
            if( p.getId() == id){
                Prof = p;
            }
        }
        return Prof;
    }
    static Professor getBY_NameProfessor(String name){
        Professor Prof = null;
        for (Professor p : arrSProfessor) {
            if( name.equals(p.getName())){
                Prof = p;
            }
        }
        return Prof;
    }
    static Student getStudent(String id){
        Student s = null;
        for (Mainstream m : arrMainstream) {
            if( m.getId() == id){
                s = m;
            }
        }
        for (Credit c : arrCredit) {
            if( c.getId() == id){
                s = c;
            }
        }
        return s;
    }

    static void displayProfessor( ArrayList<Professor> arrProfessor ){
        if(arrProfessor.size() != 0){
            for (Professor p :arrProfessor) {
                System.out.println("------------------Professor "+ p.getSpeciality() +" ----------------------");
                System.out.println("My id: " + p.getId());
                System.out.println("My name: " + p.getName());
                System.out.println("My phoneNo: " + p.getPhoneNo());
                System.out.println("My address: " + p.getAddress());
                System.out.println("My speciality: " + p.getSpeciality());
                int sz = p.appointment.size();
                for (int i = 0; i < sz ; i++) {
                    System.out.println("Appointment " + (i + 1) + " : " + p.appointment.get(i));
                }
            }
        }
        else{
            System.out.println("Professors' Array is Empty!");
        }
    }

    /* static public void deleteProfessor(String id){
        Iterator<Professor> itr = arrProfessor.iterator();
            while(itr.hasNext()){
             Professor p = itr.next();
                  if(p.getId().equals(id)) {
                          itr.remove();
                          break;
                  }   }
     }*/
    static public void deleteProfessor(String id){
        for (Professor p : arrSProfessor) {
            if( p.getId() == id){
                arrSProfessor.remove(p);
                break;
            }
        }
    }
    static public void deleteMainstream(String id){
        Iterator<Mainstream> itr = arrMainstream.iterator();
        while(itr.hasNext()){
            Mainstream m = itr.next();
            if(m.getId().equals(id)){
                itr.remove();
                break;
            }
        }
    }

    static public void deleteCredit(String id){
        Iterator<Credit> itr = arrCredit.iterator();
        while(itr.hasNext()){
            Credit c = itr.next();
            if(c.getId().equals(id)){
                itr.remove();
                break;
            }
        }
    }
}