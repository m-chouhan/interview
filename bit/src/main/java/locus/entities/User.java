package locus.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class User {

    public final String name;
    private final ArrayList<Role> roles;

    public User(String name) {
        this.name = name;
        roles = new ArrayList<>();
    }

    public ArrayList<Role> getRoles() { return roles; }
    public void addRole(Role role) {
        roles.add(role);
    }

    @Override
    public String toString() {
        return "User " + name + ", roles=" + roles.stream().map(role -> role.name).collect(Collectors.joining(","));
    }

    public boolean canAccess(Resource resource, AccessModifers.ActionType type) {
        for(Role role : roles) {
            if(role.canAccess(resource, type)) return true;
        }
        return false;
    }
}
