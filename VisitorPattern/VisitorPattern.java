import java.util.ArrayList;
import java.util.Random;

// Element  (Element)
// defines an Accept operation that takes a visitor a
// as an argument.

interface Element {
    public void Accept(Visitor visitor);
}

//"Visitor" declares a Visit operation for each class of ConcreteElement in the
//object structure. The operation's name and signature identifies the
//class that sends the Visit request to the visitor. That lets the
//visitor determine the concrete class of the element being visited.
//Then the visitor can access the elements directly through its particular
//interface
//
//"Visitor"
interface Visitor {
    public void Visit(BodyWorlds element);
    public void Visit(KingTutankhamun element);
    public void Visit(Titanic element);
}

//ConcreteElement  (Employee)
//implements an Accept operation that
//takes a visitor as an argument

abstract class Museum implements Element {
    private String name;
    private int noOfVisitors;
    private boolean continueApprove;
    // Constructor
    public Museum(String name, int noOfVisitors) {
        this.name = name;
        this.noOfVisitors = noOfVisitors;
        this.continueApprove = continueApprove;
    }

    public String getName() {return name;};
    public void setName(String value) {name = value;};
    public int getNoOfVisitors() {return noOfVisitors;};
    public void setNoOfVisitors(int value) {
        noOfVisitors = value;};

    public boolean isContinueApprove() {
        return continueApprove;
    }

    public void setContinueApprove(boolean continueApprove) {
        this.continueApprove = continueApprove;
    }
}

class BodyWorlds extends Museum {
    public BodyWorlds(String name, int noOfVisitors) {
        super (name, noOfVisitors);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}

}

class KingTutankhamun extends Museum {
    public KingTutankhamun(String name, int noOfVisitors) {
        super (name, noOfVisitors);
    }

    public void Accept(Visitor visitor) {visitor.Visit(this);}

}

class Titanic extends Museum {
    public Titanic(String name, int noOfVisitors) {
        super (name, noOfVisitors);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}

}


// ObjectStructure can enumerate its elements  may provide a
// high-level interface to allow the visitor to visit its elements
// may either be a Composite (pattern) or a collection such as a
// list or a set
//
// ObjectStructure  (Employees)
class Employees {
    public void Add(Museum museum){ museums.add(museum);};
    public void Remove(Museum museum) {
        for (int i = 0; i< museums.size(); i++) {
            if (museums.get(i).getName() == museum.getName()) {
                museums.remove(i);
                return;
            }
        }
    }
    public void Accept(Visitor visitor) {
        // elements accept the visitor
        for (Museum e: museums) {
            e.Accept(visitor);    }
    }
    private ArrayList<Museum> museums = new ArrayList<Museum>();
};


// ConcreteVisitors (IncomeVisitor, VacationVisitor)
// implements each operation declared by Visitor. Each operation implements
// a fragment of the algorithm defined for the corresponding class or object
// in the structure. ConcreteVisitor provides the context for the algorithm
// and stores its local state. This state often accumulates results during
// the traversal of the structure.
//
// "ConcreteVisitor 1"
class CountVisitor implements  Visitor {
    Random r = new Random();
    public void Visit(BodyWorlds element){
        element.setNoOfVisitors( r.nextInt(100) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.printf("%6.2f\n", element.getNoOfVisitors());
    }
    public void Visit(KingTutankhamun element){
        element.setNoOfVisitors(r.nextInt(150) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.printf("%6.2f\n",element.getNoOfVisitors());
    }
    public void Visit(Titanic element){
        element.setNoOfVisitors(r.nextInt(350) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.print(element.getNoOfVisitors());
    }
}
// "ConcreteVisitor 2"
class ApproveVisitor implements Visitor {
    Random r = new Random();
    public void Visit(BodyWorlds element){
        // Provide 3 extra vacation days
        element.setNoOfVisitors( r.nextInt(100) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.println(element.getNoOfVisitors());

        if (element.getNoOfVisitors() > 75){
            element.setContinueApprove(true);
        }
        element.setContinueApprove(false);
    }
    public void Visit(KingTutankhamun element){
        // Provide 5 extra vacation days
        element.setNoOfVisitors(r.nextInt(150) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.println(element.getNoOfVisitors());

        if (element.getNoOfVisitors() > 100){
            element.setContinueApprove(true);
        }
        element.setContinueApprove(false);
    }
    public void Visit(Titanic element){
        // Provide 7 extra vacation days
        element.setNoOfVisitors(r.nextInt(350) + 1);
        System.out.print(element.getName() + "'s new visitors:");
        System.out.println(element.getNoOfVisitors());

        if (element.getNoOfVisitors() > 250){
            element.setContinueApprove(true);
        }
        element.setContinueApprove(false);
    }
}


public class VisitorPattern {
    public static void main(String[] args) {
        // Setup employee collection
        Employees e = new Employees();
        CountVisitor visitor = new CountVisitor();

        e.Add(new BodyWorlds("Şenol Güneş",200000));
        e.Add(new KingTutankhamun("Umut Güner",300000));
        e.Add(new Titanic("Fikret Orman",400000));

        // Employees are 'visited'
        e.Accept(new CountVisitor());
        e.Accept(new ApproveVisitor());
    }
}
