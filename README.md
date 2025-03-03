Garden Puzzle App

Overview

The Garden Puzzle App is a brain teaser puzzle game implemented in Java as part of the Object Oriented Programming course. The objective of the game is to strategically place GardenObjects in a Garden to ensure that a target square receives the correct types of Pollens infused with specific colors.

Features

Implements Lists and ArrayLists for managing objects.
Uses Interfaces, Abstract Classes, Inheritance, and Polymorphism.
Randomly generates goal requirements for each game session.
Allows searching and selecting GardenObjects from the StorageShed.
Supports three types of GardenObjects:
  GardenPlants (Flower, Tree, Bush)
  LightSources (SmallLamp, LargeLamp, Spotlight)
  Statues (randomly placed in the Garden)
Implements an interactive menu for object selection and placement.
Simulates pollen spread and light infusion mechanics.
Determines success or failure based on meeting the target square's requirements.

How to Play

The game initializes an 8x6 Garden with randomly placed Statues.
The target square's pollen requirements are displayed.
The user selects up to 7 GardenObjects from the StorageShed using search filters.
The selected objects are placed strategically within the Garden.
The gardener waits, allowing plants to bloom and lights to activate.
The game evaluates if the target square meets the requirements and displays the result.

Object Mechanics

Pollens spread in specific directions based on plant type:
  Flower: Spreads diagonally (2-4 squares)
  Tree: Spreads horizontally (3-5 squares)
  Bush: Spreads vertically (1-2 squares)

LightSources influence pollen colors:
  SmallLamps: Light rightward (1-3 squares, mostly blue)
  LargeLamps: Light leftward (3-4 squares, mostly green)
  Spotlights: Light downward (4-5 squares, mostly red)

Statues block both light and pollen spread.
