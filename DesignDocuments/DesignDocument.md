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
    IModel <|.. Model : realizes
    FileUtils <-- Model : uses
    Model <-- Controller : instantiates
    View <-- Controller : instantiates
    AbstractUnit <|-- ElectricalUnit : inherits
    AbstractUnit <|-- PlumbingUnit : inherits
    AbstractUnit <|-- ApplianceUnit : inherits
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
        - homeId : int
        - category : String
        - itemName : String
        - installDate : String
        - maintainedDate : String
        - standardLifeSpan : int
        - lifeSpanMeasure : String
        - roomLocation : String
        + AbstractUnit():
        + setRoomLocation():void
    }

    class ElectricalUnit {
        + electricWatt : int
        + ElectricalUnit()
        + getUnitType() : String
    }

    class PlumbingUnit {
        + waterFlow : int
        + PlumbingUnit()
        + getUnitType() : String

    }

    class ApplianceUnit {
        + dimensions : String
        + ApplianceUnit()
        + getUnitType() : String
    }

    class User {
        - userId : int
        - homeIds : List~int~
        - name : String
        - email : String
        + User(int, List~int~, String, String)
    }

    class Home {
        - userId : int
        - address : String
        - units : List~int~
        + Home(int, String, List~int~)

    }
```

## (INITIAL DESIGN): Tests to Write - Brainstorm

## (FINAL DESIGN): Class Diagram