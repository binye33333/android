package com.teach.yo.codeshop;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenyou729 on 17/3/7.
 */

public class OkHttpTest extends Activity {


    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initKeyManager();

        Request request = new Request.Builder()
                .get()
                .url("https://kyfw.12306.cn/otn/")
                .tag(getClass().toString())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("OkHttpTest", e.getMessage(), e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("OkHttpTest", response.toString());
            }
        });
    }


    private void initKeyManager() {

        try {
            //初始化一个x.509证书格式的factory
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            //keystore 中放入服务端证书内容
            keyStore.setCertificateEntry("12306", certificateFactory.generateCertificate(getAssets().open("12306.cer")));
            //初始化服务端信任证书管理器
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

//            //初始化客户端的证书，用于双向认证 最常见的就是U盾
//            KeyStore clientKey = KeyStore.getInstance(KeyStore.getDefaultType());
//            clientKey.load(getAssets().open("chenyoyo.cer"), "chenyoyo".toCharArray());
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(clientKey, "chenyoyo".toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            //把client 的证书，服务端的证书放到ssl上下文中，适用于证书锁定，指定证书的方式
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());

            //证书锁定的使用方式
            String hostname = "kyfw.12306.cn";
            CertificatePinner certificatePinner = new CertificatePinner.Builder()
                    .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
                    .build();
            client = new OkHttpClient.Builder()
                    .certificatePinner(certificatePinner)
//                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagerFactory)
                    .build();

        } catch (CertificateException | KeyStoreException | NoSuchAlgorithmException | IOException | KeyManagementException e) {
            Log.d("OkHttpTest", e.getMessage());
        }

    }
}
