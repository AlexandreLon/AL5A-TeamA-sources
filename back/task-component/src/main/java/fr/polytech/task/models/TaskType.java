package fr.polytech.task.models;

public enum TaskType {
    VERIFICATION("verification"),
    CLEANING("cleaning"),
    REPLACING("replacing"),
    ;

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
