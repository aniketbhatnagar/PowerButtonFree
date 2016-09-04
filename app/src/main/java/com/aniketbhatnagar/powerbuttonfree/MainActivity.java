package com.aniketbhatnagar.powerbuttonfree;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

  private final static String LOG_TAG = "MainActivity";
  private static final int REQUEST_ENABLE = 1;

  private ComponentName devicePolicyAdminComponent;
  private DevicePolicyManager devicePolicyManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    devicePolicyAdminComponent = new ComponentName(this, MyDeviceAdminReceiver.class);
    devicePolicyManager = getDevicePolicyManager();
    requestAdmin();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (isAdminEnabled()) {
      if (resultCode == RESULT_OK) {
        switch (requestCode) {
          case REQUEST_ENABLE:
            Log.v(LOG_TAG, "Locking screen");
            devicePolicyManager.lockNow();
            break;
        }
      }
    }
    finish();
  }

  private void requestAdmin() {
    Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
    intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, devicePolicyAdminComponent);
    intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "I need this for power off");
    startActivityForResult(intent, REQUEST_ENABLE);
  }

  private boolean isAdminEnabled() {
    return devicePolicyManager.isAdminActive(devicePolicyAdminComponent);
  }

  private DevicePolicyManager getDevicePolicyManager() {
    return (DevicePolicyManager) getApplicationContext().getSystemService(Context.DEVICE_POLICY_SERVICE);
  }
}
