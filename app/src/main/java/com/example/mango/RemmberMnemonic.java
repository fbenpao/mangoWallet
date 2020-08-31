package com.example.mango;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

public class RemmberMnemonic extends AppCompatActivity {
    Button copyMn;
    TextView displayMn;
    Mnemonic mnemonic;
    private Toolbar toolbar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remmber_mnemonic_layout);
        copyMn = findViewById(R.id.copy);
        displayMn = findViewById(R.id.mnemonic);
        toolbar = findViewById(R.id.wallet_list_toolBar);
        toolbar.setTitle("备份钱包");
        SharedPreferences sp = getSharedPreferences("mnemonic", Context.MODE_PRIVATE);
        String word =  sp.getString("word","");
        displayMn.setText(word);
        copyMn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClipboard(word);
                Toast.makeText(RemmberMnemonic.this,"复制成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RemmberMnemonic.this,WalletList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setClipboard(String context){
      ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
      ClipData mClipData = ClipData.newPlainText("Label", context);
      cm.setPrimaryClip(mClipData);
     }



    }