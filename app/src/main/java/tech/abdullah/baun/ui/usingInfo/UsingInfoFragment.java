package tech.abdullah.baun.ui.usingInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import tech.abdullah.baun.R;

public class UsingInfoFragment extends Fragment {

    private UsingInfoViewModel usingInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usingInfoViewModel = new ViewModelProvider(this).get(UsingInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_using_info, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        usingInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }
}