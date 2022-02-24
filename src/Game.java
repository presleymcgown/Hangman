import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;

import java.awt.*;

public class Game extends GraphicsProgram {

    private Head head;
    private Body body;
    private Arm LArm;
    private Arm RArm;
    private Leg LLeg;
    private Leg RLeg;
    private letterLines lLine;

    private double lettersOfWord = 5;

    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private GLabel lettersOnScreen;

    @Override
    public void init(){

        int alphaNum = 0;

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 13; col++) {

                lettersOnScreen = new GLabel(alphabet[alphaNum] + "");

                lettersOnScreen.setFont("Calibri-22");

                add(lettersOnScreen, 190 + (col * 30), 400 + (row * 30));

                alphaNum += 1;

            }
        }

        for (int i = 0; i < lettersOfWord; i++) {

            lLine = new letterLines( (375 / (lettersOfWord) + 150)+ i * (50 + 10), 300, 50, 2);
            add(lLine);
        }

        head = new Head(350, 50, 50, this.getGCanvas(), new Color(97, 212, 149, 55));
        add(head);

        body = new Body(375, 100, .1, 75);
        add(body);

        LArm = new Arm(375, 115, 337.5, 105);
        add(LArm);

        RArm = new Arm(375, 115, 410, 105);
        add(RArm);

        LLeg = new Leg(375, 175, 400, 200);
        add(LLeg);

        RLeg = new Leg(375, 175, 350, 200);
        add(RLeg);



    }

    public static void main(String[] args) {
        new Game().start();
    }

}
