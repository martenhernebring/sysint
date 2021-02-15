package se.hernebring.extend;

public class FBOgg extends FBFile implements FBPlayable {

    public FBOgg(String name) {
        super(name);
    }
    
    public String thumbnail() {
        return "[ogg ]";
    }

    @Override
    public void play() {
        System.out.println("faked playing of file: " + this);
    }

}
