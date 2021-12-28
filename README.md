# Campus Placement Portal


Campus Placement Portal is an android application used for managing registrations for multiple companies during academic year for final year students.

## Modules

- Student module   
- Placement Co-ordinator module

#### Student Module
This service enables the user to register for companies that are made available by the placement co-ordinator

#### Placement Co-ordinator Module
This service enable the user to list new companies for registration by the students. 

## Table of Contents
- Pre-requisites
- Tools Used
- How to build and run the application
- Licenses


## Pre-requisites

#### JAVA
The application currently develped using openjdk version "1.8.0_312". If you already have JDK installed, skip this step.
Check if the right JDK is already available
Run the command java -version. If you have the right version of the JDK installed, you should see something like:
``` 
openjdk version "1.8.0_312"
OpenJDK Runtime Environment (build 1.8.0_312-8u312-b07-0ubuntu1~20.04-b07)
OpenJDK 64-Bit Server VM (build 25.312-b07, mixed mode)
```
Installing JDK on Windows, Mac or Ubuntu [refer this](https://www.liquidweb.com/kb/how-to-install-java-on-ubuntu-windows-and-macos).

#### Android Studio
Download and install Android Studio from [their website](https://developer.android.com/studio).


### Tools Used
- Firebase Database
- Firebase Authentication
- Android Studio IDE
- Android version 9.0 or later
- Android SDK 28-31

### How to build and run the application
##### Clone the project using git.

Run the following command in a terminal.
```
$ git clone https://github.com/RowelLewis/PlacementPortal.git
```
##### Import the project into Android Studio
When Android Studio starts up, it will prompt you to create a new project or import an existing project. Select the option to import an existing project, navigate to the simple-android directory you cloned earlier, and select it.
When building for the first time, gradle will download all dependencies so it'll take a few minutes to complete. Subsequent builds will be faster.
- Create a virtual device
Create an Android Virtual Device (AVD) using the AVD Manager, usually found in Tools -> AVD Manager. (ref)
Select a device and operating system
Note: You will have to download one of the available OS options the first time you create an AVD

- Run the app
Click "Run", either through Run -> Run, or the green play button in the top toolbar.









## License

Apache License Version 2.0, January 2004
