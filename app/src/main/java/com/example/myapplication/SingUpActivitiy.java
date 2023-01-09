package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivitiy extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextName;
    Button buttonJoin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up);

            firebaseAuth = FirebaseAuth.getInstance();
            editTextEmail = (EditText) findViewById(R.id.email);
            editTextPassword = (EditText) findViewById(R.id.password);
            buttonJoin = (Button) findViewById(R.id.Registerbtn);

            buttonJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!editTextEmail.getText().toString().equals("") && !editTextPassword.getText().toString().equals("")) {
                        // 이메일과 비밀번호가 공백이 아닌 경우
                        createUser(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                    } else {
                        // 이메일과 비밀번호가 공백인 경우
                        Toast.makeText(SingUpActivitiy.this, "계정과 비밀번호를 입력하세요.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        private void createUser(String email, String password) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // 회원가입 성공시
                                Toast.makeText(SingUpActivitiy.this, "회원가입 성공", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                // 계정이 중복된 경우
                                Toast.makeText(SingUpActivitiy.this, "이미 존재하는 계정입니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

