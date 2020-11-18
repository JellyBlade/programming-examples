#pragma once

class Counter {
	Counter();

private:
	int counter = 0;;
	int max = 0;
public:
	Counter(int);
	void reset();
	void incr1(int);
	void incr10(int);
	void incr100(int);
	void incr1000(int);
	void forceOverflow(); // force an overflow state
	int getCount(); // accessor for int counter
	bool overflow(); // check for overflow
};