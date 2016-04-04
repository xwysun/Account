package com.xwysun.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.xwysun.account.Utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.SaveListener;

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
    public static final String APPID="666fb08c0f0e22f9786b4cd9ef260a9e";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(getApplication(), APPID);
        if(BmobUser.getCurrentUser(getApplication())!= null){
            Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
        final BmobUser user=new BmobUser();
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                account=UserAccount.getText().toString().trim();
                passwd=UserPassword.getText().toString().trim();
                user.setUsername(account);
                user.setPassword(passwd);
                user.login(getApplicationContext(), new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Utils.toast(getApplication(),R.string.toast_login_success);
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Utils.toast(getApplication(),s);
                    }
                });
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            {
                // 创建退出对话框
                AlertDialog isExit = new AlertDialog.Builder(this).create();
                // 设置对话框标题
                isExit.setTitle("系统提示");
                // 设置对话框消息
                isExit.setMessage("确定要退出吗");
                // 添加选择按钮并注册监听
                isExit.setButton("确定", listener);
                isExit.setButton2("取消", listener);
                // 显示对话框
                isExit.show();

            }

            return false;
        }


        return super.onKeyDown(keyCode, event);

    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };


}
