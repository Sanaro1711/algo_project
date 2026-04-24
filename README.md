
# Group 7 Algorithms Project

This GitHub repository contains the code for Group 7's Algorithms Project.  
The project is based on analysing energy consumption of an algorithm to its input size.  

This project relies on the jRAPL library. Its repository can be found here: https://github.com/aservet1/jRAPL

This project consists of two parts: the Java program, and the Python script.  
The Java program generates data, and the Python script generates the graphs & correlation coefficients.  

This repository **includes the pre-made data and graphs used in the report**.   
Hence, you can ignore the Java program, and simply run the Python script to verify the results.  
The Python script, called `plotter.py`, can be found in the `/SortingAlgorithms/src/algo_project/` directory.  
Alternatively, you can see the graphs & correlation coefficients CSV used in the report in the `/SortingAlgorithms/src/algo_project/Graphs/` directory.


On the other hand, you can run the Java program again, to generate a new dataset to be graphed.  
The Java program is made up of 2 Java classes: `DataGenerator.java` and `SortingAlgorithms.java`.  
`DataGenerator.java` is the main class, responsible for generating the energy consumption data for each of the CSVs.      
**Your machine needs to meet the system requirements below before it can run the code in `DataGenerator.java`**. 

## System Requirements (Java)
### Hardware 

**Your machine needs to be compatible with jRAPL**.   
This means its CPU needs to be equipped with Intel's RAPL feature. This feature is available on most Intel CPUs released in the 2010's.   
However, 13th generation and newer Intel processors are not supported.  

Apple Silicon (M chips) and ARM processors are not supported.  
Some AMD processors may be compatible, however it is unlikely, and hence it should be assumed that they are not compatible.   

### Environment

**Linux is required to run the code in `DataGenerator.java`**.  
You will not be able to run this code on Windows, or MacOS.  
It is recommended to use Ubuntu, as that is the OS that was used originally (specifically, Ubuntu 24.04).

### Programs  

You will need to install Java on your machine **through the terminal**.  
On Ubuntu, this can be done by installing any of the packages given by the `java` command:

```
user@machine:~$ java
Command 'java' not found, but can be installed with:
sudo apt install default-jre              # version 2:1.17-75, or
sudo apt install openjdk-17-jre-headless  # version 17.0.18+8-1~24.04.1
...
```

You can now use the `java` command for the Java files in this repository.  
However, IntelliJ was used when developing this project originally. Hence, if you want to replicate the results as closely as possible, you will need to **install IntelliJ**.  
Originally, this was done by downloading the program from the website as an archive, and unarchiving it.  

You will also need Python, for running the `plotter.py` script that creates graphs from data.

### Enabling root access for IntelliJ

The main library used in this project, jRAPL, requires root access.  
Therefore, if you want to run the code in IntelliJ, you will need to run it as root.  

If you downloaded IntelliJ as an archive (as was done originally), you can enable root access by running:  
`sudo [IntelliJ download location]/bin/idea`

There may be better ways of doing this, however I am unaware of them, and hence they weren't used originally.

### Checking jRAPL compatibility

You should now clone this repository onto your machine in the root version of IntelliJ, and check if it's compatible with jRAPL.  
You can do this by running `sudo java -cp ./jRAPL-1.0.jar jRAPL.Demo ArchSpec`.  
This will print out something similar to this:
```
MICRO_ARCHITECTURE: 8e  
MICRO_ARCHITECTURE_NAME: KABYLAKE
```  
If `MICRO_ARCHITECTURE_NAME` says something like `UNDEFINED_ARCHITECTURE`, jRAPL is not compatible with your machine.



## Running the code

In order to run the program, you need to first add `jRAPL-1.0.jar` to the project's libraries.  
To do this in IntelliJ, go into "File" -> "Project Structure" -> "Libraries", click the + icon, and add the .jar file in this repo.

After that, you can simply run the `DataGenerator.java` file in IntelliJ.  
**IMPORTANT: This WILL take a long time to run.** It may take as long as several hours for the program to be finished.  
This is mainly because of Bubble Sort taking a really long time to sort with large files.

After the program has finished running, you can run the Python script `plotter.py`, and see the new results.  
The script will put the graphs in the same directory that it's located in, `/SortingAlgorithms/src/algo_project/`. Hence, you can differentiate & compare the 2 sets of results.


