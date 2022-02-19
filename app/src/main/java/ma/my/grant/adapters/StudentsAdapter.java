package ma.my.grant.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import ma.my.grant.R;
import ma.my.grant.activities.SingleStudentView;
import ma.my.grant.models.Student;
import ma.my.grant.utilities.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    private final Context context;
    private final List<Student> list;

    public StudentsAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_student, parent, false);
        return new ViewHolder(context, v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(list.get(position));
        holder.view.setTag(list.get(position));
        holder.delete.setTag(list.get(position));
        Student element = list.get(position);

        holder.title.setText(element.getFirstName() + " " + element.getLastName());
        holder.description.setText(element.getCne());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public Button view, delete;

        public ViewHolder(final Context context, View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            view = itemView.findViewById(R.id.view);
            delete = itemView.findViewById(R.id.delete);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Student element = (Student) view.getTag();
                    viewElement(element);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Student element = (Student) view.getTag();
                    deleteElement(element);
                }
            });


        }


        public void deleteElement(final Student element) {
            try {
                String query = "DELETE FROM students WHERE id_s = ?";
                PreparedStatement preparedStatement = Database.connection.prepareStatement(query);
                preparedStatement.setInt(1, element.getId());
                int result = preparedStatement.executeUpdate();
                if (result == 1) {
                    int position = list.indexOf(element);
                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());
                    Toast.makeText(context, "The operation completed successfully !", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "An error occurred, please try again !", Toast.LENGTH_SHORT).show();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void viewElement(final Student element) {
            //TODO
            Intent intent = new Intent(context, SingleStudentView.class);
            intent.putExtra("student_id", element.getId());
            context.startActivity(intent);
        }


    }
}
