# Mobile Agents

## Contributors

Steele Desmond

Brandon Wade

## Versions (Jar files)

Version 1.0 - Current working version that meets requirements

Version 1.1 - Clones now have there own threads, Program properly terminates
when all the nodes have caught on fire

## How to use this program

### Quickstart

To run the program, run the jar file provided and click the start button. This
will use a simple demo configuration for the map.

### Changing the map configuration

To change the map configuration you must navigate to MainApp.java and change the
file path given to the Configuration class. By default, the configuration files 
are stored in the docs directory. There is a second demo config file provided
in the docs directory.

## Implemented Features

### Fire Spreading

When the start button is pressed, a fire will begin to propogate to nearby nodes
over the graph. When all nodes have been set to the on fire state the simulation
ends. The fire spreading time is set to spread every 10 seconds. This can be 
changed in the Coordinator.

### Agents

An initial agent begins at the Station node and randomly walks to nodes searching
for fire. If fire is found, it will clone itself onto neighboring nodes that are
near-fire, or are in the default state but are near a near-fire node, and that 
don't have an agent already. This effectively creates a ring to "contain" the 
fire. When agents are cloned, they give a Message object to the node they are 
on to send to the Station node. The message contains information about the 
cloned agent like its unique id and the location it was created at.

### Nodes

Nodes provide information for agents to use. Each Node contains its own 
BlockingQueue and waits to receive messages from agents. If a Node receives a 
message it passes it along to a designated node labeled the Station node. The 
station node stores all messages sent out by agents in a table and it is 
displayed in the UI.

The state of node agents is set by the Coordinator and the Map. The state is 
changed as the fire spreads over the nodes. A node's color designates its 
current state. If a node is on fire its thread is terminated.

### Display

*Node Id* - Nodes have a unique id assigned to them

*Blue* - Default state

*Green* - Station Node

*Yellow* - Near-fire node

*Red* - On-fire node

*Light-Blue Ring* - The node contains an agent

*Light-Green Ring* - The node contains an agent and it is near fire

*Table* - Displays the table of agent information stored at the Station node

### Console Output

The program prints messages to console to help understand what is happening. 
There is a countdown for when the fire will start to spread. A 'Message sent' 
message is printed when an Agent sends a message. A 'Message received' message 
is printed when the station receives the message. Messages are given a unique id
for easy tracking.


## Testing and Debugging

* The fire spreads every 10 seconds. This can be changed in the Coordinator class
by changing the fireSpreadTimer integer.

* Agents randomly walk every .75 seconds. This can be changed in the Agents class
in the run() method. 

* The configuration map can be set in MainApp in the path given to the
Configuration class.

* The Map class contains printNodes and printAgents methods that can print to 
console.

* The Node class contains debugging catch cases for if a message is lost on
termination or if it is stuck somewhere and it isn't getting back to the station.
This will print to the console if such a case arises.


## Version 1.0 Bugs

The agent sometimes doesn't clone itself onto blue nodes

Agents don't always send a message when they are cloned

When an agent is on the Station node it isn't properly displayed with a ring. 
This is because the state of the Station node is set to "Station" instead of 
near-fire, standard, or fire, and it isn't accounted for.

The program doesnt terminate properly when all the nodes catch on fire

## Version 1.1 Bugs

The agent sometimes doesn't clone itself onto blue nodes

Agents don't always send a message when they are cloned

When an agent is on the Station node it isn't properly displayed with a ring. 
This is because the state of the Station node is set to "Station" instead of 
near-fire, standard, or fire, and it isn't accounted for.


## Project Design Concerns

The program can handle large graph inputs in the configuration file. However, the
display would need to be resized if the inputs were excessively large. 
Additionally, there are no catch cases if the config input isn't properly
formatted.

The program ends when all nodes have burned. This program assumes the Station and
Fire are starting at opposite ends of the graph. If the Station burns first, 
messages will be passed endlessly.

## Developers' Notes
https://docs.google.com/document/d/1dSJXVVLeDDgp6Dm-JJj-_m42xpr9T2VMNQMips5t9iw/edit?usp=sharing