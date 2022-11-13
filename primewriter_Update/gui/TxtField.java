package primewriter.gui;

import java.awt.*;

public class TxtField{
    private Frame f = new Frame ("Label");

    public void createfield (Object text){
        String txt = text.toString();
        Label l1 = new Label(txt);
        f.add(l1);
    }
    

}