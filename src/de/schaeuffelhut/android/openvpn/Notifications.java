/**
 * Copyright 2009 Friedrich Schäuffelhut
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
package de.schaeuffelhut.android.openvpn;

import java.io.File;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class Notifications {
	private Notifications(){}
	
	public static final int FIRST_CONFIG_ID = 1000000;
	
	public static void notifyConnected(int id, Context context, NotificationManager notificationManager, File configFile, String msg) {
		Notification notification = new Notification(
				R.drawable.vpn_connected,
				configFile.getName() +": " + msg,
				System.currentTimeMillis()
		);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		Intent intent = new Intent(context, OpenVpnSettings.class );
//		intent.putExtra( EnterPassphrase.EXTRA_FILENAME, configFile.getAbsolutePath() );
		
		notification.setLatestEventInfo(
				context,
				"OpenVPN, " + configFile.getName(),
				msg,
				PendingIntent.getActivity(
						context,
						0,
						intent,
						0
				)
		);
		
		notificationManager.notify( id, notification);
	}
	
	public static void notifyBytes(int id, Context context, NotificationManager notificationManager, File configFile, String msg) {
		Notification notification = new Notification(
				R.drawable.vpn_connected,
				configFile.getName() +": " + msg,
				System.currentTimeMillis()
		);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		Intent intent = new Intent(context, OpenVpnSettings.class );
//		intent.putExtra( EnterPassphrase.EXTRA_FILENAME, configFile.getAbsolutePath() );
		
		notification.setLatestEventInfo(
				context,
				"OpenVPN, " + configFile.getName(),
				msg,
				PendingIntent.getActivity(
						context,
						0,
						intent,
						0
				)
		);
		
		notificationManager.notify( id, notification);
	}

	public static void notifyDisconnected(int id, Context context, NotificationManager notificationManager, File configFile, String msg) {
		Notification notification = new Notification(
				R.drawable.vpn_disconnected,
				configFile.getName() +": " + msg,
				System.currentTimeMillis()
		);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		Intent intent = new Intent(context, OpenVpnSettings.class );
//		intent.putExtra( EnterPassphrase.EXTRA_FILENAME, configFile.getAbsolutePath() );
		
		notification.setLatestEventInfo(
				context,
				"OpenVPN, " + configFile.getName(),
				msg,
				PendingIntent.getActivity(
						context,
						0,
						intent,
						0
				)
		);
	
		notificationManager.notify( id, notification);
	}
	
	public static void sendPassphraseRequired(int id, Context context, NotificationManager notificationManager, File configFile) {
		Notification notification = new Notification(
				R.drawable.vpn_disconnected_attention,
				"Passphrase required",
				System.currentTimeMillis()
		);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		Intent intent = new Intent(context, EnterPassphrase.class );
		intent.putExtra( EnterPassphrase.EXTRA_FILENAME, configFile.getAbsolutePath() );
		
		notification.setLatestEventInfo(
				context,
				"Passphrase required",
				String.format( "for configuration %s", configFile.getName() ),
				PendingIntent.getActivity(
						context,
						0,
						intent,
						0
				)
		);
	
		notificationManager.notify( id, notification);
	}
	
	public static void sendUsernamePasswordRequired(int id, Context context, File configFile, NotificationManager notificationManager) {
		Notification notification = new Notification(
				R.drawable.vpn_disconnected_attention,
				"Username/Password required",
				System.currentTimeMillis()
		);
		notification.flags |= Notification.FLAG_NO_CLEAR;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;
		
		Intent intent = new Intent(context, EnterUserPassword.class );
		intent.putExtra( EnterUserPassword.EXTRA_FILENAME, configFile.getAbsolutePath() );
		
		notification.setLatestEventInfo(
				context,
				"Username/Password required",
				String.format( "for configuration %s", configFile.getName() ),
				PendingIntent.getActivity(
						context,
						0,
						intent,
						0
				)
		);
	
		notificationManager.notify( id, notification);
	}
	
	public static void cancel(int id, Context context)
	{
		getNotificationManager(context).cancel( id );
	}
	
	private static NotificationManager getNotificationManager(Context context) {
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		return notificationManager;
	}
}