 package pt.apt.minhaprova

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import pt.apt.minhaprova.acoes.ActivityAcao1
import pt.apt.minhaprova.acoes.ActivityAcao2
import pt.apt.minhaprova.acoes.ActivityAcao3
import pt.apt.minhaprova.acoes.Dialog
import pt.apt.minhaprova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding;
    lateinit var mainViewModel: MainViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.text1.text = mainViewModel._text1

        val configuracoes = getSharedPreferences("${R.string.app_name}_configuracoes", Context.MODE_PRIVATE);
        val editor = configuracoes.edit();
        val primeira_vez = configuracoes.getBoolean("primeira_vez", true);

        if(primeira_vez){
            Toast.makeText(this, "Bem Vindo!", Toast.LENGTH_LONG ).show();
            editor.putBoolean("primeira_vez", false).apply();
        }

        binding.button1.setOnClickListener {
            val intent = Intent(this, ActivityAcao1::class.java);
            startActivityForResult(intent, 1);
        }

        binding.button2.setOnClickListener {
            val dialog = Dialog();
            dialog.isCancelable = false
            dialog.show(supportFragmentManager,"Dialog")
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, ActivityAcao2::class.java);
            startActivityForResult(intent, 2);
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, ActivityAcao3::class.java);
            startActivityForResult(intent, 3);
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            var aux_result = data?.getStringExtra("RESPOSTA").toString();
            when (requestCode) {
                1 -> {
                    mainViewModel._text1 = aux_result;
                    binding.text1.text = mainViewModel._text1;
                }
                2 -> {
                    binding.text2.text = aux_result;
                }
            }
        }else{
            when (requestCode) {
                1 -> {
                    Snackbar.make(
                        binding.linearLayout,
                        "CANCELADO",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}