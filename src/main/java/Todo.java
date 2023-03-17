////////////////////////////////////////////////
//  DONE BY: A0227169X; ANG JIA JIN, GABRIEL  //
////////////////////////////////////////////////
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
