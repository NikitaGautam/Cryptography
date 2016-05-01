import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by deepak on 4/1/16.
 */
public class DES {
    public static String plainBinary;
    public static void main(String[] args) {
        String plaintext = "0123456789ABCDEF";
       String key = "133457799BBCDFF1";
        System.out.println("PlainText->" + plaintext +"\n");
        System.out.println("Key->"+key);
       int[] pc1 = {57,49,41,33,25,17,9,1,58,50,42,34,26,18,10,2,59,51,43,35,27,19,11,3,60,52,44,36,63,55,47,39,31,23,15,7,62,54,46,38,30,22,14,6,61,53,45,37,29,21,13,5,28,20,12,4};
//        SBox s= new SBox();
        plainBinary = hexToBinary(plaintext);
        String keyBinary = hexToBinary(key);
       getK(keyBinary,pc1);



    }
    public static String hexToBinary(String hex) {
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);

            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }
//         System.out.println(bin);
        return bin;
    }

    public static void getK(String key, int[] pc1){
        ArrayList<String> c = new ArrayList<String>();
        ArrayList<String> d = new ArrayList<String>();
        StringBuffer kPlus = new StringBuffer();
         for(int i=0;i<pc1.length;i++){
//             System.out.print(key.charAt(pc1[i] - 1));
             kPlus.append(key.charAt(pc1[i]-1));
         }
//       System.out.println("Key Plus->"+ kPlus);
       int len = kPlus.length();
        c.add(0,kPlus.substring(0,len/2));
        d.add(0,kPlus.substring(len/2,len));
        List<Integer> iteration = Arrays.asList(1,1,2,2,2,2,2,2,1,2,2,2,2,2,2,1);

       for(int i=1;i<=16;i++){
            c.add(rotate(c.get(i - 1), iteration.get(i - 1)));
            d.add(rotate(d.get(i - 1), iteration.get(i - 1)));
       }

      subKeys(c,d);
    }



    public static String rotate(String aL, int shift)
        {
            if (aL.length() == 0)
                return aL;
            aL = aL.substring(shift,aL.length()) + aL.substring(0,shift);

            return aL;
        }

    public static void subKeys(ArrayList<String> c, ArrayList<String> d){
        ArrayList<Integer> pc2 = new ArrayList<Integer>(Arrays.asList(14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32));
        ArrayList<String> k = new ArrayList<String>();
        ArrayList<String> kNew = new ArrayList<String>();
        for(int i=1;i<=16;i++){
            k.add(c.get(i) +d.get(i));
        }

        for(int j=0;j<k.size();j++){
            String ele = k.get(j);
            String kTemp = new String();
            for(int i=0;i<pc2.size();i++){
                 kTemp += ele.charAt(pc2.get(i) - 1);
            }
             kNew.add(kTemp);
        }
        plainBlock(kNew);

    }
