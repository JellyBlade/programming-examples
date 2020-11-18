## Word Counter - Tyler Hippard
*Over-engineering at it's finest!*

Requires: n/a

Steps to use the Word Counter
  1. Download the files in this subfolder, and compile with your favourite c++ compiler.
  2. Select the mode of operation
      - [D]efault: Displays the counted words in no particular order.
      - [C]ount: Displays the counted words highest-to-lowest
      - [A]lphabetical: A-Z
      - [F]ind: Prompts for words separated by spaces/commas to look for, and will show # of ocurrences.
      - [I]mport Find: Same as Find, but uses a .csv
  3. Input # of columns to output for the display (or input a list of words/.csv for Find/Import Find). 1-4 is recommended, >4 will sometimes look weird.
  4. Input a text file of words to count.
  
Known Issues:
- Forgot to include newline character as a delimiter for Import Find, so using a .csv with line breaks will smoosh the last word of the longest line with the next line's first word.
  
[Usage Example](https://www.youtube.com/watch?v=WUrYbB6KVh8)
