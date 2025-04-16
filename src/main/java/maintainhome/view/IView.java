package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import javax.swing.JTabbedPane;

import maintainhome.controller.Commands;


/**
 * The interface for our view class
 */
public interface IView {

  /**
   * Sets the command tied to the last panel switch.
   */
  void setSelectedCard(Commands card);

  /**
   * Gets the command tied to the last panel switch.
   * @return the current card/panel view from the panel switch
   */
  Commands getSelectedCard();

  /**
   * Clears the inputs/selections for both Homes & Units - Add tab.
   */
  void clearAddFields();

  /**
   * Gets the user's inputs/selections for both Homes & Units - Add tab as a map.
   * @return a map of the user's response for either the Homes or Units panel
   */
  Map<String, String> getAddValues();

  /**
   * Gets the username text input for user login.
   * @return the username input string
   */
  String getLoginUser();

  /**
   * Gets the Homes panel tab panel.
   * @return the tab panel of Homes
   */
  JTabbedPane getHomesTab();
  
  /**
   * Gets the Units panel tab panel.
   * @return the tab panel of Units
   */
  JTabbedPane getUnitsTab();

  /**
   * Sets the username panel with provided user information passed in from controller/model.
   */
  void setUserPanel(String id, String name, String email);

  /**
   * Updates the data to the JList in Homes - Add tab.
   */
  void updateHomesList(String[] list);

  /**
   * Updates the data to the JTable in Homes - Add tab.
   */
  void updateHomesTable(List<String[]> row);
  
  /**
   * Updates the data to the JList in Units - Add tab.
   */
  void updateUnitsList (String[] unitIds);

  /**
   * Updates the data to the JTable in Units - Add tab.
   */
  void updateUnitsTable(List<String[]> row);
  
  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener clicks, KeyListener keys);

  /**
   * switches the panels for the overall frame for login.
   */
  void switchRightPanel(String cardName);

  /**
   * switches the panels for the right panel tied to side panel buttons.
   */
  void switchMainPanel(String cardName);

  /**
   * gets the room type filter selected by the user.
   * @return the room type selected by the user
   */
  String getRoomTypeSelected();


}
