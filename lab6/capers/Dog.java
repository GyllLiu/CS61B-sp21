package capers;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author TODO
*/
public class Dog { // TODO

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = Paths.get(CapersRepository.dogsFile.toString(), "dogs").toFile(); // TODO (hint: look at the `join`


    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        // (hint: look at the Utils file)
        String s;
        Dog dog = null;
        if(DOG_FOLDER.canRead()) {
//                FileInputStream ins = new FileInputStream(DOG_FOLDER);
//                InputStreamReader r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
//                BufferedReader br = new BufferedReader(r);
//                while ((s = br.readLine()) != null) {
//                    String[] strArr = s.split(" ");
//                    if(name.equals(strArr[0])) {
//                        dog = new Dog(strArr[0], strArr[1], Integer.valueOf(strArr[2]));
//                        break;
//                    }
//                }
                String[] manyDogs = readObject(DOG_FOLDER, String.class).split("｜");
                for(String d : manyDogs) {
                    String[] aDog = d.split("/");
                    if(aDog[0].equals(name)) {
                        dog = new Dog(aDog[0], aDog[1], Integer.valueOf(aDog[2]));
                    }
                }
            }
        return dog;
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(toString());
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() {
        String saveStr =  this.name + "/" + this.breed + "/" + this.age + "｜";
        if(!DOG_FOLDER.canRead()) {
            writeObject(DOG_FOLDER, saveStr);
            return;
        }
        String output = readObject(DOG_FOLDER, String.class);
        writeObject(DOG_FOLDER,output + saveStr);
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }
}
