import java.util.Scanner;

/**
 * Created by nikita on 2/19/16.
 */
public class Ceaser {
    public static String getText, getText1;
    public static  String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static int c,m,k;
    public static  char ch;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        System.out.println("Enter a string to be encrypted-->");
        getText = scanner.nextLine();
        getText = getText.toLowerCase();
        StringBuffer text = new StringBuffer(getText);
        System.out.println("Enter encryption key-->");
        k = scanner.nextInt();
        System.out.println("Encrypted Text is->"+  encryption(text));


        System.out.println("Enter text to be decrypted->");
        getText1 = input1.nextLine();
        getText1 = getText1.toLowerCase();
        StringBuffer text1 = new StringBuffer(getText1);
        System.out.println("Enter decryption key->");
        k = scanner.nextInt();
        System.out.println("Decrypted Text is->"+ decryption(text1));


    }
    public static StringBuffer encryption(StringBuffer text){
        for (int j=0;j<text.length();j++){
            ch = getText.charAt(j);
            if(ch != ' ' && ch != '.'){
                for(int i=0;i<alphabet.length();i++){
                    if(ch == alphabet.charAt(i)){
                        m = i;
                        c = (m+k)%26;
                        char newch = alphabet.charAt(c);
                        text.setCharAt(j,newch);
                    }
                }
            }
        }
        return text;
    }
    public static StringBuffer decryption(StringBuffer text1){
        for (int j=0;j<text1.length();j++){
            ch = getText1.charAt(j);
            if(ch != ' ' && ch != '.'){
                for(int i=0;i<alphabet.length();i++){
                    if(ch == alphabet.charAt(i)){
                        m = (i-k+26)%26;

                        text1.setCharAt(j, alphabet.charAt(m));
                    }
                }
            }
        }
        return text1;
    }

}


