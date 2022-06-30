package sample;
//prof
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;

import java.util.ArrayList;
//LIGHTSEAGREEN_DarkCyan
//person -> user // Epatient -> credit // Patient -> student
public class Main extends Application {
    Button doctor;
    Button patient;
    Button appointment;
    BorderPane bp;
    GridPane topMenuGP;


    VBox getSideMenuPane() {
        VBox vb = new VBox();
        vb.setStyle("-fx-background-color: DarkCyan;");
        patient = new Button("Students");
        patient.setStyle("-fx-background-color: DarkCyan;-fx-font-size: 16pt");
        patient.setOnAction(e -> (new PatientButtonFX()).patientButtonAction());
        doctor = new Button("Professors");
        doctor.setStyle("-fx-background-color: DarkCyan;-fx-font-size: 16pt");
        doctor.setOnAction(e -> (new DoctorButtonFX()).doctorButtonAction());
        appointment = new Button("Appointments");
        appointment.setStyle("-fx-background-color: DarkCyan;-fx-font-size: 16pt");
        appointment.setOnAction(e -> (new AppointmentButtonFX()).appointmentButtonAction());

        vb.getChildren().addAll(doctor, patient, appointment);
        return vb;
    }

    GridPane getTopMenuPane(String s) {
        Label where = new Label("Dashboard" + s);
        where.setStyle("-fx-font-size: 12pt");
        ImageView Ihome = new ImageView(new Image("home.png"));
        Ihome.setFitHeight(30);
        Ihome.setFitWidth(30);
        Button Home = new Button("", Ihome);
        Home.setOnAction(e -> getHomeOnAction());
        Home.setStyle("-fx-background-color: DarkCyan");
        topMenuGP = new GridPane();
        topMenuGP.add(Home, 0, 0);
        topMenuGP.add(where, 1, 0);
        topMenuGP.setHgap(120);
        topMenuGP.setPadding(new Insets(10, 20, 0, 20));
        topMenuGP.setStyle("-fx-background-color: DarkCyan");
        return topMenuGP;
    }

    void getHomeOnAction() {
        HBox hb = new HBox();
        ImageView Idoc = new ImageView(new Image("teacher.png"));
        Idoc.setFitHeight(170);
        Idoc.setFitWidth(170);
        ImageView Ipat = new ImageView(new Image("graduated.png"));
        Ipat.setFitHeight(170);
        Ipat.setFitWidth(170);
        ImageView Iap = new ImageView(new Image("Appointment.png"));
        Iap.setFitHeight(170);
        Iap.setFitWidth(170);

        Button bdd = new Button("", Idoc);
        bdd.setOnAction(e -> (new DoctorButtonFX()).doctorButtonAction());
        Button bpa = new Button("", Ipat);
        bpa.setOnAction(e -> (new PatientButtonFX()).patientButtonAction());
        Button ba = new Button("", Iap);
        ba.setOnAction(e -> (new AppointmentButtonFX()).appointmentButtonAction());

        hb.getChildren().addAll(bdd, bpa, ba);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        bp.setCenter(hb);
        bp.setTop(getTopMenuPane(""));
        bp.setLeft(getSideMenuPane());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        bp = new BorderPane();

        getHomeOnAction();
        bp.setTop(getTopMenuPane(""));
        bp.setLeft(getSideMenuPane());
        Scene scene = new Scene(bp, 900, 600);
        primaryStage.setTitle("Learning Management System");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        fileReader r = new fileReader();
        r.openFile();
        r.readFile();
        r.closeFile();
        //System.out.println("UNIT TEST FOR CREDIT STUDENTS ");
        //AdminAccountController.displayCredit();

       // System.out.println("UNIT TEST FOR MAINSTREAM STUDENTS ");
       // AdminAccountController.displayMainstream();

        //System.out.println("UNIT TEST FOR PROFESSORS");
       //AdminAccountController.displayProfessor();

        //System.out.println("UNIT TEST FOR A SPECIFIC SPECIALITY");
        //AdminAccountController.displayProfessor(AdminAccountController.getSArrProfessor(Speciality.HUM));

       launch(args);

        fileWriter.openFile();
        fileWriter.writeFile();
        fileWriter.closeFile();
    }

    class DoctorButtonFX {

        VBox getDoctorPane(String id, String name) {
            HBox paneForEditDeleteButtons = new HBox();
            ImageView Iedit = new ImageView(new Image("edit.png"));
            Iedit.setFitHeight(20);
            Iedit.setFitWidth(20);
            ImageView Idelete = new ImageView("delete.png");
            Idelete.setFitHeight(20);
            Idelete.setFitWidth(20);
            Button edit = new Button("", Iedit);
            Button delete = new Button("", Idelete);
            paneForEditDeleteButtons.getChildren().addAll(edit, delete);
            paneForEditDeleteButtons.setAlignment(Pos.TOP_RIGHT);
            edit.setOnAction(e -> editButtonAction(AdminAccountController.getProfessor(id)));
            delete.setOnAction(e -> deleteButtonAction(AdminAccountController.getProfessor(id)));

            VBox labels = new VBox();
            labels.setSpacing(10);
            labels.setPadding(new Insets(20, 0, 20, 0));
            labels.setStyle("-fx-font-size: 12pt");
            Label ID = new Label("        ID : " + id + "        ");
            //Label Name = new Label("Dr. " + name);
            Button Name = new Button("Dr. " + name);
            Name.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;-fx-font-size: 12;-fx-underline: true;-fx-font-weight: bold;");
            //Color.GAINSBORO
            Name.setOnAction(e -> viewButtonAction(AdminAccountController.getProfessor(id)));
            labels.getChildren().addAll(ID, Name);
            labels.setAlignment(Pos.CENTER);

            HBox image = new HBox();
            image.setPadding(new Insets(20, 20, 0, 20));
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(100);
            iUnknown.setFitHeight(100);
            image.getChildren().add(iUnknown);
            image.setAlignment(Pos.CENTER);

            VBox doctorPane = new VBox();
            doctorPane.getChildren().addAll(paneForEditDeleteButtons, image, labels);
            doctorPane.setStyle("-fx-border-color: DarkCyan; -fx-border-width: 2;");

            return doctorPane;
        }

