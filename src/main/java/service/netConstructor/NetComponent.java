package service.netConstructor;


//import net.NetConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import service.utils.Util;

import java.util.HashMap;

/**
 * @author Zarrow
 */

@Service
public class NetComponent {
    @Autowired
    @Value("${importPackage}")
    String importPackage;
    @Value("${gpuConfiguration}")
    String gpuConfiguration;
    @Value("${loadFile}")
    String loadFile;
    @Value("${init}")
    String init;
    @Value("${toCategorical}")
    String toCategorical;
    @Value("${addModel}")
    String addModel;
    @Value("${netBuild}")
    String netBuild;
    @Value("${summary}")
    String summary;
    @Value("${earlyStopping}")
    String earlyStopping;
    @Value("${fit}")
    String fit;
    @Value("${evaluate}")
    String evaluate;
    @Value("${print}")
    String print;
    @Value("${compile}")
    String compile;

    public String importPackage(){
        return importPackage;
    }

    public String gpuConfig(){
        return gpuConfiguration;
    }

    public String loadData(NetConfig netConfig){
        return String.format(loadFile, netConfig.getFileName());
    }

    public String init(NetConfig netConfig){
        return String.format(init, netConfig.getNumClasses());
    }

    public String toCategorical(){
        return toCategorical;
    }

    public String addLayer(String functionName, HashMap<String,String> m){
        return addModel + functionName + "(" + Util.layerParameterToString(m) + "))\n";
    }

    public String netBuild(){
        return netBuild;
    }

    public String summary() {
        return summary;
    }

    public String earlyStopping() {
        return earlyStopping;
    }

    public String fit() {
        return fit;
    }

    public String evaluate() {
        return evaluate;
    }

    public String print() {
        return print;
    }

    public String compile(NetConfig netConfig) {
        StringBuilder stringBuilder = new StringBuilder(compile);
        if (netConfig.isToCategorical()){
            stringBuilder.delete(20,27);
        };
        return stringBuilder.toString();
    }
}
