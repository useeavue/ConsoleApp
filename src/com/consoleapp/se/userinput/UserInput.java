package com.consoleapp.se.userinput;

import com.consoleapp.se.point.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    Scanner command = new Scanner(System.in);

    ArrayList<Point> group1 = new ArrayList<>();
    ArrayList<Point> group2 = new ArrayList<>();
    ArrayList<Point> group3 = new ArrayList<>();
    ArrayList<Point> group4 = new ArrayList<>();

    public void inputHandle() {
        ArrayList<String> currentCommands = new ArrayList<>();
        currentCommands.add(" ");

        while (!currentCommands.get(0).equals("exit")) {
            String currentCommand = command.nextLine();
            currentCommands.clear();
            currentCommands.addAll(List.of(currentCommand.split(" ")));

            if (currentCommand.equals("help")) {
                printAvailableCommands();
            } else if (currentCommands.get(0).equals("add") && currentCommands.size() % 2 == 1) {
                addPoints(currentCommands);
            } else if (currentCommands.get(0).equals("print") && currentCommands.size() == 1) {
                printAllGroups();
            } else if (currentCommands.get(0).equals("print") && currentCommands.size() > 1) {
                printSpecificGroup(currentCommands);
            } else if (currentCommands.get(0).equals("remove") && currentCommands.size() > 1) {
                removeSpecificGroup(currentCommands);
            } else if (currentCommands.get(0).equals("clear") && currentCommands.size() == 1) {
                clearAllGroups();
            } else if (currentCommands.get(0).equals("exit")) {
                exitFromApp();
            } else {
                wrongCommand();
            }
        }

    }

    private void printAvailableCommands() {
        System.out.println("add <point> - добавить в память программы точки, координаты передаются парами чисел через пробел\n " +
                "print - напечатать построчно каждую из трех групп (входящие в них точки)\n" +
                "print <group_num> - напечатать одним списком точки, входящие в группу(ы) переданную(ые) параметром <group_num>)\n" +
                "remove <group_num> - удалить из памяти все точки, входящие в группу(ы) <group_num> \n" + "clear - очистить память\n" +
                "exit - выйти из программы");
    }

    private void addPoints(ArrayList<String> currentCommands) {
        for (int i = 1; i < currentCommands.size() - 1; i += 2) {

            Point point = new Point(Integer.parseInt(currentCommands.get(i)), Integer.parseInt(currentCommands.get(i + 1)));
            boolean validPointFlag = false;

            if (point.getX() - point.getY() >= 0) {
                group1.add(point);
                validPointFlag = true;
            }
            if (point.getX() * point.getX() - point.getY() >= 0) {
                group2.add(point);
                validPointFlag = true;
            }
            if (point.getX() * point.getX() * point.getX() - point.getY() >= 0) {
                group3.add(point);
                validPointFlag = true;
            }
            if (!validPointFlag) {
                group4.add(point);
            }
        }
    }

    private void printAllGroups() {
        if (group1.size() > 0) {
            System.out.println("First Group (y = x):\n" + group1);
        } else {
            System.out.println("First Group (y = x) is empty");
        }
        if (group2.size() > 0) {
            System.out.println("Second Group (y = x^2):\n" + group2);
        } else {
            System.out.println("Second Group (y = x^2) is empty");
        }
        if (group3.size() > 0) {
            System.out.println("Third Group (y = x^3):\n" + group3);
        } else {
            System.out.println("Third Group (y = x^3) is empty");
        }
        System.out.println("Number of points that are not included in any group: " + group4.size());
    }

    private void printSpecificGroup(ArrayList<String> currentCommands) {
        for (int i = 1; i < currentCommands.size(); i++) {
            if (currentCommands.get(i).equals("1")) {
                System.out.println("First Group (y = x):\n" + group1);
            }
            if (currentCommands.get(i).equals("2")) {
                System.out.println("Second Group (y = x^2):\n" + group2);
            }
            if (currentCommands.get(i).equals("3")) {
                System.out.println("Third Group (y = x^3):\n" + group3);
            }
        }
    }

    private void removeSpecificGroup(ArrayList<String> currentCommands) {
        for (int i = 1; i < currentCommands.size(); i++) {
            if (currentCommands.get(i).equals("1")) {
                group1.clear();
            }
            if (currentCommands.get(i).equals("2")) {
                group2.clear();
            }
            if (currentCommands.get(i).equals("3")) {
                group3.clear();
            }
        }
    }

    private void clearAllGroups() {
        group1.clear();
        group2.clear();
        group3.clear();
        group4.clear();
    }

    private void exitFromApp() {
        System.out.println("Good bye!");
    }

    private void wrongCommand() {
        System.out.println("Wrong command! Type 'help' for information about available commands.");
    }
}
