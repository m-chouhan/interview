package swiggy;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager implements Cashier {

    private static final int MAX_TOP_PERFORMERS_ALLOWED = 1;

    ArrayList<FleetEmployee> employeesList = new ArrayList<>();

    ArrayList<DeliveryExecutive> topPerformerList = new ArrayList<>();

    FleetEmployee onBoardEmployee(String name, int salary, FleetEmployee.Position position) {

        //start with best rating during onboarding
        final int id = employeesList.size();
        FleetEmployee employee = null;
        switch (position) {
            case CITY_MANAGER:
                employee = new CityManager(name, id, salary, 5);
                break;
            case FLEET_MANAGER:
                employee = new FleetManager(name, id, salary, 5);
                break;
            case DILEVERY_EXECUTIVE:
                employee = new DeliveryExecutive(name, id, salary, 5, this);
        }
        employeesList.add(employee);
        return employee;
    }

    public void printHierarchy(String name) {
        findByName(name).printDownHierarchy();
    }

    public List<DeliveryExecutive> getTopPerformers() {
        return topPerformerList;
    }

    public void giveBonusIn(String cityName, int amount) {
        //create new list every time bonus is distributed
        topPerformerList.clear();
        System.out.println("\n***Distributing bonus in " + cityName + "****\n");
        CityManager cm = getCityManager(cityName);
        cm.distributeBonus(amount);
    }

    //TODO : implement properly
    private CityManager getCityManager(String cityName) {
        return (CityManager) employeesList.get(0);
    }

    @Override
    public void giveBonusTo(DeliveryExecutive deliveryExecutive, float amount) {
        System.out.println("Cashier dispatching bonus to " + deliveryExecutive.getName()
                + " of " + amount
                + " performance rating " + deliveryExecutive.getPerformanceRating());

        if (topPerformerList.size() < MAX_TOP_PERFORMERS_ALLOWED)
            topPerformerList.add(deliveryExecutive);
        else {
            int id = getLowestPerformer(topPerformerList);
            if (topPerformerList.get(id).getPerformanceRating() < deliveryExecutive.getPerformanceRating())
                topPerformerList.set(id, deliveryExecutive);
        }
    }

    private int getLowestPerformer(ArrayList<DeliveryExecutive> topPerformerList) {
        float lowestRating = topPerformerList.get(0).getPerformanceRating();
        int index = 0;
        for (int i = 0; i < topPerformerList.size(); ++i) {
            if (topPerformerList.get(i).getPerformanceRating() < lowestRating) {
                lowestRating = topPerformerList.get(i).getPerformanceRating();
                index = i;
            }
        }
        return index;
    }

    public boolean changeRatingOf(String employee, float rating) {
        if (rating > 5 || rating < 1) return false;

        FleetEmployee fleetEmployee = findByName(employee);
        if (fleetEmployee == null) return false;
        fleetEmployee.setRating(rating);
        return true;
    }

    public FleetEmployee findByName(String name) {
        for (FleetEmployee fleetEmployee : employeesList)
            if (fleetEmployee.getName().equals(name))
                return fleetEmployee;
        return null;
    }
}
