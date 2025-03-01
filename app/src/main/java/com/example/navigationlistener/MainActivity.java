/*
 * Copyright (c) 2025 Shubham Patil
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.navigationlistener;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {
    private TextView navigationText;
    private BroadcastReceiver navigationReceiver;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    public static final String NAVIGATION_UPDATE = "com.example.navigationlistener.NAVIGATION_UPDATE";
    public static final String EXTRA_NAVIGATION_TEXT = "navigation_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationText = findViewById(R.id.navigationText);

        // Check if notification access is enabled
        if (!isNotificationServiceEnabled()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        }

        // Initialize the broadcast receiver
        navigationReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String text = intent.getStringExtra(EXTRA_NAVIGATION_TEXT);
                if (text != null) {
                    navigationText.setText(text);
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(NAVIGATION_UPDATE);
        ContextCompat.registerReceiver(this, navigationReceiver, filter, ContextCompat.RECEIVER_NOT_EXPORTED);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(navigationReceiver);
    }

    private boolean isNotificationServiceEnabled() {
        String packageName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (flat != null) {
            String[] names = flat.split(":");
            for (String name : names) {
                if (packageName.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
} 