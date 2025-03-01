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

import android.app.Notification;
import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NavigationListenerService extends NotificationListenerService {
    private static final String TAG = "NavigationListener";
    private static final String MAPS_PACKAGE = "com.google.android.apps.maps";
    
    public static final String ACTION_NAVIGATION_UPDATE = MainActivity.NAVIGATION_UPDATE;
    public static final String EXTRA_NAVIGATION_TEXT = MainActivity.EXTRA_NAVIGATION_TEXT;

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (MAPS_PACKAGE.equals(sbn.getPackageName())) {
            Notification notification = sbn.getNotification();
            CharSequence title = notification.extras.getCharSequence(Notification.EXTRA_TITLE);
            CharSequence text = notification.extras.getCharSequence(Notification.EXTRA_TEXT);

            if (title != null && text != null) {
                String navigationText = title.toString() + "\n" + text.toString();
                
                // Create an explicit intent
                Intent intent = new Intent(ACTION_NAVIGATION_UPDATE);
                intent.setPackage(getPackageName());
                intent.putExtra(EXTRA_NAVIGATION_TEXT, navigationText);
                sendBroadcast(intent);
            }
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Not needed for this implementation
    }
} 