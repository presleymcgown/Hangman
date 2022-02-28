import acm.graphics.GLabel;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;
import svu.csc213.Dialog;

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
    private String gameWord;
    private GLabel lettersOnScreen;
    private GLabel wordLetters;
    private String currentChar;
    private int rightPoints;
    private int wrongPoints;

    private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public String[] wordBank = {"hello", "earth", "jazzy", "fizzy", "icing", "apple", "award",
                                "tides", "actor", "baked", "beefy", "carol", "cater", "cello",
                                "daily", "death", "dealt", "euros", "evict", "event", "feast",
                                "fever", "grime", "gifts", "swift", "album", "karma", "style",
                                "blank", "tours", "loves", "crush", "banks", "vroom", "clean"};



    @Override
    public void init(){

        int alphaNum = 0;

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 13; col++) {

                lettersOnScreen = new GLabel(alphabet[alphaNum] + "");

                lettersOnScreen.setFont("Centaur-22");

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


        gameWord = wordBank[RandomGenerator.getInstance().nextInt(1, 35)];


        waitForClick();
        gameLoop();


    }

    private void gameLoop(){
        currentChar = Dialog.getString("please type in letter guess.");

        if (gameWord.contains(currentChar)){
            wordLetters = new GLabel(currentChar);
            add(wordLetters, 100, 100);
            rightPoints += 1;
            gameLoop();
        }
        if (gameWord.contains(currentChar)){
            wordLetters = new GLabel(currentChar);
            add(wordLetters, 100, 100);
            rightPoints += 1;
            gameLoop();
        }else{
            wrongPoints += 1;
            if(wrongPoints == 1){
                head.setColor(new Color(0, 0, 0, 210));
                gameLoop();
            }else if(wrongPoints == 2){
                body.setColor(new Color(0, 0, 0, 210));
                gameLoop();
            }else if(wrongPoints == 3){
                LArm.setColor(new Color(0, 0, 0, 210));
                gameLoop();
            }else if(wrongPoints == 4){
                RArm.setColor(new Color(0, 0, 0, 210));
                gameLoop();
            }else if(wrongPoints == 5){
                LLeg.setColor(new Color(0, 0, 0, 231));
                gameLoop();
            }else if(wrongPoints == 6){
                RLeg.setColor(new Color(0, 0, 0, 210));
                pause(50);
                Dialog.showMessage("you lost! try again with a new word.");
                resetLoop();
            }
        }


    }

    private void resetLoop(){
        removeAll();
        currentChar = "";
        rightPoints = 0;
        wrongPoints = 0;
        init();
    }

    public static void main(String[] args) {
        new Game().start();
    }

}
