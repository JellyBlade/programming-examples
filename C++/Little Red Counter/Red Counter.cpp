#include<iostream>
#include"Counter.h"


/*
	Name: Little Red Counter
	Function: Provides a counter for adding up the price of groceries, similar to the one used by the person's mom in that story. It counts, and overflows, and then has to be reset. 
		      I also had fun with it by making the text red, in-line with the story of the counter.
	Calls: All of the functions supplied by the Counter class
	Called by: It's main, so... the exe I guess
	Pre-Con.: the exe started and works
	Post-Con.: The user has counted a bit with their own custom-size counter, and enjoyed the text colors I put in.
	
	Notes: To display properly, as intended, an ANSI-compatible console is suggested, for the text color and console window clearing
*/
int main() {
	int redSize = 0, redIncrement = 0;
	bool goAgain = false, isOverflowed = false;
	char goAChar, redOption;
	std::cout << "\x1B[91m"; //ansi code for red
	std::cout << "\nWelcome to the Little Red Counter!\n\n";
	std::cout << "\x1B[37m"; // ansi code for white
	std::cout << "How big would you like your little red counter to be?: ";
	std::cin >> redSize; 
	if (redSize <= 0) { // default to 9999 if invalid
		std::cout << "Invalid input, defaulting to size '9999'\n";
		redSize = 9999;
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
	}
	Counter red = Counter(redSize); // calling constructor

	do {
		if (isOverflowed == true) { // alert the user to reset the counter if needed.
			std::cout << "\x1B[91m" << "WARN: Counter is overflowed! Please reset the counter.\n" << "\x1B[37m";
		}
		
		std::cout << "Commands: \n\t[a] - Cents\n\t[s] - Dimes\n\t[d] - Dollars\n\t[f] - Tens of Dollars\n\t[o] - Overflow\n\t[r] - Reset\n";
		std::cout << "Enter your command followed by a number (1-9): ";	
		std::cin >> redOption;
		if (redOption != 'r' && redOption != 'o') { // I wanted 'r' and 'o' to execute without a number, but will also work with one anyways. I liked the flow of not having to enter a number for those two.
			std::cin >> redIncrement;
		}

		redOption = tolower(redOption);
		switch (redOption) {
		case 'a':
			red.incr1(redIncrement); 
			break;
		case 's':
			red.incr10(redIncrement); 
			break;
		case 'd':
			red.incr100(redIncrement);
			break;
		case 'f':
			red.incr1000(redIncrement);
			break;
		case 'o':
			red.forceOverflow(); // size of counter + 1 is added, so it can overflow from 0
			break;
		case 'r':
			red.reset(); // To 0
			break;
		}

		isOverflowed = red.overflow(); // bool output
		if (isOverflowed == false) {
			std::cout << "Your current counter is: " << red.getCount(); // display count if not overflowed
		}
		else if (isOverflowed == true) { // else display error message and amount overflowed by
			std::cout << "\x1B[91m" << "\n\n****OVERFLOW****" << "\x1B[37m"; // ANSI for white and red text colors
			std::cout << "\nThe counter is overflown by: " << red.getCount() - redSize;
		}
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

		std::cout << "\nWould you like to use the counter again?: ";
		std::cin >> goAChar;
		if (goAChar == 'n' || goAChar == 'N') {
			goAgain = false;
		}
		else {
			goAgain = true;
		}
		std::cout << "\x1B[2J\x1B[H"; // ANSI escape code to "clear" the screen, and works on *nix, unlike system("cls"). requires ANSI-compatibile console though, unfortunately.
		std::cin.clear();
		std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
	} while (goAgain == true);
	return 0;
}
