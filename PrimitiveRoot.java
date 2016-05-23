import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Math.pow;

/**
 * Created by deepak on 5/5/16.
 */
public class PrimitiveRoot {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number to be checked->");
        int p = input.nextInt();
//        HashMap<Integer,Integer> hash = new HashMap<Integer, Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int a =3,key=0;
        for(int i=1;i<p;i++){
          int val = (int) (pow(3,i)%p);
            if(list.contains(val)){
                key++;
            }
            list.add(val);
        }
        if(key == 0){
            System.out.println("Is primitive root");
        }
        else{
            System.out.println("Is not primitive root");
        }


    }
}
