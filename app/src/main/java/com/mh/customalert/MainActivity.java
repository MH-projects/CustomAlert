package com.mh.customalert;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.mh.custom_alert.CustomAlert;
import com.mh.custom_alert.Type;

public class MainActivity extends AppCompatActivity {

    private CustomAlert alert;

    private CheckBox cbFullAlert;
    private EditText etTitle;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbFullAlert = findViewById(R.id.cbSetFull);
        etTitle = findViewById(R.id.etTitle);
        etMessage = findViewById(R.id.etMessage);

        final CustomAlert customAlert = new CustomAlert(this);
        customAlert.setType(Type.WARNING);
        customAlert.setFullAlert(true);
        customAlert.setTitle("Attention!");
        customAlert.setMessage("Are you sure to log out?");
        customAlert.setButton("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customAlert.dismiss();
            }
        });
        customAlert.show();

    }

    public void click_type(View v) {

        switch (v.getId()) {
            case R.id.btnSuccess:
                setCustomAlertType(Type.SUCCESS);
                break;

            case R.id.btnFail:
                setCustomAlertType(Type.FAIL);
                break;

            case R.id.btnWarning:
                setCustomAlertType(Type.WARNING);
                break;

            case R.id.btnPrg:
                setCustomAlertTypePrg(Type.PROGRESS);
                break;

            case R.id.btnCustom:
                setCustomAlertType();
                break;
        }
    }

    public void click_view(View v) {

        switch (v.getId()) {
            case R.id.btnSimple:
                setCustomAlertType(getLayoutInflater().inflate(R.layout.custom_view_simple, null));
                break;

            case R.id.btnLarge:
                setCustomAlertType(getLayoutInflater().inflate(R.layout.custom_view_identity, null));
                break;

            case R.id.btnGif:
                break;
        }
    }

    private void setCustomAlertType(int[] type) {
        alert = new CustomAlert(this);
        alert.setType(type);
        setTitles();
        showAlert();
    }

    private void setCustomAlertType() {
        alert = new CustomAlert(this);
        alert.setColor(R.color.custom_color);
        alert.setIcon(R.drawable.ic_android);
        setTitles();
        showAlert();
    }

    private void setCustomAlertTypePrg(int type) {
        alert = new CustomAlert(this);
        alert.setType(type);
        setTitles();
        showAlert();
    }

    private void setCustomAlertType(View v) {
        alert = new CustomAlert(this);

        if (((CheckBox) findViewById(R.id.cbSetIcon)).isChecked()) {
            alert.setIcon(R.drawable.ic_person);
        } else {
            alert.setIcon(null);
        }

        alert.setView(v);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        alert.setHeight(height);
        showAlert();
    }

    private void setTitles() {
        alert.setTitle(etTitle.getText().toString());
        alert.setMessage(etMessage.getText().toString());
    }

    private void showAlert() {

        alert.setCancelable(false);
        alert.setFullAlert(cbFullAlert.isChecked());

        if (((RadioButton) findViewById(R.id.rbOneButton)).isChecked()) {

            alert.setButton(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                }
            });
        } else if (((RadioButton) findViewById(R.id.rbTwoButtons)).isChecked()) {
            alert.setButtons(getString(R.string.negative_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.negative_button_click), Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                }
            }, getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                }
            });
        }
        alert.show();
    }

    public void click_happy(View view) {

        final CustomAlert alert = new CustomAlert(this);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_person);

        alert.setView(getLayoutInflater().inflate(R.layout.custom_view_simple, null));

        alert.setButton("Leer mas", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.setView(getLayoutInflater().inflate(R.layout.custom_view_identity, null));
                alert.setIcon(null);
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int height = metrics.heightPixels;
                alert.setHeight(height);

                alert.setButtons("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                }, "Enviar información", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alert.setView(null);
                        alert.setType(Type.WARNING);
                        alert.setTitle("Enviar información");
                        alert.setMessage("¿Esta seguro de enviar su información?");

                        alert.setButtons("NO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                alert.dismiss();
                            }
                        }, "SI", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                alert.setType(Type.PROGRESS);
                                alert.setTitle("Enviando información");
                                alert.setMessage("Espere un momento por favor");
                                alert.setButton("Cancelar", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        alert.dismiss();
                                    }
                                });

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alert.setType(Type.SUCCESS);
                                        alert.setTitle("Información enviada");
                                        alert.setMessage("Tu información se ha enviado con exito");
                                        alert.setButton("Aceptar", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                alert.dismiss();
                                            }
                                        });
                                    }
                                }, 3000);
                            }
                        });

                    }
                });

            }
        });

        alert.show();


    }
}