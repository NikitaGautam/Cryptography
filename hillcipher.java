import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;


public class hillcipher {
    public static String cipher;
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static ArrayList<Integer> charIndex = new ArrayList<Integer>();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner in = new Scanner(System.in);
        System.out.println("enter size of matrix->");
        int size = input.nextInt();
        int[][] matrix = new int[size][size];
        String plainText = "race";
        ArrayList<Integer> charIndex = new ArrayList<Integer>();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.println("Enter Key at: "+i+" "+j+" ->");
                matrix[i][j] = input.nextInt();
            }
        }
        StringBuffer cipher =  matrixMultiply(matrix,size,plainText);
        System.out.println("Encrypted text is-> "+ cipher);
        System.out.println("Enter text to be decrypted->");
        String cipher1 = in.nextLine();
        System.out.println("Enter the decryption key->");
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.println("Enter Key at: "+i+" "+j+" ->");
                matrix[i][j] = input.nextInt();
            }
        }
        decryption(cipher1, matrix,size);

    }

    public static StringBuffer matrixMultiply(int[][] matrix,int size, String plainText){
        StringBuffer plain = new StringBuffer(plainText);
        int index1, index2;
        int k,temp=0,check=0;
        while(check<plain.length()){
            for(int i=0;i<size;i++){
                if(temp<2){
                    k= 0;
                }
                else {
                    k =2;
                }
                int sum =0;
                for(int j=0;j<size;j++){
                    char c = plainText.charAt(k);
                    index1 = calculateIndex(c);
                    sum += matrix[i][j]*index1;
                    k+=1;
                }
                char chNew = getCharAtIndex(sum);
                plain.setCharAt(temp,chNew);
                temp++;
            }
            check +=2;
        }


        return plain;
    }

    public static int calculateIndex(char c){
        int index=0;
        for(int i=0;i<alphabet.length();i++){
            char ch = alphabet.charAt(i);
            if(c == ch){
                index = i;
            }
        }
        return index;
    }

    public static char getCharAtIndex(int sum){
       int temp =0;
        while(sum<0){
                sum +=26;
        }
        sum = sum%26;
        return alphabet.charAt(sum);

    }


   public static void decryption(String cipher, int matrix[][], int size){
           int det = 0,detA=0;
           det = matrix[0][0]*matrix[1][1]- matrix[0][1]*matrix[1][0];
           det = det %26;
          while(det <0){
              det = det+26;
          }
       BigInteger bi1, bi2,bi3,bi4;
       bi1 = new BigInteger(String.valueOf(det));
       bi2 = new BigInteger("26");
       bi3 =  bi1.gcd(bi2);
       bi4 = new BigInteger("1");
       int value = bi3.compareTo(bi4);
       if(value == 0) {
           for (int i = 1; i < 26; i++) {
               if ((det * i) % 26 == 1){
                   detA = i;
               }
           }
           System.out.println(detA);
           for (int i = 0; i < size; i++) {
               for (int j = 0; j < size; j++) {
                   if (i == j) {
                       int temp = matrix[i][j];
                       matrix[i][j] = valueMod(matrix[size-1][size-1],detA);
                       matrix[size-1][size-1] = valueMod(temp,detA);
                       break;
                   } else {
                        matrix[i][j] = valueMod(-matrix[i][j],detA);
                       matrix[size - 1 - i][size - 1 - j] = valueMod(-matrix[size - 1 - i][size - 1 - j],detA);
                       break;
                   }

               }
           }
           System.out.println(matrixMultiply(matrix,size,cipher));
       }else {
           System.out.println("Cipher cannot be decrypted");
       }


   }

   public static int valueMod(int value, int detA){
       while(value < 0){
           value +=26;
       }
       value = value * detA;
       value = value % 26;
       return value;
   }

}




