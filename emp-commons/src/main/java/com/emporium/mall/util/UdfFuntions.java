/**
 * 
 */
package com.emporium.mall.util;

/**
 * @author sg186104
 *
 */
import org.apache.spark.sql.api.java.UDF3;

/**
 * Created by ll186048 on 28/06/2017.
 * <p>
 * Collection of utility UDFs
 */
public class UdfFuntions {

    /**
     * Derive location from DPI table data
     */
    public static UDF3<String, String, String, String> code = new UDF3<String, String, String, String>() {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
         * Derive location from DPI table data
         *
         * @param cgi
         * @param sai
         * @param ecgi
         * @return
         * @throws Exception
         */
        public String call(String cgi, String sai, String ecgi) throws Exception {
            if (cgi != null && cgi.length() >= 14) {

                String lacid = Integer.valueOf(cgi.substring(6, 10), 16).toString();
                String cellid = String.format("%05d", Integer.valueOf(cgi.substring(10, 14), 16));

                return lacid + cellid.substring(1, 5);

            } else if (sai != null && sai.length() >= 14) {

                String lacid = Integer.valueOf(sai.substring(6, 10), 16).toString();
                String cellid = String.format("%04d", Integer.valueOf(sai.substring(10, 14), 16));

                return lacid + cellid.substring(0, 4);

            } else if (ecgi != null && ecgi.length() >= 11) {

                String cellid = Integer.valueOf(ecgi.substring(6, 11), 16).toString();

                return cellid;
            } else return null;
        }
    };
}