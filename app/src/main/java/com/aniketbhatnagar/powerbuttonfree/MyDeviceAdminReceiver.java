package com.aniketbhatnagar.powerbuttonfree;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyDeviceAdminReceiver extends DeviceAdminReceiver {

  @Override
  public CharSequence onDisableRequested(Context context, Intent intent) {
    return "Are you sure you want to disable Power off? You won't be able to lock screen using this app anymore.";
  }

  @Override
  public void onDisabled(Context context, Intent intent) {
    showToast(context, "Off button will no longer work.");
  }

  private void showToast(Context context, String msg) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
  }
}