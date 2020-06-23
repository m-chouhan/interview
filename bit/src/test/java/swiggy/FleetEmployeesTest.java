package swiggy;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Interview question asked at swiggy
 * you have 3 types of employees
 * city manager -> Fleet manager ->delivery executive
 * and you need to create a tree like hirerarchy with the above order
 * then you also need an operation to distribute bonus and keep track of top performers list
 */
public class FleetEmployeesTest {

    private EmployeeManager manager;
    private CityManager mahendra;
    private DeliveryExecutive raju, ravi;
    private FleetManager ajit, harish;

    @Before
    public void loadEmployees() {

        manager = new EmployeeManager();
        mahendra = (CityManager) manager.onBoardEmployee("Mahendra", 20000, FleetEmployee.Position.CITY_MANAGER);
        ajit = (FleetManager) manager.onBoardEmployee("Ajit", 10000, FleetEmployee.Position.FLEET_MANAGER);
        raju = (DeliveryExecutive) manager.onBoardEmployee("Raju", 5000, FleetEmployee.Position.DILEVERY_EXECUTIVE);

        harish = (FleetManager) manager.onBoardEmployee("Harish", 11000, FleetEmployee.Position.FLEET_MANAGER);
        ravi = (DeliveryExecutive) manager.onBoardEmployee("Ravi", 9000, FleetEmployee.Position.DILEVERY_EXECUTIVE);

        mahendra.addWorker(ajit);
        mahendra.addWorker(harish);

        ajit.addWorker(raju);
        harish.addWorker(ravi);
    }

    @Test
    public void shouldPrintCorrectHierarchy() {
        System.out.println("\nPrinting the hierarchy for City Manager!!!\n");
        mahendra.printDownHierarchy();
        System.out.println("\nPrinting the hierarchy for Delivery executive!!!\n");
        raju.printTopHirerarchy();
    }

    @Test
    public void shouldDistributeBonusEvenly() {
        manager.giveBonusIn("Delhi", 500);
        List<DeliveryExecutive> topPerformers = manager.getTopPerformers();

        assert (topPerformers.size() == 1);
        assert (topPerformers.contains(raju));
        assert (!topPerformers.contains(ravi));

        manager.changeRatingOf(ajit.getName(), 2.5f);
        manager.giveBonusIn("Bengaluru", 10000);
        topPerformers = manager.getTopPerformers();
        assert (!topPerformers.contains(raju));
        assert (topPerformers.contains(ravi));
    }

    @Test
    public void ratingShouldBeInRange() {

        assert (!manager.changeRatingOf("Mahendra", 10));
        assert (manager.changeRatingOf(raju.getName(), 3));
        assert (manager.changeRatingOf(ajit.getName(), 2.5f));
        assert (raju.getRating() == 3);
    }
}