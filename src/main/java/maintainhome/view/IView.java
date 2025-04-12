package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

/**
 * The interface for our view class
 */
public interface IView {
    /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener clicks, KeyListener keys);

  void switchRightPanel(String cardNum);
  void switchMainPanel(String cardNum);

}
