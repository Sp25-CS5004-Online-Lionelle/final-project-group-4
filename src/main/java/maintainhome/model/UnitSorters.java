
package maintainhome.model;

import java.util.Comparator;
import maintainhome.model.Home.UnitItems.IUnit;

public class UnitSorters {
    public static final Comparator<IUnit> BY_TYPE = Comparator.comparing(IUnit::getUnitType);
    public static final Comparator<IUnit> BY_ROOM = Comparator.comparing(IUnit::getRoomType);
    public static final Comparator<IUnit> BY_INSTALL_DATE = Comparator.comparing(IUnit::getInstallDate);
}

