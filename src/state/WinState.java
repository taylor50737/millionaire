package state;

import millionaire.Game;

public class WinState extends State {
    public WinState(Game g) {
        super(g);
    }

    @Override
    public void display() {
        Game g = getGame();
        System.out.println(
                """
                            /$$           /$$$$$$                                                     /$$               /$$             /$$     /$$                                        /$$    \s
                          /$$$$$$        /$$__  $$                                                   | $$              | $$            | $$    |__/                                      /$$$$$$  \s
                         /$$__  $$      | $$  \\__/  /$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$  /$$$$$$   /$$   /$$| $$  /$$$$$$  /$$$$$$   /$$  /$$$$$$  /$$$$$$$   /$$$$$$$       /$$__  $$ \s
                        | $$  \\__/      | $$       /$$__  $$| $$__  $$ /$$__  $$ /$$__  $$|____  $$|_  $$_/  | $$  | $$| $$ |____  $$|_  $$_/  | $$ /$$__  $$| $$__  $$ /$$_____/      | $$  \\__/ \s
                        |  $$$$$$       | $$      | $$  \\ $$| $$  \\ $$| $$  \\ $$| $$  \\__/ /$$$$$$$  | $$    | $$  | $$| $$  /$$$$$$$  | $$    | $$| $$  \\ $$| $$  \\ $$|  $$$$$$       |  $$$$$$  \s
                         \\____  $$      | $$    $$| $$  | $$| $$  | $$| $$  | $$| $$      /$$__  $$  | $$ /$$| $$  | $$| $$ /$$__  $$  | $$ /$$| $$| $$  | $$| $$  | $$ \\____  $$       \\____  $$ \s
                         /$$  \\ $$      |  $$$$$$/|  $$$$$$/| $$  | $$|  $$$$$$$| $$     |  $$$$$$$  |  $$$$/|  $$$$$$/| $$|  $$$$$$$  |  $$$$/| $$|  $$$$$$/| $$  | $$ /$$$$$$$/       /$$  \\ $$ \s
                        |  $$$$$$/       \\______/  \\______/ |__/  |__/ \\____  $$|__/      \\_______/   \\___/   \\______/ |__/ \\_______/   \\___/  |__/ \\______/ |__/  |__/|_______/       |  $$$$$$/ \s
                         \\_  $$_/                                      /$$  \\ $$                                                                                                        \\_  $$_/  \s
                           \\__/                                       |  $$$$$$/                                                                                                          \\__/    \s
                                                                       \\______/                                                                                                                   \s
                            /$$                      /$$     /$$                           /$$$$$                       /$$           /$$      /$$                                         /$$   \s
                          /$$$$$$                   |  $$   /$$/                          |__  $$                      | $$          | $$  /$ | $$                                       /$$$$$$ \s
                         /$$__  $$                   \\  $$ /$$//$$$$$$  /$$   /$$            | $$ /$$   /$$  /$$$$$$$ /$$$$$$        | $$ /$$$| $$  /$$$$$$  /$$$$$$$                   /$$__  $$\s
                        | $$  \\__/                    \\  $$$$//$$__  $$| $$  | $$            | $$| $$  | $$ /$$_____/|_  $$_/        | $$/$$ $$ $$ /$$__  $$| $$__  $$                 | $$  \\__/\s
                        |  $$$$$$                      \\  $$/| $$  \\ $$| $$  | $$       /$$  | $$| $$  | $$|  $$$$$$   | $$          | $$$$_  $$$$| $$  \\ $$| $$  \\ $$                 |  $$$$$$ \s
                         \\____  $$                      | $$ | $$  | $$| $$  | $$      | $$  | $$| $$  | $$ \\____  $$  | $$ /$$      | $$$/ \\  $$$| $$  | $$| $$  | $$                  \\____  $$\s
                         /$$  \\ $$                      | $$ |  $$$$$$/|  $$$$$$/      |  $$$$$$/|  $$$$$$/ /$$$$$$$/  |  $$$$/      | $$/   \\  $$|  $$$$$$/| $$  | $$                  /$$  \\ $$\s
                        |  $$$$$$/                      |__/  \\______/  \\______/        \\______/  \\______/ |_______/    \\___/        |__/     \\__/ \\______/ |__/  |__/                 |  $$$$$$/\s
                         \\_  $$_/                                                                                                                                                       \\_  $$_/ \s
                           \\__/                                                                                                                                                           \\__/   \s
                                                                                                                                                                                                 \s
                            /$$                                           /$$            /$$         /$$      /$$ /$$ /$$ /$$ /$$                                                          /$$  \s
                          /$$$$$$                                       /$$$$$$        /$$$$        | $$$    /$$$|__/| $$| $$|__/                                                        /$$$$$$\s
                         /$$__  $$                                     /$$__  $$      |_  $$        | $$$$  /$$$$ /$$| $$| $$ /$$  /$$$$$$  /$$$$$$$                                    /$$__  $$
                        | $$  \\__/                                    | $$  \\__/        | $$        | $$ $$/$$ $$| $$| $$| $$| $$ /$$__  $$| $$__  $$                                  | $$  \\__/
                        |  $$$$$$                                     |  $$$$$$         | $$        | $$  $$$| $$| $$| $$| $$| $$| $$  \\ $$| $$  \\ $$                                  |  $$$$$$\s
                         \\____  $$                                     \\____  $$        | $$        | $$\\  $ | $$| $$| $$| $$| $$| $$  | $$| $$  | $$                                   \\____  $$
                         /$$  \\ $$                                     /$$  \\ $$       /$$$$$$      | $$ \\/  | $$| $$| $$| $$| $$|  $$$$$$/| $$  | $$                                   /$$  \\ $$
                        |  $$$$$$/                                    |  $$$$$$/      |______/      |__/     |__/|__/|__/|__/|__/ \\______/ |__/  |__/                                  |  $$$$$$/
                         \\_  $$_/                                      \\_  $$_/                                                                                                         \\_  $$_/\s
                           \\__/                                          \\__/                                                                                                             \\__/  \s
                                                                                                                                                                                                  \s"""
        );
        System.out.println("Congratulations! " + g.getPlayer() + "! You are the Millionaire!");
        System.out.println("You just won $1,000,000!");
        System.out.println();
        System.out.println("[X] Exit");
    }

    @Override
    public void next(String input) {
        if (input.equalsIgnoreCase("X")) {
            System.out.println("Goodbye! Have a nice day");
            System.exit(0);
        } else {
            System.out.println("Invalid input");
        }
    }
}
