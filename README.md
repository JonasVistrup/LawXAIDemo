# FærdXel
An expert system for Danish traffic law cases.

## Getting started:
Use Maven to install dependencies, compile and run FærdXel located at src/java/demo.

## Using FærdXel:
When you run FærdXel, you will be presented with the following screen.

![Error Getting Picture](https://github.com/JonasVistrup/LawXAIDemo/tree/master/intro.png "Start Screen")

You can input facts about a traffic case in the "Insert Fakta" box in the top left (Note: Facts must be giving in correct Datalog format). Facts can be removed by clicking.

Pressing the Query button at the bottom, queries the system if anyone in the described case has broken the Danish traffic law. Results are presented on the right side.

Clicking an answer will provide explanations for that answer. There might be more than one explanation for an answer.

The following is an example of how an example could look:

![Error Getting Picture](https://github.com/JonasVistrup/LawXAIDemo/tree/master/running.png "Start Screen")
