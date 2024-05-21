import java.util.ArrayList;

//The classes and/or objects participating in this pattern are:
// 1. Subject  (Stock)  -School Bell
//    . Knows its observers. Any number of Classrooms objects may observe a subject.
//     . Provides an interface for attaching and detaching Classrooms objects.
// 2. ConcreteSubject  (IBM)
//     . Stores state of interest to ConcreteObserver sends a notification to its.
//    . Observers when its state changes.
// 3. Classrooms  (Classroom) - Classrooms
//   . Defines an updating interface for objects that should be notified
//     of changes in a subject.
// 4. ConcreteObserver  (Classroom)
//   . Maintains a reference to a ConcreteSubject object
//   . Stores state that should stay consistent with the subject's
//   . Implements the Classrooms updating interface to keep its state
//     consistent with the subject's
interface Classrooms {
    public void Update(SchoolBell sb);
}
abstract class SchoolBell{
    int minute;
    protected ArrayList<Classroom> investors = new ArrayList<>();

    public SchoolBell(int minute) {
        this.minute = minute;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
       this.minute = minute;
        Notify();
    }

    public void Attach(Classroom investor){
        investors.add(investor);
    }

    public void Notify(){
        for (int i = 0; i < investors.size(); i++){
            investors.get(i).Update(this);
        }
    }
}
class Bell extends SchoolBell{

    public Bell(int minute) {
        super(minute);
    }
    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        Notify();
    }

}

class TwentyMinutesBell extends Bell{

    public TwentyMinutesBell(int minute) {
        super(minute);
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        Notify();
    }

    public void Notify(){
        for (int i = 0; i < schoolBells.size(); i++){
           for (int j = 0; j < classrooms.size() ; j++){
               classrooms.get(j).Update(this);
           }
        }
    }

    private ArrayList<SchoolBell> schoolBells = new ArrayList<>();
    private ArrayList<Classroom> classrooms = new ArrayList<>();
}

class FiftyMinutesBell extends Bell{

    public FiftyMinutesBell(int minute) {
        super(minute);
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
        Notify();
    }
}

class Classroom implements Classrooms {
    private int minute;
    private Counter counter = new Counter();

    public Classroom() {
    }

    @Override
    public void Update(SchoolBell sb) {
       this.minute = sb.getMinute();
       System.out.println("Minute changed to " + minute);
       counter.increaseCounter();
    }
}

class Counter {
    private static int updateCounter;

    void increaseCounter(){
        updateCounter++;
    }

    public int getUpdateCounter() {
        return updateCounter;
    }
}

class Principal{

}


public class Lab10 {
    public static void main(String[] args) {


        Classroom c = new Classroom();
        Bell b = new Bell(50);

        b.setMinute(20);

    }
}
