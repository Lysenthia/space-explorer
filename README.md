# Space Explorers
A Java based space survival game
#### How to play (GUI)
1. Start the game by calling `java -jar game_gui.jar` in the terminal
2. Select how many days the game should played over
3. Name you ship
4. Customise your crew members
5. Select how many crew members you would like to have (These will be selected from left to right)
6. The game now begins in orbit around the sun. You may now:
   - Travel to a new planet
   - View the status of your ship and crew
   - Travel to the outpost to buy items
   - Transition to a new day
   - Quit the game
7. If you are orbiting a planet, then you may also search it for items, money, and ship parts
8. The game has 5 ends:
   - All needed parts are found, and the player wins
   - The final day is reached, and the player loses
   - The entire crew is killed, and the player loses
   - All but one crew member dies, and the player loses
   - The ships shields are drained, and the player loses
   - The player quits the game, and thus the player loses
   
#### How to play (CLI)
1. Start the game by calling `java -jar game_cli.jar` in the terminal
2. Enter the number of days to play for
3. Select the crew members that you want by entering the corresponding numbers in a single line

#### Requirements
* [SnakeYAML](https://bitbucket.org/asomov/snakeyaml)  (Bundled)
   * [SnakeYAML License](./resources/LICENSE.txt)