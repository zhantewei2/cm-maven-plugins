package cm.plugins;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CopyFile {
    public void copyFile(Path sourcePath,Path targetPath) throws IOException{
        FileInputStream source=new FileInputStream(sourcePath.toFile());
        FileOutputStream target=new FileOutputStream(targetPath.toFile());
        byte[] bys=new byte[100];
        for(;;){
            int completed=source.read(bys);
            if(completed<=0)break;
            if(completed>=50){
                target.write(bys);
            }else{
                byte[] endBys=Arrays.copyOfRange(bys,0,completed);
                target.write(endBys);
                break;
            }
        }
    }
    public void copyDir(Path sourcePath,Path targetPath) throws IOException{
        File sourceFile=sourcePath.toFile();
        File targetFile=targetPath.toFile();
        if(sourceFile.isDirectory()){
            String[] list=sourceFile.list();
            targetFile.mkdirs();
            for(String i :list) {
                copyDir(sourcePath.resolve(i),targetPath.resolve(i));
            }
        }else if(sourceFile.isFile()){
            Files.copy(sourcePath,targetPath);
        }
    }
}
