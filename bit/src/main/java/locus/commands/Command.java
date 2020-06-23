package locus.commands;

import locus.DbLayer;

public abstract class Command {
    final DbLayer dbLayer;
    public abstract void handle(String... args);
    public Command(DbLayer dbLayer) { this.dbLayer = dbLayer; }
}
