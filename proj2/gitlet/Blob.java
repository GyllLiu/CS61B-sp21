package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

public class Blob implements Serializable {
    private String blobId;
    private byte[] content;   //file content
    private File fileName;
    private int version;
    public Blob(File fileName) {
        this.fileName = fileName;
        this.version = 0;
        this.blobId = generateID();
        this.content = readObject(fileName, byte[].class);
    }

    /**
     * save a file to indexed stage
     */
    public void save() {
        File indexdeFile = join(Repository.ADD_DIR, fileName.getName());  //store a file with str filename into .index repo
        writeObject(indexdeFile, this.content);
    }

    private String generateID() {
        return Utils.sha1(fileName.getName(), content);
    }

    public String getBlobId() {
        return this.blobId;
    }

}
