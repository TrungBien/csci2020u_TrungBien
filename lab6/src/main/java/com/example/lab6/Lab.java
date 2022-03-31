package com.example.lab6;

import java.util.stream.*;
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




public class Lab extends Application {

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
        Scene scene = new Scene(root, 1280, 720, Color.LIGHTGRAY);



        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setScene(scene);
        primaryStage.show();



        draw(avgHousingPricesByYear, avgCommercialPricesByYear,
                ageGroups, purchasesByAgeGroup, pieColours); // the good stuff happens here
    }



    private void draw(double[] avgHouse, double[] avgComm, String[] ageGroups,
                      int[] purchasesAge, Color[] pieColours ) {
        GraphicsContext gc = canvas.getGraphicsContext2D();


        //max 50, min 680

        //since data is ordered and all values of avgComm are larger


        double[] avgCommPercent = {avgComm[0]/avgComm[5], avgComm[1]/avgComm[5], avgComm[2]/avgComm[5],
                avgComm[3]/avgComm[5], avgComm[4]/avgComm[5], 1};
        double[] avgHousePercent = {avgHouse[0]/avgComm[5], avgHouse[1]/avgComm[5], avgHouse[2]/avgComm[5],
                avgHouse[3]/avgComm[5], avgHouse[4]/avgComm[5], avgHouse[5]/avgComm[5]};

        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLUE);

        for(int a = 0; a < 6; a++){
            double height = (avgCommPercent[a] * 630) * -1;
            int x = 80 + (a * 60);
            gc.fillRect(x, 680, 25, height);

        }

        gc.setFill(Color.RED);
        gc.setStroke(Color.RED);

        for(int a = 0; a < 6; a++){
            double height = (avgHousePercent[a] * 630) * -1;
            int x = 50 + (a * 60);
            gc.fillRect(x, 680, 25, height);

        }

        System.out.println("Hmm");
        int totalAges = IntStream.of(purchasesAge).sum();
        Double total = Double.valueOf(totalAges);
        System.out.println(totalAges);

        double[] agePercent = {purchasesAge[0]/total, purchasesAge[1]/total,
                purchasesAge[2]/total, purchasesAge[3]/total,
                purchasesAge[4]/total, purchasesAge[5]/totalAges};

        double[] angles = new double[7];
        angles[0] = 0.0;

        gc.setStroke(Color.BLACK);
        gc.strokeOval(800, 200, 400, 400);

        for (int b = 0; b < 6; b++){

            double startingAngle = angles[b];
            double arcExtent = agePercent[b] * 360;
            angles[b+1] = startingAngle + arcExtent;
            gc.setFill(pieColours[b]);
            gc.setStroke(pieColours[b]);
            System.out.println(startingAngle);
            System.out.println(arcExtent);
            gc.fillArc(800, 200, 400, 400, startingAngle, arcExtent, ArcType.ROUND);

        }

//        gc.fillRect(50, 680, 25, -630);
//        gc.fillRect(80, 680, 25, -625.6);




    }

}



