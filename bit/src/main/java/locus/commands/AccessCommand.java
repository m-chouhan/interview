package locus.commands;

import locus.DbLayer;
import locus.entities.AccessModifers.ActionType;
import locus.entities.Resource;
import locus.entities.User;

public class AccessCommand extends Command {

    public AccessCommand(DbLayer dbLayer) {
        super(dbLayer);
    }

    @Override
    public void handle(String... args) {
        String userName = args[1], resourceName = args[2], accessType = args[3];
        User user = dbLayer.findUser(userName);
        Resource resource = dbLayer.findResource(resourceName);

        if(user.canAccess(resource, ActionType.valueOf(accessType.toUpperCase())))
            System.out.println("Access Granted");
        else System.out.println("Access Denied!!");
    }
}
