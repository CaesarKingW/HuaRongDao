package com.example.wangkang.huarongdao;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;
    public static final String EXTRA_MESSAGE =
            "com.example.android.MainActivity.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void StartLevel1(View view) {
        Intent intent_level1 = new Intent(this, Level1Activity.class);
        String mode = "1";
        intent_level1.putExtra(EXTRA_MESSAGE, mode);
        startActivity(intent_level1);
    }


    public void StartLevel2(View view) {
        Intent intent_level1 = new Intent(this, Level1Activity.class);
        String mode = "2";
        intent_level1.putExtra(EXTRA_MESSAGE, mode);
        startActivity(intent_level1);
    }


    public void StartLevel3(View view) {
        Intent intent_level1 = new Intent(this, Level1Activity.class);
        String mode = "3";
        intent_level1.putExtra(EXTRA_MESSAGE, mode);
        startActivity(intent_level1);
    }

    public void StartLevel4(View view) {
        Intent intent_level1 = new Intent(this, Level1Activity.class);
        String mode = "4";
        intent_level1.putExtra(EXTRA_MESSAGE, mode);
        startActivity(intent_level1);
    }

    public void exitGame(View view) {
        alert = null;
        builder = new AlertDialog.Builder(MainActivity.this);
        alert = builder
                .setTitle("系统提示：")
                .setMessage("确定要退出游戏吗？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "取消退出~", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(MainActivity.this, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }).create();             //创建AlertDialog对象
        alert.show();
    }

}
