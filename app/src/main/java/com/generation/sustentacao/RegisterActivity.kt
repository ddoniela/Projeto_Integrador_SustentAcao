package com.generation.sustentacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val textSignUp = findViewById<TextView>(R.id.textSignUp)
        textSignUp.setOnClickListener {

            startActivity(Intent(this, LoginActivity::class.java))

        }

        val buttonRegister = findViewById<Button>(R.id.buttonRegister)
        buttonRegister.setOnClickListener {

            val editTextEmail = findViewById<TextView>(R.id.editTextEmail)
            val editTextPassword = findViewById<TextView>(R.id.editTextPassword)

            when {
                TextUtils.isEmpty(editTextEmail.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Por favor, insira seu e-mail.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(editTextPassword.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Por favor, insira sua senha.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {

                    val email: String = editTextEmail.text.toString().trim { it <= ' ' }
                    val password: String = editTextPassword.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                if (task.isSuccessful) {

                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Você se registrou com sucesso.",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent =
                                        Intent(this@RegisterActivity, MainActivity::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()

                                } else {
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }


                            })

                }

            }

        }
    }
}