package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;

/**
 * The view's frame using JFrame.
 */
public class View extends JFrame implements IView {

    /** view's panel to hold the components. */
    private JPanel framePanel;
    /** view's GridBagConstraints inset . */
    private int inset = 10;

    public View(String caption) {
        super(caption);

        setFrame();
    }

    /**
     * Sets the JFrame.
     */
    private void setFrame() {
        this.setSize(500, 500);
        this.setLocation(550, 200);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;

        
        framePanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(inset, inset, 20, inset);
        constraints.anchor = GridBagConstraints.WEST;

    }

    @Override
    public void setListener(ActionListener listener, KeyListener keys) {

    }

}
