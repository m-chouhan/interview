package swiggy;

import java.util.ArrayList;

public class DeliveryExecutive extends FleetEmployee {

    private FleetManager manager;
    private ArrayList<FleetEmployee> list = new ArrayList<>();
    private final Cashier cashier;

    DeliveryExecutive(String name, long id, long salary, int rating, Cashier cashier) {
        super(name, id, salary, rating, Position.DILEVERY_EXECUTIVE);
        this.cashier = cashier;
    }

    @Override
    public FleetManager getManager() {
        return manager;
    }

    @Override
    public boolean setManager(FleetEmployee manager) {
        this.manager = (FleetManager) manager;
        return true;
    }

    @Override
    public void addWorker(FleetEmployee fleetEmployee) {
        throw new RuntimeException("Delivery Executive don't have any underlings");
    }

    @Override
    public void printDownHierarchy() {
        System.out.println(toString() + "(empty)");
    }

    @Override
    public void distributeBonus(float amount) {
        super.distributeBonus(amount);
        cashier.giveBonusTo(this, amount);
    }

    @Override
    public ArrayList<FleetEmployee> getWorkers() {
        return list;
    }
}
