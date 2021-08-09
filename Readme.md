BARTENDER
===

Bartender asks what you want to drink : beer, juice or nothing.

Depending on your choice :
- nothing : ends the program
- juice : "Bartender gives you a nice juice, refreshing !" ==> loops back to first question
- beer : triggers a new event

Beer event : The bartender asks for your age ==> enter your birthdate
- If you are 18 : "Bartender gives you a beer, drink in moderation !" ==> loops back to first question
- If you are not 18 : "You can't have a beer kiddo"  ==> loops back to first question


REFACTORING
===
Each step should be done before starting the next one :
- correct the bugs
- test existing code (could require some refactoring beforehand)
- add new drink : soda
- Bartender wishes you a happy birthday
- add 4 types of juices : Papaya, Pomegranate, Banana, Cucumber
- when asking for non-existing juice, bartender should ask again 2 times, then go back to the beginning
- add new drink : red wine (18 years old minimum restriction applies)
- add new drink : Virgin bloody marry (14 years old minimum restriction applies)
- add a wallet with random initial amount for user. You can order until you are out of money (bartender tells you're short on cash). Prices are following :
  - juices are 2€ except for Cucumber that is 1.82€
  - beer is at 4.5€
  - soda is at 3.42€  
  - red wine is at 3.13€
  - Virgin bloody marry costs 6.87€
- all prices are now considered as "without tax", add a 18.82% VAT tax
- Bartender remembers you ! You don't have to give your ID everytime birthdate
- Bartender refuses to serve alcohol after you got more than 10 alcoholic drinks
- Bartender wishes you a happy birthday, and the first drink is free if non-alcoholic
- Bartender kicks you out if you keep asking for alcohol after he refuses to serve you (ask again 3 times and you are out)
- VAT tax varies with product : juices are at 12% (exception for banana et 3%), soda is at 5%, alcoholic beverages are at 20%