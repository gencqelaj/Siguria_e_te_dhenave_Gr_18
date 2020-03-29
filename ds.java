public class ds {

    public static void main(String[] args) {
        try {
            switch (args[0]) {
                case "permutation":
                    switch (args[1]) {
                        case "encrypt":

                            Permutation permutation = new Permutation(args[2], args[3]);
                            
                            permutation.encrypt();
                            break;
                        case "decrypt":

                            Permutation permutation1 = new Permutation(args[2], args[3]);
                            permutation1.decrypt();
                            break;
                        default:
                            System.out.println("Zgjedh si argument te dyte encrypt ose decrypt");
                    }
                    break;
                case "morse":
                    switch (args[1]) {
                        case "encrypt":
                            MorseEncoder encoder = new MorseEncoder(args[2]);
                            encoder.OutputMorse();
                            break;
                        case "decrypt":
                            MorseDecoder decoder = new MorseDecoder(args[2]);
                            break;
                        default:
                            System.out.println("Zgjedh si argument te dyte encrypt ose decrypt");
                    }
                    case "tapcode":
                    if(args.length != 3)
                    {
                        System.out.println("Per te ekzekutuar tapcode ju duhen vetem 3 argumente: tapcode encode/decode 'teksti' ");
                        System.exit(1);
                    }
                    String tipi = args[1];
                    String input = args[2];
                    TapCode tapObj = new TapCode(input);
                    switch (tipi) {
                        case "encode":
                            tapObj.enkripto();
                            break;
                        case "decode":
                           tapObj.dekripto();
                            break;
                        default:
                            System.out.println("Zgjedh si argument te dyte encode ose decode");
                            System.exit(1);
                    }
                    break;

                default:
                    System.out.println("Argumenti i pare eshte gabim , zgjedh permutation morse apo tap-code per te vazhduar");


            }}
        catch (Exception e)
        {
            System.out.println("Duhet te shkruani mjaftueshem argumente");
            System.out.println(e.getMessage());
        }


    }
}
