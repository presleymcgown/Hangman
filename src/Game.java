import acm.graphics.GLabel;
import acm.graphics.GRect;
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
    private int streakCounter = 0;
    private GLabel gameStreak;

    private String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public String[] wordBank = {"blood", "blank", "blame", "crime", "cruel", "debut", "dress",
                                "drops", "exile", "happy", "heart", "lakes", "lover", "peace",
                                "ready", "seven", "smile", "speak", "state", "story", "short",
                                "fever", "woman", "woods", "swift", "album", "karma", "style",
                                "world", "tours", "lover", "paper", "today", "rings", "clean"};



    @Override
    public void init(){

        int alphaNum = 0;

        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 13; col++) {

                lettersOnScreen = new GLabel(alphabet[alphaNum] + "");

                lettersOnScreen.setFont("Centaur-22");
                lettersOnScreen.setColor(new Color(1, 1, 1, 140));

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

        gameStreak = new GLabel("your streak: " + streakCounter);
        add(gameStreak, 10, 475);


        gameWord = wordBank[RandomGenerator.getInstance().nextInt(1, 35)];


        waitForClick();
        gameLoop();


    }

    private void gameLoop(){
        currentChar = Dialog.getString("please type in letter guess.");

        if (gameWord.contains(currentChar)){

            char checker0 = gameWord.charAt(0);
            char checker1 = gameWord.charAt(1);
            char checker2 = gameWord.charAt(2);
            char checker3 = gameWord.charAt(3);
            char checker4 = gameWord.charAt(4);

            String firstChecker = "" + checker0;
            String secChecker = "" + checker1;
            String thirdChecker = "" + checker2;
            String fourthChecker = "" + checker3;
            String fifthChecker = "" + checker4;


            if(currentChar.contains(firstChecker)){

                wordLetters = new GLabel(currentChar);
                add(wordLetters, 245, 295);
                rightPoints += 1;
                greenLetterAdder();
            }
            if(currentChar.contains(secChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 305, 295);
                rightPoints += 1;
                greenLetterAdder();
            }
            if(currentChar.contains(thirdChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 375, 295);
                rightPoints += 1;
                greenLetterAdder();
            }
            if(currentChar.contains(fourthChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 430, 295);
                rightPoints += 1;
            }
            if(currentChar.contains(fifthChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 490, 295);
                rightPoints += 1;
                greenLetterAdder();
            }

            if(rightPoints == 5){
                Dialog.showMessage("you won the game! play again.");
                streakCounter += 1;
                resetLoop();
            }

            gameLoop();

        }else{
            wrongPoints += 1;

            redLetterAdder();

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
                Dialog.showMessage("you lost! the word was " + gameWord + ". try again with a new word.");
                streakCounter = 0;
                resetLoop();
            }

        }


    }

    private void redLetterAdder(){
        // * this is where the letter that gets typed in gets removed (opacity lowered) if it isn't in the word
        if(alphabet[0].contains(currentChar)){

            lettersOnScreen = new GLabel("a");
            add(lettersOnScreen, 190 + (0 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[1].contains(currentChar)){
            lettersOnScreen = new GLabel("b");
            add(lettersOnScreen, 190 + (1 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[2].contains(currentChar)){
            lettersOnScreen = new GLabel("c");
            add(lettersOnScreen, 190 + (2 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[3].contains(currentChar)){
            lettersOnScreen = new GLabel("d");
            add(lettersOnScreen, 190 + (3 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[4].contains(currentChar)){
            lettersOnScreen = new GLabel("e");
            add(lettersOnScreen, 190 + (4 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[5].contains(currentChar)){
            lettersOnScreen = new GLabel("f");
            add(lettersOnScreen, 190 + (5 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[6].contains(currentChar)){
            lettersOnScreen = new GLabel("g");
            add(lettersOnScreen, 190 + (6 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[7].contains(currentChar)){
            lettersOnScreen = new GLabel("h");
            add(lettersOnScreen, 190 + (7 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[8].contains(currentChar)){
            lettersOnScreen = new GLabel("i");
            add(lettersOnScreen, 190 + (8 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[9].contains(currentChar)){
            lettersOnScreen = new GLabel("j");
            add(lettersOnScreen, 190 + (9 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[10].contains(currentChar)){
            lettersOnScreen = new GLabel("k");
            add(lettersOnScreen, 190 + (10 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[11].contains(currentChar)){
            lettersOnScreen = new GLabel("l");
            add(lettersOnScreen, 190 + (11 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[12].contains(currentChar)){
            lettersOnScreen = new GLabel("m");
            add(lettersOnScreen, 190 + (12 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[13].contains(currentChar)){
            lettersOnScreen = new GLabel("n");
            add(lettersOnScreen, 190 + (0 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[14].contains(currentChar)){
            lettersOnScreen = new GLabel("o");
            add(lettersOnScreen, 190 + (1 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[15].contains(currentChar)){
            lettersOnScreen = new GLabel("p");
            add(lettersOnScreen, 190 + (2 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[16].contains(currentChar)){
            lettersOnScreen = new GLabel("q");
            add(lettersOnScreen, 190 + (3 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[17].contains(currentChar)){
            lettersOnScreen = new GLabel("r");
            add(lettersOnScreen, 190 + (4 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[18].contains(currentChar)){
            lettersOnScreen = new GLabel("s");
            add(lettersOnScreen, 190 + (5 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[19].contains(currentChar)){
            lettersOnScreen = new GLabel("t");
            add(lettersOnScreen, 190 + (6 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[20].contains(currentChar)){
            lettersOnScreen = new GLabel("u");
            add(lettersOnScreen, 190 + (7 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[21].contains(currentChar)){
            lettersOnScreen = new GLabel("v");
            add(lettersOnScreen, 190 + (8 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[22].contains(currentChar)){
            lettersOnScreen = new GLabel("w");
            add(lettersOnScreen, 190 + (9 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[23].contains(currentChar)){
            lettersOnScreen = new GLabel("x");
            add(lettersOnScreen, 190 + (10 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[24].contains(currentChar)){
            lettersOnScreen = new GLabel("y");
            add(lettersOnScreen, 190 + (11 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }else if(alphabet[25].contains(currentChar)){
            lettersOnScreen = new GLabel("z");
            add(lettersOnScreen, 190 + (12 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(175, 71, 45, 255));

        }

    }

    private void greenLetterAdder(){
        // * this is where the letter that gets typed in gets removed (opacity lowered) if it isn't in the word
        if(alphabet[0].contains(currentChar)){

            lettersOnScreen = new GLabel("a");
            add(lettersOnScreen, 190 + (0 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[1].contains(currentChar)){
            lettersOnScreen = new GLabel("b");
            add(lettersOnScreen, 190 + (1 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[2].contains(currentChar)){
            lettersOnScreen = new GLabel("c");
            add(lettersOnScreen, 190 + (2 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[3].contains(currentChar)){
            lettersOnScreen = new GLabel("d");
            add(lettersOnScreen, 190 + (3 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[4].contains(currentChar)){
            lettersOnScreen = new GLabel("e");
            add(lettersOnScreen, 190 + (4 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[5].contains(currentChar)){
            lettersOnScreen = new GLabel("f");
            add(lettersOnScreen, 190 + (5 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[6].contains(currentChar)){
            lettersOnScreen = new GLabel("g");
            add(lettersOnScreen, 190 + (6 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[7].contains(currentChar)){
            lettersOnScreen = new GLabel("h");
            add(lettersOnScreen, 190 + (7 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[8].contains(currentChar)){
            lettersOnScreen = new GLabel("i");
            add(lettersOnScreen, 190 + (8 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[9].contains(currentChar)){
            lettersOnScreen = new GLabel("j");
            add(lettersOnScreen, 190 + (9 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[10].contains(currentChar)){
            lettersOnScreen = new GLabel("k");
            add(lettersOnScreen, 190 + (10 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[11].contains(currentChar)){
            lettersOnScreen = new GLabel("l");
            add(lettersOnScreen, 190 + (11 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[12].contains(currentChar)){
            lettersOnScreen = new GLabel("m");
            add(lettersOnScreen, 190 + (12 * 30), 400 + (0 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[13].contains(currentChar)){
            lettersOnScreen = new GLabel("n");
            add(lettersOnScreen, 190 + (0 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[14].contains(currentChar)){
            lettersOnScreen = new GLabel("o");
            add(lettersOnScreen, 190 + (1 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[15].contains(currentChar)){
            lettersOnScreen = new GLabel("p");
            add(lettersOnScreen, 190 + (2 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[16].contains(currentChar)){
            lettersOnScreen = new GLabel("q");
            add(lettersOnScreen, 190 + (3 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[17].contains(currentChar)){
            lettersOnScreen = new GLabel("r");
            add(lettersOnScreen, 190 + (4 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[18].contains(currentChar)){
            lettersOnScreen = new GLabel("s");
            add(lettersOnScreen, 190 + (5 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[19].contains(currentChar)){
            lettersOnScreen = new GLabel("t");
            add(lettersOnScreen, 190 + (6 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[20].contains(currentChar)){
            lettersOnScreen = new GLabel("u");
            add(lettersOnScreen, 190 + (7 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[21].contains(currentChar)){
            lettersOnScreen = new GLabel("v");
            add(lettersOnScreen, 190 + (8 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[22].contains(currentChar)){
            lettersOnScreen = new GLabel("w");
            add(lettersOnScreen, 190 + (9 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[23].contains(currentChar)){
            lettersOnScreen = new GLabel("x");
            add(lettersOnScreen, 190 + (10 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[24].contains(currentChar)){
            lettersOnScreen = new GLabel("y");
            add(lettersOnScreen, 190 + (11 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

        }else if(alphabet[25].contains(currentChar)){
            lettersOnScreen = new GLabel("z");
            add(lettersOnScreen, 190 + (12 * 30), 400 + (1 * 30));

            lettersOnScreen.setFont("Centaur-22-bold");
            lettersOnScreen.sendToFront();
            lettersOnScreen.setColor(new Color(97, 212, 149, 179));

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
