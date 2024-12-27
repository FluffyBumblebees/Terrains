package net.stockieslad.magical_utilities.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class ChainLogger {
    private final SideEffect
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

    private ChainLogger base(final SideEffect type, final String[] messages) {
        for (final String message : messages)
            type.action(message);
        return this;
    }

    public ChainLogger log(SideEffect sideEffect, final String... messages) {
        return base(sideEffect, messages);
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

    public interface SideEffect {
        void action(final String message);
    }
}
