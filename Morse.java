import java.util.InputMismatchException;
import java.util.Scanner;

public class Morse {

    public static void main(String[] args) {

        int choice=0;
        String phrase,code;
        boolean cont = true;

        Scanner input = new Scanner(System.in);
        System.out.println("Jepni tekstin:");
        String teksti=input.nextLine();

        do{
            try{


                System.out.println("Deshironi te kodoni apo te dekodoni mesazhin e sipershenuar ?");
                System.out.println("\n1.Kodoje mesazhin");
                System.out.println("2.Dekodoje mesazhin");

                choice = input.nextInt();
                if(choice < 1 || choice > 2){
                    while(choice < 1 || choice > 2){
                        System.out.println("Invalid Input.");
                        System.out.println("\n1.Encode");
                        System.out.println("2.Decode");
                        choice = input.nextInt();
                    }
                }
                cont=false;
            }
            catch(InputMismatchException in){
                System.out.println("Invalid Input");
                input.nextLine();
            }

        } while(cont);

        if(choice == 1){
            Encoder encode = new Encoder(teksti);
            encode.OutputMorse();
        }
        if(choice == 2){
            code = input.nextLine();
            Decoder decode = new Decoder(teksti);
        }
    }
}


