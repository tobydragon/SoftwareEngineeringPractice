# SoftwareEngineeringPractice
## grading

To Do | correct
---|---
at least 8 commits|
isEmailValid|
withdraw|
isamountValid|
constructor & withdraw fix|


Use Case Diagram: https://drive.google.com/file/d/17tu-7V9HYjtQig_4SbFuvIeuOGc1R7wC/view?usp=sharing

UML Diagram:  https://drive.google.com/file/d/1-U7TtnJOI9wa1QnNsFQtt6SM-FXgUX0C/view?usp=sharing

Sequence Diagram for basic access functions:  https://drive.google.com/file/d/1cJ8KqJIR0ezbQfFehOFGwUzyCia5UH9L/view?usp=sharing

Questions for Toby:
 - Should email be used as an ID?  It seems to have arbitrary restrictions that seem unecessary, but what the client wants they get.
 - On that note, should createAccount() pass email as an argument as well?
 - I have set up various exceptions to communicate errors to the software on the other side of the interface, is there a cleaner way of doing it or is that OK?
 - Should createAccount() be passed a password so that it can be used to log in a later date?  And should that password be changeable?