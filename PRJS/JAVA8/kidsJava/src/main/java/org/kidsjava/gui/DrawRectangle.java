/*
 * created by leon
 */

package org.kidsjava.gui;
import java.io.IOException;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DrawRectangle {
    public static void main(String[] args) throws IOException {

       // Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();



        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        Screen screen = new TerminalScreen(terminal);

        TextGraphics tGraphics = screen.newTextGraphics();

        screen.startScreen();
        screen.clear();

        tGraphics.drawRectangle(
                new TerminalPosition(3,3), new TerminalSize(100,100), '*');
        screen.refresh();

        screen.readInput();
        screen.stopScreen();
    }
}
