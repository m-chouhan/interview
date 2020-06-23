package locus.commands;

import locus.DbLayer;
import locus.entities.Resource;
import locus.entities.Role;
import locus.entities.User;

public class PrintCommand extends Command {

    public PrintCommand(DbLayer dbLayer) {
        super(dbLayer);
    }

    @Override
    public void handle(String... args) {
        String type = args[1];

        switch (type) {
            case "users" :
                for(User user : dbLayer.getUsers())
                    System.out.println(user.toString());
                break;
            case "roles" :
                for(Role role : dbLayer.getRoles())
                    System.out.println(role.toString());
                break;
            case "resources" :
                for(Resource resource : dbLayer.getResources())
                    System.out.println(resource.toString());
                break;
            default: System.out.println("Unknown type in print command");
        }
    }
}
