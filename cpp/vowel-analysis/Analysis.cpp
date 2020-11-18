#include<iostream>
#include<iomanip>
#include<fstream>
#include<string>

using namespace std;
const int ARRAY_MAXSIZE = 5;

void nameGrabber(string& nameGrab, string& fileOldName);
ifstream getFile(string& fileToRead);
void readFile(ifstream filelyze, int vowelArray[]);
void generateReport(ofstream& of, int vowelArray[], string fileName);
void weightStars(ofstream& of, int rawNum, int option, int bigNum);

/*
	The main function
	By the end of the program, the user will be asked to
	provide a file, which will then be read for the number of each vowel type
	This will then be displayed into an analysis file, with a scaled table showing # of vowels.
	Certified Bee Movie Script tested!
*/
int main() {		
	//								  a  e  i  o  u 
	int vowelArray[ARRAY_MAXSIZE] = { 0, 0, 0, 0, 0 };
	string fileName, fileOldName;

	cout << "Document Analysis: Vowel Counter Edition\n";
	readFile(getFile(fileName), vowelArray);
	nameGrabber(fileName, fileOldName);
	ofstream analysisFile; //declare output stream
	analysisFile.open(fileName + "-Analysis.txt");
	generateReport(analysisFile, vowelArray, fileOldName);

	return 0;
}

/*
	Name: Get File
	Function: Prompts the user to input a filename, which is then tested for validity.
	Calls: Nothing
	Called by: main()
	Returns: The opened filestream of the successfully-inputted file
	Pre-Con.: program is run
	Post-Con.: A filestream is successfully opened and ready for reading.
*/
ifstream getFile(string& fileToRead) {
	ifstream filelyze;
	bool noFile = false;
	do {
		cout << "Please input the name of the file you'd like to count: ";
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
	Name: Name Grabber
	Function: Takes the file name given by the user, and removes the path and file extension from the file name, if necessary.
	Calls: N/A
	Called by: main()
	Returns: N/A
	Pre-Con.: successful file input
	Post-Con.: N/A
*/
void nameGrabber(string& nameGrab, string& fileOldName) {
	fileOldName = nameGrab;
	nameGrab = nameGrab.substr(nameGrab.find_last_of("\\") + 1); // cut out path, e.g. "C:\Users\Linda\Documents\..."
	nameGrab = nameGrab.substr(0, nameGrab.find(".")); // cut out extension, e.g. ".txt, .rtf, etc-
	return;
}

/*
	Name: Read File
	Function: Reads each character in the inputted file, checks if they're a vowel, and incrementis the appropriate array element.
	Calls: N/A
	Called by: main()
	Returns: N/A
	Pre-Con.: successful file input
	Post-Con.: vowelArray() is filled with the counts of each vowel type found in the file.
*/
void readFile(ifstream filelyze, int vowelArray[]) {
	char charCounter;
	while (filelyze >> charCounter) {
		if (isupper(charCounter) != 0 || islower(charCounter) != 0) { //testing for alphabetical characters
			charCounter = tolower(charCounter);
			switch (charCounter) {
			case 'a':
				vowelArray[0]++;
				break;
			case 'e':
				vowelArray[1]++;
				break;
			case 'i':
				vowelArray[2]++;
				break;
			case 'o':
				vowelArray[3]++;
				break;
			case 'u':
				vowelArray[4]++;
				break;
			}
		}
		else {
			continue;
		}
	}
	filelyze.close();
	return;
}

/*
	Name: Weighted Stars
	Function: Outputs *'s to the o-filestream, to a max of 40, based on several weighting styles.
	Calls: N/A
	Called by: generateReport()
	Returns: N/A
	Pre-Con.: nothing broke
	Post-Con.: Stars representing the # of that vowel type is written to the file.
*/
void weightStars(ofstream& of, int rawNum, int option, int bigNum) {
	int weightNum;
	switch (option) {
	case 1: // <100 '*' = 2.5
		weightNum = rawNum / 2.5; // loss of data because of int is kind of intentional. 
		break;
	case 2: // 100-1,000 '*' = 25
		weightNum = rawNum / 25;
		break;
	case 3: // 1,000-10,000 '*' = 250
		weightNum = rawNum / 250;
		break;
	case 0: // >10,000 or something broke '*' = 1/40th of largest number
	// [[fallthrough]]
	default:
		weightNum = rawNum / (bigNum / 40);
		break;

	}
	for (int q = 0; q < weightNum; q++)
	{
		of << "*";
	}
	int widthCount = 42 - weightNum; // crunch-time solution for displaying the numbers at the end of the bar graph line. 
	of << right << setw(widthCount); 
	return;
}

/*
	Name: Generate Report
	Function: Outputs a scaled horizontal bar graph displaying the count of each vowel in the file. 
	The graph is scaled based on the highest count of vowels, of either 100/1000/10000, or based on the largest vowel count.
	warning: The fallback if something goes wrong, or is over 10k vowels, is the 4th option for output, which will not display very prettily.
	but it works.

	Calls: N/A
	Called by: main()
	Pre-Con.: Successful file read, nothing breaks
	Post-Con.: A report is created named "filename-Analysis.txt".
*/
void generateReport(ofstream& of, int vowelArray[], string filePath) {
	int min = 0, max, mid, starOption, arrayMax;

	arrayMax = vowelArray[0];
	for (size_t i = 0; i < ARRAY_MAXSIZE; i++) // get biggest number in array
	{
		if (vowelArray[i] > arrayMax) {
			arrayMax = vowelArray[i];
		}
	}
	if (arrayMax <= 100) { // set graph scale
		max = 100;
		mid = 50;
		starOption = 1;
	}
	else if (arrayMax > 100 && arrayMax <= 1000) {
		max = 1000;
		mid = 500;
		starOption = 2;
	}
	else if (arrayMax > 1000 && arrayMax <= 10000) {
		max = 10000;
		mid = 5000;
		starOption = 3;
	}
	else { // fallback, or over 10k of a single vowel type
		starOption = 0;
		max = arrayMax;
		mid = arrayMax / 2;
	}
	of << "Vowel Count Report generated from: \"" << filePath << "\"\n";
	of << "Vowel" << setw(3) << "|" << setw(30) << "# of Vowels\n";

	of << " 'A'" << setw(5) << "| ";
	weightStars(of, vowelArray[0], starOption, arrayMax);
	of << " (" << vowelArray[0] << ")\n";
	of << " 'E'" << setw(5) << "| ";
	weightStars(of, vowelArray[1], starOption, arrayMax);
	of << " (" << vowelArray[1] << ")\n";
	of << " 'I'" << setw(5) << "| ";
	weightStars(of, vowelArray[2], starOption, arrayMax);
	of << " (" << vowelArray[2] << ")\n";
	of << " 'O'" << setw(5) << "| ";
	weightStars(of, vowelArray[3], starOption, arrayMax);
	of << " (" << vowelArray[3] << ")\n";
	of << " 'U'" << setw(5) << "| ";
	weightStars(of, vowelArray[4], starOption, arrayMax);
	of << " (" << vowelArray[4] << ")\n";
	of << "       " << "|-+--------+---------+---------+---------+\n";
	of << "       " << "  " << min << setw(9) << mid / 2 << setw(10) << mid << setw(10) << mid * 1.5 << setw(10) << max; // bottom grading scales
	of << "\nEach '*' is worth approximately " << static_cast<double>(max) / 40 << " vowels."; // static cast for accuracy
	
	cout << filePath << "'s report generated!\n";
	of.close();
	return;
}