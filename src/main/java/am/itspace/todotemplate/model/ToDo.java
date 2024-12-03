package am.itspace.todotemplate.model;

import am.itspace.todotemplate.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    private int id;
    private String title;
    private Date createdDateTime;
    private Date finishedDateTime;
    private int userId;
    private Status status;
}
