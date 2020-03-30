public class ds {
    public static void main(String[] args) {
        try {
switch (args[0]) {
    case "permutation":
        Permutation permutation = new Permutation(args[2], args[3]);
        if (args[1].equals("encrypt"))
            permutation.encrypt();
        else if (args[1].equals("decrypt"))
            permutation.decrypt();
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt si argument te dyte");

        break;

    case "morse":
        if (args[1].equals("encrypt"))
        {
            MorseEncoder encoder = new MorseEncoder(args[2]);
            encoder.OutputMorse();
        }
        else if (args[1].equals("decrypt"))
        {
            for (int i =0 ; i<args[2].length() ; i++)
            {
                if (args[2].charAt(i)=='-' || args[2].charAt(i)=='.' || args[2].charAt(i)==' ')
                {
                     MorseDecoder decoder = new MorseDecoder(args[2]);
                }
                else
                    System.out.println("Teksti nuk duhet te permbaje karaktere tjere perpos - , . , ose space");
                System.exit(0);
            }

        }
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt si argument te dyte per te ekzekutuar");



        break;


    case "tap-code":

        if (args[1].equals("encrypt")) {
            TapCode tapObj = new TapCode(args[2]);
            tapObj.enkripto();
        }
        else if (args[1].equals("decrypt"))
        {
            TapCode tapObj = new TapCode(args[2]);
            tapObj.dekripto();
        }
        else
            System.out.println("Ju lutem shtypni encrypt ose decrypt per te ekzekutuar");

        break;

    default:
        System.out.println("Ju lutem shtypni njeren nga komandat permutation morse apo tap-code si argument te pare");

}


} catch (Exception e )
        {
            System.out.println("Ju nuk keni shtypur gjithe argumentet e nevojshme");
        }
    }
}
