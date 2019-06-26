package com.example.azim.ordertracker.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.azim.ordertracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterDialog extends Dialog implements DialogInterface.OnClickListener {
    private Context context;

    public FilterDialog( Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.filter_dialog);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case R.id.byBrand:
                dialog.dismiss();
                break;
            case R.id.byCategory:
                dialog.dismiss();
                break;
        }

    }
}
