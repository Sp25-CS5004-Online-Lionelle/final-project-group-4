package maintainhome.view;

import javax.swing.*;
import java.awt.*;

/**
 * The side panel with the navigation buttons.
 */
public class ButtonPanel extends JPanel {
    /** This panel's Grid Bag Layout constraints */
    private GridBagConstraints gbc = new GridBagConstraints();
    /** The array to hold the navigation buttons */
    private JButton buttons[];
    /** A general inset of 20 to help with placement of objects on the panel */
    private int inset20 = 20;

    /**
     * Default Side Navigation panel Constructor.
     * @param caption for the Frame title display.
     */
    public ButtonPanel(int btnNum, String[] btnText) {
        super(new GridBagLayout());
        buttons = new JButton[btnNum];
        /* LEFT panel */
        gbc.insets = new Insets(inset20, inset20, inset20, inset20);
        
        //this.add(mainPanel);
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        //gbc.gridheight = 3;
        //gbc.fill = GridBagConstraints.VERTICAL;
        //sidePanel.setBounds(50,50, 500, 500);
        setButtons(btnText);
    }

    /**
     * Gets the user input text.
     * @return user input text
     */
    public JButton[] getButtons() {
        return buttons;
    }

    /**
     * Gets the user input text.
     * @return user input text
     */
    private void setButtons(String[] btnText) {
        gbc.anchor = GridBagConstraints.CENTER;

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(btnText[i]);
            gbc.gridy = i + 1;
            add(buttons[i], gbc);
        }
    }
}
