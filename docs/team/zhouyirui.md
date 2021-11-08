---
layout: page
title: Zhou Yirui's Project Portfolio Page
---

### Project: ClassMATE

ClassMATE is a desktop application designed for instructors of the CS2101 Module in NUS to easily store class and student details. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/)

* **Enhancements Implemented**
  * Added a new parameter ClassCode for Students, Tutorial Group and Tutorial Classes
    * PR [\#113](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/113), PR [\#131](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/131)
    * This parameter is refers to the ClassCode of the Tutorial Class that a Student belongs to. It contains a default 
      value that when assigned to a Student indicates the Student does not have a class.
    * Updated more than 25 files in the process of implementing ClassCode. Includes changing every instance of a Student and Tutorial Class (which used ClassCode as a String initially).
    * Changed the implementation of ClassCode from using INDEX to a parameter when a Student is first added. 
    * Challenge was when I added validation for ClassCode. This resulted in me changing 17 test files in the tedious debugging process, 
      and to correct the previous accepted format of ClassCode in test cases. I also added `TypicalClassmate` to allow verification of ClassCode in tests.
    * The process of implementing ClassCode required in depth knowledge of the `model`, how data for test cases are created, and used for testing for classes. 
  * Adding Student to Tutorial Group
    * PR [\#149](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/149)
    * Allows Student to be assigned to a Tutorial Group within their Tutorial Class
    * Referenced the implementation of `Tags` when adding `Tutorial Group` as parameter of a Student. One difference in implementation
      was that `Tutorial Groups` are accumulated by default. I also made changes to UI to allow Tutorial Groups to be shown in a Student's
      profile card.
    * Added verification of `Tutorial Groups` to ensure Student is only added to at most 1 Tutorial Group of each Group Type. 
  * Deleting Student from Tutorial Group
    * PR [\#150](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/150)
    * Allows Student to be removed from a Tutorial Group within their Tutorial Class
    * Referenced the implementation of `Tags` when adding `Tutorial Group` as parameter of a Student. One difference in implementation
      was that `Tutorial Groups` can be deleted selectively. 
      
* **Documentation**:
    * User Guide:
        * Added documentations for the features `addsg`,`deleteg` and `viewg` [\#66](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/66)
        * Added and updated documentations for the features `addsg` and `deletesg` [\#154](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/154)
        * Added an overview of the structure of User Guide to introduction.
    * README:
        * Added a mockup for classMATE [\#70](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/70)
        * Added details of the project and Continuous Integration badge
    * Developers Guide:
        * Added documentations for the features `classCode`. PR: [\#134](https://github.com/AY2122S1-CS2103T-W15-1/tp/pull/134)
        * Added documentations for the features `addsg` and `deletesg`.
        * Added Use case for Adding Student to Tutorial Group.
   
