package com.example.wangkang.huarongdao;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangkang.huarongdao.R;

public class Level1Activity extends AppCompatActivity {

    // 一局游戏十个人物按钮
    Button Qz[] = new Button[10];
    // 游戏网格 5*4
    int BG[][] = new int[5][4];
    // 提示行走步数
    TextView txt1;
    float SW;
    float x1, x2, y1, y2;
    int Step;

    {
        Step = 0;
    }
    String mode;//关卡

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);

        Intent intent = getIntent();
        mode = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        Qz[0] = (Button) findViewById(R.id.Qz1);
        Qz[1] = (Button) findViewById(R.id.Qz2);
        Qz[2] = (Button) findViewById(R.id.Qz3);
        Qz[3] = (Button) findViewById(R.id.Qz4);
        Qz[4] = (Button) findViewById(R.id.Qz5);
        Qz[5] = (Button) findViewById(R.id.Qz6);
        Qz[6] = (Button) findViewById(R.id.Qz7);
        Qz[7] = (Button) findViewById(R.id.Qz8);
        Qz[8] = (Button) findViewById(R.id.Qz9);
        Qz[9] = (Button) findViewById(R.id.Qz10);

        txt1 = (TextView) findViewById(R.id.Text1);

        for (int i = 0; i < 10; i++)
            Qz[i].setOnTouchListener(new mTouch());


        boolean post = txt1.post(new Runnable() {
            @Override
            public void run() {
                // txt1.setText("Screen Width:" + txt1.getWidth() + "; Qz Width" + Qz[1].getWidth());
                txt1.setText("你已经走了：0步！");
                SW = txt1.getWidth();
                init();
            }
        });

    }

    public class mTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int type; // 1 卒    2  张飞  3 关羽 4 曹操
            int r, c;
            if (v.getWidth() == v.getHeight()) {
                if (v.getHeight() > 300)
                    type = 4;
                else
                    type = 1;

            } else {
                if (v.getHeight() > v.getWidth())
                    type = 2;
                else
                    type = 3;
            }

            r = (int) (v.getY() / 270f);
            c = (int) (v.getX() / 270f);
            //继承了Activity的onTouchEvent方法，直接监听点击事件
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                //当手指离开的时候
                x2 = event.getX();
                y2 = event.getY();
                if (y1 - y2 > 30) //"向上滑:"
                {
                    switch (type) {
                        case 1:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r > 0 && BG[r - 1][c] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = 1;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r > 0 && BG[r - 1][c] == 0 && BG[r - 1][c + 1] == 0) {
                                SetPois(v, r - 1, c);
                                BG[r - 1][c] = BG[r - 1][c + 1] = 1;
                                BG[r + 1][c] = BG[r + 1][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;

                    }

                } else if (y2 - y1 > 30) //向下滑
                {
                    switch (type) {
                        case 1:
                            if (r < 4 && BG[r + 1][c] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 1][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (r < 3 && BG[r + 2][c] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 2][c] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }

                            break;
                        case 3:
                            if (r < 4 && BG[r + 1][c] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 1][c] = BG[r + 1][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (r < 3 && BG[r + 2][c] == 0 && BG[r + 2][c + 1] == 0) {
                                SetPois(v, r + 1, c);
                                BG[r + 2][c] = BG[r + 2][c + 1] = 1;
                                BG[r][c] = BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r == 2 && c == 1) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    Toast.makeText(Level1Activity.this, "您已经成功通关~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            break;
                    }
                } else if (x1 - x2 > 30) //向左滑
                {
                    switch (type) {
                        case 1:
                            if (c > 0 && BG[r][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r + 1][c - 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c > 0 & BG[r][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = 1;
                                BG[r][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c > 0 && BG[r][c - 1] == 0 && BG[r + 1][c - 1] == 0) {
                                SetPois(v, r, c - 1);
                                BG[r][c - 1] = BG[r + 1][c - 1] = 1;
                                BG[r][c + 1] = BG[r + 1][c + 1] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r == 3 && c == 2) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    Toast.makeText(Level1Activity.this, "您已经成功通关~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            break;
                    }
                } else if (x2 - x1 > 30) //向右滑
                {
                    switch (type) {
                        case 1:
                            if (c < 3 && BG[r][c + 1] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 2:
                            if (c < 3 & BG[r][c + 1] == 0 && BG[r + 1][c + 1] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 1] = 1;
                                BG[r + 1][c + 1] = 1;
                                BG[r][c] = 0;
                                BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 3:
                            if (c < 2 && BG[r][c + 2] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 2] = 1;
                                BG[r][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                            }
                            break;
                        case 4:
                            if (c < 2 && BG[r][c + 2] == 0 && BG[r + 1][c + 2] == 0) {
                                SetPois(v, r, c + 1);
                                BG[r][c + 2] = BG[r + 1][c + 2] = 1;
                                BG[r][c] = BG[r + 1][c] = 0;
                                Step++;
                                txt1.setText("你已经走了：" + Step + "步！");
                                if (r == 3 && c == 0) {
                                    txt1.setText("你赢了！共用" + Step + "步！");
                                    Toast.makeText(Level1Activity.this, "您已经成功通关~", Toast.LENGTH_SHORT).show();
                                }
                            }
                            break;
                    }
                }
            }
            return true;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    void SetSize(Button v, int w, int h, String txt) {
        v.setWidth(240);//(w * dip2px(getApplicationContext(),SW/4));
        v.setHeight(h * dip2px(getApplicationContext(), SW / 4));
        v.setText(txt);
    }

    void SetPois(View v, int r, int c) {
        v.setX(c * SW / 4f);
        v.setY(r * SW / 4f);
    }

    void init() {
        switch(mode){
            // 横刀立马
            case "1":
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 4; j++)
                        BG[i][j] = 1;
                BG[4][1] = 0;
                BG[4][2] = 0;
                // 四个卒
                SetSize(Qz[0], 1, 1, "");
                SetPois(Qz[0], 4, 0);
                SetSize(Qz[1], 1, 1, "");
                SetPois(Qz[1], 3, 1);
                SetSize(Qz[2], 1, 1, "");
                SetPois(Qz[2], 3, 2);
                SetSize(Qz[3], 1, 1, "");
                SetPois(Qz[3], 4, 3);
                // 四大边将
                SetSize(Qz[4], 1, 2, "");
                SetPois(Qz[4], 0, 0);
                SetSize(Qz[5], 1, 2, "");
                SetPois(Qz[5], 0, 3);
                SetSize(Qz[6], 1, 2, "");
                SetPois(Qz[6], 2, 0);
                SetSize(Qz[7], 1, 2, "");
                SetPois(Qz[7], 2, 3);

                //关羽
                SetSize(Qz[8], 2, 1, "");
                SetPois(Qz[8], 2, 1);
                // 曹操
                SetSize(Qz[9], 2, 2, "");
                SetPois(Qz[9], 0, 1);
                break;
            // 捷足先登
            case "2":
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 4; j++)
                        BG[i][j] = 1;
                BG[2][0] = 0;
                BG[2][3] = 0;
                // 四个卒
                SetSize(Qz[0], 1, 1, "");
                SetPois(Qz[0], 0, 0);
                SetSize(Qz[1], 1, 1, "");
                SetPois(Qz[1], 1, 0);
                SetSize(Qz[2], 1, 1, "");
                SetPois(Qz[2], 0, 3);
                SetSize(Qz[3], 1, 1, "");
                SetPois(Qz[3], 1, 3);
                // 四大边将
                SetSize(Qz[4], 1, 2, "");
                SetPois(Qz[4], 3, 0);
                SetSize(Qz[5], 1, 2, "");
                SetPois(Qz[5], 3, 1);
                SetSize(Qz[6], 1, 2, "");
                SetPois(Qz[6], 3, 2);
                SetSize(Qz[7], 1, 2, "");
                SetPois(Qz[7], 3, 3);

                //关羽
                SetSize(Qz[8], 2, 1, "");
                SetPois(Qz[8], 2, 1);
                // 曹操
                SetSize(Qz[9], 2, 2, "");
                SetPois(Qz[9], 0, 1);
                break;
            // 先擒后纵
            case "3":
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 4; j++)
                        BG[i][j] = 1;
                BG[2][0] = 0;
                BG[2][3] = 0;
                // 四个卒
                SetSize(Qz[0], 1, 1, "");
                SetPois(Qz[0], 0, 1);
                SetSize(Qz[1], 1, 1, "");
                SetPois(Qz[1], 0, 2);
                SetSize(Qz[2], 1, 1, "");
                SetPois(Qz[2], 4, 2);
                SetSize(Qz[3], 1, 1, "");
                SetPois(Qz[3], 4, 1);
                // 四大边将
                SetSize(Qz[4], 1, 2, "");
                SetPois(Qz[4], 0, 0);
                SetSize(Qz[5], 1, 2, "");
                SetPois(Qz[5], 0, 3);
                SetSize(Qz[6], 1, 2, "");
                SetPois(Qz[6], 3, 0);
                SetSize(Qz[7], 1, 2, "");
                SetPois(Qz[7], 3, 3);

                //关羽
                SetSize(Qz[8], 2, 1, "");
                SetPois(Qz[8], 3, 1);
                // 曹操
                SetSize(Qz[9], 2, 2, "");
                SetPois(Qz[9], 1, 1);
                break;
            // 齐头并进
            case "4":
                for (int i = 0; i < 5; i++)
                    for (int j = 0; j < 4; j++)
                        BG[i][j] = 1;
                BG[4][1] = 0;
                BG[4][2] = 0;
                // 四个卒
                SetSize(Qz[0], 1, 1, "");
                SetPois(Qz[0], 2, 0);
                SetSize(Qz[1], 1, 1, "");
                SetPois(Qz[1], 2, 1);
                SetSize(Qz[2], 1, 1, "");
                SetPois(Qz[2], 2, 2);
                SetSize(Qz[3], 1, 1, "");
                SetPois(Qz[3], 2, 3);
                // 四大边将
                SetSize(Qz[4], 1, 2, "");
                SetPois(Qz[4], 0, 0);
                SetSize(Qz[5], 1, 2, "");
                SetPois(Qz[5], 0, 3);
                SetSize(Qz[6], 1, 2, "");
                SetPois(Qz[6], 3, 0);
                SetSize(Qz[7], 1, 2, "");
                SetPois(Qz[7], 3, 3);

                //关羽
                SetSize(Qz[8], 2, 1, "");
                SetPois(Qz[8], 3, 1);
                // 曹操
                SetSize(Qz[9], 2, 2, "");
                SetPois(Qz[9], 0, 1);
                break;
        }

//        txt1.setText("SW：" +dip2px(getApplicationContext(), SW)+","+getApplicationContext().getResources().getDisplayMetrics().density);

    }
}
