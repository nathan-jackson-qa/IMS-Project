Coverage: 34%
# IMS System

The Inventory Management System is a command line interface that connects to an SQL server and allows the user to store and manage Customers, Items and Orders.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to run this .jar file you will need to have java installed on your machine (Version 14 was used for development so is recommended), and must be able to run from a command line interface.

```
Java Version 14 can be found [here](https://www.oracle.com/uk/java/technologies/javase/jdk14-archive-downloads)
```

### Installing

A step by step series of examples that tell you how to get a development env running

Step 1: Open Command line interface in the location you want to place the folder

```
*Open Command Line*
cd Documents/Project_Location
```

Step 2: Copy Repository Link and use 'git clone x' in the command line

```
C:\Users\Me\Project_Location> git clone https://github.com/nathan-jackson-qa/IMS-Project/
```

Step 3: To Run the application simply open the project folder and run the IMS-System.jar file

```
C:\Users\Me\Project_Location> cd IMS_Project
C:\Users\Me\Project_Location\IMS_Project> java -jar IMS-System
```

## Running the tests

In order to run the tests for this project, simply open the cmd line in the location that the project is stored, then type mvn test and all unit tests will be carried out

### Unit Tests 

When running the command give in the section above, maven will automatically run all the tests for the Customer, Item and Order objects, as well as the respective Controllers and DAOs for each of those 3. This will test every line of code in each class including outcomes from every conditional throughout

```
C:\Users\Me\Project_Location\IMS_Project> mvn test
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Nathan Jackson** - *Project Leader*

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
