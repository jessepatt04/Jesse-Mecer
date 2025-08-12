//Jesse Araujo Pattison
package fixthestory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Studio20-10
 */
public class FixTheStory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File myFile = new File("InputStory.txt");
        File newFile = new File("OutputFile.txt");

        try {
            Scanner scLine = new Scanner(myFile);
            FileWriter fw = new FileWriter(newFile);
            while (scLine.hasNext()) {
                String word = scLine.next();
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    switch (ch) {
                        case 'd':
                        case 'D':
                            fw.write(':');
                            break;
                        case 'n':
                        case 'N':
                            fw.write('?');
                            break;
                        case 'u':
                        case 'U':
                            fw.write('^');
                            break;
                        case 'f':
                        case 'F':
                            fw.write('@');
                            break;
                        case 'h':
                        case 'H':
                            fw.write('h');
                            fw.write('i');
                            break;
                        case 's':
                        case 'S':
                            fw.write('$');
                            break;
                        case ',':
                            fw.write('.');
                            break;
                        case '.':
                            fw.write(',');
                            break;
                        case 'b':
                        case 'B':
                            fw.write('}');
                            break;
                        case 'o':
                        case 'O':
                            fw.write('{');
                            break;
                        case 'p':
                        case 'P':
                            fw.write('0');
                            break;
                        case 'e':
                        case 'E':
                            fw.write('3');
                            fw.write('2');
                            fw.write('1');
                            break;
                        case 'r':
                        case 'R':
                            fw.write('#');
                            fw.write('!');
                            break;
                        case 'z':
                        case 'Z':
                            fw.write('5');
                            break;
                        case 'm':
                        case 'M':
                            fw.write('4');
                            break;
                        case 't':
                        case 'T':
                            fw.write('1');
                            break;
                        case 'a':
                        case 'A':
                            fw.write('2');
                            break;
                        case 'i':
                        case 'I':
                            fw.write('3');
                            break;
                        default:
                            fw.write(ch);
                            break;
                    }

                }
                fw.write(' ');
            }

            fw.close();

        } catch (FileNotFoundException ex) {
            System.out.println("No File");
        } catch (IOException ex) {
            System.out.println("IO error");
        }

        System.out.println("Success");

    }

}
