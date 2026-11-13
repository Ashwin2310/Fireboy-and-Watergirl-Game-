# Fireboy & Watergirl: high-school remake (2018)

A two-player co-operative platformer built as the final project for Grade 12 Computer Science (ICS4U) by **Ashwin Thomas and Mary Grace Wong**. It's a fan remake of the *Fireboy and Watergirl* game from Cool Math Games. Fireboy and Watergirl each have to reach their own door, avoiding the pools of the opposite element and collecting gems on the way.

> **Archived school project.** It was written as a Java **Applet**, a technology that has since been removed from Java and browsers, so it doesn't run on modern Java without an old JDK and an applet viewer. It's kept here as a record of early work. The game concept and character art belong to the original game's creators.

## What it covers

- **Two players on one keyboard:** arrow keys for one character, `W`/`A`/`D` for the other, each with its own movement, jumping and gravity.
- **Hazards and collision detection:** fire, water and acid pools, spikes, moving platforms, buttons, gates and teleporters.
- **Collectibles, a countdown timer** running on its own thread, checkpoints, and win/lose screens.
- **An in-game menu** (restart, rules, obstacle info, quit), a loading screen, and background music.

## Files

| File | Purpose |
|---|---|
| `FBWGCPT.java` | Main game: input, physics, collisions, rendering and the timer thread |
| `FireboyWatergirl.java` | Title screen |
| `LoadingScreen.java` | Animated loading screen |
| `CheckpointScreen.java` | Checkpoint / level-complete screen |
| `*.html` | Applet launch pages |
| `*.png`, `*.jpg`, `*.gif`, `*.wav` | Sprites, tiles and audio |

## Running it (legacy)

You'd need **JDK 8**, which still includes `appletviewer`:

```bash
javac *.java
appletviewer FireboyWatergirl.html
```
