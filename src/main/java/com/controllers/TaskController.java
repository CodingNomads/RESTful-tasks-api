package com.controllers;

import com.exceptions.CustomDatabaseException;
import com.model.CustomResponseObject;
import com.model.Task;
import com.model.User;
import com.services.TaskService;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ryandesmond on 7/30/18.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    /**
     * GET ALL Tasks
     *
     * This endpoint will return a list of all tasks currently in the system. You can optionally provide
     * a "userId" parameter to only select tasks by user. You can also provide a "complete" boolean
     * parameter to only show completed tasks.
     *
     * @param userId - (Optional)
     * @param complete - pass "true" to only see completed tasks
     * @return A List of Task objects
     * @throws Exception
     */
    @GetMapping
    public CustomResponseObject<List<Task>> getTasks(
            @RequestParam(value = "userId", required = false, defaultValue = "-1") long userId,
            @RequestParam(value = "complete", required = false, defaultValue = "-1") String complete) throws Exception {

        List<Task> tasks;

        if (userId == -1 && complete.equalsIgnoreCase("-1")){
            tasks = taskService.findAllTasks();
        } else if (userId == -1 && !complete.equalsIgnoreCase("-1")) {
            tasks = taskService.findAllTasksByCompletion(complete);
        } else if (userId != -1 && !complete.equalsIgnoreCase("-1")) {
            tasks = taskService.findAllTasksByUserId(userId, complete);
        } else {
            throw new Exception("Invalid request parameters");
        }


        CustomResponseObject<List<Task>> obj = new CustomResponseObject();
        obj.setData(tasks);
        obj.setStatusCode(200);

        return obj;

    }

    @GetMapping("/{id}")
    public CustomResponseObject<Task> findTaskById(@PathVariable("id") long id) throws Exception {
        Task task = taskService.findTaskById(id);

        CustomResponseObject<Task> obj = new CustomResponseObject();
        obj.setData(task);
        obj.setStatusCode(200);

        return obj;

    }

    @PostMapping
    public CustomResponseObject<Task> createTask(@Valid @RequestBody Task task) throws Exception {

        Task t = taskService.createTask(task);

        CustomResponseObject<Task> obj = new CustomResponseObject();
        obj.setData(t);
        obj.setStatusCode(200);

        return obj;
    }

    @PutMapping
    public CustomResponseObject<Task> updateTask(@Valid @RequestBody Task task) throws CustomDatabaseException {

        Task t = taskService.updateTask(task);

        CustomResponseObject<Task> obj = new CustomResponseObject();
        obj.setData(t);
        obj.setStatusCode(200);

        return obj;
    }

    @DeleteMapping("/{id}")
    public CustomResponseObject<String> deleteTask(@PathVariable("id") long id) throws Exception {

        boolean success = taskService.deleteTask(id);

        CustomResponseObject<String> obj = new CustomResponseObject();
        if (success){
            obj.setData("Task Successfully Deleted");
            obj.setStatusCode(200);
            return obj;
        }

        throw new CustomDatabaseException("Unable to delete task");
    }

}
