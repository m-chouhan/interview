package swiggy;

import java.util.ArrayList;

public abstract class FleetEmployee {

    enum Position {CITY_MANAGER, FLEET_MANAGER, DILEVERY_EXECUTIVE}

    private String name;
    private long id;
    private long salary;
    private float rating;
    private float performanceRating;
    private Position position;

    FleetEmployee(String name, long id, long salary, int rating, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.rating = rating;
        this.position = position;
        this.performanceRating = 0;
    }

    public abstract FleetEmployee getManager();

    public abstract boolean setManager(FleetEmployee manager);

    public abstract void addWorker(FleetEmployee fleetEmployee);

    public void printTopHirerarchy() {
        if (position != Position.CITY_MANAGER) getManager().printTopHirerarchy();
        System.out.println(toString());
    }

    public abstract ArrayList<? extends FleetEmployee> getWorkers();

    public void printDownHierarchy() {
        System.out.println(toString());
        for (FleetEmployee fleetEmployee : getWorkers())
            fleetEmployee.printDownHierarchy();
    }

    public void distributeBonus(float amount) {
        performanceRating = amount / salary;
        float totalRatings = getWorkers().stream()
                .map(worker -> worker.getRating())
                .reduce(0f, (sum, rating) -> sum + rating);

        System.out.println(getName() + " received " + amount);
        for (FleetEmployee employee : getWorkers()) {
            float employeeBonus = (employee.getRating() / totalRatings) * amount;
            employee.distributeBonus(employeeBonus);
        }
    }


    @Override
    public String toString() {
        StringBuffer str = new StringBuffer("[" + getName() + "," + getId() + "," + getPosition() + " ->");
        for (FleetEmployee fm : getWorkers())
            str.append(fm.getName() + " , ");
        return str.append("]").toString();
    }

    //employee data
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public float getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(float performanceRating) {
        this.performanceRating = performanceRating;
    }

}
