/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pojos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author IaraDenisse
 */
public class CopiarImagenes {
    
     public void copy(Path h,String s) throws NoSuchFileException, IOException{
        
        Path a = Paths.get(System.getProperty("user.dir"),"src/Empleados_Image",s + ".jpeg");
        
        Files.copy(h, a, REPLACE_EXISTING);
    
    }
    
}
