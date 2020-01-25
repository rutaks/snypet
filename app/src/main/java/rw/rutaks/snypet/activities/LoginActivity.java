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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //VIEW ELEMENTS
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.buttonLogin)Button buttonLogin;
    @BindView(R.id.buttonGoToRegister)Button buttonGoToRegister;
    @BindView(R.id.progressBarLogin) ProgressBar progressBar;

    //VARIABLES
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        auth = FirebaseAuth.getInstance();

        setupLoginButton();
        setupGoToRegisterButton();
    }

    /**
     * Override Method to redirect user to Main on authentication success
     * i.e: if he/she already logged in
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null) goToMain();
    }

    /**
     * Setup of register button to redirect to register view On Click
     */
    private void setupGoToRegisterButton() {
        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    /**
     * Setup of login button to attempt login On Click
     */
    private void setupLoginButton() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                attemptLogin(email,password);
            }
        });
    }

    /**
     * Method To Redirect User To Main View
     */
    private void goToMain(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Method To Redirect User To Registration View
     */
    private void goToRegister(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * Method To Make Login Request To Firebase,
     * will set proper animations and messages from request
     * @param email user's email
     * @param password user's password
     */
    private void attemptLogin(String email, String password){
        if(Validations.isValidEmail(email) && Validations.isValidPassword(password)){
            progressBar.setVisibility(View.VISIBLE);
            enableFields(false);
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressBar.setVisibility(View.INVISIBLE);
                    if(!task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        enableFields(true);
                        return;
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    enableFields(true);
                    goToMain();
                }
            });
        } else {
            Toast.makeText(this, "Make Sure All Fields Are Valid", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method To Disable or Enable Buttons,
     * To ensure valid requests
     * @param state boolean representing disabling(false) or enabling(true) fields
     */
    private void enableFields(boolean state){
        buttonLogin.setEnabled(state);
        buttonGoToRegister.setEnabled(state);
    }
}
