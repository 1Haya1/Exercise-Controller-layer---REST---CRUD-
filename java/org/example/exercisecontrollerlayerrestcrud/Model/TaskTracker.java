package org.example.exercisecontrollerlayerrestcrud.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskTracker {

    private long id;
    private String title;
    private String description;
    private boolean status;


}
