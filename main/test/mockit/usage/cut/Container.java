package mockit.usage.cut;

import java.util.ArrayList;

public class Container extends Component {

    private java.util.List<Component> component = new ArrayList<>();

    public int getComponentCount() {
        return component.size();
    }
}
