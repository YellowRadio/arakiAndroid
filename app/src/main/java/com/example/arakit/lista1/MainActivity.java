package com.example.arakit.lista1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //tela_media
    Spinner jspinnerPMU;
    EditText jedtProva;
    EditText jedtProjeto;
    EditText jedtListas;
    Button jbtnCalc;
    Button jbtnLimpar;
    Button jbtnVoltarMed;
    TextView jtxtResult;
    ArrayAdapter<CharSequence> adapter;
    //fim_tela_media

    //tela principal
    Button jbtnTelaMedia;
    Button jbtnTelaSalario;
    //tela principal end;



    //tela_salario
    EditText jedtVlHora,jedtQuantHoras;
    Spinner jspinnerINSS;
    TextView jtxtBruto,jtxtINSS,jtxtLiquido;
    Button jbtnCalcular,jbtnLimparSal,jbtnVoltarSal;
    ArrayAdapter<CharSequence> adaptador_INSS;

    //Fim_tela_salario

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        principio();
    }

    private void rotinas_media(){
        jbtnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double prova,projeto,lista,pmu,result;
                int flag=0;
                prova=Double.valueOf(jedtProva.getText().toString());
                projeto=Double.valueOf(jedtProjeto.getText().toString());
                lista=Double.valueOf(jedtListas.getText().toString());
                pmu=Double.valueOf(jspinnerPMU.getSelectedItem().toString());

                result=(prova*3+projeto*5+lista*2)/10;
                result+=pmu;
                if(result>10)result=10;

                if(result>=6){
                    jtxtResult.setText("APROVADO");
                    jtxtResult.setTextColor(Color.BLUE);
                    flag=0;
                }else if(prova<10 && flag==0){
                    jtxtResult.setText("POR FAVOR REINSIRA A NOTA DA PROVA (SUB)");
                    jtxtResult.setTextColor(Color.DKGRAY);
                    jedtProva.setText("");
                    flag++;
                }else{
                    jtxtResult.setText("REPROVADO");
                    jtxtResult.setTextColor(Color.RED);
                    flag=0;
                }
            }
        });

        jbtnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*jedtProva.setText("");
                jedtListas.setText("");
                jtxtResult.setText("");
                jedtProjeto.setText("");*/
                tela_media();
            }
        });

        jbtnVoltarMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principio();
            }
        });
    }

    private void principio(){
        setContentView(R.layout.principal);
        jbtnTelaMedia= findViewById(R.id.btnTelaMedia);
        jbtnTelaSalario= findViewById(R.id.btnTelaSalario);
        jbtnTelaMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_media();
            }
        });
        jbtnTelaSalario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_salario();
            }
        });
    }

    private void tela_media(){
        setContentView(R.layout.activity_main);
        jspinnerPMU= findViewById(R.id.spinnerPMU);
        adapter= ArrayAdapter.createFromResource(this,R.array.spinnerPMU,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jspinnerPMU.setAdapter(adapter);
        jedtListas= findViewById(R.id.edtListas);
        jedtProjeto= findViewById(R.id.edtProjeto);
        jedtProva= findViewById(R.id.edtProva);
        jbtnCalc= findViewById(R.id.btnCalc);
        jbtnLimpar= findViewById(R.id.btnLimpar);
        jtxtResult= findViewById(R.id.txtResult);
        jbtnVoltarMed=findViewById(R.id.btnVoltarMed);
        rotinas_media();
    }

    private void tela_salario(){
        //aqui inicializar a tela activity_salario.
        setContentView(R.layout.activity_salario);
        //depois inicializar os elementos no JAVA
        jedtVlHora= findViewById(R.id.edtVlHora);
        jedtQuantHoras= findViewById(R.id.edtQuantHoras);
        jspinnerINSS= findViewById(R.id.spinnerINSS);
        adaptador_INSS=ArrayAdapter.createFromResource(this,R.array.spinerINSS,android.R.layout.simple_spinner_item);
        adaptador_INSS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jspinnerINSS.setAdapter(adaptador_INSS);
        jtxtBruto= findViewById(R.id.txtBruto);
        jtxtINSS= findViewById(R.id.txtINSS);
        jtxtLiquido= findViewById(R.id.txtLiquido);
        jbtnCalcular= findViewById(R.id.btnCalcular);
        jbtnLimparSal=findViewById(R.id.btnLimparSal);
        jbtnVoltarSal=findViewById(R.id.btnVoltarSal);
        //depois invocar as rotinas de listener dos elementos desta tela;



        jbtnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //variaveis
                double inss,vlhora,quanthoras,bruto,liquido;


                vlhora=Double.parseDouble(jedtVlHora.getText().toString());
                quanthoras=Double.parseDouble(jedtQuantHoras.getText().toString());
                //System.out.println(jspinnerINSS.getSelectedItem().toString());
                switch(jspinnerINSS.getSelectedItem().toString()){
                    case "PF":
                            inss=0.02;
                            break;
                    case "PJ":
                            inss=0.05;
                            break;
                    case "CLT":
                            inss=0.06;
                            break;
                            default:
                                inss=0;
                }

                bruto=vlhora*quanthoras;
                jtxtINSS.setText(String.valueOf(bruto*inss));
                jtxtBruto.setText(String.valueOf(bruto));
                liquido=bruto-(bruto*inss);
                jtxtLiquido.setText(String.valueOf(liquido));


            }
        });

        jbtnLimparSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tela_salario();
            }
        });

        jbtnVoltarSal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                principio();
            }
        });
    }
}
