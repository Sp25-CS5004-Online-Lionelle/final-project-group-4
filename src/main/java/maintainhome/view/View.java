package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;
// import java.awt.*;

/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    public View(String caption) {
        super(caption);
    }

    @Override
    public void setListener(ActionListener listener, KeyListener keys) {

    }

}
