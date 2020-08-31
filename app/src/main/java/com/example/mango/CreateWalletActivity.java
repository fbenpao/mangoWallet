package com.example.mango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import wallet.core.jni.CoinType;
import wallet.core.jni.HDWallet;

public class CreateWalletActivity extends AppCompatActivity {
    Mnemonic mnemonic;
    Button createButton;
    Button backWallet;
    TextView displayMn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("TrustWalletCore");
        createButton = findViewById(R.id.createWallet);
        backWallet = findViewById(R.id.backWallet);
        mnemonic = new Mnemonic();
        //创建钱包
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = mnemonic.generateMnemonic();
                saveWord(word);
                Intent intent = new Intent(getApplicationContext(), RemmberMnemonic.class);
                startActivity(intent);
                finish();

            }
        });
        //备份钱包
        backWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        createWalle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//        HDWallet wallet =  new HDWallet(mnemonic.generateMnemonic(), "");
//        String addressBTC = wallet.getAddressForCoin(CoinType.BITCOIN);
//        Log.d("btc",addressBTC);
//        String addressETH = wallet.getAddressForCoin(CoinType.ETHEREUM);
//        Log.d("eth",addressETH);
//        String addressFIO = wallet.getAddressForCoin(CoinType.FIO);
//        Log.d("fio",addressFIO);
//            }
//        });

    }

    private void saveWord(String word){
        SharedPreferences sp = getSharedPreferences("mnemonic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("word",word);
        editor.commit();
    }
}