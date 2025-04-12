package maintainhome.view;

import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();
    private JButton buttons[];
    private int inset20 = 20;

    public ButtonPanel(int btnNum) {
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
        setButtons();
    }

    public JButton[] getButtons() {
        return buttons;
    }

    private void setButtons() {
        gbc.anchor = GridBagConstraints.CENTER;

        String[] btnText = {"User", "Homes", "Units"};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(btnText[i]);
            gbc.gridy = i + 1;
            add(buttons[i], gbc);
        }
    }
}
