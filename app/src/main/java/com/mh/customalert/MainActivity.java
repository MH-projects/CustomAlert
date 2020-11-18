package com.mh.customalert;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

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
        customAlert.setType(Type.PROGRESS);
        customAlert.setTitle("Wait!");
        customAlert.setMessage("Your information is uploading...");
        customAlert.setNegativeText("Cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

        alert.setColorTitle(R.color.colorAccent);
        alert.setColorMessage(R.color.colorPrimaryDark);

        alert.setNeutralTextColor(android.R.color.white);
        alert.setNeutralBackColor(R.color.color_neutral);
        alert.setNeutralColorPress(R.color.colorPrimary, 109);

        alert.setNegativeTextColor(android.R.color.white);
        alert.setNegativeBackColor(R.color.color_negative);
        alert.setNegativeColorPress(R.color.colorPrimary, 109);

        alert.setPositiveTextColor(android.R.color.white);
        alert.setPositiveBackColor(R.color.color_positive);
        alert.setPositiveColorPress(R.color.colorPrimary, 109);

        setTitles();
        showAlert();
    }

    private void setCustomAlertTypePrg(int type) {
        alert = new CustomAlert(this);
        alert.setType(type);
        alert.setColor(R.color.custom_color);
        alert.setColorPrg(R.color.colorPrimaryDark);
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
        showAlert();
    }

    private void setTitles() {
        alert.setTitle(etTitle.getText().toString());
        alert.setMessage(etMessage.getText().toString());
    }

    private void showAlert() {

        alert.setCancelable(false);
        alert.setFullAlert(cbFullAlert.isChecked());

        if (((RadioButton) findViewById(R.id.rbSimple)).isChecked()) {

            alert.setNeutralText(null);
            alert.setNegativeText(null);
            alert.setPositiveText(null);

            /*alert.setButton(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                }
            });*/
        } else if (((RadioButton) findViewById(R.id.rbOneButton)).isChecked()) {

            alert.setPositiveText("Accept", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                }
            });

            /*alert.setButton(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                    alert.dismiss();
                }
            });*/
        } else if (((RadioButton) findViewById(R.id.rbTwoButtons)).isChecked()) {

            alert.setNegativeText("Negative", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            alert.setPositiveText("Positive", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            /*alert.setButton(getString(R.string.negative_button), new View.OnClickListener() {
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
            });*/
        } else if (((RadioButton) findViewById(R.id.rbThreeButtons)).isChecked()) {

            alert.setNeutralText("Neutral");
            alert.setNegativeText("Negative");
            alert.setPositiveText("Positive", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("===> ");
                }
            });

            /*alert.setButton(getString(R.string.negative_button), new View.OnClickListener() {
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
            });*/
        }
        alert.show();
    }

    public void click_happy(View view) {

        final CustomAlert alert = new CustomAlert(this);
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_person);

        alert.setView(getLayoutInflater().inflate(R.layout.custom_view_simple, null));

        alert.setPositiveText("Leer mas", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.setView(getLayoutInflater().inflate(R.layout.custom_view_identity, null));
                alert.setIcon(null);

                alert.setNegativeText("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });

                alert.setPositiveText("Enviar información", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alert.setView(null);
                        alert.setType(Type.WARNING);
                        alert.setTitle("Enviar información");
                        alert.setMessage("¿Esta seguro de enviar su información?");

                        alert.setNegativeText("NO");
                        alert.setPositiveText("SI", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                alert.setType(Type.PROGRESS);
                                alert.setTitle("Enviando información");
                                alert.setMessage("Espere un momento por favor");
                                alert.setNegativeText("Cancelar");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alert.setType(Type.SUCCESS);
                                        alert.setTitle("Información enviada");
                                        alert.setMessage("Tu información se ha enviado con exito");
                                        alert.setPositiveText("Aceptar", new View.OnClickListener() {
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