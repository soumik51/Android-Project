package com.example.otpverification

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class LoginScreen : AppCompatActivity() {

    lateinit var enterNumber: EditText
    lateinit var getOtpButton: Button
    private lateinit var intent: Intent
    lateinit var progressBar: ProgressBar

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        enterNumber=findViewById(R.id.inputNumber)
        getOtpButton=findViewById(R.id.button_verify_otp)
        progressBar= findViewById(R.id.progress_sending_otp)
        getOtpButton.setOnClickListener{
            val number= enterNumber.text.toString().trim()
            if (number.isNotEmpty()) {
                if (number.length == 10) {
                    progressBar.visibility=View.VISIBLE
                    getOtpButton.visibility=View.INVISIBLE

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91$number",
                        60,
                        TimeUnit.SECONDS,
                        this@LoginScreen,
                        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                                progressBar.visibility=View.GONE
                                getOtpButton.visibility=View.VISIBLE
                            }

                            override fun onVerificationFailed(p0: FirebaseException) {
                                progressBar.visibility=View.GONE
                                getOtpButton.visibility=View.VISIBLE
                                Toast.makeText(this@LoginScreen,"Error Chrck Internet Connection", Toast.LENGTH_SHORT).show()
                            }

                            override fun onCodeSent(
                                p0: String ,
                                p1: PhoneAuthProvider.ForceResendingToken
                            ) {
                                super.onCodeSent(p0, p1)
                                progressBar.visibility=View.GONE
                                getOtpButton.visibility=View.VISIBLE
                                intent = Intent( this@LoginScreen, VerifyOtpTwo::class.java)
                                intent.putExtra("mobile", number)
                                intent.putExtra("backendotp", p0)
                                startActivity(intent)
                            }
                        }
                    )


                }else{
                    Toast.makeText(this, "Please Enter Correct number", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this, "Enter Mobile number", Toast.LENGTH_LONG).show()
            }
        }
    }
}