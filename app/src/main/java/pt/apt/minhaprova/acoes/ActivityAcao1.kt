package pt.apt.minhaprova.acoes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import pt.apt.minhaprova.R
import pt.apt.minhaprova.databinding.ActivityAcao1Binding

class ActivityAcao1 : AppCompatActivity() {

    lateinit var binding: ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_acao1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1);

        binding.buttonOk.setOnClickListener {
            var intent = Intent();
            var resposta = binding.editTextText.text.toString();
            if(resposta != ""){
                intent.putExtra("RESPOSTA", resposta);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(this, "Coloque uma resposta ou cancele o teste.", Toast.LENGTH_LONG ).show()
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