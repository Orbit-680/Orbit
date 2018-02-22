package net.orbit.orbit.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import net.orbit.orbit.R;
import net.orbit.orbit.models.pojo.Ticket;
import net.orbit.orbit.models.pojo.User;
import net.orbit.orbit.services.TicketService;
import net.orbit.orbit.utils.Constants;
import net.orbit.orbit.utils.OrbitUserPreferences;

import java.util.ArrayList;

/**
 * Created by sristic on 2/19/18.
 */
public class ReportABugActivity extends BaseActivity {
    EditText issueName;
    EditText issueDescription;
    Spinner issuePrioritySpinner;
    TicketService ticketService = new TicketService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_a_bug);

        getLayoutInflater().inflate(R.layout.activity_view_course, relativeLayout);

        issueName = (EditText)findViewById(R.id.issueName);
        issueDescription = (EditText)findViewById(R.id.issueDescription);
        issuePrioritySpinner = (Spinner)findViewById(R.id.issuePriority);
        CardView submitIssueBtn = (CardView) findViewById(R.id.cardView);

        // Populating issue spinner
        ArrayList<String> issuePriorityList = new ArrayList<>();
        issuePriorityList.add(Constants.ISSUE_LOW_PRIORITY);
        issuePriorityList.add(Constants.ISSUE_MEDIUM_PRIORITY);
        issuePriorityList.add(Constants.ISSUE_HIGH_PRIORITY);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, issuePriorityList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        issuePrioritySpinner.setAdapter(dataAdapter);

        // On submitting the form
        submitIssueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitAnIssue();
            }
        });
    }

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, EnrollStudentInCourseActivity.class);
        return i;
    }

    private void submitAnIssue(){
        String name = issueName.getText().toString();
        String description = issueDescription.getText().toString();
        String priority = (String) ( (Spinner) findViewById(R.id.issuePriority) ).getSelectedItem();

        boolean proceed = true;

        // Validation
        if (TextUtils.isEmpty(name)) {
            issueName.setError(getString(R.string.error_field_required));
            proceed = false;
        }

        if (TextUtils.isEmpty(description)) {
            issueDescription.setError(getString(R.string.error_field_required));
            proceed = false;
        }

        if (proceed) {
            OrbitUserPreferences orbitPref = new OrbitUserPreferences(this);
            User user = orbitPref.getUserPreferenceObj("loggedUser");
            Ticket ticket = new Ticket(name, description, priority, user);
            ticketService.addTicket(ticket);
        }
    }

}
