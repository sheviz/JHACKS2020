
import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class wordList {

    public static String[][] getEasyList() {
        String[][] easy = {
            {"key", "key.wav", "noun"}, 
            {"cup", "cup.wav", "noun"},
            {"ink", "ink.wav", "noun"},
            {"box", "box.wav", "noun"},
            {"bed", "bed.wav", "noun"},
            {"egg", "egg.wav", "noun"},
            {"ear", "ear.wav", "noun"},
            {"hat", "hat.wav", "noun"},
            {"cow", "cow.wav", "noun"},
            {"man", "man.wav", "noun"},
            {"say", "say.wav", "verb"}, 
            {"did", "did.wav", "verb"}, 
            {"use", "use.wav", "verb"}, 
            {"get", "get.wav", "verb"}, 
            {"try", "try.wav", "verb"},
            {"ask", "ask.wav", "verb"},
            {"big", "big.wav", "adj"},
            {"wet", "wet.wav", "adj"},
            {"bad", "bad.wav", "adj"},
            {"far", "far.wav", "adj"},
            {"old", "old.wav", "adj"},
            {"shy", "shy.wav", "adj"},
            {"gently", "gently.wav", "adv"},
            {"happily", "happily.wav", "adv"},
            {"easily", "easily.wav", "adv"},
            {"angrily", "angrily.wav", "adv"},
            {"quickly", "quickly.wav", "adv"},
            {"nearly", "nearly.wav", "adv"}
            };
            List<String[]> strList = Arrays.asList(easy);
            Collections.shuffle(strList);
            strList.toArray(easy);
            return easy;
    }

    public static String[][] getMediumList() {
        String[][] medium = {
            {"time", "time.wav", "noun"}, 
            {"hand", "hand.wav", "noun"},
            {"city", "city.wav", "noun"},
            {"word", "word.wav", "noun"},
            {"girl", "girl.wav", "noun"},
            {"news", "news.wav", "noun"},
            {"hill", "hill.wav", "noun"},
            {"baby", "baby.wav", "noun"},
            {"ball", "ball.wav", "noun"},
            {"wind", "wind.wav", "noun"},
            {"read", "read.wav", "verb"}, 
            {"make", "make.wav", "verb"}, 
            {"want", "want.wav", "verb"}, 
            {"find", "find.wav", "verb"}, 
            {"give", "give.wav", "verb"},
            {"good", "good.wav", "adj"},
            {"blue", "blue.wav", "adj"},
            {"tall", "tall.wav", "adj"},
            {"happy", "happy.wav", "adj"},
            {"young", "young.wav", "adj"},
            {"loud", "loud.wav", "adj"},
            {"very", "very.wav", "adv"},
            {"always", "always.wav", "adv"}
            };
            List<String[]> strList = Arrays.asList(medium);
            Collections.shuffle(strList);
            strList.toArray(medium);
            return medium;
    }

    public static String[][] getHardList() {
         String[][] difficult = {
            {"government", "government.wav", "noun"}, 
            {"industry", "industry.wav", "noun"},
            {"language", "language.wav", "noun"},
            {"president", "president.wav", "noun"},
            {"knowledge", "knowledge.wav", "noun"},
            {"behavior", "behavior.wav", "noun"},
            {"economy", "economy.wav", "noun"},
            {"improve", "improve.wav", "verb"}, 
            {"multiply", "multiply.wav", "verb"}, 
            {"discuss", "discuss.wav", "verb"}, 
            {"explain", "explain.wav", "verb"}, 
            {"educate", "educate.wav", "verb"},
            {"elaborate", "elaborate.wav", "verb"},
            {"organize", "organize.wav", "verb"},
            {"adamant", "adamant.wav", "adj"},
            {"attractive", "attractive.wav", "adj"},
            {"rebellious", "rebellious.wav", "adj"},
            {"naive", "naive.wav", "adj"},
            {"tenacious", "tenacious.wav", "adj"},
            {"nefarious", "nefarious.wav", "adj"},
            {"often", "often.wav", "adv"},
            {"never", "never.wav", "adv"},
            {"almost", "almost.wav", "adv"}
            };
            List<String[]> strList = Arrays.asList(difficult);
            Collections.shuffle(strList);
            strList.toArray(difficult);
            return difficult;
    }

    public static void playFile(String name) {
        File audioFile = new File(name);
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
             AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
            Thread.sleep(1000);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Oh dear");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Oh dear pt 2");
        } catch (LineUnavailableException e) {
            System.out.println("Oh dear pt 3");
        } catch (InterruptedException e) {
            System.out.println("Lol wut pt 3");
        }
    }

    public static void main(String[] args) {
        //TODO: rn based on user input, will change to accomadate hardware
        switch(Integer.parseInt(args[0])) {
            case 1:
            String[][] easy = getEasyList();
            for (String[] s : easy) {
                String str = s[1];
                playFile("Easy Converted/" + str);
            }
            break;
            case 2:
            String[][] medium = getMediumList();
            for (String[] s : medium) {
                String str = s[1];
                playFile("Medium Converted/" + str);
            }
            break;
            case 3:
            String[][] hard = getHardList();
            for (String[] s : hard) {
                String str = s[1];
                playFile("Hard Converted/" + str);
            }
            break;
            default: System.out.println("Bad Input.");

        }
    }
}
