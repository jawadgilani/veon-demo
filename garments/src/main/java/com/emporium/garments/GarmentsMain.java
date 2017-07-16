/**
 * 
 */
package com.emporium.garments;

/**
 * @author sg186104
 *
 */
/**
 * 
 */

import com.emporium.mall.util.LogGenerator;
import com.emporium.mall.util.UdfFuntions;
import static com.emporium.mall.util.attributes.Garments.*;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import static org.apache.spark.sql.functions.*;


public class GarmentsMain {

    
    public static void main(String[] args) {
    	        
    	Logger logger = LogGenerator.getLoggerInstance("Transformation");
    	logger.info("Test Log ...");
    	
    	if (args.length < 3) {
            System.out.println("Usage: spark-submit");

            System.exit(1);
        }

        String source = args[0]; 
        String target = args[1]; 
        String timestamp = args[2]; 
        String where = "";
        boolean incremental = false;

        if (args.length == 4) {
            incremental = Boolean.valueOf(args[3]);
        }

        if (args.length == 5) {
            where = String.format("where %s", args[4]);
        }

        SparkSession spark = SparkSession
        		.builder()
                .appName("Garments Transformation")
                .enableHiveSupport()
                .getOrCreate();

        spark.udf().register(CODE.toString(), UdfFuntions.code, DataTypes.StringType);
        
    

        Dataset<Row> sourceDf = spark.sql(String.format("select * from %s %s", source, where));

        Dataset<Row> transformedDf;
        transformedDf = sourceDf.select(
        		col("tag").as(TAG.val()),
              
                callUDF(CODE.val(), col("Tag"), col("id"),col("type"),col("quantity") ) 
                  
        );

        transformedDf.show();

        transformedDf.createOrReplaceTempView("Garments");
        
        if (incremental) {
            spark.sql(String.format("insert into table %s select * from Garments", target));
        } else {
            spark.sql(String.format("insert overwrite table %s select * from Garments", target));
        }
        
        //Very Important; Close Spark Resources ...
      	spark.close();
    }
}

