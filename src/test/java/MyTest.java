import org.junit.Test;
import service.netConstructor.NetConfig;
import service.PyGenerator;
import service.PyRun;
import service.utils.NetText;
import service.utils.Pair;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyTest {
    @Test
    public void testAutoGenerate() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        PyGenerator pyGenerator = new PyGenerator();
        ArrayList<Pair<String, HashMap<String, String>>> arrayList= new ArrayList<>();
        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.conv2D, new HashMap<String, String>()));
        arrayList.get(0).snd.put(NetText.filters,"128");
        arrayList.get(0).snd.put(NetText.kernalSize,"6,6");
        arrayList.get(0).snd.put(NetText.activation,"relu");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.conv2D, new HashMap<String, String>()));
        arrayList.get(1).snd.put(NetText.filters,"128");
        arrayList.get(1).snd.put(NetText.kernalSize,"6,6");
        arrayList.get(1).snd.put(NetText.activation,"relu");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.maxPooling2D, new HashMap<String, String>()));
        arrayList.get(2).snd.put(NetText.poolSize, "2,2");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.dropout, new HashMap<String, String>()));
        arrayList.get(3).snd.put(NetText.rate,"0.5");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.flatten,new HashMap<String, String>()));

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.dense,new HashMap<String, String>()));
        arrayList.get(5).snd.put(NetText.units,"128");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.dropout, new HashMap<String, String>()));
        arrayList.get(6).snd.put(NetText.rate,"0.5");

        arrayList.add(new Pair<String, HashMap<String, String>>(NetText.dense, new HashMap<String, String>()));
        arrayList.get(7).snd.put(NetText.units,"10");
        arrayList.get(7).snd.put(NetText.activation,"softmax");

        NetConfig netConfig = new NetConfig();

        netConfig.setArrayList(arrayList);

        netConfig.setFileName("mnist.pkl.gz");

        netConfig.setNumClasses(10);

        netConfig.setToCategorical(true);

        PyGenerator.codeGenerator(netConfig);
    }

    @Test
    public void runCode(){
        PyRun.run("main.py");

    }

    @Test
    public void stopRunning(){
        PyRun.destroy();
    }
}
