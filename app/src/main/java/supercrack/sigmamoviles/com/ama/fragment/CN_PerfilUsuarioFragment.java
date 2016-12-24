package supercrack.sigmamoviles.com.ama.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import supercrack.sigmamoviles.com.ama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CN_PerfilUsuarioFragment extends Fragment {


    public CN_PerfilUsuarioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cn_perfil_usuario, container, false);

        return view;
    }

}
