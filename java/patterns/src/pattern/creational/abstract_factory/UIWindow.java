package pattern.creational.abstract_factory;


/**
 * Created by alexsch on 2/10/2017.
 */
public interface UIWindow extends UIComponent {

    boolean isVisible();

    String getTitle();

    void setTitle(String title);

    void setVisible(boolean visible);

    void add(UIComponent component);
}
