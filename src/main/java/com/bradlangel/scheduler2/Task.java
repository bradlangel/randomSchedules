package com.bradlangel.scheduler2;

/**
 * User: blangel
 * Date: 4/13/13
 * Time: 4:32 PM
 */
public enum Task {

    Exercise("Exercise", 2),
    Breakfast("Breakfast", 2),
    Hygiene("Hygiene", 2),
    SetGoals("Set Goals", 1),
    ReadBook1("Read Book 1", 8),
    Journal("Journal", 4),
    GBMApp("GBMApp", 10),
    Japanese("Japanese", 4),
    Lunch("Lunch", 2),
    FreeBlock("Free Block", 6),
    CreativeWriting("Creative Writing", 4),
    Walk("Go for a walk", 2),
    ReadBook2("Read Book niban", 4),
    Math("do some math", 4),
    Music("listen to some music", 2),
    Library("Go to library", 2),
    Hangout("Go Hangout", 8),
    Internet("surf the web", 4),
    TV("Watch the blobtube", 4);

    private final String name;

    private final int weight;

    private Task(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

}
