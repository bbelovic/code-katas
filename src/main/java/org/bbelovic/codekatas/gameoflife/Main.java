package org.bbelovic.codekatas.gameoflife;

import org.bbelovic.codekatas.gameoflife.gui.ApplicationFrame;

import javax.swing.SwingUtilities;

public class Main {

    private static final String DEFAULT_PATTERN = "./src/main/resources/gameoflife/octagon4.txt";

    public static void main(final String[] args) throws Exception {
        final String filePath = args.length == 0 ? DEFAULT_PATTERN : args[0];
        final GridSerializer gridSerializer = new GridSerializer();
        final Grid grid = gridSerializer.deserializeFromFile(filePath);
        final ApplicationFrame applicationFrame = new ApplicationFrame(grid);
        SwingUtilities.invokeLater(applicationFrame::createAndShowGui);
    }
}
