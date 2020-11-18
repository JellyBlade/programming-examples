#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>

using namespace std;

// utility functions
string sanitizeCase(string sanword);
bool isPunctWOApostrophe(char i);
int biggestWord(vector<string> word);

// user-input functions
void bannerOptions(char& option, int& cols, vector<string>& wordsToFind);
void getWordsToFind(vector<string>& wordsToFind, bool import);
ifstream getFile(string& fileToRead);

// work functions
void readFile(ifstream filelyze, vector<string>& word, vector<int>& count);
void sortVector(vector<string>& word, vector<int>& count, int option, vector<string>& wordsToFind);

// output functions
void reportToScreen(vector<string> word, vector<int> count, int colss);

/*
	The program in it's default state will display the unique words found in a text file in 3 columns. There are also a few other options
	to choose from.
*/
int main() {
	vector<string> word, wordsToFind;
	vector<int> count;
	char displayOption, keepGoingChar;
	int colCount;
	string fileToRead;
	bool keepGoing = true;;

	do {
		bannerOptions(displayOption, colCount, wordsToFind);
		readFile(getFile(fileToRead), word, count);
		sortVector(word, count, displayOption, wordsToFind);
		if (displayOption != 'f' && displayOption != 'i') { // only display chosen words and their occurrences, bypassing the usual display method.
			reportToScreen(word, count, colCount);
		}
		cout << "\nWould you like to read another file? (Y/N): ";
		cin >> keepGoingChar;
		if (keepGoingChar == 'n' || keepGoingChar == 'N') {
			keepGoing = false;
			cout << "\nThanks.\n";
		}
		cin.clear();
		cin.ignore(numeric_limits<streamsize>::max(), '\n');
		word.clear(); wordsToFind.clear(); count.clear(); // clearing vectors for next run
	} while (keepGoing == true);
	return 0;
}

/*
	Name: Get Words to Find
	Function: Either reads a .csv file for -- or asks user to input -- words that the program will look for when reading the text file. 
	Returns: a vector containing unique words to look for
	Calls: N/A
	Called by: bannerOptions()
	Pre-Con.: User must have selected either "Find" or "Import Find" as options.
	Post-Con.: a vector is filled with words to look for when reading a file.
*/
void getWordsToFind(vector<string>& wordsToFind, bool import) {
	string findWordLine, subStr, tempString;
	if (import == false) {
		getline(cin, findWordLine);
	}
	else if (import == true) {
		string csvName = "csv";
		string findWordLine2;
		ifstream csvFile = getFile(csvName);
		while (getline(csvFile, findWordLine, '\n')) {
			tempString += findWordLine;
		}
		findWordLine = tempString;
	}
	stringstream ss(findWordLine);
	while (ss >> findWordLine) { // removing spaces
		stringstream s2(findWordLine);
		while (s2.good()) { // removing commas
			getline(s2, subStr, ',');
			subStr = sanitizeCase(subStr);
			subStr.erase(remove_if(subStr.begin(), subStr.end(), isPunctWOApostrophe), subStr.end()); // removing punctuation attached to the word
			vector<string>::iterator icsv = (find(wordsToFind.begin(), wordsToFind.end(), subStr));
			if (subStr.size() != 0) { // not adding empty strings to vector
				if (icsv != wordsToFind.end()) {
					continue;
				}
				else {
					wordsToFind.push_back(subStr);
				}
			}
		}
	}
}

