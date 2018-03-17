package ptit.ntnt.ptitapp.CustomClass;

/**
 * Created by vdkhoa on 3/10/18.
 */

public class drawerMenuItem {
    private String Title;
    private int Icon;

    public drawerMenuItem(String title, int icon) {
        Title = title;
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }
}
