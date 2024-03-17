package co.istad.todolistcrudthymeleaf.controller;

import co.istad.todolistcrudthymeleaf.model.ToDoList;
import co.istad.todolistcrudthymeleaf.service.ServerToDoList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ToDoListController {
    private final ServerToDoList serverToDoList;

    @GetMapping("/todo")
    public String index(Model model){
        List<ToDoList> toDoLists = serverToDoList.getAllList();
        model.addAttribute("toDoLists", toDoLists);
        return "index";
    }

    @GetMapping("/todo/new")
    public String addProduct(Model model){
        ToDoList toDoLists = new ToDoList();
        model.addAttribute("toDoLists", toDoLists);
        return "addModal";
    }
    @PostMapping("/todo/new")
    public String submit(@ModelAttribute("index") ToDoList toDoList) {
        serverToDoList.addToDoList(toDoList);
        return "redirect:/todo";
    }

    @GetMapping("/todo/update/{id}")
    public String showUpdateList(@PathVariable("id") Integer id, Model model){
        ToDoList isFoundList = serverToDoList.findById(id);

        model.addAttribute("toDoLists", isFoundList);
        return "update";
    }
    @PostMapping("/todo/update")
    public String updateList(@ModelAttribute("index") ToDoList toDoList){
        serverToDoList.updateToDoList(toDoList);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        serverToDoList.deleteToDoList(id);
        return "redirect:/todo";
    }


    @GetMapping("/todo/search")
    public String search(@RequestParam(name = "task", required = false) String task,
                         @RequestParam(name = "isDone", required = false, defaultValue = "false") Boolean isDone,
                         Model model) {
        System.out.println(task);
        List<ToDoList> searchResults = serverToDoList.searchByTaskAndIsDone(task, isDone);
        model.addAttribute("toDoLists", searchResults);
        return "index"; // Return to the index page with search results
    }
}
