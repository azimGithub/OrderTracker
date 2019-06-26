// Generated code from Butter Knife. Do not modify!
package com.example.azim.ordertracker.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.azim.ordertracker.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OrderSummary_ViewBinding implements Unbinder {
  private OrderSummary target;

  @UiThread
  public OrderSummary_ViewBinding(OrderSummary target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OrderSummary_ViewBinding(OrderSummary target, View source) {
    this.target = target;

    target.qtyCountTv = Utils.findRequiredViewAsType(source, R.id.qtyCount, "field 'qtyCountTv'", TextView.class);
    target.qtyTv = Utils.findRequiredViewAsType(source, R.id.qty_tv, "field 'qtyTv'", TextView.class);
    target.hashTv = Utils.findRequiredViewAsType(source, R.id.hash_tv, "field 'hashTv'", TextView.class);
    target.hashCountTv = Utils.findRequiredViewAsType(source, R.id.hash_count, "field 'hashCountTv'", TextView.class);
    target.ivNxt = Utils.findRequiredViewAsType(source, R.id.ivNxt, "field 'ivNxt'", Button.class);
    target.storeNameTv = Utils.findRequiredViewAsType(source, R.id.storeName, "field 'storeNameTv'", TextView.class);
    target.customBalTv = Utils.findRequiredViewAsType(source, R.id.custmBallTv, "field 'customBalTv'", TextView.class);
    target.getCustomBalTv = Utils.findRequiredViewAsType(source, R.id.customBalSumTv, "field 'getCustomBalTv'", TextView.class);
    target.discTv = Utils.findRequiredViewAsType(source, R.id.discTv, "field 'discTv'", TextView.class);
    target.DiscSumTv = Utils.findRequiredViewAsType(source, R.id.discSumTv, "field 'DiscSumTv'", TextView.class);
    target.codeTv = Utils.findRequiredViewAsType(source, R.id.codeTv, "field 'codeTv'", TextView.class);
    target.codeValTv = Utils.findRequiredViewAsType(source, R.id.codeValTv, "field 'codeValTv'", TextView.class);
    target.amtWithVatTv = Utils.findRequiredViewAsType(source, R.id.amtWithVatTv, "field 'amtWithVatTv'", TextView.class);
    target.amtWithVatSumTv = Utils.findRequiredViewAsType(source, R.id.amtWithVatSumTv, "field 'amtWithVatSumTv'", TextView.class);
    target.dateTv = Utils.findRequiredViewAsType(source, R.id.dateTv, "field 'dateTv'", TextView.class);
    target.dateValTv = Utils.findRequiredViewAsType(source, R.id.dateValTv, "field 'dateValTv'", TextView.class);
    target.netAmtTv = Utils.findRequiredViewAsType(source, R.id.netAmtTv, "field 'netAmtTv'", TextView.class);
    target.netAmtValTv = Utils.findRequiredViewAsType(source, R.id.netAmtValTv, "field 'netAmtValTv'", TextView.class);
    target.vatTv = Utils.findRequiredViewAsType(source, R.id.vatTV, "field 'vatTv'", TextView.class);
    target.vatValTv = Utils.findRequiredViewAsType(source, R.id.vatValTv, "field 'vatValTv'", TextView.class);
    target.netInvTv = Utils.findRequiredViewAsType(source, R.id.netInvTv, "field 'netInvTv'", TextView.class);
    target.netAmt = Utils.findRequiredViewAsType(source, R.id.netAmt, "field 'netAmt'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OrderSummary target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.qtyCountTv = null;
    target.qtyTv = null;
    target.hashTv = null;
    target.hashCountTv = null;
    target.ivNxt = null;
    target.storeNameTv = null;
    target.customBalTv = null;
    target.getCustomBalTv = null;
    target.discTv = null;
    target.DiscSumTv = null;
    target.codeTv = null;
    target.codeValTv = null;
    target.amtWithVatTv = null;
    target.amtWithVatSumTv = null;
    target.dateTv = null;
    target.dateValTv = null;
    target.netAmtTv = null;
    target.netAmtValTv = null;
    target.vatTv = null;
    target.vatValTv = null;
    target.netInvTv = null;
    target.netAmt = null;
  }
}
