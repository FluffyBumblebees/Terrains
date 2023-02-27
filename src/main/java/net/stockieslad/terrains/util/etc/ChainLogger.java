package net.stockieslad.terrains.util.etc;

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

    private ChainLogger base(final String[] messages, final SideEffect type) {
        for (final String message : messages)
            type.action(message);
        return this;
    }

    public ChainLogger debug(final String... messages) {
        return base(messages, DEBUG);
    }

    public ChainLogger error(final String... messages) {
        return base(messages, ERROR);
    }

    public ChainLogger info(final String... messages) {
        return base(messages, INFO);
    }

    public ChainLogger shout(final String... messages) {
        return base(messages, SHOUT);
    }

    public ChainLogger trace(final String... messages) {
        return base(messages, TRACE);
    }

    public ChainLogger warn(final String... messages) {
        return base(messages, WARN);
    }

    public interface SideEffect {
        void action(final String message);
    }
}
