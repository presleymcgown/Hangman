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
    private int streakCounter = 0;
    private GLabel gameStreak;

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
            }
            if(currentChar.contains(secChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 305, 295);
                rightPoints += 1;
            }
            if(currentChar.contains(thirdChecker)){
                wordLetters = new GLabel(currentChar);
                add(wordLetters, 375, 295);
                rightPoints += 1;
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
            }

            if(rightPoints == 5){
                Dialog.showMessage("you won the game! play again.");
                streakCounter += 1;
                resetLoop();
            }

            gameLoop();

        }else{
            wrongPoints += 1;

            // * this is where the letter that gets typed in gets removed (opacity lowered) if it isn't in the word
            // ! THIS NEEDS TO BE BUG CHECKED AND COMPLETED, IT DOESN'T WORK!
            if(alphabet[1].contains(currentChar)){
               lettersOnScreen = new GLabel("A");
               add(lettersOnScreen, (190 + 30), (400 + 30));

               lettersOnScreen.setFont("Centaur-22");
               lettersOnScreen.setColor(new Color(175, 71, 45, 227));

            }

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
