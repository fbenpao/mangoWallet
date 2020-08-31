package com.example.mango;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WalletList extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private Button btcReceiveButton;
    private Button btcSendButton;
    private Button ethReceiveButton;
    private Button ethSendButton;
    private BottomNavigationView bottomNavigationView;
    private TextView btcBalance;
    private TextView ethBalance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_list_layout);
        toolbar = findViewById(R.id.wallet_list_toolBar);
        btcBalance = findViewById(R.id.btc_balance);
        ethBalance = findViewById(R.id.eth_balance);
        btcReceiveButton = findViewById(R.id.btc_receive);
        btcSendButton = findViewById(R.id.btc_send);
        ethReceiveButton = findViewById(R.id.eth_receive);
        ethSendButton = findViewById(R.id.eth_send);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btcReceiveButton.setOnClickListener((View.OnClickListener) this);
        btcSendButton.setOnClickListener((View.OnClickListener) this);
        ethReceiveButton.setOnClickListener((View.OnClickListener) this);
        ethSendButton.setOnClickListener((View.OnClickListener) this);
        toolbar.setTitle("钱包列表");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        // 关联toolbar和menu，只需这一句代码菜单就可以正常显示了
        // 手动设置溢出菜单项的图标
        // 设置菜单点击事件监听
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(), "菜单项2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.item3:
                        Intent intent = new Intent(WalletList.this,RemmberMnemonic.class);
                        startActivity(intent);
                        break;
                    case R.id.item4:
                        Toast.makeText(getApplicationContext(), "菜单项4", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_bottom_1:
                        Intent intent = new Intent(WalletList.this,WalletList.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.item_bottom_2:
//                        Intent intent2 = new Intent(WalletList.this,RemmberMnemonic.class);
//                        startActivity(intent2);
//                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;

                }
                return true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.eth_receive:
                Intent intent1 = new Intent(WalletList.this,WalletActivity.class);
                intent1.setAction("eth");
                intent1.putExtra("eth","eth");
                startActivity(intent1);
                break;
            case R.id.eth_send:
                Toast.makeText(getApplicationContext(),"todo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btc_receive:
                Intent intent2 = new Intent(WalletList.this,WalletActivity.class);
                intent2.setAction("btc");
                intent2.putExtra("btc","btc");
                startActivity(intent2);
                break;
            case R.id.btc_send:
                Toast.makeText(getApplicationContext(),"todo2",Toast.LENGTH_SHORT).show();
                break;
            default:break;
        }

    }


}
