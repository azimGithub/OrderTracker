// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view.authentication.areaSelection;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AreaSearching_ViewBinding implements Unbinder {
  private AreaSearching target;

  @UiThread
  public AreaSearching_ViewBinding(AreaSearching target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AreaSearching_ViewBinding(AreaSearching target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.recyclerView, "field 'recyclerView'", RecyclerView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolBar, "field 'toolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AreaSearching target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.toolbar = null;
  }
}
