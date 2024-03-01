package xyz.gamars.graphics;

import xyz.gamars.PanelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayButton extends Button {

    public PlayButton() {
        super(new ImageIcon("src/main/resources/brawlstarsPlayButton.jpeg"));

    }

    //do i need to overwrite my constructor? how do i add it to panel from here?
    public PlayButton(PanelManager panelManager) {
       super(new ImageIcon("src/main/resources/brawlstarsPlayButton.jpeg"));
    }

}
