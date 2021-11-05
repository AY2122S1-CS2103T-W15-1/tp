---
layout: page
title: User Guide
---


Are you having trouble with administrative work as a CS2101 tutor? Does current tools like Excel sheets or the pen and paper cause a lot of pain
when trying to organize and search for students or recording class participation? Could there be a way to streamline this process
while providing you a better way to visualise the information? Well, ClassMATE has it all covered! ClassMATE is a **desktop app
designed for you to manage student contacts and organize them into their tutorial classes and groups neatly, as well as recording of students'
class participation without much hassle. Having both a Command Line Interface** (CLI)
and a Graphical User Interface (GUI), it saves you time on your CS2101 administrative work while providing a pleasant experience at the same time.

This user guide for ClassMATE which you are reading now, will teach you how to:
* Create students, classes and groups
* Organize groups in their respective classes
* Add students to a class and their respective oral presentation groups within the class
* Filter and search students
* Add class participation marks for students

The first part is a guide on how to set up ClassMATE and an overview of the symbols and syntax we use in the user guide.
The remaining sections explain the important features of ClassMATE, such as the Student,
Marks, Tutorial Class and Tutorial Group Commands.

Use the Table of Contents below to
navigate to relevant sections to learn the commands for ClassMATE.

* Table of Contents
{:toc}
--------------------------------------------------------------------------------------------------------------------

## How to use this User Guide

This User Guide has been created to help users, old and new alike, familiarize themselves with ClassMATE, its features, commands and uses!

