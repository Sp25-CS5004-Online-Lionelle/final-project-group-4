## (EXTRA): Design Questions
1. Should there be a room object to hold the unitItems? No, don't need as there is no further action that will be taken for an instance of a room under the purpose of this application.

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
    AbstractUnit <|-- ElectricalUnit : inherits
    AbstractUnit <|-- PlumbingUnit : inherits
    AbstractUnit <|-- ApplianceUnit : inherits
    ElectricalUnit *-- ApplianceUnit : composes
    PlumbingUnit *-- ApplianceUnit : composes
    ElectricalUnit *-- Home : composes
    PlumbingUnit *-- Home : composes
    ApplianceUnit *-- Home : composes
    Home <-- User : association
    User <-- Model : instantiates

    class FileUtils {
        <<final>>
        + saveFile(List~String~):void
        + readFile(String):List~String~
    }

    class AbstractUnit {
        - unitId : int
        - itemName : String
        - mapKeyword : String
        - installDate : Date
        - maintainedDate : Date
        - maintenanceFrequency : int
        - frequencyMeasure : String
        - roomLocation : String
        + AbstractUnit()
        + setRoomLocation() : void
        + getDateFromString(String) : Date
    }

    class ElectricalUnit {
        + electricWatt : int
        + ElectricalUnit()
        + getUnitType() : String
        + setMapKeyword():void
    }

    class PlumbingUnit {
        + waterFlow : int
        + PlumbingUnit()
        + getUnitType() : String
        + setMapKeyword():void

    }

    class ApplianceUnit {
        + height : int
        + width : int
        + depth : int
        + ApplianceUnit()
        + getUnitType() : String
        + getDimension() : String
        + setMapKeyword() : void
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