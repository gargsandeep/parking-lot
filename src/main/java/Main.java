package main.java;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        String line;
        try {
            if(args.length >0){
                String fileName = args[0];
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            }
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
