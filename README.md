# CS3560-MiniTwitter
### Language Used: Java

## Description
A class project for CS3560 at Cal Poly Pomona that involves creating a "Mini Twitter." The assignment focuses on 
implementing four design patterns:

	* Composite
	* Observer
	* Singleton
	* Visitor
	
The program has two main GUI elements: (1) the Admin panel, and (2) the user view panel. The admin panel allows for 
User and UserGroups to be created, which are displayed in a tree view. Users and UserGroups can be selected in this view,
and this data is displayed for the respective item selected. There is a button to open a user view which opens a window showing
the respective User's panel. In this panel, it shows who they are following, the tweets from their followings, and buttons to 
"Tweet" a message as well as following a new user.

### Persistence
No persistence is used in this program. Since the main goal is implementing certain design patterns, we aren't worried about storing
any data.

### Built With

	* Spring
	
