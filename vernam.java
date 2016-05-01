import java.util.Scanner;

/**
 * Created by deepak on 3/21/16.
 */
public class vernam {

    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter text to be encrypted->");
        String plainText = input.nextLine();
        System.out.println("Enter the key->");
        String key = input1.nextLine();
        StringBuffer enc = encryption(plainText, key);
        System.out.println("Encrypted Text->"+ enc);
       decryption(enc, key);
    }

    public static StringBuffer encryption(String plain, String key) {
        StringBuffer encrypted = new StringBuffer(plain);

        for (int i = 0; i < plain.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                char c = plain.charAt(i);
                char ch = key.charAt(j);
                int cIndex = checkIndex(c);
                int chIndex = checkIndex(ch);
                int xor = (cIndex ^ chIndex);
                while (xor > 26) {
                    xor = xor % 26;
                }
                i++;
                if (i <= plain.length()) {
                    encrypted.setCharAt(i-1, alphabet.charAt(xor));
                    continue;

                } else {
                    break;
                }

            }

        }
        return  encrypted;
    }


    public static int checkIndex(char c){
        int index = 0;
        for(int i=0;i<alphabet.length();i++){
            if(c == alphabet.charAt(i)){
               index = i;
            }
        }
        return index;

    }

    public static void decryption(StringBuffer cipher , String key){
        StringBuffer dec = new StringBuffer(cipher);

        for(int i=0;i<cipher.length();i++){
            for(int j=0;j<key.length();j++){
                char c = cipher.charAt(i);
                char ch = key.charAt(j);
                int cIndex = checkIndex(c);
                int chIndex = checkIndex(ch);
                int xor = (cIndex^chIndex);
                while(xor > 26){
                    xor = xor%26;
                }
                i++;
                if(i<= cipher.length()){
                    dec.setCharAt(i-1, alphabet.charAt(xor));
                    continue;

                }
                else
                {
                    break;
                }

            }

        }
        System.out.println("Decrypted Text->"+dec);
    }
}
