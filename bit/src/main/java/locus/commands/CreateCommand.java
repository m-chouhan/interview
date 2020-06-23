package locus.commands;

import locus.DbLayer;
import locus.entities.Resource;
import locus.entities.Role;
import locus.entities.User;

public class CreateCommand extends Command {

    public CreateCommand(DbLayer dbLayer) {
        super(dbLayer);
    }

    @Override
    public void handle(String... args) {

        String type = args[1];
        switch (type) {
            case "user" :
                dbLayer.addUser(new User(args[2]));
                break;
            case "role" :
                dbLayer.addRole(new Role(args[2]));
                break;
            case "resource" :
                dbLayer.addResource(new Resource(args[2]));
                break;
            default: System.out.println("Unkown <type> in create!!");
        }
    }
}