        FlowPane getDoctorsFlowPane() {
            FlowPane fp = new FlowPane();
            fp.setPadding(new Insets(20, 20, 20, 20));
            fp.setHgap(20);
            fp.setVgap(20);
            for (Professor d : AdminAccountController.arrSProfessor) {

                fp.getChildren().add(getDoctorPane(d.getId(), d.getName()));

            }

            return fp;
        }

        void doctorButtonAction() {
            bp.setLeft(getSideMenuPane());
            doctor.setStyle("-fx-background-color: LIGHTSEAGREEN;-fx-font-size: 16pt");
            ScrollPane sp = new ScrollPane();
            sp.setContent(getDoctorsFlowPane());
            sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
            sp.setFitToWidth(true);
            sp.setPannable(true);
            HBox addDrHB = new HBox();
            ImageView Iadd = new ImageView(new Image("add.png"));
            Iadd.setFitHeight(20);
            Iadd.setFitWidth(20);
            Button addDrButton = new Button("Add Doctor", Iadd);
            addDrButton.setOnAction(e -> addDrButtonAction());
            // addDrButton.setStyle("-fx-background-color: grayRgb");

            addDrButton.setPadding(new Insets(10, 10, 10, 10));
            addDrHB.getChildren().add(addDrButton);
            addDrHB.setPadding(new Insets(0, 15, 0, 0));
            addDrHB.setAlignment(Pos.CENTER_RIGHT);
            VBox allPane = new VBox();
            allPane.getChildren().addAll(addDrHB, sp);
            bp.setCenter(allPane);
            bp.setTop(getTopMenuPane("    \\   Professors"));
        }

        void addDrButtonAction() {
            bp.setCenter(getAddDrPane());
            bp.setTop(getTopMenuPane("    \\   Professors    \\   Add Professor"));
        }

        VBox getAddDrPane() {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + (Professor.noOfProfessors + 1));
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();


            TextField tName = new TextField();
            //tName.setText(d.getName());

            TextField tPhoneNum = new TextField();
            //tPhoneNum.setText(d.getPhoneNo());

            TextField tAddress = new TextField();
            //tAddress.setText(d.getAddress());
            Button add = new Button("Add");
            add.setStyle("-fx-color: DarkCyan");
            ComboBox<Speciality> cbo = new ComboBox<>();
            cbo.getItems().addAll(Speciality.CSE, Speciality.PHM, Speciality.ECE, Speciality.MCT, Speciality.UPL, Speciality.HUM, Speciality.CES);
            // cbo.setStyle("-fx-color: DarkCyan");
            //cbo.setValue(d.getSpeciality());

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Speciality:"), 2, 1);
            infoGrid.add(cbo, 3, 1);
            infoGrid.add(add, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(add, HPos.RIGHT);

            add.setPadding(new Insets(10, 20, 10, 20));
            add.setOnAction(e -> doneAddDoctorButtonAction(tName, tPhoneNum, tAddress, cbo));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }

