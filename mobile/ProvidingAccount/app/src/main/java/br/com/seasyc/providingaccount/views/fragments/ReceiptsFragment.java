package br.com.seasyc.providingaccount.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.seasyc.providingaccount.R;
import br.com.seasyc.providingaccount.common.CMToken;
import br.com.seasyc.providingaccount.models.Receipt;
import br.com.seasyc.providingaccount.viewmodels.VMReceipt;
import br.com.seasyc.providingaccount.views.adapters.ReceipterAdapter;

public class ReceiptsFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private ReceipterAdapter receipterAdapter;

    private VMReceipt vmReceipt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_receipts, container, false);

        initComponents(root);
        configLayout();
        loadReceipts();
        return root;
    }

    private void initComponents(View root) {
        vmReceipt = new VMReceipt();
        progressBar = root.findViewById(R.id.frg_receipts_pb);
        recyclerView = root.findViewById(R.id.frg_receipts_rv);
    }

    private void configLayout() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
    }

    private void loadReceipts() {
        vmReceipt.all(new CMToken().header(getContext())).observe(getViewLifecycleOwner(), new Observer<List<Receipt>>() {
            @Override
            public void onChanged(List<Receipt> receipts) {
                while (receipts == null) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
                receipterAdapter = new ReceipterAdapter(getContext(), receipts);
                recyclerView.setAdapter(receipterAdapter);
            }
        });
    }

}