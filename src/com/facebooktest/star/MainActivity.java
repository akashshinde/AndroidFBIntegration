package com.facebooktest.star;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easy.facebook.android.apicall.GraphApi;
import com.easy.facebook.android.data.User;
import com.easy.facebook.android.error.EasyFacebookError;
import com.easy.facebook.android.facebook.FBLoginManager;
import com.easy.facebook.android.facebook.Facebook;
import com.easy.facebook.android.facebook.LoginListener;

public class MainActivity extends Activity implements LoginListener {
  public final String STAR_ID = "224625294288774";

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    if (Global.facebook != null && Global.facebook.sessionIsValid()) {
      onLoginSuccess();
    } else {
      loginToFB();
    }
  }

  public void connectToFacebook() {
    // read about Facebook Permissions here:
    // http://developers.facebook.com/docs/reference/api/permissions/
    String permissions[] = { "user_about_me", "user_activities",
        "user_birthday", "user_checkins", "user_education_history",
        "user_events", "user_groups", "user_hometown", "user_interests",
        "user_likes", "user_location", "user_notes", "user_online_presence",
        "user_photo_video_tags", "user_photos", "user_relationships",
        "user_relationship_details", "user_religion_politics", "user_status",
        "user_videos", "user_website", "user_work_history", "email",
        "read_friendlists", "read_insights", "read_mailbox", "read_requests",
        "read_stream", "xmpp_login", "ads_management", "create_event",
        "manage_friendlists", "manage_notifications", "offline_access",
        "publish_checkins", "publish_stream", "rsvp_event", "sms",
        // "publish_actions",

        "manage_pages"

    };

    Global.fbLoginManager = new FBLoginManager(this, R.layout.main, STAR_ID,
        permissions);
    if (Global.fbLoginManager.existsSavedFacebook()) {
      Global.fbLoginManager.loadFacebook();
    } else {
      Global.fbLoginManager.login();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
      android.content.Intent data) {
    Global.fbLoginManager.loginSuccess(data);
  }

  public void loginSuccess(final Facebook facebook) {
    Global.facebook = facebook;
    GraphApi graphApi = new GraphApi(facebook);

    User user = new User();
    Log.i(
        "user",
        "about " + user.getAbout() + " Bio " + user.getBio() + " B'day "
        + user.getBirthday() + " Email " + user.getEmail()
        + user.getPolitical() + user.getQuotes() + user.getLink()
        + user.getId() + user.getRelationship_status() + user.getReligion()
        + user.getVerified() + user.getWebsite());
    Log.i("user", user.toString());
    try {
      user = graphApi.getMyAccountInfo();
      // update your status if logged in
      // graphApi.setStatus("Hello, world!");
    } catch (EasyFacebookError e) {
      Log.d("TAG: ", e.toString());
    }
    setTitle(user.getFirst_name() + " " + user.getLast_name());
    setTitleColor(Color.WHITE);
    if (Global.facebook.sessionIsValid()) {
      Global.fbLoginManager.displayToast(" Login success!");
    }
    onLoginSuccess();
  }

  private void onLoginSuccess() {
    if (Global.facebook.sessionIsValid()) {
      findViewById(R.id.fblogin_Button).setVisibility(View.GONE);
      final Button nextButton = (Button) findViewById(R.id.next_Button);
      nextButton.setVisibility(View.VISIBLE);
      final TextView tv = (TextView) findViewById(R.id.text);
      tv.setText("Welcome to new App");
      tv.setTextColor(Color.CYAN);
      nextButton.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
          Intent intent = new Intent(MainActivity.this, ScrollActivity.class);
          startActivity(intent);
          MainActivity.this.finish();
        }
      });
    }
  }

  protected void logout(Facebook facebook) throws EasyFacebookError {
    Global.fbLoginManager.logout(facebook);
  }

  private void loginToFB() {
    Button fbLogin = (Button) findViewById(R.id.fblogin_Button);
    fbLogin.setVisibility(View.VISIBLE);
    fbLogin.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        try {
          URL url = new URL("http://uthsms.net");
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();

          String data = URLEncoder.encode("Send SMS", "UTF-8") + "="
                  + URLEncoder.encode("Send SMS", "UTF-8");
          data += "&" + URLEncoder.encode("country", "UTF-8") + "="
                  + URLEncoder.encode("91", "UTF-8");
          data += "&" + URLEncoder.encode("gateway", "UTF-8") + "="
                  + URLEncoder.encode("0", "UTF-8");
          data += "&" + URLEncoder.encode("hyderabad", "UTF-8") + "="
                  + URLEncoder.encode("this demo message", "UTF-8");
          data += "&" + URLEncoder.encode("remLen", "UTF-8") + "="
                  + URLEncoder.encode("140", "UTF-8");
          data += "&" + URLEncoder.encode("sindh", "UTF-8") + "="
                  + URLEncoder.encode("919970170217", "UTF-8");
          data += "&" + URLEncoder.encode("5", "UTF-8") + "="
                  + URLEncoder.encode("0", "UTF-8");
          data += "&" + URLEncoder.encode("5", "UTF-8") + "="
                  + URLEncoder.encode("0", "UTF-8");
          conn.setDoOutput(true);

          OutputStreamWriter wr = new OutputStreamWriter(
                  conn.getOutputStream());
          wr.write(data);
          wr.flush();

          BufferedReader inStream = new BufferedReader(new InputStreamReader((conn.getInputStream())));

          String result = inStream.readLine();
          Log.i("result", result);
          inStream.close();
        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      @SuppressWarnings("deprecation")
      private void sendSms() {
        PendingIntent sentPI = PendingIntent.getBroadcast(MainActivity.this, 0, new Intent(
            "SMS_SENT"), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(MainActivity.this, 0,
            new Intent("SMS_DELIVERED"), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("9970170217", null, "hello world", sentPI, deliveredPI);
        sms.sendTextMessage("9970170217", null,"desc", null, null);
      }
    });
  }

  public void logoutSuccess() {
    Global.fbLoginManager.displayToast("Logout Success!");
  }

  public void loginFail() {
    Global.fbLoginManager.displayToast("Login Epic Failed!");
  }

  // Initiating Menu XML file (menu.xml)
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.layout.menu, menu);
    return true;
  }

  /**
   * Event Handling for Individual menu item selected Identify single menu item
   * by it's id
   * */
  @SuppressLint("NewApi")
  public boolean onOptionsItemSelected(ActionMode mode, MenuItem item) {

    switch (item.getItemId()) {
    case R.id.menu_shutdown:
      // Single menu item is selected do something
      // Ex: launching new activity/screen or show alert message
      Toast.makeText(MainActivity.this, "Shutdown is Selected",
          Toast.LENGTH_SHORT).show();
      return true;

    case R.id.menu_save:
      Toast.makeText(MainActivity.this, "Save is Selected", Toast.LENGTH_SHORT)
      .show();
      return true;

    case R.id.menu_search:
      Toast.makeText(MainActivity.this, "Search is Selected",
          Toast.LENGTH_SHORT).show();
      return true;

    case R.id.menu_share:
      Toast
      .makeText(MainActivity.this, "Share is Selected", Toast.LENGTH_SHORT)
      .show();
      return true;

    case R.id.menu_logout:
      if (Global.facebook != null && Global.facebook.sessionIsValid()) {
        try {
          Global.fbLoginManager.logout(Global.facebook);
          Toast.makeText(MainActivity.this, "logged out", Toast.LENGTH_SHORT)
          .show();
          Intent intent = getIntent();
          finish();
          startActivity(intent);
        } catch (EasyFacebookError e) {
          e.printStackTrace();
        }
      } else
        Toast.makeText(MainActivity.this, "Please login", Toast.LENGTH_SHORT)
        .show();
      return true;

    case R.id.menu_home:
      Toast.makeText(MainActivity.this, "Preferences is Selected",
          Toast.LENGTH_SHORT).show();
      return true;

    default:
      return super.onOptionsItemSelected(item);
    }
  }

}
