---
layout: page
title: Rushil Ramesh's Project Portfolio Page
---

### Project: ClassMATE

ClassMATE is a desktop application designed for instructors of the CS2101 Module in NUS to easily store class and student details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: Check out my contributions [here](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/)

* **Enchancements Implemented**:
    * New Feature: Added the ability to add tutorial classes:
        * PR [\#94](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/94),  [\#97](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/97)
        * Allows the user to create a tutorial class with a class code and schedule (and optional tags) so that
          they can add students to them in the future. This is a basic feature of a CRUD application like ClassMATE.
        * This implementation was slightly challenging as it was my first feature implemented, which involved making several
        additions to the existing code, including the creation of `TutorialClass`, `Schedule`, `ClassCode` (contributed by Yirui) and the
        `EmptyClasscode` which define the tutorial class. I also modified several files involved in the logic behind parsing and executing an
        `AddClassCommand`. As I implemented the `TutorialClass` feature, I also had to work on the UI, creating a
      `ClassListPanel` so that users could view classes and students on two separate lists.
    * New Feature: Added the ability to delete tutorial classes
        * PR [\#97](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/97), [\#147](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/147)
        * Allows the user to delete a tutorial class from a specified index in the list of classes. This is a basic CRUD operation
        that users should be able to perform in the event they no longer require details of a particular class or are assigned to
        another class.
        * This implementation required an understanding of the `UniqueList`s and the `ModelManager` as a whole,
        as deleting a class involved more than simply removing a `TutorialClass` from the list of classes, but involved
        an update of each student's designated class to an 'empty class', meaning the student has no class. This required
        adding `Predicate` classes and new methods in `ModelManager`.
    * New Feature: Implemented the ability to view the students in a class
        * PR [\#135](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/135)
        * Allows the user to view a particular class by its index in the list of classes. The student list is filtered to
        display only students from the particular tutorial class, and the class list only displays the selected class.
        * This implementation involved the creations of more `Predicate` classes to filter the students out. In addition,
        I made changes to the UI, including setting the layout of the main page and the UI of the individual students and tutorial classes.
        I also implemented the list class feature which brings the user back to the original list of classes.
    * Enchancement to existing feature: Student management features.
        * PR [\#135](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/135)
        * Adjusted student functionality, such as preventing students from being added to an empty class and adding a classcode.
        * These features were related to tutorial class, therefore some of my other PRs include updates to student management features.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `addstu`, `viewstu` and `deletestu` [\#57](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/57)
        * Added a write-up for the various sections, including the **Features** section and its various subsections. [\#148](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/148)
        * Included a CLI Tutorial for users. [\#148](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/148)
        * Made cosmetic tweaks to various headings, numberings etc. [\#148](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/148)
        * Updated Command Summary with all new features [\#143](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/143)
    * Developer Guide:
        * Added User Stories and Use Cases for CRUD features for students and tutorial classes. [\#65](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/65)
        * Added and updated Tutorial Class Management features section. [\#124](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/124)
        * Updated Architecture diagrams to reflect ClassMATE's architecture. [\#124](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/124)
        * Updated Class Diagrams for UI, Logic and Model [\#124](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/124)
        * Created Sequence diagram for `AddClassCommand` [\#124](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/124)

* **Team-Based tasks**:
    * Managed testing for releases `v1.1` - `v1.4` (4 releases) on GitHub
    * Conducted manual testing of features based on descriptions provided in the User Guide.
    * **Bug Fixes**:
        * Empty ClassCode bugs [\#185](https://github.com/AY2122S1-CS2103T-W15-1/tp/issues/185), Schedule Format Bugs [\#162](https://github.com/AY2122S1-CS2103T-W15-1/tp/issues/162), [\#187](https://github.com/AY2122S1-CS2103T-W15-1/tp/issues/187) and UI whitespace bugs [\#173](https://github.com/AY2122S1-CS2103T-W15-1/tp/issues/173)
        * PR [\#198](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/198) covers bug fixes and implementation of test cases to check these specific cases (if applicable).
    * Added summary of follow-up action following the PE-D to each issue raised by reviewers.

* **Review Contributions**
    * PRs Reviewed with comments: [\#120](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/120), [\#139](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/139), [\#155](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/155)

* **Community Contributions**
    * Bugs reported in other teams work: [1](https://github.com/AY2122S1-CS2103T-F13-2/tp/issues/153), [2](https://github.com/AY2122S1-CS2103T-F13-2/tp/issues/154)
    * [Smoke Test CATcher](https://github.com/rushilramesh/ped)
