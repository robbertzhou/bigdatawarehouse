package chapter04;

import org.apache.commons.lang.math.NumberUtils;

/**
 * create 2020-11-08
 * author 周宇
 * desc flume日志过滤工具类
 */
public class LogUtils {
    public static boolean validateEvent(String log){
        // first split 切割
        String[] logContents = log.split("\\|");
        // second validate 校验
        if(logContents.length!=2){
            return false;
        }
        // third validate server datetime 校验服务器时间
        if(logContents[0].length()!=13 || !NumberUtils.isDigits(logContents[0])){
            return false;
        }
        //forth validate json
        if(!logContents[1].trim().startsWith("{") || !logContents[1].trim().endsWith("}")){
            return false;
        }
        return true;
    }

    public static boolean validateStart(String log){
        if (log ==null){
            return false;
        }
        if(!log.trim().startsWith("{") || !log.trim().endsWith("}")){
            return false;
        }
        return true;
    }
}
