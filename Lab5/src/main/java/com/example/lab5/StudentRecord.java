package com.example.lab5;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import com.example.lab5.DataSource;

import java.io.IOException;

public class StudentRecord extends Application {

    public StudentRecord(String studentID, float assignments, float midterm, float finalExam) {
        ObservableList<Object> records = DataSource.getAllMarks();
        //assignments 20%
        //midterm 30%
        //final exam 50%
    }

    @Override
    public void start(Stage stage) throws IOException {

        TableView studentRecords = new TableView();
        TableColumn<StudentRecord, String> column1 = new TableColumn<>("Student ID");



        FXMLLoader fxmlLoader = new FXMLLoader(StudentRecord.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {
        launch();
    }
}