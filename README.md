
# 🃏 Flip and Match - Memory Card Game

**Team 11**

Flip and Match is a fun and interactive **memory card game** built using **Java Swing**. The objective is simple: flip over two cards at a time to find all the matching pairs. The game tracks your number of moves and time taken, encouraging both speed and memory!

---

## 🧠 Game Description

Match all pairs of cards by flipping them over two at a time. Each game session:
- Tracks the number of moves
- Tracks the time taken
- Ends when all pairs are matched

---

## ✨ Features

- **Java & Swing**: Built using Java Swing for GUI and event-driven programming
- **OOP Principles**: Encapsulation, object-oriented class structure
- **Randomized Board**: `Collections.shuffle()` used to randomize card placement
- **Game Timer**: Tracks how long the player takes
- **Restart Button**: Reset and play again

---

## 🧰 Technologies Used

| Feature                | Implementation                       |
|------------------------|---------------------------------------|
| GUI Components         | `JFrame`, `JPanel`, `JButton`         |
| Event Handling         | `ActionListener`, `TimerTask`         |
| Card Randomization     | `Collections.shuffle()`               |
| Layout                 | `4x4` grid using `JPanel`             |
| Feedback               | Color change on matched cards         |
| Game Management        | Match checking, move tracking, reset  |

---

## 🧱 Game Architecture

### GUI Components
- **frame**: Main window (`JFrame`)
- **panel**: 4x4 button grid (`JPanel`)
- **topPanel**: Move counter, timer, and restart button
- **buttons[]**: 16 clickable cards

### Core Logic Variables
- `cardValues`: Stores shuffled values (1-8, twice)
- `firstIndex`, `secondIndex`: Track selected cards
- `checking`: Prevents double-clicking during check
- `matchedPairs`: Track successful matches
- `moves`: Count of moves made
- `gameTimer`, `elapsedTime`: Track time taken

---

## 🧩 How it Works

### `MemoryMatchingGame()` Constructor
- Creates the main window
- Initializes panels and game components
- Adds event listeners
- Starts game with `initializeGame()`

### `initializeGame()`
- Resets board, timer, move counter
- Shuffles card values
- Sets up 16 buttons with listeners
- Starts a timer to track play time

### `CardClickListener`
- Responds to card clicks
- Reveals card values
- Matches two selected cards
- Waits 500ms before checking match (via `Timer`)
- Handles move counter and disables matched cards

### `checkMatch()`
- Compares selected cards
- Hides unmatched cards
- Highlights matched ones
- Checks win condition (8 pairs)
- Displays completion message

### `resetGame()`
- Restarts the entire game state

### `main()`
- Entry point that runs the game

---

## 🎮 Getting Started

### Requirements
- Java JDK 8 or higher
- IDE or text editor (e.g., IntelliJ, Eclipse)

### Run the Game
```bash
javac MemoryMatchingGame.java
java MemoryMatchingGame
```

---

## 🏁 Conclusion

Flip and Match is a great demonstration of:
- Java GUI programming
- Object-oriented design
- Interactive and event-driven logic

Perfect for beginners exploring Swing or wanting to build a simple but engaging game!

---

## 🙏 Thank You

Happy flipping! 💡
