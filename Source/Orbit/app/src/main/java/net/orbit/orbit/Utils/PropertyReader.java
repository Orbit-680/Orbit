package net.orbit.orbit.utils;

import android.content.Context;
import android.content.res.AssetManager;

import net.orbit.orbit.services.StudentService;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by brocktubre on 11/4/17.
 */

public class PropertyReader {

    // Creates a singleton
    private PropertyReader() { }

    private static PropertyReader _propertyReader;

    public static PropertyReader getInstance(){
        if (_propertyReader == null){
            _propertyReader = new PropertyReader();
        }
        return _propertyReader;
    }
    private Context context;
    private Properties properties;

    public PropertyReader(Context context){
        this.context=context;
        properties = new Properties();
    }

    public Properties getMyProperties(String file){
        try{
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(file);
            properties.load(inputStream);

        }catch (Exception e){
            System.out.print(e.getMessage());
        }

        return properties;
    }
}
