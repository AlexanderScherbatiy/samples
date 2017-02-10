package pattern.creational.abstract_factory.swing;

import java.awt.*;
import javax.swing.*;

import pattern.creational.abstract_factory.UIButton;
import pattern.creational.abstract_factory.UIComponent;
import pattern.creational.abstract_factory.UIFactory;
import pattern.creational.abstract_factory.UIWindow;


/**
 * Created by alexsch on 2/10/2017.
 */
public class SwingUIFactory implements UIFactory {

    @Override
    public UIButton createButton() {
        return new SwingButton();
    }

    @Override
    public UIWindow createWindow() {
        return new SwingWindow();
    }

    @Override
    public void runUI(Runnable run) {
        SwingUtilities.invokeLater(run);
    }

    static class SwingComponent<C extends Component> implements UIComponent {

        protected C component;

        public SwingComponent(C component) {
            this.component = component;
        }

        @Override
        public int getWidth() {
            return component.getWidth();
        }

        @Override
        public void setWidth(int width) {
            component.setSize(width, getHeight());
        }

        @Override
        public int getHeight() {
            return component.getHeight();
        }

        @Override
        public void setHeight(int height) {
            component.setSize(getWidth(), height);
        }
    }

    static class SwingButton extends SwingComponent<JButton> implements UIButton {

        public SwingButton() {
            super(new JButton());
        }

        @Override
        public String getText() {
            return component.getText();
        }

        @Override
        public void setText(String text) {
            component.setText(text);
        }
    }

    static class SwingWindow extends SwingComponent<JFrame> implements UIWindow {

        public SwingWindow() {
            super(new JFrame());
            component.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        @Override
        public String getTitle() {
            return component.getTitle();
        }

        @Override
        public void setTitle(String title) {
            component.setTitle(title);
        }

        @Override
        public boolean isVisible() {
            return component.isVisible();
        }

        @Override
        public void setVisible(boolean visible) {
            component.setVisible(visible);
        }

        @Override
        public void add(UIComponent component) {
            SwingComponent swingComponent = (SwingComponent) component;
            this.component.getContentPane().add(swingComponent.component);
        }
    }
}
