package com.example.lab6;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;




public class Lab6 extends Application {

    //Bar chart
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };


    //Pie Chart

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };




    @FXML
    private Canvas canvas;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.LIGHTGRAY);



        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(ageGroups.length);


        draw(avgHousingPricesByYear, avgCommercialPricesByYear,
                ageGroups, purchasesByAgeGroup, pieColours); // the good stuff happens here
    }



    private void draw(double[] avgHouse, double[] avgComm, String[] ageGroups,
                      int[] purchasesAge, Color[] pieColours ) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.strokeOval(650, 50, 100, 75);
        gc.fillOval(650, 175, 100, 75);
        gc.strokeArc(50, 350, 100, 75, 115.0, 45.0, ArcType.ROUND);
        gc.fillArc(50, 500, 100, 75, 45.0, 115.0, ArcType.ROUND);

    }

}



