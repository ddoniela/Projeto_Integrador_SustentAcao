package com.generation.sustentacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textCreate = findViewById<TextView>(R.id.textCreate)
        textCreate.setOnClickListener {

            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        buttonLogin.setOnClickListener {

            val editTextEmailLogin = findViewById<TextView>(R.id.editTextEmailLogin)
            val editTextPasswordLogin = findViewById<TextView>(R.id.editTextPasswordLogin)

            when {
                TextUtils.isEmpty(editTextEmailLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Por favor, insira seu e-mail.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(editTextPasswordLogin.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Por favor, insira sua senha.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {

                    val email: String = editTextEmailLogin.text.toString().trim { it <= ' ' }
                    val password: String = editTextPasswordLogin.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Você logou com sucesso.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()

                                } else {
                                    Toast.makeText(
                                        this@LoginActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            }

                }

            }

        }
    }
}

