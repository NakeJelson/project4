package com.company;

public class Task implements Comparable<Task>{
    private String name;
    private String description;
    private int priority;

    public Task( String name, String description, int priority) {

        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +

                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }


    @Override
    public int compareTo(Task task) {
        if (this.priority == task.getPriority())
            return this.name.compareTo(task.getName());
        else if (this.priority > task.getPriority())
            return 1;
        else
            return -1;
    }
}

