TwitchPlays
===========

Warning: This project is badly written and contains a lot of bad code. If you want to change something, please do so.
TwitchPlays reads chatinput from Twitch and simulates a keypress if the words defined in the config are typed. To run TwitchPlays, copy the latest verison from the /releases/ folder and run the following command:

	java -classpath TwitchPlays_jar.jar TwitchPlays.Main

The following configuration will be generated:

	#You can generate a oauth here: http://twitchapps.com/tmi/ Make sure that it starts with oauth(oauth:[OAUTH])
	[connection]
	server = irc.twitch.tv
	port = 6667
	user = 
	oauth = 
	channel = 

	[keys]
	up = VK_UP
	down = VK_DOWN
	left = VK_LEFT
	right = VK_RIGHT
	select = VK_X
	start = VK_Y
	a = VK_A
	b = VK_B

	#Actiontime is the time it takes before the program decides what to do while in democracy mode, 2da is the percentage of votes it most get to switch to anarchy, a2d is the opposite
	[mode]
	mode = anarchy
	multiple = true
	vote = true
	actiontime = 20000
	d2a = 50
	a2d = 80

This configuration is the same as TwitchPlaysPokemon. The only thing you need to do is add your username, oauth key and channel. You can generate your oauth key [here](/http://twitchapps.com/tmi/)