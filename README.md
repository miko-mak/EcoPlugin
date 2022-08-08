# EcoPlugin
Minecraft Java plugin, created for the EcoCraft event.

## Introduction
This plugin is an addition that brings some varienty to regular survival gameplay with friends. The main purpose of this plugin is collaboration between all players. They must know that they have an impact on the future of enviroment. Their task is to create as little as possible Pollution points. 

Creating a huge amount of pollution by players will have negative results - disasters. 
You should know that you are not the only one on this world! Take care of your world and other entities.

## Installation
No other plugins are required.
Plugin supports only 1.18+ versions (Spigot/PaperSpigot).

1. Download the jar of the plugin from **Releases**.
2. Put this plugin into your **plugins** folder on your Minecraft server.
2. Restart the server.
3. Enjoy! 

## Pollution
Pollution points will increase when:
- players will not use filters for furnaces (hopper above furnace)
- players will kill a lot of animals
- players will throw items from inventory
- players will use a lot of campfires
- players will destroy beehives and bee nests
- players will destroy daylight detectors (solar panels)
- players will destroy saplings
- players will create explosions

Pollution points also can be reduced by:
- planting trees
- placing beehives
- picking up own rubish from the ground
- using filters for furnaces
- placing solar panels

## Disasters
When pollution will reach:
- **50%-64%**: players can get more hunger than before
- **65%-74%**: players can get more hunger than before and plants are not growing anymore
- **75%-99%**: players can get more hunger than before, plants are not growing anymore, all animals have died, players are forced to wear a mask due to air pollution that is poisoning players without a mask (any kind of glass, that is on player's head after using /put-mask comamnd)

When pollution will reach 100% players will be killed by enviroment and they will be not able to play on this world. Also after rejoin.

## Commands
- /pollution (Check the current size of pollution)
- /put-mask (Protect yourself from the polluted air by putting the mask)
- /disasters (Display the list of current disasters)

## Special thanks
Special thanks to [@Kamilkime](https://github.com/Kamilkime). Thank you for your tips that helped me to improve my code quality! 
