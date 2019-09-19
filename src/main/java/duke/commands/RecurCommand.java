package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.items.DateTime;
import duke.items.Deadline;
import duke.items.Event;
import duke.items.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Command object for scheduling recurring tasks
 * Requires recurInterval, numberOfRecur and startDate
 */
public class RecurCommand extends Command {

    private int recurInterval; //number of days
    private int numberOfRecur;
    private String details;
    private String description;

    public  RecurCommand (CommandType type, String description, String details, int recurInterval, int numberOfRecur) {
        super(type);
        this.recurInterval = recurInterval;
        this.details = details;
        this.numberOfRecur = numberOfRecur;
        this.description = description;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy HHmm");
        Calendar startDate = new DateTime(details).getCalendar();

        for(int i = 0; i < numberOfRecur; ++i) {
            startDate.add(Calendar.DATE, recurInterval);
            String dt = sdf.format(startDate.getTime());
            if (super.type == CommandType.DEADLINE) {
                list.addDeadlineItem(description, dt);
            } else { //Type is event
                list.addEventItem(description, dt);
            }
        }
    }

}





