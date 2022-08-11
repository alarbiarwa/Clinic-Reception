/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab567;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author arwaelarabi
 */
public class Lab567 extends Application {
     Scene HomeScene ;  //Clinic Reception Scene
    Scene SearchScene ;  //Search Scene

    @Override
    
    public void start(Stage primaryStage) {
        Label fname  = new Label("Patient First Name: ");
        Label lname  = new Label("Patient Second Name: ");
        Label mobile  = new Label("Patient Mobile Number: ");
        Label ClinicName  = new Label("Clinic Name: ");
        Label Date  = new Label("Appointment Date: ");
        Label Time  = new Label("Appointment Time: ");


        TextField FName = new TextField();
        TextField SName = new TextField();
        TextField Mobile = new TextField();

        ComboBox<String> Clinic = new ComboBox();
        Clinic.getItems().addAll("Dental", "Dermatology","pediatric","Eye","Radiology");
        Clinic.setValue("Dental");

        ComboBox<String> time = new ComboBox();
        time.getItems().addAll("8:00 AM", "9:00 AM","10:00 AM","11:00 AM","12:00 PM", "1:00 PM","2:00 PM", "3:00 PM", "4:00 PM" );
        time.setValue("8:00 AM");

        DatePicker date = new DatePicker();
        date.setValue(LocalDate.now());


        GridPane form = new GridPane();
        form.add(fname,0,0);
        form.add(FName,1,0);

        form.add(lname,0,1);
        form.add(SName,1,1);

        form.add(mobile,0,2);
        form.add(Mobile,1,2);

        form.add(ClinicName,0,3);
        form.add(Clinic,1,3);

        form.add(Date,0,4);
        form.add(date,1,4);

        form.add(Time,0,5);
        form.add(time,1,5);

        form.setAlignment(Pos.CENTER);
        form.setHgap(10);
        form.setVgap(10);



        Button Book = new Button("Book");
        Button Delete = new Button("Delete");
        Button Search = new Button("Search");
        Button Clear = new Button ("Clear");

        HBox buttons = new HBox(20);
        buttons.setAlignment(Pos.CENTER);
        //buttons.getChildren().addAll(Book,Delete,Search,Clear);

        Label lblmsg = new Label();

        VBox controls = new VBox(20);
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(form,lblmsg, buttons);


           ObservableList<String> obNames=FXCollections.observableArrayList();
           ObservableList<String> obMobile=FXCollections.observableArrayList();
           ObservableList<String> obClinic=FXCollections.observableArrayList();
           ObservableList<String> obTime=FXCollections.observableArrayList();
           ObservableList<String> obCilinicFilter=FXCollections.observableArrayList();

        ListView lvName = new ListView(obNames);

        ListView lvMobile = new ListView(obMobile);

        ListView lvClinic = new ListView(obClinic);

        ListView lvTime = new ListView(obTime);



        HBox lvs = new HBox(10);
        lvs.setAlignment(Pos.CENTER);
        lvs.setMaxWidth(700);
        lvs.setMinWidth(700);


        VBox VBlvName = new VBox(10);

        VBox VBlvMobile = new VBox(10);

        VBox VBlvClinic = new VBox(10);

        VBox VBlvTime = new VBox(10);

        VBlvName.getChildren().addAll(new Label("Full Name: "), new ScrollPane(lvName));
        VBlvMobile.getChildren().addAll(new Label("Mobile Number: "), new ScrollPane(lvMobile));
        VBlvClinic.getChildren().addAll(new Label("Clinic: "), new ScrollPane(lvClinic));
        VBlvTime.getChildren().addAll(new Label("Date and Time: "), new ScrollPane(lvTime));



        lvs.getChildren().addAll(VBlvName,VBlvMobile,VBlvClinic,VBlvTime);



        HBox root = new HBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(controls,lvs);

        HomeScene = new Scene(root, 1400, 500);

        //The Search Scene -----------------------------------------------------------------------------------------

        VBox searchControl = new VBox();
        Label lbltf = new Label("Enter the patient name: ") ;

        TextField tfSearch = new TextField();

        Button SearchPatient = new Button("Search");

        Label msg = new Label();
        msg.setTextFill(Color.DARKRED);


        HBox  paneSearch = new HBox(lbltf, tfSearch, SearchPatient);
        paneSearch.setAlignment(Pos.TOP_LEFT);
        paneSearch.setSpacing(10);


        searchControl.getChildren().addAll(paneSearch, msg);
        searchControl.setAlignment(Pos.TOP_LEFT);
        searchControl.setSpacing(10);


        ComboBox<String> Clinicfilter = new ComboBox();
        Clinicfilter.getItems().addAll("Dental", "Dermatology","pediatric","Eye","Radiology");
        Clinicfilter.setValue("Dental");

        ListView lvPatientbyClinc = new ListView(obCilinicFilter);
        lvPatientbyClinc.setPrefWidth(750);


        HBox filter = new HBox(10);
        filter.setAlignment(Pos.TOP_LEFT);
        filter.getChildren().addAll(new Label("Filter patients by clinic: "), Clinicfilter, lvPatientbyClinc);

        Button Back = new Button("Back");
        HBox back = new HBox(10);
        back.setAlignment(Pos.BOTTOM_RIGHT);
        back.getChildren().add(Back);

        VBox root2 = new VBox(10);
        root2.setPadding(new Insets(20));

        root2.getChildren().addAll(searchControl,filter,back);

        SearchScene = new Scene(root2,1100, 500);



        //Actions of Scene Clinics Reception ------------------------------------------------------------------------------------------
        //###### Task 2.1: Add an ActionEvent on the Book button:
        
       Book.setOnAction(e->{
         
 String FName1=FName.getText();
        String SName1=SName.getText();
        String sp=" ";
        String FullName1= FName1.concat(sp);
        String FullName2= FullName1.concat(SName1);
      
         String mobile1 =Mobile.getText();
         
         obClinic.add(Clinic.getValue());

          
          if(FName1.equals(sp)|SName1.equals(sp)|mobile1.equals(sp)){
              lblmsg.setText("Enter the patient name and mobile \n" +"number");}
              
              else {
              
             if(mobile1.length()!=10){
         lblmsg.setText("The length of Mobile number must be 10 digits");}
                      else{
                 if(obNames.contains(FullName2)){
                 lblmsg.setText ("The patient name is already exists");
                 }
                 
                 
                 else{
                   
             obNames.add(FullName2);
             obMobile.add(mobile1);
             String time1=date.getValue().toString(), at1=" at ",time2=time.getValue(),time3= time1.concat(at1),time4= time3.concat(time2);
              obTime.add(time4);}
             };
           }});


        //##### Task 2.3: Add an ActionEvent on the Delete button:
        Delete.setOnAction(e-> {
        if (lvName != null) { 
            
//            lblmsg.setText("Patient"+obNams+"was Deleted");
            lblmsg.setText("Patient"+lvName.getSelectionModel().getSelectedItems()+"was Deleted");
            
             int index_item = lvName.getSelectionModel().getSelectedIndex();  
            obNames.remove(index_item);  
            obMobile.remove(index_item);
            obClinic.remove(index_item);
            obTime.remove(index_item);
            obCilinicFilter.remove(index_item);
        }
       
    });
             
        
        //##### Task 2.4: Add an ActionEvent on the “Search” button:
        SearchPatient.setOnAction(e-> {
            String s1=tfSearch.getText();
            String s2=" The Phone Number is: ";
             String s3=" The Appointment is: ";
             
             boolean b=true;
        if(s1.equals("")){
           msg.setText("Enter the patient name to search the information" );
        }
        else if(obNames.isEmpty()){
          msg.setText(" No Patient in the list" );}
        else{  
            
            
            for (String s : obNames){            
                if(s.contains(s1)){
               int i= obNames.indexOf(s);            
               msg.setText(s2+obMobile.get(i)+s3+obTime.get(i));
                b=false;
               break;
                }}
            if(b)
           msg.setText(" The patient not found" );                
        }
       
        });

        
        //##### Task 2.5: Add an ActionEvent on the “Clear” button:
        Clear.setOnAction(e-> {
         FName.clear();
         SName.clear();
         Mobile.clear();
  });

        //Actions of Scene Search
        //##### Task 3.1: Add an ActionEvent on the Search button:
        Search.setOnAction(e -> {
         primaryStage.setTitle("Search");
        primaryStage.setScene(SearchScene);
        primaryStage.show();
        });

        //Task 3.2: Add an ActionEvent on the ClinicFilter combobox:
        Back.setOnAction(e-> {
         primaryStage.setTitle("Clinics Reception");
        primaryStage.setScene(HomeScene);
        primaryStage.show();
        });
        
        //Task 3.3: Add an ActionEvent on the Back button:
        Clinicfilter.setOnAction(e-> {
            
            obCilinicFilter.clear();
            
          String s1= Clinicfilter.getValue(); 
          
         for (int j=0;j< lvClinic.getItems().size();j++){  
             
               String s2= lvClinic.getItems().get(j).toString();
               
               if(s1.equals(s2)){  
                   String s3= "Patient Name: ".concat(obNames.get(j) );
                   String s4= " Appointment Date and Time: ".concat( obTime.get(j));
                   
                  String s5=s3.concat(s4);
                  
                   obCilinicFilter.add(s5);
             
                }      
         }
           
       
        });
        
        
      

        //###### Task 2.2: Add Listeners on the lvName, lvMobile, lvClinic and lvTime  ListViews:
        //###### You only need to uncomment the following part
/*
    lvName.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvName.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvName.getSelectionModel().getSelectedIndex();
            lvMobile.getSelectionModel().select(index);
            lvClinic.getSelectionModel().select(index);
            lvTime.getSelectionModel().select(index);

            String[] FullName = obNames.get(index).split(" ");
            String FirstName = FullName [0];
            String LastName = FullName [1];
            FName.setText(FirstName);
            SName.setText(LastName);
            Mobile.setText(obMobile.get(index));
            Clinic.setValue(obClinic.get(index));

            String[] DateTime = obTime.get(index).split(" at ");

            date.setValue(LocalDate.parse(DateTime[0]));
            time.setValue(DateTime[1]);



        });

        lvMobile.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvMobile.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvMobile.getSelectionModel().getSelectedIndex();
            lvName.getSelectionModel().select(index);
            lvClinic.getSelectionModel().select(index);
            lvTime.getSelectionModel().select(index);

            String[] FullName = obNames.get(index).split(" ");
            String FirstName = FullName [0];
            String LastName = FullName [1];
            FName.setText(FirstName);
            SName.setText(LastName);
            Mobile.setText(obMobile.get(index));
            Clinic.setValue(obClinic.get(index));
            String[] DateTime = obTime.get(index).split(" at ");

            date.setValue(LocalDate.parse(DateTime[0]));
            time.setValue(DateTime[1]);


        });

        lvClinic.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvClinic.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvClinic.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            lvName.getSelectionModel().select(index);
            lvMobile.getSelectionModel().select(index);
            lvTime.getSelectionModel().select(index);

            String[] FullName = obNames.get(index).split(" ");
            String FirstName = FullName [0];
            String LastName = FullName [1];
            FName.setText(FirstName);
            SName.setText(LastName);
            Mobile.setText(obMobile.get(index));
            Clinic.setValue(obClinic.get(index));
            String[] DateTime = obTime.get(index).split(" at ");

            date.setValue(LocalDate.parse(DateTime[0]));
            time.setValue(DateTime[1]);


        });


        lvTime.getSelectionModel().selectedItemProperty().addListener(e-> {
            lvTime.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            int index = lvTime.getSelectionModel().getSelectedIndex();
            lvName.getSelectionModel().select(index);
            lvMobile.getSelectionModel().select(index);
            lvClinic.getSelectionModel().select(index);

            String[] FullName = obNames.get(index).split(" ");
            String FirstName = FullName [0];
            String LastName = FullName [1];
            FName.setText(FirstName);
            SName.setText(LastName);
            Mobile.setText(obMobile.get(index));
            Clinic.setValue(obClinic.get(index));
            String[] DateTime = obTime.get(index).split(" at ");

            date.setValue(LocalDate.parse(DateTime[0]));
            time.setValue(DateTime[1]);


        });
        */
       //Task 4.1: Add a new button “ExportToFile” to the Clinics Reception Screen:
            Button ExporToFile=new Button("Export to file");
            buttons.getChildren().addAll(Book,Delete,Search,Clear,ExporToFile);
  File info_file = new File("records.txt");
        ExporToFile.setOnAction(e->{
        PrintWriter PW =null;
        try{
        PW = new PrintWriter(info_file);
        for(int count=0 ; count<=obNames.size()-1;count++){
            PW.println(obNames.get(count)+" "+obMobile.get(count)+" "+obClinic.get(count)+" "+obTime.get(count)+" ");
        } 
        }catch(Exception FileNotFoundException){
            System.out.println("File not Exist");;
        }finally{
            PW.close();}
        });

          
          //Extra task :reading data from file
          
         Scanner read_file=null ;
       if(info_file.exists()){
       try{
          read_file = new Scanner(info_file);
           while(read_file.hasNext()){
              String[] info = read_file.nextLine().split(" ");
              obNames.add(info[0]+" "+info[1]);
              obMobile.add(info[2]);
              obClinic.add(info[3]);
              obTime.add(info[4]+" "+info[5]+" "+info[6]+" "+info[7]);
           }
       }catch(Exception FileNotFoundException ){
            System.out.println("File not exist");;
              }finally{
           read_file.close();     
       }
    };

        primaryStage.setTitle("Clinics Reception");
        primaryStage.setScene(HomeScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
