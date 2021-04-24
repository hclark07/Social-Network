# Frankly (Social media website)

## Project Description

In this web application every Revature employee can connect to other employees, whether it be an associate, a trainer, or contracted employees. Each person has their own account, that comes with a unique username, Revature employee information. Within this network employees can access locations and information to help them with many different possibilities. Also it is being created to ease the transition as a Revature employee, whether it be as a trainer/associate, contracted employee/contractor, or staff member.

## Technologies Used

* Spring
* Java
* SQL
* Hibernate
* HTML
* CSS
* JavaScript
* Log4J
* JUnit
* Angular
* EC2
* Jenkins
* S3

## Features

List of features ready and TODOs for future development
* As a user, we are able to create new accounts and login to our social media account.
* As we are friends with all users in our app we are able to view their posts upon logging in and we are able to create new posts and attach an image.
* We are able to view our own profile with post we created while editing our profile information, and we are able to view our own friends profiles with their post as well.
* We are able to search for other user through a search bar. 
* There is a live chat where you can talk to other users logged in.
* We are able to logout.

To-do list:
* Make improvements to the speed of our application as it makes to many requests/checks over http requests slowing down our application visibily. 
* We have an issue with editing our profile information as it is not being updated correctly on the frontend side.
* Reorgainize the layout of both the main page and profile page to be more visibily appealing. 
* Add our logo to the frontpage.

## Getting Started
   
* First we need to git clone our project
> https://github.com/hclark07/Social-Network.git

* Next we need to install both IntelliJ and Visual Studio Code if you have not done so yet.
> https://www.jetbrains.com/idea/download/#section=windows
> https://code.visualstudio.com/download

* Open the frontend folder in Visual Studio Code and open console with ctrl + ` 
* Install Angular with npm install -g @angular/cli
* Download the modules using the command npm install.

* For the backend open IntelliJ and under file, new, import module from existing source, import the folder project-two with gradle. 
* Configure a new tomcat server using Apache Tomcat/9.0.45
* Deployment Directory set to our webapp folder in our proect
* Context path /social
* Server Port 9005 as that is what the project is already setup for. 
* Your backend should be ready.
> ![tomcat](https://user-images.githubusercontent.com/69532931/115968051-a73c9500-a4ea-11eb-8cc4-f97bdf99655d.png)


* To start the application
* In the command line in VS code type in ng serve -o to open the front page in a browser
* In the backend simply run the tomcat server

## Usage

* Front page of our application and also the login page
* > ![login](https://user-images.githubusercontent.com/69532931/115947441-876e8800-a47c-11eb-87fb-b709c099aa72.png)

* We can create a new user by clicking register link button.
> ![newUSer](https://user-images.githubusercontent.com/69532931/115947462-9ce3b200-a47c-11eb-983c-ebde091578d0.png)

* Once you login it navigates a user to the main page. On top of the main page you have the nav bar while on the body you have a global feed of all users posts. 
> ![feed](https://user-images.githubusercontent.com/69532931/115947473-b1c04580-a47c-11eb-9f8b-0065d44d0b4c.png)

* On the main page we are able to create a new post with images.
> ![create_post](https://user-images.githubusercontent.com/69532931/115947491-d3213180-a47c-11eb-813a-67114bcc8528.png)
> ![image_to_post](https://user-images.githubusercontent.com/69532931/115947501-dc120300-a47c-11eb-915d-9303626870e1.png)
> ![new_post](https://user-images.githubusercontent.com/69532931/115947512-ee8c3c80-a47c-11eb-9064-290c8aba6da9.png)

* You can also navigate to a profile page through the nav bar. On the profile page you have a left container containing the user's information, Current profile view posts and a friends list on the right
> ![myprofile](https://user-images.githubusercontent.com/69532931/115947570-65c1d080-a47d-11eb-8b56-26e858818a97.png)

* We can edit a profile information by clicking edit profile in the left container. 
> ![edit_profile_info](https://user-images.githubusercontent.com/69532931/115947582-812cdb80-a47d-11eb-9de9-3e8d8f61555d.png)
> ![edit_profile_yoda](https://user-images.githubusercontent.com/69532931/115947592-9144bb00-a47d-11eb-9309-532de8da2f8f.png)

* Hit the submit button to be routed back to the profile page. You will notice the information has changed in the left container. 
> ![profile_change](https://user-images.githubusercontent.com/69532931/115947612-b20d1080-a47d-11eb-93d0-e23cdafa68b7.png)

* You can change to a different profile view by clicking the view profile link on the right in the friends list.
> ![friend1](https://user-images.githubusercontent.com/69532931/115947636-d832b080-a47d-11eb-991a-a6a6358c67a5.png)
> ![friend2](https://user-images.githubusercontent.com/69532931/115947637-da950a80-a47d-11eb-8826-147a372ba03a.png)

* You can also search up a friends profile in the nav bar using the search bar. 
> ![search_friend](https://user-images.githubusercontent.com/69532931/115947666-07492200-a47e-11eb-848d-7da4f61347b2.png)
> ![search_friend_page](https://user-images.githubusercontent.com/69532931/115947673-0f08c680-a47e-11eb-9751-39fba60e45da.png)

* You can also navigate to a live chat room with other users currently logged in
* Below an account named Mikey will initiate a conversation with the user we just created.
> ![convo1](https://user-images.githubusercontent.com/69532931/115968434-dc49e700-a4ec-11eb-8afe-781364bdd9cd.png)
> ![convo2](https://user-images.githubusercontent.com/69532931/115968437-e10e9b00-a4ec-11eb-8d9a-665570bcfb60.png)
> ![convo3](https://user-images.githubusercontent.com/69532931/115968441-e370f500-a4ec-11eb-9cff-0f4e1ce7dca0.png)
> ![convo4](https://user-images.githubusercontent.com/69532931/115968444-e5d34f00-a4ec-11eb-80bb-cb727b66255a.png)
> ![convo5](https://user-images.githubusercontent.com/69532931/115968447-e79d1280-a4ec-11eb-9d03-f71ae171b342.png)
> ![convo6](https://user-images.githubusercontent.com/69532931/115968448-e8ce3f80-a4ec-11eb-93f4-4f3c1ce705a8.png)


* We can logout by clicking the logout button in the nav bar. Upon logging out if a user forgets their password. Red text will appear letting users know their login information is incorrect. 
> ![warning](https://user-images.githubusercontent.com/69532931/115947765-cd2c5000-a47e-11eb-8a0b-c1d22da352bb.png)

* If users forget their password, they can initiate a password recovery that will send an email with the users password in the email.
> ![password_recovery](https://user-images.githubusercontent.com/69532931/115947795-ffd64880-a47e-11eb-87a2-7b8fb217e3b3.png)
> ![email_recovery](https://user-images.githubusercontent.com/69532931/115947797-05339300-a47f-11eb-937e-7b8552340427.png)
> ![password_recovered](https://user-images.githubusercontent.com/69532931/115947798-08c71a00-a47f-11eb-8c3c-7d09005754b2.png)

## Contributors

* Corey Schink
* Michael DeGennaro
* Erik Williams


