Hello! This is my implementation of the game chess. It currently is still a work in progress, however you can still download it to try it out. The current and upcoming features are listed in [Roadmap](#roadmap).

## Requirements
- Java 21
- A graphical environment

## Download and Run
- Go to releases and download the `.jar` file in the assets of the [latest release](https://github.com/spaghettiBolognese213/chess/releases/latest).
- Run `java -jar <filename>.jar`

## Roadmap
Checked items are already implemented. Unchecked items in the first list are planned before the game is considered feature-complete. The second list contains features I may add later, but aren't a priority.

- [x] Basic moves (e.g. pawn/king/knight moves)
- [x] Board and chess piece images
- [x] Check/checkmate detection
- [x] End of match text (e.g. white won)
- [ ] En passant
- [ ] Pawn promotion
- [ ] Castling
- [ ] Game settings selection

**Possible future additions:**
- [ ] Timer
- [ ] Move history
- [ ] Online functionality

## Known Issues
- Pawns can illegally capture a piece when moving two squares forward from their starting position.
- Checkmate detection doesn't account for capturing the attacking piece as an escape.
