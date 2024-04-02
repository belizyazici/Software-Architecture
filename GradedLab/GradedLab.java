import java.util.ArrayList;
import java.util.Iterator;

//"Command"
//
interface Command {
    public void Execute();
    public void add(Command c);

}
//"ConcreteCommand" - leaf in composite
//
class CalculatorCommand implements Command {
    // Constructor
    public CalculatorCommand(Calculator calculator, char op, int operand) {
        _calculator = calculator;
        _operator = op;
        _operand = operand;
    }
    public void Execute() {
        _calculator.Action(_operator, _operand);
    }

    @Override
    public void add(Command c) {
        System.out.println("Cannot be added");
    }

    private Calculator _calculator;
    private char _operator;
    private int _operand;
}

// MacroCommand
class CompositeCommand implements Command{
    public void add ( Command c){
        elements.add(c);
    }
    private ArrayList<Command> elements = new ArrayList<Command>();

    @Override
    public void Execute() {
        Iterator< Command> it = elements.iterator();
        while (it.hasNext()){
            it.next().Execute();
        }
    }
}
// "Receiver"
//
class Calculator {
    public Calculator() {
        current_value = 0;
    }
    public void Action(char _operator, int operand) {
        switch (_operator) {
            case '+': current_value += operand; break;
            case '-': current_value -= operand; break;
            case '*': current_value *= operand; break;
            case '/': current_value /= operand; break;
        }

    }
    public int getValue(){return current_value;}
    private int current_value;
}

// "Invoker"
class User {
    void Compute(Command command) {
        command.Execute();
    }
};

public class GradedLab {
    public static void main(String[] args) {

        // Create user and let her compute
        User user = new User();
        Calculator calculator = new Calculator();
        int PI = 3;
        int radius = 5;

        Command macro = new CompositeCommand();
        macro.add(new CalculatorCommand(calculator, '+', 1));
        macro.add(new CalculatorCommand(calculator, '*', 2));
        macro.add(new CalculatorCommand(calculator, '*', PI));
        macro.add(new CalculatorCommand(calculator, '*', radius));


        user.Compute(macro);

        System.out.println("Curriculumreference is "+ calculator.getValue());

    }
}
