/*
 * Copyright (c) 2018. UnifiedPost, SA. http://www.unifiedpost.com
 * This software is the proprietary information of UnifiedPost, SA.
 * Use is subject to license terms.
 */

/*
 * created by leon
 */

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DrawRectangle {
    public static void main(String[] args) throws IOException {

       // Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();

        Terminal terminal = new DefaultTerminalFactory().setTerminalEmulatorTitle("ESODA -/- EXODA (- ΛΟΓΙΣΤΙΚΟ ΠΡΟΓΡΑΜΜΑ -)  ---  ΓΙΩΡΓΟΣ ΛΕΟΥ ΟΘΟΣ ΚΑΡΠΑΘΟΣ 2018 ").createTerminal();



        Screen screen = new TerminalScreen(terminal);

        TextGraphics tGraphics = screen.newTextGraphics();

//        setPreferredSize(new TerminalSize(20, 5))


        screen.startScreen();
        screen.clear();

        tGraphics.drawRectangle(
                new TerminalPosition(3,3), new TerminalSize(10,10), '*');
        screen.refresh();

        screen.readInput();
        screen.stopScreen();
    }
}
