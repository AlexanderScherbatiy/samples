package pattern.creational.abstract_factory;

/**
 * Created by alexsch on 2/10/2017.
 */
public interface UIFactory {

    UIButton createButton();

    UIWindow createWindow();

    void runUI(Runnable run);
}
