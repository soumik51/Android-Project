package com.example.otpverification

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class VerifyOtpTwo : AppCompatActivity() {
    lateinit var inputotp1: EditText
    lateinit var inputotp2: EditText
    lateinit var inputotp3: EditText
    lateinit var inputotp4: EditText
    lateinit var inputotp5: EditText
    lateinit var inputotp6: EditText
    lateinit var textView: TextView
    lateinit var verifyOtpButton: Button
    lateinit var getOtpBackend:String
    lateinit var enterCodeOtp: String
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_verify_otp_two)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        verifyOtpButton=findViewById(R.id.button_submit)
        inputotp1=findViewById(R.id.inputotp1)
        inputotp2=findViewById(R.id.inputotp2)
        inputotp3=findViewById(R.id.inputotp3)
        inputotp4=findViewById(R.id.inputotp4)
        inputotp5=findViewById(R.id.inputotp5)
        inputotp6=findViewById(R.id.inputotp6)

        textView= findViewById(R.id.showNumber)
        textView.text = String.format("+91-%s", intent.getStringExtra("mobile"))
//        val mobileNumber = intent.getStringExtra("mobile")
//        if (mobileNumber != null) {
//            textView.text = String.format("+91-%s", mobileNumber)
//        } else {
//            textView.text = "+91-"
//            Toast.makeText(this, "Mobile number is missing", Toast.LENGTH_LONG).show()
//        }
        getOtpBackend=intent.getStringExtra("backendotp")?:""
        if(getOtpBackend.isEmpty()){
            Toast.makeText(this, "Backend Otp Missing", Toast.LENGTH_SHORT).show()
            return
        }
        verifyOtpButton.setOnClickListener{
            if (inputotp1.text.toString().isNotEmpty() &&
                inputotp2.text.toString().isNotEmpty() &&
                inputotp3.text.toString().isNotEmpty() &&
                inputotp4.text.toString().isNotEmpty() &&
                inputotp5.text.toString().isNotEmpty() &&
                inputotp6.text.toString().isNotEmpty()){

                enterCodeOtp = inputotp1.text.toString()+
                        inputotp2.text.toString()
//                Toast.makeText(this, "OTP Verify", Toast.LENGTH_LONG).show()
            } else{
                Toast.makeText(this, "Please Enter all number", Toast.LENGTH_LONG).show()
            }
        }

        numberotpmove();
    }

    private fun numberotpmove() {
        inputotp1.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()){
                    inputotp2.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        inputotp2.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()){
                    inputotp3.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        inputotp3.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()){
                    inputotp4.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        inputotp4.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()){
                    inputotp5.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

        inputotp5.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().trim().isNotEmpty()){
                    inputotp6.requestFocus()
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}