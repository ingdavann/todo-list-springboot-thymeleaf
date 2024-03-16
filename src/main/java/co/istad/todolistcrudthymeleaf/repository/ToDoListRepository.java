package co.istad.todolistcrudthymeleaf.repository;

import co.istad.todolistcrudthymeleaf.model.ToDoList;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class ToDoListRepository {
    List<ToDoList> toDoLists = new ArrayList<>();
    public ToDoListRepository(){
        toDoLists.add(new ToDoList(1,"Learn Java Basic","Java Review feature and first app",true, LocalDate.now()));
        toDoLists.add(new ToDoList(2,"Learn JavaScript Basic","Java Review feature and first app",true, LocalDate.now()));
        toDoLists.add(new ToDoList(3,"Learn Bootstrap Basic","Java Review feature and first app",true, LocalDate.now()));
    }
}
