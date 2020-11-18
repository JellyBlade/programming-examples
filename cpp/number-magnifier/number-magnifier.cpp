// number magnifier
// by Tyler Hippard
// input: 1 char (expected 0-9 or x/X)
// output: "magnified" version of that number (ASCII art)

#include <iostream>

using namespace std;

char numVar;
bool keepGoing = true;
int errCode;
char varList[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'x', 'X' };

/*
Welcome function
Displays title of the program and author once oc
Called by main, no calls, no returns.
*/
void Welcome() {
	cout << endl << "The Number Magnifier --- by Tyler Hippard" << endl << "It magnifies numbers! That's it! Enjoy." << endl;
}

/*
Input char vs Array Checker
Checks the user-inputted char against an array of possible values, by checking equivalency against each character (same as what I used strchr for in my last program)
Called by UserInput(), no calls.
Returns true if the char if the char is in the array, false otherwise.
*/
bool ArrayCheck() {
	for (size_t i = 0; i < size(varList); i++)
	{
		if (numVar == varList[i]) {
			return true;
		}
		if (i == size(varList)) {
			return false;
		}
	}
	return false;
}

/*
User Input
Asks the user to input a digit between 0-9, or x/X to exit the program. Performs all the error-checking this program does. Loops until a valid input is reached.
Called by main, calls nothing
Returns 1 if the inputted value isn't valid, 0 if it is.
*/
int UserInput() {
	cout << endl << "Enter a digit (0-9), or the letter 'x' to exit the program: ";
	cin >> numVar;
	if (cin.fail() || ArrayCheck() == false) {
		cin.clear();
		cin.ignore(2048, '\n');
		cout << "I can't magnify that number yet, sorry.";
		return 1;
	}
	cin.ignore(2048, '\n');
	return 0;
}

/*
Big Numberificator
Turns numbers bigs. Checks numVar (user-input), and outputs the magnified number from a switch statement.
No default, as all values should be accounted for from the user-input function.
Called by main, calls nothing, returns nothing.
*/
void BigNumber() {
	switch (numVar) {
	case 'X':
	case 'x':
		cout << "Thanks for playing!";
		keepGoing = false;
		break;
	case '0':
		cout << endl << " 000 \n0   0\n0   0\n0   0\n0   0\n0   0\n 000 " << endl;
		break;
	case '1':
		cout << endl << "  1  \n 11  \n1 1  \n  1  \n  1  \n  1  \n11111" << endl;
		break;
	case '2':
		cout << endl << " 222 \n2   2\n   2 \n  2  \n 2\n2\n22222" << endl;
		break;
	case '3':
		cout << endl << " 333 \n3   3\n   3 \n 33  \n   3 \n3   3\n 333 " << endl;
		break;
	case '4':
		// cout << endl << "   4 \n  44 \n 4 4 \n4  4 \n44444\n   4 " << endl; ***for closed-style 4***
		cout << endl << "4  4\n4  4\n4  4\n4  4\n44444\n   4 \n   4 \n" << endl;
		break;
	case '5':
		cout << endl << "55555\n5    \n5    \n5555 \n    5\n5   5\n 555 " << endl;
		break;
	case '6':
		cout << endl << " 666 \n6    \n6    \n 666 \n6   6\n6   6\n 666 " << endl;
		break;
	case '7':
		cout << endl << "77777\n    7\n   7 \n  7  \n  7  \n  7  \n  7  " << endl;
		// cout << endl << "77777\n    7\n    7\n    7\n    7\n    7\n    7" << endl; ***simplified digital-style***
		break;
	case '8':
		cout << endl << " 888 \n8   8\n8   8\n 888 \n8   8\n8   8\n 888 " << endl;
		break;
	case '9':
		cout << endl << " 999 \n9   9\n9   9\n 999 \n    9\n    9\n 999 " << endl;
		break;
	}
}

// main function. Calls all the other functions
// has two do-while loops, one for user-input, the other for continuing except when user inputs x/X.
int main() {
	Welcome();
		
	do {
		do {
			UserInput();
		} while (errCode != 0);

		BigNumber();

	} while (keepGoing == true);
	return 0;
}