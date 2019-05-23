# Space Explorers
A Java based space survival game

#### How to play (GUI)
1. Ensure Java 11 is installed on the machine
2. Open zip folder in terminal window
3. Start the game by calling `java -jar game_gui.jar` in the terminal
   - The folder from which this file is called must also contain the resources folder provided
4. Select how many days the game should played over
5. Name you ship
6. Customise your crew members
7. Select how many crew members you would like to have (These will be selected from left to right)
8. The game now begins in orbit around the sun. You may now:
   - Travel to a new planet
   - View the status of your ship and crew
   - Travel to the outpost to buy items
   - Transition to a new day
   - Quit the game
9. If you are orbiting a planet, then you may also search it for items, money, and ship parts
10. The game has 5 ends:
   - All needed parts are found, and the player wins
   - The final day is reached, and the player loses
   - The entire crew is killed, and the player loses
   - All but one crew member dies, and the player loses
   - The ships shields are drained, and the player loses
   - The player quits the game, and thus the player loses

##### Customising the game
If you wish to, you may add new images to the resource folder to use in-game. Note: a file with a filename of default is required in both folders
  - To add a new crew member image, place the image in resources/crew-img
  - To add a new planet image, place the image in resources/planet-img
  
You may also edit the planets and consumables used in the game
  - The planets are located in resources/game-data/planets.yaml
  - The consumables are located in resources/game-data/consumables.yaml

#### How to play (CLI)
1. Ensure Java 11 is installed on the machine
2. Open zip folder in terminal window
3. Start the game by calling `java -jar game_cli.jar` in the terminal
4. Enter the number of days to play for
5. Select the crew members that you want by entering the corresponding numbers in a single line
6. The game now begins in orbit around the sun. Entering the corresponding numbers in a single line, you may now:
  - Go to a new planet
  - Check on your crew and access your inventory
  - Go to Outpost
  - Transition to a new day
  - Quit game
7.
  - If you are orbiting a planet, you may search it for items, money and ship parts
8. The game has 5 endings:
   - All needed parts are found, and the player wins
   - The final day is reached, and the player loses
   - The entire crew is killed, and the player loses
   - All but one crew member dies, and the player loses
   - The ships shields are drained, and the player loses
   - The player quits the game, and thus the player loses

#### Requirements
* [SnakeYAML](https://bitbucket.org/asomov/snakeyaml)  (Bundled)
   * [SnakeYAML License](./resources/LICENSE.txt)

#### Opening Space Explorers in Eclipse
1. 


   
#### Building the game
The game can be built using the makefile
- In order to build the command line version of the game, run `make cli`
- In order to build the GUI version of the game, run `make gui`
- You can see the possible usage for the makefile by running `make help`

   
#### Image sources
* Mercury : https://commons.wikimedia.org/wiki/File:Mercury_in_color_-_Prockter07-edit1.jpg
* Venus: https://commons.wikimedia.org/wiki/File:Venus-real_color.jpg
* Earth: https://commons.wikimedia.org/wiki/File:The_Earth_seen_from_Apollo_17.jpg
* Mars: https://en.wikipedia.org/wiki/Mars#/media/File:OSIRIS_Mars_true_color.jpg
* Jupiter: https://en.wikipedia.org/wiki/Jupiter#/media/File:Jupiter_and_its_shrunken_Great_Red_Spot.jpg
* Saturn: https://en.wikipedia.org/wiki/Saturn#/media/File:Saturn_during_Equinox.jpg
* Uranus: https://en.wikipedia.org/wiki/Uranus#/media/File:Uranus2.jpg
* Neptune: https://en.wikipedia.org/wiki/Neptune#/media/File:Neptune_Full.jpg
