import java.util.ArrayList;
import java.util.Scanner;
//
//Iterator pattern:
//
//Provide a way to access the elements of an aggregate object
//sequentially without exposing its underlying representation.
//
//The classes and/or objects participating in this pattern are:

//1. Iterator  (AbstractIterator)
//		defines an interface for accessing and traversing elements.
//2. ConcreteIterator  (Iterator)
//		implements the Iterator interface.
//		keeps track of the current position in the traversal of the aggregate.
//3. Aggregate  (AbstractCollection)
//		defines an interface for creating an Iterator object
//4. ConcreteAggregate  (Collection)
//		implements the Iterator creation interface to return an instance of the proper ConcreteIterator
//

public class IteratorPatternLab {
    static void printAggregate(AbstractIterator i) {
        System.out.println("Iterating over collection:");
        for (i.First(); !i.IsDone(); i.Next()) {
            System.out.println(i.CurrentItem().getName());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Create Aggregate.

        Scanner sc = new Scanner(System.in);

        AbstractAggregate aggregate = new Collection();
        aggregate.add(new Channel("Das Erste", "Germany", 10));
        aggregate.add(new Channel("CCTV-1", "China", 657));
        aggregate.add(new Channel("NOW", "Türkiye", 555));
        aggregate.add(new Channel("Show Tv", "Türkiye", 0));
        aggregate.add(new Channel("TVNZ-1", "New Zealand", 999));
        aggregate.add(new Channel("CNC World", "China", 789));
        aggregate.add(new Channel("TRT-1", "Türkiye", 676));
        aggregate.add(new Channel("ZDF", "Germany", 155));
        aggregate.add(new Channel("Mehwar Tv", "Egypt", 56));

        // Create Iterator
        AbstractIterator iterator = aggregate.CreateIterator();
        // Traverse the Aggregate.
        printAggregate(iterator);

        System.out.println("Enter a number: ");
        int input = sc.nextInt();

        AbstractIterator frequencyIterator = aggregate.CreateIterator(input);
        printAggregate(frequencyIterator);


    }
}

//
//Our concrete collection consists of Items.
//

class Channel {
    private String name;
    private int frequency;
    private String countryOrigin;

    public Channel(String name, String countryOrigin, int frequency) {
        this.name = name;
        this.frequency = frequency;
        this.countryOrigin = countryOrigin;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\tCountryOrigin: " + getCountryOrigin() + "\tFrequency: " + getFrequency();
    }
}

//
//This is the abstract "Iterator".
//		AbstractIterator
//

interface AbstractIterator {
    void First();

    void Next();

    Boolean IsDone();

    Channel CurrentItem();
}

//
//This is the "concrete" Iterator for collection.
//		CollectionIterator
//

class TurkiyeIterator implements AbstractIterator {
    public void First() {
        _current = 0;
    }

    public void Next() {
        _current++;
    }

    public Channel CurrentItem() { // traverse only the channels that are broadcasted in “Türkiye.” normalde bu metot tüm elemanları döndürür
        while (!IsDone()) {
            Channel currentChannel = _collection.get(_current);
            if (currentChannel.getCountryOrigin().equals("Türkiye")) {
                return currentChannel;
            }
            Next();
        }
        return new Channel("Empty", "Empty", 0); // to avoid exception
    }

    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }

    public TurkiyeIterator(Collection collection) {
        _collection = collection;
        _current = 0;
    }

    private Collection _collection;
    private int _current;
};

class FrequencyIterator implements AbstractIterator {
    private int _maxFrequency;

    public void First() {
        _current = 0;
    }

    public void Next() {
        _current++;
    }

    public Channel CurrentItem() { // traverse only the channels that are broadcasted in “Türkiye.” normalde bu metot tüm elemanları döndürür

        while (!IsDone()) {
            Channel currentChannel = _collection.get(_current);
            if (currentChannel.getFrequency() >= 0 && currentChannel.getFrequency() <= _maxFrequency) {
                return currentChannel;
            }
            Next();
        }
        return new Channel("Empty", "Empty", 10000); // to avoid exception
    }

    public Boolean IsDone() {
        return _current >= _collection.getCount();
    }

    public FrequencyIterator(Collection collection, int maxFrequency) {
        _collection = collection;
        _current = 0;
        _maxFrequency = maxFrequency;
    }

    private Collection _collection;
    private int _current;
};


//
//This is the abstract "Aggregate".
//			AbstractAggregate
//

interface AbstractAggregate {
    public AbstractIterator CreateIterator();

    public void add(Channel it);        // Not needed for iteration.

    public int getCount(); // Needed for iteration.

    public Channel get(int idx); // Needed for iteration.

    AbstractIterator CreateIterator(int input);
};

//
//This is the concrete Aggregate.
//			Collection
//

class Collection implements AbstractAggregate {
    private ArrayList<Channel> _items = new ArrayList<Channel>();
    private int input;

    public TurkiyeIterator CreateIterator() {
        return new TurkiyeIterator(this);
    }
    public FrequencyIterator CreateIterator(int input){
        return new FrequencyIterator(this, input);
    }

    public int getCount() {
        return _items.size();
    }

    public void add(Channel item) {
        _items.add(item);
    }

    ;

    public Channel get(int index) {
        return _items.get(index);
    }

    ;
};
