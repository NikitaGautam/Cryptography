import java.util.Scanner;

/**
 * Created by deepak on 2/25/16.
 */
public class vigenere {
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input1 = new Scanner(System.in);
        String key,text,key1,text1;
        System.out.println("Enter the text to be encrypted->");
        text = input.nextLine();
        System.out.println("Enter the key->");
        key = input.next();
        System.out.println("Encrypted text is->" + encryption(text,key));

        System.out.println("Enter the text to be decrypted->");
        text1 = input1.nextLine();
        System.out.println("Enter the key->");
        key1 = input1.next();
        System.out.println("Decrypted text is->" + decryption(text1, key1));


    }
    public static StringBuffer encryption(String text, String key){
           text = text.toLowerCase();
           StringBuffer ency = new StringBuffer(text);
           key = key.toLowerCase();
           int i =0;
           char c,ch;
           int index1 =0, index2 = 0;
          while(i<ency.length()){
              c = ency.charAt(i);
              if(c != ' ' &&  c!= '.'){
                  int j =0;
                  while(j<key.length()){
                      ch = key.charAt(j);
                      c = ency.charAt(i);
                      if(c == ' '){
                          ++i;
                          c = ency.charAt(i);
                      }
                      for(int k=0; k<alphabet.length();k++){
                          if(ch == alphabet.charAt(k)){
                              index2 = k;
                          }
                          if(c == alphabet.charAt(k)){
                              index1 = k;
                          }
                      }
                      index1 = (index1+index2)%26;
                      char txt = alphabet.charAt(index1);
                      ency.setCharAt(i,txt);
                      ++i;
                      ++j;
                      if (i == ency.length()){
                          break;
                      }

                  }
              }
              else {
                  ++i;
              }

          }

        return ency;
    }
    public static StringBuffer decryption(String text, String key) {
        text = text.toLowerCase();
        StringBuffer ency = new StringBuffer(text);
        key = key.toLowerCase();
        int i =0;
        char c,ch;
        int index1 =0, index2 = 0;
        while(i<ency.length()){
            c = ency.charAt(i);
            if(c != ' ' &&  c!= '.'){
                int j =0;
                while(j<key.length()){
                    if (i == ency.length()){
                        break;
                    }
                    ch = key.charAt(j);
                    c = ency.charAt(i);
                    if(c == ' '){
                        ++i;
                        c = ency.charAt(i);
                    }
                    for(int k=0; k<alphabet.length();k++){
                        if(ch == alphabet.charAt(k)){
                            index2 = k;
                        }
                        if(c == alphabet.charAt(k)){
                            index1 = k;
                        }
                    }
                    index1 = (index1 - index2 +26)%26;
                    char txt = alphabet.charAt(index1);
                    ency.setCharAt(i,txt);
                    ++i;
                    ++j;

                }
            }
            else {
                ++i;
            }

        }

        return ency;

    }

}
