package com.example.conor.q_skip;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private EditText etUserName;
    private EditText etPassword;
    private Button bLogin;
    private TextView tvRegister;
    private String UserName;
    private String Password;

    // [START declare_auth]

    private FirebaseAuth mAuth;

    // [END declare_auth]



    // [START declare_auth_listener]

    private FirebaseAuth.AuthStateListener mAuthListener;

    // [END declare_auth_listener]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etUserName = (EditText) findViewById(R.id.etUserName);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView tvRegister = (TextView) findViewById(R.id.tvRegister);

        // [START initialize_auth]

        mAuth = FirebaseAuth.getInstance();

        // [END initialize_auth]

        // [START auth_state_listener]

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {

                    // User is signed in

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                } else {

                    // User is signed out

                    Log.d(TAG, "onAuthStateChanged:signed_out");

                }



            }

        };

        // [END auth_state_listener]

        // [START sign_in_with_email]

        mAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());



                        // If sign in fails, display a message to the user. If sign in succeeds

                        // the auth state listener will be notified and logic to handle the

                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {

                            Log.w(TAG, "signInWithEmail:failed", task.getException());

                            Toast.makeText(Lohis, R.string.auth_failed,

                                    Toast.LENGTH_SHORT).show();

                        }



                        // [START_EXCLUDE]

                        if (!task.isSuccessful()) {

                            mStatusTextView.setText(R.string.auth_failed);

                        }

                        hideProgressDialog();

                        // [END_EXCLUDE]

                    }

                });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(MainActivity.this, UserArea.class);
                MainActivity.this.startActivity(registerIntent);
            }

        });

    }
}
