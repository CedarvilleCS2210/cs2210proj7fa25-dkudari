Project #7 ArrayHeap 
CS 2210 – Fall 2025
Doxa Kudari
# Requirements
This assigment required us to implement the main functions of the heap interface using an array-based binary tree as the foundation. We were also supposed to throw the InvalidObjectException when the key provided in the add() method is not comparable with the comparable method. We then tested the function with a loop that entered 10000 numbers and then removed them all. They were to come off in non-increasing order. We were also to make the array heap-resizable.
# Design
I started with the add method, then added the bubble-up and swap methods to support it. I then checked to make sure they were working by creating my own print methods to help visualize the heap. I then worked on the removeRoot method and created the bubble down method to support it. I then tested to ensure it removes the items correctly and maintains the heap property. I then added a helper method to return the element at the root.
# Security Analysis
There are no significant security concerns with this project, as the methods are appropriately assigned as private and public.
# Implementation
The main interesting implementation details of this project were the bubble-up and bubble-down methods. The bubble-up method was harder to implement, and the bubble-down method was almost the reverse of that method. These methods were also the most often ones that would not work correctly and required the most debugging to get working.
# Testing
I tested the project first by creating my own print method to ensure the add method worked correctly. I then tested the removeRoot method by checking the heap after removal. I used my print method to ensure that each print method adhered to the heap property. I then tested the heap by adding and removing 10,000 random elements to ensure it remained heap-ordered.
# AI Use
I did not use AI as instructed in the assignment.
# Summary/Conclusion
The project works per the assignment specifications. There are limitations with large data sets; it also throws the correct errors when necessary.
# Lessons Learned
I learned how to implement a heap data structure using arrays. I also understand how to manage priority queues using heaps efficiently.
# Time Spent
Approximately how much time did you spend on this project?
I spent about 5 hours on this project.


