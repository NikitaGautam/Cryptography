import java.util.Scanner;


public class RailFence {
    public static String plain;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        System.out.println("Enter no of rails->");
        int rails = in.nextInt();
        System.out.println("Enter plain text to be encrypted->");
        plain = input.nextLine();
        StringBuffer plainWithoutSpace = new StringBuffer(plain.toLowerCase());
        for (int i = 0; i < plainWithoutSpace.length(); i++) {
            if (plainWithoutSpace.charAt(i) == ' ') {
                plainWithoutSpace.deleteCharAt(i);
            }
        }
        encryption(plainWithoutSpace, rails);

    }

    public static void encryption(StringBuffer plain, int rails) {
        int i = 0, j, temp;
        int length = plain.length();
        StringBuffer pt = new StringBuffer(plain);
        char[][] matrix = new char[rails][length];

        while (i < length) {
            for (j = 0; j < rails; j++) {
                if (i == length) {
                    break;
                }
                matrix[j][i] = plain.charAt(i);
                i += 1;
            }

            for (j = rails - 2; j > 0; j--) {
                if (i == length) {
                    break;
                }
                matrix[j][i] = plain.charAt(i);
                i += 1;
            }
        }

        for (i = 0; i < rails; i++) {
            for (j = 0; j < length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = ' ';
                }
            }
        }
        System.out.println("Encrypted Matrix->");
        for (i = 0; i < rails; i++) {
            for (j = 0; j < length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        int k = 0;
        System.out.println("\nEncrypted text->");
        for (i = 0; i < rails; i++) {
            for (j = 0; j < length; j++) {
                if (matrix[i][j] != ' ') {
//                    System.out.print(matrix[i][j]);
                    if (k < length) {
                        pt.setCharAt(k, matrix[i][j]);
                        k++;
                    }
                }
            }
        }

        System.out.println("\n" + pt);
        decryption(pt, length, rails);

    }

    public static void decryption(StringBuffer cipher, int length, int rails) {
        char[][] matrix = new char[rails][length];
        int index = 0, k = 0;
        for (int i = 0; i < rails; i++) {
            int flag = 0;
            for (int j = i; j < length; ) {
                matrix[i][j] = cipher.charAt(index);
                index++;
                if (i == rails - 1 || i == 0) {
                    j = j + 2 * (rails - 1);
                } else {
                    if (flag == 0) {
                        j = j + 2 * (rails - (i + 1));
                        flag = 1;
                    } else {
                        j = j + k;
                        flag = 0;
                    }
                }
            }
            k += 2;
        }

        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = ' ';
                }
            }
        }
        System.out.println("\nDecrypted Matrix->");
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println("\nDecrypted Text->");

        for(int i=0;i<length;i++){
            for(int j=0; j<rails;j++){
                if(matrix[j][i] != ' '){
                    System.out.print(matrix[j][i]);
                }
            }
        }



    }


}