/*
	Name: Banner Options
	Function: Display welcome banner, and ask the user how they would like to use the program, and how its information will be displayed.
	Returns: User-choice in option, number of display columns, and which words to find for the Find option.
	Calls: getWordsToFind()
	Called by: main()
	Pre-Con.: None
	Post-Con.: Display option, # of columns to display, and list of CSV/space-separated words to find are ready for use.
*/
void bannerOptions(char& option, int& cols, vector<string>& wordsToFind) {

	cout << "\n*-- Document Analysis: Word Counter Edition --*\n\n";
	cout << "Options:\n";
	cout << "\t[D]efault: No Sorting\n" << "\t[C]ount: Highest-to-Lowest by # of appearances\n";
	cout << "\t[A]lphabetical Order: A-Z\n" <<"\t[F]ind: Search for inputted words\n";
	cout << "\t[I]mport Find: Search for words using a .csv file";
	cout << "\nPlease select a display option: ";
	cin.get(option);
	option = tolower(option);


	switch (option) {
	case 'd':
		cout << "Default option selected.\n";
		break;
	case 'c':
		cout << "Sort by Count selected.\n";
		break;
	case 'a':
		cout << "Sort by A. Order selected.\n";
		break;
	case 'f' :
			cout << "Find word selected.\n";
			cout << "Please select the words you'd like to search for, separated by spaces and/or commas: ";
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			getWordsToFind(wordsToFind, false);
			cout << "Press Enter...";
			break;
	case 'i' :
		cout << "Import Find selected.\n";
		getWordsToFind(wordsToFind, true);
		cout << "Press Enter...";
		break;
	default:
		if (option != '\n') { // don't display warning message if users hit enter key for option
			cout << "WARN: Invalid Display option. ";
		}
		cout << "Display option has been set to \"Default\"\n";
		option = 'd';
		break;
	}


	if (option != 'f' && option != 'i') { // no columns for any "Find" mode
		cout << "Please input how many columns (1-4) you would like to display in: ";
		cin >> cols;
		if (cols < 1) {
			cout << "Cannot display that value in columns, defaulting to a 3-col display";
			cols = 3;
		}
		if (cols > 4) {
			char proceedChar;
			cout << "Displaying more than 4 columns is not suggested. This may cause unintended display side-effects.\nContinue with " << cols << " columns?: (Y/[N]) ";
			cin.clear();
			cin.ignore(numeric_limits<streamsize>::max(), '\n');
			cin.get(proceedChar);
			if (proceedChar == 'y' || proceedChar == 'Y') {
				cout << cols << "-col display has been selected.\n"; // If users want to, they may display with many columns, but this isn't recommended. It kinda looks cool though.
			}
			else {
				cout << "Defaulting to a 3-col display\n"; // default to 3 if they don't want their n-column display
				cols = 3;
			}
		}
	}
	return;
}

/*
	Name: Get File
	Function: Gets a file for the readFile() function to read.
	Returns: an active ifstream to read, as well as the name of the file.
	Calls: N/A
	Called by: readFile()
	Pre-Con.: None
	Post-Con.: A file has been chosen and opened successfully.
*/
ifstream getFile(string& fileToRead) {
	ifstream filelyze;
	bool noFile = false, csvFlag = false;
	cin.clear();
	cin.ignore(numeric_limits<streamsize>::max(), '\n');

	do {
		if (fileToRead == "csv" || csvFlag == true) { // starting value of csv Import function is "csv", then its flagged after that.
			csvFlag = true;
			cout << "Please enter the CSV file for words to search for: ";
		}
		else {
			cout << "Please input the name of the file you'd like to analyze: ";
		}
		getline(cin, fileToRead);
		if (fileToRead.at(0) == '"') { //remove quotes from the filename, if necessary
			while (fileToRead.find('"') != fileToRead.npos) {
				fileToRead.erase(fileToRead.find('"'), 1);
			}
		}
		filelyze.open(fileToRead); // temp open file to test its existence
		if (!filelyze) {
			noFile = true;
			cout << "The file \"" << fileToRead << "\" does not exist!" << endl;
		}
		else {
			noFile = false;
			return filelyze;
		}
	} while (noFile == true);
	abort(); // This line should never run, ever. If it does, somehow, the abort is justified, because the rest of the program will explode otherwise.
}

/*
	Name: Is Punctuation? But Without Apostrophe (or hypen)
	Function: Unary predicate for 'remove_if'. Same as ispunct(), but not including apostrophes or hyphens.
	Returns: bool
	Called by: readFile(), bannerOptions()
*/
bool isPunctWOApostrophe(char i) { return(ispunct(i) && i != '\'' && i != '-'); }

/*
	Name: Sanitize Case
	Function: tolowers every character in the inputted string
	Returns: a fully lowercase string
	Called by: readFile(), bannerOptions(), and getWordsToFind()
	Calls: N/A
	Pre-Con.: None
	Post-Con.: every string in all of the vector<string>'s will be lowercase
*/
string sanitizeCase(string sanword) {
	for (size_t i = 0; i < sanword.size(); i++)
	{
		sanword.at(i) = tolower(sanword.at(i));
	}
	return sanword;
}

