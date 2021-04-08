package com.klasevich.cms.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class CommandProvider {
    public static final Logger logger = LogManager.getLogger();

    public static Optional<Command> defineCommand(String commandName) {
        Optional<Command> current;
        if (commandName == null || commandName.isBlank()) {
            return Optional.empty();
        }
        try {
            logger.debug("commandName {}", commandName);
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCurrentCommand());
            logger.debug("Command is defined as {}", type);
        } catch (IllegalArgumentException e) {
            logger.error("Unsupported command", e);
            current = Optional.empty();
        }
        return current;
    }
}
