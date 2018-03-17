package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 11/03/2018.
 */

public class UserGroup {
    private String name;
    private String shortDescription;
    private String permission[];

    public UserGroup() {
    }

    public UserGroup(String name, String shortDescription, String[] permission) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.permission = permission;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String[] getPermission() {
        return permission;
    }
}
