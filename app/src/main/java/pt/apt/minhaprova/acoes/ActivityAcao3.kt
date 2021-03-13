package pt.apt.minhaprova.acoes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import pt.apt.minhaprova.R
import pt.apt.minhaprova.databinding.ActivityAcao3Binding
import pt.apt.minhaprova.db.LivroDBOpener

class ActivityAcao3 : AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_acao3)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3);

        val dataBase = LivroDBOpener(this)
        var totalLivros = dataBase.findAll().size
        var id  = 1;

        if(totalLivros >= 1){
            setDados(id, dataBase, totalLivros);

            binding.buttonAnterior.setOnClickListener {
                setDados(--id, dataBase, totalLivros);
            }

            binding.buttonProximo.setOnClickListener {
                setDados(++id, dataBase, totalLivros);
            }
        }else{
            Toast.makeText(this, "Não há livros cadastrados!", Toast.LENGTH_LONG ).show();
        }

    }

    fun setNota(nota:Float){
        if(nota == 0.0F){
            binding.imageViewNota.setImageResource(R.drawable.sem_estrlas);
        }else if(nota == 0.5F){
            binding.imageViewNota.setImageResource(R.drawable.meia_estrlas);
        }else if(nota == 1.0F){
            binding.imageViewNota.setImageResource(R.drawable.um_estrelas);
        }else if(nota == 1.5F){
            binding.imageViewNota.setImageResource(R.drawable.um_e_meia_estrelas);
        }else if(nota == 2.0F){
            binding.imageViewNota.setImageResource(R.drawable.duas_estrelas);
        }else if(nota == 2.5F){
            binding.imageViewNota.setImageResource(R.drawable.duas_e_meia_estrelas);
        }else if(nota == 3.0F){
            binding.imageViewNota.setImageResource(R.drawable.tres_estrlas);
        }else if(nota == 3.5F){
            binding.imageViewNota.setImageResource(R.drawable.tres_e_meia_estrelas);
        }else if(nota == 4.0F){
            binding.imageViewNota.setImageResource(R.drawable.quatro_estrlas);
        }else if(nota == 4.5F){
            binding.imageViewNota.setImageResource(R.drawable.quatro_e_meia_estrlas);
        }else{
            binding.imageViewNota.setImageResource(R.drawable.cinco_estrlas);
        }
    }

    fun setVisibilidade(id : Int, totalLivros :Int) {
        if(totalLivros <= 1){
            binding.buttonAnterior.visibility = View.INVISIBLE;
            binding.buttonProximo.visibility = View.INVISIBLE;
        }else if(id == 1 ){
            binding.buttonAnterior.visibility = View.INVISIBLE;
            binding.buttonProximo.visibility = View.VISIBLE;
        }else if(id == totalLivros){
            binding.buttonAnterior.visibility = View.VISIBLE;
            binding.buttonProximo.visibility = View.INVISIBLE;
        }else{
            binding.buttonAnterior.visibility = View.VISIBLE;
            binding.buttonProximo.visibility = View.VISIBLE;
        }
    }

    fun setDados(id: Int, dataBase: LivroDBOpener, totalLivros: Int){
        var livro = dataBase.findById(id);
        binding.apply {
            editTextTextTitulo.text = "${livro.titulo}"
            editTextTextAutor.text = "${livro.autor}"
            editTextTextAno.text = "${livro.ano}"
        }
        setNota(livro.nota);
        setVisibilidade(id, totalLivros);
    }
}