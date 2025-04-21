## (EXTRA): Design Questions/Thoughts


## (INITIAL DESIGN): Class Diagram

Llink to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
---
title: Home Maintenance
---

classDiagram
    direction TD
    IController <|.. Controller : realizes
    IView <|.. View : realizes
    JFrame <|-- View : inherits
    IModel <|.. Model : realizes
    FileUtils <-- Model : uses
    Model <-- Controller : instantiates
    View <-- Controller : instantiates
    IUnit *-- AbstractUnit : realizes
    AbstractUnit <|-- ElectricUnit : inherits
    AbstractUnit <|-- ApplianceUnit : inherits
    AbstractUnit <|-- PlumbingUnit : inherits
    IElectricUnit *-- ApplianceUnit : realizes
    IPlumbingUnit *-- ApplianceUnit : realizes
    IElectricUnit *-- ElectricUnit : realizes
    IPlumbingUnit *-- PlumbingUnit : realizes
    ElectricUnit *-- Home : composes
    PlumbingUnit *-- Home : composes
    ApplianceUnit *-- Home : composes
    Home <-- User : association
    User <-- Model : instantiates

    class FileUtils {
        <<final>>
        + saveFile(List~String~):void
        + readFile(String):List~String~
    }

    class IUnit {
        <<interface>>
        + getUnitId():String
        + getItemName():String
        + getUnitType():UnitType
        + getRoomType():RoomType
        + getInstallDate():LocalDate
        + parseDate(String)$:LocalDate
        + dateToString()$:String
        + toCSV():String
    }

    class AbstractUnit {
        - unitId : String
        - itemName : String
        - roomType : RoomType
        - roomName : String
        - installDate : Date
        - maintainedDate : Date
        - maintenanceFrequency : int
        - frequencyMeasure : String
        + AbstractUnit(int, String, RoomType, String, Date, Date, int, String)
        + getUnitId() : String
        + setUnitId(int) : void
        + getItemName():String
        + setItemName(String):void
        + setRoomType(RoomType):void
        + getRoomType():RoomType
        + getRoomName():String
        + setRoomName(String):void
        + getInstallDate():LocalDate
        + setInstallDate(LocalDate):void
        + getMaintainedDate():LocalDate
        + setMaintainedDate(LocalDate):void
        + getMaintenanceFrequency():int
        + setMaintenanceFrequency(int):void
        + getFrequencyMeasure():String
        + setFrequencyMeasure(String):void
        + toCSV():String
    }

    class IElectricUnit {
        <<interface>>
        + getElectricWatt():int
    }

    class ElectricUnit {
        + electricWatt : int
        + ElectricalUnit()
        + getUnitType() : UnitType
    }

    class IPlumbingUnit {
        <<interface>>
        + getPlumbingGallon():int
    }

    class PlumbingUnit {
        + waterFlow : int
        + PlumbingUnit()
        + getUnitType() : UnitType

    }

    class ApplianceUnit {
        + height : int
        + width : int
        + depth : int
        + ApplianceUnit()
        + getUnitType() : UnitType
        + getDimension() : String
        + getElectricWatt():int
    }

    class User {
        - userId : int
        - homeIds : List~int~
        - name : String
        - email : String
        + User(int, List~int~, String, String)
        + getHome() : IHome
    }

    class Home {
        - userId : int
        - address : String
        - units : List~int~
        + Home(int, String, List~int~)
        + getUnit() : IUnit
        + getUnits() : IUnit

    }

    class View {
        + View(String)
        - setFrame() : void
        + setListener(ActionListener, KeyListener) : void
    }
```

## (FINAL DESIGN): Class Diagram

```mermaid
---
title: Home Maintenance
---

