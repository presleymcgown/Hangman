public class wordBank {

    public String wordGetter;

    public String[] wordBank = { "hello", "earth", "jazzy", "fizzy", "icing", "apple", "award",
                                 "tides", "actor", "baked", "beefy", "carol", "cater", "cello",
                                 "daily", "death", "dealt", "euros", "evict", "event", "feast",
                                 "fever", "grime", "gifts", "swift", "album", "karma", "style",
                                 "blank", "tours", "loves", "crush", "banks", "vroom", "clean"};

    public String getWord(int num) {

        wordGetter = wordBank[num];

        return wordGetter;


    }


}
