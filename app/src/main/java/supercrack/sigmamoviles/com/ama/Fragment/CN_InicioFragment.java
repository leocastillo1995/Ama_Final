package supercrack.sigmamoviles.com.ama.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.Activity.CN_Pregunta1Activity;
import supercrack.sigmamoviles.com.ama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CN_InicioFragment extends Fragment {


    @InjectView(R.id.btn_fragmenteninicio_inicio)
    View btn_inicio;

    public CN_InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cn_inicio, container, false);

        ButterKnife.inject(this , view);

        return view;
    }

    @OnClick(R.id.btn_fragmenteninicio_inicio)
    public void inicio()
    {
        Intent intent = new Intent(getContext() , CN_Pregunta1Activity.class);
        startActivity(intent);
    }

}
