import java.util.ArrayList;
//=======================================================================
//Name        : AbstractFactory.cpp
// 1. AbstractFactory  ( CarFactory )
//   Declares an interface for operations that create abstract products
// 2. ConcreteFactory  (OPELFactory,FordFactory)
//   Implements the operations to create concrete product objects
// 3. AbstractProduct   (Part, Engine, Transmission)
//   Declares an interface for a type of product object
// 4. Product  (OPEL_Engine, OPEL_Transmission,
//           FORD_Engine, FORD_Transmission.)
//   Defines a product object to be created by the corresponding
//    concrete factory implements the AbstractProduct interface
// 5. Client  (BuildCar)
//   Uses interfaces declared by AbstractFactory and AbstractProduct
//    classes
//=======================================================================

// Top "Abstract Product" Part class
abstract class Furniture { // furniture
    abstract public String displayName();
    abstract double getPrice();
}
// An 'Abstract Product A' class
// Engine base class.
abstract class Chair extends Furniture { // chair
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name; }
}

//A 'ConcreteProduct A1' class
class Modern_Africa extends Chair { //art modern
    public Modern_Africa(double p) {
        price = p;
        name = new String("Modern Africa chair");
        System.out.println("Modern Africa chair is created...");
    }
}
//A 'ConcreteProduct A2' class
class Antique_1 extends Chair { // history furniture
    public Antique_1(double p) {
        price = p;
        name = new String("Antique-1 chair");
        System. out.println("Antique-1 chair is created...");
    }
}

class Wavelet_Chair extends Chair{
    public Wavelet_Chair(double p) {
        price =p;
        name = new String("Wavelet Chair");
        System.out.println("Wavelet Chair is created...");
    }
}

//An 'Abstract Product B' class
//Transmission base class
abstract class CoffeeTable extends Furniture { // coffee table
    protected double price;
    protected String name;
    public double getPrice(){ return price; }
    public String displayName() { return name; }
}

//A 'ConcreteProduct B1' class
class Marble_Cloud extends CoffeeTable {
    public Marble_Cloud(double p) {
        price = p;
        name = new String("Marble cloud table");
        System. out.println("Marble cloud table is created...");
    }
}
//A 'ConcreteProduct B2' class
class Valedictorian_Table extends CoffeeTable {
    public Valedictorian_Table(double p) {
        price = p;
        name = new String("Valedictorian table");
        System. out.println("Valedictorian table is  created...");
    }
}

class Corian_Table extends CoffeeTable{
    public Corian_Table(double p){
        price = p;
        name = new String("Corian Table");
        System.out.println("Corian Table is created...");
    }
}
//An 'Abstract Factory' class
abstract class FurnitureFactory {
    //abstract public Chair createChair();
    //abstract public CoffeeTable createTable();

    abstract public Furniture createFurnitures(String input);
}
//A 'Concrete Factory' class
class ArtModernCompany extends FurnitureFactory {
    /*
    public Chair createChair() {
        return new Modern_Africa(25000.00);
    }
    public CoffeeTable createTable() {
        return new Marble_Cloud(10000.00);
    }

     */
    public Furniture createFurnitures(String input){
        if (input == "1"){
            return new Modern_Africa(25000.00);
        }
        else{
            return new Marble_Cloud(10000.00);
        }
    }
}

//Another 'Concrete Factory' class
class HistoryFurniture extends FurnitureFactory {
    /*
    public Chair createChair() {
        return new Antique_1(20000.00);
    }
    public CoffeeTable createTable() {
        return new Valedictorian_Table(12000.00);
    }

     */
    public Furniture createFurnitures(String input){
        if (input == "1"){
            return new Antique_1(20000.00);
        }
        else{
            return new Valedictorian_Table(12000.00);
        }
    }
}

class Future_Now extends FurnitureFactory{
    /*
    public Chair createChair() {
        return new Wavelet_Chair(13000.00);
    }

    public CoffeeTable createTable() {
        return new Corian_Table(15000.00);
    }
*/
    public Furniture createFurnitures(String input){
        if (input == "1"){
            return new Wavelet_Chair(13000.00);
        }
        else{
            return new Corian_Table(15000.00);
        }
    }
}

//The 'Client'.
class BuildFurniture {  // 4TH QUESTION
    // Object creation is delegated to factory.
    public void createFurniture(FurnitureFactory factory) {
        furnitures = new ArrayList<Furniture>();
        //furnitures.add(factory.createChair());
        //furnitures.add(factory.createTable());
        furnitures.add(factory.createFurnitures("1"));
        furnitures.add(factory.createFurnitures("2"));
    }
    void displayFurnitures() {
        System.out.println("\tListing Furnitures\n\t-------------");
        furnitures.forEach(p  -> System.out.println("\t"+ p.displayName() +
                " " + p.getPrice()));
    }
    private ArrayList<Furniture> furnitures;
}
//Abstract Factory Method Design Pattern.
//Entry point into main application.
public class Lab6 {
    public static void main(String[] args) {
        // Create factories.
        FurnitureFactory artModernCompany = new ArtModernCompany();
        FurnitureFactory historyFurniture = new HistoryFurniture();
        FurnitureFactory futureNow = new Future_Now();

        BuildFurniture furniture = new BuildFurniture();
        System.out.println("Creating Art Modern Factory");
        furniture.createFurniture(artModernCompany);
        furniture.displayFurnitures();

        System.out.println("Creating History Furniture");
        furniture.createFurniture(historyFurniture);
        furniture.displayFurnitures();

        System.out.println("Creating Future Now");
        furniture.createFurniture(futureNow);
        furniture.displayFurnitures();



    }
}
