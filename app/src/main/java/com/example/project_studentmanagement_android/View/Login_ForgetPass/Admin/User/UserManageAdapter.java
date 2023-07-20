package com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.project_studentmanagement_android.Model.Classes;
import com.example.project_studentmanagement_android.Model.User;
import com.example.project_studentmanagement_android.R;
import com.example.project_studentmanagement_android.View.Login_ForgetPass.Admin.Classes.ClassesAdapter;

import java.util.List;

public class UserManageAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<User> UserList;

    public UserManageAdapter(Context context, int layout, List<User> userlist) {
        this.context = context;
        this.layout = layout;
        UserList = userlist;
    }

    @Override
    public int getCount() {
        return UserList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Holder
    {
        TextView edtusername, edtnote;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserManageAdapter.Holder holder = new UserManageAdapter.Holder();
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.edtusername = (TextView) convertView.findViewById(R.id.edtUserName);
            holder.edtnote = (TextView) convertView.findViewById(R.id.edtNoteUser);

            convertView.setTag(holder);
        }
        else {
            holder = (UserManageAdapter.Holder) convertView.getTag();
        }

        User user = UserList.get(position);

        holder.edtusername.setText(user.getUsername());
        holder.edtnote.setText("Role: "+ user.getRole());

        return convertView;
    }
}
