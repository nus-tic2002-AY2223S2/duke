import java.util.Scanner;

public class Ui {
    protected String sentences;

    public Ui(){

    }


    public String getUserCommand() {
        Scanner in = new Scanner(System.in);
        this.sentences = in.nextLine();
        return sentences;
    }

}

