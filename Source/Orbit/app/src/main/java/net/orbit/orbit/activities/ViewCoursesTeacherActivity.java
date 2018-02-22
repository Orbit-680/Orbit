package net.orbit.orbit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.orbit.orbit.models.pojo.Course;

import net.orbit.orbit.R;
import net.orbit.orbit.services.CourseService;

import java.util.ArrayList;
import java.util.List;

public class ViewCoursesTeacherActivity extends BaseActivity {
    private RecyclerView recyclerView;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, ViewCoursesTeacherActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_view_courses_teacher, relativeLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton mFabAddCourse = (FloatingActionButton) findViewById(R.id.fab_add_course);
        mFabAddCourse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("ViewCoursesTActivity", "We want to add a new Course.");
                Intent chooseCourseActivity = new Intent(ViewCoursesTeacherActivity.this, ChooseCourseActivity.class);
                ViewCoursesTeacherActivity.this.startActivity(chooseCourseActivity);
            }

        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this));

        Adapter.courses = new ArrayList<>();
        CourseService.getInstance().getAllCoursesAssignedToCurrentTeacher(this);




    }

    public void test()
    {

    }

    public void updateCourseList(List<Course> courseList){


        if(courseList.size() == 0){
            Log.i("ViewCourseActivity", "No courses found for teacher logged in.");
            Toast.makeText(this, "You have no courses." , Toast.LENGTH_SHORT).show();
            TextView noCoursesFound = (TextView)findViewById(R.id.noCoursesFound);
            noCoursesFound.setVisibility(View.VISIBLE);
            return;
        }

        Log.i("ViewCourseActivity", "Found courses associated with teacher logged in." + courseList);
        for (Course c : courseList){
            Adapter.courses.add(c);
        }
        reloadList();
    }

    public static void loadAssignments(int courseID)
    {

    }

    public void reloadList()
    {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<ViewCoursesTeacherActivity.ViewHolder> {

        private final Activity context;
        private static List<Course> courses = new ArrayList<>();

        public Adapter(Activity context) {
            this.context = context;
        }

        public static void addCourse(Course course)
        {
            courses.add(course);
        }

        @Override
        public ViewCoursesTeacherActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = context.getLayoutInflater().inflate(R.layout.course_item, parent, false);
            return new ViewCoursesTeacherActivity.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewCoursesTeacherActivity.ViewHolder holder, int position) {
            Course course = courses.get(position);

            holder.txtCourseName.setText(course.getName());

            //set created text info section
            //StringBuilder sb = new StringBuilder();

            //String testImage = "http://media2.s-nbcnews.com/j/streams/2013/june/130617/6c7911377-tdy-130617-leo-toasts-1.nbcnews-ux-2880-1000.jpg";
            //Glide.with(context).load(testImage).into(holder.memeImage);
        }

        @Override
        public int getItemCount() {
            return courses.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final ImageView iconImage;
        public final TextView txtCourseName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            iconImage = (ImageView) itemView.findViewById(R.drawable.ic_class_white_24px);
            txtCourseName = (TextView) itemView.findViewById(R.id.txtCourseName);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Course course = ViewCoursesTeacherActivity.Adapter.courses.get(position);
            Context context = itemView.getContext();
            Intent intent = ViewCourseAssignmentsActivity.createIntent(context, course.getCourseId());
            context.startActivity(intent);
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