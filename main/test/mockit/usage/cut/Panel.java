package mockit.usage.cut;

import javax.accessibility.AccessibleContext;

public class Panel extends Container {
    private String name;

    public String getName() {
        return name;
    }


    public void addNotify() {

    }

    public AccessibleContext getAccessibleContext() {
        return null;
    }
}
