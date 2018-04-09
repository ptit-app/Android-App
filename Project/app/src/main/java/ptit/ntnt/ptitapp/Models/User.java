package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 27/03/2018.
 */

public class User {
    private String id;
    private String groupName;

    public User() {
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public User setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
