package rw.rutaks.snypet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import rw.rutaks.snypet.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    //VIEW ELEMENTS
    @BindView(R.id.main_menu) Toolbar toolbarMainMenu;

    //VARIABLES
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupToolbar();

        auth = FirebaseAuth.getInstance();
    }

    /**
     * Override Method to redirect user to login on authentication failure
     */
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null) goToLogin();
    }

    /**
     * Override Method to setup options menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    /**
     * Override Method to handle click event on menu item
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.button_action_logout:
                logOut();
                return true;
            default:
                return false;
        }
    }

    /**
     * Function to go to Sign User Out Using THe FirebaseAuth class,
     * where the user instance is stored; afterwich redirecting user to
     * login view
     */
    private void logOut() {
        auth.signOut();
        goToLogin();
    }

    /**
     * Function to go to Login activity,
     * Will Close Current MainActivity afterwards
     */
    private void goToLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Function to setup toolbar with its elements,
     * will setup menu text
     */
    private void setupToolbar(){
        setSupportActionBar(toolbarMainMenu);
        getSupportActionBar().setTitle("Snypet");
    }
}
