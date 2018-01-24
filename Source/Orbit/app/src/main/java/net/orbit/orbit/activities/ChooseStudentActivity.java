package net.orbit.orbit.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.orbit.orbit.R;
import net.orbit.orbit.models.Student;
import net.orbit.orbit.services.StudentService;
import net.orbit.orbit.utils.OrbitUserPreferences;

import java.util.ArrayList;
import java.util.List;

public class ChooseStudentActivity extends BaseActivity {
    private RecyclerView recyclerView;
    StudentService studentService = new StudentService(this);

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, ChooseStudentActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_choose_student);

        //need to inflate this activity inside the relativeLayout inherited from BaseActivity.  This will add this view to the mainContent layout
        getLayoutInflater().inflate(R.layout.activity_choose_student, relativeLayout);

        //get UID of current user
        OrbitUserPreferences orbitPref = new OrbitUserPreferences(getApplicationContext());
        String uid = orbitPref.getUserPreference("userUID");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter(this));

        studentService.findLinkedStudents(this, uid);

    }

    public void updateStudentList(List<Student> studentList)
    {
        for(int i = 0; i < studentList.size(); i++)
        {
            Adapter.students.add((Student)studentList.get(i));
        }

        reloadList();
    }

    public void reloadList()
    {
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private final Activity context;
        private static List<Student> students = new ArrayList<>();

        public Adapter(Activity context) {
            this.context = context;

            /*if(students.size() <= 0) {
                //inital seed of list if needed
            }*/
        }

        public static void addStudent(Student student)
        {
            students.add(student);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = context.getLayoutInflater().inflate(R.layout.student_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Student student = students.get(position);

            holder.txtStudentName.setText(student.getStudentFirstName() + " " + student.getStudentLastName());

            //set created text info section
            StringBuilder sb = new StringBuilder();

            //String testImage = "http://media2.s-nbcnews.com/j/streams/2013/june/130617/6c7911377-tdy-130617-leo-toasts-1.nbcnews-ux-2880-1000.jpg";
            //Glide.with(context).load(testImage).into(holder.memeImage);
        }

        @Override
        public int getItemCount() {
            return students.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public final ImageView memeImage;
        public final TextView txtStudentName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            memeImage = (ImageView) itemView.findViewById(R.drawable.default_student);
            txtStudentName = (TextView) itemView.findViewById(R.id.txtStudentName);
        }

        @Override
        public void onClick(View v) {

            /*TextView top = (TextView) itemView.findViewById(R.id.txtTop);
            TextView bottom = (TextView) itemView.findViewById(R.id.txtBottom);
            TextView url = (TextView) itemView.findViewById(R.id.txtURLString);

            String text = (String) top.getText();

            Context context = itemView.getContext();
            context.startActivity(MemeCloseUpActivity.createIntent(
                    context, (String) top.getText(), (String) bottom.getText(), (String) url.getText()));*/

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