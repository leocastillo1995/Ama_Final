package supercrack.sigmamoviles.com.ama.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import supercrack.sigmamoviles.com.ama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CN_AcercaDeFragment extends Fragment{

    @InjectView(R.id.btn_fragmentacercade_correo)
    View btn_correo;

    @InjectView(R.id.btn_fragmentacercade_pagina)
    View btn_pagina;

    @InjectView(R.id.btn_fragmentacercade_sisol)
    View btn_sisol;

    @InjectView(R.id.btn_fragmentacercade_infosalud)
    View btn_infosalud;

    @InjectView(R.id.btn_fragmentacercade_cunamas)
    View btn_cunama;

    @InjectView(R.id.btn_fragmentacercade_segurosalud)
    View btn_segurointegral;


    public CN_AcercaDeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cn_acerca_de, container, false);

        ButterKnife.inject(this , view);

        return view;
    }

    @OnClick(R.id.btn_fragmentacercade_correo)
    public void correo()
    {
        Intent i = new Intent(Intent.ACTION_SEND);

        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL , new String[]{"ama@amaeducation.com"});
        i.putExtra(Intent.EXTRA_SUBJECT , "");
        i.putExtra(Intent.EXTRA_TEXT , "");

        startActivity(Intent.createChooser(i , "Seleccione como desea mandar el mensaje"));
    }

    @OnClick(R.id.btn_fragmentacercade_pagina)
    public void pagina()
    {
        Uri url = Uri.parse("http://www.amaeducation.com/");

        Intent intent = new Intent(Intent.ACTION_VIEW , url);

        startActivity(intent);
    }

    @OnClick(R.id.btn_fragmentacercade_sisol)
    public void sisol()
    {
        Uri url = Uri.parse("http://www.sisol.gob.pe/");

        Intent intent = new Intent(Intent.ACTION_VIEW , url);

        startActivity(intent);
    }

    @OnClick(R.id.btn_fragmentacercade_infosalud)
    public void infosalud()
    {
        Uri uri = Uri.parse("tel:080010828");

        Intent intent = new Intent(Intent.ACTION_CALL , uri);

        startActivity(intent);
    }

    @OnClick(R.id.btn_fragmentacercade_cunamas)
    public void cunamas()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final String[] list = {"visitar pagina" , "Realizar llamada"};

        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(list[i].equals("visitar pagina"))
                {
                    Uri url = Uri.parse("http://www.cunamas.gob.pe/");

                    Intent intent = new Intent(Intent.ACTION_VIEW , url);

                    startActivity(intent);
                }
                else
                {
                    Uri uri = Uri.parse("tel:017482000");

                    Intent intent = new Intent(Intent.ACTION_CALL , uri);

                    startActivity(intent);
                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @OnClick(R.id.btn_fragmentacercade_segurosalud)
    public void segurointegral()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final String[] list = {"visitar pagina" , "Realizar llamada"};

        builder.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(list[i].equals("visitar pagina"))
                {
                    Uri url = Uri.parse("http://www.sis.gob.pe/");

                    Intent intent = new Intent(Intent.ACTION_VIEW , url);

                    startActivity(intent);
                }
                else
                {
                    Uri uri = Uri.parse("tel:015145555");

                    Intent intent = new Intent(Intent.ACTION_CALL , uri);

                    startActivity(intent);
                }

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
