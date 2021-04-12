package com.klasevich.cms.command;

import static com.klasevich.cms.command.command_parameter.PagePath.INDEX;

public class CommandResult {

    private String path;
    private Type type;

    public enum Type {
        FORWARD,
        REDIRECT,
        RETURN_WITH_REDIRECT,
    }

    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public CommandResult(String path) {
        this.path = path;
        this.type = Type.FORWARD;
    }

    public CommandResult(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String providePath() {
        String result;
        if (path == null || path.isBlank()) {
            result = INDEX;
        } else {
            result = path;
        }
        return result;
    }
}
