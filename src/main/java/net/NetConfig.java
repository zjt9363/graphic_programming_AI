package net;


import org.springframework.stereotype.Service;
import utils.Pair;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Zarrow
 */

@Service
public class NetConfig {
    private String fileName;
    private boolean toCategorical = false;
    private int numClasses;

    private ArrayList<Pair<String, HashMap<String,String>>> arrayList = null;

    public ArrayList<Pair<String, HashMap<String, String>>> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Pair<String, HashMap<String, String>>> arrayList) {
        this.arrayList = arrayList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isToCategorical() {
        return toCategorical;
    }

    public void setToCategorical(boolean toCategorical) {
        this.toCategorical = toCategorical;
    }

    public int getNumClasses() {
        return numClasses;
    }

    public void setNumClasses(int numClasses) {
        this.numClasses = numClasses;
    }
}
