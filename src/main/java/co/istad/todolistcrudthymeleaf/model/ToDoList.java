package co.istad.todolistcrudthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ToDoList {
    private Integer id;
    private String task;
    private String description;
    private Boolean isDone;
    private LocalDate createAt;

}
