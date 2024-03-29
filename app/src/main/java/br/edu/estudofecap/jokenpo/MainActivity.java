package br.edu.estudofecap.jokenpo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView resultado, ganhadas, perdidas;
    EditText inputRodadas;
    Button btnReset;
    int placarVencidas, placarPerdidas, numeroJogadas, numeroRodadas;

    ImageView imgApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.txtResultado);
        imgApp = findViewById(R.id.imgNeutro);

        ganhadas = findViewById(R.id.txtPlacarGanhou);
        perdidas = findViewById(R.id.txtPlacarPerdeu);
        inputRodadas = findViewById(R.id.inputRodadas);

        btnReset = findViewById(R.id.btnReset);

        placarVencidas = 0;
        placarPerdidas = 0;
        numeroJogadas = 0;
    }
    public void selecPedra(View view){
        this.opcaoSelec("pedra");
    }
    public void selecPapel(View view){
        this.opcaoSelec("papel");
    }
    public void selecTesoura(View view){
        this.opcaoSelec("tesoura");
    }

    public void opcaoSelec(String opcaoSelec){

        if(podeJogar()){
            int numero = new Random().nextInt(3);
            String[] opcoes = {"pedra","papel","tesoura"};

            String opcaoApp = opcoes[numero];
            compararJogo(opcaoApp, opcaoSelec);
            setarImagemApp(opcaoApp);
            numeroJogadas++;
            if(!podeJogar()){
                definirVencedor();
                btnReset.setVisibility(View.VISIBLE);
            }
        }

    }

    public void compararJogo(String opcaoApp, String opcaoJogador){
        if(opcaoJogador == opcaoApp){
            resultado.setText("Empate");
        }else if(opcaoJogador == "pedra" && opcaoApp == "papel"){
            perdeu();
        }else if(opcaoJogador == "papel" && opcaoApp == "tesoura"){
            perdeu();
        }else if(opcaoJogador == "tesoura" && opcaoApp == "pedra"){
            perdeu();
        }else{
            ganhou();

        }
    }
    public void setarImagemApp(String opcaoApp){
        switch (opcaoApp){
            case "papel":
                imgApp.setImageResource(R.drawable.img_papel);
                break;
            case "pedra":
                imgApp.setImageResource(R.drawable.img_pedra);
                break;
            case "tesoura":
                imgApp.setImageResource(R.drawable.img_tesoura);
                break;
        }

    }
    public void definirVencedor(){
        if (placarPerdidas > placarVencidas){
            resultado.setText("Você perdeu");
        } else if(placarVencidas == placarPerdidas){
            resultado.setText("Empate no final");
        }else{
            resultado.setText("Você ganhou");
        }
    }
    public void ganhou(){
        placarVencidas++;
        resultado.setText("Ganhou");
        ganhadas.setText("Vencidas:\n" + placarVencidas);
    }
    public void perdeu(){
        placarPerdidas++;
        resultado.setText("Perdeu");
        perdidas.setText("Perdidas:\n" + placarPerdidas);
    }
    public boolean podeJogar(){
        if(!inputRodadas.getText().toString().equals("")){
            numeroRodadas = Integer.parseInt(inputRodadas.getText().toString());
            if(numeroJogadas < numeroRodadas){
                return true;
            }
        }else{
            resultado.setText("Rodadas não definidas");
        }
        return false;
    }

    public void limpar(View view){
        placarVencidas = 0;
        placarPerdidas = 0;
        numeroJogadas = 0;
        imgApp.setImageResource(R.drawable.padrao);
        resultado.setText("Resultado");
        ganhadas.setText("Vencidas:\n" + placarVencidas);
        perdidas.setText("Perdidas:\n" + placarPerdidas);

        btnReset.setVisibility(View.INVISIBLE);
    }
}