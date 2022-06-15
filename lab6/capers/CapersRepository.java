package capers;

import java.io.File;
import java.nio.file.Paths;

import static capers.Utils.*;

/** A repository for Capers 
 * @author TODO
 * The structure of a Capers Repository is as follows:
 *
 * .capers/ -- top level folder for all persistent data in your lab12 folder
 *    - dogs/ -- folder containing all of the persistent data for dogs
 *    - story -- file containing the current story
 *
 * TODO: change the above structure if you do something different.
 */
public class CapersRepository {
    /** Current Working Directory. */
    static final File CWD = new File(System.getProperty("user.dir"));

    /** Main metadata folder. */
    static final File CAPERS_FOLDER = Paths.get(CWD.toString(), "capersFolder").toFile(); // TODO Hint: look at the `join` function in Utils
    static final File dogsFile = Paths.get(CAPERS_FOLDER.toString(), "dogs").toFile();
    static final File storyFile = Paths.get(CAPERS_FOLDER.toString(), "story").toFile();

    /**
     * Does required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Remember: recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab12 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void setupPersistence() {
        CAPERS_FOLDER.mkdir();
        dogsFile.mkdir();
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * @param text String of the text to be appended to the story
     */
    public static void writeStory(String text) {
        StringBuilder sb = new StringBuilder();

        if (!storyFile.canRead()) {
            System.out.println(text);
            sb.append(text).append("\n");
            writeObject(storyFile, sb.toString());
            return;
        }

        String output = readObject(storyFile, String.class);
        System.out.print(output);
        System.out.println(text);
        sb.append(output).append(text).append("\n");
        writeObject(storyFile, sb.toString());
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     */
    public static void makeDog(String name, String breed, int age) {
        Dog dog = new Dog(name, breed, age);
        dog.saveDog();
        System.out.println(dog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * @param name String name of the Dog whose birthday we're celebrating.
     */
    public static void celebrateBirthday(String name) {
        Dog birthDayDog = Dog.fromFile(name);
        birthDayDog.haveBirthday();
        birthDayDog.saveDog();
    }
}
