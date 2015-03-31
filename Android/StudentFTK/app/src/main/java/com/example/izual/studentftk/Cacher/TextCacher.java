package com.example.izual.studentftk.Cacher;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.content.ContextCompat;

import com.example.izual.studentftk.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by oglandx on 21.03.2015.
 */
public class TextCacher {

    private final Activity activity;
    private final String filename;
    private File outDir = null;
    private File outFile = null;

    public TextCacher(Activity activity, final String filename) {
        this.activity = activity;
        this.filename = filename;
    }

    public void CacheOutput(final String [] data) throws TextCacherException{
        outDir = activity.getCacheDir();
        outFile = new File(outDir, filename);

        if(!outFile.canWrite()){
            throw new TextCacherException(
                    "Cannot write to the file (attributes) <" + filename + ">");
        }

        try {
            FileWriter fileWriter = new FileWriter(outFile);
            for(String line: data){
                fileWriter.append(line);
            }
        }
        catch(IOException e){
            throw new TextCacherException("Error while writing file <" + filename + ">");
        }
    }

    public String [] CacheInput() throws TextCacherException{
        if(outDir == null){
            outDir = activity.getCacheDir();
        }
        if(outFile == null){
            outFile = new File(outDir, filename);
        }
        if(!outFile.canRead()){
            throw new TextCacherException(
                    "Cannot read from the file (attributes) <" + filename + ">");
        }
        ArrayList<String> buffer = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(outFile);
            while(scanner.hasNext()){
                buffer.add(scanner.nextLine());
            }
        }
        catch(IOException e){
            throw new TextCacherException("Error while reading file <" + filename + ">");
        }
        return (String[])buffer.toArray();
    }
}
