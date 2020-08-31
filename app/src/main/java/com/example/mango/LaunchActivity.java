package com.example.mango;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Thread.sleep;

public class LaunchActivity extends AppCompatActivity {
    String word = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("mnemonic", Context.MODE_PRIVATE);
        word =  sp.getString("word","");
        new Thread( new Runnable( ) {
            @Override
            public void run() {
                //耗时任务，比如加载网络数据
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 这里可以睡几秒钟，如果要放广告的话
                        try {
                            sleep(1000);
                            if(!word.equals("")){
                                Intent intent = new Intent(LaunchActivity.this,WalletList.class);
                                startActivity(intent);
                                finish();
                                //取消跳转时的动画，使过度更加连贯
                                overridePendingTransition(0, 0);
                            }else {
                                Intent intent = new Intent(LaunchActivity.this, CreateWalletActivity.class);
                                startActivity(intent);
                                finish();
                                //取消跳转时的动画，使过度更加连贯
                                overridePendingTransition(0, 0);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } ).start();

    }
}
