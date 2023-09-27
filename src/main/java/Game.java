import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {
    private int x = 20;
    private int y = 10;
    private final Screen screen;
    private final Hero hero;

    public Game() throws IOException {
        TerminalSize terminalSize = new TerminalSize(40, 20);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary();

        hero = new Hero(10, 10);
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }
    private boolean  processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        boolean res= true;
        if (key.getKeyType() == KeyType.EOF){
            res=false;

        }

        if (key.getKeyType() == KeyType.ArrowUp){
            hero.moveUp();

        }
        if (key.getKeyType() == KeyType.ArrowDown){
            hero.moveDown();
        }
        if (key.getKeyType() == KeyType.ArrowLeft){
            hero.moveLeft();
        }
        if (key.getKeyType() == KeyType.ArrowRight){
            hero.moveRight();
        }

        if (key.getKeyType() == KeyType.Character && key.getCharacter()
                == 'q'){
            screen.close();

            System.exit(0);
        }
        return res;
    }
    public void run() throws IOException {
        boolean loop=true;
        while (loop){
            draw();
            KeyStroke key = screen.readInput();
            if( !processKey(key)){
                loop=false;
            }
            draw();

        }

    }
}
