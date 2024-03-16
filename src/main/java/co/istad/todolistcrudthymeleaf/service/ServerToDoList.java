package co.istad.todolistcrudthymeleaf.service;

import co.istad.todolistcrudthymeleaf.model.ToDoList;

import java.util.List;

public interface ServerToDoList {
    public List<ToDoList> getAllList();
    public void addToDoList(ToDoList toDoList);
    public ToDoList findById(Integer id);
    public void updateToDoList(ToDoList toDoList);
    public void deleteToDoList(Integer id);
}
