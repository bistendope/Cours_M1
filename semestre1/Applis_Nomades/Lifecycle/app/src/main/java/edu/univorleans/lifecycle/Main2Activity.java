package edu.univorleans.lifecycle;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);
            notify("onCreate");
        }

        @Override
        protected void onStart() {
            super.onStart();
            notify("onStart");
        }

        @Override
        protected void onResume() {
            super.onResume();
            notify("onResume");
        }

        @Override
        protected void onPause() {
            super.onPause();
            notify("onPause");
        }

        @Override
        protected void onStop() {
            super.onStop();
            notify("onStop");
        }

        @Override
        protected void onRestart() {
            super.onRestart();
            notify("onreStart");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            notify("onDestroy");
        }

    private void notify(String methodName) {
        Notification noti = new NotificationCompat.Builder(this)
                .setContentTitle(methodName)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(this.getClass().getName()).build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify((int) System.currentTimeMillis(), noti);
    }

    public void onClick(View v){
        Intent intent = new Intent(this, Main3Activity.class);
        startActivity(intent);
    }

    public void onClickBack(View v){
        finish();
    }
}
