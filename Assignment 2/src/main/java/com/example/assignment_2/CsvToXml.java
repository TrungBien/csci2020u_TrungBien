package com.example.assignment_2;



import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CsvToXml {



    public static void main(String[] args) throws ParserConfigurationException {





        DocumentBuilderFactory domFac = DocumentBuilderFactory.newInstance();
        DocumentBuilder domBuild = domFac.newDocumentBuilder();

        Document newDoc = domBuild.newDocument();
        Element root = null;

        ArrayList<String[]> data = new ArrayList<>();

        try{
//            InputStream csv = classLoader.getResourceAsStream("airline_safety.csv");
            File csv = new File(CsvToXml.class.getClassLoader().getResource("airline_safety.csv").toURI().getPath());

            root = newDoc.createElement(csv.getName());
            newDoc.appendChild(root);

            Scanner reader = new Scanner(csv);


            if (reader.hasNextLine()){
                String head = reader.nextLine();
                String[] headers = head.split(",");
                data.add(headers);
            }
            while (reader.hasNextLine()){
                data.add(reader.nextLine().split(","));

            }
            reader.close();

            //reads ArrayList
            String[] headers = data.get(0);
            ArrayList<Integer> incidentColIndex = new ArrayList<>();

            Pattern pattern = Pattern.compile("incidents", Pattern.CASE_INSENSITIVE);
            Matcher matcher = null;
            boolean found = false;

            int counter = 0;
            for(String title:headers){
                matcher = pattern.matcher(title);
                found = matcher.find();
                if(found){
                    incidentColIndex.add(counter);

                }
                counter++;
            }
            //prints index list
            //System.out.println(Arrays.toString(incidentColIndex.toArray()));

            String[] totalIncidents = new String[data.size()-1];
            //String[] incidentsCombinedData = new String[(data.size()-1)*incidentColIndex.size()];
            ArrayList<String> incidentsCombinedData = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>();

            for (int a = 0; a < incidentColIndex.size(); a++){
                temp.clear();

                for (int b = 1; b < data.size(); b++){
                    temp.add(data.get(b)[incidentColIndex.get(a)]);
                }

//                for (String b:data.get(incidentColIndex.get(a))){
//                    temp.add(b);
//                }
                incidentsCombinedData.addAll(temp);

            }



            int[] incidentsNumCombinedData = new int[(data.size()-1)*incidentColIndex.size()];

            //convert string to int
            for (int x = 0; x < incidentsCombinedData.size(); x++ ){

                incidentsNumCombinedData[x] = Integer.parseInt(incidentsCombinedData.get(x));
            }



            int sum = 0;


            for (int i = 0; i < data.size()-1; i++){

                for (int arr = 0; arr < incidentColIndex.size(); arr++){
                    sum += incidentsNumCombinedData[i + (arr*incidentColIndex.get(arr))];

                }

                totalIncidents[i] = String.valueOf(sum);
                sum = 0;
            }


            String[] headerList = new String[data.get(0).length];
            for (int i = 0; i < data.get(0).length; i++ ){
                headerList[i] = data.get(0)[i];

            }
            headerList[data.get(0).length-1] = "total_number_of_incidents_between_1985_and_2014";

//            data.set(0,headerList);
//            System.out.println(Arrays.toString(data.get(0)));


            for( int s = 1; s < data.size(); s++){
                String[] tempArr = new String[data.get(0).length];
                for (int t = 0; t < data.get(0).length; t++){
//                    System.out.println(t);
//                    System.out.println(data.get(0).length);
                    tempArr[t] = data.get(s)[t];

                }
                tempArr[data.get(0).length-1] = totalIncidents[s-1];

                data.set(s, tempArr);
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        //conversion

//        for(String[] lineData:data){
//
//            System.out.println(Arrays.toString(lineData));
//            //System.out.println(lineData[0]);
//        }


        try{
            Element statHeader = null;
            Element airline = null;
            Attr stat = null;



            for (int row = 1; row < data.size(); row++){

                for (int col = 0; col < data.get(0).length; col++){

                    if (col == 0){

                        airline = newDoc.createElement(data.get(0)[0]);
                        stat = newDoc.createAttribute("name");
                        stat.setValue(data.get(row)[0]);
                        airline.setAttributeNode(stat);

                        root.appendChild(airline);

                    } else {

                        statHeader = newDoc.createElement(data.get(0)[col]);

                        statHeader.appendChild(newDoc.createTextNode(data.get(row)[col]));

                        airline.appendChild(statHeader);


                    }


                }



            }



            TransformerFactory transFac = TransformerFactory.newInstance();
            Transformer trans = transFac.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource src = new DOMSource(newDoc);
            StreamResult streamResult =  new StreamResult(new File("converted_airline_safety.xml"));
            trans.transform(src, streamResult);



//            String xml = streamResult.getWriter().toString();
//            System.out.println(xml);

        } catch (Exception e){
            System.out.println("Something has gone wrong in conversion");
        }

//        System.out.println(data.get(6)[1]);
//        try{
//            System.out.println(Integer.parseInt(""));
//        } catch (NumberFormatException e){
//            System.out.println("conversion of string to int failed");
//        }

        //Summary


        try{

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Summary");
            doc.appendChild(rootElement);


            ArrayList<long[]> summary = new ArrayList<>();
            long[] tempLongArr = new long[3];//three for min, max, and avg


            for (int c = 1; c < data.get(0).length; c++){ //no need for airline name

                ArrayList<Long> tempLongList = tempColArrList(data, c);


                Collections.sort(tempLongList); //sort results in descending order

                tempLongArr[0] = tempLongList.get(tempLongList.size() - 1); //min

                tempLongArr[1] = tempLongList.get(0); //max

                tempLongArr[2] = colAvg(tempLongList); //average


                summary.add(tempLongArr);
            }






//            Element header = null;
//            Element minStat = null;
//            Element maxStat = null;
//            Element avgStat = null;
//            Element stat = null;


            for (int row = 0; row < summary.size(); row++){

                Element stat = doc.createElement("stat");
                rootElement.appendChild(stat);

                Element header = doc.createElement("Name");
                header.appendChild(doc.createTextNode(data.get(0)[row+1]));
                stat.appendChild(header);



                Element minStat = doc.createElement("min");
                minStat.appendChild(doc.createTextNode(String.valueOf(summary.get(row)[0])));

                stat.appendChild(minStat);



                Element maxStat = doc.createElement("max");
                maxStat.appendChild(doc.createTextNode(String.valueOf(summary.get(row)[1])));
                stat.appendChild(maxStat);



                Element avgStat = doc.createElement("avg");
                avgStat.appendChild(doc.createTextNode(String.valueOf(summary.get(row)[2])));
                stat.appendChild(avgStat);



            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource domSource = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File("airline_summary_statitic.xml"));
            transformer.transform(domSource, result);



        } catch (Exception e){
            System.out.println("Error with summary");
        }



    }

    //creates ArrayList of ints for summary
    public static ArrayList<Long> tempColArrList(ArrayList<String[]> input, int colNum){

        ArrayList<Long> temp = new ArrayList<>();

        for(int r = 1; r < input.size(); r++){
            //loops through each row but the first row (headers)
            temp.add(Long.parseLong(input.get(r)[colNum]));

        }

        return temp;
    }


    public static long colAvg(ArrayList<Long> input){

        long sum = 0;
        for(long v : input){
            sum+=v;
        }


        return sum/input.size();
    }




}
