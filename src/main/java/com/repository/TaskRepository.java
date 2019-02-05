package com.repository;

import com.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ryandesmond on 7/30/18.
 */
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


    public Task findByNameAndDescription(String name, String description);

    @Modifying
    @Transactional
    @Query(value = "update tasks_app.tasks set description = ?1, name= ?2, completed = ?3 where id = ?4", nativeQuery = true)
    int updateTask(String description, String name, boolean completed, Long id);

    List<Task> findByUserIdAndCompletedFalse(long userId);

    List<Task> findByUserId(long userId);


}
