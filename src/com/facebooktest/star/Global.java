package com.facebooktest.star;

import com.easy.facebook.android.facebook.FBLoginManager;
import com.easy.facebook.android.facebook.Facebook;

import android.app.Application;

public class Global extends Application {
  public static Facebook facebook = null;
  public static FBLoginManager fbLoginManager = null;

  public static FBLoginManager getFbLoginManager() {
    return fbLoginManager;
  }

  public static void setFbLoginManager(FBLoginManager fbLoginManager) {
    Global.fbLoginManager = fbLoginManager;
  }

  public Global() {
    super();
  }

  public static Facebook getFacebook() {
    return facebook;
  }

  public static void setFacebook(Facebook facebook) {
    Global.facebook = facebook;
  }
}
