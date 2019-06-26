// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SalesReportActivity_ViewBinding implements Unbinder {
  private SalesReportActivity target;

  @UiThread
  public SalesReportActivity_ViewBinding(SalesReportActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SalesReportActivity_ViewBinding(SalesReportActivity target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SalesReportActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
