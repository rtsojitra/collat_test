# collat_test

==================================================
Purpose:
==================================================

The purpose of this project is  to read txt file located at "/tmp/collat_test" folder which can contains serias of words delimited by spaces and printing out PRIMITIVE DEFINITIONs, DEFINITIONs and KEYWORDs in the order.
If there is any comment in the file starting with #, then it will be excluded.
Each subsequent word is used to define the work which is called Definition.
example: In "blue color", blue is keyword and color is definition

Keywords can have definition of period which is considered as PRIMITIVE KEYWORD
example: "color ."



==================================================
Pre-requisite:
==================================================
- This project is written in JAVA so in order to run the project, JAVA needs to be installed on the system.
- input text file should be located in "/tmp/collat_test" folder. If you're using windows OS, please place file in C:\tmp\collat_test


==================================================
Steps to run project
==================================================
1) copy the project in any directory
2) open terminal
3) navigate to "{directory}/Collat_test/src/" folder
4) execute "javac CollatTesterMain.java" to compile the project
5) to run the project, execute "java CollectTesterMain".


==================================================
Example of INPUT/OUTPUT 
==================================================
Below are examples of input and output.
-------------------------------------------------
File-1.txt
-------------------------------------------------

sky blue 

sun yellow round

blue color

color . # example, pink blue red

shape .

round shape

circle shape


File-1.txt output

..................................................


color, shape, blue, round, yellow, sky, circle, sun

-------------------------------------------------
File-2.txt
-------------------------------------------------

red color

color .

blood red


File-2.txt output

..................................................

color, red, blood
