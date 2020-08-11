package br.com.seasyc.providingaccount.helpers;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.text.NumberFormat;

public class Formatters {

    public static EditText date(EditText editText) {
        SimpleMaskFormatter smf = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtw = new MaskTextWatcher(editText, smf);
        editText.addTextChangedListener(mtw);
        return editText;
    }

    public static TextWatcher monetary(final EditText ediTxt) {
        return new TextWatcher() {
            // Mascara monetaria para o preço do produto
            private String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    ediTxt.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[R$,.]", "");

                    double parsed = Double.parseDouble(cleanString);
                    String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    current = formatted.replaceAll("[R$]", "");
                    ediTxt.setText(current);
                    ediTxt.setSelection(current.length());

                    ediTxt.addTextChangedListener(this);
                }
            }

        };
    }
}
