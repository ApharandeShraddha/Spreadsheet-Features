# Spreadsheet-Features
Application of design principles to implement a spreadsheet feature

----------------------------------------------------------------------------------------------------------------------------------
Project Description:
Observer Pattern used to implement the feature of a spreadsheet wherein values of cells get updated, when the cells they are dependent on get updated.

----------------------------------------------------------------------------------------------------------------------------------
An example of the input.txt file:

a1=32+27
a2=a1+37
a3=a1+a2
b1=29+31
a1=37+b1

Each line of the input file will have a simple expression. 

The value before the equals sign will always refer to the cell. The right hand side will always have two operands and the "+" operator. 

The operands will be either two digit integers greater than 10 or cell indexes. The cell indexes will only refer to rows a-z, and columns 1-26.

------------------------------------------------------------------------------------------------------------------------------------

DEBUG_VALUE=1 [Print to stdout the total cycles detected]
DEBUG_VALUE=0 [Print to stdout The sum of all cell values is: X"] where X= sum of the cells

------------------------------------------------------------------------------------------------------------------------------------

Assuming you are in the directory containing src folder

## To clean:
ant -buildfile src/build.xml clean
------------------------------------------------------------------------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

------------------------------------------------------------------------------------------------------------------------------------

## To run by specifying arguments from command line 

ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=output.txt -Darg2=0

------------------------------------------------------------------------------------------------------------------------------------

justification for Data Structures used in this assignment in term of Big O complexity (time and/or space)

ArrayList :- Time complexity for ArrayList is O(1) for add which is faster, O(n) for remove and O(1) for Get. In this assignment i am storing students in Arraylist,As i need to add each student in to List after course allocation and for add time complexity of Arraylist is O(1).
