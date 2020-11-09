package chapter04;


import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * create 2020-11-08
 * author 周宇
 */
public class LogETLInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //get data
        byte[] body = event.getBody();
        String log = new String(body, Charset.forName("UFT-8"));
        //is illeage
        if(log.contains("start")){
            if(LogUtils.validateStart(log)){
                return event;
            }
        }else{
            if(LogUtils.validateEvent(log)){
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        List<Event> interceptors = new ArrayList<>();
        for (Event event:list){
            Event intercept1 = intercept(event);
            if(intercept1!=null){
                interceptors.add(intercept1);
            }
        }
        return interceptors;
    }

    @Override
    public void close() {

    }
}
