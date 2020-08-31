package com.example.mango;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitmapUtils;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.math.BigDecimal;

import wallet.core.jni.CoinType;
import wallet.core.jni.HDWallet;

public class WalletActivity extends AppCompatActivity {
    TextView walletAddress;
    ImageView qrImage;
    TextView balance;
    TextView dispalyAddress;
    ImageButton addressCopy;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_layout);
        System.loadLibrary("TrustWalletCore");
        walletAddress = findViewById(R.id.address);
        qrImage = findViewById(R.id.qrCode);
        balance = findViewById(R.id.balance);
        dispalyAddress = findViewById(R.id.dispalyAddress);
        toolbar = findViewById(R.id.wallet_list_toolBar);
        addressCopy = findViewById(R.id.address_copy);
        toolbar.setTitle("接收地址");
        Intent intent = getIntent();
        String action = intent.getAction();
        generateAddress(action);
        addressCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences("mnemonic", Context.MODE_PRIVATE);
                String word =  sp.getString("word","");
                HDWallet wallet =  new HDWallet(word, "");
                Toast.makeText(WalletActivity.this,"复制成功",Toast.LENGTH_SHORT).show();
                switch (action){
                    case "btc" :
                        String btcAddress = wallet.getAddressForCoin(CoinType.BITCOIN);
                        setClipboard(btcAddress);
                        break;
                    case "eth":
                        String ethAddress = wallet.getAddressForCoin(CoinType.ETHEREUM);
                        setClipboard(ethAddress);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    private void setClipboard(String context){
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", context);
        cm.setPrimaryClip(mClipData);
    }
    private void generateAddress(String coinType){
        SharedPreferences sp = getSharedPreferences("mnemonic", Context.MODE_PRIVATE);
        String word =  sp.getString("word","");
        HDWallet wallet =  new HDWallet(word, "");
        String address;
        switch (coinType){
            case "btc":
                address = wallet.getAddressForCoin(CoinType.BITCOIN);
                walletAddress.setText(address);
                dispalyAddress.setText("比特币地址");
                generateBitMap(address);
                break;
            case "eth":
                address = wallet.getAddressForCoin(CoinType.ETHEREUM);
                walletAddress.setText(address);
                dispalyAddress.setText("以太坊地址");
                Log.d("以太坊地址",address);
                generateBitMap(address);
                getBalance(address);
                break;
            default:break;
        }
    }

   private void generateBitMap(String coinType){
       Bitmap bitmap = null;
       try {
           bitmap = BitmapUtils.create2DCode(coinType);
           qrImage.setImageBitmap(bitmap);
       } catch (WriterException e) {
           e.printStackTrace();
       }
   }

    private void getBalance(String address){
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                // 通过账户地址查询余额
                EthGetBalance ethGetBalance = Web3.getWeb3j().ethGetBalance(
                        address, DefaultBlockParameterName.LATEST).sendAsync().get();
                BigDecimal getBalance = Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER);
                Log.d("余额","余额"+getBalance.toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        balance.setText(getBalance.toString());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     }).start();

    }
}