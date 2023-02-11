import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello there! I'm Duke\nWhat's on your mind today?");
        //String[] com= new String[100];
        Task[] t= new Task[10];
        String temp;
        int i=1;
        int j=0;
        while(i==1){
            Scanner in = new Scanner(System.in);
            temp = in.nextLine();

            if(temp.equals("list")){
                System.out.println("\nContent of your List\n");

                for(int k=0;k<j;k++){
                    //    System.out.println(k+1 + ". ["+t[k].getStatusIcon()+"] " + t[k].getDescription() +"\n");
                    System.out.println(k+1 + ". "+t[k]);

                }
                System.out.println("\nEnd of list\n");

            }
            else if(temp.equals("bye")){
                i=0;
            }

            else if (temp.substring(0,3).equals("unm")) {
                //unmark
                int num= Integer.parseInt(temp.substring(7));
                t[num-1].markAsUndone();
                System.out.println(t[num-1]+ "\n");
            }

            else if (temp.substring(0,3).equals("mar")) {
                //mark
                int num= Integer.parseInt(temp.substring(5));
                t[num-1].markAsDone();
                System.out.println(t[num-1]+ "\n");

            }
            else if (temp.substring(0,4).equals("todo")){

                t[j]= new Todo(temp);
                j++;
                System.out.println("The task has been successfully added to your list!");
                System.out.println(t[j-1]);
                System.out.println("Now you have "+ j +" tasks in your list\n");

            }
            else if (temp.substring(0,8).equals("deadline")){

                int index= temp.indexOf('/');

                t[j]= new Deadline(temp.substring(9,index), temp.substring(index+1));

                j++;

                System.out.println("The task has been successfully added to your list!");
                System.out.println(t[j-1]);
                System.out.println("Now you have "+ j +" tasks in your list\n");
            }
            else if (temp.substring(0,5).equals("event")){

                int index= temp.indexOf('/');

                t[j]= new Event(temp.substring(6,index), temp.substring(index+1));

                j++;

                System.out.println("The task has been successfully added to your list!");
                System.out.println(t[j-1]);
                System.out.println("Now you have "+ j +" tasks in your list\n");
            }

        }
        System.out.println("Byeee!! See you Again!!");
    }
}
