package com.company;
//Jake Nelson 5-3-22 Project 4

import com.google.gson.Gson;

import java.io.*;
import java.util.*;


public class Main {
    static Scanner input = new Scanner(System.in);
    static int selection;
    static Gson gson = new Gson();
    static File saveData = new File("C:\\Users\\111182\\project4\\data.json");
    static FileReader fileReader;

    static {
        try {
            fileReader = new FileReader(saveData);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static TaskList master = gson.fromJson(fileReader, TaskList.class);


    public static void main(String[] args) {
        menu();
    }


    static void menu() {
        boolean isInt = false;

        System.out.println("Please select an option: " +
                "\n(1) Add a task" +
                "\n(2) Remove a task" +
                "\n(3) Update a task" +
                "\n(4) List all tasks" +
                "\n(5) List all tasks of a certain priority" +
                "\n(0) Save and exit");

        try {
            System.out.println("Hit enter to begin program");
            input.nextLine();
            selection = input.nextInt();
            input.nextLine();

            while (!isInt) {


                isInt = true;

                switch (selection) {
                    case 1:
                        addTask(master);
                        break;
                    case 2:
                        removeTask(master);
                        break;
                    case 3:
                        updateTask(master);
                        break;
                    case 4:
                        Collections.sort(master);
                        for (Task a:master) {
                            System.out.println(a);
                        }
                        menu();
                        break;
                    case 5:
                        getTasksByPriority(master);
                    case 0:
                        try {
                            FileWriter writer = new FileWriter("data.json");
                            gson.toJson(master, writer);
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.exit(0);
                        break;
                }
            }
        } catch (Exception e) {

            System.out.println("Something went wrong. Please enter a new integer between 0-5:");


            menu();
        }


    }


    static TaskList addTask(TaskList master) {
        String name;
        String description;
        int priority;

        System.out.println("Please enter the name of the task: ");
        name = input.nextLine();
        System.out.println("Please enter the description of the task: ");
        description = input.nextLine();
        System.out.println("Please enter the priority of the task(5 means high, 0 means low): ");
        priority = input.nextInt();
        input.nextLine();
        Task task = new Task(name, description, priority);
        master.add(task);
        System.out.println(master);
        menu();
        return master;
    }

    static TaskList removeTask(TaskList master) {
        System.out.println(master);
        System.out.println("Please enter the number of the task you would like to remove: ");
        int delete = input.nextInt();
        input.nextLine();
        System.out.println("I have removed " + master.get(delete - 1));
        master.remove(delete - 1);
        menu();
        return master;
    }

    static TaskList updateTask(TaskList master) {
        System.out.println(master);
        System.out.println("Please enter the number of the task you would like to update: ");
        int pick = input.nextInt();
        input.nextLine();
        System.out.println("Please enter the name of the task: ");
        String newName = input.nextLine();
        System.out.println("Please enter the description of the task: ");
        String newDescription = input.nextLine();
        System.out.println("Please enter the priority of the task(5 means high, 0 means low): ");
        int newPriority = input.nextInt();
        input.nextLine();
        Task newTask = new Task(newName, newDescription, newPriority);
        master.set(pick - 1, newTask);
        System.out.println(master);
        menu();
        return master;
    }

    static void getTasksByPriority(TaskList master) {
        System.out.println("Please enter the priority level you want to view");
        int priorityPick = input.nextInt();
        input.nextLine();
        for (Task task : master) {
            task.getPriority();
            if (task.getPriority() == priorityPick) {
                System.out.println(task);
            }
        }
        menu();
    }


}