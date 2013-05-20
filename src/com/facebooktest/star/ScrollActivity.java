package com.facebooktest.star;

import com.easy.facebook.android.error.EasyFacebookError;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ScrollActivity extends MainActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scroll);
    final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.planets_array, android.R.layout.simple_spinner_item);
    adapter
    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner1.setAdapter(adapter);
    final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
    adapter
    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner2.setAdapter(adapter);
    final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
    adapter
    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner3.setAdapter(adapter);
    Button ok = (Button) findViewById(R.id.button_ok);
    ok.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Toast.makeText(
            ScrollActivity.this,
            "OnClickListener : " + "\nSpinner 1 : "
            + String.valueOf(spinner1.getSelectedItem()) + "\nSpinner 2 : "
            + String.valueOf(spinner2.getSelectedItem()) + "\nSpinner 3 : "
            + String.valueOf(spinner3.getSelectedItem()),
            Toast.LENGTH_SHORT).show();
      }
    });
  }
//
//  @Override
//  public boolean onCreateOptionsMenu(Menu menu) {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    getMenuInflater().inflate(R.layout.menu, menu);
//    return true;
//  }
//  
//  private void restartFirstActivity()
//  {
//  Intent i = getApplicationContext().getPackageManager()
//  .getLaunchIntentForPackage(getApplicationContext().getPackageName() );
//
//  i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
//  startActivity(i);
//  }
//  
//  
//  /**
//   * Event Handling for Individual menu item selected Identify single menu item
//   * by it's id
//   * */
//  @Override
//  public boolean onOptionsItemSelected(MenuItem item) {
//
//    switch (item.getItemId()) {
//    case R.id.menu_shutdown:
//      // Single menu item is selected do something
//      // Ex: launching new activity/screen or show alert message
//      Toast.makeText(ScrollActivity.this, "Bookmark is Selected",
//          Toast.LENGTH_SHORT).show();
//      return true;
//
//    case R.id.menu_save:
//      Toast.makeText(ScrollActivity.this, "Save is Selected",
//          Toast.LENGTH_SHORT).show();
//      return true;
//
//    case R.id.menu_search:
//      Toast.makeText(ScrollActivity.this, "Search is Selected",
//          Toast.LENGTH_SHORT).show();
//      return true;
//
//    case R.id.menu_share:
//      Toast.makeText(ScrollActivity.this, "Share is Selected",
//          Toast.LENGTH_SHORT).show();
//      return true;
//
//    case R.id.menu_logout:
//      if (Global.facebook != null && Global.facebook.sessionIsValid()) {
//        try {
//          Global.fbLoginManager.logout(Global.facebook);
//          Toast.makeText(ScrollActivity.this, "logged out", Toast.LENGTH_SHORT)
//          .show();
//          ScrollActivity.this.finish();
//          restartFirstActivity();
//        } catch (EasyFacebookError e) {
//          e.printStackTrace();
//        }
//      } else
//        Toast.makeText(ScrollActivity.this, "Please login", Toast.LENGTH_SHORT)
//        .show();
//      return true;
//
//    case R.id.menu_home:
//      Toast.makeText(ScrollActivity.this, "At Home",
//          Toast.LENGTH_SHORT).show();
//      restartFirstActivity();
//      return true;
//
//    default:
//      return super.onOptionsItemSelected(item);
//    }
//  }
}
