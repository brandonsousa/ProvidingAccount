package br.com.seasyc.providingaccount.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.helpers.Formatters;
import br.com.seasyc.providingaccount.helpers.GenerateKey;
import br.com.seasyc.providingaccount.models.Receipt;
import br.com.seasyc.providingaccount.services.FStorage.Upload;
import br.com.seasyc.providingaccount.util.QuicklyMessage;
import br.com.seasyc.providingaccount.viewmodels.VMReceipt;

import static android.app.Activity.RESULT_OK;

//TODO: store on api
public class NewFragment extends Fragment implements View.OnClickListener {

    private static final int PICK_IMG_REQUEST = 1;

    private TextInputLayout name, price, date, description;
    private Spinner spn_category;
    private ImageView iv_receipt;
    private Button save;

    private Uri uri;

    private Receipt receipt;
    //TODO: instance this
    private VMReceipt vmReceipt;

    private String key;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_new, container, false);

        initComponents(root);
        configFields();
        clickListeners();

        return root;
    }

    private void initComponents(View view) {
        key = GenerateKey.generate();
        name = view.findViewById(R.id.frg_new__edt_name);
        price = view.findViewById(R.id.frg_new__edt_price);
        date = view.findViewById(R.id.frg_new__edt_date);
        description = view.findViewById(R.id.frg_new__edt_description);
        spn_category = view.findViewById(R.id.frg_new_spn_category);
        iv_receipt = view.findViewById(R.id.frg_new_iv_receipt);
        save = view.findViewById(R.id.frg_new_btn_save);
    }

    private void configFields() {
        Formatters.date(date.getEditText());
        price.getEditText().addTextChangedListener(Formatters.monetary(price.getEditText()));
    }

    private void clickListeners() {
        iv_receipt.setOnClickListener(this);
        save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frg_new_btn_save:
                store();
                break;
            case R.id.frg_new_iv_receipt:
                loadImage();
                break;
        }
    }

    private void store() {
        if (verifyFilds()) {
            setReceipt();
            QuicklyMessage.toast(getContext(), receipt.toString());
        }
    }

    private boolean verifyFilds() {
        if (name.getEditText().getText().length() < 5) {
            name.setError("Invalid name, required more than 5 characters");
            return false;
        } else if (price.getEditText().getText().length() < 3) {
            price.setError("Invalid price, required more than 3 characters");
            return false;
        } else if (date.getEditText().getText().length() < 10) {
            date.setError("Invalid date");
            return false;

        } else if (description.getEditText().getText().length() < 10) {
            description.setError("Invalid description, required more then 10 characters");
            return false;
        }
        if (uri == null) {
            QuicklyMessage.toast(getContext(), "Required image");
            return false;
        }
        name.setError(null);
        price.setError(null);
        date.setError(null);
        description.setError(null);
        return true;
    }

    private void setReceipt() {
        receipt = new Receipt();

        addListenerOnSpinnerItemSelection();
        receipt.setKey(key);
        receipt.setName(name.getEditText().getText().toString());
        receipt.setDescription(description.getEditText().getText().toString());
        receipt.setPrice(price.getEditText().getText().toString());
        receipt.setDate(Formatters.unformat(date.getEditText().getText().toString()));
        //TODO: add user code
        receipt.setImg_url("https://firebasestorage.googleapis.com/v0/b/providingaccount.appspot.com/o/" + 1 + "%2F2" +
                key + "." + Upload.getExtension(uri, getContext()) + "?alt=media");


    }

    private void loadImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            uri = data.getData();
            Glide.with(this).load(uri).into(iv_receipt);

        }
    }

    public void addListenerOnSpinnerItemSelection() {
        spn_category.setOnItemSelectedListener(new NewFragment.SpinnerOnItemSelectedListener());
        receipt.setCategory(String.valueOf(spn_category.getSelectedItem()));
    }

    public class SpinnerOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            receipt.setCategory(String.valueOf(spn_category.getSelectedItem()));
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }

    }
}