package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 11/03/2018.
 */

public class UserGroup {
    private String name;
    private String shortDescription;
    private String permission;

    public UserGroup() {
    }

    public UserGroup(String name, String shortDescription, String permission) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public UserGroup setName(String name) {
        this.name = name;
        return this;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public UserGroup setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public UserGroup setPermission(String permission) {
        this.permission = permission;
        return this;
    }
}
