package com.test.activityeventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityEventBus.getInstance().addEvent(MainActivity.class.getName(), new IEventData() {
                    @Override
                    public int getEventId() {
                        return 0;
                    }
                });
                ActivityEventBus.getInstance().addEvent(MainActivity.class.getName(), new IEventData() {
                    @Override
                    public int getEventId() {
                        return 1;
                    }
                });
                finish();
            }
        });
    }

    public void onEvent(IEventData iEventData) {
        Toast.makeText(TestActivity.this, "接到事件" + iEventData.getEventId(), Toast.LENGTH_SHORT).show();
    }
}
