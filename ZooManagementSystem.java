import java.util.ArrayList;
import java.util.Scanner;

abstract class Entity {
    public abstract void displayInfo();
}

class Zoo extends Entity {
    private String name;
    private String location;
    private int visitorCount;
    private ArrayList<Enclosure> enclosures;

    public Zoo(String name, String location) {
        this.name = name;
        this.location = location;
        this.visitorCount = 0;
        this.enclosures = new ArrayList<>();
    }

    public void addEnclosure(Enclosure enclosure) {
        enclosures.add(enclosure);
    }

    public void removeEnclosure(Enclosure enclosure) {
        enclosures.remove(enclosure);
    }

    public void admitVisitor() {
        visitorCount++;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Enclosure> getEnclosures() {
        return enclosures;
    }

    @Override
    public void displayInfo() {
        System.out.println("Zoo Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Visitor Count: " + visitorCount);
        System.out.println("Enclosures:");
        for (Enclosure enclosure : enclosures) {
            enclosure.displayInfo();
        }
    }
}

class Enclosure extends Entity {
    private String name;
    private String type;
    private ArrayList<Animal> animals;

    public Enclosure(String name, String type) {
        this.name = name;
        this.type = type;
        this.animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Enclosure Name: " + name);
        System.out.println("Enclosure Type: " + type);
        System.out.println("Animals:");
        for (Animal animal : animals) {
            animal.displayInfo();
        }
    }
}

class Animal extends Entity {
    private String name;
    private String species;
    private int age;
    private String diet;
    private String healthStatus;

    public Animal(String name, String species, int age, String diet, String healthStatus) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.diet = diet;
        this.healthStatus = healthStatus;
    }

    public void feed() {
        System.out.println(name + " the " + species + " is being fed.");
    }

    public void treat() {
        System.out.println(name + " the " + species + " is being treated.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Animal Name: " + name);
        System.out.println("Species: " + species);
        System.out.println("Age: " + age);
        System.out.println("Diet: " + diet);
        System.out.println("Health Status: " + healthStatus);
    }
}

class Visitor extends Entity {
    private String name;
    private int age;
    private String ticketType;

    public Visitor(String name, int age, String ticketType) {
        this.name = name;
        this.age = age;
        this.ticketType = ticketType;
    }

    public void buyTicket() {
        System.out.println(name + " has purchased a " + ticketType + " ticket.");
    }

    public void visitEnclosures(Enclosure enclosure) {
        System.out.println(name + " is visiting the " + enclosure.getName() + " enclosure.");
    }

    @Override
    public void displayInfo() {
        System.out.println("Visitor Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Ticket Type: " + ticketType);
    }
}

public class ZooManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Zoo zoo = new Zoo("My Zoo", "City Park");

        Enclosure lionEnclosure = new Enclosure("Lion Enclosure", "Savannah");
        Animal lion = new Animal("Simba", "Lion", 5, "Meat", "Healthy");
        lionEnclosure.addAnimal(lion);
        zoo.addEnclosure(lionEnclosure);

        Enclosure penguinEnclosure = new Enclosure("Penguin Enclosure", "Arctic");
        Animal penguin = new Animal("Tux", "Penguin", 3, "Fish", "Healthy");
        penguinEnclosure.addAnimal(penguin);
        zoo.addEnclosure(penguinEnclosure);

        Enclosure giraffeEnclosure = new Enclosure("Giraffe Enclosure", "Savannah");
        Animal giraffe = new Animal("Gerry", "Giraffe", 7, "Leaves", "Healthy");
        giraffeEnclosure.addAnimal(giraffe);
        zoo.addEnclosure(giraffeEnclosure);

        Enclosure elephantEnclosure = new Enclosure("Elephant Enclosure", "Jungle");
        Animal elephant = new Animal("Dumbo", "Elephant", 10, "Hay", "Healthy");
        elephantEnclosure.addAnimal(elephant);
        zoo.addEnclosure(elephantEnclosure);

        System.out.println("Welcome to " + zoo.getName() + "!");
        System.out.print("Enter your name: ");
        String visitorName = scanner.nextLine();
        System.out.print("Enter your age: ");
        int visitorAge = Integer.parseInt(scanner.nextLine());
        System.out.print("Choose your ticket type (Adult/Child/Senior): ");
        String ticketType = scanner.nextLine();

        Visitor visitor = new Visitor(visitorName, visitorAge, ticketType);
        visitor.buyTicket();
        zoo.admitVisitor();

        zoo.displayInfo();

        System.out.println("Which enclosure would you like to visit?");
        System.out.print("Enter the enclosure name: ");
        String selectedEnclosureName = scanner.nextLine();

        for (Enclosure enclosure : zoo.getEnclosures()) {
            if (enclosure.getName().equalsIgnoreCase(selectedEnclosureName)) {
                visitor.visitEnclosures(enclosure);
                break;
            }
        }

        scanner.close();
    }
}
