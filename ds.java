public class ds {

    public static void main(String[] args) {
        if ("permutation".equals(args[0])) {
            Permutation permutation = new Permutation(args[2],args[3]);
            if ("encrypt".equals(args[1])) {
                permutation.encrypt();
            } else if ("decrypt".equals(args[1])) {
                permutation.decrpyt();
            }
        }

        if ("morse".equals(args[0])) {

            if ("encrypt".equals(args[1])) {
                MorseEncoder encoder=new MorseEncoder(args[2]);
                encoder.OutputMorse();

            } else if ("decrypt".equals(args[1])) {
                MorseDecoder decoder=new MorseDecoder(args[2]);

            }
        }
    }}
