package psg.bobby.testapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //explicit
    private EditText userEditText, passwordEditText;
    private String userString, passwordString;
    private String[] loginStrings;
    private boolean aBoolean = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind widget
        userEditText = (EditText) findViewById(R.id.editText4);
        passwordEditText = (EditText) findViewById(R.id.editText5);


    } // main method

    public void clickSignInMain(View view) {



        userString = userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();


        if (userString.equals("") || passwordString.equals(""))
        {
            //have space
            Myalert myalert=new Myalert(MainActivity.this,
                    getResources().getString(R.string.title_have_space),
                    getResources().getString(R.string.message_have_space),
                    R.drawable.bird48);
            myalert.myDialog();
            Log.d("xxx", userString);




        } else
            Log.d("xxx", "false");
        {
            //no space
            try {

                SyncUser syncUser = new SyncUser(MainActivity.this);
                syncUser.execute();
                String s = syncUser.get();
                Log.d("14decV2", "JSON ==>" + s);


                JSONArray jsonArray = new JSONArray(s);

                loginStrings = new String[4];
                for (int i=0; i<jsonArray.length(); i+=1) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (userString.equals(jsonObject.get("User"))) {

                        loginStrings[0] = jsonObject.getString("id");
                        loginStrings[1] = jsonObject.getString("Name");
                        loginStrings[2] = jsonObject.getString("User");
                        loginStrings[3] = jsonObject.getString("Password");

                        aBoolean = false;

                    }

                } // for

                if (aBoolean) {

                    Myalert myalert = new Myalert(MainActivity.this,
                            getResources().getString(R.string.title_user_false),
                            getResources().getString(R.string.message_user_false),
                            R.drawable.rat48);
                } else if (passwordString.equals(loginStrings[3])) {
                    //password true
                    Toast.makeText(MainActivity.this, "welcome " + loginStrings[1],
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    //password false
                    Myalert myalert = new Myalert(MainActivity.this,
                            getResources().getString(R.string.title_pass_false),
                            getResources().getString(R.string.message_pass_false),
                            R.drawable.kon48);
                    myalert.myDialog();

                }

            } catch (Exception e) {

                Log.d("14decV2", "e Main ==>" + e.toString());

            }


        }


    } // click sign In

    public void clickSignUpMain(View view) {
        //Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT);
        startActivity(new Intent(MainActivity.this, SignUpActivity.class));
    }

} // main class
