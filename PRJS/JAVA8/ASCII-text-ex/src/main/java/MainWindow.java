/*
 * Copyright (c) 2018. UnifiedPost, SA. http://www.unifiedpost.com
 * This software is the proprietary information of UnifiedPost, SA.
 * Use is subject to license terms.
 */

import com.github.lalyos.jfiglet.FigletFont;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.BestCharacterFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.ColorSquareErrorFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToImageConverter;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class MainWindow {

    public static void main(String[] args) throws IOException { startGUI(); }


    public static void startGUI() {
        try {
            String asciiArt = FigletFont.convertOneLine("test");
            System.out.println(asciiArt);
            String lines[] = asciiArt.split("\\r?\\n");



            Terminal terminal = new DefaultTerminalFactory(System.out, System.in, Charset.forName("UTF8")).createTerminal();
            Screen screen = new TerminalScreen(terminal);

            TerminalSize size = new TerminalSize(600, 400);


            size =  screen.getTerminalSize()  ;
            System.out.println("LEON DEV   >>>"+ size.toString());


            TextGraphics tGraphics = screen.newTextGraphics();
            screen.startScreen();
            screen.clear();

            tGraphics.putString(  1,1, "Welcome ...... ");


            tGraphics.drawRectangle(new TerminalPosition(3, 3), new TerminalSize(600, 400), '*');

            int col = 10;
            int row = 4;
            for (String line : lines) {
                row++;
                tGraphics.putString(col, row, line);
            }

            final Table<String> table = new Table<String>("Column 1", "Column 2", "Column 3");
            table.getTableModel().addRow("1", "2", "3");
            table.setSelectAction(new Runnable() {
                public void run() {
                    List<String> data = table.getTableModel().getRow(table.getSelectedRow());
                    for (int i = 0; i < data.size(); i++) {
                        System.out.println(data.get(i));
                    }
                }
            });


            // System.out.println(());
            screen.refresh();
            String linesImage[] = getProfileImage().split("\\r?\\n");
            col = 10;
            row = 4;
            for (String line : linesImage) {
                row++;
                tGraphics.putString(col, row, line);
            }


            screen.refresh();
            screen.readInput();
            screen.stopScreen();

        } catch (Exception ex1) {
            System.out.println(" MainWindow startGUI Exception ex1 ");
            ex1.printStackTrace();
        }

    }

    private static String getProfileImage() {

        try {

            // initialize caches
            AsciiImgCache smallFontCache = AsciiImgCache.create(new Font("Courier", Font.BOLD, 6));
            AsciiImgCache mediumBlackAndWhiteCache = AsciiImgCache.create(new Font(
                    "Courier", Font.BOLD, 10), new char[]{'\\', ' ', '/'});
            AsciiImgCache largeFontCache = AsciiImgCache.create(new Font("Courier",  Font.PLAIN, 16));

            // load image
            BufferedImage portraitImage = ImageIO.read(new File(  "examples/portrait.png"));

            // initialize algorithms
            BestCharacterFitStrategy squareErrorStrategy = new ColorSquareErrorFitStrategy();
            BestCharacterFitStrategy ssimStrategy = new StructuralSimilarityFitStrategy();

            // initialize converters
            AsciiToImageConverter imageConverter = new AsciiToImageConverter(
                    smallFontCache, squareErrorStrategy);
            AsciiToStringConverter stringConverter = new AsciiToStringConverter(
                    largeFontCache, ssimStrategy);

            // large font images, ssim
            imageConverter.setCharacterCache(largeFontCache);
            imageConverter.setCharacterFitStrategy(ssimStrategy);
            ImageIO.write(imageConverter.convertImage(portraitImage), "png",
                    new File("examples/portrait_small_ssim.png"));

            // string converter, output to console
            System.out.println(stringConverter.convertImage(portraitImage));
            return stringConverter.convertImage(portraitImage).toString();

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

        return null;

    }


}
