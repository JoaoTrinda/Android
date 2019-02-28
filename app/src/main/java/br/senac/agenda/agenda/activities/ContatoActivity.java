package br.senac.agenda.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.senac.agenda.agenda.R;
import br.senac.agenda.agenda.dao.ContatoDAO;
import br.senac.agenda.agenda.dao.EnderecoDAO;
import br.senac.agenda.agenda.model.ContatoEntity;
import br.senac.agenda.agenda.model.EnderecoEntity;

public class ContatoActivity extends AppCompatActivity {

    ContatoEntity contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        Intent intent = getIntent();

        contato = (ContatoEntity) intent.getSerializableExtra("contato");

        if(contato != null){

            EditText nomeEditText = findViewById(R.id.nomeEditText);
            EditText telefoneEditText = findViewById(R.id.telefoneEditText);
            RatingBar pontuacaoEditText = findViewById(R.id.pontuacaoRatingBar);
            nomeEditText.setText(contato.getNome());
            telefoneEditText.setText(contato.getTelefone());
            pontuacaoEditText.setProgress(contato.getPontuacao().intValue());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterc = getMenuInflater();
        inflaterc.inflate(R.menu.contato_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.salvar_contato_menun_item:
                EditText nomeEditText = findViewById(R.id.nomeEditText);
                EditText telefoneEditText = findViewById(R.id.telefoneEditText);
                EditText ruaEditText = findViewById(R.id.ruaEditText);
                EditText numeroEditText = findViewById(R.id.numeroEditText);
                EditText cidadeEditText = findViewById(R.id.cidadeEditText);

                RatingBar pontuacaoRatingBar = findViewById(R.id.pontuacaoRatingBar);


                if(contato != null){
                    contato.setNome(nomeEditText.getText().toString());
                   contato.setTelefone(telefoneEditText.getText().toString());
                   contato.setPontuacao(Double.valueOf(pontuacaoRatingBar.getProgress()));

                }else {
                    contato = new ContatoEntity(nomeEditText.getText().toString(),
                            telefoneEditText.getText().toString(),
                            Double.valueOf(pontuacaoRatingBar.getProgress()));
                }
                    EnderecoEntity endereco = new EnderecoEntity(cidadeEditText.getText().toString(),
                            ruaEditText.getText().toString(),
                            numeroEditText.getText().toString());


                ContatoDAO contatoDAO = new ContatoDAO(ContatoActivity.this);
                contatoDAO.salvar(contato);

                EnderecoDAO enderecoDAO = new EnderecoDAO(ContatoActivity.this);
                enderecoDAO.salvar(endereco);

                Toast.makeText(ContatoActivity.this, "Contato Salvo!", Toast.LENGTH_LONG
                ).show();

                Intent main = new Intent(ContatoActivity.this, MainActivity.class);
                startActivity(main);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}


