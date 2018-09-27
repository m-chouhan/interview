package swiggy;

import java.util.ArrayList;

public class CityManager extends FleetEmployee {

    ArrayList<FleetManager> fleetManagersList = new ArrayList<>();

    CityManager(String name, long id, long salary, int rating) {
        super(name, id, salary, rating, Position.CITY_MANAGER);
    }

    @Override
    public FleetEmployee getManager() {
        throw new RuntimeException("City Manager Dont have any managers on top!");
    }

    @Override
    public boolean setManager(FleetEmployee manager) {
        throw new RuntimeException("City Manager Dont have any managers on top!");
    }

    @Override
    public void addWorker(FleetEmployee fleetEmployee) {
        FleetManager fm = (FleetManager) fleetEmployee;
        fleetManagersList.add(fm);
        fm.setManager(this);
    }

    @Override
    public void printTopHirerarchy() {
        System.out.println(getName() + getId() + getPosition());
    }

    @Override
    public ArrayList<FleetManager> getWorkers() {
        return fleetManagersList;
    }
}
