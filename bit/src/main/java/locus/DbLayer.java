package locus;

import locus.entities.Resource;
import locus.entities.Role;
import locus.entities.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class DbLayer {
    List<User> users = new ArrayList<>();
    List<Role> roles = new ArrayList<>();
    List<Resource> resources = new ArrayList<>();


    public void addResource(Resource resource) {
        resources.add(resource);
    }
    public Resource findResource(String resourceName) {
        return resources.stream()
                .filter(resource -> resource.name.equals(resourceName))
                .findFirst().orElse(null);
    }
    public void removeResource(String resourceName) {
        resources.removeIf(resource -> resource.name.equals(resourceName));
    }

    public void addUser(User user) {
        users.add(user);
    }
    public User findUser(String userName) {
        return users.stream()
                .filter(user -> user.name.equals(userName))
                .findFirst().orElse(null);
    }
    public void removeUser(String userName) {
        users.removeIf(user -> user.name.equals(userName));
    }

    public void addRole(Role role) {
        roles.add(role);
    }
    public Role findRole(String roleName) {
        return roles.stream()
                .filter(role -> role.name.equals(roleName))
                .findFirst().orElse(null);
    }
    public void removeRole(String roleName) {
        roles.removeIf(role -> role.name.equals(roleName));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<Resource> getResources() {
        return resources;
    }
}
