package kr.brian.study.common.logging;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SupportLogger {

    private static final Log LOG = LogFactory.getLog(SupportLogger.class);
    public static final String FQCN_KEY =  "SupportLogger.adapter.fqcn";

    private String moduleName;
    private SupportLoggerAdapter adapter;

    public SupportLogger(String moduleName, String name) {
        this.moduleName = moduleName;

        String fqcn = getSupportLoggerAdapterFQCN();
        if (StringUtils.isNotBlank(fqcn)) {
            try {
                adapter = (SupportLoggerAdapter) Class.forName(fqcn).newInstance();
                adapter.setName(name);
            } catch (InstantiationException e) {
                LOG.error("Unable to create instance of SupportLogger [" + fqcn + "] Creating default logger.", e);
            } catch (IllegalAccessException e) {
                LOG.error("Unable to create instance of SupportLogger [" + fqcn + "] Creating default logger.", e);
            } catch (ClassNotFoundException e) {
                LOG.error("Unable to create instance of SupportLogger [" + fqcn + "] Creating default logger.", e);
            }
        }

        if (adapter == null) {
            adapter = new SystemSupportLoggerAdapter();
            adapter.setName(name);
        }
    }

    /**
     * emit a SUPPORT level message
     * @param message
     */
    public void support(Object message) {
        adapter.support(moduleName + " - " + message);
    }

    /**
     * emit a SUPPORT level message with throwable
     * @param message
     * @param t
     */
    public void support(Object message, Throwable t) {
        adapter.support(moduleName + " - " + message, t);
    }

    /**
     * emit a SUPPORT lifecycle message
     * @param lifeCycleEvent
     * @param message
     */
    public void lifecycle(LifeCycleEvent lifeCycleEvent, Object message) {
        adapter.lifecycle(lifeCycleEvent, moduleName + " - " + lifeCycleEvent.toString() + (!StringUtils.isEmpty(message.toString())?" - " + message:""));
    }

    /**
     * In order to be backwards compatible. The support logger should also support
     * the debug, error, fatal, info, and warn levels as well.
     * @param message
     */

    public void debug(Object message) {
        adapter.debug(moduleName + " - " + message);
    }

    public void debug(Object message, Throwable t) {
        adapter.debug(moduleName + " - " + message, t);
    }

    public void error(Object message) {
        adapter.error(moduleName + " - " + message);
    }

    public void error(Object message, Throwable t) {
        adapter.error(moduleName + " - " + message, t);
    }

    public void fatal(Object message) {
        adapter.fatal(moduleName + " - " + message);
    }

    public void fatal(Object message, Throwable t) {
        adapter.fatal(moduleName + " - " + message, t);
    }

    public void info(Object message) {
        adapter.info(moduleName + " - " + message);
    }

    public void info(Object message, Throwable t) {
        adapter.info(moduleName + " - " + message, t);
    }

    public void warn(Object message) {
        adapter.warn(moduleName + " - " + message);
    }

    public void warn(Object message, Throwable t) {
        adapter.warn(moduleName + " - " + message, t);
    }

    public static String getSupportLoggerAdapterFQCN() {
        return System.getProperty(FQCN_KEY);
    }

}
