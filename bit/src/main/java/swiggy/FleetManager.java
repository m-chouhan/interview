package swiggy;

import java.util.ArrayList;

public class FleetManager extends FleetEmployee {

    private CityManager manager;
    private ArrayList<DeliveryExecutive> deliveryExecutivesList = new ArrayList<>();

    FleetManager(String name, long id, long salary, int rating) {
        super(name, id, salary, rating, Position.FLEET_MANAGER);
    }

    @Override
    public FleetEmployee getManager() {
        return manager;
    }

    @Override
    public boolean setManager(FleetEmployee manager) {
        try {
            this.manager = (CityManager) manager;
            return true;
        } catch (ClassCastException exception) {
            return false;
        }
    }

    @Override
    public void distributeBonus(float amount) {
        super.distributeBonus(amount);

    }

    @Override
    public void addWorker(FleetEmployee fleetEmployee) {
        DeliveryExecutive de = (DeliveryExecutive) fleetEmployee;
        deliveryExecutivesList.add(de);
        de.setManager(this);
    }

    @Override
    public ArrayList<DeliveryExecutive> getWorkers() {
        return deliveryExecutivesList;
    }
}
