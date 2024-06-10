interface IPlace{ // element
    public void Accept(IWorker worker);
}

interface IWorker{ // visitor
    public void getLock(String lock);
    public void Visit(Apt_Flat apt_flat);
    public void Visit(House house);
}

abstract class Place implements IPlace{ // concrete element
    private String name;
    private boolean electric_job;
    private boolean waterworks_job;
    private String lock;

    public Place(String name, boolean electric_job, boolean waterworks_job, String lock) {
        this.name = name;
        this.electric_job = electric_job;
        this.waterworks_job = waterworks_job;
        this.lock = lock;
    }

    public String acquireLock() {
        return lock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isElectric_job() {
        return electric_job;
    }

    public void setElectric_job(boolean electric_job) {
        this.electric_job = electric_job;
    }

    public boolean isWaterworks_job() {
        return waterworks_job;
    }

    public void setWaterworks_job(boolean waterworks_job) {
        this.waterworks_job = waterworks_job;
    }

}

class Apt_Flat extends Place{

    public Apt_Flat(String name, boolean electric_job, boolean waterworks_job, String lock) {
        super(name, electric_job, waterworks_job, lock);
    }

    @Override
    public void Accept(IWorker worker) {
        worker.Visit(this);
    }
}

class House extends Place{

    public House(String name, boolean electric_job, boolean waterworks_job, String lock) {
        super(name, electric_job, waterworks_job, lock);
    }

    @Override
    public void Accept(IWorker worker) {
        worker.Visit(this);
    }
}

abstract class Worker implements IWorker{
    private String name;
    private int salary;

    public Worker(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void getLock(String lock) {
        System.out.println("lock is called "+lock );
    }

}


class Plumber extends Worker{
    public Plumber(String name, int salary) {
        super(name, salary);
    }

    @Override
    public void Visit(Apt_Flat apt_flat) {
        getLock(apt_flat.acquireLock());
        apt_flat.setWaterworks_job(true);
        setSalary(getSalary()+250);

        System.out.println(getSalary());
    }

    @Override
    public void Visit(House house) {
        getLock(house.acquireLock());
        house.setWaterworks_job(true);
        setSalary(getSalary()+500);

        System.out.println(getSalary());
    }
}

class Electrician extends Worker{
    public Electrician(String name, int salary) {
        super(name, salary);
    }

    @Override
    public void Visit(Apt_Flat apt_flat) {
        getLock(apt_flat.acquireLock());
        apt_flat.setElectric_job(true);
        setSalary(getSalary()+100);

        System.out.println(getSalary());
    }

    @Override
    public void Visit(House house) {
        getLock(house.acquireLock());
        house.setElectric_job(true);
        setSalary(getSalary()+200);

        System.out.println(getSalary());
    }
}

public class Lab12 {
    public static void main(String[] args) {
        Apt_Flat apt_flat = new Apt_Flat("Apt",false,false,"3543");
        House house = new House("house", false, false,"4532");


        Electrician electrician = new Electrician("Pikachu",0);
        Plumber plumber = new Plumber("Mario",0);

        house.Accept(electrician);
        house.Accept(plumber);

        apt_flat.Accept(electrician);
        apt_flat.Accept(plumber);



    }


}