//Encoding starts
    public static void plainBlock(ArrayList<String> kNew){
        ArrayList<Integer> ip = new ArrayList<Integer>(Arrays.asList(58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7));
        String plain = new String();
        for(int i=0;i<ip.size();i++){
            plain +=  plainBinary.charAt(ip.get(i) - 1);
        }
//        System.out.println(plain);
        ArrayList<String> l = new ArrayList<String>();
        ArrayList<String> r = new ArrayList<String>();
        ArrayList<String> finalString = new ArrayList<String>();
        int len = plain.length();
        l.add(plain.substring(0,len/2));
        r.add(plain.substring(len/2,len));
        for(int i=1;i<=16;i++){
            l.add(r.get(i-1));
            r.add(convertR(l.get(i-1),r.get(i-1), kNew.get(i-1)));

        }
        finalString.add(r.get(r.size()-1)+l.get(l.size()-1));
//        System.out.println(finalString);

        ArrayList<Integer> ipInverse = new ArrayList<Integer>(Arrays.asList(40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25));
        String cipherText = new String();

        String subString = finalString.get(0);
        for(int i=0;i<ipInverse.size();i++){
            cipherText += subString.charAt(ipInverse.get(i) - 1) ;
        }
//        System.out.println(cipherText);
        cipherTextGeneration(cipherText);

    }

    public static String convertR(String l,String r, String k){
        ArrayList<Integer> eBitTable = new ArrayList<Integer>(Arrays.asList(32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1));
         String eR = new String();
        for(int i=0;i<eBitTable.size();i++){
            eR +=  r.charAt(eBitTable.get(i) - 1);
        }
//        System.out.println(eR);
        String keR = new String();
        for(int i=0;i<eR.length();i++){
            keR += convertXOR((int)eR.charAt(i),(int)k.charAt(i));
        }
//      System.out.println(keR);
        SBox newBox = new SBox();
        String partString = new String();
        partString += newBox.forS1(keR.substring(0, 6));
        partString += newBox.forS2(keR.substring(6, 12));
        partString += newBox.forS3(keR.substring(12, 18));
        partString += newBox.forS4(keR.substring(18, 24));
        partString += newBox.forS5(keR.substring(24, 30));
        partString += newBox.forS6(keR.substring(30, 36));
        partString += newBox.forS7(keR.substring(36, 42));
        partString += newBox.forS8(keR.substring(42, 48));

//       System.out.println(partString);

        ArrayList<Integer> pTable = new ArrayList<Integer>(Arrays.asList(16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25));
        String eRNew = new String();
        for(int i=0;i<pTable.size();i++){
            eRNew +=  partString.charAt(pTable.get(i) - 1);
        }

        String keLast = new String();
        for(int i=0;i<l.length();i++){
            keLast += convertXOR((int)l.charAt(i),(int)eRNew.charAt(i));
        }


       return keLast;

    }

    public static int convertXOR(int r, int k)
    {
        return r^k;
    }

    static class SBox {

        public StringBuilder forS1(String str) {
            Integer[][] sTable = new Integer[][]{
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS2(String str) {
            Integer[][] sTable = new Integer[][]{
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS3(String str) {
            Integer[][] sTable = new Integer[][]{
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS4(String str) {
            Integer[][] sTable = new Integer[][]{
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS5(String str) {
            Integer[][] sTable = new Integer[][]{
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS6(String str) {
            Integer[][] sTable = new Integer[][]{
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS7(String str) {
            Integer[][] sTable = new Integer[][]{
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }

        public StringBuilder forS8(String str) {
            Integer[][] sTable = new Integer[][]{
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            };
            StringBuilder fourLengthBinary = calculateS(sTable, str);
            return fourLengthBinary;
        }


        public StringBuilder calculateS(Integer[][] sTable, String str) {
            StringBuilder fourLengthBinary = new StringBuilder();
            String rowString = String.valueOf(str.charAt(0)) + String.valueOf(str.charAt(5));
            int row = Integer.parseInt(rowString, 2);

            String colString = String.valueOf(str.substring(1, 5));
            int col = Integer.parseInt(colString, 2);
            String temp = Integer.toBinaryString(sTable[row][col]);

            fourLengthBinary.append(temp);
            for (int i = fourLengthBinary.length(); i < 4; i++) {
                fourLengthBinary.reverse().append("0").reverse();
            }
            return fourLengthBinary;
        }

        public StringBuilder permutateAfterSBox(StringBuilder str) {
            ArrayList<Integer> pTable = new ArrayList<Integer>(Arrays.asList(
                    16, 7, 20, 21,
                    29, 12, 28, 17,
                    1, 15, 23, 26,
                    5, 18, 31, 10,
                    2, 8, 24, 14,
                    32, 27, 3, 9,
                    19, 13, 30, 6,
                    22, 11, 4, 25)
            );
            StringBuilder finalStr = new StringBuilder();
            for (int i = 1; i <= pTable.size(); i++) {
                int indexFrompcTable = pTable.get(i - 1);
                finalStr.append(str.charAt(indexFrompcTable - 1));
            }
            return finalStr;
        }
    }

    public static void cipherTextGeneration(String cipher) {
        System.out.println("\nCipher Text is as follows->");
        String cipherText = new String();
       for(int i=0;i<cipher.length();i+=4){
           cipherText += Integer.toHexString(Integer.parseInt(cipher.substring(i,i+4), 2));
       }
        System.out.println(cipherText);
    }
}


