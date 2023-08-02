package br.edu.icomp.plaintext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {
    private PasswordDAO passwordDAO;
    private int passwordId;
    private TextView editName, editLogin, editPassword, editNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editName = findViewById(R.id.addName);
        editLogin = findViewById(R.id.addLogin);
        editPassword = findViewById(R.id.addPassword);
        editNotes = findViewById(R.id.addNotes);
        passwordDAO = new PasswordDAO(this);

        Intent intent = getIntent();
        passwordId = intent.getIntExtra("passwordId", -1);

        // Verifica se uma senha foi passada como parâmetro
        if (passwordId != -1) {
            Password password = passwordDAO.get(passwordId);
            editName.setText(password.getName());
            editLogin.setText(password.getLogin());
            editPassword.setText(password.getPassword());
            editNotes.setText(password.getNotes());
        }
    }

    public void salvarClicado(View view) {
        Password password = new Password(passwordId, editName.getText().toString(),
                editLogin.getText().toString(), editPassword.getText().toString(),
                editNotes.getText().toString());

        boolean result;
        if (passwordId == -1) result = passwordDAO.add(password);
        else result = passwordDAO.update(password);

        if (result) finish();
    }
}
