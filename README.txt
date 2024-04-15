Hello! My name is Emilian and this is my final iSkillo project for the automation course!


1. In src/main/java/objects/ you will find an object file for every page that was needed for my tests:

1.1 The Header Object contains the click of the Profile, Home, NewPost buttons

1.2 The HomePage Object contains the URL(homepage), its navigation, assert for the URl, click of the LikeElement and a test if it is correctly liked.

1.3 The LoginPage Object contains the URL(loginpage), assert for the URL, filling of username, password, rememberme button check, assert for it and SignIn button click.

1.4 The PostPage Object contains the elements needed for a postCreation, upload of a picture, assert for it, caption and click of the CreatePost button

1.5 The ProfilePage Object contains the URL(profilepage), assert for the URL, assert for the userID.

1.6 The RegistrationPage Object contains the URL(registrationpage), assert for the URL, filling the fields(username, email, password, confirmpassword) and click sign in.



2. In src/test/java/SkilloTesting you will find all my tests that were created and executed:

2.1 I have started with a LoginTest which is a test that provides correct user data, inserts it in the correct fields and executes the login. All actions were monitored by asserts.

2.2 LikePostTest is a test that executes a successful login, finds the like button for a particular post, likes the post and monitors all actions by asserts.

2.3 PostCreationTest is a test that executes a successful login, goes to the new post page, uploads the wanted picture, adds a caption and creates a new post. All actions are monitored by asserts.

2.4 RegistrationTest is a test that should execute a successful registration. I have implemented a feature that generates random credentials needed for a registration.
I have done it with a lot of effort to make sure that every field is filled with correct symbols(for email @, etc...).
However, the iSkillo platform returns "Registration Failed". I have tried to manually retest this error but without any success.
 The strangest thing is that for every field incorrectly filled there is a particular error. Here they are all with ticks, the button is clicked but the iSkillo
returns an error. After discussion with @Vidko, probably there is a bug with the platform which causes this error.