package pattern.creational.abstract_factory;

import pattern.creational.abstract_factory.swing.SwingUIFactory;

/**
 * Created by alexsch on 2/10/2017.
 */
public class UIFactorySample {

    public static void main(String[] args) {
        final UIFactory factory = getUIFactory();

        factory.runUI(() -> {
            UIWindow win = factory.createWindow();
            win.setWidth(600);
            win.setHeight(300);
            win.setTitle("Hello World!");

            UIButton button = factory.createButton();
            button.setText("Press me!");
            win.add(button);
            win.setVisible(true);
        });
    }

    private static UIFactory getUIFactory() {
        return new SwingUIFactory();
    }
}
