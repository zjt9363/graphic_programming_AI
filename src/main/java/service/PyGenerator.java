package service;

import config.SpringConfig;
import net.NetComponent;
import net.NetConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import utils.Pair;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @author Zarrow
 */
public class PyGenerator {


    static protected void fileGenerator(byte[] bytes) throws IOException {
        OutputStream outputStream = new FileOutputStream("main.py");
        try {
            outputStream.write(bytes);
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            outputStream.close();
        }
    }



    static public void codeGenerator(NetConfig netConfig) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        NetComponent netComponent = ctx.getBean(NetComponent.class);

        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(netComponent.importPackage());
        stringBuilder.append(netComponent.gpuConfig());
        stringBuilder.append(netComponent.loadData(netConfig));
        stringBuilder.append(netComponent.init(netConfig));
        if (netConfig.isToCategorical()){
            stringBuilder.append(netComponent.toCategorical());
        }
        stringBuilder.append(netComponent.netBuild());

        Boolean flag = true;
        for (Pair<String, HashMap<String,String>> pair : netConfig.getArrayList()){
            if (flag){
                pair.snd.put("input_shape","input_shape");
                flag = false;
            }
            stringBuilder.append(netComponent.addLayer(pair.fst, pair.snd));
        }

        stringBuilder.append(netComponent.summary());
        stringBuilder.append(netComponent.compile(netConfig));
        stringBuilder.append(netComponent.earlyStopping());
        stringBuilder.append(netComponent.fit());
        stringBuilder.append(netComponent.evaluate());
        stringBuilder.append(netComponent.print());

        String data = stringBuilder.toString();

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        fileGenerator(bytes);
    }
}
