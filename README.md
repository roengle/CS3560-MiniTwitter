# CS3560-MiniTwitter

### Language Used: Java

##Video

Watch the video describing the program [here](https://www.youtube.com/watch?v=G8Gog94gs2A&feature=youtu.be)!

## Description

A class project for CS3560.01 at Cal Poly Pomona that involves creating a "Mini Twitter." The assignment focuses on 
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

## Pattern Usage

### Composite

The composite pattern is used for both the Users and UserGroups, as they are able to be put into the same hierarchy through this pattern.
They both extend an object called TreeEntry, and UserGroup contains a List of TreeEntry objects.

### Observer

The observer pattern is used between Users, in the case of (1) user followings or (2) someone that your User follows sends a tweet. To follow a User,
another User essentially attaches themselves(since they can be observers as well) onto the target User's observers list. This updates the User to add
to their followers list, and when that target User sends a tweet, it will notify the observer(the User) and it will update their feed accordingly.

### Singleton

The singleton pattern is used for the AdminController, which is directly connected to the AdminControlPanel(which is used for the GUI.) This allows for
any object in this project path to use AdminController, which is extremely helpful when trying to find user and user groups. This also allows for only one
instance of AdminController to be made, which in turn allows AdminControlPanel to definitively know the AdminController object.

### Visitor

The visitor pattern is used to add functionality to the UserGroup. There are four elements:

	* Show User Total
	* Show Group Total
	* Show Message Total
	* Show Positive Message Percentage
	
"Positive Messages" refer to there being positive words in that message. This feature was really open so I added some words such as "nice," "great,"
"wonderful," and "happy."

## Persistence

No persistence is used in this program. Since the main goal is implementing certain design patterns, we aren't worried about storing
any data.

## Demo Pictures

![AdminControlPanel](https://github.com/roengle/CS3560-MiniTwitter/blob/master/assets/demo/1.png)

Admin Control Panel View

![UserView](https://github.com/roengle/CS3560-MiniTwitter/blob/master/assets/demo/2.PNG)

User View

### Built With

	* Spring
	
