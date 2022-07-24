package gitlet;

import java.io.File;

import static gitlet.Utils.*;
import static java.lang.System.exit;

/** Represents a gitlet repository.
 *  does at a high level.
 *
 *  @author
 */
public class Repository {
    /**
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    public static final File OBJ_DIR = join(GITLET_DIR, "object");
    public static final File COMMIT_DIR = join(OBJ_DIR, "commits");

    public static final File ADD_DIR = join(OBJ_DIR, "index");  //staging area


    public static void init() {
        if(GITLET_DIR.exists()) {
            message("A Gitlet version-control system already exists in the current directory.");
            exit(0);
        }
        commit("initialCommit");
        GITLET_DIR.mkdir();
        OBJ_DIR.mkdir();
        COMMIT_DIR.mkdir();
        ADD_DIR.mkdir();
    }

    /**
     * store the file into indexed area
     * @param str
     */
    public static void add(String str) {
        File nonIndexFile = join(CWD, str);
        if (!nonIndexFile.exists()) {
            throw new GitletException("File does not exist.");
        }

        Blob blob  = new Blob(nonIndexFile);
        blob.save();
    }

    public static void commit(String message) {
        if(message.equals("initialCommit")) {
            Commit initialCommit = new Commit();
            File initialCommitFile = join(COMMIT_DIR,"initialCommitFile");
            writeObject(initialCommitFile, initialCommit);
        } else {
            Commit commit = new Commit(message);
            commit.addParent(commit.getCommitId());
            File[] files = join(ADD_DIR).listFiles();   //get all files from .index repo
            if(files.length == 0) {
                throw new GitletException("No changes added to the commit.");
            }
            for(File file : files) {
                Blob blob = new Blob(file);             //save the indexed file into Blob
                commit.addPathToBlobs(blob, file.getPath());
                file.delete();                          //after saved the file into commit stage, delete it from staging area
            }
            File commitFile = join(COMMIT_DIR, commit.getCommitId());
            writeObject(commitFile, commit);
        }
    }
}
