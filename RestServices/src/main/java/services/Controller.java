package services;

import entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/to-do-list/tasks")
public class Controller {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable Integer id) {
        Task task = taskRepository.findOne(id);

        if (task == null)
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Task> findAll() {
        return new ArrayList<>(taskRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task add(@RequestBody Task task) {
        taskRepository.add(task);
        return task;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            taskRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Task update(@RequestBody Task task) {
        taskRepository.update(task);
        return task;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String eventError(Exception e) {
        return e.getMessage();
    }
}