classDiagram
    direction TD

    PriorityType
    RoomType
    UnitType

    Filters
    Sorters
    
    Model <-- Controller : instantiates
    View <-- Controller : instantiates
    IUnit *-- AbstractUnit : realizes
    AbstractUnit <|-- ElectricUnit : inherits
    AbstractUnit <|-- ApplianceUnit : inherits
    AbstractUnit <|-- PlumbingUnit : inherits
    IElectricUnit *-- ApplianceUnit : realizes
    IPlumbingUnit *-- ApplianceUnit : realizes
    IElectricUnit *-- ElectricUnit : realizes
    IPlumbingUnit *-- PlumbingUnit : realizes
    ElectricUnit *-- Home : composes
    PlumbingUnit *-- Home : composes
    ApplianceUnit *-- Home : composes
    IColumnEnum *-- UserData : realizes

    ICsvSource *-- CsvLoader : realizes
    ICsvSource *-- CsvUpdater : realizes
    CsvLoader <-- Model : uses
    CsvUpdater <-- Model : uses
    User <-- Model : instantiates
    Home <-- Model : instantiates
    AbstractUnit <-- Model : instantiates
    Commands <-- Controller : uses
    ActionListener *-- Controller : realizes
    Commands <-- View : uses
    JFrame <|-- View : inherits
    IView *-- View : realizes

    class Commands {
        <<enumeration>>
        + loginButton(String)
        + userButton(String)
        + homesButton(String)
        + unitsButton(String)
        + clearButton(String)
        + insertButton(String)
        + resetFilter(String)
        + saveFiltered(String)
        + updatUser(String)
        - final command:String
        + Commands(String)
        + getCommandText():String
        + toCommand(String):Commands
    }

    class Controller {
        - model:Model
        - view:IView
        + Controller(Model, IView)
        - setModelUser() : void
        - setHomesView() : void
        - setUnitsView() : void
        - loginClicked() : void
        + actionPerformed(ActionEvent) : void
    }

    class PriorityType {
        <<enumeration>>
        + URGENT(int)
        + IMPORTANT(int)
        + LOW(int)
        - type:int
        + PriorityType(int)
        + getPriorityType():int
        + toPriorityType(int)$:PriorityType
        + containsValues(String)$:PriorityType
    }

    class RoomType {
        <<enumeration>>
        + KITCHEN(String)
        + LIVING_ROOM(String)
        + DINING_ROOM(String)
        + BEDROOM(String)
        + BATHROOM(String)
        + GARAGE(String)
        + BASEMENT(String)
        + UTILITY_ROOM(String)
        - roomType:String
        + RoomType(String)
        + getRoomType():String
        + toRoomType(String)$:RoomType   
    }

    class UnitType {
        <<enumeration>>
        + ELECTRIC_UNIT(String)
        + PLUMBING_UNIT(String)
        + APPLIANCE(String)
        - unitType:String
        + UnitType(String)
        + getUnitType():String
        + toUnitType(String)$:UnitType
        + containsValues(String)$:UnitType
    }

    class IUnit {
        <<interface>>
        + final DATE_FORMATTER:DateTimeFormatter$
        + getUserId():String
        + getHomeId():String
        + getUnitId():String
        + getItemName():String
        + getUnitType():UnitType
        + getRoomType():RoomType
        + getRoomName():String
        + getInstallDate():LocalDate
        + getMaintainedDate():LocalDate
        + getMaintenanceFrequency():int
        + getFrequencyMeasure():String
        + getIssue():String
        + getPriority():PriorityType
        + getUnitRow():String[]
        + parseDate(String)$:LocalDate
        + dateToString()$:String
    }

    class AbstractUnit {
        - userId : String
        - homeId : String
        - unitId : String
        - itemName : String
        - roomType : RoomType
        - roomName : String
        - installDate : Date
        - maintainedDate : Date
        - maintenanceFrequency : int
        - frequencyMeasure : String
        - issue : String
        - priority : PriorityType
        + AbstractUnit(String, String, String, String, UnitType, RoomType, String, LocalDate, LocalDate, int, String, String, PriorityType)
        + getUserId() : String
        + setUserId(String) : void
        + getHomeId() : String
        + setHomeId(String) : void
        + getUnitId() : String
        + setUnitId(int) : void
        + getItemName():String
        + setItemName(String):void
        + getUnitType() : UnitType
        + setUnitType(UnitType) : void
        + getRoomType : RoomType
        + setRoomType(RoomType):void
        + getRoomName():String
        + setRoomName(String):void
        + getInstallDate():LocalDate
        + setInstallDate(LocalDate):void
        + getMaintainedDate():LocalDate
        + setMaintainedDate(LocalDate):void
        + getMaintenanceFrequency():int
        + setMaintenanceFrequency(int):void
        + getFrequencyMeasure():String
        + setFrequencyMeasure(String):void
        + getIssue() : String
        + setIssue(String):void
        + getPriority():PriorityType
        + setPriority(PriorityType):void
        + getUnitRow() : String[]
        + toString():String
    }

    class IElectricUnit {
        <<interface>>
        + getElectricWatt():int
    }

    class ElectricUnit {
        - electricWatt : int
        - wireCount : int
        - hasBattery : boolean
        + ElectricalUnit(String, String, String, String, UnitType, RoomType, String, LocalDate, LocalDate, int, String, String, PriorityType, int)
        + getUnitType() : UnitType
        + getElectricWatt() : int
        + setElectricWatt(int) : void
    }

    class IPlumbingUnit {
        <<interface>>
        + getPlumbingGallon():int
    }

    class PlumbingUnit {
        - plumbingGallon : int
        - hasFaucet : boolean
        - hasDrain : boolean
        - pipeCount : int
        + PlumbingUnit(String, String, String, String, unitType, RoomType, String, LocalDate, LocalDate, int, String, String, PriorityType, int)
        + getUnitType() : UnitType
        + getPlumbingGallon() : int
        + setPlumbingGallon(int) : void

    }

    class ApplianceUnit {
        - electricWatt : int
        - height : int
        - width : int
        - depth : int
        + ApplianceUnit(String, String, String, String, UnitType, RoomType, String, LocalDate, LocalDate, int, String, String, PriorityType, int, int, int, int)
        + getUnitType() : UnitType
        + getDimension() : String
        + getElectricWatt():int
        + setElectricWatt(int):void
    }

    class User {
        - userId : String
        - name : String
        - email : String
        - homeIds : List~int~
        + User(String, String, String)
        + getUserId() : String
        + getName() : String
        + getEmail() : String
        + setName(String) : void
        + setEmail(String) : void
        + getHomes() : List~Home~
        + setHome(Home) : void
        + setHomes(List~Home~) : void
        + getNextHomeNum() : int
        + findHomeByName(String) : Home
    }

    class Home {
        - homeId : String
        - num : int
        - homeName : String
        - address : String
        - zip : String
        - unitItems : List~IUnit~
        + Home(String, int, String, String, String)
        + getHomeId() : String
        + getHomeName() : String
        + getHomeNum() : int
        + getAddress() : String
        + getZip() : String
        + getUnitItems() : List~IUnit~
        + getUnitJList() : String[]
        + setUnitItem(IUnit) : void
        + setUnitItems(List~IUnit~) : void
        + getHomeRow() : String[]
    }

    class Filters {
        + filterByType(List~IUnit~, String)$:List~IUnit~
        + filterByRoom(List~IUnit~, String)$:List~IUnit~
        + filterByInstallDateAfter(List~IUnit~, LocalDate)$:List~Iunit~
        + filterByHomeName(List~Home~, String)$:List~Home~
    }

    class Sorters {
        + final BY_TYPE:Comparator~IUnit~$
        + final BY_ROOM:Comparator~IUnit~$
        + final BY_INSTALL_DATE:Comparator~IUnit~$
        + final BY_MAINTAIN_DATE:Comparator~IUnit~$
        + final BY_HOME_NUM:Comparator~Home~$
    }

    class IColumnEnum {
        <<interface>>
        getColumnName():String
    }

    class ColumnData {
        - ColumnData()
        + UserData
        + HomeData
        + UnitItemData
        + UserHomeData
        + fromColumnName(String, FileType)$:IColumnEnum
        + fromString(String, FileType)$:IColumnEnum
        - fromColumnNameException(String)$:IllegalArgumentException
        - fromStringException(String)$:IllegalArgumentException
    }

    class UserData {
        <<enumeration>>
    }

    class CsvLoader {
        <<final>>
        - final DELIMITER:String$
        - final filePath:String$
        + overrideFilePath(String)$:void
        - trimValues(String[])$:String[]
        - toObject(String, Map~IColumnEnum, Integer~, FileType)$:Object
        - toUnitItems(String[], Map<IColumnEnum, Integer>)$:IUnit
        - processHeader(String, FileType)$:Map~ICOlumnEnum, Integer~
        - getFilePath(FileType)$:String
        - processLInes(FileType)$:List~String~
        + loadUserFile(String)$:String
        + loadHomesFile(String)$:List~Home~
        + loadUserHomesFile(String)$:Set~String~
        + loadUnitItemsFile(String, String)$:List~IUnit~
    }

    class CsvUpdater {
        <<final>>
        - final DELIMITER:String$
        - filePath:String$
        + overrideFilePath(String)$:void
        + getLastRow(FileType)$:int
        - trimValues(String[])$:String[]
        + updateWriteCsvFile(FileType, Object)$:void
        + writeCsvFile(FileType, Map~String,String~)$:void
        - readCsvFile(FileType)$:List~String~
        - getFilePath(FileType)$:String
        - processHeader(String, FileType)$:Map~IColumnEnum, Integer~
        - writeToFile(FileType, List~String~)$:void
        - updateUserCsv(List~String~, Map~IColumnEnum, Integer~, User)$:void
        - formatUserLine(User, Map~IColumnEnum, Integer~)$:String
        - updateHomesCsv(List~String~, Map~IColumnEnum, Integer~, Home)$:void
        - formatHomeLine(Home, Map~IColumnEnum, Integer~)$:String
        - updateUnitItemsCsv(List~String~, Map~IColumnEnum, Integer~, IUnit)$:void
        - formatUnitItemLine(IUnit, Map~IColumnEnum, Integer~)$:String
        - updateUserHomesCsv(List~String~, Map~IColumnEnum, Integer~, User)$:void
        - formatUserHomesLine(User, Map~IColumnEnum, Integer~)$:String
    }

    class Model {
        - user:User
        - newHome:Home
        - newUnit:IUnit
        - data:Map~String, String~
        - filteredUnits:List~IUnit~
        - selectedHome:Home
        + Model()
        + setFilteredUnits(List~IUnit~):void
        + getFilteredUnits():List~IUnit~
        + filterUnitsByRoomType(String) : void
        + setUser(String):void
        + getUser():User
        + getNewUnit():IUnit
        + addUnit():void
        - setSelectedHome(Home):void
        - getSelectedHome():Home
        - newUnitId(String, String):String
        + setNewUnit():void
        + saveUnit():void
        + setNewHome():void
        + getNewHome():Home
        + addHome():void
        + setUserHomes():void
        + setData(Map~String,String~):void
        + setUserItems():void
        + getHomeJList():String[]
        + getHomeRows():List~String[]~
        + getUnitRows():List~String[]~
        + getUnitRows(List~IUnit~):List~String[]~
        + getUnitsJList():String[]
        + saveHome():void
    }



    class View {
        + View(String)
        - setFrame() : void
        + setListener(ActionListener, KeyListener) : void
    }
```