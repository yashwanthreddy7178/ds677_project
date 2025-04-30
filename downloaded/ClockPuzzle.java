import java.io.*;
import java.util.*;

public class ClockPuzzle extends Puzzle {
  
  public ClockPuzzle() {
    answer = new String[1];
    question = new String[1];
    submission = new ArrayList<String>();
    response = new String("That random clock you took sure came in HANDY, didn't it? HA! ...I'm sorry...");
    question[0] = "It seems clear that you have to press a key. Which key are you going to press?";
    answer[0] = "L";
  }
  
}
