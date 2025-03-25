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
    Factory <-- Model : uses
    Model <-- Controller : instantiates
    View <-- Controller : instantiates
    MaintenanceItem <-- Factory : instantiates
    Home <-- Model : instantiates
    User <-- Model : instantiates

    class FileUtils {
        - saveFile
        - inputFile
    }

    class MaintenanceItem {
        - Category
    }
```

## (INITIAL DESIGN): Tests to Write - Brainstorm

## (FINAL DESIGN): Class Diagram