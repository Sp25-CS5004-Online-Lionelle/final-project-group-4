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
        + saveFile():void
        + readFile():void
    }

    class AbstractUnit {
        - category : String
        - itemName : String
        - initialInstallDate : String
        - lastMaintainedDate : String
        - lifeSpane : int
        - roomLocation : String
        + AbstractUnit():
    }

    class ElectricalUnit {
        - homeId : int
        + ElectricalUnit()
        + setRoomLocation():void
    }

    class PlumbingUnit {
        - homeId : int
        + PlumbingUnit()
        + setRoomLocation():void

    }

    class ApplianceUnit {
        - homeId : int
        + ApplianceUnit()
        + setRoomLocation():void

    }

    class User {
        - userId : int
        - name : String
        - email : String
    }

    class Home {
        - homeId : int
        - userId : int
        - address : String
    }
```

## (INITIAL DESIGN): Tests to Write - Brainstorm

## (FINAL DESIGN): Class Diagram