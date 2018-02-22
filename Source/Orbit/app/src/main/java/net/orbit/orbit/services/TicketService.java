package net.orbit.orbit.services;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.JsonHttpResponseHandler;

import net.orbit.orbit.activities.HomeActivity;
import net.orbit.orbit.models.pojo.Ticket;
import net.orbit.orbit.utils.Constants;
import net.orbit.orbit.utils.OrbitRestClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by sristic on 2/19/18.
 */

public class TicketService {
    // Creates a singleton
    private TicketService() { }

    private static TicketService _ticketService;

    public static TicketService getInstance(){
        if (_ticketService == null){
            _ticketService = new TicketService();
        }
        return _ticketService;
    }
    private Context context;

    public TicketService(Context context){
        this.context = context;
    }

    public void addTicket(Ticket ticket){
        Gson gson = new Gson();
        String json = gson.toJson(ticket);
        StringEntity entity = null;
        try {
            entity = new StringEntity(json.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Sets the URL for the API url
        OrbitRestClient.getInstance().setBaseUrl(PropertiesService.getInstance().getProperty(this.context, Constants.ORBIT_API_URL));
        OrbitRestClient.getInstance().post(this.context, "add-ticket", entity, "application/json",
                new JsonHttpResponseHandler(){
                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject ticket) {
                        // called when success happens
                        Log.i("TicketService", "Successfully added new ticket: " + ticket);
                        Toast.makeText(context, "Ticket successfully submitted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject errorResponse) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        Log.e("TicketService", "Error when adding new ticket: " + errorResponse);
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                    }
                });
    }
}
