import net.NetComponent;
import net.NetConfig;
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
        StringBuilder stringBuilder = new StringBuilder();


        stringBuilder.append(NetComponent.importPackage());
        stringBuilder.append(NetComponent.gpuConfig());
        stringBuilder.append(NetComponent.loadData(netConfig));
        stringBuilder.append(NetComponent.init(netConfig));
        if (netConfig.isToCategorical()){
            stringBuilder.append(NetComponent.toCategorical());
        }
        stringBuilder.append(NetComponent.netBuild());

        Boolean flag = true;
        for (Pair<String, HashMap<String,String>> pair : netConfig.getArrayList()){
            if (flag){
                pair.snd.put("input_shape","input_shape");
                flag = false;
            }
            stringBuilder.append(NetComponent.addLayer(pair.fst, pair.snd));
        }

        stringBuilder.append(NetComponent.summary());
        stringBuilder.append(NetComponent.compile(netConfig));
        stringBuilder.append(NetComponent.earlyStopping());
        stringBuilder.append(NetComponent.fit());
        stringBuilder.append(NetComponent.evaluate());
        stringBuilder.append(NetComponent.print());

        String data = stringBuilder.toString();

        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);

        fileGenerator(bytes);
    }
}
