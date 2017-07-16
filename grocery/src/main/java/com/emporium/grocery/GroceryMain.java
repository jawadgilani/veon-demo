/**
 * 
 */
package com.emporium.grocery;

import com.emporium.mall.util.LogGenerator;
import com.emporium.mall.util.UdfFuntions;
/**
 * @author sg186104 Jawad Gilani
 *
 */
import static com.emporium.mall.util.attributes.Grocery.*;


import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import static org.apache.spark.sql.functions.*;

/**
 * Created by SG186104 Jawad Gilani
 * <p>
 */
public class GroceryMain {

    public static void main(String[] args) {
    	        
    	Logger logger = LogGenerator.getLoggerInstance("Transformation");
    	logger.info(" Test Log ...");
    	
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
                .appName("Grocery Transformation")
                .enableHiveSupport()
                .getOrCreate();

        spark.udf().register(CODE.toString(), UdfFuntions.code, DataTypes.StringType);
        
    

        Dataset<Row> sourceDf = spark.sql(String.format("select * from %s %s", source, where));

        Dataset<Row> transformedDf;
        transformedDf = sourceDf.select(
        		col("name").as(NAME.val()),
              
                callUDF(CODE.val(), col("name"), col("id"),col("type"),col("quantity") ) 
                  
        );

        transformedDf.show();

        transformedDf.createOrReplaceTempView("Grocery");
        
        if (incremental) {
            spark.sql(String.format("insert into table %s select * from Grocery", target));
        } else {
            spark.sql(String.format("insert overwrite table %s select * from Grocery", target));
        }
        
     
      	spark.close();
    }
}

