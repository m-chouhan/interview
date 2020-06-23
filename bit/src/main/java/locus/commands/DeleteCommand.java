package locus.commands;

import locus.DbLayer;
import locus.entities.Resource;
import locus.entities.Role;
import locus.entities.User;

public class DeleteCommand extends Command {

    public DeleteCommand(DbLayer dbLayer) {
        super(dbLayer);
    }

    @Override
    public void handle(String... args) {
        String type = args[1];
        switch (type) {
            case "user" :
                dbLayer.removeUser(args[2]);
                break;
            case "role" :
                dbLayer.removeRole(args[2]);
                break;
            case "resource" :
                dbLayer.removeRole(args[2]);
                break;
            default: System.out.println("Unkown <type> in delete!!");
        }
    }
}
