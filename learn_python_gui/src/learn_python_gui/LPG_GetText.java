
// @author duck

package learn_python_gui;
        
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

public class LPG_GetText {
    
    BufferedReader buffer;
    String line;
    String result = "";
    
    public String readFile(String file){
        result = "";
        
        try {
            InputStream in = getClass().getResourceAsStream(file);
            Reader fis = new InputStreamReader(in, "utf-8");
            buffer = new BufferedReader(fis);
            
            while ((line = buffer.readLine()) != null){
            result = result + buffer.readLine();
        
        }
            
        } catch (Exception e) {
            System.out.println("LPG_GetText: can't open file");
        }
       return result;
    }
}
