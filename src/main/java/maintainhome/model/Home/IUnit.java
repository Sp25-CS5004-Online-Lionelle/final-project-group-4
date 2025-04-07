package maintainhome.model.Home;

import java.time.LocalDate;

public interface IUnit {
    String getUnitType();
    String getRoomLocation();
    LocalDate getInstallDate();
    String getItemName();
    
}
