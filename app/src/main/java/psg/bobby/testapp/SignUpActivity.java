package psg.bobby.testapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {


    //explicit
    private EditText nameEditText, userEditText, passwordEditText;
    private String nameString, userString, passwordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // bind widget
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);



    } // Main method

    public void clickSignUpSign(View view) {

        nameString = nameEditText.getText().toString().trim();
        userString=userEditText.getText().toString().trim();
        passwordString = passwordEditText.getText().toString().trim();

        // check space
        if (nameString.equals("") || userString.equals("") || passwordString.equals("")) {

            //have space
            Log.d("13decV1", "Have space");

            Myalert myalert = new Myalert(SignUpActivity.this,"Have space","pelase Fill All blank", R.drawable.doremon48);
            myalert.myDialog();

        }

    } //clickSign


} // Main class
