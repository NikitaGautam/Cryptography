import java.io.File;
import java.io.IOException;

/**
 * Created by deepak on 3/24/16.
 */
public class virusFile {
    public static void main(String[] args) {
        int i=0;
        while(i<=5){
            try{
                File f=new File("/Users/deepak/Desktop/try"+i+".xls");
                if (f.createNewFile()){
                    System.out.println("created");
                }
                else {
                    System.out.println("Nop!");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            i++;
        }

    }
}
