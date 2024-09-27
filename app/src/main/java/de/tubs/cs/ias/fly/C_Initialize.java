package de.tubs.cs.ias.fly;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import de.tubs.cs.ias.fly.databinding.InitializeBinding;

public class C_Initialize extends Fragment {

    public static final String DEV_KEY = "AMPLITUDE_DEV_KEY_PLACEHOLDER";
    private InitializeBinding binding;
    private static boolean INITIALIZE_SDK = true;
    private CharSequence text = "3. State: Initialize";

    private MainActivity getMainActivity() {
        return (MainActivity) this.getActivity();
    }

    private void initializeAppsFlyer() {
        if (INITIALIZE_SDK) {
            Context context = getMainActivity().getApplicationContext();
            MainActivity.appsFlyer.init(DEV_KEY, null, context);
            binding.textviewInitialize.setText(text + " [SUCCESS]");
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = InitializeBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonInitialize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeAppsFlyer();
            }
        });

        binding.buttonInitializeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(C_Initialize.this)
                        .navigate(R.id.action_Initialize_to_CoreFunctionality);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
