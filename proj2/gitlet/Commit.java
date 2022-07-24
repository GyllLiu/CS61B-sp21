package gitlet;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  does at a high level.
 *
 *  @author
 */
public class Commit implements Serializable {
    /**
     *
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;

    private String currentTime;

    private String commitId;

    private List<String> parents;

    private Map<String, String> pathToBlob;

    public Commit() {
        message = "initial commit";
        this.commitId = generateID();
        this.currentTime = dateToTimeStamp(new Date(0));//00:00:00 UTC, Thursday, 1 January 1970
    }

    public Commit(String message) {
        this.message = message;
        this.commitId = generateID();
        this.currentTime = dateToTimeStamp(new Date());//time when commit some files
    }

    public String getCommitId() {
        return commitId;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeStamp() {
        return currentTime;
    }

    public List<String> getParent() {
        return parents;
    }

    public void addParent(String parent) {
        this.parents.add(parent);
    }

    public void addPathToBlobs(Blob blob, String path) {
        this.pathToBlob.put(path, blob.getBlobId());
    }

    private static String dateToTimeStamp(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);
        return dateFormat.format(date);
    }

    private String generateID() {
        return Utils.sha1(currentTime, message, parents.toString());
    }
}
