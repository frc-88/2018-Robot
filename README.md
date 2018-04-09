# Team 88's 2018 Robot Code
This repository contains the Team 88 2018 codebase.
All of the following code is written in Java to keep it familiar, clean, and consice. Our school offers Java as a class, and as such our
group of programmers and people willing to contribute will continue to grow.

## Contents
This years code is written in command based Java, with all the code living in robot/ directory and is then organized based of purpose
and functionality.

If you need a place to start try here! [frc-88/2018-Robot/robot/Robot](https://github.com/frc-88/2018-Robot/blob/master/src/org/usfirst/frc/team88/robot/Robot.java).

### team88.robot
* **Robot.java** contains the initialization of the robot as well as maintains the structure of the code for during matches.
* **RobotMap.java** all constants that relate to where motors/sensors and other things are plugged into the robot.
* **OI.java** has all the buttons to trigger specific commands using the driver and operator controller

### team88.robot.commands
Contains all the commands the robot calls on to function properly on the field, gladly seperated again into folder based of the device the program revolves around.
* **commands.auto** contains all the autonomous commands used, split again for this years game based of our three starting positions of left, right, and center.

### team88.robot.subsystems
Houses all the different mechanical aspects that our robot uses to call on constants and allows other commands to call for them to use the specific part effectively.

### team88.robot.util
Utility programs used for things such as sensors and controllers.

## Links
Be sure to check our **[wiki page](https://github.com/frc-88/TJ-Squared/wiki)** for guides and tutorials for getting started, or checking out other code we have written. 
