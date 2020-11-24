package com.mh.customalert;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.mh.custom_alert.CustomAlert;
import com.mh.custom_alert.Theme;
import com.mh.custom_alert.Type;


public class MainActivity extends AppCompatActivity {

    private CustomAlert alert;

    private CheckBox cbFullAlert;
    private EditText etTitle;
    private EditText etMessage;

    @SuppressLint("SwitchIntDef")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Amboqia_Boriango.otf");
            ((TextView) findViewById(R.id.tvCustomAlert)).setTypeface(font);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cbFullAlert = findViewById(R.id.cbSetFull);
        etTitle = findViewById(R.id.etTitle);
        etMessage = findViewById(R.id.etMessage);

        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_NO:
                ((RadioButton) findViewById(R.id.rbLight)).setChecked(true);
                break;

            case AppCompatDelegate.MODE_NIGHT_YES:
                ((RadioButton) findViewById(R.id.rbDark)).setChecked(true);
                break;

            default:
                ((RadioButton) findViewById(R.id.rbSystem)).setChecked(true);
                break;
        }

        ((RadioGroup) findViewById(R.id.rgTheme)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rbSystem:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;

                    case R.id.rbLight:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;

                    case R.id.rbDark:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                }
            }
        });
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
                setCustomAlertTypePrg();
                break;

            case R.id.btnCustom:
                setCustomAlertType();
                break;
        }
    }

    @SuppressLint("InflateParams")
    public void click_view(View v) {

        switch (v.getId()) {
            case R.id.btnSimple:
                setCustomAlertType(getLayoutInflater().inflate(R.layout.custom_view_simple, null));
                break;

            case R.id.btnLarge:
                setCustomAlertType(getLayoutInflater().inflate(R.layout.custom_view_identity, null));
                break;

            case R.id.btnGif:
                setCustomAlertGif();
                break;
        }
    }

    private void setCustomAlertType(int[] type) {
        alert = new CustomAlert(this, getAppTheme());
        alert.setType(type);
        setTitles();
        showAlert();
    }

    private void setCustomAlertType() {

        alert = new CustomAlert(this, getAppTheme());

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

    private void setCustomAlertTypePrg() {
        alert = new CustomAlert(this, getAppTheme());
        alert.setType(Type.PROGRESS);
        alert.setColor(R.color.custom_color);
        alert.setColorPrg(R.color.colorPrimaryDark);
        setTitles();
        showAlert();
    }

    private void setCustomAlertType(View v) {
        alert = new CustomAlert(this, getAppTheme());
        if (((CheckBox) findViewById(R.id.cbSetIcon)).isChecked()) {
            alert.setIcon(R.drawable.ic_person);
        } else {
            alert.setIcon(null);
        }
        alert.setView(v);
        showAlert();
    }

    private void setCustomAlertGif() {
        alert = new CustomAlert(this, getAppTheme());
        if (((CheckBox) findViewById(R.id.cbSetIcon)).isChecked()) {
            alert.setIcon(R.drawable.ic_android);
        } else {
            alert.setIcon(null);
        }
        alert.setGif(R.drawable.gif_android);
        setTitles();
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
        } else if (((RadioButton) findViewById(R.id.rbOneButton)).isChecked()) {
            alert.setPositiveText(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (((RadioButton) findViewById(R.id.rbTwoButtons)).isChecked()) {
            alert.setNegativeText(getString(R.string.negative_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.negative_button_click), Toast.LENGTH_SHORT).show();
                }
            });
            alert.setPositiveText(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (((RadioButton) findViewById(R.id.rbThreeButtons)).isChecked()) {

            alert.setNeutralText(getString(R.string.neutral_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.neutral_button_click), Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeText(getString(R.string.negative_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.negative_button_click), Toast.LENGTH_SHORT).show();
                }
            });
            alert.setPositiveText(getString(R.string.positive_button), new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alert.dismiss();
                    Toast.makeText(MainActivity.this, getString(R.string.positive_button_click), Toast.LENGTH_SHORT).show();
                }
            });
        }
        alert.show();
    }

    private int getAppTheme() {
        if (((RadioButton) findViewById(R.id.rbLight)).isChecked()) {
            return Theme.LIGHT;
        } else if (((RadioButton) findViewById(R.id.rbDark)).isChecked()) {
            return Theme.DARK;
        } else {
            return Theme.SYSTEM;
        }
    }

    @SuppressLint("InflateParams")
    public void click_happy(View view) {

        final CustomAlert alert = new CustomAlert(this, getAppTheme());
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_person);

        alert.setView(getLayoutInflater().inflate(R.layout.custom_view_simple, null));

        alert.setPositiveText("Leer mas", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert.setView(getLayoutInflater().inflate(R.layout.custom_view_identity, null));
                alert.setIcon(null);

                alert.setNegativeText(getString(R.string.negative_button), new View.OnClickListener() {
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
                                alert.setNegativeText(getString(R.string.negative_button));

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        alert.setType(Type.SUCCESS);
                                        alert.setTitle("Información enviada");
                                        alert.setMessage("Tu información se ha enviado con exito");
                                        alert.setPositiveText(getString(R.string.positive_button_click), new View.OnClickListener() {
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