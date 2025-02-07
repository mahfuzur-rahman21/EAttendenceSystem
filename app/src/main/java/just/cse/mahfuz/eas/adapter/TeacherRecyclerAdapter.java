package just.cse.mahfuz.eas.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import just.cse.mahfuz.eas.activity.AssignCourseActivity;
import just.cse.mahfuz.eas.R;
import just.cse.mahfuz.eas.model.Teacher;

public class TeacherRecyclerAdapter extends RecyclerView.Adapter<TeacherRecyclerAdapter.teacherViewHolder> {

    Context context;
    List<Teacher> teacherList;

    String sName,sDesignation,sEmail,sImage,sShortName;
    String myunit,mydepartment;
    public TeacherRecyclerAdapter() {
    }

    public TeacherRecyclerAdapter(Context context, List<Teacher> teacherList,String myunit,String mydepartment) {
        this.context = context;
        this.teacherList = teacherList;
        this.myunit = myunit;
        this.mydepartment = mydepartment;

    }

    @NonNull
    @Override
    public teacherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.row_teacher, viewGroup, false);
        return new teacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final teacherViewHolder teacherViewHolder, int i) {
        sName= teacherList.get(i).getName();
        sDesignation= teacherList.get(i).getDesignation();
        sEmail= teacherList.get(i).getEmail();
        sImage= teacherList.get(i).getImage();
        sShortName= teacherList.get(i).getShortName();


        if (!"".equals(sImage) && sImage!=null) {
            Glide.with(context)
                    .load(sImage)
                    //.override(80, 80)
                    //.thumbnail(0.1f)
                    .into(teacherViewHolder.image);
        }

        teacherViewHolder.name.setText(sName);
        teacherViewHolder.designation.setText(sDesignation);
        teacherViewHolder.email.setText(sEmail);
        teacherViewHolder.shortName.setText(sShortName);

        teacherViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String  shortName= teacherViewHolder.shortName.getText().toString();
                Intent intent= new Intent(context, AssignCourseActivity.class);
                intent.putExtra("shortName",shortName);
                intent.putExtra("department",mydepartment);
                intent.putExtra("unit",myunit);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public class teacherViewHolder extends RecyclerView.ViewHolder{

        CircleImageView image;
        TextView name,designation,email,shortName;
        LinearLayout linearLayout;
        public teacherViewHolder(@NonNull View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            designation=itemView.findViewById(R.id.designation);
            email=itemView.findViewById(R.id.email);
            shortName=itemView.findViewById(R.id.shortName);

            linearLayout=itemView.findViewById(R.id.linearLayout);
        }
    }
}
