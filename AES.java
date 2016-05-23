import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by deepak on 5/19/16.
 */
public class AES {
    public static void main(String[] args) {
        String[] input ={"EA", "04", "65", "85", "83", "45", "5D", "96", "5C", "33", "98", "B0", "F0", "2D", "AD", "C5"};
        String[] key = {"E5", "83", "5C", "F0", "04", "65", "33", "2D", "65", "5D", "99", "AD", "85", "96", "B0", "CC"};
       String[] inputF = new String[input.length];
        for(int i=0;i<input.length;i++){
           BigInteger i1 = new BigInteger(input[i], 16);
           BigInteger i2 = new BigInteger(key[i], 16);
           BigInteger res = i1.xor(i2);
           inputF[i] = res.toString(16).toUpperCase();
           System.out.println(inputF[i]);
       }

        sBox(input);
    }

    public static void sBox(String[] input){
        String[][] s_box={{"63", "7C", "77", "7B", "F2","6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76"},
                { "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C","A4", "72", "C0"},
                {"B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15"},
                {"04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75"},
                {"09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84"},
                {"53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF"},
                {"D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8"},
                {"51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2"},
                {"CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73"},
                {"60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB"},
                {"E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79"},
                {"E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08"},
                {"BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A"},
                {"70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E"},
                {"E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF"},
                {"8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16"}};

       HashMap<Character,Integer> hMap = new HashMap<Character, Integer>();

        String inputChar = "0123456789ABCDEF";

        for(int i=0;i<inputChar.length();i++) {
            hMap.put(inputChar.charAt(i),i);
        }


//        System.out.println(hMap);
        String[] newBox = new String[input.length];
        for(int i=0;i<input.length;i++){
            String temp = input[i];
            int j = hMap.get(temp.charAt(0));
            int k = hMap.get(temp.charAt(1));

            String value = s_box[j][k];
            newBox[i] = value;
//            System.out.println(newBox[i]);
        }
        shiftRow(newBox);

    }

    public static void shiftRow(String[] newB) {
        String[][] dimNew = new String[4][4];
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                dimNew[i][j] = newB[count];
                count++;
            }
        }
        String[] toShift= new String[4];
        int shiftValue =0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dimNew[i][j] + " ");
            }
            System.out.println("\n");
        }
        System.out.println("----");
       for(int i=0;i<4;i++){
           toShift = dimNew[i];
           dimNew[i] = shift(toShift,shiftValue);
           shiftValue++;
       }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dimNew[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public static String[] shift(String[] in, int val){

      for(int j=0;j<val;j++){
           String last = in[0];
           for(int i=0;i<in.length-1;i++){
               in[i] = in[i+1];
           }
           in[in.length-1] = last;

       }

     return in;
    }


}
