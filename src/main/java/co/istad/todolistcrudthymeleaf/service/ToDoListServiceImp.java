package co.istad.todolistcrudthymeleaf.service;

import co.istad.todolistcrudthymeleaf.model.ToDoList;
import co.istad.todolistcrudthymeleaf.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ToDoListServiceImp implements ServerToDoList{
    private final ToDoListRepository toDoListRepository;
    @Override
    public List<ToDoList> getAllList() {
        return toDoListRepository.getToDoLists();
    }

    @Override
    public void addToDoList(ToDoList toDoList) {
        List<ToDoList> toDoLists = toDoListRepository.getToDoLists();
        ToDoList newList = new ToDoList();
        if (!toDoLists.isEmpty()){
            newList = toDoLists.get(toDoLists.size()-1);
        }
        toDoList.setId(newList.getId()+1);
        toDoList.setCreateAt(newList.getCreateAt());
        toDoListRepository.getToDoLists().add(toDoList);
    }

    @Override
    public ToDoList findById(Integer id) {
        return toDoListRepository.getToDoLists()
                .stream()
                .filter(idInList -> idInList.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateToDoList(ToDoList toDoList) {
        toDoListRepository.getToDoLists()
                .stream()
                .filter(l -> l.getId().equals(toDoList.getId()))
                .forEach(
                        l -> {
                            l.setTask(toDoList.getTask());
                            l.setDescription(toDoList.getDescription());
                            l.setIsDone(toDoList.getIsDone());
                        }
                );
    }

    @Override
    public void deleteToDoList(Integer id) {
        List<ToDoList> deleteProducts = toDoListRepository.getToDoLists();
        for(ToDoList deleteProduct : deleteProducts){
            if (deleteProduct.getId().equals(id)){
                deleteProducts.remove(deleteProduct);
                break;
            }

        }
    }

    @Override
    public List<ToDoList> searchByTaskAndIsDone(String task, Boolean isDone) {
        List<ToDoList> allToDoLists = toDoListRepository.getToDoLists();
        List<ToDoList> searchResults = new ArrayList<>();
        for (ToDoList searchList : allToDoLists) {
            // Check if task contains the provided substring and matches the isDone value
            if ((task == null || searchList.getTask().toLowerCase().contains(task)) && searchList.getIsDone().equals(isDone)) {
                searchResults.add(searchList);
            }
        }
        System.out.println(searchResults);
        return searchResults;
    }


}
