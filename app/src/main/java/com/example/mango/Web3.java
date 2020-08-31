package com.example.mango;

import android.util.Log;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Web3 {
    private static String ethURL = "https://ropsten.infura.io/v3/7d866d7c855a4499b1af32a8cb32d628";
    public static Web3j getWeb3j(){
        Web3j web3 = Web3j.build(new HttpService(ethURL));
        return web3;
    }

    private static void ETHTransaction(String fromAddress,String value,String toAddress) throws ExecutionException, InterruptedException {
//        Web3j web3j = Web3j.build(new HttpService(ethURL));
//
////        Credentials credentials = WalletUtils.loadCredentials(fromAddress);
//
//        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
//                fromAddress, DefaultBlockParameterName.LATEST).sendAsync().get();
//
//        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
//
//        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(
//                nonce, Convert.toWei("18", Convert.Unit.GWEI).toBigInteger(),
//                Convert.toWei("45000", Convert.Unit.WEI).toBigInteger(), toAddress, new BigInteger(value));
//        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
//        String hexValue = Numeric.toHexString(signedMessage);
//
//        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).sendAsync().get();
//        if (ethSendTransaction.hasError()) {
//            Log.d("transfer error:", ethSendTransaction.getError().getMessage());
//        } else {
//            String transactionHash = ethSendTransaction.getTransactionHash();
//            Log.d("transactionHash:", transactionHash);
//        }

    }
}
