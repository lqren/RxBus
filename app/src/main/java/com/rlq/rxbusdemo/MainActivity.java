package com.rlq.rxbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Disposable mDisposable;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.tv);
        mDisposable = RxBus.getInstance().register(String.class, new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                mTv.setText(o);
                RxBus.getInstance().unregister(mDisposable);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this,SecondActivity.class));
//                finish();

                RxBus.getInstance().post("hello rxBus"+(i++));

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unregister(mDisposable);
    }
}
