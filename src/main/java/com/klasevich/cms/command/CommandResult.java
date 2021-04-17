package com.klasevich.cms.command;

import static com.klasevich.cms.command.command_parameter.PagePath.INDEX;

/**
 * @author Tatsiana Klasevich
 * The class contains types of command result.
 */
public class CommandResult {

    private String path;
    private Type type;

    /**
     * Enumeration of command result returned types.
     */
    public enum Type {
        /**
         * Represents the return type of a forward.
         */
        FORWARD,
        /**
         * Represents the return type of a redirect.
         */
        REDIRECT,
    }

    /**
     * Instantiates a new Command result.
     *
     * @param path page path
     * @param type the return type
     */
    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Instantiates a new Command result.
     *
     * @param path page path
     */
    public CommandResult(String path) {
        this.path = path;
        this.type = Type.FORWARD;
    }

    /**
     * Instantiates a new Command result.
     *
     * @param type the return type
     */
    public CommandResult(Type type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the return type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Provide path string.
     *
     * @return ready page path, if page doesn't exist page path is default
     */
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
