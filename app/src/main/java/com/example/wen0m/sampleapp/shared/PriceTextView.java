package com.example.wen0m.sampleapp.shared;

import android.content.Context;
import android.util.AttributeSet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;


public class PriceTextView extends android.support.v7.widget.AppCompatTextView {
    private String price;

    public PriceTextView(Context context) {
        super(context);
    }

    public PriceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PriceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        price = text.toString();
        String prezzo = text.toString();
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###", symbols);
            prezzo = decimalFormat.format(Integer.parseInt(text.toString()));
        }catch (Exception e){}

        super.setText(prezzo, type);
    }

    @Override
    public CharSequence getText() {
        return price;
    }
}
