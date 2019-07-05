import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Base64;

public class Base64UtilMain {

    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\Administrator\\Desktop\\12.jpg");

        String e = Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
        FileUtils.writeStringToFile(new File("C:\\Users\\Administrator\\Desktop\\12-encode.txt"),e);

//        System.out.println(FileUtils.readFileToByteArray(file));
    }
}
