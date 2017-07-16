/**
 * 
 */
package com.emporium.mall.util;

/**
 * @author sg186104
 *
 */
import org.apache.log4j.BasicConfigurator;

import org.apache.log4j.Logger;


public class LogGenerator {

    private LogGenerator() {
        BasicConfigurator.configure();
    }


   
    
    public static Logger getLoggerInstance(String appLoggerName) {
        return Logger.getLogger(appLoggerName);
    }

}
