package io.github.mkanev.common;

import org.apache.commons.lang.builder.StandardToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * @author Maksim Kanev
 */
public class LoggedClass implements Serializable {

    private static final long serialVersionUID = 2163919862005646481L;
    private static final transient StandardToStringStyle style = new StandardToStringStyle();

    static {
        style.setFieldSeparator(", ");
        style.setUseClassName(false);
        style.setUseIdentityHashCode(false);
    }

    private static final LoggedClass INSTANCE = new LoggedClass();
    private transient Logger log;

    public LoggedClass() {
        log = Logger.getLogger(getClass().getName());
    }

    protected LoggedClass(Class<?> c) {
        log = Logger.getLogger(c);
    }

    public static LoggedClass getStaticInstance() {
        return INSTANCE;
    }

    public static LoggedClass newInstance(Class<?> c) {
        return new LoggedClass(c);
    }

    public Logger getLogger() {
        return log;
    }

    public void logDebug(String msg) {

        if (log.isDebugEnabled()) {
            log.debug(msg);
        }
    }

    public void logDebug(String msg, Exception e) {
        if (log.isDebugEnabled()) {
            log.debug(msg, e);
        }
    }

    public void logDebug(Exception e) {

        if (log.isDebugEnabled()) {
            log.debug(e.getLocalizedMessage(), e);
        }
    }

    public ToStringBuilder getNewProtocolBuilder() {
        return new ToStringBuilder(this, style);
    }

}