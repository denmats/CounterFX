package com.denmats;

import java.io.*;
import java.util.Scanner;

public class Keeper {

    private String filename;
    private File file;
    private int savedNumber;

    public Keeper(String filename) {
        this.filename = filename;
    }

    public void createFile(){
        try{
            file = new File(filename);
            if(file.createNewFile()){
                System.out.println("File created: "+this.filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(MainView mainView){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mainView.getCounter().getCount());
        System.out.println(stringBuffer);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            bufferedWriter.write(String.valueOf(stringBuffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readFromFile(MainView mainView){
        try{
            Scanner scanner = new Scanner(new File(filename));
            if(scanner.hasNextInt()){
                savedNumber = scanner.nextInt();
            }else{
                savedNumber = 0;
            }

            mainView.getCounter().setCount(savedNumber);
            mainView.getLabel().setText(String.valueOf(savedNumber));
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found!");;
        }
    }
}
