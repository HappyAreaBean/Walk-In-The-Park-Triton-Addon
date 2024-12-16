# WalkInTheParkTritonAddon

## Overview

The `WalkInTheParkTritonAddon` is an addon designed to integrate the **[Walk in the Park](https://www.spigotmc.org/resources/87226/)** (WITP) parkour plugin with the
**[Triton](https://www.spigotmc.org/resources/30331/)** language management system. It ensures that when a player joins or leaves a parkour game, their language
preferences are synchronized between WITP and Triton. The addon automatically adjusts the player's language locale based
on their current settings in either system.

## Features

- Syncs player's language locale between **WITP** and **Triton**.
- Ensures that when a player joins a parkour game, their language in WITP is set to match their Triton language.
- When the player leaves the parkour game, their Triton language is updated based on their WITP settings.
- Asynchronous task handling to prevent blocking the main thread during language sync.

## Dependencies

- **Triton** - For managing player language preferences.
- **Walk in the Park (WITP)** - For handling parkour-related events and player locales.

## Setup

### Installation

1. Download the `WalkInTheParkTritonAddon` plugin jar file.
2. Place the jar file into your server's `plugins/` folder.
3. Ensure that you have the following dependencies installed and correctly configured:
    - **Triton Plugin** (for language management).
    - **Walk in the Park (WITP) Plugin** (for parkour functionality).
4. Restart your server to load the plugin.

### Configuration

The plugin doesn't require any additional configuration files. However, make sure the following are set up properly in
your server:

- **Triton Language Settings**: The player language preferences in Triton should be correctly configured. This addon
  will synchronize these settings with WITP.
- **Walk in the Park Locale Settings**: Ensure that the locales available in WITP match the language codes used by
  Triton.

## How It Works

The `WITPListener` listens for the following events:

### 1. **ParkourJoinEvent**

When a player joins a parkour game, the plugin:

- Retrieves the player's language from **Triton**.
- Checks if the player's locale is already set in WITP.
- If the player's locale in WITP differs from Triton, the plugin synchronizes the language by updating the WITP locale.
- A delayed task is scheduled to ensure that the locale update doesn't block the main thread.

### 2. **ParkourLeaveEvent**

When a player leaves a parkour game, the plugin:

- Retrieve the player's current locale in WITP.
- Check if the locale is different from Triton.
- If the locale is different, the plugin updates the player's language in Triton to match their WITP locale.

## Code Breakdown

### Key Methods

- **`onParkourJoin`**:
    - Retrieves the player's language from Triton.
    - Compare the language with the WITP locale.
    - If different, update the WITP locale asynchronously.

- **`onParkourLeave`**:
    - Retrieve the player's locale from WITP.
    - Compare the locale with the Triton language.
    - If different, update the Triton language.

### Helper Classes

- **`LocaleUtils`**: Utility class for handling locale checks and providing available locale lists for both WITP and
  Triton.
- **`PlayerUtils`**: Utility class for setting the player's language in both WITP and Triton.

## Commands

Currently, there are no specific commands associated with this addon. The plugin operates automatically based on the
events from the **Walk in the Park** plugin.

## Troubleshooting

- **Locales not syncing**: Ensure that the player's language is correctly set in both Triton and WITP.
- **Player language does not change**: Make sure that the locale names in both Triton and WITP match, or the plugin will
  not sync the languages.
- **Errors in console**: Check the plugin's logs for any specific errors related to locale handling or event listening.
  Ensure all dependencies are correctly installed.

## Contributing

If you would like to contribute to this project, feel free to fork the repository, make changes, and submit a pull
request.

## License

This project is licensed under the MIT License.