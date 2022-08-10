package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Zarrow
 */

public class PyRun {
    static Process proc;
    static boolean count;

    static public void run(String fileName) {

        // TODO Auto-generated method stub

        try {
            proc = Runtime.getRuntime().exec(("python " + fileName));
            count = true;
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if(!count){
                    proc.destroy();
                }
            }

            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public void destroy(){
        count = false;
    }
}
