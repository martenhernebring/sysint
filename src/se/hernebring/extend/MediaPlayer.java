package se.hernebring.extend;

import se.hernebring.extend.utils.FileList;

public class MediaPlayer {

    public static void main(String[] args) {
        String dir = ".";
        if ( args.length!=0) {
            dir = args[0];
        }
        
        System.out.println("Playing in " + dir + ":");
        for (FBPlayable p : FileList.listPlayable(dir)) {
            p.play();
        }
    }

}
