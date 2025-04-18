
package maintainhome.model.Utilities.Operations;

import java.util.Comparator;

import maintainhome.model.Home.Home;
import maintainhome.model.Home.UnitItems.IUnit;

/**
 * The Sorters to sort the Unit Items or Homes by
 */
public class Sorters {
    /** Comparator object to sort by unit type */
    public static final Comparator<IUnit> BY_TYPE = Comparator.comparing(IUnit::getUnitType);
    /** Comparator object to sort by room */
    public static final Comparator<IUnit> BY_ROOM = Comparator.comparing(IUnit::getRoomType);
    /** Comparator object to sort by install date */
    public static final Comparator<IUnit> BY_INSTALL_DATE = Comparator.comparing(IUnit::getInstallDate);
    /** Comparator object to sort by maintenance date */
    public static final Comparator<IUnit> BY_MAINTAIN_DATE = Comparator.comparing(IUnit::getMaintainedDate);
    /** Comparator object to sort by home row */
    public static final Comparator<Home> BY_HOME_NUM = Comparator.comparingInt(Home::getHomeNum);
}

