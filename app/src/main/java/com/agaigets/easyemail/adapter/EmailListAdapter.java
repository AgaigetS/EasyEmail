package com.agaigets.easyemail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agaigets.easyemail.R;
import com.agaigets.easyemail.email.Email;

import java.util.List;

public class EmailListAdapter extends RecyclerView.Adapter<EmailListAdapter.EmailListViewHolder> {

    private List<Email> list=null;

    public EmailListAdapter(List<Email> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public EmailListAdapter.EmailListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.email_list_fragment, parent, false);
        EmailListViewHolder holder = new EmailListViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EmailListAdapter.EmailListViewHolder holder, int position) {
        Email email = list.get(position);
        holder.tv_from.setText(email.getFrom());
        holder.tv_short_content.setText(email.getContent());
        holder.tv_subject.setText(email.getSubject());
        holder.tv_time.setText(email.getTime().toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EmailListViewHolder extends RecyclerView.ViewHolder {

        public final TextView tv_from;
        public final TextView tv_time;
        public final TextView tv_short_content;
        public final TextView tv_subject;


        public EmailListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_from = (TextView) itemView.findViewById(R.id.tv_from);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_short_content = (TextView) itemView.findViewById(R.id.tv_short_content);
            tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
        }
    }
}
