package co.istad.todolistcrudthymeleaf.service;

import co.istad.todolistcrudthymeleaf.model.ToDoList;
import co.istad.todolistcrudthymeleaf.repository.ToDoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
