package com.services;

import com.exceptions.CustomDatabaseException;
import com.model.Task;
import com.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.List;

/**
 * Created by ryandesmond on 7/30/18.
 */
@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    //@Cacheable(value = "tasks")
    public List<Task> findAllTasks() throws Exception {

        List<Task> tasks;

        try {
            tasks = (List<Task>) taskRepository.findAll();
        } catch(Exception e){
            throw e;
        }

        return tasks;
    }

    //@Cacheable(value = "tasks", key = "#id")
    public Task findTaskById(long id) throws Exception{

        Task u;

        try {
            u = taskRepository.findOne(id);
        } catch(Exception e){
            throw e;
        }

        return u;
    }

    //@CachePut(value = "tasks", key = "#task.id")
    public Task createTask(Task task) throws Exception {

        try {
            taskRepository.save(task);
            Task u = taskRepository.findByNameAndDescription(task.getName(), task.getDescription());
            return u;
        } catch (Exception e){
            throw e;
        }

    }

    //@CachePut(value = "tasks", key = "#task.id")
    public Task updateTask(Task task) throws CustomDatabaseException {
        System.out.println();

        PrintStream test = System.out;

        try {
            int i = taskRepository.updateTask(task.getDescription(), task.getName(), task.isCompleted(), task.getId());

            if (i < 1) {
                throw new CustomDatabaseException("Unable to update Task");
            }

            return taskRepository.findOne(task.getId());

        } catch (Exception e){
            throw new CustomDatabaseException(e.getMessage());
        }
    }

    //@CacheEvict(value = "tasks", key = "#id")
    public boolean deleteTask(long id) throws Exception{

        try {
            taskRepository.delete(id);
            return true;
        } catch (Exception e){
            throw e;
        }
    }

    //@Cacheable(value = "tasks", key = "{#userId, #todo}")
    public List<Task> findAllTasksByUserId(long userId, boolean complete) {

        if (complete){
            return taskRepository.findByUserIdAndCompletedTrue(userId);
        } else {
            return taskRepository.findByUserId(userId);
        }
    }
}
