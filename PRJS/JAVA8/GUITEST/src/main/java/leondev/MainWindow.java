/*
 * Copyright (c) 2018. UnifiedPost, SA. http://www.unifiedpost.com
 * This software is the proprietary information of UnifiedPost, SA.
 * Use is subject to license terms.
 */

package leondev;

import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class MainWindow {

    public static void main(String[] args) throws IOException {
        String asciiArt = FigletFont.convertOneLine("test");
        System.out.println(asciiArt);
        String lines[] = asciiArt.split("\\r?\\n");
        Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
        Screen screen = new TerminalScreen(terminal);
        TextGraphics tGraphics = screen.newTextGraphics();
        screen.startScreen();
        screen.clear();
        tGraphics.drawRectangle( new TerminalPosition(3,3), new TerminalSize(60,10), '*');
        int col = 10;
        int row = 4;
        for( String line : lines){
            row ++;
            tGraphics.putString( col,row, line );
        }




        Table<String> table = new Table<String>("Column 1", "Column 2", "Column 3");
        table.getTableModel().addRow("1", "2", "3");
        table.setSelectAction(new Runnable() {
            @Override
            public void run() {
                List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                for(int i = 0; i < data.size(); i++) {
                    System.out.println(data.get(i));
                }
            }
        });



        screen.refresh();
        screen.readInput();
        screen.stopScreen();
    }

}
