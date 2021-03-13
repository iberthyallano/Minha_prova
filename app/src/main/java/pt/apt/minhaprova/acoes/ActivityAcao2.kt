package pt.apt.minhaprova.acoes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import pt.apt.minhaprova.R
import pt.apt.minhaprova.databinding.ActivityAcao2Binding
import pt.apt.minhaprova.db.Livro
import pt.apt.minhaprova.db.LivroDBOpener

class ActivityAcao2 : AppCompatActivity() {

    lateinit var binding: ActivityAcao2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_acao2)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2);

        binding.buttonSalvar.setOnClickListener {

            var titulo = binding.editTextTextTitulo.text.toString();
            var autor = binding.editTextTextAutor.text.toString();
            var ano = binding.editTextTextAno.text.toString();
            var nota = binding.ratingBarNota.rating;

            if(titulo != "" && autor != "" && ano != ""){
                var novoLivro = Livro(0, titulo, autor, ano.toInt(), nota);
                var database = LivroDBOpener(this);
                var intent = Intent();


                database.insert(novoLivro);
                intent.putExtra("RESPOSTA", "cadastrado");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this, "Preencha todos os campos ou cancele o cadastro!", Toast.LENGTH_LONG ).show();
            }
        }

        binding.buttonCancelar.setOnClickListener {
            val intent = Intent();
            intent.putExtra("VOLTAR", "Cancelado");
            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        }
    }
}