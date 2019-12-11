Ex1

This assigment is representing an Object Oriented Java project which computes several mathematical functions over a visual interface (GUI) which includes the following classes:

 1. Monom Functions: Represents Mathematical Monom in Object Oriented programming in the form of ax^b where a is a (double) real number and b(int) is a natural number.
    The monom class has two objects, the coefficiant and the power of the monom.


 2. Polynom Functions: Represents Mathematical Polynom in Object Oriented programming in the form of a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n, which is consisted of  ArrayList collection of Monoms.

 
 3. Compex Function: Represents Mathematical Complex Function in the form of (operation(operation(operation,operation),function),function) and so on.
    Complex Function Class is built with three Objects:
 a. Left-The Left Function.
 b. Right-The Right Function.
 c. The Operation (OP).

 4. FunctionGUI: This class is able to representing a collection of functions that can be saved to a file, be read from a file, and displayed in GUI form using STDDraw.
FunctionGUI is consisted of Arraylist collections representing a function of the three mentioned above.
 
 

Importent Notes and limitations:

* Function is an interface to the above mentioned classes and the functions as a name are implemented in all of the above classes.

* polynom_able is another interface that corresponds to function in the polynom class workspace.

*equals function in compexfunction (obj) - function which compares functions. To accomplisg this we made several tests. 
Check equality between each monom to monom, Monom to Polynom, Polynom  to polynom Polynom to Monom ComplexFunction to ComplexFunction on so on.
if a complex function has mul or plus operation we defined it as a polynom with add/mul function and then initiated the comparison.
If the complex function Operator is None we compared the function to the left function of the polynom.
This method works only on cases where there is only one operation since if there is more than one operation complex function can be compared only to itself as a data structure.

*ComplexFunction with Operation - none. We Built the constructor so that it can only create a complex function with this operation if there is only one function on the left and 0 at the right,
all other cases are forbidden.
If there are functions from both sides in case where there is operation None, this is a an incorrect function since there is no math operation between the left and right functions.
Furthermore a calculation of this kind is forbidden because it may report wrong results, therefor we desided to output an error to the user.

* The ComplexFunction div returns an error if the function got 0 as input.

* The project is using json.simple.jar

* ComplexFunction Class  can accept only lower case letter in String Constructor - eg. 'mul' 'max' 'div'....
 
 
 
 
 
