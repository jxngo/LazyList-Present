# LazyList-Present
## Problem 1: The Birthday Presents Party
### How to Run
To change the input, locate the main and update the number of presents. 
javac sortPresents.java
java sortPreents.java

### Approach, Reflection and Experimental Evaluation
Unfortunately I have ran out of time to execute my approach, therefore the program will not compute desired results.

I first used references from the textbook to create my LazyList class for special chain of presents and my Node class to use LazyList. 
Next according to the problem, I implemented an arraylist from 0 to N or 500,000 and shuffle it to fit the criteria of random presents in a bag. 
I then pass list and new initialized lazylist into my Servant thread constructor to utilize LazyList functions such as add, delete, and contains which the servants will use. 
I then have a rng from 1-3 to randomly pick which function the servant will use and then proceed the chosen number. 
My approach is to use AtomicIntegers and AtomicBoolean to keep track of counters for the random present list and flag to end the threads once the counter for removing reaches to 500,000 or N if input. 
The idea seems doable, however there were issues required to be debug which unfortunately I have ran out of time. I regretably started this last minute and ended with an incomplete program. This assignment seems very fun and I will continue to finish after deadline.

There are no experimental evaluation & efficiency since program is incomplete. 

## Problem 2: Atmospheric Temperature Reading Module
N/A
