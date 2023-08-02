package br.edu.icomp.plaintext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PasswordsAdapter extends RecyclerView.Adapter<PasswordsViewHolder> {
    private Context context;
    private ArrayList<Password> passwords;
    PasswordDAO passwordDAO;

    public PasswordsAdapter(Context context) {
        this.context = context;
        passwordDAO = new PasswordDAO(context);
        update();
    }

    public void update() { passwords = passwordDAO.getList(); }

    public PasswordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        PasswordsViewHolder vh = new PasswordsViewHolder(v, context);
        return vh;
    }

    public void onBindViewHolder(PasswordsViewHolder holder, int position) {
        holder.name.setText(passwords.get(position).getName());
        holder.login.setText(passwords.get(position).getLogin());
        holder.id = passwords.get(position).getId();
    }

    public int getItemCount() { return passwords.size(); }
}
