import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JennaKwon on 6/26/16.
 */
public class removeFirstNameDuplicates {

    public static class Person implements Comparable<Person> {
        public String firstName;
        public String lastName;

        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String toString() {
            return firstName + " " + lastName;
        }

        public int compareTo(Person person) {
            int comFirst = person.firstName.compareTo(firstName);
            if (comFirst != 0) {
                return comFirst;
            }
            return person.lastName.compareTo(lastName);
        }
    }


    /**
     * NOT in place, additional space storage.

     */
    public static ArrayList<Person> removeFirstNameDups(ArrayList<Person> list) {
        Collections.sort(list);
        ArrayList<Person> modifiedList = new ArrayList<>();

        int nameToKeep = 0;
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(nameToKeep).firstName.equals(list.get(i).firstName)) {
                modifiedList.add(list.get(nameToKeep));
                nameToKeep = i;
            }
        }
        modifiedList.add(list.get(nameToKeep));
        return modifiedList;

    }

    /**
     * In place duplicate removal
     */
    public static void removeFirstNameDupsInPlace(ArrayList<Person> list) {
        Collections.sort(list);

        int nameToKeep = 0;
        for (int i = 1; i < list.size(); i++) {
            if (!list.get(nameToKeep).firstName.equals(list.get(i).firstName)) {
                list.set(++nameToKeep, list.get(i));
            }
        }
        for (int i = list.size() - 1; i > nameToKeep; i--) {
            list.remove(i);
        }
    }


    public static void main(String[] args) {
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("Jenna", "Kwon"));
        personList.add(new Person("Jenna", "S"));
        personList.add(new Person("Jenna", "P"));
        personList.add(new Person("Jenna", "M"));
        personList.add(new Person("Jenna", "Q"));

        personList.add(new Person("Marissa", "DeSouza"));
        personList.add(new Person("Marissa", "S"));

        personList.add(new Person("John", "Aloha"));
        personList.add(new Person("John", "S"));

        personList.add(new Person("Joshua", "Alley"));
        personList.add(new Person("Joshua", "S"));
        personList.add(new Person("Joshua", "P"));
        personList.add(new Person("Joshua", "M"));
        personList.add(new Person("Joshua", "Q"));

        System.out.println(personList);

        ArrayList<Person> modifiedList = removeFirstNameDups(personList);

        System.out.println(modifiedList);

        removeFirstNameDupsInPlace(personList);

        System.out.println(personList);
    }
}
