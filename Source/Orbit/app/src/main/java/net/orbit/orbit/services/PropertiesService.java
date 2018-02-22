package net.orbit.orbit.services;

import android.content.Context;

import net.orbit.orbit.utils.Constants;
import net.orbit.orbit.utils.PropertyReader;

import java.util.Properties;

/**
 * Created by brocktubre on 11/5/17.
 */

public class PropertiesService {
    // Creates a singleton of the TeacherService
    private PropertiesService() { }

    private static PropertiesService   _propertiesService;

    public static PropertiesService getInstance(){
        if (_propertiesService == null){
            _propertiesService = new PropertiesService();
        }
        return _propertiesService;
    }

    private Context context;

    public PropertiesService(Context context){
        this.context = context;
    }

    public PropertyReader propertyReader;
    public Properties properties;

    public String getProperty(Context context, String propName) {
        this.context = context;
        this.propertyReader = new PropertyReader(context);
        this.properties = this.propertyReader.getMyProperties(Constants.APP_PROPERTIES);
        return this.properties.getProperty(propName);
    }
}
