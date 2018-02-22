package net.orbit.orbit.services;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import net.orbit.orbit.activities.BaseActivity;
import net.orbit.orbit.activities.LoginActivity;
import net.orbit.orbit.utils.OrbitUserPreferences;

/**
 * Created by sristic on 1/31/18.
 */

public class LogoutService extends BaseActivity{

    // Creates a singleton
    private LogoutService() { }

    private static LogoutService _logoutService;

    public static LogoutService getInstance(){
        if (_logoutService == null){
            _logoutService = new LogoutService();
        }
        return _logoutService;
    }

    private Context context;

    public LogoutService(Context context){
        this.context = context;
    }

    public void logout(){
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(context);
        FirebaseAuth.getInstance().signOut();
        orbitPref.clear();
        context.startActivity(LoginActivity.createIntent(context));
    }
}
