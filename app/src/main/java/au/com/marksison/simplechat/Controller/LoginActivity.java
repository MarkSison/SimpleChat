package au.com.marksison.simplechat.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import au.com.marksison.simplechat.R;


public class LoginActivity extends Activity
{
    // View.
    ImageButton signupButton;
    ImageButton loginButton;
    EditText usernameField;
    EditText passwordField;

    // Controller.
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Start Parse Application.
        LoginService loginService = (LoginService) getApplication();

        // Initialise View.
        loginButton = (ImageButton) findViewById(R.id.loginButton);
        signupButton = (ImageButton) findViewById(R.id.signupButton);
        usernameField = (EditText) findViewById(R.id.usernameField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        // Log In On Click Listener.
        loginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                ParseUser.logInInBackground(username, password, new LogInCallback()
                {
                    public void done(ParseUser user, com.parse.ParseException exception)
                    {
                        if (user != null)
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Successfully Logged In: " + username + ".",
                                    Toast.LENGTH_LONG).show();
                        }

                        else
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Your Username and Password is Invalid.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        // Sign Up On Click Listener.
        signupButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                username = usernameField.getText().toString();
                password = passwordField.getText().toString();

                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setPassword(password);

                user.signUpInBackground(new SignUpCallback()
                {
                    public void done(com.parse.ParseException exception)
                    {
                        if (exception == null)
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Successfully Signed Up: " + username + ".",
                                    Toast.LENGTH_LONG).show();
                        }

                        else
                        {
                            Toast.makeText(LoginActivity.this,
                                    "Error. Username has already been taken.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
