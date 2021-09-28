package org.crosswalk.engine;

import android.os.Bundle;
import android.util.Log;

import org.apache.cordova.CordovaActivity;
import org.chromium.base.ApplicationStatus;
import org.xwalk.core.XWalkInitializer;

public class XWalkCordovaActivity extends CordovaActivity implements XWalkInitializer.XWalkInitListener {
   private XWalkInitializer mXWalkInitializer;
   private String pendingUrl;
   private Boolean ready;

   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      ApplicationStatus.initialize( this.getApplication() );

      super.onCreate(savedInstanceState);

      mXWalkInitializer = new XWalkInitializer(this, this);
      mXWalkInitializer.initAsync();

      ready = false;
      pendingUrl = null;
   }

   @Override
   public void loadUrl(String url) {
      if ( ready )
         super.loadUrl(url);
      else {
         pendingUrl = url;
      }
   }

   @Override
   public void onBackPressed() {
      super.onBackPressed();
   }

   @Override
   public void onXWalkInitStarted() {
      Log.i("qqq","onXWalkInitStarted");

   }

   @Override
   public void onXWalkInitCancelled() {
      Log.i("qqq","onXWalkInitCancelled");
   }

   @Override
   public void onXWalkInitFailed() {
      Log.i("qqq","onXWalkInitFailed");

   }

   @Override
   public void onXWalkInitCompleted() {
      Log.i("qqq","onXWalkInitCompleted");
      ready = true;
      if ( pendingUrl != null ) loadUrl(pendingUrl);
   }

}
