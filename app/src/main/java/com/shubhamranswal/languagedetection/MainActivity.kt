package com.shubhamranswal.languagedetection

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var etLangText:EditText
    lateinit var btnCheckNow:Button
    lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLangText = findViewById(R.id.etLangText)
        btnCheckNow = findViewById(R.id.btnCheckNow)
        tvResult = findViewById(R.id.tvResult)

        btnCheckNow.setOnClickListener{
            val langText:String = etLangText.text.toString()
            if (langText == ""){
                //toast for entering input
            }else{
                detectLang(langText)
            }
        }
    }

    private fun detectLang(langText: String) {
        val languageIdentifier:LanguageIdentifier = LanguageIdentification.getClient()

        languageIdentifier.identifyLanguage(langText)
            .addOnSuccessListener { languageCode ->
                if (languageCode == "und") {
                    tvResult.text = "Can't identify language."
                } else {
                    val locale = Locale(languageCode)
                    tvResult.text = "Language: ${locale.displayLanguage}"
                }
            }
            .addOnFailureListener {
                tvResult.text = "Can't detect it. Reason ${it.message}"
            }


    }
}