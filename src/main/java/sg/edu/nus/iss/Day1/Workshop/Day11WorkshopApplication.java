package sg.edu.nus.iss.Day1.Workshop;


import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Day11WorkshopApplication {

	//workshop1: Configure the port via command line & environmental variable
	//Application start -> Check the port for the following:
	//#1: Check if --port <port number> is set -> set -> use provided port number
	//#2: Otherwise; check if env variable PORT is set -> set -> use provided port number
	//#3: Else; set default to port 3000

	//declare variables

	//create logger object using SLF4J method
	//arg 'Day11_Workshop.class': Logger created for this class
	//cosntant that belongs to the class and can be accessed anywhere within this class
	private static final Logger logger = LoggerFactory.getLogger(Day11WorkshopApplication.class); 

	//create portNumber - placeholder to store and use later; can update using APP_PORT, or change to default port
	//private = only accessible within this class
	//static = only belong this class and no other individual instance
	private static String portNumber = null; 

	//create default port
	private static final String DEFAULT_PORT = "8080"; 

	public static void main(String[] args) {
		//for each loop - for each value in the 'args' array
		//for loop iterates over each value in args and assigns each value as argVal for each iteration
		//args is the array which stores the command line arguments passed by the user

		//TLDR: for each loop: Loop through each command line argument and check if the the log contains --port; then finds the value of port number from the debug log
		for (String argVal : args){
			//provide debugging info for the code
			logger.debug("argVal: " + argVal);
			if (argVal.contains("--port=")){
				//gets substring which starts from index position 7 (i.e. 4th index before end), end of string position
				portNumber = argVal.substring(argVal.length() - 4, argVal.length());
				logger.debug("portNumber: " + portNumber);
			}
		}

		//now that we have found the port number within the debug log, we will check for portnumber
		if (portNumber == null){
			portNumber = System.getenv("APP_PORT"); 
			logger.debug("System environment portNUMBER: " + portNumber);
		}

		if (portNumber == null){
			portNumber = DEFAULT_PORT; 
		}

		//instantiate 
		SpringApplication app = new SpringApplication(Day11WorkshopApplication.class); 
		//set default properties with singletonmap as it has only one key-valye pair. 
		app.setDefaultProperties(Collections.singletonMap("server.port", portNumber));
		//run app with the argument keyed in the command line
		app.run(args); 
	}

}
