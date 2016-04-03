package com.xwysun.account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.Icon)
    ImageView Icon;
    @Bind(R.id.UserAccount)
    EditText UserAccount;
    @Bind(R.id.UserPassword)
    EditText UserPassword;
    @Bind(R.id.LoginButton)
    Button LoginButton;

    private String account;
    private String passwd;
    public static final int LOGIN_FAIL_PASSWORD=203;
    public static final int LOGIN_FAIL_INTERNET=202;
    public static final int LOGIN_FAIL_CODE=201;
    public static final int LOGIN_SUCCESS=200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account=UserAccount.getText().toString().trim();
                passwd=UserPassword.getText().toString().trim();
                switch (Login(account,passwd)){
                    case LOGIN_SUCCESS:
                        Toast.makeText(getApplication(),R.string.toast_login_success,Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,RecordsActivity.class);
                        startActivity(intent);
                        break;
                    case LOGIN_FAIL_CODE:
                        Toast.makeText(getApplication(),R.string.toast_login_fail_code,Toast.LENGTH_SHORT).show();
                        break;
                    case LOGIN_FAIL_INTERNET:
                        Toast.makeText(getApplication(),R.string.toast_login_fail_internet,Toast.LENGTH_SHORT).show();
                        break;
                    case LOGIN_FAIL_PASSWORD:
                        Toast.makeText(getApplication(),R.string.toast_login_fail_password,Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private int Login(String Account,String Password){
        return LOGIN_SUCCESS;
    }
}
