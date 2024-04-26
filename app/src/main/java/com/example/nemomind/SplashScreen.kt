package com.example.nemomind

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.widget.TextView
import android.util.Log
import com.example.nemomind.databinding.SplashScreenBinding

class SplashScreen : AppCompatActivity() {
    lateinit var binding: SplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = SplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // for full screen in SplashScreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        runSplash()

        val textView = findViewById<TextView>(R.id.textViewAnimated)
        animateText(textView, "Nemo Mind")
    }

    private fun animateText(textView: TextView, text: String) {
        val animator = ValueAnimator.ofInt(0, text.length)
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val newText = try {
                text.substring(0, animatedValue + 1)
            } catch (e: Exception) {
                Log.e("AnimateText", "Error in animateText: animatedValue=$animatedValue, text.length=${text.length}", e)
                ""
            }
            textView.text = newText
        }

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                animateTextSize(textView)
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animator.duration = 3500 // Adjust the animation duration here
        animator.start()
    }

    private fun animateTextSize(textView: TextView) {
        val animator = ValueAnimator.ofFloat(24f, 48f)
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            textView.textSize = animatedValue
        }
        animator.duration = 3000 // Adjust the animation duration here
        animator.start()
    }

    private fun runSplash() {
        // Run SplashScreen
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }, 3000)
    }
}