        void doneAddDoctorButtonAction(TextField tName, TextField tPhoneNum, TextField tAddress, ComboBox<Speciality> cbo) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || cbo.getValue() == null) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                AdminAccountController.addProfessor(tName.getText(), tPhoneNum.getText(), tAddress.getText(), cbo.getValue());
                doctorButtonAction();
            }

        }

        void deleteButtonAction(Professor d) {
            bp.setTop(getTopMenuPane("    \\   Professors    \\   " + d.getName() + "    \\   Delete"));
            Label sure = new Label("Are you sure you want to delete Dr. " + d.getName() + " ?");
            sure.setStyle("-fx-font-size: 16pt");
            Button yes = new Button("Delete");
            yes.setStyle("-fx-font-size: 10pt");
            Button no = new Button("Cancel");
            no.setStyle("-fx-font-size: 10pt");
            HBox buttonsHpane = new HBox(30);
            buttonsHpane.setAlignment(Pos.CENTER);
            buttonsHpane.getChildren().addAll(yes, no);
            VBox pane = new VBox(30);
            pane.getChildren().addAll(sure, buttonsHpane);
            pane.setPadding(new Insets(30, 30, 30, 30));
            Scene deleteScene = new Scene(pane);
            Stage deleteStage = new Stage();
            deleteStage.setTitle("Confirmation");
            deleteStage.setScene(deleteScene);
            deleteStage.show();
            yes.setOnAction(e -> {
                AdminAccountController.deleteProfessor(d.getId());
                deleteStage.close();
                doctorButtonAction();
            });
            no.setOnAction(e -> {
                deleteStage.close();
                doctorButtonAction();
            });

        }

        void editButtonAction(Professor d) {
            bp.setCenter(getEditDoctorPane(d));
            bp.setTop(getTopMenuPane("    \\   Professors    \\   " + d.getName() + "    \\   Edit"));

        }


        VBox getEditDoctorPane(Professor d) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + d.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();


            TextField tName = new TextField();
            tName.setText(d.getName());

            TextField tPhoneNum = new TextField();
            tPhoneNum.setText(d.getPhoneNo());

            TextField tAddress = new TextField();
            tAddress.setText(d.getAddress());
            Button save = new Button("save");
            save.setStyle("-fx-color: DarkCyan");
            ComboBox<Speciality> cbo = new ComboBox<>();
            cbo.getItems().addAll(Speciality.CSE, Speciality.PHM, Speciality.ECE, Speciality.MCT, Speciality.UPL, Speciality.CES, Speciality.HUM);
            // cbo.setStyle("-fx-color: DarkCyan");
            cbo.setValue(d.getSpeciality());

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Speciality:"), 2, 1);
            infoGrid.add(cbo, 3, 1);
            infoGrid.add(save, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(save, HPos.RIGHT);

            save.setPadding(new Insets(10, 20, 10, 20));
            save.setOnAction(e -> saveDoctorButtonAction(d, tName, tPhoneNum, tAddress, cbo));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }

        void saveDoctorButtonAction(Professor d, TextField tName, TextField tPhoneNum, TextField tAddress, ComboBox<Speciality> cbo) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || cbo.getValue() == null) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                AdminAccountController.editProfessor(d.getId(), tName.getText(), tPhoneNum.getText(), tAddress.getText(), cbo.getValue());
                doctorButtonAction();
            }

        }

        void viewButtonAction(Professor d) {
            bp.setCenter(getViewDoctorPane(d));
            bp.setTop(getTopMenuPane("    \\   Professors    \\   " + d.getName() + "    \\   View"));
        }

        VBox getViewDoctorPane(Professor d) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + d.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();

            Button done = new Button("Done");
            done.setStyle("-fx-color: DarkCyan");

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            Label name = new Label("Name:");
            name.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label phoneNum = new Label("Phone Number:");
            phoneNum.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label address = new Label("Address:");
            address.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label speciality = new Label("Speciality:");
            speciality.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            infoGrid.add(name, 0, 0);
            infoGrid.add(new Label(d.getName()), 1, 0);
            infoGrid.add(phoneNum, 0, 1);
            infoGrid.add(new Label(d.getPhoneNo()), 1, 1);
            infoGrid.add(address, 0, 2);
            infoGrid.add(new Label(d.getAddress()), 1, 2);
            infoGrid.add(speciality, 0, 3);
            infoGrid.add(new Label(String.valueOf(d.getSpeciality())), 1, 3);
            infoGrid.add(done, 2, 4);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(done, HPos.RIGHT);

            done.setPadding(new Insets(10, 20, 10, 20));
            done.setOnAction(e -> doctorButtonAction());

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }
    }

    class PatientButtonFX {
        void patientButtonAction() {
            bp.setLeft(getSideMenuPane());
            patient.setStyle("-fx-background-color: LIGHTSEAGREEN;-fx-font-size: 16pt");
            HBox hb = new HBox();
            ImageView In = new ImageView(new Image("mainstream.png"));
            In.setFitHeight(170);
            In.setFitWidth(170);
            ImageView Ie = new ImageView(new Image("Credit.png"));
            Ie.setFitHeight(170);
            Ie.setFitWidth(170);


            Button bn = new Button("MainStream", In);
            bn.setOnAction(e -> patientButtonAction('n'));
            Button be = new Button("Credit", Ie);
            be.setOnAction(e -> patientButtonAction('e'));

            hb.getChildren().addAll(bn, be);
            hb.setAlignment(Pos.CENTER);
            hb.setSpacing(20);
            bp.setCenter(hb);
            bp.setTop(getTopMenuPane("    \\   Student"));

        }

        VBox getPatientPane(String id, String name) {
            HBox paneForEditDeleteButtons = new HBox();
            ImageView Iedit = new ImageView(new Image("edit.png"));
            Iedit.setFitHeight(20);
            Iedit.setFitWidth(20);
            ImageView Idelete = new ImageView("delete.png");
            Idelete.setFitHeight(20);
            Idelete.setFitWidth(20);
            Button edit = new Button("", Iedit);
            Button delete = new Button("", Idelete);
            paneForEditDeleteButtons.getChildren().addAll(edit, delete);
            paneForEditDeleteButtons.setAlignment(Pos.TOP_RIGHT);
            //Potential bug
            edit.setOnAction(e ->{
                if (Integer.valueOf(id) >= 550){
                    // System.out.println(id);
                    editEButtonAction(AdminAccountController.getStudent(id));
                }

                else
                    editNButtonAction(AdminAccountController.getStudent(id));
            });


            delete.setOnAction(e -> deleteButtonAction(AdminAccountController.getStudent(id)));

            VBox labels = new VBox();
            labels.setSpacing(10);
            labels.setPadding(new Insets(20, 0, 20, 0));
            labels.setStyle("-fx-font-size: 12pt");
            Label ID = new Label("        ID : " + id + "        ");
            //Label Name = new Label("Dr. " + name);
            Button Name = new Button(name);
            Name.setStyle("-fx-border-color: transparent;-fx-background-color: transparent;-fx-font-size: 12;-fx-underline: true;-fx-font-weight: bold;");
            //Color.GAINSBORO
            //Name.setOnAction(e -> viewButtonAction(HC.getDoctor(id)) );
            //Potential bug
            Name.setOnAction(e ->{
                if (Integer.valueOf(id) >= 550){
                    //   System.out.println(id);
                    viewEButtonAction((Credit)(AdminAccountController.getStudent(id)));
                }

                else
                    viewNButtonAction((Mainstream)(AdminAccountController.getStudent(id)));
            });
            labels.getChildren().addAll(ID, Name);
            labels.setAlignment(Pos.CENTER);

            HBox image = new HBox();
            image.setPadding(new Insets(20, 20, 0, 20));
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(100);
            iUnknown.setFitHeight(100);
            image.getChildren().add(iUnknown);
            image.setAlignment(Pos.CENTER);

            VBox patientPane = new VBox();
            patientPane.getChildren().addAll(paneForEditDeleteButtons, image, labels);
            patientPane.setStyle("-fx-border-color: DarkCyan; -fx-border-width: 2;");

            return patientPane;
        }

        FlowPane getPatientsFlowPane(char c) {
            FlowPane fp = new FlowPane();
            fp.setPadding(new Insets(20, 20, 20, 20));
            fp.setHgap(20);
            fp.setVgap(20);
            if (c == 'e') {
                for (Credit e : AdminAccountController.arrCredit) {

                    fp.getChildren().add(getPatientPane(e.getId(), e.getName()));

                }
            } else {
                for (Mainstream n : AdminAccountController.arrMainstream) {

                    fp.getChildren().add(getPatientPane(n.getId(), n.getName()));
                }
            }

            return fp;
        }
        void patientButtonAction(char c) {
            bp.setLeft(getSideMenuPane());
            patient.setStyle("-fx-background-color: LIGHTSEAGREEN;-fx-font-size: 16pt");
            ScrollPane sp = new ScrollPane();
            sp.setContent(getPatientsFlowPane(c));
            sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
            sp.setFitToWidth(true);
            sp.setPannable(true);
            HBox addDrHB = new HBox();
            ImageView Iadd = new ImageView(new Image("add.png"));
            Iadd.setFitHeight(20);
            Iadd.setFitWidth(20);
            Button addDrButton;
            if(c=='e')
                addDrButton = new Button("Add " +"Credit", Iadd);
            else
                addDrButton = new Button("Add " +"Mainstream", Iadd);
            // addDrButton.setStyle("-fx-background-color: grayRgb");
            addDrButton.setOnAction(e -> addPatientButtonAction(c));

            addDrButton.setPadding(new Insets(10, 10, 10, 10));
            addDrHB.getChildren().add(addDrButton);
            addDrHB.setPadding(new Insets(0, 15, 0, 0));
            addDrHB.setAlignment(Pos.CENTER_RIGHT);
            VBox allPane = new VBox();
            allPane.getChildren().addAll(addDrHB, sp);
            bp.setCenter(allPane);
            if(c == 'e')
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Credit"));
            else
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Mainstream"));
        }
        void addPatientButtonAction(char c) {
            if(c=='e')
                bp.setCenter(getAddEPane());
            else
                bp.setCenter(getAddNPane());
            if(c=='e')
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Credit    \\   "+"Add"));
            else
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Mainstream    \\   "+"Add"));
        }

        VBox getAddNPane() {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + (Mainstream.noOfMainstream + 1));
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editNPane = new VBox();


            TextField tName = new TextField();
            //tName.setText(d.getName());

            TextField tPhoneNum = new TextField();
            //tPhoneNum.setText(d.getPhoneNo());
            TextField tGender = new TextField();
            TextField tPaymentMethod = new TextField();
            TextField tsymptoms = new TextField();
            TextField tdiagnosis = new TextField();
            TextField tAddress = new TextField();
            //tAddress.setText(d.getAddress());
            Button add = new Button("Add");
            add.setStyle("-fx-color: DarkCyan");

            // cbo.setStyle("-fx-color: DarkCyan");
            //cbo.setValue(d.getSpeciality());

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Payment Amount:"), 0, 2);
            infoGrid.add(tPaymentMethod, 1, 2);
            infoGrid.add(new Label("Major:"), 0,3 );
            infoGrid.add(tsymptoms, 1, 3);

            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Gender:"), 2, 1);
            infoGrid.add(tGender, 3, 1);
            infoGrid.add(new Label("Level:"), 2, 2);
            infoGrid.add(tdiagnosis, 3, 2);
            infoGrid.add(add, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(add, HPos.RIGHT);

            add.setPadding(new Insets(10, 20, 10, 20));
            add.setOnAction(e -> doneAddNButtonAction(tName, tPhoneNum, tAddress,tGender,tPaymentMethod,tsymptoms,tdiagnosis));

            editNPane.setSpacing(0);
            editNPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editNPane;
        }
        void doneAddNButtonAction(TextField tName, TextField tPhoneNum, TextField tAddress, TextField tgender, TextField tpaymentMethod, TextField tsymptoms,TextField tdiagnosis ) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || tgender.getText().compareTo("") == 0 || tpaymentMethod.getText().compareTo("") == 0 || tsymptoms.getText().compareTo("") == 0 || tdiagnosis.getText().compareTo("") == 0  ) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ") || tgender.getText().contains(" ") || tpaymentMethod.getText().contains(" ") || tsymptoms.getText().contains(" ") || tdiagnosis.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                AdminAccountController.addMainstream(tName.getText(),tPhoneNum.getText(), tAddress.getText(),tgender.getText(), tpaymentMethod.getText(),tsymptoms.getText(),tdiagnosis.getText());
                patientButtonAction('n');
            }

        }
        VBox getAddEPane() {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + (Credit.noOfCredit + 1));
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editNPane = new VBox();


            TextField tName = new TextField();
            //tName.setText(d.getName());

            TextField tPhoneNum = new TextField();
            //tPhoneNum.setText(d.getPhoneNo());
            TextField tGender = new TextField();
            TextField tPaymentMethod = new TextField();
            TextField tsymptoms = new TextField();
            TextField tdiagnosis = new TextField();
            TextField tAddress = new TextField();
            TextField tRoomNo = new TextField();
            //tAddress.setText(d.getAddress());
            Button add = new Button("Add");
            add.setStyle("-fx-color: DarkCyan");

            // cbo.setStyle("-fx-color: DarkCyan");
            //cbo.setValue(d.getSpeciality());

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Payment Amount:"), 0, 2);
            infoGrid.add(tPaymentMethod, 1, 2);
            infoGrid.add(new Label("Major:"), 0,3 );
            infoGrid.add(tsymptoms, 1, 3);

            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Gender:"), 2, 1);
            infoGrid.add(tGender, 3, 1);
            infoGrid.add(new Label("Level:"), 2, 2);
            infoGrid.add(tdiagnosis, 3, 2);
            infoGrid.add(new Label("GPA:"), 2, 3);
            infoGrid.add(tRoomNo, 3, 3);
            infoGrid.add(add, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(add, HPos.RIGHT);

            add.setPadding(new Insets(10, 20, 10, 20));
            add.setOnAction(e -> doneAddEButtonAction(tName, tPhoneNum, tAddress,tGender,tPaymentMethod,tsymptoms,tdiagnosis, tRoomNo));

            editNPane.setSpacing(0);
            editNPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editNPane;
        }
        void doneAddEButtonAction(TextField tName, TextField tPhoneNum, TextField tAddress, TextField tgender, TextField tpaymentMethod, TextField tsymptoms,TextField tdiagnosis, TextField troomNo ) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || tgender.getText().compareTo("") == 0 || tpaymentMethod.getText().compareTo("") == 0 || tsymptoms.getText().compareTo("") == 0 || tdiagnosis.getText().compareTo("") == 0  || troomNo.getText().compareTo("") == 0 ) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ") || tgender.getText().contains(" ") || tpaymentMethod.getText().contains(" ") || tsymptoms.getText().contains(" ") || tdiagnosis.getText().contains(" ") || troomNo.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                AdminAccountController.addCredit(tName.getText(),tPhoneNum.getText(), tAddress.getText(),tgender.getText(), tpaymentMethod.getText(),tsymptoms.getText(),tdiagnosis.getText(),troomNo.getText());
                patientButtonAction('e');
            }

        }

        void deleteButtonAction(Student p) {
            if(p instanceof Credit)
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Credit\\   "+ p.getName() + "    \\   Delete"));
            else
                bp.setTop(getTopMenuPane("    \\   Student\\   "+"Mainstream\\   " + p.getName() + "    \\   Delete"));

            Label sure = new Label("Are you sure you want to delete Dr. " + p.getName() + " ?");
            sure.setStyle("-fx-font-size: 16pt");
            Button yes = new Button("Delete");
            yes.setStyle("-fx-font-size: 10pt");
            Button no = new Button("Cancel");
            no.setStyle("-fx-font-size: 10pt");
            HBox buttonsHpane = new HBox(30);
            buttonsHpane.setAlignment(Pos.CENTER);
            buttonsHpane.getChildren().addAll(yes, no);
            VBox pane = new VBox(30);
            pane.getChildren().addAll(sure, buttonsHpane);
            pane.setPadding(new Insets(30, 30, 30, 30));
            Scene deleteScene = new Scene(pane);
            Stage deleteStage = new Stage();
            deleteStage.setTitle("Confirmation");
            deleteStage.setScene(deleteScene);
            deleteStage.show();
            yes.setOnAction(e -> {
                if(p instanceof Mainstream){
                    AdminAccountController.deleteMainstream(p.getId());
                    deleteStage.close();
                    patientButtonAction('n');
                }
                else{
                    AdminAccountController.deleteCredit(p.getId());
                    deleteStage.close();
                    patientButtonAction('e');
                }

            });
            no.setOnAction(e -> {
                if(p instanceof Mainstream){
                    deleteStage.close();
                    patientButtonAction('n');
                }
                else{
                    deleteStage.close();
                    patientButtonAction('e');
                }

            });

        }

        void editNButtonAction(Student p) {
            bp.setCenter(getEditNPatientPane(p));
            bp.setTop(getTopMenuPane("    \\   Student\\   "+"Mainstream\\   "+ p.getName() + "    \\   Edit"));

        }
        VBox getEditNPatientPane(Student p) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + p.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();


            TextField tName = new TextField();
            tName.setText(p.getName());

            TextField tPhoneNum = new TextField();
            tPhoneNum.setText(p.getPhoneNo());

            TextField tAddress = new TextField();
            tAddress.setText(p.getAddress());
            Button save = new Button("save");
            save.setStyle("-fx-color: DarkCyan");

            TextField tGender = new TextField();
            tGender.setText(p.getGender());
            TextField tPaymentMethod = new TextField();
            tPaymentMethod.setText(p.getPaymentAmount());
            TextField tsymptoms = new TextField();
            tsymptoms.setText(p.getMajor());
            TextField tdiagnosis = new TextField();
            //tAddress.setText(d.getAddress());
            tdiagnosis.setText(p.getLevel());
            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Payment Amount:"), 0, 2);
            infoGrid.add(tPaymentMethod, 1, 2);
            infoGrid.add(new Label("Major:"), 0,3 );
            infoGrid.add(tsymptoms, 1, 3);

            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Gender:"), 2, 1);
            infoGrid.add(tGender, 3, 1);
            infoGrid.add(new Label("Level:"), 2, 2);
            infoGrid.add(tdiagnosis, 3, 2);
            infoGrid.add(save, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(save, HPos.RIGHT);

            save.setPadding(new Insets(10, 20, 10, 20));
            save.setOnAction(e -> saveNPatientButtonAction(p, tName, tPhoneNum, tAddress, tGender, tPaymentMethod, tsymptoms, tdiagnosis));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }
        void saveNPatientButtonAction(Student p, TextField tName, TextField tPhoneNum, TextField tAddress, TextField tGender, TextField tPaymentMethod, TextField tSymptoms, TextField tdiagnosis) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || tGender.getText().compareTo("") == 0 || tPaymentMethod.getText().compareTo("") == 0 || tSymptoms.getText().compareTo("") == 0 || tdiagnosis.getText().compareTo("") == 0  ) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ") || tGender.getText().contains(" ") || tPaymentMethod.getText().contains(" ") || tSymptoms.getText().contains(" ") || tdiagnosis.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                AdminAccountController.editMainstream(p.getId(), tName.getText(), tPhoneNum.getText(), tAddress.getText(), tGender.getText(), tPaymentMethod.getText(),tSymptoms.getText(),tdiagnosis.getText());
                patientButtonAction('n');
            }

        }

        void editEButtonAction(Student p) {
            bp.setCenter(getEditEPatientPane(p));
            bp.setTop(getTopMenuPane("    \\   Students\\   "+"Credit\\   "+ p.getName() + "    \\   Edit"));
            ;

        }
        VBox getEditEPatientPane(Student p) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + p.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();


            TextField tName = new TextField();
            tName.setText(p.getName());

            TextField tPhoneNum = new TextField();
            tPhoneNum.setText(p.getPhoneNo());
            TextField tRoomNo = new TextField();
            tRoomNo.setText(((Credit)p).getGPA());

            TextField tAddress = new TextField();
            tAddress.setText(p.getAddress());
            Button save = new Button("save");
            save.setStyle("-fx-color: DarkCyan");

            TextField tGender = new TextField();
            tGender.setText(p.getGender());
            TextField tPaymentMethod = new TextField();
            tPaymentMethod.setText(p.getPaymentAmount());
            TextField tsymptoms = new TextField();
            tsymptoms.setText(p.getMajor());
            TextField tdiagnosis = new TextField();
            tdiagnosis.setText(p.getLevel());
            //tAddress.setText(d.getAddress());
            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(20);
            infoGrid.setHgap(20);
            infoGrid.add(new Label("Name:"), 0, 0);
            infoGrid.add(tName, 1, 0);
            infoGrid.add(new Label("Phone Number:"), 0, 1);
            infoGrid.add(tPhoneNum, 1, 1);
            infoGrid.add(new Label("Payment Amount:"), 0, 2);
            infoGrid.add(tPaymentMethod, 1, 2);
            infoGrid.add(new Label("Major:"), 0,3 );
            infoGrid.add(tsymptoms, 1, 3);

            infoGrid.add(new Label("Address:"), 2, 0);
            infoGrid.add(tAddress, 3, 0);
            infoGrid.add(new Label("Gender:"), 2, 1);
            infoGrid.add(tGender, 3, 1);
            infoGrid.add(new Label("Level:"), 2, 2);
            infoGrid.add(new Label("GPA:"), 2, 3);
            infoGrid.add(tRoomNo, 3, 3);
            infoGrid.add(tdiagnosis, 3, 2);
            infoGrid.add(save, 4, 2);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(save, HPos.RIGHT);

            save.setPadding(new Insets(10, 20, 10, 20));
            save.setOnAction(e -> saveEPatientButtonAction(p, tName, tPhoneNum, tAddress, tGender, tPaymentMethod, tsymptoms, tdiagnosis, tRoomNo));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }
        void saveEPatientButtonAction(Student s, TextField tName, TextField tPhoneNum, TextField tAddress, TextField tGender, TextField tPaymentAmount, TextField tMajor, TextField tLevel, TextField tGPA) {
            if ((tName.getText().compareTo("")) == 0 || tPhoneNum.getText().compareTo("") == 0 || tAddress.getText().compareTo("") == 0 || tGender.getText().compareTo("") == 0 || tPaymentAmount.getText().compareTo("") == 0 || tMajor.getText().compareTo("") == 0 || tLevel.getText().compareTo("") == 0  || tGPA.getText().compareTo("") == 0 ) {
                Label completeInfo = new Label("Please fill in ALL information ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (tName.getText().contains(" ") || tPhoneNum.getText().contains(" ") || tAddress.getText().contains(" ") || tGender.getText().contains(" ") || tPaymentAmount.getText().contains(" ") || tMajor.getText().contains(" ") || tLevel.getText().contains(" ") || tGPA.getText().contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(c -> noSpacesStage.close());
            } else {
                AdminAccountController.editCredit(s.getId(), tName.getText(), tPhoneNum.getText(), tAddress.getText(), tGender.getText(), tPaymentAmount.getText(),tMajor.getText(),tLevel.getText(),tGPA.getText());
                patientButtonAction('c');
            }

        }


        void viewNButtonAction(Mainstream n) {
            bp.setCenter(getViewNPane(n));
            bp.setTop(getTopMenuPane("    \\   Student  \\   "+"Mainstream  \\   "+ n.getName() + "    \\   View"));
        }
        VBox getViewNPane(Mainstream n) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + n.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();

            Button done = new Button("Done");
            done.setStyle("-fx-color: DarkCyan");
            Button calcPay = new Button("Calculate Payment");
            done.setStyle("-fx-color: DarkCyan");

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(10);
            infoGrid.setHgap(20);
            Label name = new Label("Name:");
            name.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label phoneNum = new Label("Phone Number:");
            phoneNum.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label address = new Label("Address:");
            address.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label Gender = new Label("Gender:");
            Gender.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label paymentMethod = new Label("Payment Amount:");
            paymentMethod.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label diagnosis = new Label("Level:");
            diagnosis.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label symptoms = new Label("Major:");
            symptoms.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            infoGrid.add(name, 0, 0);
            infoGrid.add(new Label(n.getName()), 1, 0);
            infoGrid.add(phoneNum, 0, 1);
            infoGrid.add(new Label(n.getPhoneNo()), 1, 1);
            infoGrid.add(address, 0, 2);
            infoGrid.add(new Label(n.getAddress()), 1, 2);
            infoGrid.add(Gender, 0, 3);
            infoGrid.add(new Label(String.valueOf(n.getGender())), 1, 3);

            infoGrid.add(symptoms, 0, 5);
            infoGrid.add(new Label(String.valueOf(n.getMajor())), 1, 5);
            infoGrid.add(diagnosis, 0, 6);
            infoGrid.add(new Label(String.valueOf(n.getLevel())), 1, 6);
            infoGrid.add(calcPay, 2, 4);
            infoGrid.add(done, 2, 6);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(done, HPos.RIGHT);

            calcPay.setOnAction(e ->{
                infoGrid.add(new Label(String.valueOf(n.getPaymentAmount())), 1, 4);
            } );
            infoGrid.add(paymentMethod, 0, 4);
            infoGrid.add(new Text(""), 1, 4);

            done.setPadding(new Insets(10, 20, 10, 20));
            done.setOnAction(e -> patientButtonAction('n'));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }

        void viewEButtonAction(Credit e) {
            bp.setCenter(getViewEPane(e));
            bp.setTop(getTopMenuPane("    \\   Student  \\   "+"Credit  \\   "+ e.getName() + "    \\   View"));
        }
        VBox getViewEPane(Credit ei) {
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image("unknownCircle.png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("ID:    " + ei.getId());
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox editDoctorPane = new VBox();

            Button done = new Button("Done");
            done.setStyle("-fx-color: DarkCyan");
            Button calcPay = new Button("Calculate Payment");

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(5);
            infoGrid.setHgap(20);
            Label name = new Label("Name:");
            name.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label phoneNum = new Label("Phone Number:");
            phoneNum.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label address = new Label("Address:");
            address.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label Gender = new Label("Gender:");
            Gender.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label paymentMethod = new Label("Payment Amount:");
            paymentMethod.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label diagnosis = new Label("Level:");
            diagnosis.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label symptoms = new Label("Major:");
            symptoms.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label roomNo = new Label("GPA:");
            roomNo.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            infoGrid.add(name, 0, 0);
            infoGrid.add(new Label(ei.getName()), 1, 0);
            infoGrid.add(phoneNum, 0, 1);
            infoGrid.add(new Label(ei.getPhoneNo()), 1, 1);
            infoGrid.add(address, 0, 2);
            infoGrid.add(new Label(ei.getAddress()), 1, 2);
            infoGrid.add(Gender, 0, 3);
            infoGrid.add(new Label(String.valueOf(ei.getGender())), 1, 3);
            infoGrid.add(paymentMethod, 0, 4);
            infoGrid.add(new Label(""), 1, 4);
            infoGrid.add(symptoms, 0, 5);
            infoGrid.add(new Label(String.valueOf(ei.getMajor())), 1, 5);
            infoGrid.add(diagnosis, 0, 6);
            infoGrid.add(new Label(String.valueOf(ei.getLevel())), 1, 6);
            infoGrid.add(roomNo, 0, 7);
            infoGrid.add(new Label(String.valueOf(ei.getGPA())), 1, 7);
            infoGrid.add(calcPay, 2, 4);
            infoGrid.add(done, 2, 7);
            infoGrid.setAlignment(Pos.CENTER);
            GridPane.setHalignment(done, HPos.RIGHT);

            calcPay.setOnAction(e ->{infoGrid.add(new Label(String.valueOf(ei.getPaymentAmount())), 1, 4);});
            done.setPadding(new Insets(10, 20, 10, 20));
            done.setOnAction(e -> patientButtonAction('e'));

            editDoctorPane.setSpacing(0);
            editDoctorPane.getChildren().addAll(imageIdVPane, infoGrid);
            return editDoctorPane;
        }


    }

    class AppointmentButtonFX{
        void appointmentButtonAction() {
            bp.setLeft(getSideMenuPane());
            appointment.setStyle("-fx-background-color: LIGHTSEAGREEN;-fx-font-size: 16pt");

            ImageView Identist = new ImageView(new Image("CSE.png"));
            Identist.setFitHeight(100);
            Identist.setFitWidth(100);
            ImageView Idermatologist = new ImageView(new Image("PHM.png"));
            Idermatologist.setFitHeight(100);
            Idermatologist.setFitWidth(100);
            ImageView IENT = new ImageView(new Image("ECE.png"));
            IENT.setFitHeight(100);
            IENT.setFitWidth(100);
            ImageView IGeneralist = new ImageView(new Image("MCT.png"));
            IGeneralist.setFitHeight(100);
            IGeneralist.setFitWidth(100);
            ImageView ISurgeon = new ImageView(new Image("UPL.png"));
            ISurgeon.setFitHeight(100);
            ISurgeon.setFitWidth(100);
            ImageView Iopthamologist = new ImageView(new Image("CES.png"));
            Iopthamologist.setFitHeight(100);
            Iopthamologist.setFitWidth(100);
            ImageView Ineurologist = new ImageView(new Image("HUM.png"));
            Ineurologist.setFitHeight(100);
            Ineurologist.setFitWidth(100);

            Label choose = new Label("                          Please, Choose a Speciality ");
            choose.setStyle("-fx-font-size: 18pt");
            Button bdentist = new Button("CSE", Identist);
            bdentist.setOnAction(e -> specialityButtonAction(Speciality.CSE));
            Button bdermatologist = new Button("PHM", Idermatologist);
            bdermatologist.setOnAction(e -> specialityButtonAction(Speciality.PHM));
            Button bENT = new Button("ECE", IENT);
            bENT.setOnAction(e -> specialityButtonAction(Speciality.ECE));
            Button bGeneralist = new Button("MCT", IGeneralist);
            bGeneralist.setOnAction(e -> specialityButtonAction(Speciality.MCT));
            Button bSurgeon = new Button("UPL", ISurgeon);
            bSurgeon.setOnAction(e -> specialityButtonAction(Speciality.UPL));
            Button bOphthalmologist = new Button("CES", Iopthamologist);
            bOphthalmologist.setOnAction(e -> specialityButtonAction(Speciality.CES));
            Button bNeurologist = new Button("HUM", Ineurologist);
            bNeurologist.setOnAction(e -> specialityButtonAction(Speciality.HUM));

            GridPane infoGrid = new GridPane();
            infoGrid.setVgap(5);
            infoGrid.setHgap(5);
            infoGrid.add(bdentist, 0, 0);
            infoGrid.add(bdermatologist, 1, 0);
            infoGrid.add(bENT, 2, 0);
            infoGrid.add(bGeneralist, 0, 1);
            infoGrid.add(bSurgeon, 1, 1);
            infoGrid.add(bOphthalmologist, 2, 1);
            infoGrid.add(bNeurologist, 0, 2);
            HBox hb = new HBox();
            hb.getChildren().addAll(choose);
            hb.setPadding(new Insets(50,50,50,50));
            VBox vb = new VBox();
            vb.getChildren().addAll(hb, infoGrid);
            infoGrid.setAlignment(Pos.CENTER);
            // GridPane.setHalignment(save, HPos.RIGHT);

            bp.setCenter(vb);
            bp.setTop(getTopMenuPane("    \\   Appointments"));
        }

        void specialityButtonAction(Speciality s){
            ArrayList<Professor> arrSpeciality =  AdminAccountController.getSArrProfessor(s);
            GridPane gp = new GridPane();
            gp.setVgap(20);
            gp.setHgap(20);
            final ToggleGroup group = new ToggleGroup();
            RadioButton rb1 = new RadioButton("Home");
            rb1.setToggleGroup(group);
            //gp.add(new Label("Professor's Name"), 0 ,0);
            //gp.add(new Label("Professor's Address"), 1, 0);
            for (Professor d : arrSpeciality) {
                //new RadioButton("Dr. " + d.getName()).setToggleGroup(group);
                HBox h = new HBox();
                RadioButton r = new RadioButton("Dr. " + d.getName());
                r.setToggleGroup(group);
                gp.add(r, 0 ,gp.getRowCount() );
                gp.add(new Label(d.getAddress()), 1, gp.getRowCount() - 1 );
            }
            VBox imageIdVPane = new VBox();
            imageIdVPane.setPadding(new Insets(30, 30, 30, 30));
            imageIdVPane.setSpacing(20);
            ImageView iUnknown = new ImageView(new Image(String.valueOf(s)+".png"));
            iUnknown.setFitWidth(150);
            iUnknown.setFitHeight(150);
            Label id = new Label("Choose one of the following " +String.valueOf(s)+"s");
            id.setStyle("-fx-font-size: 16pt");

            HBox IdHPane = new HBox();
            IdHPane.getChildren().add(id);
            IdHPane.setAlignment(Pos.CENTER);
            imageIdVPane.getChildren().addAll(iUnknown, IdHPane);
            imageIdVPane.setAlignment(Pos.CENTER);

            VBox listDoctorPane = new VBox();

            Button Proceed = new Button("Proceed");
            //Proceed.setStyle("-fx-color: DarkCyan");


            Label name = new Label("Name:");
            name.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label phoneNum = new Label("Phone Number:");
            phoneNum.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan; -fx-font-weight: bold");
            Label address = new Label("Address:");
            address.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");
            Label speciality = new Label("Speciality:");
            speciality.setStyle("-fx-font-size: 12pt; -fx-text-fill: DarkCyan;-fx-font-weight: bold");

            gp.setAlignment(Pos.CENTER);
            GridPane.setHalignment(Proceed, HPos.RIGHT);

            Proceed.setPadding(new Insets(10, 20, 10, 20));
            String na;
            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
            {
                public void changed(ObservableValue<? extends Toggle> ob,
                                    Toggle o, Toggle n)
                {
                    RadioButton rb = (RadioButton)group.getSelectedToggle();

                    if (rb != null) {
                        Proceed.setOnAction(e -> proceedButtonAction( rb.getText() )) ;

                    }
                }
            });
            Proceed.setOnAction(e -> proceedButtonAction( "NONE" ));

            listDoctorPane.setSpacing(0);
            listDoctorPane.getChildren().addAll(imageIdVPane, gp);
            gp.add(Proceed, 3, gp.getRowCount());
            gp.setPadding(new Insets(0,0,30,0));
            ScrollPane sp = new ScrollPane();
            sp.setContent(listDoctorPane);
            sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
            sp.setFitToWidth(true);
            sp.setPannable(true);

            bp.setCenter(sp);
            bp.setTop(getTopMenuPane("    \\   Appointments"+"    \\   "+ String.valueOf(s)));

        }
        void proceedButtonAction(String s){
            if(s.equals("NONE")){
                Label completeInfo = new Label("Please, select a Professor first ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("No Selection");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            }
            else{
                Professor d = AdminAccountController.getBY_NameProfessor(s.substring(4));
                GridPane g = new GridPane();
                g.setVgap(20);
                g.setHgap(20);
                Label l = new Label("Reserved Slots");
                l.setStyle("-fx-font-size: 16pt;-fx-text-fill: DarkCyan; -fx-font-weight: bold");
                g.add(l, 0 , 0);
                for (String a: d.appointment ) {

                    g.add(new Label(a), 0 ,g.getRowCount() );
                }
                Label lf = new Label("Reserve Another");
                lf.setStyle("-fx-font-size: 16pt;-fx-text-fill: DarkCyan; -fx-font-weight: bold");
                g.add(lf, 0, g.getRowCount());
                TextField t = new TextField();
                t.setText("Enter a Date");
                g.add(t, 0, g.getRowCount());
                Button reserve = new Button("Reserve");
                reserve.setPadding(new Insets(10,20,10,20));
                g.add(reserve, 1, g.getRowCount()-1);
                reserve.setOnAction(e -> reserveButtonAction(d,t.getText()));
                g.setAlignment(Pos.CENTER);
                ScrollPane sp = new ScrollPane();
                sp.setContent(g);
                sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
                sp.setFitToWidth(true);
                sp.setPannable(true);
                sp.setPadding(new Insets(30,0,0,0));
                bp.setCenter(sp);
                bp.setTop(getTopMenuPane("    \\   Appointments"+"    \\   "+ String.valueOf(d.getSpeciality())+"    \\   "+s+"    \\   Book"));

            }

        }
        void reserveButtonAction(Professor d, String text){
            if ((text.compareTo("")) == 0 || (text.compareTo("Enter a Date")) == 0 ) {
                Label completeInfo = new Label("Please, enter a date first ");
                completeInfo.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(completeInfo, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene completeInfoScene = new Scene(pane);
                Stage completeInfoStage = new Stage();
                completeInfoStage.setTitle("Missing Information");
                completeInfoStage.setScene(completeInfoScene);
                completeInfoStage.show();
                okay.setOnAction(e -> completeInfoStage.close());
            } else if (text.contains(" ")) {
                Label noSpaces = new Label("Please note that NO SPACES allowed in any input ");
                noSpaces.setStyle("-fx-font-size: 16pt");
                Button okay = new Button("Okay");
                okay.setStyle("-fx-font-size: 10pt");
                HBox buttonsHpane = new HBox(30);
                buttonsHpane.setAlignment(Pos.CENTER);
                buttonsHpane.getChildren().add(okay);
                VBox pane = new VBox(30);
                pane.getChildren().addAll(noSpaces, buttonsHpane);
                pane.setPadding(new Insets(30, 30, 30, 30));
                Scene noSpacesScene = new Scene(pane);
                Stage noSpacesStage = new Stage();
                noSpacesStage.setTitle("Error");
                noSpacesStage.setScene(noSpacesScene);
                noSpacesStage.show();
                okay.setOnAction(e -> noSpacesStage.close());
            } else {
                d.appointment.add(text);
                proceedButtonAction("Dr. " + d.getName());
            }

        }

    }

}