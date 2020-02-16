import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import processing.serial.*;
Serial myPort;

  String[][] getEasyList() {
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

     String[][] getMediumList() {
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

    String[][] getHardList() {
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
        void playFile(String name, int num) {
        File audioFile = new File(name);
        

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
             AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
            Thread.sleep(num);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Oh dear");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oh dear pt 2");
        } catch (LineUnavailableException e) {
            System.out.println("Oh dear pt 3");
        } catch (InterruptedException e) {
            System.out.println("Lol wut pt 3");
        }
    }

    int getNum(String name) {
        switch(name) {
            case "noun" : return 1;
            case "verb" : return 2;
            case "adj" : return 3;
            case "adv" : return 4;
        }
        return 0;
    }

boolean correct = true;
int noun;
int verb;
int adj;
int adv;
int level;
int lvlCount =1;
int subject;
int subCount=1;
boolean vCheck = false;
boolean aCheck = false;
String[][] easy = getEasyList();
String[][] medium = getMediumList();
String[][] hard = getHardList();
int i = 0;

void setup() {
  println(Serial.list()); // list all available serial ports
  myPort = new Serial(this, Serial.list()[0], 9600); // define input port
  myPort.clear(); // clear the port of any initial junk
  size(1000, 750);
  background(0,0, 150);
  stroke(0,0,0);
  fill(255,255,255);
  ellipse(100, 100, 75, 75);
  textSize(15);
  text("Noun", 77, 165);
  ellipse(400, 100, 75, 75);
  text("Verb", 383, 165);
  ellipse(100, 500, 75, 75);
  text("Adjective", 70, 565);
 
}

void MouseReleased() {
  System.out.println(mouseX + " jjj");
  if(63 <=  mouseX && mouseX <= 137 && mouseY >= 63 && mouseY <=137) {
    System.out.println("Hurray");
    lvlCount = 1;
  }
}


int runGrammarBoop(int i) {
  String str ="";
  switch(3) {
    case 1:
         str = easy[i][1];
        playFile("Easy Converted/" + str, 4000);
        return getNum(easy[i][2]);

    case 2:
         str = medium[i][1];
        playFile("Medium Converted/" + str, 4000);
        return getNum(medium[i][2]);

    case 3:
    
         str = hard[i][1];
        playFile("Hard Converted/" + str, 4000);
        return getNum(hard[i][2]);

    default: System.out.println("Bad Input.");
  }
  return 0;
}

boolean checkGrammarBoop(int num) {
  switch(num) {
    case 1: if (noun != 1) return false; else return true; 
    case 2: if (verb != 10) return false; else return true;
    case 3: if (adj != 1) return false; else return true; 
    case 4: if (adv != 500) return false; else return true; 
  }
  return true;
}

boolean runHistoryBoop() {
  return true;
}


void draw () {
  vCheck = false;
  aCheck = false;
  if(subCount == 1) {
    int num = runGrammarBoop(i);
    i++;
    //delay(3000);
    noun = 0;
    verb = 0;
    adj = 0;
    adv = 0;
    
    while (myPort.available () > 0){
      String inString = myPort.readStringUntil('\n');
      if(inString != null) {
        inString = trim(inString);
        String[] xyRaw = splitTokens(inString, "\t");
        if(xyRaw.length == 5) {
           if(noun != 1) noun = int(xyRaw[0]);
           if (verb > .6) vCheck = true; verb = int(xyRaw[1]);
           if (adj != 1) adj = int(xyRaw[2]);
           if (adv > 100) aCheck = true; adv = int(xyRaw[3]);
           //level = int(xyRaw[4]);
           //subject = int(xyRaw[5]);
  
        }
      }
    }
    if (verb >=.6) {
      System.out.println("Didn't twist back");
      for(;;) {}
    }
    if(vCheck) verb = 10;
    if(aCheck) adv = 500;
    if(!checkGrammarBoop(num)) {
      fill(225,0,0);
      stroke(225,0,0);
      textSize(30);
      text("You Lose", 300, 350);
      System.out.println("oh no no");
      for(;;) {}
    }
    System.out.println("hello");
  } else {
    if(!runHistoryBoop()) {
      for(;;) {}
    }
  }
  
  
  
  
}