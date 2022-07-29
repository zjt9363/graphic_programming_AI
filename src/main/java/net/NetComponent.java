package net;


//import net.NetConfig;

import utils.CodeText;
import utils.Util;

import java.util.HashMap;

/**
 * @author Zarrow
 */
public class NetComponent {



    public static String importPackage(){
        return CodeText.IMPORT_PACKAGE;
    }

    public static String gpuConfig(){
        return CodeText.GPU_CONFIGURATION;
    }

    public static String loadData(NetConfig netConfig){
        return String.format(CodeText.LOAD_FILE, netConfig.getFileName());
    }

    public static String init(NetConfig netConfig){
        return String.format(CodeText.INIT, netConfig.getNumClasses());
    }

    public static String toCategorical(){
        return CodeText.TO_CATEGORICAL;
    }

    public static String addLayer(String functionName, HashMap<String,String> m){
        return CodeText.ADD_MODEL + functionName + "(" + Util.layerParameterToString(m) + "))\n";
    }

    public static String netBuild(){
        return CodeText.NET_BUILD;
    }

    public static String summary() {
        return CodeText.SUMMARY;
    }


    public static String earlyStopping() {
        return CodeText.EARLY_STOPPING;
    }

    public static String fit() {
        return CodeText.FIT;
    }

    public static String evaluate() {
        return CodeText.EVALUATE;
    }

    public static String print() {
        return CodeText.PRINT;
    }

    public static String compile(NetConfig netConfig) {
        StringBuilder stringBuilder = new StringBuilder(CodeText.COMPILE);
        if (netConfig.isToCategorical()){
            stringBuilder.delete(20,27);
        };
        return stringBuilder.toString();
    }
}
