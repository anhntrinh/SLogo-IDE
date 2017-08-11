# SLogo

## Names 

**Gideon Pfeffer** **Anh Trinh** **Tahia Emran** **Talha Koc**

## Dates

**Date Started:** Feb 16

**Date Completed:** March 10

**Total Hour Estimate:** 160 - 200 hours total

## Roles

**GUI:** Talha

**Model Classes:** Anh

**Controller Classes and Ask/Tell:** Tahia

**Parsing and Class Generation:** Gideon

## Resources

We used the example code from the CS308 gitlab. example_advanced for example. Stack Overflow helped with some debugging in the factory as well. 

## Starter

The program can be start by running main in src.(default package) 

## Testers

The parser tests testers can be found in the src.parser.tests package

## Files needed 

Images folder is needed for pictures in the GUI. Additionally, resources folder in src is needed for parsing commands. 

## How to use the program

Start the program.Click on the top left menu, then the question mark to get a background of the commands that can be applied/variables/functions. Type away and have fun!

## Any known bugs, crashes, or problems with the project's functionality

For nested command such as product fd 50 rt 30, the order of commands executed on the turtle is reversed ( the turtle rotates then move forward ). Also, the control structures don't support variables to iterate over although they do support commands. For example, must say for [ :x 1 10 1 ] or for [ :x 1 product 1 10 1 ]. make :y 10 for [ :x 1 :y 1 ] is not supported.

## Any extra features included in the project

We did not implement extra features beyond what is required by the basic and complete implementations. 

## Impressions of the assignment to help improve it in the future

This assignment is really well-designed and challenging. It gives room for practicing different design patterns and features such as reflection, observable,  encapsulation with interface, and so on.