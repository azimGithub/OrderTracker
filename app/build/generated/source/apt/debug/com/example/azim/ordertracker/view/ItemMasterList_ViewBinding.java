// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ItemMasterList_ViewBinding implements Unbinder {
  private ItemMasterList target;

  @UiThread
  public ItemMasterList_ViewBinding(ItemMasterList target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ItemMasterList_ViewBinding(ItemMasterList target, View source) {
    this.target = target;

    target.emptyView = Utils.findRequiredViewAsType(source, R.id.emptyView, "field 'emptyView'", TextView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.tvQuantity = Utils.findRequiredViewAsType(source, R.id.tvQuantity, "field 'tvQuantity'", TextView.class);
    target.tvRow = Utils.findRequiredViewAsType(source, R.id.tvRow, "field 'tvRow'", TextView.class);
    target.tvValue = Utils.findRequiredViewAsType(source, R.id.tvValue, "field 'tvValue'", TextView.class);
    target.ivNext = Utils.findRequiredViewAsType(source, R.id.ivNext, "field 'ivNext'", ImageView.class);
    target.tvTitleBottom = Utils.findRequiredViewAsType(source, R.id.tvTitle, "field 'tvTitleBottom'", TextView.class);
    target.filter = Utils.findRequiredViewAsType(source, R.id.action_search_i, "field 'filter'", ActionMenuView.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.action_search, "field 'search'", ActionMenuView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ItemMasterList target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.emptyView = null;
    target.toolbar = null;
    target.tvQuantity = null;
    target.tvRow = null;
    target.tvValue = null;
    target.ivNext = null;
    target.tvTitleBottom = null;
    target.filter = null;
    target.search = null;
  }
}
