package rw.rutaks.snypet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.rutaks.snypet.R;
import rw.rutaks.snypet.utils.Validations;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    //VIEW ELEMENTS
    @BindView(R.id.edit_text_email_register) EditText editTextEmail;
    @BindView(R.id.edit_text_password_register) EditText editTextPassword;
    @BindView(R.id.edit_text_confirm_password_register) EditText editTextConfirmPassword;
    @BindView(R.id.button_register) Button buttonRegister;
    @BindView(R.id.button_go_to_login) Button buttonGoToLogin;
    @BindView(R.id.progress_bar_register) ProgressBar progressBar;

    //VARIABLES
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        setupRegisterButton();
        setupGoToLoginButton();
    }

    /**
     * Setup of login button to redirect to login view On Click
     */
    private void setupGoToLoginButton() {
        buttonGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogin();
            }
        });
    }

    /**
     * Setup of register button to attempt registration On Click
     */
    private void setupRegisterButton() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirmPassword = editTextConfirmPassword.getText().toString();
                registerAttempt(email,password,confirmPassword);
            }
        });
    }

    /**
     * Method To Make Registration/Account Creation Request To Firebase,
     * will set proper animations and messages from request
     * @param email new user's email
     * @param password new user's password
     * @param confirmPassword new user's password again, making sure he remembers it
     */
    private void registerAttempt(String email, String password, String confirmPassword){
        if(!password.equals(confirmPassword)) Toast.makeText(this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();
        if(!Validations.isValidEmail(email)) Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        if(!Validations.isValidPassword(password)) Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
        enableFields(false);
        progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                enableFields(true);
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    goToMain();
                } else {
                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Method To Redirect User To Login View
     */
    private void goToLogin(){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Method To Redirect User To Main View
     */
    private void goToMain(){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Method To Disable or Enable Buttons,
     * To ensure valid requests
     * @param state boolean representing disabling(false) or enabling(true) fields
     */
    private void enableFields(boolean state){
        buttonRegister.setEnabled(state);
        buttonGoToLogin.setEnabled(state);
    }


}
