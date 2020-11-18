#include "Counter.h"

Counter::Counter(int maxSet) { // constructor for main intended usage.
	max = maxSet;
}
void Counter::reset() {
	counter = 0;
}
void Counter::incr1(int cycles) {
	counter += 1 * cycles;
}
void Counter::incr10(int cycles) {
	counter += 10 * cycles;
}
void Counter::incr100(int cycles) {
	counter += 100 * cycles;
}
void Counter::incr1000(int cycles) {
	counter += 1000 * cycles;
}
void Counter::forceOverflow() {
	counter += max + 1; // max + 1 so it can be used from a reset/fresh state (0)
}
int Counter::getCount() {
	return counter; // accessor for the private int
}
bool Counter::overflow() {
	if (counter > max) {
		return true;
	}
	else {
		return false;
	}
}