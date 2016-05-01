import java.util.*;

/**
 * Created by deepak on 2/28/16.
 */
public class ceaserattack {
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static StringBuffer decrypted;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        alphabet = alphabet.toUpperCase();
        int counter;
        String cipher = "KHOOR ZRUOG";
        double[] frequency = {0.080,0.015,0.030,0.040,0.130,0.020,0.015,0.060,0.065,
                0.005,0.005,0.035,0.030,0.070,0.080,0.020,0.002,0.065
                ,0.060,0.090,0.030,0.010,0.015,0.005,0.020,0.002};
        HashMap<Character, Double> initialFrequency =  calcFreq(cipher);
        HashMap<Character, Double> universalHashMap = new HashMap<Character, Double>();
        for (int i=0;i<frequency.length;i++){
            char c = alphabet.charAt(i);
            universalHashMap.put(c,frequency[i]);
        }
        HashMap<Integer,Double> finalValue = calcSi(initialFrequency, universalHashMap, alphabet);

        ArrayList<Double> siValues = new ArrayList<Double>();
        ArrayList<Double> siValuesSorted = new ArrayList<Double>();
        Set set=  finalValue.entrySet();
        Iterator k = set.iterator();
        while (k.hasNext()){
                Map.Entry me = (Map.Entry)k.next();
                siValues.add((Double) me.getValue());
                siValuesSorted.add((Double) me.getValue());
            }

        Collections.sort(siValuesSorted,Collections.reverseOrder());
        int key;
        for(int i=0;i<siValuesSorted.size();i++){
            key = siValues.indexOf(siValuesSorted.get(i));
//            System.out.println(key);
          StringBuffer newString = decryption(cipher,key);
            System.out.println(newString);
            System.out.println("Is original text Yes->1 No->0 :");
            counter = input.nextInt();
            if(counter == 1){
                break;
            }
            else {
                continue;
            }
        }




    }
    public static StringBuffer decryption(String cipher, int k){

       decrypted = new StringBuffer(cipher);
        for (int j=0;j<decrypted.length();j++){
            char ch = decrypted.charAt(j);
            if(ch != ' ' && ch != '.'){
                for(int i=0;i<alphabet.length();i++){
                    if(ch == alphabet.charAt(i)){
                        int m = (i-k+26)%26;
                        decrypted.setCharAt(j, alphabet.charAt(m));
                    }
                }
            }
        }
        return decrypted;
    }
    public static HashMap<Integer,Double> calcSi (HashMap<Character, Double> initialFrequency, HashMap<Character, Double> universalFrequency, String alphabet){
        HashMap<Integer, Double> siHashMap = new HashMap<Integer, Double>();
        ArrayList<Character> alphabetArray= new ArrayList<Character>();
        for(int i=0;i<alphabet.length();i++){
            alphabetArray.add(alphabet.charAt(i));
        }
        for(int i=0;i<universalFrequency.size();i++){
             Double siSum = 0.0;
            for(int j =0;j<initialFrequency.size();j++){
                Set set=  initialFrequency.entrySet();
                Iterator k = set.iterator();
                ArrayList<Character> arrayList = new ArrayList<Character>();
                while (k.hasNext()){
                    Map.Entry me = (Map.Entry)k.next();
                    arrayList.add((Character) me.getKey());
                }
                int index =alphabetArray.indexOf(arrayList.get(j));
                char c = alphabetArray.get((index-i+26)%26);
                Double value =  universalFrequency.get(c);
                siSum += initialFrequency.get(arrayList.get(j)) * value;
            }
            siHashMap.put(i,siSum);
        }
        return siHashMap;

    }

    public static HashMap<Character, Double> calcFreq(String cipher){
        int length = cipher.length();
        int lengthNew = length;
        for(int i=0;i<length;i++){
            char c = cipher.charAt(i);
            if(c == ' '){
                  lengthNew -= 1;
            }
        }
        LinkedHashMap<Character, Double> frequencyHashMap = new LinkedHashMap<Character, Double>();
        for(int i=0;i<length;i++){
            char c = cipher.charAt(i);
            if (c != ' '){
                if(frequencyHashMap.containsKey(c)){
                   //nothing
                }
                else {
                    double index= 0;
                    for(int j=i;j<length;j++){
                        char ch = cipher.charAt(j);
                        if (c == ch) {
                            index++;
                        }
                    }
                index = index/lengthNew;
                    frequencyHashMap.put(c,index);
                }

            }
            else {
                 continue;
            }
        }
        return frequencyHashMap;

    }
}
