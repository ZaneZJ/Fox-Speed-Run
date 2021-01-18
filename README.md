# Fox Game

Welcome to the Fox Speed run! A simple Java game created using Swing.

## How to run

1. Install dependencies and build the jar by running `mvn clean install`
1. Execute the jar by running `java -jar ./target/speed-run-1.0.jar`

## High scores

High scores are stored in a local SQLite database, which is automatically created in the working directory
and called [fox.db](fox.db). To delete high scores, just delete this file.

## Demo

![Demo](https://media4.giphy.com/media/kvUO1rfwxWLKYDFILG/giphy.gif)

## Features
- The game becomes faster every 300 points
- Obstacle type and distance between them is generated randomly
- Local high scores using SQLite and sorted with the highest score on top
- Jump with SPACEBAR, and hold it to increase the jump height
- Fox tail can touch obstacles
- Animated fox which becomes sad when you lose
