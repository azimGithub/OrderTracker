// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CustomerAddress_ViewBinding implements Unbinder {
  private CustomerAddress target;

  @UiThread
  public CustomerAddress_ViewBinding(CustomerAddress target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CustomerAddress_ViewBinding(CustomerAddress target, View source) {
    this.target = target;

    target.tvCrDays = Utils.findRequiredViewAsType(source, R.id.crDays, "field 'tvCrDays'", TextView.class);
    target.tvCrLimit = Utils.findRequiredViewAsType(source, R.id.crLimit, "field 'tvCrLimit'", TextView.class);
    target.tvStoereName = Utils.findRequiredViewAsType(source, R.id.tvStoereName, "field 'tvStoereName'", TextView.class);
    target.tvAddress1 = Utils.findRequiredViewAsType(source, R.id.add1, "field 'tvAddress1'", TextView.class);
    target.tvAddress2 = Utils.findRequiredViewAsType(source, R.id.add2, "field 'tvAddress2'", TextView.class);
    target.tvAddress3 = Utils.findRequiredViewAsType(source, R.id.add3, "field 'tvAddress3'", TextView.class);
    target.tvTitle = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitle'", TextView.class);
    target.areaName = Utils.findRequiredViewAsType(source, R.id.areaName, "field 'areaName'", TextView.class);
    target.city = Utils.findRequiredViewAsType(source, R.id.city, "field 'city'", TextView.class);
    target.pin = Utils.findRequiredViewAsType(source, R.id.pinCode, "field 'pin'", TextView.class);
    target.contactPerson = Utils.findRequiredViewAsType(source, R.id.contactPerson, "field 'contactPerson'", TextView.class);
    target.phone1 = Utils.findRequiredViewAsType(source, R.id.phone1, "field 'phone1'", TextView.class);
    target.phone2 = Utils.findRequiredViewAsType(source, R.id.phone2, "field 'phone2'", TextView.class);
    target.phone3 = Utils.findRequiredViewAsType(source, R.id.phone3, "field 'phone3'", TextView.class);
    target.longitude = Utils.findRequiredViewAsType(source, R.id.longitude, "field 'longitude'", TextView.class);
    target.latitude = Utils.findRequiredViewAsType(source, R.id.latitude, "field 'latitude'", TextView.class);
    target.outBal = Utils.findRequiredViewAsType(source, R.id.outAmt, "field 'outBal'", TextView.class);
    target.freq = Utils.findRequiredViewAsType(source, R.id.freq, "field 'freq'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CustomerAddress target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tvCrDays = null;
    target.tvCrLimit = null;
    target.tvStoereName = null;
    target.tvAddress1 = null;
    target.tvAddress2 = null;
    target.tvAddress3 = null;
    target.tvTitle = null;
    target.areaName = null;
    target.city = null;
    target.pin = null;
    target.contactPerson = null;
    target.phone1 = null;
    target.phone2 = null;
    target.phone3 = null;
    target.longitude = null;
    target.latitude = null;
    target.outBal = null;
    target.freq = null;
  }
}
