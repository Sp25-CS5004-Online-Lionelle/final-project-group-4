package maintainhome.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import maintainhome.controller.Commands;


/**
 * The interface for the View.
 */
public interface IView {

  /**
   * Sets the command tied to the last panel switch.
   * @param card the card/panel that the frame is currently displaying and switched to
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
   * Sets the Home identifier JComboBox for user selection in the Add pane of Units Panel.
   * @param list the list of items to be selected form the dropdown
   */
  void setUnitsAddHomesDropDown(String[] list);

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
   * @return the tab pane of Homes
   */
  JTabbedPane getHomesTab();
  
  /**
   * Gets the Units panel tab panel.
   * @return the tab pane of Units
   */
  JTabbedPane getUnitsTab();

  /**
   * Sets the username panel with provided user information passed in from controller/model.
   * @param id the user's id to be displayed
   * @param name the user's name to be displayed
   * @param email the user's email to be displayed
   */
  void setUserPanel(String id, String name, String email);

  /**
   * Updates the data to the JList in Homes - Add tab.
   * @param list the list that will populate and display in the Homes Panel View pane JList 
   */
  void updateHomesList(String[] list);

  /**
   * Updates the data to the JTable in Homes - Add tab.
   * @param row the row data that will be displayed in the Homes Panel View pane JTable
   */
  void updateHomesTable(List<String[]> row);
  
  /**
   * Updates the data to the JList in Units - Add tab.
   * @param list the list that will populate and display in the in the Units Panel View pane JList
   */
  void updateUnitsList(String[] list);

  /**
   * Updates the data to the JTable in Units - Add tab.
   * @param row the row data that will be displayed in the Units Panel View pane JTable
   */
  void updateUnitsTable(List<String[]> row);
  
  /**
   * Set the listener for any actions.
   * @param clicks the Action Listener that captures the source of this event and sends back to the object assigned to this listener
   * @param keys the Key Listener that captures the source of this event and sends back to the object assigned to this listener
   */
  void setListener(ActionListener clicks, KeyListener keys);

  /**
   * switches the panels for the overall frame for login.
   * @param cardName the cardname to switch for the main panel
   */
  void switchRightPanel(String cardName);

  /**
   * switches the panels for the right panel tied to side panel buttons.
   * * @param cardName the cardname to switch for the right panel
   */
  void switchMainPanel(String cardName);

  /**
   * gets the room type filter selected by the user.
   * @return the room type selected by the user
   */
  String getRoomTypeSelected();

  /**
   * gets the updated user name from the user panel.
   * @return the updated user name from the user panel
   */
  String getUpdatedUserName();

  /**
   * gets the updated email from the user panel.
   * @return the updated email from the user panel
   */
  String getUpdatedUserEmail();

  /**
   * refreshes the user panel labels with the updated user information.
   */
  void refreshUserPanelLabels();



}
