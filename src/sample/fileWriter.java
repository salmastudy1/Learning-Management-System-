package sample;

import java.io.File;
import java.io.PrintWriter;

public class fileWriter {
    static PrintWriter write ;
    static void openFile(){
        try{
            File file = new File("Data.txt");

            write = new PrintWriter(file);

        }
        catch (Exception e){
            System.out.println("File not Found! {while writing}");

        }

    }

    static void writeFile(){
        for (Professor p : AdminAccountController.arrSProfessor) {
            write.print("Professor ");
            write.print(p.getId()+" ");
            write.print(p.getName()+" ");
            write.print(p.getPhoneNo()+" ");
            write.print(p.getAddress()+" ");
            write.print(p.getSpeciality()+" ");
            int sz = p.appointment.size();
            for (int i = 0; i < sz ; i++) {
                write.print(p.appointment.get(i)+" ");
            }
            write.println("/");
        }
        for (Mainstream m : AdminAccountController.arrMainstream) {
            write.print("Mainstream ");
            write.print(m.getId()+" ");
            write.print(m.getName()+" ");
            write.print(m.getPhoneNo()+" ");
            write.print(m.getAddress()+" ");
            write.print(m.getGender()+" ");
            write.print(m.getPaymentAmount()+" ");
            write.print(m.getMajor()+" ");
            write.println(m.getLevel());
        }
        for (Credit c : AdminAccountController.arrCredit) {
            write.print("Credit ");
            write.print(c.getId()+" ");
            write.print(c.getName()+" ");
            write.print(c.getPhoneNo()+" ");
            write.print(c.getAddress()+" ");
            write.print(c.getGender()+" ");
            write.print(c.getPaymentAmount()+" ");
            write.print(c.getMajor()+" ");
            write.print(c.getLevel()+" ");
            write.println(c.getGPA());

        }
    }
    static void closeFile(){
        write.close();
    }
}
