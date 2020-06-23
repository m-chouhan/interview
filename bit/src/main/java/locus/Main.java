package locus;

import locus.commands.*;
import org.javatuples.Pair;

import java.util.Scanner;

public class Main {

    static void printWelcomeMesage() {
        System.out.println("Welcome!! \n" +
                            "you have following commands available : create/print/show/delete/quit/access \n" +
                            ":: Sample usage (you can copy paste to test):: \n" +
                            "create user ram \n" +
                            "create user shyam\n" +
                            "create role admin\n" +
                            "create role guest\n" +
                            "create resource file\n" +
                            "update role guest file read\n"+
                            "update role admin file create\n" +
                            "update user ram guest\n" +
                            "update user shyam admin\n" +
                            "show users\n" +
                            "show roles\n" +
                            "show resources\n" +
                            "access shyam file read\n");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        printWelcomeMesage();
        //init logic
        DbLayer dbLayer = new DbLayer();
        CreateCommand createCommand = new CreateCommand(dbLayer);
        PrintCommand printCommand = new PrintCommand(dbLayer);
        DeleteCommand deleteCommand = new DeleteCommand(dbLayer);
        AccessCommand accessCommand = new AccessCommand(dbLayer);
        UpdateCommand updateCommand = new UpdateCommand(dbLayer);

        while(true) {
            String []tokens = scanner.nextLine().split(" ");
            Pair<Integer, Integer> pair;
            switch (tokens[0]) {
                case "create" :
                    createCommand.handle(tokens);
                    break;
                case "print" : case "show" :
                    printCommand.handle(tokens);
                    break;
                case "update" :
                    updateCommand.handle(tokens);
                    break;
                case "delete" :
                    //TODO:
                    deleteCommand.handle(tokens);
                    break;
                case "access" :
                    accessCommand.handle(tokens);
                    break;
                case "quit" :
                    return;
                default: System.out.println("unknown command..:/");
            }
        }
    }
}
