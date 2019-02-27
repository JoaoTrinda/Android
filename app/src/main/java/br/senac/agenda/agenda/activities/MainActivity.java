package br.senac.agenda.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.senac.agenda.agenda.R;
import br.senac.agenda.agenda.dao.ContatoDAO;
import br.senac.agenda.agenda.dao.EnderecoDAO;
import br.senac.agenda.agenda.model.ContatoEntity;
import br.senac.agenda.agenda.model.EnderecoEntity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera a referencia da lista que tem no layout do aplicativo
        final ListView lista = findViewById(R.id.listaContatos);

        //Cria a lista de contatos como string
        ContatoDAO contatoDAO = new ContatoDAO(this);
        EnderecoDAO enderecoDAO = new EnderecoDAO(this);
        List<ContatoEntity> contatos = contatoDAO.listar();
        List<EnderecoEntity> enderecos = enderecoDAO.listou();

        //para conseguirmos  exibir a lista de listview, preciso criar um adaptador
        ArrayAdapter<ContatoEntity> adapter = new ArrayAdapter<ContatoEntity>(this, android.R.layout.simple_list_item_1, contatos);
        ArrayAdapter<EnderecoEntity> adapter_E = new ArrayAdapter<EnderecoEntity>(this, android.R.layout.simple_list_item_1, enderecos);

        //insere o adaptador na lista de contatos
        lista.setAdapter(adapter);
        //lista.setAdapter(adapter_E);

        //Recuperar o botao e criar acao para ele
        Button novoContato = findViewById(R.id.novoContatoButton);

        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contato = new Intent (MainActivity.this, ContatoActivity.class);
                startActivity(contato);
                finish();
                     }
        });



        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContatoEntity contato = (ContatoEntity) lista.getItemAtPosition(position);

                Intent intentContatoAcitivy = new Intent(MainActivity.this, ContatoActivity.class);
                intentContatoAcitivy.putExtra("contato", contato);
                startActivity(intentContatoAcitivy);
            }
        });

        Button buscarContato = findViewById(R.id.buscarContatoButton);
        buscarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomeEditText = findViewById(R.id.buscarContatoEditText);
                ContatoDAO contatoDAO = new ContatoDAO(MainActivity.this);
                List<ContatoEntity> contatos = contatoDAO.listarPorNome(nomeEditText.getText().toString());
                ArrayAdapter<ContatoEntity> adapter =
                        new ArrayAdapter<ContatoEntity>(MainActivity.this, android.R.layout.simple_list_item_1, contatos);
                lista.setAdapter(adapter);
            }
        });
    }
}
