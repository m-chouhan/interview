package locus.commands;

import locus.DbLayer;
import locus.entities.AccessModifers.ActionType;
import locus.entities.Resource;
import locus.entities.Role;
import locus.entities.User;

public class UpdateCommand extends Command {

    public UpdateCommand(DbLayer dbLayer) {
        super(dbLayer);
    }

    @Override
    public void handle(String... args) {
        String type = args[1];
        switch (type) {
            case "user":
                User user = dbLayer.findUser(args[2]);
                Role role = dbLayer.findRole(args[3]);
                user.addRole(role);
                break;
            case "role" :
                Role role1 = dbLayer.findRole(args[2]);
                Resource resource = dbLayer.findResource(args[3]);
                ActionType actionType = ActionType.valueOf(args[4].toUpperCase());
                role1.addResource(resource, actionType);
                break;
            default: System.out.println("unknown type in update command");
        }
    }
}
