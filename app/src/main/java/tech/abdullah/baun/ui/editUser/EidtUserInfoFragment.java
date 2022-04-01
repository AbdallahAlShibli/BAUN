package tech.abdullah.baun.ui.editUser;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import tech.abdullah.baun.R;
import tech.abdullah.baun.ui.home.HomeFragment;

public class EidtUserInfoFragment extends Fragment {

    private EidtUserInfoViewModel mViewModel;

    public static EidtUserInfoFragment newInstance() {
        return new EidtUserInfoFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.eidt_user_info_fragment, container, false);
        final Button back = root.findViewById(R.id.back_to_home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager1 = getFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                fragmentTransaction1.replace(R.id.container, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
            }
        });

        return root;
    }

}