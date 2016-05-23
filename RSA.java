import java.math.BigInteger;

import static java.lang.Math.pow;
/**
 * Created by deepak on 5/12/16.
 */
public class RSA {
    public static void main(String[] args) {
        int p=3,q=7,n=p*q,e = 0,d = 0;
        int totient = (p-1)*(q-1);

        for(int i=2;i<totient;i++){
            int val = hcf(i,totient);
            if(val != 0){
                e = val;
                System.out.println(e);
                break;
            }
        }

        for(int i=0;i<100;i++){
            if((e*i)% totient == 1)
            {
                d = i;
                break;
            }
        }
        encrypt(e,n,d);

    }

    public static int hcf(int a, int b){
        BigInteger bi1, bi2,bi3;
        bi1 = new BigInteger(String.valueOf(a));
        bi2 = new BigInteger(String.valueOf(b));
        bi3 =  bi1.gcd(bi2);
//        return Integer.parseInt(String.valueOf(bi3));
        if (Integer.parseInt(String.valueOf(bi3)) == 1){
            return a;
        }
        else {
            return 0;
        }
    }

    public static void encrypt(int e, int n, int d){
        double c,m=2;
        c = pow(m,e)%n;
        System.out.println("Encrypted is->"+c);
        decrypt(c,d,n);
    }

    public static void decrypt(double c, int d, int n){
        int m;
        m= (int) pow(c,d) % n;
        System.out.println("Decrypted is->"+m);
    }
}
