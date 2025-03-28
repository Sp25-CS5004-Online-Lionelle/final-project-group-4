## (INITIAL DESIGN): Class Diagram

Llink to tools that can help you create a class diagram: [Class Resources: Class Design Tools](https://github.com/CS5004-khoury-lionelle/Resources?tab=readme-ov-file#uml-design-tools)

```mermaid
---
title: Domain Name Information
---

classDiagram
    direction TD
    IController <|.. Controller : realizes
    IView <|.. View : realizes
    IModel <|.. Model : realizes
    FileUtils <-- Model : uses
    Model <-- Controller : instantiates
    View <-- Controller : instantiates
    MaintenanceItem <|-- ElectricalUnit : inherits
    MaintenanceItem <|-- PlumbingUnit : inherits
    MaintenanceItem <|-- ApplianceUnit : inherits
    ElectricalUnit *-- Home : composes
    PlumbingUnit *-- Home : composes
    ApplianceUnit *-- Home : composes
    Home <-- User : association
    User <-- Model : instantiates

    class FileUtils {
        + saveFile():void
        + readFile():void
    }

    class MaintenanceItem {
        + category : String
        + itemName : String
        + initialInstallDate : String
        + lastMaintainedDate : String
        + lifeSpane : int
        + roomLocation : String
    }

    class ElectricalUnit {
        - homeId : int

    }

    class PlumbingUnit {
        - homeId : int

    }

    class ApplianceUnit {
        - homeId : int

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