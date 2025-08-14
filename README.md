# Retro Run

A Java Swing-based platformer game where players navigate through challenging obstacles, collect keys, and use powerups to reach the end.

## How to Run

### With Maven 
```bash
mvn compile exec:java
```

### With Java directly
```bash
# Compile
javac -d target/classes -cp src/main/resources src/main/java/*.java

# Run
java -cp target/classes:src/main/resources Main
```

## Controls

- Arrow keys: Move
- Spacebar: Jump
- Number keys 1-4: Use items

## Goal

Unlock the last door each the top-right corner to win!