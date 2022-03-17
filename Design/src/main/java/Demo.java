import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        //Creates factoryclass
        FactoryClass f = new FactoryClass();
        //initializing phone
        Phone p = null;
        //setting up input
        Scanner input = new Scanner(System.in);
        boolean valid = false;
        System.out.println("Enter a phone company. (Apple, Samsung, Nokia)");
        String answer = input.nextLine(); //user input here
        //loops as long as input is invalid and sets p using factoryclass
        while (!valid){
            if (answer.equalsIgnoreCase("APPLE")){
                valid = true;
                p = f.getPhone(String.valueOf(answer));
            } else if (answer.equalsIgnoreCase("SAMSUNG")) {
                valid = true;
                p = f.getPhone(String.valueOf(answer));
            } else if (answer.equalsIgnoreCase("NOKIA")) {
                valid = true;
                p = f.getPhone(String.valueOf(answer));
            } else {
                System.out.println("Enter a valid input.");
                answer = input.nextLine();
            }

        }
        //calls name of subclass
        p.name();


    }

}
