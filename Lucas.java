import java.util.Scanner;

import static java.lang.StrictMath.pow;

/**
 * Created by deepak on 5/4/16.
 */
public class Lucas {
    public static void main(String[] args) {
        System.out.println("---Test For Prime Numbers---");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number to be tested->");
        int p = input.nextInt();
        int s =4;
        int m = (int) (pow(2,p))-1;
        System.out.println(m);
        for(int i=1;i<=(p-2);i++){
            s = ((s*s) -2) % m;
            if(s==0){
                System.out.println("Prime");
                break;
            }

        }
        if(s != 0){
            System.out.println("Composite");
        }


    }
}
