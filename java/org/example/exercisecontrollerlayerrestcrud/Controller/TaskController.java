package org.example.exercisecontrollerlayerrestcrud.Controller;


import org.example.exercisecontrollerlayerrestcrud.Api.ApiResponse;
import org.example.exercisecontrollerlayerrestcrud.Model.TaskTracker;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/tasks")
public class TaskController {

    private List<TaskTracker> tasks = new ArrayList<>();
    private long taskId = 1;

    @GetMapping("/all")
    public List<TaskTracker> getAllTasks() {
        return tasks;
    }

    @PutMapping("/update/{id}")
    public TaskTracker updateTask(@PathVariable("id") long id, @RequestBody TaskTracker updatedTask) {
        for (TaskTracker task : tasks) {
            if (task.getId() == id) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setStatus(true);
                return task;
            }
        }
        return null;
    }

    @PostMapping("/add")
    public TaskTracker addTask(@RequestBody TaskTracker task) {
        task.setId(taskId++);
        tasks.add(task);
        return task;
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteTask(@PathVariable long id) {
        try {
            tasks.removeIf(task -> task.getId() == id);
            return new ApiResponse("تم الحذف بنجاح");
        } catch (Exception e) {
            return new ApiResponse("خطأ في الحذف");
        }
    }

    @PutMapping("/change/{id}")
    public ApiResponse changeTaskStatus(@PathVariable long id) {
        for (TaskTracker task : tasks) {
            if (task.getId() == id) {
                task.setStatus(!task.isStatus()); // تغيير الحالة
                return new ApiResponse("تم تغيير حالة المهمة");
            }
        }
        return new ApiResponse("لم يتم العثور على المهمة");
    }

    @GetMapping("/search/{title}")
    public List<TaskTracker> searchByTitle(@PathVariable String title) {
        List<TaskTracker> foundTasks = new ArrayList<>();
        for (TaskTracker task : tasks) {
            if (task.getTitle().equals(title)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}



