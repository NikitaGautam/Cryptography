import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by deepak on 5/5/16.
 */
public class EulerTotient {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Input Number->");
        int n = input.nextInt();
        int count=0;

        for(int i=1;i<n;i++){
            int temp = hcf(n,i);
            if(temp == 1){
                count +=1;
            }
        }
        System.out.println("si(x)->"+count);
    }

    public static int hcf(int a, int b){
        BigInteger bi1, bi2,bi3;
        bi1 = new BigInteger(String.valueOf(a));
        bi2 = new BigInteger(String.valueOf(b));
        bi3 =  bi1.gcd(bi2);
        return Integer.parseInt(String.valueOf(bi3));
    }
}
