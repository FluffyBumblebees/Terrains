package net.stockieslad.magical_utilities.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class ChainLogger {
    private final Consumer<String>
            DEBUG,
            ERROR,
            INFO,
            SHOUT,
            TRACE,
            WARN;

    public ChainLogger(final String loggerName) {
        final Logger internalLogger = LoggerFactory.getLogger(loggerName);
        DEBUG = internalLogger::debug;
        ERROR = internalLogger::error;
        INFO = internalLogger::info;
        SHOUT = message -> System.out.println("(" + loggerName + "): " + message);
        TRACE = internalLogger::trace;
        WARN = internalLogger::warn;
    }

    private ChainLogger base(final Consumer<String> type, final String[] messages) {
        for (final String message : messages)
            type.accept(message);
        return this;
    }

    public ChainLogger log(Consumer<String> type, final String... messages) {
        return base(type, messages);
    }

    public ChainLogger debug(final String... messages) {
        return log(DEBUG, messages);
    }

    public ChainLogger error(final String... messages) {
        return log(ERROR, messages);
    }

    public ChainLogger info(final String... messages) {
        return log(INFO, messages);
    }

    public ChainLogger shout(final String... messages) {
        return log(SHOUT, messages);
    }

    public ChainLogger trace(final String... messages) {
        return log(TRACE, messages);
    }

    public ChainLogger warn(final String... messages) {
        return log(WARN, messages);
    }
}
