# 2D Prop Hunt

A multiplayer 2D hide-and-seek game built in Java. One player takes the role of a "hider" trying to evade capture, while the other player is the "seeker" trying to find and catch them.

## Prerequisites

- **Java Development Kit (JDK)**: Java 8 or higher
- **Compiler**: `javac` command-line tool
- Two machines or terminal windows to run the server and client(s)

## Project Structure

```
2D-Prop-Hunt/
├── GameServer.java          # Server that handles game communication
├── GameStarter.java         # Client entry point
├── assets/                  # Game assets (images, screens)
├── engine/                  # Core game engine
│   ├── GameFrame.java
│   ├── GameCanvas.java
│   ├── GameObject.java
│   ├── drawing/            # Graphics rendering
│   ├── input/              # Input handling
│   ├── network/            # Networking
│   └── physics/            # Physics simulation
├── game/                    # Game-specific classes
│   ├── Player.java
│   ├── Hider.java
│   ├── Seeker.java
│   ├── HiderScene.java
│   ├── SeekerScene.java
│   ├── Bullet.java
│   ├── Gun.java
│   ├── OtherPlayer.java
│   └── ...
└── math/                    # Math utilities
    └── Vector2.java
```

## Compilation

### Step 1: Navigate to the Project Directory

```bash
cd /path/to/2D-Prop-Hunt
```

### Step 2: Compile All Files

Compile the entire project by running:

```bash
javac -d bin $(find . -name "*.java")
```

This will compile all `.java` files and place the compiled `.class` files in the `bin` directory.

Alternatively, if you don't have a `bin` directory:

```bash
javac $(find . -name "*.java")
```

## Running the Game

### Step 1: Start the Server

In a terminal, run:

```bash
java GameServer
```

The server will output:

```
Server starting
Server waiting
```

The server listens on **port 4952** and waits for two client connections.

### Step 2: Start Client 1 (First Player)

In a second terminal, run:

```bash
java GameStarter
```

A game window will open. Wait for the server to acknowledge the connection.

### Step 3: Start Client 2 (Second Player)

In a third terminal, run:

```bash
java GameStarter
```

A second game window will open. Once both clients are connected, the game will begin!

**Note**: If you only have one machine, you can open two terminal windows and run the client command in each.

## Game Mechanics

### Roles

- **Hider**: One player hides somewhere on the map during a grace period (10 seconds). After the grace period ends, the seeker is released.
- **Seeker**: The other player tries to find and catch the hider within the time limit (120 seconds).

### Win Conditions

- **Hider Wins**: If the hider remains hidden until time runs out.
- **Seeker Wins**: If the seeker finds and catches the hider before time runs out.

### Controls

- **Movement**: Arrow keys or WASD to move the player around the map
- **Shooting**: Left mouse click to shoot bullets (seeker has a gun)

### Post-Game

After a round ends, both players are prompted to play again. If both agree, the roles are switched for the next round.

## Troubleshooting

### "Port already in use" Error

If you get a "port already in use" error, either:

- Wait a few seconds and try again
- Identify and kill the process using port 4952

On macOS/Linux:

```bash
lsof -i :4952
kill -9 <PID>
```

### "Connection refused" Error

- Ensure the server is running before starting the clients
- Check that both clients are connecting to the correct machine (localhost if running locally)

### Compilation Errors

- Ensure all `.java` files are in the correct package structure
- Verify Java version: `java -version`

## Network Configuration

The server runs on:

- **Host**: localhost (127.0.0.1)
- **Port**: 4952

To connect clients on different machines, modify the connection details in the client code (see `GameFrame.java` for network initialization).

## Authors

- Alinus Abuke (230073)
- Neil Aldous Biason (230940)

**Version**: 13 May 2024

## Academic Integrity Statement

This project represents original work by the authors. No code was obtained from other students or unauthorized sources without proper citation.