/*
	Name: Read File
	Function: Reads the supplied ifstream file for words, adding them to a vector and setting a counter to 1 if they're unique,
	or incrementing a counter for that word if they're not.
	Returns: Fully-supplied vectors of words and their occurrences.
	Calls: sanitizeCase()
	Called by: main()
	Pre-Con.: A file is successfully inputted and opened
	Post-Con.: Two vectors are full, ready to be sorted and/or displayed
*/
void readFile(ifstream filelyze, vector<string>& word, vector<int>& count) {
	string wordGetter;

	while (filelyze >> wordGetter) {
		wordGetter.erase(remove_if(wordGetter.begin(), wordGetter.end(), isPunctWOApostrophe), wordGetter.end()); // Remove punctuation from the word, except apostrophes. 
		if (wordGetter.size() == 0) { // skip empty TODO: Figure out if this is necessary, now that hyphens aren't cut out.
			continue;
		}
		wordGetter = sanitizeCase(wordGetter);
		vector<string>::iterator it = (find(word.begin(), word.end(), wordGetter)); // iterator gets the position of the unique word if it exists
		if (it != word.end()) { // not unique
			int index = distance(word.begin(), it);
			count[index]++;
		}
		else { // unique
			word.push_back(wordGetter);
			count.push_back(1);
		}
	}
	filelyze.close();
	return;
}

/*
	Name: Biggest Word
	Function: Finds the size of the biggest word in the word vector, for calculating column width.
	Returns: int for size of the biggest word
	Calls: N/A
	Called by: main()
	Pre-Con.: word vector is filled
	Post-Con.: ScreenSize has a value for the biggest word's size
*/
int biggestWord(vector<string> word) {

	size_t biggest = 0;
	for (size_t i = 0; i < word.size(); i++) 
	{
		string sizeChk = word[i];
		if (biggest < sizeChk.size()) {
			biggest = sizeChk.size();
		}
	}
	return biggest;
}

/*
	Name: Sort Vector(s)
	Function: Re-arranges the word and count vectors based on user selection of alphabetical, by # of occurrence, or none. Also checks to see
	if the user-inputted words have been found or not, bypassing the column output. 
	Returns: Sorted word and count vectors
	Called by: main()
	Calls: N/A
	Pre-Con.: Vectors are filled, user may have inputted words to find and a sorting option.
	Post-Con.: Vectors are sorted based on user-selection, or found words are output and the program is done.
*/
void sortVector(vector<string>& word, vector<int>& count, int option, vector<string>& wordsToFind) {
	if (option == 'd') {
		// No sorting needed
		return;
	}
	else if (option == 'c') {
		// sort by count
		for (size_t i = 0; i < count.size() - 1; i++)
		{
			for (size_t j = 0; j < count.size() - i - 1; j++) {
				if (count[j] < count[j+1]) {
					swap(count[j], count[j + 1]);
					swap(word[j], word[j + 1]);
				}
			}
		}
	}
	else if (option == 'a') {
		// alphabetical sorting
		for (size_t i = 0; i < word.size() - 1; i++)
		{
			for (size_t j = 0; j < word.size() - i - 1; j++) {
				if (word[j] > word[j + 1]) {
					swap(count[j], count[j + 1]);
					swap(word[j], word[j + 1]);	
				}
			}
		}
	}
	else if (option == 'f' || option == 'i') {
		// specific word search
		for (size_t i = 0; i < wordsToFind.size(); i++)
		{

			vector<string>::iterator findIt = (find(word.begin(), word.end(), wordsToFind[i])); // same as uniqueness in readFile()
			if (findIt != word.end()) {
				int indexOfFound = distance(word.begin(), findIt);
				cout << "\nThe word '" << wordsToFind[i] << "' appears " << count[indexOfFound] << " time(s).";
			}
			else {
				cout << "\nThe word '" << wordsToFind[i] << "' was not found in the file.";
			}
		}
	}
	return;
}

/*
	Name: Report To Screen
	Function: Display the unique words and their counts to the console screen, in the user-specified # of columns.
	Returns: N/A
	Calls: N/A
	Called by: N/A
	Pre-Con.: vectors are filled and sorted, the Find option wasn't chosen, and nothing broke.
	Post-Con.: Full data of words and counts are displayed onto the screen, and the program is done (unless the user wants to go again).
*/
void reportToScreen(vector<string> word, vector<int> count, int cols) {
	int screenSize = biggestWord(word);
	cout << endl;
	for (size_t i = 0; i < word.size(); i++)
	{
		string test = word[i];
		if ((i+1) % cols == 0) { // i+1 solved a display issue on the first row, where number of displayed columns would be cols+1 because i starts as zero.
			cout << word[i] << setw((screenSize + 3) - test.size()) << right << count[i] << endl;
		}
		else {
			cout << word[i] << setw((screenSize + 3) - test.size()) << right << count[i] << " | " << left;
		}
	}
	cout << endl;
	return;
}