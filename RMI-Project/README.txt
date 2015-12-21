#All the files that constitute the data server are put into the 'DB_SERVER' folder.
#All the files that constitute the front end servers are put into the 'FE_SERVER1' 'FE_SERVER2' 'FE_SERVER3' folder.
#All the files that constitute the client are put into the 'CLIENT' folder.
#Each of these two folders has a Makefile, which when executed will compile all the Java source files and generate the corresponding .class files or bytecode.
#The SERVER folder has a 'startServer.sh' shell script file which when executed will start the server.
#Start DB server first then FE_SERVER1 2nd. Then start other two FE servers. Start the CLIENT at the last.
#To kill the server we need to execute the shell script 'stopServer.sh'.
#To start the client we have 'startClient.sh' shell file in CLIENT folder. Run this script to start the client.
#We can switch off the client programmatically by selecting option 8 in the console.




#If the server is being run on a different machine then we need to specify the server host to the client.
#This is done by editting the startClient.sh file and specifying the server host IP inplace of localhost sent as a program runtime command line argument.


#Running Shell script:

sh startClient.sh
sh startServer.sh
sh stopServer.sh

