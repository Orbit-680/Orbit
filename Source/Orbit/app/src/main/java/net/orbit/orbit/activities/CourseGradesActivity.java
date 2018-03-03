package net.orbit.orbit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.orbit.orbit.R;
import net.orbit.orbit.models.pojo.CourseGrade;
import net.orbit.orbit.models.pojo.Grade;
import net.orbit.orbit.services.GradeService;
import net.orbit.orbit.utils.OrbitUserPreferences;

import java.util.ArrayList;
import java.util.List;

public class CourseGradesActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private static int studentID = 0;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, CourseGradesActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if student ID has not been set then the user must select a student to view grades
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(this);
        String id = orbitPref.getStringPreference("chosenStudentID");

        if(id == null || id.equals(""))
        {
            Intent intent = ChooseStudentActivity.createIntent(this);
            this.startActivity(intent);
        }
        else
            CourseGradesActivity.studentID = Integer.parseInt(id);

        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_course_grades, relativeLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CourseGradesActivity.Adapter(this));

        /*FloatingActionButton mFabAddCourse = (FloatingActionButton) findViewById(R.id.fab_add_assignment);
        mFabAddCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("CreateAssignment", "We want to add a new Assignment.");
                Intent chooseCourseActivity = CreateAssignmentActivity.createIntent(CourseGradesActivity.this, CourseGradesActivity.courseID);
                CourseGradesActivity.this.startActivity(chooseCourseActivity);
            }
        });

        FloatingActionButton mFabEnrollStudents = (FloatingActionButton) findViewById(R.id.fab_enroll_students);
        mFabEnrollStudents.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("EnrollStudents", "We want to add a new Assignment.");
                Intent enrollStudentActivity = EnrollStudentInCourseActivity.createIntent(CourseGradesActivity.this, CourseGradesActivity.courseID);
                CourseGradesActivity.this.startActivity(enrollStudentActivity);
            }
        });*/

        GradeService gradeService = new GradeService(this);
        gradeService.getCourseGrades(this, studentID);
    }

    public void updateCourseGradeList(List<CourseGrade> courseGradeList)
    {
        for(CourseGrade g : courseGradeList)
        {
            CourseGradesActivity.Adapter.grades.add(g);
        }

        reloadList();
    }

    public void reloadList()
    {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<CourseGradesActivity.ViewHolder> {

        private final Activity context;
        private static List<CourseGrade> grades = new ArrayList<>();

        public Adapter(Activity context) {
            this.context = context;
            grades.clear();
        }

        public static void addGrade(CourseGrade grade)
        {
            grades.add(grade);
        }

        @Override
        public CourseGradesActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = context.getLayoutInflater().inflate(R.layout.course_grade_item, parent, false);
            return new CourseGradesActivity.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CourseGradesActivity.ViewHolder holder, int position) {
            CourseGrade grade = grades.get(position);

            holder.txtCourseName.setText(grade.getCourse().getName());
            holder.txtGrade.setText(grade.getCalcGrade());

            /*if(grade.getIsSelected())
                holder.itemView.setBackgroundColor(Color.parseColor("#90CAF9"));
            else
                holder.itemView.setBackgroundColor(Color.WHITE);*/
        }

        @Override
        public int getItemCount() {
            return grades.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final TextView txtCourseName;
        public final TextView txtGrade;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            txtCourseName = (TextView) itemView.findViewById(R.id.txtCourseName);
            txtGrade = (TextView) itemView.findViewById(R.id.txtGrade);
        }

        @Override
        public void onClick(View v) {
            /*int position = getAdapterPosition();
            int assignmentID = CourseGradesActivity.Adapter.assignments.get(position).getAssignmentId();

            Context context = itemView.getContext();
            Intent intent = ViewAssignmentGradesActivity.createIntent(context, courseID, assignmentID);
            context.startActivity(intent);*/

        }

        @Override
        public boolean onLongClick(View v) {
            /*int position = getAdapterPosition();
            String top = Adapter.memes.get(position).getTxtTop();
            String bottom = Adapter.memes.get(position).getTxtBottom();
            String url = Adapter.memes.get(position).getMemeURL();

            Context context = itemView.getContext();
            int TEST = 0;

            context.startActivity(EditMeme.createIntent(
                    context, Adapter.memes, position, itemView, top, bottom, url));*/

            return false;
        }

    }

}
