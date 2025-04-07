
package maintainhome.model;

import java.util.Comparator;

import maintainhome.model.Home.IUnit;

public class UnitSorters {
    public static final Comparator<IUnit> BY_TYPE = Comparator.comparing(IUnit::getUnitType);
    public static final Comparator<IUnit> BY_ROOM = Comparator.comparing(IUnit::getRoomLocation);
    public static final Comparator<IUnit> BY_INSTALL_DATE = Comparator.comparing(IUnit::getInstallDate);
}

