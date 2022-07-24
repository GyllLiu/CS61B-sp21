package gitlet;

import static gitlet.Utils.message;
import static java.lang.System.exit;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            message("Please enter a command.");
            exit(0);
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                Repository.add(args[1]);
                break;
            case "commit":
                if(args[1] == null || args[1].length() == 0) {
                    message("Please enter a commit message.");
                }
                Repository.commit(args[1]);
                break;
        }
    }
}
