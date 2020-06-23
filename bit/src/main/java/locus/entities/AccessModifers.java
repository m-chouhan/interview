package locus.entities;

public class AccessModifers {

    public boolean hasPermission(ActionType type) {
        switch (type) {
            case CREATE: return create;
            case READ: return read;
            case UPDATE: return update;
            case DELETE: return delete;
        }
        return false;
    }

    public void grantPermission(ActionType actionType) {
        switch (actionType) {
            case CREATE: create = true;
                        read = true;
                        update = true;
                break;
            case READ: read = true;
                break;
            case UPDATE: update = true;
                break;
            case DELETE: delete = true;
                break;
        }
    }

    public enum ActionType { CREATE, READ, UPDATE, DELETE };
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if(create) builder.append("C");
        if(read) builder.append("R");
        if(update) builder.append("U");
        if(delete) builder.append("D");
        return builder.toString();
    }
}
