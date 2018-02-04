package net.orbit.orbit.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by brocktubre on 1/31/18.
 */

public class SecurityService {

    public String getCurrentUsersUid(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String UID = currentUser.getUid();
        return UID;
    }
}
