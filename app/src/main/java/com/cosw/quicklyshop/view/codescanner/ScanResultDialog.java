package com.cosw.quicklyshop.view.codescanner;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.TypedValue;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cosw.quicklyshop.R;
import com.google.zxing.Result;

public class ScanResultDialog extends AppCompatDialog {
    public ScanResultDialog(@NonNull Context context, @NonNull Result result) {
        super(context, resolveDialogTheme(context));
        setTitle(R.string.scan_result);
        setContentView(R.layout.dialog_scan_result);
        //noinspection ConstantConditions
        ((TextView) findViewById(R.id.result)).setText(result.getText());
        //noinspection ConstantConditions
        ((TextView) findViewById(R.id.format)).setText(String.valueOf(result.getBarcodeFormat()));
        //noinspection ConstantConditions
        findViewById(R.id.usenselectother_scanner).setOnClickListener(v -> {
            //noinspection ConstantConditions
//            ((ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE))
//                    .setPrimaryClip(ClipData.newPlainText(null, result.getText()));
            Toast.makeText(context, R.string.product_added, Toast.LENGTH_LONG).show();
            dismiss();
        });
        //noinspection ConstantConditions
        findViewById(R.id.usenselectother_scanner).setOnClickListener(v -> dismiss());
    }

    private static int resolveDialogTheme(@NonNull Context context) {
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, outValue, true);
        return outValue.resourceId;
    }
}