package com.project.moneytablite;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    Button clearbtn,submitbtn;
    EditText inpName,impEMail,inpPhno,inpPwd;

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        clearbtn=findViewById(R.id.Clearbtn);
        submitbtn=findViewById(R.id.submitbtn);
        inpName=findViewById(R.id.inpName);
        impEMail=findViewById(R.id.impEMail);
        inpPhno=findViewById(R.id.inpPhno);
        inpPwd=findViewById(R.id.inpPwd);

        inpName.setText("");
        impEMail.setText("");
        inpPwd.setText("");
        inpPhno.setText("");

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sp = getSharedPreferences("MYPREF", MODE_PRIVATE);

                String newUser = inpName.getText().toString();
                String newpwd = inpPwd.getText().toString();
                String newmail = impEMail.getText().toString();
                String newphno = inpPhno.getText().toString();

                if ("".equals(newUser)){
                    Toast.makeText(Register.this, "UserName Field is Compulsory ", Toast.LENGTH_SHORT).show();
                }
//                }else if (!validateUserName(newUser)){
//                    Toast.makeText(Register.this, "UserName Field is badly formatted ", Toast.LENGTH_SHORT).show();
//                    inpName.setText("");
//                }
                else if ("".equals(newmail)){
                    Toast.makeText(Register.this, "Email Field is Compulsory ", Toast.LENGTH_SHORT).show();
                }
                else   if (!isEmailValid(newmail)){
                    Toast.makeText(Register.this, "Email Field is badly formatted ", Toast.LENGTH_SHORT).show();
                    impEMail.setText("");
                }
                else if ("".equals(newphno)){
                    Toast.makeText(Register.this, "Phone Number Field is Compulsory ", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidMobile(newphno)){
                    Toast.makeText(Register.this, "Phone Number Field is badly formatted ", Toast.LENGTH_SHORT).show();
                    inpPhno.setText("");
                }
                else if ("".equals(newpwd)){
                    Toast.makeText(Register.this, "Password Field is Compulsory ", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidPassword(newpwd)) {
                    Toast.makeText(Register.this, "Password must contain mix of upper " +
                                    "and lower case letters as well as digits and one special charecter(4-20) ",
                            Toast.LENGTH_SHORT).show();
                    inpPwd.setText("");
                }
                else {
                     SharedPreferences.Editor editor = sp.edit();
                     editor.putString(newUser + newpwd + "Data", newUser + "\n" + newmail + "\n" + newphno);
                     editor.putString(newUser + "a", newUser);
                     editor.putString(newpwd + "pwd", newpwd);
                     editor.apply();

                     Intent intent = new Intent(Register.this, MainActivity.class);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                     startActivity(intent);
                 }

            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inpName.setText("");
                impEMail.setText("");
                inpPwd.setText("");
                inpPhno.setText("");
            }
        });
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    public static boolean isValidPassword(String password) {
        Matcher matcher = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{4,20})").matcher(password);
        return matcher.matches();
    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 9 && phone.length() <= 11;
        }
        return false;
    }
//    public boolean validateUserName(final String username){
//        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
//        Matcher matcher = pattern.matcher(username);
//        return matcher.matches();
//
//    }

}
