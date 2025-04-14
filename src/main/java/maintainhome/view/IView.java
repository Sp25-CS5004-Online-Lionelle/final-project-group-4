package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


/**
 * The interface for our view class
 */
public interface IView {
    /**
   * Set the listener for any actions.
   */
  String getLoginUser();
  void setUserPanel(String id, String name, String email);
  void updateHomesList(String[] list);
  void updateHomesTable(List<String[]> row);
  void setListener(ActionListener clicks, KeyListener keys);

  void switchRightPanel(String cardNum);
  void switchMainPanel(String cardNum);

}
