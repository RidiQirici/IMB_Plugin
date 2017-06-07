package imb.ridiqirici.plugin.cordova.universal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.util.LinkedList;
import java.util.List;

import rego.printlib.export.regoPrinter;

public class ApplicationContext extends Application {
    private regoPrinter printer;
    private int myState = 0;
    private String printName = "RG-MTP58B";
    private int alignTypetext;
    private static Context context;
    private boolean labelmark = true;
    private static ApplicationContext instance = null;

    public static ApplicationContext getInstance() {
        if (null == instance) {
            instance = new ApplicationContext();
        }
        return instance;

    }
    public ApplicationContext() {
    }
    
    public static Context getContext() {
        return context;
    }

    public regoPrinter getObject() {
        return printer;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        context = getApplicationContext();
        instance = this;
    }

    public int getAlignType() {
        return alignTypetext;
    }

    public void setObject() {
        printer = new regoPrinter(this);
    }

    public String getName() {
        return printName;
    }

    public void setName(String name) {
        printName = name;
    }

    public void setState(int state) {
        myState = state;
    }

    public int getState() {
        return myState;
    }

}
