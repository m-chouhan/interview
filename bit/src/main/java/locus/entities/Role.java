package locus.entities;

import java.util.HashMap;
import java.util.Map;

public class Role {
    public final String name;
    private final HashMap<Resource, AccessModifers> accessMap;
    public Role(String name) {
        this.name = name;
        accessMap = new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Role : ").append(name).append(" [");
        for(Map.Entry<Resource, AccessModifers> entry : accessMap.entrySet()) {
            builder.append(entry.getKey().name).append(" ")
                    .append(entry.getValue().toString()).append(",");
        }
        builder.append("]");
        return builder.toString();
    }

    public boolean canAccess(Resource resource, AccessModifers.ActionType type) {
        if(!accessMap.containsKey(resource)) return false;
        AccessModifers accessModifers = accessMap.get(resource);
        return accessModifers.hasPermission(type);
    }

    public void addResource(Resource resource, AccessModifers.ActionType actionType) {
        if(!accessMap.containsKey(resource)) accessMap.put(resource, new AccessModifers());
        AccessModifers accessModifers = accessMap.get(resource);
        accessModifers.grantPermission(actionType);
    }
}
