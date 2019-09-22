package cm.plugins;

import cm.plugins.entity.Copy;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Mojo(name="copy",defaultPhase= LifecyclePhase.PACKAGE)
public class MyMojo extends AbstractMojo {
    private CopyFile copyFile=new CopyFile();

    public void execute(){
        System.out.println("cm copy plugin running~~~：" + this.msg);
        try {
            executeCopy();
        }catch (Exception e){}
    }

    public void executeCopy() throws Exception{
        if(copys==null||copys.size()<=0)return;
        for(Copy i:copys){
            copyFile.copyDir(Paths.get(i.getFrom()),Paths.get(i.getTo()));
        }
    }

    @Parameter
    private String msg;

    @Parameter
    private List<Copy> copys;
}
