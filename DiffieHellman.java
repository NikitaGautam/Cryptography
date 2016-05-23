import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.random;

/**
 * Created by deepak on 5/12/16.
 */
public class DiffieHellman {
    public static void main(String[] args) {
        double xa,ya,xb,yb,ka,kb;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter prime number->");
        int p = input.nextInt();
        System.out.println("Enter primitive root of p->");
        int g = input.nextInt();
        if(g<p){
            int value = primitiveRoot(p,g);
            if (value ==0){
                System.out.println("Process Cannot be continued, input is not primitive root");
            }
            else {
//                xa =(int) random()*p;
             xa = 6;
//                System.out.println("User A, select a random value->"+xa);
                ya = pow(g,xa);
                ya = ya %p;
//                xb = (int) random()*p;
                xb = 15;
//                System.out.println("User B, select a random value->"+xb);
                yb = pow(g,xb);
                System.out.println(yb);

                yb = yb % p;
                ka =  pow(yb,xa) % p;
                kb = pow(ya,xb) % p;
                System.out.println("Key is ka->"+ka+" Kb->"+kb);
            }
        }

    }
    public static int primitiveRoot(int g,int p){
        ArrayList<Integer> list = new ArrayList<Integer>();
        int key=0;
        for(int i=1;i<p;i++){
            int val = (int) (pow(g,i)%p);
            if(list.contains(val)){
                key++;
            }
            list.add(val);
        }
        if(key == 0){
            return p;
        }
        else{
          return 0;
        }

    }
}
