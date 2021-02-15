package se.hernebring.extend;

public class FBMp3 extends FBFile implements FBPlayable {

    public FBMp3(String name) {
        super(name);
    }
    
    public String thumbnail() {
        return "[mp3 ]";
    }

    @Override
    public void play() {
        System.out.println("faked playing of file: " + this);
    }

}
