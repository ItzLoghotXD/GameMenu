# GameMenu

A simple Minecraft Server Menu plugin like Hypixel's Skyblock's "Skyblock Menu".<br>
Fully customizable, you can customize this plugin by editing `config.yml`

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Commands](#commands)
- [Permissions](#permissions)
- [Contributing](#contributing)
- [License](#license)
- [Thankyou](#thankyoudonations)

## Introduction

`GameMenu` is a lightweight and user-friendly plugin designed for Minecraft servers. It provides a customizable menu interface for server players, allowing easy navigation and access to server features and commands.

## Features

- Easy to install and configure.
- Customizable menu items and commands.
- [PREMIUM] Supports multiple menu pages.
- User-friendly interface.
- Lightweight and efficient.

## Installation

To install the `GameMenu` plugin, follow these steps:

1. **Download the plugin**: Download the latest version of the `GameMenu` plugin from [here](https://hangar.papermc.io/ItzLoghotXD/GameMenu).

2. **Add the plugin to your server**:
    - Place the downloaded JAR file into the `plugins` directory of your Minecraft server.<br>
[Note - This plugin will not work on any spigot server.]

3. **Start the server**: Simply Start your Minecraft server to load the plugin.

4. **Verify installation**: Once the server is started, verify that the plugin is loaded by running the command `/plugins` in the server console or in-game. You should see `GameMenu` listed among the loaded plugins.

## Configuration

The `GameMenu` plugin comes with a default configuration file that can be customized to your liking. To configure the plugin:

1. Navigate to the `plugins/GameMenu` directory.
2. Open the `config.yml` file in a text editor.
3. Modify the configuration options to suit your needs. Below is the default configuration:

```yaml
#
# AUTHOR: Loghot (ItzLoghotXD)
# WIKI: https://github.com/ItzLoghotXD/GameMenu/wiki
# Copyright (c) ItzLoghotXD 2024-2024. All rights reserved.
#--------
# SERVER MENU ITEM
#   The custom item which is used for
#   server menu which is present in
#   player's hotbar
#--------
# SERVER MENU/GUI
#   The default gui which is opens when
#   player execute 'gm menu' command
#--------
# BUILT IN PLUGIN PLACEHOLDERS
#   %player% - Returns player name
#   %online% - Returns number of players online
#   %online_max% - Returns number of max player slots
#        *** USE PLACEHOLDERAPI TO GET MORE ***
#  (https://hangar.papermc.io/HelpChat/PlaceholderAPI)
#--------
# BUILT-IN FEATURES
#   You can use minecraft color codes
#   like '&a' for green you can find all
#   code here https://www.digminecraft.com/lists/color_list_pc.php

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
# | SERVER MENU ITEM SETTINGS                |
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
server_menu_item:
  material: NETHER_STAR   # The material which is use for this custom item. (you can find all item names here
  # https://raw.githubusercontent.com/ItzLoghotXD/GameMenu/main/Materials.txt)
  display_name: '&aServer Menu &7(Right Click)'   # The display name of this item
  lore:   # Lore(s) of this item
     - '&7View all of your Server'
     - '&7progress, including your Skills,'
     - '&7Collections, Recipes, and more!'
     - ''
     - '&eClick to open'

# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
# | SERVER MENU/GUI SETTINGS                 |
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#
menu:
  slots: 54   # Total number of slots in the gui (if you enter any number which is not divisible by 9
  # it will auto adjust it)
  title: '&aServer Menu'   # The title of this menu
  items:   # You can set items here which will be in the menu
    # Some example items
    Report_Island_Name:
      material: ANVIL   # The material which will be displayed in the menu for this item
      slot: 8   # The number of slot in which the item will be
      display_name: '&aReport Island Name'   # The display name of this item
      lore:   # Lore(s) of this item
          - '&7You can report this island'
          - '&7if you think it has an'
          - '&7in appropriate name.'
          - ''
          - '&eClick to Report it!'
      commands:   # The list of commands which will be executed when the player will right/left click on it
          # (It has a built-in command 'CLOSE' which will close the menu and if you put 'CONSOLE: ' in before
          # any command it will execute it as console else player)
          - 'CONSOLE: say replace this with actual command'
          - 'CONSOLE: say or edit config.yml'
    Crafting_Table:
      material: CRAFTING_TABLE
      slot: 31
      display_name: '&aCrafting Table'
      lore:
          - '&7Opens the Crafting Grid.'
          - ''
          - '&eClick to open!'
      commands:
          - 'CONSOLE: say replace this with actual command'
          - 'CONSOLE: say or edit config.yml'
    CLOSE:
      material: BARRIER
      slot: 49
      display_name: '&4Close'
      commands:
          - 'CLOSE'
```

Save the changes and reload the plugin by typing `/gamemenu reload` in the console to apply the new configuration.

## Usage

Once the `GameMenu` plugin is installed and configured, all players can use the menu by executing the command `/gamemenu menu` in-game or by right-clicking when they are holding the custom `server_menu_item`. This will open the server menu with the configured items.

## Commands

The `GameMenu` plugin provides the following commands:
* `/gamemenu` - The default command.
  * aliases - `/gm`
  * usage - `/gamemenu <subcommand>`
* `/gamemenu help` - Shows the usage of every command.
* `gamemenu menu` - Opens the `Main menu`.
* `gamemenu reload` - Reloads the plugin

## Permissions

The `GameMenu` plugin uses the following permissions:

* `gamemenu.command.gm` - Allows a player to use the default command.
* `gamemenu.command.help` - Allows a player to use the help subcommand.
* `gamemenu.command.menu` - Allows a player to open the `Main menu`
* `gamemenu.command.reload` - Allows a player to reload the plugin.

## Contributing
Contributions to the `GameMenu` plugin are welcome! If you would like to contribute, please follow these steps:

1. Fork the repository. 
2. Create a new branch for your feature or bug fix. 
3. Make your changes and commit them with descriptive messages. 
4. Push your changes to your forked repository. 
5. Create a pull request to the main repository.

Please ensure that your code follows the project's coding standards and includes appropriate tests.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.

# Thankyou/Donations

Thank you for using `GameMenu`! If you have any questions or need support, please open an issue on the [GitHub repository](https://github.com/ItzLoghotXD/GameMenu/issues).

I work on this Project with the little amount of free time that I have. Please feel free to donate via Crypto any amount you desire to show your support, and help me stay motivated to keep this project going. Thank You!<br>
* Bitcoin (BTC) Address: bc1qgarxwpp4rn3y5h8hwmhc6a5cpwzqqu3p5pun07
* Litecoin (LTC) Address: LaHYbGK3ysnGQyC8PNmrFkZu3xvDfFQg14
* Ethereum (ETH) Address: 0xE842deFED402339F77637e95cD09d7b3820630ac
* Solana (SOL) Address: HcXwLbebt7mvjc69we8jnQtBGSAcB9U5rnbAMQeRAdSD
* Dogecoin (DOGE) Address: DKgToq8vnBHTgik3iQnm3r3r4mnqvXy4Um

