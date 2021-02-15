package se.hernebring.extend;

public class FBDir extends FBFile {

    public FBDir(String name) {
        super(name);
    }
    

    public String thumbnail() {
        return "[dir ]";
    }
    
}
