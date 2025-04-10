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

## (INITIAL DESIGN): Tests to Write - Brainstorm

## (FINAL DESIGN): Class Diagram