* Use the Quick Start [below](#Quick-Start) :arrow_down_small: to setup ClassMATE on your Computer.
* You may view the entire list of ClassMATE's Features by clicking [here](#features).
* Alternatively, you can refer to the Command Summary Table at the bottom, or by clicking [here](#Command-Summary).
* A list of frequently-asked questions (FAQs) is maintained [here](#FAQ)

Throughout the User Guide,

**:information_source: Notes** will be used to give additional points regarding features

**:bulb:Tips** will be used to provide quick information bits about features

**:warning:Warning** will be used to provide warnings about features

We hope you find this User Guide helpful in using ClassMATE!

------

## Quick Start


1. Ensure you have Java `11` or above installed in your Computer. (Go to [this website](https://codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk) and follow the instructions to download and install Oracle JDK 11, which is _basically_ Java 11.)
1. Download the latest `classmate.jar` from [here](https://github.com/AY2122S1-CS2103T-W15-1/tp/releases).
1. Copy the file to the folder you want to use as the _home folder_ for your ClassMATE.
1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui-annotated.png)
1. Type the command in the **Command-Line Input** and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:
   * **`liststu`** : Lists all students.

   * **`addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06`**: Adds a student named `John Doe` to ClassMATE.

   * **`deletestu 3`**: Deletes the 3rd student shown in the current list.

   * **`clear`** : Deletes all students.

   * **`exit`** : Exits the app.
1. Refer to the [Features](#features) below for details of each command.


### Command Features

<div markdown="block" class="alert alert-info">
**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `addstu n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* You can add parameters in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, you can also type `p/PHONE_NUMBER n/NAME` for the parameters.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* If you add parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`), they will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Entering format: `help`

## Student Commands

### Adding a student: `addstu`

Adds a student to ClassMATE.

Entering format: `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE [t/TAG]…​`

<div markdown="span" class="alert alert-info">:information_source: **Notes about addstu:** <br>

* The Name of a student accommodates special characters such as hyphens, apostrophes and slashes.
* The phone number should be at least 3 digits long.
* The tutorial class with the given Class Code must already exist in classmate.
* A student can have any number of tags (including 0)

</div>

Examples:
* If class G06 has not been created, add the class first using `addc`.
* `addstu n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/G06`
* `addstu n/Betsy Crowe t/proactive e/betsycrowe@example.com a/10 Kent Dr, #02-02 p/1234567 c/G06 t/teamPlayer`

### Editing a student : `editstu`

Edits an existing student in ClassMATE.

Entering format: `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_CODE] [t/TAG]…​`

* Edits the student at the specified `INDEX`. The index refers to the index number shown in the displayed student list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the student will be removed i.e adding of tags is not cumulative.
* You can remove all the student’s tags by typing `t/` without
  specifying any tags after it.

Examples:
*  `editstu 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st student to be `91234567` and `johndoe@example.com` respectively.
*  `editstu 2 n/Betsy Crower t/` Edits the name of the 2nd student to be `Betsy Crower` and clears all existing tags.

### Viewing a student : `viewstu`

![view student](images/viewstu.png)

View a student's details in ClassMATE, as shown in the picture above

Format: `viewstu INDEX`

* Views the student's details at specified `INDEX`
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1, 2, 3, …​

Examples:

* `liststu` followed by `viewstu 2` shows second student in the student list.
* `findstu Betsy` followed by `viewstu 1` shows the 1st student in the results of the find command.

### Listing all students : `liststu`

Shows a list of all students in ClassMATE.

Entering format: `liststu`

* All students stored are listed.

### Finding students by name: `findstu`

Finds students whose names contain any of the given keywords.

Entering format: `findstu KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`

Examples:
* `findstu John` returns `john` and `John Doe`
* `findstu alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting a student : `deletestu`

Deletes the specified student from the student list.

Entering format: `deletestu INDEX`

* Deletes the student at the specified `INDEX`.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `liststu` followed by `deletestu 2` deletes the 2nd student in the student list.
* `findstu Betsy` followed by `deletestu 1` deletes the 1st student in the results of the `findstu` command.

## Student Marks Commands

There are 6 types of Marks that can be assigned to a student, for the numerous sessions that the students attends. It is meant to be a class participation score, but its use can be flexible. The marks that can be assigned are:

* Poor
* Low
* Avg
* Good
* High
* Excellent

### Adding Latest Mark: `addlm`

Adds the mark for latest session.

Entering format: `addlm INDEX m/Mark`

* Adds mark as the mark for latest session.
* Mark is not case-sensitive.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by`addlm 2 m/Low` assigns LOW mark to the latest session for 2nd student in the student list.
* `findstu Betsy` followed by `addlm 1 m/excellent` assigns EXCELLENT mark to the latest session for 1st student in the results of `findstu`.

### Deleting Latest Mark: `deletelm`

Deletes the mark for latest session.

Entering format: `deletelm INDEX`

* Deletes mark as the mark for latest session.
* Student has to have some marks assigned previously.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by `addlm 2 m/Low`  and `deletelm 2` deletes the LOW mark assigned to the 2nd student in the student list.
* `findstu Betsy` followed by `deletelm 1` deletes latest sessions' mark for 1st student in the results of `findstu`.

### Deleting All Marks: `deleteam`

Deletes the all marks for student.

Entering format: `deleteam INDEX`

* Deletes all marks.
* Student has to have some marks assigned previously.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:

* `liststu` followed by `addlm 2 m/Low`  and `deleteam 2` deletes all marks assigned to the 2nd student in the student list.
* `findstu Betsy` followed by `deleteam 1` deletes all sessions' mark for 1st student in the results of `findstu`.

### Clearing all students : `clear`

Clears all students from ClassMATE. Below is how it would look like.

![clear](images/clear.png)

Entering format: `clear`

<div markdown="span" class="alert alert-primary">:warning: **Warning:**
This command deletes **ALL** students and is irreversible :warning:
</div>

## Tutorial Class Commands

### Adding a tutorial class : `addc`

Adds a tutorial class to ClassMATE.

Entering format: `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`

<div markdown="span" class="alert alert-primary"> :bulb: **Note:**

* Class Code should consist of 'G' followed by two numerical digits (i.e. any value from 'G01' to 'G99').
* Schedule consists of 2 weekly timeslots
</div>

Examples:
* `addc c/G06 s/Tuesday 2 to 4pm, Friday 2 to 4pm`
* `addc c/G01 s/Monday 10am to 12pm, Thursday 10am to 12pm`

### Viewing a class: `viewc`

![viewing a class](images/viewc.png)

Views a class in ClassMATE, as shown above

<div markdown="span" class="alert alert-info">:information_source: **Note:**<br>
`viewc` highlights the class chosen, and filters out only students in the class!
</div>

Entering format: `viewc INDEX`

* Views the class details at the specified INDEX.
* Details of a class includes students in the class and the class schedule.
* The index refers to the index number shown in the displayed list of classes.
* The index must be a positive integer 1, 2, 3, …​

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
In order to find a specific class, use the `findc` command to find a particular class before viewing it.
</div>

Examples:

* `listc` followed by `viewc 2` shows the 2nd class in the displayed class list.

### Listing all classes : `listc`

Shows a list of all classes in ClassMATE.

Entering format: `listc`

### Finding classes by class codes: `findc`

Find classes by class codes.

Entering format: `findc KEYWORD [MORE_KEYWORDS]`

* The search is not absolute. e.g `G0` will match `G06`

Examples:
* `findc G02` returns `G02` if it exists
* `findc G` returns `G01`, `G02`, `G03`<br>

### Deleting a class: `deletec`

Deletes a class from ClassMATE

Entering format: `deletec INDEX`

* Deletes the class at the specified INDEX.
* The index refers to the index number shown in the displayed list of classes.
  The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listc` followed by `deletec 2` deletes the 2nd class in the list of classes.
* `findc G06` followed by `deletec 1` deletes the 1st class in the results of the `findc` command.

## Tutorial Group Commands

These are the commands that involve tutorial groups!

The first part covers interactions between groups and classes, while the second part
covers interactions between students and groups.

### Adding a group: `addcg`

Adds a group to a particular tutorial class in ClassMATE

Entering format: `addcg gn/GROUP_NUMBER c/CLASS_CODE type/GROUP_TYPE`


**:information_source: Note:**<br>
* GROUP_NUMBER should be a single digit

* GROUP_TYPE refers to the assignment that the group will work for. It should only be OP1 or OP2.

* The class must have already been added to ClassMATE first. If you are unsure, you can refer to
  [Adding a Tutorial Class](#adding-a-tutorial-class)
  
* Tutorial groups are identified by GROUP_NUMBER, GROUP_TYPE and CLASS_CODE. This means that
  any two tutorial groups are identical if all three fields are identical, which is not allowed.

Examples:
* `addcg gn/1 c/G01 type/OP1` adds Group 1 to class `G01` assigned to the task `OP1`


### Viewing a Group: `viewg`

Lists students in a particular tutorial group in ClassMATE

Entering format: `viewg c/CLASS_CODE type/GROUP_TYPE gn/GROUP_NUMBER`

**:information_source: Note:**<br>

Only `OP1` and `OP2` are accepted as Group Types.


Examples:
* `viewg c/G06 type/OP2 gn/1` lists the students in `OP2` Group `1` of class `G06`


### Deleting a Group: `deletecg`

Deletes a group from ClassMATE


Entering Format: `deletecg c/CLASS_CODE type/GROUP_TYPE gn/GROUP_NUMBER`

**:information_source: Note:**<br>
* GROUP_NUMBER should be a single digit

* GROUP_TYPE refers to the assignment that the group will work for. It should only be OP1 or OP2.


Examples:
* `deletecg c/G06 type/OP2 gn/1` deletes the `OP2` Group `1` of class `G06`

### Adding Student to a group: `addsg`

Adds a student to a group.

Entering format: `addsg INDEX gn/GROUP_NUMBER c/CLASSCODE type/TYPE`

* Adds the student to a group in the class

**:information_source: Note:**<br>

Only `OP1` and `OP2` are accepted as Group Types.


Example:
* `liststu c/G06`shows that Betsy is a student in class G06, with Index 1.
  `addsg 1 gn/1 c/G06 type/OP1` then adds the student at Index 1, Betsy, to OP1 Group 1 in class G06

### Deleting Student from a group: `deletesg`

Deletes a student from a group.

Entering format: `deletesg INDEX g/GROUP_NUMBER c/CLASSCODE type/TYPE`

* Deletes the student from a group in the class
* Type refers to the assignment that the group will work together for

**:information_source: Note:**<br>

Only `OP1` and `OP2` are accepted as Group Types.


Example:
* `liststu c/G06`shows that Betsy is a student in class G06 with Index 1.
  `deletesg 1 g/A c/G06 type/OP1` then removes Betsy from OP1 Group A in class G06


### Exiting the program : `exit`

Exits the program.

Entering format: `exit`

### Saving the data

ClassMATE data is saved in the hard disk automatically after any command that changes the data. **There is no need to save manually.**

### Editing the data file

ClassMATE data are saved as a JSON file `[JAR file location]/data/classmate.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, ClassMATE will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ClassMATE home folder.

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Help** | `help`
**Add student** | `addstu n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS c/CLASS_CODE [t/TAG]…​`<br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/G01 atd/1 t/attentive`
**Edit student** | `editstu INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CLASS_CODE] [t/TAG]…​`<br> e.g., `edit 2 n/James Lee e/jameslee@example.com`
**View student** | `viewstu INDEX`<br> e.g., `liststu` followed by `viewstu 2`
**Find student** | `findstu KEYWORD [MORE_KEYWORDS]`<br> e.g., `findstu John`
**Delete student** | `deletestu INDEX`<br> e.g., `liststu` followed by `deletestu 3`
**List students** | `liststu [c/CLASS_CODE]`<br> e.g., `liststu c/G06`
**Add class** | `addc c/CLASS_CODE s/SCHEDULE [t/TAG]…​`<br> e.g., `addc c/G06 s/Tuesday 2 to 4pm, Friday 2 to 4pm`
**Delete class** | `deletec INDEX`<br> e.g., `listc` followed by `deletec 2`
**View class** | `viewc INDEX`<br> e.g., `listc` followed by `viewc 3`
**List all classes** | `listc`
**Find class** | `findc KEYWORD [MORE_KEYWORDS]`<br> e.g., `findc A02`
**Add Tutorial Group** | `addcg gn/GROUP_NUMBER c/CLASS_CODE type/TYPE` <br> e.g.,`addcg gn/1 c/G11 type/OP1`
**Delete Tutorial Group** | `deletecg INDEX` <br> e.g., `deletecg 2`
**List Tutorial Group** | `listg`
**Add Student to Group** | `addsg INDEX g/GROUP_NUMBER c/CLASSCODE type/TYPE` <br> e.g., `addsg  1 gn/1 c/G01 type/OP1`
**Delete Student from Group** | `deletesg INDEX g/GROUP_NUMBER c/CLASSCODE type/TYPE` <br> e.g., `deletesg  1 gn/1 c/G01 type/OP1`
**Clear all students** | `clear`
**Exit ClassMATE** | `exit`

## Glossary

**Java 11** | Java is a programming language, more on it [here](https://en.wikipedia.org/wiki/Java_(programming_language)).
**JSON** | a JSON file is an open standard file format, more on it [here](https://en.wikipedia.org/wiki/JSON).3
