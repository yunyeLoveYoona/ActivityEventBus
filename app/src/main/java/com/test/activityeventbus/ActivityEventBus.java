package com.test.activityeventbus;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dell on 2018/4/19.
 */

public class ActivityEventBus implements Application.ActivityLifecycleCallbacks {
    private static ActivityEventBus instance;
    private HashMap<String, ArrayList<IEventData>> eventMap;

    public ActivityEventBus() {
        eventMap = new HashMap<String, ArrayList<IEventData>>();
    }

    public static ActivityEventBus getInstance() {
        if (instance == null) {
            instance = new ActivityEventBus();
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (eventMap.get(activity.getClass().getName()) != null) {
            Method method = null;
            try {
                method = activity.getClass().getMethod("onEvent", IEventData.class);
                for (IEventData eventData : eventMap.get(activity.getClass().getName())) {
                    method.invoke(activity, eventData);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            eventMap.remove(activity.getClass().getName());
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    public void addEvent(String activityClassName, IEventData iEventData) {
        if (eventMap.get(activityClassName) == null) {
            ArrayList<IEventData> arrayList = new ArrayList<IEventData>();
            arrayList.add(iEventData);
            eventMap.put(activityClassName, arrayList);
        } else {
            eventMap.get(activityClassName).add(iEventData);
        }

    }

    public void removeEvent(String activityClassName) {
        eventMap.remove(activityClassName);
    }
}
