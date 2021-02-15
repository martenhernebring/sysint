package se.hernebring.extend;

import java.util.List;

import se.hernebring.extend.utils.FileList;

public class TextMain {

    public static void main(String[] args) {
        String dir = ".";
        if ( args.length!=0) {
            dir = args[0];
        }
        
        List<FBFile> files = FileList.list(dir);

        for (FBFile f : files) {
            System.out.println(f.thumbnail() + " " + f.name());
        }
    }

}
