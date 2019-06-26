// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class GetData_ViewBinding implements Unbinder {
  private GetData target;

  @UiThread
  public GetData_ViewBinding(GetData target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GetData_ViewBinding(GetData target, View source) {
    this.target = target;

    target.ivNext = Utils.findRequiredViewAsType(source, R.id.iv_farward, "field 'ivNext'", ImageView.class);
    target.getData = Utils.findRequiredViewAsType(source, R.id.get_data, "field 'getData'", Button.class);
    target.sendData = Utils.findRequiredViewAsType(source, R.id.send_data, "field 'sendData'", Button.class);
    target.logOut = Utils.findRequiredViewAsType(source, R.id.log_out, "field 'logOut'", TextView.class);
    target.next = Utils.findRequiredViewAsType(source, R.id.continueBtn, "field 'next'", Button.class);
    target.gettingData = Utils.findRequiredViewAsType(source, R.id.tv_gettingData, "field 'gettingData'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    GetData target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivNext = null;
    target.getData = null;
    target.sendData = null;
    target.logOut = null;
    target.next = null;
    target.gettingData = null;
  }
}
