import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by deepak on 3/2/16.
 */
public class polyfair {
    public static   String[][] matrix = new String[5][5];
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        String key,text;
        String alphabet = "abcdefghiklmnopqrstuvwxyz";
        System.out.println("Enter the text to be encrypted->");
        text = input.nextLine();
        text = text.toLowerCase();
        System.out.println("Enter the key->");
        key = input.next();
        key = key.toLowerCase();
        int keyLength = key.length();
        char[][] matrix = new char[5][5];
        int i =0;
        StringBuffer newKey = checkRepeatedKey(key);
        StringBuffer newAlpha = remainingCharacter(alphabet,newKey);
        insertIntoMatrix(newKey,newAlpha);

        StringBuffer plain = checkRepeatedPlain(text);
        encryption(plain);

    }

    public static StringBuffer checkRepeatedKey(String key){
        StringBuffer newString = new StringBuffer(key);
         for(int i=0;i<newString.length();i++){
             char ch = newString.charAt(i);
             for(int j=i+1;j<newString.length();j++){
                 char c = newString.charAt(j);
                 if(ch == c){
                       newString.deleteCharAt(j);
                 }
             }
         }
        return  newString;
    }

    public static StringBuffer checkRepeatedPlain(String text){
        StringBuffer plainText = new StringBuffer(text);
        int i;
        for(i=0;i<plainText.length()-1;i+=2){
            char c = plainText.charAt(i);
            char ch = plainText.charAt(i+1);
            if(c == ch){
                plainText.insert(i+1,'x');
            }
            if(c == ' '){
                plainText.deleteCharAt(i);
            }
            if(ch == ' '){
                plainText.deleteCharAt(i+1);
            }
        }
        if(plainText.length()%2 != 0){
            plainText.append('x');
        }
        return plainText;
    }

    public static void insertIntoMatrix(StringBuffer key, StringBuffer alphabet){
        int k =0;
        int a =0;
        for(int i=0;i<5;i++){
             for(int j=0;j<5;j++) {
                 if (k < key.length()) {
                     matrix[i][j] = String.valueOf(key.charAt(k));
                     k++;
                 } else {
                     if (a < alphabet.length()) {
                         matrix[i][j] = String.valueOf(alphabet.charAt(a));
                         a++;
                     }
                 }
//               System.out.println(matrix[i][j]);
             }

         }



    }


    public static StringBuffer remainingCharacter(String alphabet, StringBuffer newKey){
        StringBuffer newAlphabet = new StringBuffer(alphabet);
        for(int i=0;i<newKey.length();i++){
            char ch = newKey.charAt(i);
            for(int j=0;j<newAlphabet.length();j++){
                char c = newAlphabet.charAt(j);
                if(c == ch){
//                  System.out.println(c);
                    newAlphabet.deleteCharAt(j);
                }
            }
        }


        return  newAlphabet;
    }

    public static void encryption(StringBuffer plain){
        int i=0,j=0,k=0;
        int x1=0,x2 = 0,y1=0,y2=0;
         for(i=0;i<plain.length()-1;i+=2){
             char c = plain.charAt(i);
//             String c = plain.toString().charAt(i);
             char ch = plain.charAt(i+1);
             for(j=0;j<5;j++){
                 for(k=0;k<5;k++){
                     char[] chchecked = matrix[j][k].toCharArray();
                     if(c == chchecked[0]){
                         x1 = j;
                         y1 = k;
                     }
                     if(ch == chchecked[0]){
                         x2= j;
                         y2 =k;
                     }
                 }
             }

             if(x1 == x2){
                 y1++;
                 y2++;
                 if(y1 >= 5){
                     y1 = 0;
                 }
                 if(y2 >= 5){
                     y2 = 0;
                 }
                 char[] chchecked = matrix[x1][y1].toCharArray();
                 plain.setCharAt(i,chchecked[0]);
                 char[] chchecked1 = matrix[x2][y2].toCharArray();

                 plain.setCharAt(i+1,chchecked1[0]);
                 continue;
             }
             if(y1 == y2){
                 x1++;
                 x2++;
                 if(x1 >= 5){
                     x1 = 0;
                 }
                 if(x2 >= 5){
                     x2 = 0;
                 }
                 char[] chchecked = matrix[x1][y1].toCharArray();
                 plain.setCharAt(i,chchecked[0]);
                 char[] chchecked1 = matrix[x2][y2].toCharArray();
                 plain.setCharAt(i+1,chchecked1[0]);
                 continue;
             }
             else {
                 char[] chchecked = matrix[x1][y2].toCharArray();
                 plain.setCharAt(i,chchecked[0]);
                 char[] chchecked1 = matrix[x2][y1].toCharArray();
                 plain.setCharAt(i+1,chchecked1[0]);
                 continue;
             }

         }


        System.out.println("Encrypted Text->"+ plain);
    }

}
